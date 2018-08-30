package id.chirikualii.catalogmovieuiux.presentasion.ui.main.adapter;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.chirikualii.catalogmovieuiux.presentasion.ui.nowplaying.NowPlayingFragment;
import id.chirikualii.catalogmovieuiux.presentasion.ui.upcoming.UpcomingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private NavigationView navigationView;

    public ViewPagerAdapter(FragmentManager fm, NavigationView navigationView) {
        super(fm);
        this.navigationView = navigationView;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:

                fragment = new NowPlayingFragment();
                break;
            case 1:
                fragment = new UpcomingFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
