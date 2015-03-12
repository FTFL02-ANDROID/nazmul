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
import android.widget.Spinner;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.adapter.DoctorListAdapter;
import com.nazmul.minecare.database.DoctorTableDataSource;
import com.nazmul.minecare.fragment.CreateDoctorsProfileFragment;
import com.nazmul.minecare.fragment.EditDoctorsProfileFragment;
import com.nazmul.minecare.fragment.ViewDoctorsProfileFragment;
import com.nazmul.minecare.model.DoctorModel;
import com.nazmul.minecare.util.FTFLConstants;
import com.nazmul.minecare.util.FTFLICareFunctions;

public class DoctorListActivity extends ActionBarActivity {

	private DoctorTableDataSource doctorTableObject;
	private ArrayList<String> upcommingDates;
	private ArrayList<DoctorModel> doctorList;
	private int profileId = 1;
	private ListView doctorListView, upcomingVaccineListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;
	private Spinner spinner;
	Fragment fragment;
	FragmentManager fragmentMng;
	Bundle bundleedit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_list);
		fragmentMng = getFragmentManager();
		bundleedit = new Bundle();

		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		doctorTableObject = new DoctorTableDataSource(this);
		doctorList = doctorTableObject.getAllDoctor(profileId);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		DoctorListAdapter adapter = new DoctorListAdapter(
				DoctorListActivity.this, doctorList);
		doctorListView = (ListView) findViewById(R.id.doctorList);
		doctorListView.setAdapter(adapter);

		doctorListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								DoctorListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.view_doctor),
								getString(R.string.edit_doctor),
								getString(R.string.delete_doctor) };
						alertDialog.setTitle(getString(R.string.options));
						selectedId = doctorList.get(position).getmId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:

											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											ViewDoctorsProfileFragment viewfragobj = new ViewDoctorsProfileFragment();
											viewfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															viewfragobj,
															"edit_Doctor")
													.commit();

											break;
										case 1:

											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditDoctorsProfileFragment editfragobj = new EditDoctorsProfileFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj,
															"edit_Doctor")
													.commit();
											break;

										case 2:
											DoctorTableDataSource mDoctorTableDS = new DoctorTableDataSource(
													getApplicationContext());

											mDoctorTableDS
													.deleteDoctor(selectedId); //
											Intent intent = new Intent(
													DoctorListActivity.this,
													DoctorListActivity.class);
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

		fragment = new CreateDoctorsProfileFragment();

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
