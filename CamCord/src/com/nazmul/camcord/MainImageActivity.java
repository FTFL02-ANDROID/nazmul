package com.nazmul.camcord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainImageActivity extends Activity {

	Button mBtnRegister;
	Button mBtnRetrieve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);

		mBtnRegister = (Button) findViewById(R.id.btnRegister);
		mBtnRetrieve = (Button) findViewById(R.id.btnRetrieve);

		mBtnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						ImageFormActivity.class);
				startActivity(i);

			}
		});

		mBtnRetrieve.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						GalaryActivity.class);
				startActivity(i);

			}
		});

	}

}
