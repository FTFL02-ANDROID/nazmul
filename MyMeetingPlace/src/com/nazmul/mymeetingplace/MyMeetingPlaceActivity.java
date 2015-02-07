package com.nazmul.mymeetingplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyMeetingPlaceActivity extends Activity {

	Button mBtnRegister;
	Button mBtnRetrieve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBtnRegister = (Button) findViewById(R.id.regBtn);
		mBtnRetrieve = (Button) findViewById(R.id.viewBtn);

		mBtnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						PlaceInfoActivity.class);
				startActivity(i);

			}
		});

		mBtnRetrieve.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						VisitedPlaces.class);
				startActivity(i);

			}
		});

	}

}
