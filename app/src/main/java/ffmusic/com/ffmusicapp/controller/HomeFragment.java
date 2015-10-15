package ffmusic.com.ffmusicapp.controller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ffmusic.com.ffmusicapp.R;

public class HomeFragment extends Fragment {

    public static final String TITLE = "title";
    private FragmentManager fragmentManaget;
    private SectionsPagerAdapter adapter;


    public void setFragmentManager(FragmentManager fm){
        this.fragmentManaget = fm;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String title = getArguments().getString(TITLE);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.home_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home_tabs_my_rooms));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home_tabs_other_rooms));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_pager);

        setupViewPager(viewPager, fragmentManaget);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("TEST", "Here: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("TEST", "Here2: " + tab.getPosition());
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    /**
     * Crea una instancia del view pager con los datos
     * predeterminados
     *
     * @param viewPager Nueva instancia
     */
    private void setupViewPager(ViewPager viewPager,FragmentManager xd) {
        adapter = new SectionsPagerAdapter(xd);
        adapter.addFragment(RoomsFragment.newInstance(1), getString(R.string.home_tabs_my_rooms));
        adapter.addFragment(RoomsFragment.newInstance(2), getString(R.string.home_tabs_other_rooms));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume(){

        super.onResume();
        getFragmentManager();
    }



    /**
     * Un {@link FragmentPagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


}
