package com.nazmul.publcuniversity_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.publicuniversity_v2.R;
import com.nazmul.publcuniversity_v2.database.DataSources;
import com.nazmul.publcuniversity_v2.util.PublicUniversity;

public class PublicUniversityViewActivity extends ActionBarActivity {

	EditText etName = null;
	EditText etDescription = null;
	EditText etAddress = null;
	EditText etLatitude = null;
	EditText etLongitude = null;
	EditText etCourse = null;
	EditText etStudent = null;
	EditText etTeacher = null;

	DataSources mPublicUniversityDS = null;
	PublicUniversity mUpdatePublicUniversity = null;

	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_university_view);

		etName = (EditText) findViewById(R.id.addName);
		etDescription = (EditText) findViewById(R.id.addDescription);
		etAddress = (EditText) findViewById(R.id.addAddress);
		etLatitude = (EditText) findViewById(R.id.Latitude);
		etLongitude = (EditText) findViewById(R.id.Longitude);
		etCourse = (EditText) findViewById(R.id.addCourses);
		etStudent = (EditText) findViewById(R.id.addStudent);
		etTeacher = (EditText) findViewById(R.id.addTeachers);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mPublicUniversityDS = new DataSources(this);
			mUpdatePublicUniversity = mPublicUniversityDS
					.singlePublicUniversityData(mLId);

			String mName = mUpdatePublicUniversity.getmName();
			String mDescription = mUpdatePublicUniversity.getmDescription();
			String mAddress = mUpdatePublicUniversity.getmAddress();
			String mLatitude = mUpdatePublicUniversity.getmLatitude();
			String mLongitude = mUpdatePublicUniversity.getmLongitude();
			String mCourse = mUpdatePublicUniversity.getmCourse();
			String mStudent = mUpdatePublicUniversity.getmStudent();
			String mTeachers = mUpdatePublicUniversity.getmTeachers();

			// set the value of database to the text field.

			etName.setText(mName);
			etName.setFocusable(false);
			etName.setClickable(false);
			etDescription.setText(mDescription);
			etDescription.setFocusable(false);
			etDescription.setClickable(false);
			etAddress.setText(mAddress);
			etAddress.setFocusable(false);
			etAddress.setClickable(false);
			etLatitude.setText(mLatitude);
			etLatitude.setFocusable(false);
			etLatitude.setClickable(false);
			etLongitude.setText(mLongitude);
			etLongitude.setFocusable(false);
			etLongitude.setClickable(false);
			etCourse.setText(mCourse);
			etCourse.setFocusable(false);
			etCourse.setClickable(false);
			etStudent.setText(mStudent);
			etStudent.setFocusable(false);
			etStudent.setClickable(false);
			etTeacher.setText(mTeachers);
			etTeacher.setFocusable(false);
			etTeacher.setClickable(false);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
