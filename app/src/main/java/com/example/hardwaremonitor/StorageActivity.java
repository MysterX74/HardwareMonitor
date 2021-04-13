package com.example.hardwaremonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class StorageActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        TextView TV_infoStorage = (TextView) findViewById(R.id.TV_infoStorage);


        String textstorage = "\n Internal Total : "+addCommasToNumericString(getTotalInternalMemorySize())+" Gb\n"
                +" Internal Free : "+addCommasToNumericString(Storage_free()) +" Gb\n\n\n"
                +ExternalStorage();

        TV_infoStorage.setText(textstorage);


    }


    public String addCommasToNumericString(String digits) {
        String result = "";
        for (int i=1; i <= digits.length(); ++i) {
            char ch = digits.charAt(digits.length() - i);
            if (i % 3 == 1 && i > 1) {
                result = "," + result;
            }
            result = ch + result;
        }
        result= result.substring(0,5);
        return result;
    }

    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                Environment.MEDIA_UNMOUNTED);
    }

    public String ExternalStorage(){
        if (externalMemoryAvailable()){
            long freeBytesExternal =  new File(getExternalFilesDir(null).toString()).getFreeSpace();
            int free = (int) (freeBytesExternal/ (1024 * 1024));
            long totalSize =  new File(getExternalFilesDir(null).toString()).getTotalSpace();
            int total= (int) (totalSize/ (1024 * 1024));
            String availableMb = "External Total : "+addCommasToNumericString(String.valueOf(total))+ " Gb\n"
                    +"External Free : "+addCommasToNumericString(String.valueOf(free))+ " Gb\n";
            return availableMb;
        }else {
            return "No SD";
        }
    }

    public static String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return String.valueOf(totalBlocks*blockSize);
    }

    public String Storage_free(){
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long availBlocks = stat.getAvailableBlocksLong();
        long blockSize = stat.getBlockSizeLong();
        String free_memory = String.valueOf((long)availBlocks * (long)blockSize);
        return free_memory;
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