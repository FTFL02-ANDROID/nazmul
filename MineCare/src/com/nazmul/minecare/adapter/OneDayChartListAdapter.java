package com.nazmul.minecare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.model.DietModel;



public class OneDayChartListAdapter extends ArrayAdapter<DietModel> {
	private final Activity mContext;
    private  LayoutInflater inflater ;
	static class ViewHolder {
		public TextView name, time, menu, date;
	}

	ArrayList<DietModel> objectArray;

	public OneDayChartListAdapter(Activity context,
			ArrayList<DietModel> objectArray) {
		super(context, R.layout.each_row_daily_chart, objectArray);
		this.mContext = context;
		this.objectArray = objectArray;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView = convertView;
		
		if (convertView == null) {
			
			inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.each_row_daily_chart, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.name = (TextView) rowView
					.findViewById(R.id.feastNameText);
			
			viewHolder.time = (TextView) rowView
					.findViewById(R.id.feastTimeText);
			
			viewHolder.menu = (TextView) rowView.findViewById(R.id.menuView);
			
			viewHolder.date = (TextView) rowView.findViewById(R.id.dateText);

			

			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		holder.name.setText(objectArray.get(position).getmDietName());
		
		holder.time.setText(objectArray.get(position).getmDietTime());
		
		holder.menu.setText(objectArray.get(position).getmDietMenu());
		
		holder.date.setText(objectArray.get(position).getmDietDate());

		
		return rowView;
	}
}