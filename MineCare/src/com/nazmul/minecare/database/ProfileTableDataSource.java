package com.nazmul.minecare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nazmul.minecare.model.ProfileModel;

public class ProfileTableDataSource {

	private DatabaseHelperClass dbHelper;

	public ProfileTableDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create diet into table
	public long createProfile(ProfileModel profileObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("name", profileObj.getmName());
		contentValues.put("gender", profileObj.getmGender());
		contentValues.put("blood", profileObj.getmBloodGroup());
		contentValues.put("age", profileObj.getmAge());
		contentValues.put("weight", profileObj.getmWeight());
		contentValues.put("height", profileObj.getmHeight());

		return db.insert(DatabaseHelperClass.PROFILE_TABLE_NAME, null,
				contentValues);

	}

	// this function performs to edit an specific diet from dietLIst by id

	public long editProfile(Integer id, ProfileModel profileModelObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_NAME,
				profileModelObj.getmName().toString());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_GENDER,
				profileModelObj.getmGender().toString());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_BLOOD,
				profileModelObj.getmBloodGroup().toString());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_AGE,
				profileModelObj.getmAge().toString());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_WEIGHT,
				profileModelObj.getmWeight().toString());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_HEIGHT,
				profileModelObj.getmHeight().toString());

		return db.update("profile", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		// return true;
	}

	// return all diet list by date
	public ArrayList<ProfileModel> getAllProfile() {

		ArrayList<ProfileModel> allProfileList = new ArrayList<ProfileModel>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from profile", null);
		if (result.moveToFirst()) {
			do {

				int profileId = result.getInt(0);
				String profileName = result.getString(1);
				String profileGender = result.getString(2);
				String profileBloodGroup = result.getString(3);
				String profileAge = result.getString(4);
				String profileWeight = result.getString(5);
				String profileHeight = result.getString(6);

				ProfileModel profileList = new ProfileModel(profileName,
						profileGender, profileBloodGroup, profileAge,
						profileWeight, profileHeight);
				profileList.setmId(profileId);

				allProfileList.add(profileList);
			} while (result.moveToNext());

		}
		return allProfileList;
	}
}
