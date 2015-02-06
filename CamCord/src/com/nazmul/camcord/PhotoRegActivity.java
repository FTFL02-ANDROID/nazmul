package com.nazmul.camcord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.nazmul.util.ImageModel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PhotoRegActivity  extends Activity implements LocationListener{
 private ImageView mIvPhotoView;
private String mCurrentPhotoPath=null;
private LocationManager locationManager;
private Location location;
private TextView mLatitudeTextView;
private TextView mLongitudeTextView;
private EditText mRemarksEditText;
private String provider;
private Criteria criteria;
private ImageModel imgObj;
private String mCurrentDate;
private String mCurrentTime;
LocationManager lm;
TextView lt, ln;
Location l;
GPSTracker gps;


@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.photoreg);
    initialize();
    mCurrentPhotoPath=getIntent().getExtras().getString("path");
    
    setPic();
    mLatitudeTextView=(TextView) findViewById(R.id.latitudeTextView);
    mLongitudeTextView=(TextView) findViewById(R.id.longitudeTextView);
    gps = new GPSTracker(PhotoRegActivity.this);
    if(gps.canGetLocation()){                  
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        mLatitudeTextView.setText(String.valueOf(latitude));
        mLongitudeTextView.setText(String.valueOf(longitude));

        
        // \n is for new line
      
        /*mEtLatitude.setText(String.valueOf(latitude));
        mEtLongitude.setText(String.valueOf(longitude));*/
    }else{
        // can't get location
        // GPS or Network is not enabled
        // Ask user to enable GPS/network in settings
        gps.showSettingsAlert();
    }    

	 /*locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	// Creating a criteria object to retrieve provider
	 criteria = new Criteria();

	// Getting the name of the best provider
	 provider = locationManager.getBestProvider(criteria, true);

	// Getting Current Location
	 location = locationManager.getLastKnownLocation(provider);

	if (location != null) {
		onLocationChanged(location);
	}
	locationManager.requestLocationUpdates(provider, 20000, 0, this);
	 mLatitudeTextView.setText(String.valueOf());
	 mLongitudeTextView.setText(String.valueOf(mLongitude));*/
}


private void setPic() {
	
    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
	mIvPhotoView.setImageBitmap(bitmap);
}

@Override
public void onLocationChanged(Location arg0) {
	double lng = l.getLongitude();
	double lat = l.getLatitude();
	mLatitudeTextView.setText(""+lng);
    mLongitudeTextView.setText(""+lat);

}
@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	
}
@Override
public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	
}
@Override
public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	
}

public void insertdData(View v){
	Toast.makeText(this, "ok",Toast.LENGTH_SHORT).show();
	Date date = Calendar.getInstance().getTime();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
	  mCurrentDate= sdf.format(date);
	  mCurrentTime="";
	imgObj= new ImageModel(String.valueOf(mLatitudeTextView), String.valueOf(mLongitudeTextView), mRemarksEditText.getText().toString(),mCurrentPhotoPath, mCurrentDate,mCurrentTime);
}
public void initialize(){
	
	 mIvPhotoView=(ImageView) findViewById(R.id.ivPhotoView);
	    mLatitudeTextView=(TextView) findViewById(R.id.latitudeTextView);
	    mLongitudeTextView=(TextView) findViewById(R.id.longitudeTextView);
	    mIvPhotoView=(ImageView) findViewById(R.id.ivPhotoView);
	    mRemarksEditText=(EditText) findViewById(R.id.remarkEditText);
}
}