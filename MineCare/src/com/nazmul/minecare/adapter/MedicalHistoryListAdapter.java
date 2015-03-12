package com.nazmul.minecare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.model.MedicalHistoryModel;

public class MedicalHistoryListAdapter extends ArrayAdapter< MedicalHistoryModel> {
	   ArrayList<MedicalHistoryModel> history_list_obj;
		private Activity context;
		private LocationManager locationManager;
	
		
		static class ViewHolder {
			public TextView mDoctorName,mDate,mPurpose;
			
			public ImageView image;
			/*public TextView name,address;
			public ImageView image;*/
			}
		public MedicalHistoryListAdapter(Activity context,  ArrayList<MedicalHistoryModel> history_list_obj) {
			super(context,R.layout.each_medical_history_row ,history_list_obj);
			this.history_list_obj=history_list_obj;
			this.context = context;
			
			
			// TODO Auto-generated constructor stub
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (convertView == null) {
		// LayoutInflater
		// inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutInflater inflater = context.getLayoutInflater();
		rowView = inflater.inflate(R.layout.each_medical_history_row, parent,
		false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.image = (ImageView) rowView
		.findViewById(R.id.ivImage);
		
		viewHolder.mDoctorName= (TextView) rowView
				.findViewById(R.id.tvDoctor);
		
		viewHolder.mDate = (TextView) rowView
		.findViewById(R.id.tvDate);
		
		viewHolder.mPurpose = (TextView) rowView
				.findViewById(R.id.tvPurpose);
		
		
	
		rowView.setTag(viewHolder);
		}
		//double distance=distance(Double.valueOf(mCurrentLatitude),Double.valueOf(mCurrentLongitude),Double.valueOf(history_list_obj.get(position).getmLatitude()),Double.valueOf(history_list_obj.get(position).getmLongitude()));
		//float distance=distFrom(Float.valueOf(mCurrentLatitude),Float.valueOf(mCurrentLongitude),Float.valueOf(history_list_obj.get(position).getmLatitude()),Float.valueOf(history_list_obj.get(position).getmLongitude()));
		//float distance=distFrom(20.2, 90.33,22.223, 92.222);

	

		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.mDoctorName.setText( ""+history_list_obj.get(position).getmDoctor());
		holder.mDate.setText(""+history_list_obj.get(position).getmDate());
		holder.mPurpose.setText(""+history_list_obj.get(position).getmPurpose());
	

		//setPic(holder, position);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 16;
		Bitmap bitmap= BitmapFactory.decodeFile(history_list_obj.get(position).getmPhotopath(),options);
		holder.image.setImageBitmap(bitmap);

		//calculateDistance
		
		
		
		
		
		
		return rowView;
		}

	}
