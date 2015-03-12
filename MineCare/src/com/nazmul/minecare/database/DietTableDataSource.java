package com.nazmul.minecare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nazmul.minecare.model.DietModel;

public class DietTableDataSource {

	private DatabaseHelperClass dbHelper;

	public DietTableDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create diet into table
	public long createDiet(DietModel dietObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("profileId", dietObj.getmProfileId());
		contentValues.put("date", dietObj.getmDietDate());
		contentValues.put("time", dietObj.getmDietTime());
		contentValues.put("day", dietObj.getmDay());
		contentValues.put("dietname", dietObj.getmDietName());
		contentValues.put("menu", dietObj.getmDietMenu());

		return db.insert(DatabaseHelperClass.DIET_TABLE_NAME, null,
				contentValues);

	}

	// this function performs to edit an specific diet from dietLIst by id

	public long editDiet(Integer id, DietModel dietModelObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelperClass.DIET_COLUMN_FEAST_NAME,
				dietModelObj.getmDietName().toString());
		contentValues.put(DatabaseHelperClass.DIET_COLUMN_MENU, dietModelObj
				.getmDietMenu().toString());
		contentValues.put(DatabaseHelperClass.DIET_COLUMN_DATE, dietModelObj
				.getmDietDate().toString());
		contentValues.put(DatabaseHelperClass.DIET_COLUMN_TIME, dietModelObj
				.getmDietTime().toString());
		
		return db.update("diet", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		// return true;
	}

	// return diet list for current date
	public ArrayList<DietModel> getCurrentDateDietList(int profile_id,
			String mCurrentDate) {

		ArrayList<DietModel> array_list = new ArrayList<DietModel>();

		// hp = new HashMap();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from diet where date ='"
				+ mCurrentDate + "'", null);
		// Cursor result = db.rawQuery("select * from diet", null);
		if (result.moveToFirst()) {
			do {

				int dietId = result.getInt(0);
				int profileId = result.getInt(1);
				String dietDate = result.getString(2);
				String dietTime = result.getString(3);
				String dietDay = result.getString(4);
				String dietName = result.getString(5);
				String dietMenu = result.getString(6);

				DietModel dietList = new DietModel(dietName, dietDate,
						dietTime, dietDay, dietMenu);
				dietList.setmDietId(dietId);
				dietList.setmProfileId(profileId);
				array_list.add(dietList);
			} while (result.moveToNext());

		}
		return array_list;
	}

	// return upcomming dates drom diet Table
	public ArrayList<DietModel> getUpcommingDietListByDate(String mCurrentDate) {

		ArrayList<DietModel> upcommingList = new ArrayList<DietModel>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from diet where date >('"
				+ mCurrentDate + "') ", null);
		if (result.moveToFirst()) {
			do {

				int dietId = result.getInt(0);
				int profileId = result.getInt(1);
				String dietDate = result.getString(2);
				String dietTime = result.getString(3);
				String dietDay = result.getString(4);
				String dietName = result.getString(5);
				String dietMenu = result.getString(6);

				DietModel dietList = new DietModel(dietName, dietDate,
						dietTime, dietDay, dietMenu);
				dietList.setmDietId(dietId);
				dietList.setmProfileId(profileId);
				upcommingList.add(dietList);
			} while (result.moveToNext());

		}
		return upcommingList;
	}

	// retrn all diet list by date
	public ArrayList<DietModel> getAllDietHistoy(String mCurrentDate) {

		ArrayList<DietModel> allHistoryList = new ArrayList<DietModel>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from diet where date >('"
				+ mCurrentDate + "') ", null);
		if (result.moveToFirst()) {
			do {

				int dietId = result.getInt(0);
				int profileId = result.getInt(1);
				String dietDate = result.getString(2);
				String dietTime = result.getString(3);
				String dietDay = result.getString(4);
				String dietName = result.getString(5);
				String dietMenu = result.getString(6);

				DietModel dietList = new DietModel(dietName, dietDate,
						dietTime, dietDay, dietMenu);
				dietList.setmDietId(dietId);
				dietList.setmProfileId(profileId);
				allHistoryList.add(dietList);
			} while (result.moveToNext());

		}
		return allHistoryList;
	}

	// return a diet list by specific det Id
	public DietModel getDietById(int id) {

		DietModel dietList = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from diet where id='" + id + "'",
				null);
		if (result.moveToFirst()) {
			do {

				int dietId = result.getInt(0);
				int profileId = result.getInt(1);
				String dietDate = result.getString(2);
				String dietTime = result.getString(3);
				String dietDay = result.getString(4);
				String dietName = result.getString(5);
				String dietMenu = result.getString(6);

				dietList = new DietModel(dietName, dietDate, dietTime, dietDay,
						dietMenu);
				dietList.setmDietId(dietId);
				dietList.setmProfileId(profileId);

			} while (result.moveToNext());

		}
		return dietList;
	}

	public void deleteDiet(Integer id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("diet", "id = ? ", new String[] { Integer.toString(id) });
	}

}
