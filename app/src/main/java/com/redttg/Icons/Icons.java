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
    Drawable wifiBackground = Drawable.createFromPath("/storage/emulated/0/icon.png");

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.android.systemui")) {
            return;
        }
        String layoutName = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? "status_bar_wifi_group" : "signal_cluster_view";
        resparam.res.hookLayout("com.android.systemui", "layout", layoutName, new XC_LayoutInflated() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                wifiIndicator = liparam.view.findViewById(liparam.res.getIdentifier("wifi_signal", "id", "com.android.systemui"));
                wifiIndicator.setRotation(180);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    wifiIndicator.setBackground(wifiBackground);
                }
            }
        });
    }
}
