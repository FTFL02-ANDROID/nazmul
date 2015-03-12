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
import android.widget.ListView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.adapter.VaccinationListAdapter;
import com.nazmul.minecare.database.VaccineTableDataSource;
import com.nazmul.minecare.fragment.CreateVaccinationFragment;
import com.nazmul.minecare.fragment.EditVaccinationFragment;
import com.nazmul.minecare.model.VaccinationModel;
import com.nazmul.minecare.util.FTFLConstants;
import com.nazmul.minecare.util.FTFLICareFunctions;

public class VaccinationListActivity extends ActionBarActivity {

	private VaccineTableDataSource vaccineTableObject;
	private ArrayList<VaccinationModel> allVaccineList;
	private ArrayList<VaccinationModel> expiredVaccineObject;
	private ListView allVaccineListView, expiredVaccineListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;

	Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vaccination_list);
		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		vaccineTableObject = new VaccineTableDataSource(this);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		allVaccineList = vaccineTableObject.getAllVaccinationList(1,
				mCurrentDate);

		VaccinationListAdapter adapter = new VaccinationListAdapter(
				VaccinationListActivity.this, allVaccineList);
		allVaccineListView = (ListView) findViewById(R.id.allVaccinationList);
		allVaccineListView.setAdapter(adapter);
		allVaccineListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								VaccinationListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {

						getString(R.string.edit_vaccine),
								getString(R.string.delete_vaccine) };
						alertDialog.setTitle(getString(R.string.options));
						selectedId = allVaccineList.get(position).getmId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:

											FragmentManager fragmentMng = getFragmentManager();
											Bundle bundleedit = new Bundle();
											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditVaccinationFragment editfragobj = new EditVaccinationFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj)
													.commit();
											break;

										case 1:

											VaccineTableDataSource mVaccineTableDS = new VaccineTableDataSource(
													getApplicationContext());

											mVaccineTableDS
													.deleteVaccine(selectedId); //
											Intent intent = new Intent(
													VaccinationListActivity.this,
													VaccinationListActivity.class);
											startActivity(intent);

											break;

										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});

		expiredVaccineObject = vaccineTableObject.getGivenVaccinationList(1,
				mCurrentDate);
		VaccinationListAdapter expiredAdapter = new VaccinationListAdapter(
				VaccinationListActivity.this, expiredVaccineObject);
		expiredVaccineListView = (ListView) findViewById(R.id.expiredVAccinationList);
		expiredVaccineListView.setAdapter(expiredAdapter);
		expiredVaccineListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								VaccinationListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {

						getString(R.string.edit_vaccine),
								getString(R.string.delete_vaccine) };
						alertDialog.setTitle(getString(R.string.options));
						selectedId = allVaccineList.get(position).getmId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:

											FragmentManager fragmentMng = getFragmentManager();
											Bundle bundleedit = new Bundle();
											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditVaccinationFragment editfragobj = new EditVaccinationFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj)
													.commit();
											break;

										case 1:

											VaccineTableDataSource mVaccineTableDS = new VaccineTableDataSource(
													getApplicationContext());

											mVaccineTableDS
													.deleteVaccine(selectedId); //
											Intent intent = new Intent(
													VaccinationListActivity.this,
													VaccinationListActivity.class);
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

		fragment = new CreateVaccinationFragment();

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
