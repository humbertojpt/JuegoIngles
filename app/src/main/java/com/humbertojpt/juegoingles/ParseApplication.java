package com.humbertojpt.juegoingles;

import android.app.Application;

import com.parse.Parse;
//import com.parse.ParseCrashReporting;

/**
 * Created by Client on 10/26/2015.
 */
public class ParseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //ParseCrashReporting.enable(this);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Voqh1jl3kvAqIAp0LEkqSHP8dJDYfJxl1I2eZSNE", "ZZCIPUYKDhFuNvHl8tRqWEOIGQKHQzD8e6jlDIky");
    }
}
