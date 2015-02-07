package com.nazmul.camcord;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.nazmul.Database.DataSource;
import com.nazmul.Database.SQLiteHelper;
import com.nazmul.adapter.Customadapter;
import com.nazmul.util.ImageModel;

public class GalaryActivity extends Activity {

	SQLiteHelper mDBHelper;
	DataSource mImageDataSource;
	ListView mListView;
	TextView mId_tv = null;
	TextView mLatitude_tv = null;
	TextView mLongitude_tv = null;
	String mId = "";
	String mLatitude = "";
	String mLongitude = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_list_view);
		mDBHelper = new SQLiteHelper(this);
		mImageDataSource = new DataSource(this);
		List<ImageModel> galery_list = mImageDataSource.allImages();
		Customadapter arrayAdapter = new Customadapter(this, galery_list);
		mListView = (ListView) findViewById(R.id.lvImageList);
		mListView.setAdapter(arrayAdapter);
	}
}
