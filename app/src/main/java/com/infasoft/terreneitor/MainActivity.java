package com.infasoft.terreneitor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Button bnt_back, btn_go, btn_left, btn_right, btn_stop, btn_led1;
    private BluetoothAdapter hc05;
    private BluetoothSocket socket;
    private boolean isConnected = false;
    private ProgressDialog progressDialog;
    private String hc05_address = "98:D3:32:30:E2:6D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_go = findViewById(R.id.button_go);
        bnt_back = findViewById(R.id.button_back);
        btn_left = findViewById(R.id.button_left);
        btn_right = findViewById(R.id.button_right);
        btn_stop = findViewById(R.id.button_stop);
        btn_led1 = findViewById(R.id.button_led1);

        new ConnectBT().execute();


        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("1");
            }
        });

        bnt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("2");
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("4");
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("3");
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("0");
            }
        });

        btn_led1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("9");
            }
        });

        /*
        btn_go.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    msg("Presionando...");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    msg("Soltando...");
                }
                return false;
            }
        });
         */
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>{
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "Connecting...", "Please wait...");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices){
            try {
                if (socket == null || !isConnected) {
                    hc05 = BluetoothAdapter.getDefaultAdapter(); // get the mobile bluetooth device
                    BluetoothDevice dispositivo = hc05.getRemoteDevice(hc05_address); // connects to the device's address and checks if it's available
                    socket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID); //create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    socket.connect(); //start connection
                }
            }
            catch (IOException e) {
                ConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Try again.");
                finish();
            }
            else {
                msg("Connected.");
                isConnected = true;
            }
            progressDialog.dismiss();
        }
    }

    private void msg(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private void sendCommand(String message){
        if (socket !=null) {
            try {
                socket.getOutputStream().write(message.getBytes());
            }
            catch (IOException e) {
                msg("Error: "+e);
            }
        }
    }
}
