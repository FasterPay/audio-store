package com.paymentwall.sample;

import com.paymentwall.sample.activity.tabs.ControllerFactory;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;

public class DemoApplication extends Application {
    public ControllerFactory mControllerFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        mControllerFactory = new ControllerFactory(this);
    }
}
