package io.gleap;

import android.app.Application;

public class MainApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Gleap.initialize("7qnF4SaW8daomwcBLdXAd8ahlIYJtxos",this);
    }
}
