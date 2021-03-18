package com.example.hardwaremonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class DisplayInfo extends AppCompatActivity {

    TextView varText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        varText=(TextView) findViewById(R.id.TV_DisplayInfo);

        //screensize in px, W x H
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        String widthh= String.valueOf(width);
        String heightt= String.valueOf(height + getNavigationBarHeight());
        String resolution = heightt + 'x' + widthh + " px";

        //physical size in inch
        double x = Math.pow(width/dm.xdpi,2);
        double y = Math.pow(height/dm.ydpi,2);
        double screenInches = Math.sqrt(x+y);
        NumberFormat form = NumberFormat.getNumberInstance();
        form.setMinimumFractionDigits(1);
        form.setMaximumFractionDigits(1);
        String screenInchesOuput = form.format(screenInches);

        //FPS
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        float refreshRating = display.getRefreshRate();
        NumberFormat form1 = NumberFormat.getNumberInstance();
        form.setMinimumFractionDigits(1);
        form.setMaximumFractionDigits(1);
        String refreshRatingOutput = form1.format(refreshRating);

        //orientation
        String orientation ="";
        int orien = this.getResources().getConfiguration().orientation;
        if(orien==1){
            orientation = "Horizontal";
        }else{
            orientation = "Vertical";
        }

        String Density = String.valueOf(getResources().getDisplayMetrics().densityDpi-45);


        String info = "\nRefresh Rate : "+refreshRatingOutput+" Hz"+"\n"+
                "\nRésolution : "+resolution+"\n"+
                "\nTaille en Pouce : "+screenInchesOuput+" \""+"\n"+
                "\nDensité : "+Density+" dpi"+"\n"+
                "\nOrientation : "+ orientation;
        varText=(TextView) findViewById(R.id.TV_DisplayInfo);
        varText.setText(info);
    }

    private int getNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }


    /*BUTTON*/

    public void BTN_Back(View view) {
        Intent BTN_Back = new Intent(DisplayInfo.this, MainActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void View_Battery(View view) {
        Intent View_Battery = new Intent(DisplayInfo.this, BatteryActivity.class);
        this.finish();
        startActivity(View_Battery);
    }

    public void View_SpeedTest(View view) {
        Intent View_SpeedTest = new Intent(DisplayInfo.this, SpeedTestActivity.class);
        this.finish();
        Toast.makeText(getApplicationContext(), "/!\\ Veuillez attendre le chargement du site pour effectuer un SpeedTest", Toast.LENGTH_LONG).show();
        startActivity(View_SpeedTest);
    }

    public void View_Network(View view) {
        Intent View_Network = new Intent(DisplayInfo.this, netWork.class);
        this.finish();
        startActivity(View_Network);
    }
}