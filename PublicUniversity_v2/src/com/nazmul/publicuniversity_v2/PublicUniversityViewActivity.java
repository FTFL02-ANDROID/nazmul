package com.nazmul.publicuniversity_v2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.publicuniversity_v2.R;
import com.nazmul.publicuniversity_v2.database.DataSources;
import com.nazmul.publicuniversity_v2.util.PublicUniversity;

public class PublicUniversityViewActivity extends ActionBarActivity {

	EditText mEtName = null;
	EditText mEtDescription = null;
	EditText mEtAddress = null;
	EditText mEtLatitude = null;
	EditText mEtLongitude = null;
	EditText mEtCourse = null;
	EditText mEtStudent = null;
	EditText mEtTeacher = null;

	DataSources mPublicUniversityDS = null;
	PublicUniversity mUpdatePublicUniversity = null;

	String mID = "";
	Long mLId;
	private ImageView mImgPreview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_university_view);

		mEtName = (EditText) findViewById(R.id.addName);
		mEtDescription = (EditText) findViewById(R.id.addDescription);
		mEtAddress = (EditText) findViewById(R.id.addAddress);
		mEtLatitude = (EditText) findViewById(R.id.Latitude);
		mEtLongitude = (EditText) findViewById(R.id.Longitude);
		mEtCourse = (EditText) findViewById(R.id.addCourses);
		mEtStudent = (EditText) findViewById(R.id.addStudent);
		mEtTeacher = (EditText) findViewById(R.id.addTeachers);
		mImgPreview = (ImageView) findViewById(R.id.imgPreview);

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
			String mPhotopath = mUpdatePublicUniversity.getmPhotoPath();
			if (mPhotopath != null) {

				Bitmap image = BitmapFactory.decodeFile(mPhotopath);
				mImgPreview.setImageBitmap(image);
			}

			// set the value of database to the text field.

			mEtName.setText(mName);
			mEtName.setFocusable(false);
			mEtName.setClickable(false);
			mEtDescription.setText(mDescription);
			mEtDescription.setFocusable(false);
			mEtDescription.setClickable(false);
			mEtAddress.setText(mAddress);
			mEtAddress.setFocusable(false);
			mEtAddress.setClickable(false);
			mEtLatitude.setText(mLatitude);
			mEtLatitude.setFocusable(false);
			mEtLatitude.setClickable(false);
			mEtLongitude.setText(mLongitude);
			mEtLongitude.setFocusable(false);
			mEtLongitude.setClickable(false);
			mEtCourse.setText(mCourse);
			mEtCourse.setFocusable(false);
			mEtCourse.setClickable(false);
			mEtStudent.setText(mStudent);
			mEtStudent.setFocusable(false);
			mEtStudent.setClickable(false);
			mEtTeacher.setText(mTeachers);
			mEtTeacher.setFocusable(false);
			mEtTeacher.setClickable(false);

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
