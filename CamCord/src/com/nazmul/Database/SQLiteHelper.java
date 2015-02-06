package com.nazmul.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "imageGalary.db";
	public static final String TABLE_NAME = "image";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_REMARKS = "remarks";
	public static final String COLUMN_IMAGE_PATH = "imagepath";
	public static final String COLUMN_DATETIME = "datetime";

	public SQLiteHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE image"
				+ "(id INTEGER PRIMARY KEY,latitude TEXT,longitude TEXT,remarks TEXT,imagepath TEXT,datetime TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS restaurant");
		onCreate(db);
	}

}