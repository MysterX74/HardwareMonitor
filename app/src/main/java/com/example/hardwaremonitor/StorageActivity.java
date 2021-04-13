package com.example.hardwaremonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
    }

    public void View_Cpu(View view) {
        Intent BTN_Back = new Intent(StorageActivity.this, procs.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void View_Memory(View view) {
        Intent BTN_Back = new Intent(StorageActivity.this, MemoryActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void BTN_Back(View view) {
        Intent BTN_Back = new Intent(StorageActivity.this, MainActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }
}