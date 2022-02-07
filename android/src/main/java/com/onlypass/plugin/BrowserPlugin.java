package com.onlypass.plugin;

import android.net.Uri;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.WebColor;
import com.getcapacitor.JSObject;
import java.util.*;

@CapacitorPlugin(name = "Onlypass")
public class BrowserPlugin extends Plugin {

    private Browser implementation;

    public void load() {
        implementation = new Browser(getContext());
        implementation.setBrowserEventListener(this::onBrowserEvent);
    }

    @PluginMethod
    public void PayNow(PluginCall call) {
        // get the URL
        String urlString = "http://api.marshalsoft.pro/v1.0/plugin/";//call.getString("url");
        String amount = call.getString("amount");
        String gateway = call.getString("gateway");
        String apikey = call.getString("apikey");
        String memo = call.getString("memo");
        String str = "{\"amount\":\""+amount+"\",";
        str = str+"\"gateway\":\""+gateway+"\",";
        str = str+"\"apikey\":\""+apikey+"\",";
        str = str+"\"memo\":\""+memo+"\"}";
        urlString = urlString+str;
        if (urlString == null) {
            call.reject("Must provide a URL to open");
            return;
        }
        if (urlString.isEmpty()) {
            call.reject("URL must not be empty");
            return;
        }
        Uri url;
        try {
            url = Uri.parse(urlString);
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
            return;
        }

        // get the toolbar color, if provided
        String colorString = call.getString("toolbarColor");
        Integer toolbarColor = null;
        if (colorString != null) try {
            toolbarColor = WebColor.parseColor(colorString);
        } catch (IllegalArgumentException ex) {
            Logger.error(getLogTag(), "Invalid color provided for toolbarColor. Using default", null);
        }
        // hex call
        // open the browser and finish
        
        implementation.open(url, toolbarColor);
        call.resolve();
    }

    @PluginMethod
    public void close(PluginCall call) {
        call.unimplemented();
    }

    @Override
    protected void handleOnResume() {
        if (!implementation.bindService()) {
            Logger.error(getLogTag(), "Error binding to custom tabs service", null);
        }
    }

    @Override
    protected void handleOnPause() {
        implementation.unbindService();
    }

    void onBrowserEvent(int event) {
        switch (event) {
            case Browser.BROWSER_LOADED:
                notifyListeners("browserPageLoaded", null);
                break;
            case Browser.BROWSER_FINISHED:
                notifyListeners("browserFinished", null);
                break;
        }
    }
    
}

