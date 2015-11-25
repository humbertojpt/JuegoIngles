package com.humbertojpt.juegoingles.PracticarFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Client on 11/19/2015.
 */
public class PracticarPagerAdapter extends FragmentPagerAdapter {
    public PracticarPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return PracticarF1.newInstance(position + 1);
        else{
            if (position == 1)
                return PracticarF2.newInstance(position + 1);
            else{
                if (position == 2)
                    return PracticarF3.newInstance(position + 1);
                else{
                    if (position == 3)
                        return PracticarF4.newInstance(position + 1);
                    else{
                        return PracticarF5.newInstance(position + 1);
                    }
                }
            }
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
