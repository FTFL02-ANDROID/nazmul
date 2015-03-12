package com.nazmul.minecare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.model.VaccinationModel;

public class VaccinationListAdapter extends ArrayAdapter<VaccinationModel> {
	private final Activity mContext;

	private LayoutInflater inflater;

	static class ViewHolder {
		public TextView vaccine, date, note;

	}

	ArrayList<VaccinationModel> objectArray;

	public VaccinationListAdapter(Activity context,
			ArrayList<VaccinationModel> objectArray) {
		super(context, R.layout.each_vacination_row, objectArray);
		this.mContext = context;
		this.objectArray = objectArray;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;

		if (convertView == null) {

			inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.each_vacination_row, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.vaccine = (TextView) rowView
					.findViewById(R.id.diseaseText);

			viewHolder.date = (TextView) rowView.findViewById(R.id.dateText);

			viewHolder.note = (TextView) rowView
					.findViewById(R.id.noteText);

			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();

		holder.vaccine.setText(objectArray.get(position).getmVaccine());

		holder.date.setText(objectArray.get(position).getmDate());
		holder.note.setText(objectArray.get(position).getmNotes());

		return rowView;
	}
}