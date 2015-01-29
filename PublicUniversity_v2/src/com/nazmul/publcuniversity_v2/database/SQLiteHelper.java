package com.nazmul.publcuniversity_v2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	// ICare Profile Table
	public static final String UNIVERSITY_TABLE_NAME = "university";
	public static final String UNIVERSITY_COLUMN_ID = "id";
	public static final String UNIVERSITY_COLUMN_NAME = "name";
	public static final String UNIVERSITY_COLUMN_DESCRIPTION = "description";
	public static final String UNIVERSITY_COLUMN_ADDRESS = "address";
	public static final String UNIVERSITY_COLUMN_LATITUDE = "latitude";
	public static final String UNIVERSITY_COLUMN_LONGITUDE = "longitude";
	public static final String UNIVERSITY_COLUMN_COURSES = "courses";
	public static final String UNIVERSITY_COLUMN_STUDENTS = "students";
	public static final String UNIVERSITY_COLUMN_TEACHERS = "teachers";


	private static final String DATABASE_NAME = "pU.db";
	private static final int DATABASE_VERSION = 2;

	// Database creation sql statement
	/*private static final String DATABASE_CREATE_PROFILE = "create table "
			+ UNIVERSITY_TABLE_NAME + "( " + UNIVERSITY_COLUMN_ID
			+ " integer primary key autoincrement, " + " "
			+ UNIVERSITY_COLUMN_NAME + " text not null," + " "
			+ UNIVERSITY_COLUMN_DESCRIPTION + " text not null," + " "
			+ UNIVERSITY_COLUMN_ADDRESS + " text not null," + " "
			+ UNIVERSITY_COLUMN_LATITUDE + " text not null," + " "
			+ UNIVERSITY_COLUMN_LONGITUDE + " text not null," + " "
			+ UNIVERSITY_COLUMN_COURSES + " text not null," + " "
			+ UNIVERSITY_COLUMN_STUDENTS + " text not null," + " "
			+ UNIVERSITY_COLUMN_TEACHERS + " text not null);";*/


	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL("create table university "
				+ "(id integer primary key, name text,description text,address text, latitude text,longitude text,courses text,students text,teachers text)");

		//database.execSQL(DATABASE_CREATE_PROFILE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + UNIVERSITY_TABLE_NAME);
		onCreate(db);
	}

}
