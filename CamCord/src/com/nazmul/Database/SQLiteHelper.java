package com.nazmul.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "imagelocations.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_IMAGE = "image";
	public static final String COL_IMAGE_ID = "image_id";
	public static final String COL_IMAGE_DATE = "date";
	public static final String COL_IMAGE_TIME = "time";
	public static final String COL_IMAGE_LATITUDE = "latitude";
	public static final String COL_IMAGE_LONGITUDE = "longitude";
	public static final String COL_IMAGE_DESCRIPTION = "description";
	public static final String COL_IMAGE = "image";

	// Database creation sql statement
	private static final String TABLE_CREATE_IMAGE = "create table "
			+ TABLE_IMAGE + "(" + COL_IMAGE_ID
			+ " integer primary key autoincrement, " + COL_IMAGE_DATE
			+ " text not null," + COL_IMAGE_TIME + " text not null,"
			+ COL_IMAGE_LATITUDE + " text not null," + COL_IMAGE_LONGITUDE
			+ " text not null," + COL_IMAGE_DESCRIPTION + " text not null ,"
			+ COL_IMAGE + " BLOB not null);";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		database.execSQL(TABLE_CREATE_IMAGE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
		onCreate(db);
	}

}
