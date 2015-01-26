package com.example.publcuniversity_v2.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.publicuniversity_v2.util.PublicUniversity;

;

public class DataSources {

	// Database fields
	private SQLiteDatabase publicUniversityDatabase;
	private SQLiteHelper publicUniversityDbHelper;
	List<PublicUniversity> publicUniversityList = new ArrayList<PublicUniversity>();

	public DataSources(Context context) {
		publicUniversityDbHelper = new SQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		publicUniversityDatabase = publicUniversityDbHelper
				.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		publicUniversityDbHelper.close();
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

		long check = publicUniversityDatabase.insert(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, null, cv);
		publicUniversityDatabase.close();

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

		int check = publicUniversityDatabase.update(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, cvUpdate,
				SQLiteHelper.UNIVERSITY_COLUMN_ID + "=" + eId, null);
		publicUniversityDatabase.close();

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
			publicUniversityDatabase.delete(SQLiteHelper.UNIVERSITY_TABLE_NAME,
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

		Cursor mCursor = publicUniversityDatabase.query(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, new String[] {
						SQLiteHelper.UNIVERSITY_COLUMN_ID,
						SQLiteHelper.UNIVERSITY_COLUMN_NAME,
						SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
						SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
						SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
						SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
						SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS }, null, null,
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
					// long mmId = Long.parseLong(mId);
					publicUniversityList.add(new PublicUniversity(mId, mName,
							mDescription, mAddress, mLatitude, mLongitude,
							mCourse, mStudent, mTeachers));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return publicUniversityList;
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

		Cursor mUpdateCursor = publicUniversityDatabase.query(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, new String[] {
						SQLiteHelper.UNIVERSITY_COLUMN_NAME,
						SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
						SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
						SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
						SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
						SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS, },
				SQLiteHelper.UNIVERSITY_COLUMN_ID + "=" + mActivityId, null,
				null, null, null);

		mUpdateCursor.moveToFirst();

		mId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.UNIVERSITY_COLUMN_ID));
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
		mUpdateCursor.close();
		informationObject = new PublicUniversity(mId, mName, mDescription,
				mAddress, mLatitude, mLongitude, mCourse, mStudent, mTeachers);
		this.close();
		return informationObject;
	}

	public boolean isEmpty() {
		this.open();
		Cursor mCursor = publicUniversityDatabase.query(
				SQLiteHelper.UNIVERSITY_TABLE_NAME, new String[] {
						SQLiteHelper.UNIVERSITY_COLUMN_NAME,
						SQLiteHelper.UNIVERSITY_COLUMN_DESCRIPTION,
						SQLiteHelper.UNIVERSITY_COLUMN_ADDRESS,
						SQLiteHelper.UNIVERSITY_COLUMN_LATITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_LONGITUDE,
						SQLiteHelper.UNIVERSITY_COLUMN_COURSES,
						SQLiteHelper.UNIVERSITY_COLUMN_STUDENTS,
						SQLiteHelper.UNIVERSITY_COLUMN_TEACHERS},
				null, null, null, null, null);
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
