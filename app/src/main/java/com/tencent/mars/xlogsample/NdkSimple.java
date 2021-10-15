package com.tencent.mars.xlogsample;

import android.util.Log;

import java.util.UUID;

/**
 * 两点思考 1 如何使用别人so 2，动态加载so 测试
 */
public class NdkSimple {
    // Used to load the 'native-lib' library on application startup.
    static {
        //
        System.loadLibrary("native-lib");
        // System.loadLibrary ：android 加载apk中的libs目录下 .so 库
        // System.load : 加载一个具体路径上的 .so 库，去服务器上下载再进行加载(data/data)
        // System.load("C:/Users/hcDarren/Desktop/android/NDK/NDK_Day12/x64/Debug/NDK_Day12.dll");
    }

    public String name = "wangweijun";

    public static int age = 24;

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * c 层生成密钥返回java层
     * @return
     */
    public static native String getSingnaturePassword();

    /**
     * c修改java对象的非静态属性
     */
    public native void changeName();

    /**
     * c修改java对象的静态属性
     */
    public static native void changeAge();

    /**
     * c调用java对象方法
     */
    public native void callAddMethod();

    /**
     * c调用java对象方法(传参)
     */
    public native void callAddMethod2(int num1, int num2);

    /**
     * 1 java 层定义nativie方法，c 去实现(传给c参数有Jobject(java中定义的非static native方法)
     * 或 Jclass(如果java中定义的static native方法))
     * java 调用c 创建point对象
     * @return
     */
    public native static Point createPoint();

    // 小的思考：静态获取 uuid 的方法，然后再 c 调用这个方法获取uuid
    public static String getUUID() {
        String randomStr =  UUID.randomUUID().toString();
        Log.i(MainActivity.TAG, "randomStr: "+randomStr);
        return randomStr;
    }
}
