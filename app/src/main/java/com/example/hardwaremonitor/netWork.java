package com.example.hardwaremonitor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


public class netWork extends AppCompatActivity {


    private static final String TAG = "";
    //Views
    ImageView mConStatusIv;
    TextView mConStatusTv;
    Button mConStatusBtn;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 250;

    TextView varText;

    int wifiOn = 1;

    int requestgps = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);

        //link view with xml
        mConStatusIv=findViewById(R.id.conStatusIv);
        mConStatusTv=findViewById(R.id.conStatusTv);

        varText=(TextView) findViewById(R.id.TV_WifiInfo);

        if (ContextCompat.checkSelfPermission(netWork.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(netWork.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(netWork.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(netWork.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }


    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(netWork.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        requestgps=1;
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }



    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                checkNetworkConnectionStatus();
                if (requestgps==0){
                    statusCheck();
                }

            }
        }, delay);
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
    }

    public String getEmoji(int uni){
        return new String(Character.toChars(uni));
    }


    public static String getMobileIPAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        return  addr.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

    private void checkNetworkConnectionStatus() {
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if(activeInfo != null && activeInfo.isConnected()){
            int unicode=0x1F604;
            String emoji= getEmoji(unicode);
            String text=emoji;
            wifiConnected = activeInfo.getType()==ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType()==ConnectivityManager.TYPE_MOBILE;
            if(wifiConnected){
                mConStatusIv.setImageResource(R.drawable.ic_baseline_wifi_24);
                mConStatusTv.setText("Connecté en Wifi "+text);
                wifiOn = 1;
            }
            else if (mobileConnected){
                ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);

                NetworkCapabilities nc = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    nc = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                }
                String downSpeed = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    downSpeed = String.valueOf(nc.getLinkDownstreamBandwidthKbps()/1024);
                }
                String upSpeed = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    upSpeed = String.valueOf(nc.getLinkUpstreamBandwidthKbps()/1024);
                }

                mConStatusIv.setImageResource(R.drawable.ic_action_data);
                mConStatusTv.setText("Connecté en données mobile "+text+"\nDown : "+downSpeed+" Kb/s | Up : "+upSpeed+" Kb/s");
                wifiOn = 0;


            }

        }
        else{

            int unicode=0x1F622;
            String emoji= getEmoji(unicode);
            String text=emoji;

            mConStatusIv.setImageResource(R.drawable.ic_action_no);
            mConStatusTv.setText("Pas de connexion Internet "+text);
            wifiOn = 5;
        }

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = (WifiInfo) wifiManager.getConnectionInfo();

        String mobileIp = getMobileIPAddress();

        int ip = wifiInfo.getIpAddress();
        String ipAdress = Formatter.formatIpAddress(ip);

        String macAdress = getMacAddr();/*wifiInfo.getMacAddress()*/;

        /*int speedMbps = wifiInfo.getLinkSpeed();
        String SpeedDown = String.valueOf(speedMbps);*/

        String SSID = wifiInfo.getSSID();


        if (wifiOn == 1){
            wifiOn=1;
            macAdress=macAdress;
        }else if(wifiOn == 0){
            ipAdress= mobileIp;
            macAdress= Settings.Secure.getString(this.getApplicationContext().getContentResolver(), "android_id");
            SSID = "Données mobiles activées !";
        }else if(wifiOn == 5){
            ipAdress="No Ip";
            SSID = "Wifi désactivé !";
        }




        String info = "IP : " + ipAdress+
                "\nMAC : " + macAdress+
                "\nSSID : " + SSID;
        varText=(TextView) findViewById(R.id.TV_WifiInfo);
        varText.setText(info);


    }


    public static String getMacAddr() {
        try {
            List <NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif: all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b: macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {}
        return "Try to turn on Wifi";
    }



    public void BTN_Back(View view) {
        Intent BTN_Back = new Intent(netWork.this, MainActivity.class);
        this.finish();
        startActivity(BTN_Back);
    }

    public void View_Battery(View view) {
        Intent View_Battery = new Intent(netWork.this, BatteryActivity.class);
        this.finish();
        startActivity(View_Battery);
    }

    public void View_SpeedTest(View view) {
        Intent View_SpeedTest = new Intent(netWork.this, SpeedTestActivity.class);
        this.finish();
        Toast.makeText(getApplicationContext(),  "/!\\ Veuillez attendre le chargement du site pour effectuer un SpeedTest", Toast.LENGTH_LONG).show();
        startActivity(View_SpeedTest);
    }

    public void View_SimInfo(View view) {
        Intent View_SimInfo = new Intent(netWork.this, DisplayInfo.class);
        this.finish();
        startActivity(View_SimInfo);
    }





}