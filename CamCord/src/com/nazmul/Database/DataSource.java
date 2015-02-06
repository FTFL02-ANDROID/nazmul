package com.nazmul.Database;

import java.util.ArrayList;

import com.nazmul.util.ImageModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

	private SQLiteHelper dbHelper;

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
				imgObj.getmRemarks());
		contentValues.put(SQLiteHelper.COLUMN_IMAGE_PATH,
				imgObj.getmPhotoPath());
		contentValues.put(SQLiteHelper.COLUMN_DATETIME,
				imgObj.getmDateTime());
		

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
			String datetime=result.getString(6);
			
			ImageModel imgInfo = new ImageModel(latitude, longitude, remarks,datetime);
			imgInfo.setmId(id);
			imgInfo.setmPhotoPath(image_path);
			img_list.add(imgInfo);
			} while (result.moveToNext());
			}
			return img_list;
			}
	
	public ImageModel getRestaurantById(int eId) {
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
			String datetime=result.getString(6);
			
			imgObj = new ImageModel(latitude, longitude, remarks,datetime);
			imgObj.setmId(id);
			imgObj.setmPhotoPath(image_path);
			
		} while (result.moveToNext());
		}
		return imgObj;
		}
		 public void deleteImage (Integer id)
	   {
	      SQLiteDatabase db = dbHelper.getWritableDatabase();
	      db.delete("image", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
}