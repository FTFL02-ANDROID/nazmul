package com.nazmul.mymeetingplace;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nazmul.adapter.Customadapter;
import com.nazmul.database.DataSource;
import com.nazmul.database.SQLiteHelper;
import com.nazmul.util.PlacesModel;

public class VisitedPlaces extends Activity {

	SQLiteHelper mDBHelper;
	DataSource mImageDataSource;
	ListView mListView;
	TextView mId_tv = null;
	TextView mLatitude_tv = null;
	TextView mLongitude_tv = null;
	String mId = "";
	String mLatitude = "";
	String mLongitude = "";
	GPSTracker gps;
	TextView mCurrentLocation;
	Button mBackButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visitedplace);
		mCurrentLocation = (TextView) findViewById(R.id.currentlocation);
		gps = new GPSTracker(VisitedPlaces.this);
		// check if GPS enabled
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			// \n is for new line
			mCurrentLocation.setText("Current Location "
					+ String.valueOf(latitude) + " , "
					+ String.valueOf(longitude));

		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		mDBHelper = new SQLiteHelper(this);
		mImageDataSource = new DataSource(this);
		List<PlacesModel> galery_list = mImageDataSource.allPlaces();
		Customadapter arrayAdapter = new Customadapter(this, galery_list);
		mListView = (ListView) findViewById(R.id.lvImageList);
		mListView.setAdapter(arrayAdapter);
		mBackButton = (Button) findViewById(R.id.backBtn);
		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						MyMeetingPlaceActivity.class);
				startActivity(i);
			}
		});
	}
}
