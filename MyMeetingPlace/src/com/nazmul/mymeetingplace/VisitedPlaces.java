package com.nazmul.mymeetingplace;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nazmul.adapter.Customadapter;
import com.nazmul.database.DataSource;
import com.nazmul.database.SQLiteHelper;
import com.nazmul.util.PlacesModel;

public class VisitedPlaces extends Activity {
	List<String> mIdList = new ArrayList<String>();

	SQLiteHelper mDBHelper;
	DataSource mPlaceDataSource;
	ListView mListView;
	TextView mId_tv = null;
	TextView mLatitude_tv = null;
	TextView mLongitude_tv = null;
	String mId = "";
	String mLatitude = "";
	String mLongitude = "";
	GPSTracker gps;
	TextView mCurrentLocation;
	Button mBackButton;
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visitedplace);
		mCurrentLocation = (TextView) findViewById(R.id.currentlocation);
		gps = new GPSTracker(VisitedPlaces.this);
		// check if GPS enabled
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			// \n is for new line
			mCurrentLocation.setText("Current Location "
					+ String.valueOf(latitude) + " , "
					+ String.valueOf(longitude));

		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		mDBHelper = new SQLiteHelper(this);
		mPlaceDataSource = new DataSource(this);
		setListData();
		List<PlacesModel> galery_list = mPlaceDataSource.allPlaces();
		Customadapter arrayAdapter = new Customadapter(this, galery_list);
		final String[] option = new String[] { "Edit Data", "Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Log.e("Selected Item", String.valueOf(which));

				if (which == 0) {
					editData(mPosition);
				}

				if (which == 1) {
					deleteData(mPosition);
				}
			}

		});
		final AlertDialog dialog = builder.create();

		mListView = (ListView) findViewById(R.id.lvImageList);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mPosition = position;
				dialog.show();
			}
		});

		mBackButton = (Button) findViewById(R.id.backBtn);
		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						MyMeetingPlaceActivity.class);
				startActivity(i);
			}
		});
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				PlaceInfoActivity.class);
		Long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", eActivityId.toString());
		startActivity(mEditIntent);
	}

	private void setListData() {
		DataSource mPlaceDataSource = new DataSource(this);
		List<PlacesModel> galery_list = mPlaceDataSource.allPlaces();
		for (int i = 0; i < galery_list.size(); i++) {
			PlacesModel mPlaces = galery_list.get(i);
			mIdList.add(mPlaces.getmId());

		}

	}

	public void deleteData(Integer ePosition) {
		mPlaceDataSource = new DataSource(this);
		long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mPlaceDataSource.deleteData(eActivityId);
		Intent i = new Intent(getApplicationContext(), VisitedPlaces.class);
		startActivity(i);
	}

}
