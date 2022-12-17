package com.redttg.Icons;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class Icons implements IXposedHookInitPackageResources {
    ImageView wifiIndicator;
    ImageView mobileIndicator;
    Drawable wifi_no_0;
    Drawable wifi_no_1;
    Drawable wifi_no_2;
    Drawable wifi_no_3;
    Drawable wifi_no_4;
    Drawable wifi_signal_0;
    Drawable wifi_signal_1;
    Drawable wifi_signal_2;
    Drawable wifi_signal_3;
    Drawable wifi_signal_4;

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.android.systemui")) {
            return;
        }
        // Load icons
        wifi_no_0 = resparam.res.getDrawable(R.drawable.ic_no_internet_wifi_signal_0);


        String layoutName = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? "status_bar_wifi_group" : "signal_cluster_view";
        resparam.res.hookLayout("com.android.systemui", "layout", layoutName, new XC_LayoutInflated() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                wifiIndicator = liparam.view.findViewById(liparam.res.getIdentifier("wifi_signal", "id", "com.android.systemui"));
                wifiIndicator.setImageDrawable(wifi_no_0);
                wifiIndicator.setRotation(180);
            }
        });
//        resparam.res.hookLayout("com.android.systemui", "layout", "status_bar_mobile_signal_group", new XC_LayoutInflated() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                mobileIndicator = liparam.view.findViewById(liparam.res.getIdentifier("mobile_signal", "id", "com.android.systemui"));
//                mobileIndicator.setRotation(180);
//            }
//        });
    }
}
