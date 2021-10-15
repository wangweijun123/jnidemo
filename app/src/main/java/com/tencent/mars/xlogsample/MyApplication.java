package com.tencent.mars.xlogsample;

import android.app.Application;
import android.os.Environment;

import com.tencent.mars.BuildConfig;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

public class MyApplication extends Application {

    static {
        System.loadLibrary("stlport_shared");
        System.loadLibrary("marsxlog");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final String SDCARD = getCacheDir().getAbsolutePath();
        final String logPath = SDCARD + "/marssample/log";

// this is necessary, or may crash for SIGBUS
        final String cachePath = this.getFilesDir() + "/xlog";

//init xlog
        if (BuildConfig.DEBUG) {
            Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "MarsSample", 0, "");
            Xlog.setConsoleLogOpen(true);

        } else {
            Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cachePath, logPath, "MarsSample", 0, "");
            Xlog.setConsoleLogOpen(false);
        }
        Log.setLogImp(new Xlog());
    }
}
