package com.nazmul.minecare;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.adapter.OneDayChartListAdapter;
import com.nazmul.minecare.database.DietTableDataSource;
import com.nazmul.minecare.fragment.CreateDietChartFragment;
import com.nazmul.minecare.fragment.EditDietChartFragment;
import com.nazmul.minecare.fragment.ViewDietChartFragment;
import com.nazmul.minecare.model.DietModel;
import com.nazmul.minecare.util.FTFLConstants;
import com.nazmul.minecare.util.FTFLICareFunctions;

public class DietListActivity extends ActionBarActivity {

	private DietTableDataSource dietTableObject;
	private ArrayList<String> upcommingDates;
	private ArrayList<DietModel> CurrentDateDietList;
	private ArrayList<DietModel> upcomingDietObject;
	private ListView todaysDietListView, upcomingDietListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;

	Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diethome);
		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		dietTableObject = new DietTableDataSource(this);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		// Current Date DietList
		CurrentDateDietList = dietTableObject.getCurrentDateDietList(1,
				mCurrentDate);

		OneDayChartListAdapter adapter = new OneDayChartListAdapter(
				DietListActivity.this, CurrentDateDietList);
		todaysDietListView = (ListView) findViewById(R.id.dailyDietList);
		todaysDietListView.setAdapter(adapter);
		todaysDietListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								DietListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.view_diet),
								getString(R.string.edit_diet),
								getString(R.string.delete_diet) };
						alertDialog.setTitle(getString(R.string.options));
						// alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = CurrentDateDietList.get(position)
								.getmDietId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:

											FragmentManager fragmentManager = getFragmentManager();
											Bundle bundle = new Bundle();
											bundle.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											ViewDietChartFragment fragobj = new ViewDietChartFragment();
											fragobj.setArguments(bundle);
											fragmentManager
													.beginTransaction()
													.replace(R.id.layout,
															fragobj).commit();
											break;
										case 1:

											FragmentManager fragmentMng = getFragmentManager();
											Bundle bundleedit = new Bundle();
											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditDietChartFragment editfragobj = new EditDietChartFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj)
													.commit();
											break;

										case 2:

											DietTableDataSource mDietTableDS = new DietTableDataSource(
													getApplicationContext());

											mDietTableDS.deleteDiet(selectedId); //
											Intent intent = new Intent(
													DietListActivity.this,
													DietListActivity.class);
											startActivity(intent);

											break;

										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});

		// upcoming Date Diet List

		upcomingDietObject = dietTableObject.getAllDietHistoy(mCurrentDate);
		OneDayChartListAdapter UpcomingAdapter = new OneDayChartListAdapter(
				DietListActivity.this, upcomingDietObject);
		upcomingDietListView = (ListView) findViewById(R.id.upComingDietList);
		upcomingDietListView.setAdapter(UpcomingAdapter);
		upcomingDietListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								DietListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.view_diet),
								getString(R.string.edit_diet),
								getString(R.string.delete_diet) };
						alertDialog.setTitle(getString(R.string.options));
						selectedId = upcomingDietObject.get(position)
								.getmDietId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:

											FragmentManager fragmentManager = getFragmentManager();
											Bundle bundle = new Bundle();
											bundle.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											ViewDietChartFragment fragobj = new ViewDietChartFragment();
											fragobj.setArguments(bundle);
											fragmentManager
													.beginTransaction()
													.replace(R.id.layout,
															fragobj).commit();
											break;
										case 1:

											FragmentManager fragmentMng = getFragmentManager();
											Bundle bundleedit = new Bundle();
											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditDietChartFragment editfragobj = new EditDietChartFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj)
													.commit();
											// function 2 code here
											break;

										case 2:

											DietTableDataSource mDietTableDS = new DietTableDataSource(
													getApplicationContext());

											mDietTableDS.deleteDiet(selectedId); //
											Intent intent = new Intent(
													DietListActivity.this,
													DietListActivity.class);
											startActivity(intent);

											break;

										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});
	}

	public void add(View v) {
		FragmentManager fragmentManager = getFragmentManager();

		fragment = new CreateDietChartFragment();

		fragmentManager.beginTransaction().replace(R.id.layout, fragment)
				.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(getApplicationContext(),
					DashBoardActivity.class);
			startActivity(intent);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
