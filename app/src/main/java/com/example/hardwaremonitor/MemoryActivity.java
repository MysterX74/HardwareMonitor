package com.example.hardwaremonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MemoryActivity extends AppCompatActivity {

    ProgressBar barmemory;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);


    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);

                getMemoryInfo();
            }
        }, delay);
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
    }

    private void getMemoryInfo(){
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);

        Runtime runtime = Runtime.getRuntime();

        String MemoireTel = String.valueOf(memoryInfo.totalMem);
        MemoireTel = MemoireTel.substring(0,4);
        String MemoireDispo = String.valueOf(memoryInfo.availMem);
        MemoireDispo= MemoireDispo.substring(0,4);
        String MAXRAM = String.valueOf(runtime.maxMemory());
        MAXRAM= MAXRAM.substring(0,4);
        String TOTALRAM = String.valueOf(runtime.totalMemory());
        TOTALRAM= TOTALRAM.substring(0,4);
        String FREERAM = String.valueOf(runtime.freeMemory());
        FREERAM= FREERAM.substring(0,4);


        StringBuilder builder = new StringBuilder();
        builder.append("RAM Total : ").append(MemoireTel).append("MB \n").
                append("RAM Disponible : ").append(MemoireDispo).append("MB \n")/*.
                append("RAM Max : ").append(MAXRAM).append("MB \n").
                append("RAM Total : ").append(TOTALRAM).append("MB \n").
                append("RAM Free : ").append(FREERAM).append("MB \n")*/;

        TextView TV_infoMemory = (TextView) findViewById(R.id.TV_infoMemory);
        barmemory = (ProgressBar) findViewById(R.id.ProgressBarMemory);
        TV_infoMemory.setText(builder.toString());

        int progressbarmax = Integer.parseInt(MemoireTel);
        int progressbarmemory = Integer.parseInt(MemoireDispo);

        barmemory.setMax(progressbarmax);
        barmemory.setProgress(progressbarmemory);

    }

    public void View_Storage(View view) {
        Intent BTN_Back = new Intent(MemoryActivity.this, StorageActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void View_Cpu(View view) {
        Intent BTN_Back = new Intent(MemoryActivity.this, procs.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void BTN_Back(View view) {
        Intent BTN_Back = new Intent(MemoryActivity.this, MainActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }
}