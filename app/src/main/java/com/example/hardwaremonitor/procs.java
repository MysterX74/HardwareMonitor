package com.example.hardwaremonitor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class procs extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procs);
        Button button = (Button) findViewById(R.id.button);

        monGestionnaire = new Handler();
        monGestionnaire.postDelayed(tacheDeFond, periode);
    };

    public void menuPrin(View v) {

        Intent intent1 = new Intent(procs.this, MainActivity.class);
        startActivity(intent1);
    };

   // public static final int DEVICE_TEMPERATURE_CPU { };

}