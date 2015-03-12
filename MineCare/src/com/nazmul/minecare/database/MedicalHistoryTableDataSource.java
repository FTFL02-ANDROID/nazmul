package com.nazmul.minecare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nazmul.minecare.model.MedicalHistoryModel;

public class MedicalHistoryTableDataSource {

	private DatabaseHelperClass dbHelper;

	public MedicalHistoryTableDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create diet into table
	public long insertHistory(MedicalHistoryModel historyObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("profileId", historyObj.getmProfileId());
		contentValues.put("prescription", historyObj.getmPhotopath());
		contentValues.put("date", historyObj.getmDate());
		contentValues.put("doctor", historyObj.getmDoctor());
		contentValues.put("purpose", historyObj.getmPurpose());

		return db.insert(DatabaseHelperClass.MEDICAL_HISTORY_TABLE_NAME, null,
				contentValues);

	}

	// this function performs to edit an specific diet from dietLIst by id

	public long editHistory(Integer id, MedicalHistoryModel historyObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("profileId", historyObj.getmProfileId());
		contentValues.put("prescription", historyObj.getmPhotopath());
		contentValues.put("date", historyObj.getmDate());
		contentValues.put("doctor", historyObj.getmDoctor());
		contentValues.put("purpose", historyObj.getmPurpose());

		return db.update("history", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		// return true;
	}

	// return all diet list by date
	public ArrayList<MedicalHistoryModel> getAllMedicalHistory(int eProfileId) {

		ArrayList<MedicalHistoryModel> allHistoryList = new ArrayList<MedicalHistoryModel>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from history", null);
		if (result.moveToFirst()) {
			do {

				int mId = result.getInt(0);
				int profileId = result.getInt(1);
				String historyPrescription = result.getString(2);
				String historyDate = result.getString(3);
				String historyDoctor = result.getString(4);
				String historyPurpose = result.getString(5);

				MedicalHistoryModel historyList = new MedicalHistoryModel(mId,
						profileId, historyPrescription, historyDate,
						historyDoctor, historyPurpose);

				allHistoryList.add(historyList);
			} while (result.moveToNext());

		}
		return allHistoryList;
	}

	public MedicalHistoryModel getMedicalHistoryId(int id) {
		MedicalHistoryModel historyObj = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from history where id='" + id
				+ "'", null);
		if (result.moveToFirst()) {
			do {
				int mId = result.getInt(0);
				int profileId = result.getInt(1);
				String historyPrescription = result.getString(2);
				String historyDate = result.getString(3);
				String historyDoctor = result.getString(4);
				String historyPurpose = result.getString(5);
				historyObj = new MedicalHistoryModel(mId, profileId,
						historyPrescription, historyDate, historyDoctor,
						historyPurpose);
			} while (result.moveToNext());
		}
		return historyObj;
	}

	public void deleteHistory(Integer id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("history", "id = ? ", new String[] { Integer.toString(id) });
	}

}
