package com.example.hardwaremonitor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private TextView modeSonnerie;
    private TextView infoManuf;
    private TextView infoProduit;
    private TextView infoId;
    private TextView infoNomHard;
    private AudioManager audioManager;
    private static String [] modeSonnerieTexte;
    private Handler monGestionnaire;
    private static int periode = 500; //période d'appel de la tache de fond en ms

    private Runnable tacheDeFond = new Runnable() {
        @Override
        public void run() {
            //CallBack
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            modeSonnerie.setText(modeSonnerieTexte[audioManager.getRingerMode()]);
            //Initialisation tempo avant le nouvel appel de la tache de fond
            monGestionnaire.postDelayed(this, periode);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialisation mode de sonnerie
        modeSonnerieTexte = new String[]{getString(R.string.sonSilence),getString(R.string.sonVibreur),getString(R.string.sonNormal)};

         //informarion générale invariante
        infoManuf = findViewById(R.id.infoManuf);
        infoManuf.setText(Build.MANUFACTURER);
        infoProduit = findViewById(R.id.infoProduit);
        infoProduit.setText(Build.PRODUCT);
        infoId = findViewById(R.id.infoId);
        infoId.setText(Build.ID);
        infoNomHard = findViewById(R.id.infoNomHard);
        infoNomHard.setText(Build.HARDWARE);
        //lancement du gestionnaire de tache de fond
        monGestionnaire = new Handler();
        monGestionnaire.postDelayed(tacheDeFond, periode);

        modeSonnerie = findViewById(R.id.modeSonnerie);

    }


    public void netWork(View v){
        //Information Réseau
        // commentaire ci-dessous à supprimer quand l'activité sera créée dans le projet
        intent = new Intent(MainActivity.this, netWork.class); 
        startActivity(intent);
    }

    public void procs(View v){
        //Information Processeur
        // commentaire ci-dessous à supprimer quand l'activité sera créée dans le projet
       intent = new Intent(MainActivity.this, procs.class);
       startActivity(intent);
    }
}
