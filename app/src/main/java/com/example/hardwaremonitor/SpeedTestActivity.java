package com.example.hardwaremonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SpeedTestActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_test);

        wv = (WebView) findViewById(R.id.WebView);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.loadUrl("https://beta.speedtest.net/");

    }

    public void BTN_Back(View view) {
        Intent BTN_Back = new Intent(SpeedTestActivity.this, MainActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void View_Battery(View view) {
        Intent View_Battery = new Intent(SpeedTestActivity.this, BatteryActivity.class);
        this.finish();
        startActivity(View_Battery);
    }

    public void View_Network(View view) {
        Intent View_Network = new Intent(SpeedTestActivity.this, netWork.class);
        this.finish();
        startActivity(View_Network);
    }

    public void View_SimInfo(View view) {
        Intent View_SimInfo = new Intent(SpeedTestActivity.this, DisplayInfo.class);
        this.finish();
        startActivity(View_SimInfo);
    }

}