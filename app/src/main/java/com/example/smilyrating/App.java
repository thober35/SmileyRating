package com.example.smilyrating;

import android.app.Application;
import android.util.Log;

/**
 * Created by sujith on 15/10/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("App", "Smilyrating");
    }
}
