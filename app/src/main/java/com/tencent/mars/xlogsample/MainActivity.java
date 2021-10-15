package com.tencent.mars.xlogsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "wangweijun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(NdkSimple.getSingnaturePassword());

        NdkSimple ndkSimple = new NdkSimple();
        Log.i(TAG, "c修改前 "+ndkSimple.name);
        ndkSimple.changeName();
        Log.i(TAG, "c修改后 "+ndkSimple.name);

        Log.i(TAG, "c修改前age= "+NdkSimple.age);
        NdkSimple.changeAge();
        Log.i(TAG, "c修改后age= "+ndkSimple.age);
        ndkSimple.callAddMethod();

        ndkSimple.callAddMethod2(10, 30);
        Point point = NdkSimple.createPoint();
        Log.i(TAG, "point: x = "+point.getX()+" , y = "+point.getY());
    }
}
