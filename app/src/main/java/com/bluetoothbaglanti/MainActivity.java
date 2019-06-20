package com.bluetoothbaglanti;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch onOff;
    TextView showStatus;
    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    private final static int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onOff = findViewById(R.id.swButton);
        showStatus = findViewById(R.id.statusText);

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (onOff.isChecked()) {
                    showStatus.setText("AYAR: AÇIK");
                    Intent permForBT = new Intent(adapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(permForBT, REQUEST_ENABLE_BT);
                } else {
                    showStatus.setText("AYAR: KAPALI");
                        adapter.disable();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Bluetooth Başlatıldı", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bluetooth Başlatılamadı", Toast.LENGTH_SHORT).show();
            }
        }
    }
}