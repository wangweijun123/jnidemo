package com.tencent.mars.xlogsample;

import android.app.Application;
import android.os.Environment;

public class MyApplication extends Application {

    /*static {
        System.loadLibrary("stlport_shared");
        System.loadLibrary("marsxlog");
    }*/

    @Override
    public void onCreate() {
        super.onCreate();

        //initXLOG();
    }

    private void initXLOG() {
        /*final String SDCARD = getCacheDir().getAbsolutePath();
        final String logPath = SDCARD + "/marssample/log";

        final String cachePath = this.getFilesDir() + "/xlog";

        if (BuildConfig.DEBUG) {
            Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "MarsSample", 0, "");
            Xlog.setConsoleLogOpen(true);

        } else {
            Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cachePath, logPath, "MarsSample", 0, "");
            Xlog.setConsoleLogOpen(false);
        }
        Log.setLogImp(new Xlog());*/
    }


}
