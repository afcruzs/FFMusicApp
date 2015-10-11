package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffmusic.backend.ffMusicApi.model.User;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.EndpointsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertUserAsyncTask;

public class HomeFragment extends Fragment {

    public static final String TITLE = "title";

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
        final HomeTabsPagerAdapter adapter = new HomeTabsPagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }


    @Override
    public void onResume(){
        super.onResume();
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Pecora UN"));
    }





}
