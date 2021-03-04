package com.example.hardwaremonitor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.System.getProperty;


public class procs extends AppCompatActivity {

    private TextView infoModel;
    private TextView infoManuf;
    private TextView infoDev;
    private TextView infoNomHard;
    private TextView infoBoard;
    private TextView infoCode;
    private TextView infoRel;
    private TextView infoVerSDK;
    private TextView infoOS;
    private TextView infoArchOS;
    private TextView infoVerJava;
    private Handler monGestionnaire;
    private static int periode = 500; //période d'appel de la tache de fond en ms

    private Runnable tacheDeFond = new Runnable() {
        @Override
        public void run() {
            //CallBack

            //Initialisation tempo avant le nouvel appel de la tache de fond
            monGestionnaire.postDelayed(this, periode);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procs);
        Button button = (Button) findViewById(R.id.button);

        //informarion générale invariante
        infoModel = findViewById(R.id.infoModel);
        infoModel.setText(Build.MODEL);
        infoManuf = findViewById(R.id.infoManuf);
        infoManuf.setText(Build.MANUFACTURER);
        infoDev = findViewById(R.id.infoDev);
        infoDev.setText(Build.DEVICE);
        infoNomHard = findViewById(R.id.infoNomHard);
        infoNomHard.setText(Build.HARDWARE);
        infoBoard = findViewById(R.id.infoBoard);
        infoBoard.setText(Build.BOARD);
        infoCode = findViewById(R.id.infoCode);
        infoCode.setText(Build.VERSION.CODENAME);
        infoRel = findViewById(R.id.infoRel);
        infoRel.setText(Build.VERSION.RELEASE);
        infoVerSDK = findViewById(R.id.infoVerSDK);
        infoVerSDK.setText(Build.VERSION.SDK_INT);
        infoOS = findViewById(R.id.infoOS);
        infoOS.setText(Build.VERSION.BASE_OS);
        infoArchOS = findViewById(R.id.infoArchOS);
        infoArchOS.setText(getProperty("os.arch"));
        infoVerJava = findViewById(R.id.infoVerJava);
        infoVerJava.setText(getProperty("java.vm.version"));


        monGestionnaire = new Handler();
        monGestionnaire.postDelayed(tacheDeFond, periode);


    };

    public void menuPrin(View v) {

        Intent intent1 = new Intent(procs.this, MainActivity.class);
        startActivity(intent1);
    };

}