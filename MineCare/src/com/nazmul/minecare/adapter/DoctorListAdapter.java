package com.nazmul.minecare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.model.DoctorModel;

public class DoctorListAdapter extends ArrayAdapter<DoctorModel> {
	private final Activity mContext;

	private LayoutInflater inflater;

	static class ViewHolder {
		public TextView name, speciality, chamber;

	}

	private ArrayList<DoctorModel> objectArray;

	public DoctorListAdapter(Activity context,
			ArrayList<DoctorModel> objectArray) {
		super(context, R.layout.each_doctor_row, objectArray);
		this.mContext = context;
		this.objectArray = objectArray;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;

		if (convertView == null) {

			inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.each_doctor_row, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.name = (TextView)rowView
					.findViewById(R.id.doctorNameTextView);

			viewHolder.speciality = (TextView) rowView.findViewById(R.id.specialityTextView);

			viewHolder.chamber = (TextView) rowView
					.findViewById(R.id.chamberTextView);

			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();

		holder.name.setText(objectArray.get(position).getmName());

		holder.speciality.setText(objectArray.get(position).getmSpeciality());
		holder.chamber.setText(objectArray.get(position).getmChamber());

		return rowView;
	}
}