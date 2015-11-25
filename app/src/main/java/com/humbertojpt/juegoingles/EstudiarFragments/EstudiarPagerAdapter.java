package com.humbertojpt.juegoingles.EstudiarFragments;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.humbertojpt.juegoingles.R;

import java.util.Locale;

/**
 * Created by Client on 11/18/2015.
 */
public class EstudiarPagerAdapter extends FragmentPagerAdapter {

    public EstudiarPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        
        if (position == 0)
            return EstudiarF1.newInstance(position + 1);
        else{
            if (position == 1)
                return EstudiarF2.newInstance(position + 1);
            else{
                if (position == 2)
                    return EstudiarF3.newInstance(position + 1);
                else{
                    if (position == 3)
                        return EstudiarF4.newInstance(position + 1);
                    else{
                        return EstudiarF5.newInstance(position + 1);
                    }
                }
            }
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        Resources res = Resources.getSystem();
        switch (position) {
            case 0:
                return res.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return res.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return res.getString(R.string.title_section3).toUpperCase(l);
            case 3:
                return res.getString(R.string.title_section4).toUpperCase(l);
            case 4:
                return res.getString(R.string.title_section5).toUpperCase(l);
        }
        return null;
    }
}
