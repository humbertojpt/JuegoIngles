package com.humbertojpt.juegoingles.EvaluarFragments;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.humbertojpt.juegoingles.EstudiarFragments.EstudiarPagerAdapter;
import com.humbertojpt.juegoingles.R;

public class Evaluar extends AppCompatActivity {

    public static int calif;
    EvaluarPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluar);
        calif =0;
        mSectionsPagerAdapter = new EvaluarPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
