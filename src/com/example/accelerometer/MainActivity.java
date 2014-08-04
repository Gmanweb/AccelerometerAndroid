package com.example.accelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

	//this class help select a particular sensor
	Sensor sensor;
	//help us manage sensor components
	SensorManager sm;
	
	TextView displayReading;
	
	MediaPlayer mPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setup a sensor service
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		//select the sensor we wish to use
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		displayReading = (TextView)findViewById(R.id.display_reading);
		
		mPlayer = MediaPlayer.create(this, R.raw.sword);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		displayReading.setText("X"+event.values[0]+"\nY"+event.values[1]+"\nZ"+event.values[2]);
		
		if(event.values[0]>10)
		{
			mPlayer.start();
		}
	}

}
