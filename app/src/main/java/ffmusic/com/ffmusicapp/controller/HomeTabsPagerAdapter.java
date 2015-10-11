package ffmusic.com.ffmusicapp.controller;

/**
 * Created by Milder on 11/10/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HomeTabsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public HomeTabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RoomsFragment myRooms = new RoomsFragment();
                return myRooms;
            case 1:
                RoomsFragment otherRooms = new RoomsFragment();
                return otherRooms;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
