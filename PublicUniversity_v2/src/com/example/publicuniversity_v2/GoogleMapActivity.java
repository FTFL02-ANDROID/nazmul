package com.example.publicuniversity_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.publcuniversity_v2.database.DataSources;
import com.example.publicuniversity_v2.util.PublicUniversity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends Activity {
	static final LatLng BCC = new LatLng(23.7519909, 90.4157172);
	private GoogleMap map;
	DataSources mPublicUniversityDS;
	PublicUniversity mUpdatePublicUniversity;
	String mID = "";
	Long mLId;
	String mAddress;
	String mLatitude;
	String mLongitude;
	int latitude, longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googlemap);
		mPublicUniversityDS = new DataSources(this);
		mUpdatePublicUniversity = mPublicUniversityDS
				.singlePublicUniversityData(mLId);

		mAddress = mUpdatePublicUniversity.getmAddress();
		mLatitude = mUpdatePublicUniversity.getmLatitude();
		mLongitude = mUpdatePublicUniversity.getmLongitude();
		latitude = Integer.parseInt(mLatitude.toString());
		longitude = Integer.parseInt(mLatitude.toString());
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		Marker university = map.addMarker(new MarkerOptions().position(
				new LatLng(latitude, longitude)).title(mAddress));

		// Move the camera instantly to bcc with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(BCC, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}