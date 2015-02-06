package com.nazmul.camcord; //your package name

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class PhotoRegActivity extends Activity implements LocationListener {
	LocationManager lm;
	TextView lt, ln;
	String provider;
	Location l;
	GPSTracker gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photoreg);
		ln = (TextView) findViewById(R.id.LatLng);
		// get location service
		gps = new GPSTracker(PhotoRegActivity.this);
        // check if GPS enabled       
        if(gps.canGetLocation()){                  
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();  
            ln.setText("Latitude "+String.valueOf(latitude)+"Longitude"+String.valueOf(longitude));
            // \n is for new line
          
            /*mEtLatitude.setText(String.valueOf(latitude));
            mEtLongitude.setText(String.valueOf(longitude));*/
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }    
		}

	// If you want location on changing place also than use below method
	// otherwise remove all below methods and don't implement location listener
	@Override
	public void onLocationChanged(Location arg0) {
		double lng = l.getLongitude();
		double lat = l.getLatitude();
		ln.setText("" + lng + "" + lat);

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}
}