package com.nazmul.publicuniversity_v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publicuniversity_v2.R;
import com.nazmul.publicuniversity_v2.database.DataSources;
import com.nazmul.publicuniversity_v2.util.PublicUniversity;

public class AddPublicUniversityActivity extends ActionBarActivity implements
		OnClickListener {
	Button mBtns_save = null;

	EditText mEtName = null;
	EditText mEtDescription = null;
	EditText mEtAddress = null;
	EditText mEtLatitude = null;
	EditText mEtLongitude = null;
	EditText mEtCourse = null;
	EditText mEtStudent = null;
	EditText mEtTeacher = null;

	String mName = null;
	String mDescription = null;
	String mAddress = null;
	String mLatitude = null;
	String mLongitude = null;
	String mCourse = null;
	String mStudent = null;
	String mTeachers = null;

	TextView mTvItem = null;

	String mStrProfileID = "";
	String mID = "";
	Long mLId;
	DataSources mPublicUniversityDS = null;
	PublicUniversity mUpdatePublicUniversity = null;
	private static String mCurrentPhotoPath = null;

	private Uri mFileUri = null; // file url to store image/video
	private Uri mCropUri = null; // file url to store Crop Image

	private ImageView mImgPreview = null;
	private Button mBtnCapturePicture = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_university);

		mEtName = (EditText) findViewById(R.id.addName);
		mEtDescription = (EditText) findViewById(R.id.addDescription);
		mEtAddress = (EditText) findViewById(R.id.addAddress);
		mEtLatitude = (EditText) findViewById(R.id.addLatitude);
		mEtLongitude = (EditText) findViewById(R.id.addLongitude);
		mEtCourse = (EditText) findViewById(R.id.addCourses);
		mEtStudent = (EditText) findViewById(R.id.addStudent);
		mEtTeacher = (EditText) findViewById(R.id.addTeachers);
		mImgPreview = (ImageView) findViewById(R.id.imgPreview);
		mBtnCapturePicture = (Button) findViewById(R.id.btnCapturePicture);
		mBtns_save = (Button) findViewById(R.id.btnSave);
		mBtns_save.setOnClickListener(this);
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			updatePublicUniversity();
		}
		mBtnCapturePicture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// capture picture
				captureImage();
			}
		});

		// Checking camera availability
		if (!isDeviceSupportCamera()) {
			Toast.makeText(getApplicationContext(),
					"Sorry! Your device doesn't support camera",
					Toast.LENGTH_LONG).show();
			// will close the app if the device does't have camera
			finish();
		}
	}

	private boolean isDeviceSupportCamera() {
		if (getApplicationContext().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	/**
	 * Capturing Camera Image will launch camera app request image capture
	 */
	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		mFileUri = getOutputMediaFileUri(1);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);

		// start the image capture Intent
		startActivityForResult(intent, 100);

	}

	/**
	 * Here we store the file url as it will be null after returning from camera
	 * app
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on screen orientation
		// changes
		outState.putParcelable("file_uri", mFileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		mFileUri = savedInstanceState.getParcelable("file_uri");
	}

	/**
	 * Receiving activity result method will be called after closing the camera
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				// successfully captured the image
				// display it in image view
				previewCapturedImage();
				// carry out the crop operation
				performCrop();
			} // user is returning from cropping the image
			else if (requestCode == 200) {
				previewCropped();

			} else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			} else {
				// failed to capture image
				Toast.makeText(getApplicationContext(),
						"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * Display image from a path to ImageView
	 */
	private void previewCapturedImage() {
		try {
			// mImgPreview.setVisibility(View.VISIBLE);
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * downsizing image as it throws OutOfMemory Exception for larger
			 * images
			 */
			options.inSampleSize = 8; // should be power of 2.
			Bitmap bitmap = BitmapFactory.decodeFile(mFileUri.getPath(),
					options);

			mImgPreview.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void previewCropped() {

		try {
			// bitmap factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * down sizing image as it throws OutOfMemory Exception for larger
			 * images
			 **/
			options.inSampleSize = 8; // should always be, power of 2

			final Bitmap bitmap = BitmapFactory.decodeFile(mCropUri.getPath(),
					options);

			mImgPreview.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void performCrop() {

		try {
			/**
			 * call the standard crop action intent (the user device may not
			 * support it)
			 */
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			// indicate image type and Uri
			cropIntent.setDataAndType(mFileUri, "image/*");
			// set crop properties
			cropIntent.putExtra("crop", "true");
			// indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 0);
			cropIntent.putExtra("aspectY", 0);
			// indicate output X and Y
			cropIntent.putExtra("outputX", 200);
			cropIntent.putExtra("outputY", 150);
			// retrieve data on return, true for bitmap type
			cropIntent.putExtra("return-data", false);

			mCropUri = getOutputMediaFileUri(2);
			// save output image in uri
			cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCropUri);
			// start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, 200);

		} catch (ActivityNotFoundException anfe) {
			// display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	/**
	 * ------------ Helper Methods ----------------------
	 * */

	/**
	 * Creating file uri to store image/video
	 */
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/**
	 * returning image / video
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard mLocation
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Public_university Gallery");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("Public_university Gallery", "Oops! Failed create "
						+ "Public_university Gallery" + " directory");
				return null;
			}
		}

		// Create a media file mName
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());

		File mediaFile;

		if (type == 1) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");

		} else if (type == 2) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + "CROPPED_" + timeStamp + ".jpg");

			// Save a file: path for use with ACTION_VIEW intents
			mCurrentPhotoPath = mediaFile.getAbsolutePath();
		} else {
			return null;
		}

		return mediaFile;
	}

	private void updatePublicUniversity() {
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
		String mPhotoPath = mUpdatePublicUniversity.getmPhotoPath();
		mCurrentPhotoPath = mPhotoPath;
		if (mPhotoPath != null) {
			File f = new File(mPhotoPath);
			BitmapFactory.Options option = new BitmapFactory.Options();
			option.inSampleSize = 0;

			try {
				Bitmap image = BitmapFactory.decodeStream(
						new FileInputStream(f), null, option);

				mImgPreview.setImageBitmap(image);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		// set the value of database to the text field.

		mEtName.setText(mName);
		mEtDescription.setText(mDescription);
		mEtAddress.setText(mAddress);
		mEtLatitude.setText(mLatitude);
		mEtLongitude.setText(mLongitude);
		mEtCourse.setText(mCourse);
		mEtStudent.setText(mStudent);
		mEtTeacher.setText(mTeachers);

		/*
		 * change button name
		 */
		mBtns_save.setText("Update");
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

	@Override
	public void onClick(View v) {
		Toast toast = null;
		switch (v.getId()) {

		case R.id.btnSave:

			mName = mEtName.getText().toString();
			mDescription = mEtDescription.getText().toString();
			mAddress = mEtAddress.getText().toString();
			mLatitude = mEtLatitude.getText().toString();
			mLongitude = mEtLongitude.getText().toString();

			mCourse = mEtCourse.getText().toString();
			mStudent = mEtStudent.getText().toString();
			mTeachers = mEtTeacher.getText().toString();

			PublicUniversity publicUniversityInsert = new PublicUniversity();
			publicUniversityInsert.setmName(mName);
			publicUniversityInsert.setmDescription(mDescription);
			publicUniversityInsert.setmAddress(mAddress);
			publicUniversityInsert.setmLatitude(mLatitude);
			publicUniversityInsert.setmLongitude(mLongitude);
			publicUniversityInsert.setmCourse(mCourse);
			publicUniversityInsert.setmStudent(mStudent);
			publicUniversityInsert.setmTeachers(mTeachers);
			publicUniversityInsert.setmPhotoPath(mCurrentPhotoPath);

			/*
			 * if update is needed then update otherwise submit
			 */
			if (mID != null) {

				mLId = Long.parseLong(mID);

				mPublicUniversityDS = new DataSources(this);

				if (mPublicUniversityDS
						.updateData(mLId, publicUniversityInsert) == true) {
					toast = Toast.makeText(this, "Successfully Updated.",
							Toast.LENGTH_LONG);
					toast.show();
					startActivity(new Intent(AddPublicUniversityActivity.this,
							PublicUniversityListActivity.class));
					finish();
				} else {
					toast = Toast.makeText(this, "Not Updated.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				mPublicUniversityDS = new DataSources(this);
				if (mPublicUniversityDS.insert(publicUniversityInsert) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(AddPublicUniversityActivity.this,
							PublicUniversityListActivity.class));

				} else {
					toast = Toast.makeText(this, "Not Saved.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			}
			break;
		}

	}
}
