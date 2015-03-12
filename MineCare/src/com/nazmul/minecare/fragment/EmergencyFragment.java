package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ftflproject.ftflicareapplication.R;
import com.google.gson.Gson;
import com.nazmul.minecare.model.Emergency;

public class EmergencyFragment extends Fragment implements OnClickListener,
		SensorEventListener {
	Editor prefsEditor;
	SharedPreferences myPrefs;
	private SensorManager sensorManager;

	private EditText mPhoneEditText;

	private String mPhone;
	private Emergency emergencyModelObject;
	// private ProfileTableDataSource profileTableObject;
	private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		rootView = inflater.inflate(R.layout.fragment_emergency, container,
				false);
		mCreateButton = (Button) rootView.findViewById(R.id.buttonSubmit);
		mCreateButton.setOnClickListener(this);
		sensorManager = (SensorManager) getActivity().getSystemService(
				Context.SENSOR_SERVICE);
		initialize();
		return rootView;
	}

	public void initialize() {
		myPrefs = this.getActivity().getSharedPreferences("MyPrefs",
				Context.MODE_PRIVATE);
		mPhoneEditText = (EditText) rootView.findViewById(R.id.phoneEditText);
		if (myPrefs.contains("number")) {

			Gson gson = new Gson();
			String json = myPrefs.getString("number", "");
			emergencyModelObject = gson.fromJson(json, Emergency.class);
			mPhoneEditText.setText(emergencyModelObject.getmPhone());

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mPhone = mPhoneEditText.getText().toString();
		emergencyModelObject = new Emergency(mPhone);

		// saving data to shared preference
		Editor prefsEditor = myPrefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(emergencyModelObject);
		prefsEditor.putString("number", json);
		prefsEditor.commit();

		Intent intent = getActivity().getIntent();
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_NO_ANIMATION);
		getActivity().overridePendingTransition(0, 0);
		getActivity().finish();

		getActivity().overridePendingTransition(0, 0);
		startActivity(intent);

		// Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
		/*
		 * Toast.makeText(getActivity(), "sfsdsfsdf",
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

	}

	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];
		// String phoneNumber = myPrefs.getString(mPhone, "");

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

		if (accelationSquareRoot >= 2) //
		{
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ emergencyModelObject.getmPhone()));
			startActivity(intent);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}
