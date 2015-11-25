package com.humbertojpt.juegoingles.EvaluarFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Client on 11/19/2015.
 */
public class EvaluarPagerAdapter  extends FragmentPagerAdapter {
    public EvaluarPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return EvaluarF1.newInstance(position + 1);
        else{
            if (position == 1)
                return EvaluarF2.newInstance(position + 1);
            else{
                if (position == 2)
                    return EvaluarF3.newInstance(position + 1);
                else{
                    if (position == 3)
                        return EvaluarF4.newInstance(position + 1);
                    else{
                        return EvaluarF5.newInstance(position + 1);
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
