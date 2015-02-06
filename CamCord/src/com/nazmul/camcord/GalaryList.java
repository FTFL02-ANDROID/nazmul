package com.nazmul.camcord;

import java.lang.reflect.Field;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nazmul.camcord.R;
import com.nazmul.adapter.CustomAdapter;
import com.nazmul.Database.DataSource;
import com.nazmul.Database.SQLiteHelper;
import com.nazmul.util.ImageModel;

public class GalaryList extends Activity {

	SQLiteHelper mDBHelper;
	DataSource mDataSource;
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
		setContentView(R.layout.image_listview);
		mDBHelper = new SQLiteHelper(this);
		mDataSource = new DataSource(this);
		List<ImageModel> image_list = mDataSource.allImage();
		CustomAdapter arrayAdapter = new CustomAdapter(this, image_list);
		mListView = (ListView) findViewById(R.id.lvList);
		mListView.setAdapter(arrayAdapter);
		
			}

}