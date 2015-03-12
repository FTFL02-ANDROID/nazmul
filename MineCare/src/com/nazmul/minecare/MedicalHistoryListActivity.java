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
import com.nazmul.minecare.adapter.MedicalHistoryListAdapter;
import com.nazmul.minecare.database.MedicalHistoryTableDataSource;
import com.nazmul.minecare.fragment.CreateMedicalHistoryFragment;
import com.nazmul.minecare.fragment.EditMedicalHistoryFragment;
import com.nazmul.minecare.fragment.ViewMedicalHistoryFragment;
import com.nazmul.minecare.model.MedicalHistoryModel;
import com.nazmul.minecare.util.FTFLConstants;
import com.nazmul.minecare.util.FTFLICareFunctions;

public class MedicalHistoryListActivity extends ActionBarActivity {

	private MedicalHistoryTableDataSource historyTableObject;

	private ArrayList<MedicalHistoryModel> medicalHistoryList;
	private int profileId = 1;
	private ListView medicalHistoryListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;
	private Spinner spinner;
	Fragment fragment;
	FragmentManager fragmentMng;
	Bundle bundleedit;

	private MedicalHistoryTableDataSource medicalHistoryTableObject;

	private ListView MedicalHistoryListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_history_list);

		fragmentMng = getFragmentManager();
		bundleedit = new Bundle();

		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		medicalHistoryTableObject = new MedicalHistoryTableDataSource(this);
		medicalHistoryList = medicalHistoryTableObject
				.getAllMedicalHistory(profileId);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		MedicalHistoryListAdapter adapter = new MedicalHistoryListAdapter(
				MedicalHistoryListActivity.this, medicalHistoryList);
		MedicalHistoryListView = (ListView) findViewById(R.id.medicalHistoryList);
		MedicalHistoryListView.setAdapter(adapter);

		MedicalHistoryListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								MedicalHistoryListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.view_medicalHistory),
								getString(R.string.edit_medicalHistory),
								getString(R.string.delete_medicalHistory) };
						alertDialog.setTitle(getString(R.string.options));
						// alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = medicalHistoryList.get(position).getmId();
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
											ViewMedicalHistoryFragment viewfragobj = new ViewMedicalHistoryFragment();
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
											EditMedicalHistoryFragment editfragobj = new EditMedicalHistoryFragment();
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
											MedicalHistoryTableDataSource mHistoryTableDS = new MedicalHistoryTableDataSource(
													getApplicationContext());

											mHistoryTableDS
													.deleteHistory(selectedId); //
											Intent intent = new Intent(
													MedicalHistoryListActivity.this,
													MedicalHistoryListActivity.class);
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

		fragment = new CreateMedicalHistoryFragment();

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
