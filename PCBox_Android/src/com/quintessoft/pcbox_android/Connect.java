package com.quintessoft.pcbox_android;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.bluetooth.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class Connect extends Activity {

	static int DISCOVERY_REQUEST = 2;
	
	ListView deviceList;
	Button connect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect);
		
		handleWidgets();
		searchForDevices();
		connectToDevice();
	}

	protected void searchForDevices(){
		
		BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
		
		if(bluetooth!=null){
			
			if (bluetooth.isEnabled()) {
			    // Enabled. Work with Bluetooth.
				if (bluetooth.isDiscovering()) {
					bluetooth.cancelDiscovery();
				}
				
				bluetooth.startDiscovery();
				
				IntentFilter intent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			    intent.addAction(BluetoothDevice.ACTION_UUID);
			    intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
			    intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
			    
			    final BroadcastReceiver mReceiver = new BroadcastReceiver() 
                { 
			    	@Override
                    public void onReceive(Context context, Intent intent) 
                    {
                        String action = intent.getAction(); 
                        // When discovery finds a device 
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) 
                        {
                        // Get the BluetoothDevice object from the Intent 
                        BluetoothDevice device = intent.getParcelableExtra(
                        BluetoothDevice.EXTRA_DEVICE);
                        Log.v("BlueTooth Testing",device.getName() + "\n"
                        + device.getAddress()); 
                        }
                    }

					   
                };

                String aDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
                startActivityForResult(new Intent(aDiscoverable), DISCOVERY_REQUEST);
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND); 
                registerReceiver(mReceiver, filter); 
                bluetooth.startDiscovery();
			    
			}
			else
			{
			    // Disabled. Do something else.
				Toast t = Toast.makeText(getApplicationContext(), "Please turn on Bluetooth to proceed", Toast.LENGTH_SHORT);
				t.show();
			}
			
		}else{
			Toast t = Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT);
			t.show();
		}
		
	}
	
	protected void handleWidgets(){
		connect = (Button) findViewById(R.id.button1);
	}

	protected void connectToDevice(){
		connect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect, menu);
		return true;
	}

}
