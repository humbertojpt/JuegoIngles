package com.humbertojpt.juegoingles.PracticarFragments;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.humbertojpt.juegoingles.EstudiarFragments.EstudiarPagerAdapter;
import com.humbertojpt.juegoingles.R;

public class Practicar extends AppCompatActivity {

    PracticarPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicar);


        mSectionsPagerAdapter = new PracticarPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
