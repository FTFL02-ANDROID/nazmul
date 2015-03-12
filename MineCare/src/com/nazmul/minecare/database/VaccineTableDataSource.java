package com.nazmul.minecare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nazmul.minecare.model.VaccinationModel;

public class VaccineTableDataSource {

	private DatabaseHelperClass dbHelper;

	public VaccineTableDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create diet into table
	public long AddVaccineInformation(VaccinationModel vaccineObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(dbHelper.VACCINE_COLUMN_PROFILE_ID,
				vaccineObj.getmProfileId());
		contentValues.put(dbHelper.VACCINE_COLUMN_VACCINE,
				vaccineObj.getmVaccine());
		contentValues.put(dbHelper.VACCINE_COLUMN_DATE, vaccineObj.getmDate());
		contentValues
				.put(dbHelper.VACCINE_COLUMN_NOTES, vaccineObj.getmNotes());
		return db.insert(DatabaseHelperClass.VACCINE_TABLE_NAME, null,
				contentValues);

	}

	// this function performs to edit an specific diet from dietLIst by id

	public long editVaccine(Integer id, VaccinationModel vaccineModelObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		/*
		 * contentValues.put(DatabaseHelperClass.VACCINE_COLUMN_VACCINE,
		 * vaccineModelObj.getmVaccine().toString());
		 */
		contentValues.put(DatabaseHelperClass.VACCINE_COLUMN_DATE,
				vaccineModelObj.getmDate().toString());
		contentValues.put(DatabaseHelperClass.VACCINE_COLUMN_NOTES,
				vaccineModelObj.getmNotes().toString());
		return db.update("vaccination", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		// return true;
	}

	public ArrayList<VaccinationModel> getGivenVaccinationList(int profile_id,
			String mCurrentDate) {

		ArrayList<VaccinationModel> array_list = new ArrayList<VaccinationModel>();

		// hp = new HashMap();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		/*
		 * Cursor result =
		 * db.rawQuery("select * from vaccination  where profileId='"
		 * +profile_id+"' and date >('" + mCurrentDate + "') group by date",
		 * null);
		 */

		Cursor result = db.rawQuery("select * from vaccination where date <('"
				+ mCurrentDate + "') order by date DESC", null);
		// Cursor result = db.rawQuery("select * from diet", null);
		if (result.moveToFirst()) {
			do {

				int vaccinationtId = result.getInt(0);
				int profileId = result.getInt(1);
				String vaccinationName = result.getString(2);
				String vaccinationDate = result.getString(3);
				String vaccineNotes = result.getString(4);

				VaccinationModel vaccineList = new VaccinationModel(
						vaccinationtId, profileId, vaccinationName,
						vaccinationDate, vaccineNotes);

				array_list.add(vaccineList);
			} while (result.moveToNext());

		}
		return array_list;
	}

	public ArrayList<VaccinationModel> getTodaysVaccinationList(int profile_id,
			String mCurrentDate) {

		ArrayList<VaccinationModel> array_list = new ArrayList<VaccinationModel>();

		// hp = new HashMap();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		/*
		 * Cursor result =
		 * db.rawQuery("select * from vaccination  where profileId='"
		 * +profile_id+"' and date >('" + mCurrentDate + "') group by date",
		 * null);
		 */

		Cursor result = db.rawQuery("select * from vaccination where date ='"
				+ mCurrentDate + "'", null);
		// Cursor result = db.rawQuery("select * from diet", null);
		if (result.moveToFirst()) {
			do {

				int vaccinationtId = result.getInt(0);
				int profileId = result.getInt(1);
				String vaccinationName = result.getString(2);
				String vaccinationDate = result.getString(3);
				String vaccineNotes = result.getString(4);

				VaccinationModel vaccineList = new VaccinationModel(
						vaccinationtId, profileId, vaccinationName,
						vaccinationDate, vaccineNotes);

				array_list.add(vaccineList);
			} while (result.moveToNext());

		}
		return array_list;
	}

	public ArrayList<VaccinationModel> getAllVaccinationList(int profile_id,
			String mCurrentDate) {

		ArrayList<VaccinationModel> array_list = new ArrayList<VaccinationModel>();

		// hp = new HashMap();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		/*
		 * Cursor result =
		 * db.rawQuery("select * from vaccination  where profileId='"
		 * +profile_id+"' and date >('" + mCurrentDate + "') group by date",
		 * null);
		 */

		Cursor result = db.rawQuery(
				"select * from vaccination order by date ASC", null);
		// Cursor result = db.rawQuery("select * from diet", null);
		if (result.moveToFirst()) {
			do {

				int vaccinationtId = result.getInt(0);
				int profileId = result.getInt(1);
				String vaccinationName = result.getString(2);
				String vaccinationDate = result.getString(3);
				String vaccineNotes = result.getString(4);

				VaccinationModel vaccineList = new VaccinationModel(
						vaccinationtId, profileId, vaccinationName,
						vaccinationDate, vaccineNotes);

				array_list.add(vaccineList);
			} while (result.moveToNext());

		}
		return array_list;
	}

	public VaccinationModel getVaccineById(int id) {

		VaccinationModel vaccineList = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from vaccination where id='" + id
				+ "'", null);
		if (result.moveToFirst()) {
			do {

				int vaccinationtId = result.getInt(0);
				int profileId = result.getInt(1);
				String vaccinationName = result.getString(2);
				String vaccinationDate = result.getString(3);
				String vaccineNotes = result.getString(4);
				vaccineList = new VaccinationModel(vaccinationtId, profileId,
						vaccinationName, vaccinationDate, vaccineNotes);

			} while (result.moveToNext());

		}
		return vaccineList;
	}

	public void deleteVaccine(Integer id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("vaccination", "id = ? ",
				new String[] { Integer.toString(id) });
	}

}
