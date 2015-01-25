package com.example.testgmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	static final LatLng BCC = new LatLng(23.7519909, 90.4157172);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		Marker bcc = map.addMarker(new MarkerOptions().position(BCC).title(
				"BCC"));

		// Move the camera instantly to hamburg with a zoom of 15.
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