package com.nazmul.publicuniversity_v2;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;

import com.example.publicuniversity_v2.R;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nazmul.publicuniversity_v2.database.DataSources;
import com.nazmul.publicuniversity_v2.util.PublicUniversity;

public class GoogleMapActivity extends Activity implements LocationListener {
	private GoogleMap mMap;
	LatLng mCoordinates;
	DataSources mPublicUniversityDS;
	PublicUniversity mUpdatePublicUniversity;
	String mID = "";
	Long mLId;
	String mName;
	String mLatitude;
	String mLongitude;
	double m_latitude, m_longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googlemap);
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");
		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mPublicUniversityDS = new DataSources(this);
			mUpdatePublicUniversity = mPublicUniversityDS
					.singlePublicUniversityData(mLId);

			mName = mUpdatePublicUniversity.getmName();
			mLatitude = mUpdatePublicUniversity.getmLatitude();
			mLongitude = mUpdatePublicUniversity.getmLongitude();

		}

		m_latitude = Double.parseDouble(mLatitude.toString());
		m_longitude = Double.parseDouble(mLongitude.toString());
		mCoordinates = new LatLng(m_latitude, m_longitude);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		Marker university = mMap.addMarker(new MarkerOptions().position(
				mCoordinates).title(mName));

		// Move the camera instantly to bcc with a zoom of 15.
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCoordinates, 15));

		// Zoom in, animating the camera.
		mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		mMap.setMyLocationEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

}