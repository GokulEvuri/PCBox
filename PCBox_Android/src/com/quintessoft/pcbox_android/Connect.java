package com.quintessoft.pcbox_android;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Connect extends Activity {

	static int DISCOVERY_REQUEST = 2;
	
	ListView deviceList;
	Button connect;
	ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
	DeviceAdapter adapter;
	
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
			
			Log.d("BLUETOOTH", "bluetooth not null"); 
			
			if (bluetooth.isEnabled()) {
			    // Enabled. Work with Bluetooth.
				if (bluetooth.isDiscovering()) {
					Log.d("BLUETOOTH", "is discovering"); 
					bluetooth.cancelDiscovery();
					devices.clear();
					adapter.notifyDataSetChanged();
					//deviceList.setAdapter(adapter);
				}
				
				//bluetooth.startDiscovery();
				
				IntentFilter intent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			    //intent.addAction(BluetoothDevice.ACTION_UUID);
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
                        	Log.d("BLUETOOTH", "here here"); 
	                        // Get the BluetoothDevice object from the Intent 
	                        BluetoothDevice device = intent.getParcelableExtra(
	                        BluetoothDevice.EXTRA_DEVICE);
	                        Log.d("BlueTooth Testing",device.getName() + "\n"
	                        + device.getAddress());
	                        if(!alreadyAdded(device.getAddress())){
	                        	devices.add(device);
		                        adapter.notifyDataSetChanged();
	                        }
                        }
                    }  
                };

               // String aDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
               // startActivityForResult(new Intent(aDiscoverable), DISCOVERY_REQUEST);
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
	
	protected boolean alreadyAdded(String address){
		 
		boolean isAdded = false;
		 
		for(BluetoothDevice device: devices){
			
			if(device.getAddress().matches(address)){
				isAdded = true;
				break;
			}
		}
		 
		return isAdded;
	}
	
	protected void handleWidgets(){
		connect = (Button) findViewById(R.id.button1);
		deviceList = (ListView) findViewById(R.id.device_list);
		adapter = new DeviceAdapter();
		deviceList.setAdapter(adapter);
	}

	protected void connectToDevice(){
		connect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				devices.clear();
				adapter.notifyDataSetChanged();
				searchForDevices();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect, menu);
		return true;
	}
	
	private class DeviceAdapter extends BaseAdapter {

		LayoutInflater inflater;
		
		DeviceAdapter(){
			inflater = (LayoutInflater) Connect.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return devices.size();
		}

		@Override
		public BluetoothDevice getItem(int position) {
			// TODO Auto-generated method stub
			return devices.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = inflater.inflate(R.layout.device_cell, parent, false);
			
			TextView txt = (TextView) convertView.findViewById(R.id.device_name);
			
			BluetoothDevice device = getItem(position);
			
			txt.setText(device.getName());
			
			handleDeviceSelect(convertView, position);
			
			return convertView;
		}
		
		protected void handleDeviceSelect(View view, final int position){
			
			view.setOnClickListener( new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("device", getItem(position).getName()+" selected");
				}
			});
		}
	}
}
