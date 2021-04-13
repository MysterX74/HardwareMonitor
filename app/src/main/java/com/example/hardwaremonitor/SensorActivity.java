package com.example.hardwaremonitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    ListView mListView;
    ListView mListView2;
    SearchView SV_Sensor;

    List<Sensor> searchlist;
    ArrayAdapter<Sensor> testad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mListView = findViewById(R.id.LV_sensor);
        mListView2 = findViewById(R.id.LV_sensor);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);


        mListView.setAdapter(new MySensorsAdapter(this, R.layout.sensor_rows, sensors));
        String total = mListView.getCount()+"";

        Toast.makeText(this, "Nombres de capteurs : "+total, Toast.LENGTH_SHORT).show();


    }

    public static class MySensorsAdapter extends ArrayAdapter<Sensor>{
        private int TV_RESSOURCE_ID;

        private static class ViewHolder{
            TextView itemVIew;
        }
        //constructor
        MySensorsAdapter(Context context, int TV_RESSOURCE_ID, List<Sensor> items){
            super(context, TV_RESSOURCE_ID, items);
            this.TV_RESSOURCE_ID=TV_RESSOURCE_ID;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView==null){
                convertView= LayoutInflater.from(this.getContext()).inflate(TV_RESSOURCE_ID, parent, false);
                viewHolder= new ViewHolder();
                viewHolder.itemVIew=(TextView) convertView.findViewById(R.id.content);
                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) convertView.getTag();
            }

            Sensor items = getItem(position);

            //get all sensors list

            if (items != null){
                viewHolder.itemVIew.setText("Name : "+items.getName()
                        +"\nInt Type : " +items.getType()
                        +"\nString Type : " + sensTypeToString(items.getType())
                        +"\nVendor : " + items.getVendor()
                        +"\nVersion : " + items.getVersion()
                        +"\nResolution : " +items.getResolution()
                        +"\nPower : "+items.getPower()
                        +"\nMaximum Range : "+items.getMaximumRange());
            }

            return convertView;
        }
    }

    public static String sensTypeToString(int sensorType){
        switch (sensorType){
            case Sensor
                    .TYPE_ACCELEROMETER:
                return "Accelerometre";
            case Sensor
                    .TYPE_TEMPERATURE:
                return "Tempérture";
            case Sensor
                    .TYPE_AMBIENT_TEMPERATURE:
                return "Température ambiente";
            case Sensor
                    .TYPE_GAME_ROTATION_VECTOR:
                return "Game rotation Vecteur";
            case Sensor
                    .TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "GEOMAGNETIC_ROTATION_VECTOR";
            case Sensor
                    .TYPE_GRAVITY:
                return "GRAVITY";
            case Sensor
                    .TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor
                    .TYPE_GYROSCOPE_UNCALIBRATED:
                return "Gyroscope uncalibrated";
            case Sensor
                    .TYPE_HEART_BEAT:
                return "HEART BEAT";
            case Sensor
                    .TYPE_LIGHT:
                return "Light";
            case Sensor
                    .TYPE_LINEAR_ACCELERATION:
                return "LINEAR ACCELERATION";
            case Sensor
                    .TYPE_MAGNETIC_FIELD:
                return "MAGNETIC FIELD";
            case Sensor
                    .TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "MAGNETIC FIELD UNCALIBRATED";
            case Sensor
                    .TYPE_ORIENTATION:
                return "ORIENTATION";
            case Sensor
                    .TYPE_PRESSURE:
                return "PRESSURE";
            case Sensor
                    .TYPE_PROXIMITY:
                return "PROXIMITY";
            case Sensor
                    .TYPE_RELATIVE_HUMIDITY:
                return "RELATIVE HUMIDITY";
            case Sensor
                    .TYPE_ROTATION_VECTOR:
                return "ROTATION VECTOR";
            case Sensor
                    .TYPE_SIGNIFICANT_MOTION:
                return "SIGNIFICANT MOTIONT";
            case Sensor
                    .TYPE_STEP_DETECTOR:
                return "STEP DETECTOR";
            case Sensor
                    .TYPE_STEP_COUNTER:
                return "STEP COUNTER";
            default:
                return "Unknow";

        }
    }


    public void BTN_Back(View view) {
        Intent BTN_Back = new Intent(SensorActivity.this, MainActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void View_Battery(View view) {
        Intent View_Battery = new Intent(SensorActivity.this, BatteryActivity.class);
        this.finish();
        startActivity(View_Battery);
    }

    public void View_Network(View view) {
        Intent View_Network = new Intent(SensorActivity.this, netWork.class);
        this.finish();
        startActivity(View_Network);
    }

    public void View_SimInfo(View view) {
        Intent View_SimInfo = new Intent(SensorActivity.this, DisplayInfo.class);
        this.finish();
        startActivity(View_SimInfo);
    }

}