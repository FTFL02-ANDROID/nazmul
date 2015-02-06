package com.nazmul.Database;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nazmul.util.ImageModel;

public class DataSource {

	private SQLiteHelper dbHelper;
	List<ImageModel> image_list = new ArrayList<ImageModel>();

	public DataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	// Create diet
	public long insertRestaurantInfo(ImageModel imgObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(SQLiteHelper.COLUMN_LATITUDE,
				imgObj.getmLatitude());
		contentValues.put(SQLiteHelper.COLUMN_LONGITUDE,
				imgObj.getmLongitude());
		contentValues.put(SQLiteHelper.COLUMN_REMARKS,
				imgObj.getmLongitude());
		contentValues.put(SQLiteHelper.COLUMN_IMAGE_PATH,
				imgObj.getmPhotoPath());
		contentValues.put(SQLiteHelper.COLUMN_DATE,
				imgObj.getmDate());
		contentValues.put(SQLiteHelper.COLUMN_TIME,
				imgObj.getmTime());
		

		return db.insert(dbHelper.TABLE_NAME, null, contentValues);

	}
	
	public ArrayList<ImageModel> getImageList() {
			ArrayList<ImageModel> img_list  = new ArrayList<ImageModel>();
			
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from image ", null);
			if (result.moveToFirst()) {
			do {
			int id = result.getInt(0);
			String latitude = result.getString(1);
			String longitude = result.getString(2);
			String remarks = result.getString(3);
			String image_path= result.getString(4);
			String date=result.getString(5);
			String time=result.getString(6);
			
		//	LocationAndImage imgInfo = new LocationAndImage(latitude, longitude, remarks,date,time);
			//imgInfo.setmId(id);
			
			} while (result.moveToNext());
			}
			return img_list;
			}
	
	public ImageModel getImageById(int eId) {
		ImageModel imgObj = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from image where id='" + eId + "'",
		null);
		if (result.moveToFirst()) {
		do {
			int id = result.getInt(0);
			String latitude = result.getString(1);
			String longitude = result.getString(2);
			String remarks = result.getString(3);
			String image_path= result.getString(4);
			String date=result.getString(5);
			String time=result.getString(6);
			
		//	imgObj = new LocationAndImage(latitude, longitude, remarks,date,time);
		//	imgObj.setmId(id);
			
			
		} while (result.moveToNext());
		}
		return imgObj;
		}
		/* public void deleteImage (Integer id)
	   {
	      SQLiteDatabase db = dbHelper.getWritableDatabase();
	      db.delete("image", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }*/
	public List<ImageModel> allImage() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.query(SQLiteHelper.TABLE_NAME,
				new String[] { SQLiteHelper.COLUMN_ID,
						SQLiteHelper.COLUMN_LATITUDE,
						SQLiteHelper.COLUMN_LONGITUDE,
						SQLiteHelper.COLUMN_REMARKS,
						SQLiteHelper.COLUMN_IMAGE_PATH,
						SQLiteHelper.COLUMN_DATE,
						SQLiteHelper.COLUMN_TIME,

				}, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					int id = mCursor.getInt(0);
					String latitude = mCursor.getString(1);
					String longitude = mCursor.getString(2);
					String remarks = mCursor.getString(3);
					String image_path= mCursor.getString(4);
					String date=mCursor.getString(5);
					String time=mCursor.getString(6);

					

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		return image_list;
	}
}