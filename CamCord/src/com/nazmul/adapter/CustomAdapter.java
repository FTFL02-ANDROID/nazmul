package com.nazmul.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nazmul.camcord.GPSTracker;
import com.nazmul.camcord.R;
import com.nazmul.util.ImageModel;

public class CustomAdapter extends ArrayAdapter<ImageModel> {

	private static LayoutInflater mInflater = null;

	List<ImageModel> mGalary;
	public String mImage;
	String yourDoubleString;
    String yourlongitudeString;
	String provider;
	Location l;
	// GPSTracker class
    GPSTracker gps; 


	public CustomAdapter(Activity context, List<ImageModel> eGalary) {
		super(context, R.layout.activity_image_list, eGalary);
		this.mGalary = eGalary;
		
		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView mId;
		public TextView mDate;
		public TextView mTime;
		public TextView mLatitude;
		public TextView mLongitude;
		public TextView mDescription;
		public TextView mDistance;
		public ImageView mIvImage;
		
		
	

	}
	
	 // convert from byte array to bitmap
    
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = mInflater.inflate(R.layout.activity_image_list, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.mId = (TextView) vi.findViewById(R.id.tvId);
			holder.mDate = (TextView) vi.findViewById(R.id.tvDate);
			holder.mTime = (TextView) vi.findViewById(R.id.tvTime);
			holder.mLatitude = (TextView) vi.findViewById(R.id.tvLatitude);
			holder.mLongitude = (TextView) vi.findViewById(R.id.tvLongitude);
			holder.mDescription = (TextView) vi.findViewById(R.id.tvremarks);
			holder.mDistance = (TextView) vi.findViewById(R.id.tvDistance);
			holder.mIvImage = (ImageView) vi.findViewById(R.id.ivImage);
			
			

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		ImageModel image;
		image = mGalary.get(position);

		holder.mId.setText(image.getmId());
		holder.mDate.setText("Date: "+image.getmDate().toString());
		holder.mTime.setText("Time: " +image.getmTime().toString());
		holder.mLatitude.setText(image.getmLatitude().toString());
		holder.mLongitude.setText(image.getmLongitude().toString());
		holder.mDescription.setText("Description: "+image.getmRemarks().toString());
		holder.mDistance = (TextView) vi.findViewById(R.id.tvDistance);
		


		 // create class object
       gps = new GPSTracker(getContext());
       // check if GPS enabled       
       if(gps.canGetLocation()){                  
           double latitude = gps.getLatitude();
           double longitude = gps.getLongitude();   
           
           yourDoubleString = String.valueOf(latitude);
           yourlongitudeString = String.valueOf(longitude);
           // \n is for new line
         
       }else{
           // can't get location
           // GPS or Network is not enabled
           // Ask user to enable GPS/network in settings
           gps.showSettingsAlert();
       }        
		
		Location l1=new Location("One");
		l1.setLatitude(Double.parseDouble(yourDoubleString));
		l1.setLongitude(Double.parseDouble(yourlongitudeString));
		 
		     
		Location l2=new Location("Two");
		l2.setLatitude(Double.parseDouble(image.getmLatitude().toString()));
		l2.setLongitude(Double.parseDouble(image.getmLongitude().toString()));
		     
		float distance_bw_one_and_two=l1.distanceTo(l2);
		
		holder.mDistance.setText("Distance: "+String.valueOf(distance_bw_one_and_two));
		
		/*mImage = image.getmPhotoPath();
		holder.mIvImage.setImageURI(mImage);;*/
		Bitmap image2 = BitmapFactory.decodeFile(image.getmPhotoPath());
		holder.mIvImage.setImageBitmap(image2);

		
		
		
		return vi;
	}
}