package com.nazmul.minecare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "FTFLICareApp.db";

	/** DIET TABLE */

	public static final String DIET_TABLE_NAME = "diet";
	public static final String DIET_COLUMN_ID = "id";
	public static final String DIET_COLUMN_PROFILE_ID = "profileId";
	public static final String DIET_COLUMN_DATE = "date";
	public static final String DIET_COLUMN_TIME = "time";
	public static final String DIET_COLUMN_DAY = "day";
	public static final String DIET_COLUMN_FEAST_NAME = "dietname";
	public static final String DIET_COLUMN_MENU = "menu";

	/** VACCINE TABLE */

	public static final String VACCINE_TABLE_NAME = "vaccination";
	public static final String VACCINE_COLUMN_ID = "id";
	public static final String VACCINE_COLUMN_PROFILE_ID = "profileId";
	public static final String VACCINE_COLUMN_VACCINE = "vaccine";
	public static final String VACCINE_COLUMN_DATE = "date";
	public static final String VACCINE_COLUMN_NOTES = "status";

	/** DOCTOR TABLE */

	public static final String DOCTOR_TABLE_NAME = "doctor";
	public static final String DOCTOR_COLUMN_ID = "id";
	public static final String DOCTOR_COLUMN_PROFILE_ID = "profileId";
	public static final String DOCTOR_COLUMN_NAME = "name";
	public static final String DOCTOR_COLUMN_SPECIALITY = "speciality";
	public static final String DOCTOR_COLUMN_PHONE = "phone";
	public static final String DOCTOR_COLUMN_EMAIL = "email";
	public static final String DOCTOR_COLUMN_CHAMBER = "chamber";

	/** MEDICAL HISTORY TABLE */

	public static final String MEDICAL_HISTORY_TABLE_NAME = "history";
	public static final String MEDICAL_HISTORY_COLUMN_ID = "id";
	public static final String MEDICAL_HISTORY_COLUMN_PROFILE_ID = "profileId";
	public static final String MEDICAL_HISTORY_COLUMN_PRESCRIPTION = "prescription";
	public static final String MEDICAL_HISTORY_COLUMN_DATE = "date";
	public static final String MEDICAL_HISTORY_COLUMN_DOCTOR = "doctor";
	public static final String MEDICAL_HISTORY_COLUMN_PURPOSE = "purpose";

	/** PROFILE TABLE */

	public static final String PROFILE_TABLE_NAME = "profile";
	public static final String PROFILE_COLUMN_ID = "id";
	public static final String PROFILE_COLUMN_NAME = "name";
	public static final String PROFILE_COLUMN_GENDER = "gender";
	public static final String PROFILE_COLUMN_BLOOD = "doctor";
	public static final String PROFILE_COLUMN_AGE = "age";
	public static final String PROFILE_COLUMN_WEIGHT = "weight";
	public static final String PROFILE_COLUMN_HEIGHT = "height";

	public DatabaseHelperClass(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table diet"
				+ "(id integer primary key,profileId integer, date text,time text,day text,dietname text,menu text)");

		db.execSQL("create table vaccination"
				+ "(id integer primary key,profileId integer, vaccine text,date text,status text)");

		db.execSQL("create table doctor"
				+ "(id integer primary key,profileId integer, name text,speciality text,phone text,email text,chamber text)");

		db.execSQL("create table history"
				+ "(id integer primary key,profileId integer, prescription text,date text,doctor text,purpose text)");

		db.execSQL("create table profile"
				+ "(id integer primary key, name text,gender text,blood text,age text,weight text,height text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS diet");
		onCreate(db);
	}

}
