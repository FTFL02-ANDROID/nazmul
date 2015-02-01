package com.nazmul.publicuniversity_v2.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nazmul.publicuniversity_v2.util.PublicUniversity;

public class DataSources {

	// Database fields
	private SQLiteDatabase mPublicUniversityDatabase;
	private SQLiteHelper mPublicUniversityDbHelper;
	List<PublicUniversity> mPublicUniversityList = new ArrayList<PublicUniversity>();

	public DataSources(Context context) {
		mPublicUniversityDbHelper = new SQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		mPublicUniversityDatabase = mPublicUniversityDbHelper
				.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		mPublicUniversityDbHelper.close();
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(PublicUniversity eInsertObject) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_NAME, eInsertObject.getmName());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
				eInsertObject.getmDescription());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
				eInsertObject.getmAddress());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
				eInsertObject.getmLatitude());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
				eInsertObject.getmLongitude());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
				eInsertObject.getmCourse());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
				eInsertObject.getmStudent());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS,
				eInsertObject.getmTeachers());
		cv.put(SQLiteHelper.UNIVERSITY_COLUMN_PHOTO,
				eInsertObject.getmPhotoPath());

		long check = mPublicUniversityDatabase.insert(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, null, cv);
		mPublicUniversityDatabase.close();

		this.close();
		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eId, PublicUniversity eUpdateObject) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_NAME,
				eUpdateObject.getmName());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
				eUpdateObject.getmDescription());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
				eUpdateObject.getmAddress());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
				eUpdateObject.getmLatitude());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
				eUpdateObject.getmLongitude());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
				eUpdateObject.getmCourse());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
				eUpdateObject.getmStudent());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS,
				eUpdateObject.getmTeachers());
		cvUpdate.put(SQLiteHelper.UNIVERSITY_COLUMN_PHOTO,
				eUpdateObject.getmPhotoPath());

		int check = mPublicUniversityDatabase.update(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, cvUpdate,
				SQLiteHelper.UNIVERSITY_COLUMN_ID + "=" + eId, null);
		mPublicUniversityDatabase.close();

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long eId) {
		this.open();
		try {
			mPublicUniversityDatabase.delete(
					SQLiteHelper.UNIVERSITY_TABLE_NAME,
					SQLiteHelper.UNIVERSITY_COLUMN_ID + "=" + eId, null);
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
	public List<PublicUniversity> publicUniversityData() {
		this.open();

		Cursor mCursor = mPublicUniversityDatabase.query(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, new String[] {
						SQLiteHelper.UNIVERSITY_COLUMN_ID,
						SQLiteHelper.UNIVERSITY_COLUMN_NAME,
						SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
						SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
						SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
						SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
						SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS,
						SQLiteHelper.UNIVERSITY_COLUMN_PHOTO }, null, null,
				null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mId = mCursor.getString(mCursor
							.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_ID));
					String mName = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_NAME));
					String mDescription = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION));
					String mAddress = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS));
					String mLatitude = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE));
					String mLongitude = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE));
					String mCourse = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_COURSES));
					String mStudent = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS));
					String mTeachers = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS));
					String mPhotoPath = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_PHOTO));
					mPublicUniversityList.add(new PublicUniversity(mId, mName,
							mDescription, mAddress, mLatitude, mLongitude,
							mCourse, mStudent, mTeachers, mPhotoPath));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mPublicUniversityList;
	}

	/*
	 * create a profile of ICareProfile. Here the data of the database according
	 * to the given id is set to the profile and return a profile.
	 */
	public PublicUniversity singlePublicUniversityData(long mActivityId) {
		this.open();
		PublicUniversity informationObject;
		String mId;
		String mName;
		String mDescription;
		String mAddress;
		String mLatitude;
		String mLongitude;
		String mCourse;
		String mStudent;
		String mTeachers;
		String mPhotoPath;

		Cursor mUpdateCursor = mPublicUniversityDatabase.query(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, new String[] {
						SQLiteHelper.UNIVERSITY_COLUMN_NAME,
						SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
						SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
						SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
						SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
						SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS,
						SQLiteHelper.UNIVERSITY_COLUMN_PHOTO },
				SQLiteHelper.UNIVERSITY_COLUMN_ID + "=" + mActivityId, null,
				null, null, null);

		mUpdateCursor.moveToFirst();

		mId = mUpdateCursor.getString(0);
		mName = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_NAME));
		mDescription = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION));
		mAddress = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS));
		mLatitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE));
		mLongitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE));
		mCourse = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_COURSES));
		mStudent = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS));
		mTeachers = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS));
		mPhotoPath = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_PHOTO));
		mUpdateCursor.close();
		informationObject = new PublicUniversity(mId, mName, mDescription,
				mAddress, mLatitude, mLongitude, mCourse, mStudent, mTeachers,
				mPhotoPath);
		this.close();
		return informationObject;
	}

	public boolean isEmpty() {
		this.open();
		Cursor mCursor = mPublicUniversityDatabase.query(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, new String[] {
						SQLiteHelper.UNIVERSITY_COLUMN_NAME,
						SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
						SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
						SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
						SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
						SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS,
						SQLiteHelper.UNIVERSITY_COLUMN_PHOTO }, null, null,
				null, null, null);
		if (mCursor.getCount() == 0) {
			this.close();
			return true;
		}

		else {
			this.close();
			return false;
		}
	}

}
