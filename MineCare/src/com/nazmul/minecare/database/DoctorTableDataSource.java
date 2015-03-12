package com.nazmul.minecare.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nazmul.minecare.model.DoctorModel;

public class DoctorTableDataSource {

	private DatabaseHelperClass dbHelper;

	public DoctorTableDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create doctor into table
	public long createDoctorProfile(DoctorModel doctorObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("profileId", doctorObj.getmProfileId());
		contentValues.put("name", doctorObj.getmName());
		contentValues.put("speciality", doctorObj.getmSpeciality());
		contentValues.put("phone", doctorObj.getmPhone());
		contentValues.put("email", doctorObj.getmEmail());
		contentValues.put("chamber", doctorObj.getmChamber());

		return db.insert(DatabaseHelperClass.DOCTOR_TABLE_NAME, null,
				contentValues);

	}

	// this function performs to edit an specific doctor from doctorLIst by id

	public long editDoctor(Integer id, DoctorModel doctorModelObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelperClass.DOCTOR_COLUMN_NAME,
				doctorModelObj.getmName().toString());
		contentValues.put(DatabaseHelperClass.DOCTOR_COLUMN_SPECIALITY,
				doctorModelObj.getmSpeciality().toString());
		contentValues.put(DatabaseHelperClass.DOCTOR_COLUMN_PHONE,
				doctorModelObj.getmPhone().toString());
		contentValues.put(DatabaseHelperClass.DOCTOR_COLUMN_EMAIL,
				doctorModelObj.getmEmail().toString());
		contentValues.put(DatabaseHelperClass.DOCTOR_COLUMN_CHAMBER,
				doctorModelObj.getmChamber().toString());

		return db.update("doctor", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		// return true;
	}

	// return all diet list
	public ArrayList<DoctorModel> getAllDoctor(int eProfileId) {

		ArrayList<DoctorModel> allDoctorList = new ArrayList<DoctorModel>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from doctor", null);
		if (result.moveToFirst()) {
			do {

				int doctorId = result.getInt(0);
				int profileId = result.getInt(1);
				String doctorName = result.getString(2);
				String doctorSpeciality = result.getString(3);
				String doctorPhone = result.getString(4);
				String doctorEmail = result.getString(5);
				String doctorChamber = result.getString(6);

				DoctorModel doctorList = new DoctorModel(doctorName,
						doctorSpeciality, doctorPhone, doctorEmail,
						doctorChamber);
				doctorList.setmId(doctorId);
				doctorList.setmProfileId(profileId);
				allDoctorList.add(doctorList);
			} while (result.moveToNext());

		}
		return allDoctorList;
	}
	
	public DoctorModel getDoctorById(int id) {

		DoctorModel doctorsProfile = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from doctor where id='" + id + "'",
				null);
		if (result.moveToFirst()) {
			do {

				int doctorId = result.getInt(0);
				int profileId = result.getInt(1);
				String doctorName = result.getString(2);
				String doctorSpeciality = result.getString(3);
				String doctorPhone = result.getString(4);
				String doctorEmail = result.getString(5);
				String doctorChamber = result.getString(6);

				doctorsProfile = new DoctorModel(doctorName, doctorSpeciality, doctorPhone, doctorEmail, doctorChamber);

			} while (result.moveToNext());

		}
		return doctorsProfile;
	}
	
	
	 public List<String> getAllNames(){
	        List<String> names = new ArrayList<String>();
	          
	        SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from doctor ",
					null);
			
			if (result.moveToFirst()) {
				do {

					
					names.add(result.getString(2));
					


				} while (result.moveToNext());

			}
			
			return names;
			
			
	    }
	 public void deleteDoctor(Integer id) {
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.delete("doctor", "id = ? ", new String[] { Integer.toString(id) });
		}
	
	

}
