package com.example.hardwaremonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void netWork(View v){
//        intent = new Intent(MainActivity.this, netWork.class);
        startActivity(intent);
    }

    public void procs(View v){
 //       intent = new Intent(MainActivity.this, procs.class);
        startActivity(intent);
    }
}
