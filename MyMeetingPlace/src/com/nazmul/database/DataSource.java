package com.nazmul.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nazmul.util.PlacesModel;

public class DataSource {

	// Database fields
	private SQLiteDatabase mPlaceDatabase;
	private SQLiteHelper mPlaceDbHelper;

	List<PlacesModel> mPlaces = new ArrayList<PlacesModel>();

	public DataSource(Context context) {
		mPlaceDbHelper = new SQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		mPlaceDatabase = mPlaceDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		mPlaceDbHelper.close();
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(PlacesModel meetingPlaces) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(SQLiteHelper.COL_PLACE_DATE, meetingPlaces.getmDate());
		cv.put(SQLiteHelper.COL_PLACE_TIME, meetingPlaces.getmTime());
		cv.put(SQLiteHelper.COL_PLACE_LATITUDE, meetingPlaces.getmLatitude());
		cv.put(SQLiteHelper.COL_PLACE_LONGITUDE, meetingPlaces.getmLongitude());
		cv.put(SQLiteHelper.COL_PLACE_DESCRIPTION, meetingPlaces.getmRemarks());
		cv.put(SQLiteHelper.COL_IMAGE, meetingPlaces.getmPhotoPath());

		Long check = mPlaceDatabase.insert(SQLiteHelper.TABLE_PLACE, null, cv);
		mPlaceDatabase.close();

		this.close();

		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eId, PlacesModel eUpdateObject) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.COL_PLACE_DATE, eUpdateObject.getmDate());
		cvUpdate.put(SQLiteHelper.COL_PLACE_TIME, eUpdateObject.getmTime());
		cvUpdate.put(SQLiteHelper.COL_PLACE_LATITUDE,
				eUpdateObject.getmLatitude());
		cvUpdate.put(SQLiteHelper.COL_PLACE_LONGITUDE,
				eUpdateObject.getmLongitude());
		cvUpdate.put(SQLiteHelper.COL_PLACE_DESCRIPTION,
				eUpdateObject.getmRemarks());
		cvUpdate.put(SQLiteHelper.COL_IMAGE, eUpdateObject.getmPhotoPath());

		int check = mPlaceDatabase.update(SQLiteHelper.TABLE_PLACE, cvUpdate,
				SQLiteHelper.COL_PLACE_ID + "=" + eId, null);
		mPlaceDatabase.close();

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long eActivityId) {
		this.open();
		try {
			mPlaceDatabase.delete(SQLiteHelper.TABLE_PLACE,
					SQLiteHelper.COL_PLACE_ID + "=" + eActivityId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<PlacesModel> allPlaces() {
		this.open();

		Cursor mCursor = mPlaceDatabase.query(SQLiteHelper.TABLE_PLACE,
				new String[] { SQLiteHelper.COL_PLACE_ID,
						SQLiteHelper.COL_PLACE_DATE,
						SQLiteHelper.COL_PLACE_TIME,
						SQLiteHelper.COL_PLACE_LATITUDE,
						SQLiteHelper.COL_PLACE_LONGITUDE,
						SQLiteHelper.COL_PLACE_DESCRIPTION,
						SQLiteHelper.COL_IMAGE,

				}, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("place_id"));
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("date"));
					String mActivityTime = mCursor.getString(mCursor
							.getColumnIndex("time"));
					String mActivityLatitude = mCursor.getString(mCursor
							.getColumnIndex("latitude"));
					String mActivityLongitude = mCursor.getString(mCursor
							.getColumnIndex("longitude"));
					String mActivityDescription = mCursor.getString(mCursor
							.getColumnIndex("description"));
					String mImage = mCursor.getString(mCursor
							.getColumnIndex("image"));

					mPlaces.add(new PlacesModel(mActivityId, mActivityDate,
							mActivityTime, mActivityLatitude,
							mActivityLongitude, mActivityDescription, mImage));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mPlaces;
	}

	public PlacesModel singlePlaceData(long mActivityId) {
		this.open();
		PlacesModel informationObject;
		String mId;
		String mDate;
		String mTime;
		String mLatitude;
		String mLongitude;
		String mRemarks;
		String mPhotoPath;

		Cursor mUpdateCursor = mPlaceDatabase.query(SQLiteHelper.TABLE_PLACE,
				new String[] { SQLiteHelper.COL_PLACE_ID,
						SQLiteHelper.COL_PLACE_DATE,
						SQLiteHelper.COL_PLACE_TIME,
						SQLiteHelper.COL_PLACE_LATITUDE,
						SQLiteHelper.COL_PLACE_LONGITUDE,
						SQLiteHelper.COL_PLACE_DESCRIPTION,
						SQLiteHelper.COL_IMAGE, }, SQLiteHelper.COL_PLACE_ID
						+ "=" + mActivityId, null, null, null, null);

		mUpdateCursor.moveToFirst();

		mId = mUpdateCursor.getString(0);
		mDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_PLACE_DATE));
		mTime = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_PLACE_TIME));
		mLatitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_PLACE_LATITUDE));

		mLongitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_PLACE_LONGITUDE));
		mRemarks = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_PLACE_DESCRIPTION));
		mPhotoPath = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_IMAGE));

		mUpdateCursor.close();
		informationObject = new PlacesModel(mId, mDate, mTime, mLatitude,
				mLongitude, mRemarks, mPhotoPath);
		this.close();
		return informationObject;
	}

}
