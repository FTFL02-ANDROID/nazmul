package com.nazmul.publicuniversity_v2;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.publicuniversity_v2.R;
import com.nazmul.publicuniversity_v2.database.DataSources;
import com.nazmul.publicuniversity_v2.util.PublicUniversity;

public class PublicUniversityListActivity extends ActionBarActivity {
	DataSources mPublicUniversityDS = null;
	List<PublicUniversity> mPublicUniversitiesList = new ArrayList<PublicUniversity>();
	List<String> mNamesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_public_university_list);

		final String[] option = new String[] { "View Data", "Edit Data",
				"Google Map", "Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					viewData(mPosition);
				}
				if (which == 1) {
					editData(mPosition);
				}
				if (which == 2) {
					googleMap(mPosition);
				}
				if (which == 3) {
					deleteData(mPosition);
				}
			}

		});
		final AlertDialog dialog = builder.create();

		setListData();
		mListView = (ListView) findViewById(R.id.publicUniversityList);

		ArrayAdapter<String> mPublicUniversityAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, mNamesList);

		mListView.setAdapter(mPublicUniversityAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mPosition = position;
				dialog.show();
			}
		});
	}

	private void setListData() {
		DataSources publicUniversityData = new DataSources(this);
		mPublicUniversitiesList = publicUniversityData.publicUniversityData();
		for (int i = 0; i < mPublicUniversitiesList.size(); i++) {
			PublicUniversity mPublicUniversity = mPublicUniversitiesList.get(i);
			mIdList.add(mPublicUniversity.getmId());
			mNamesList.add(mPublicUniversity.getmName());
		}

	}

	public void viewData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				PublicUniversityViewActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", String.valueOf(id));
		startActivity(mEditIntent);
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				AddPublicUniversityActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
	}

	public void googleMap(Integer ePosition) {
		Intent mMapIntent = new Intent(getApplicationContext(),
				GoogleMapActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mMapIntent.putExtra("id", id.toString());
		startActivity(mMapIntent);
	}

	public void deleteData(Integer ePosition) {
		mPublicUniversityDS = new DataSources(this);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mPublicUniversityDS.deleteData(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addpublicUniversity:
			startActivity(new Intent(PublicUniversityListActivity.this,
					AddPublicUniversityActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
