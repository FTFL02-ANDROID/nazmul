package com.nazmul.mymeetingplace;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nazmul.database.DataSource;
import com.nazmul.util.PlacesModel;

public class ContactDetails extends Activity {
	String mID = "";
	Long mLId;
	DataSource mImageDS = null;
	PlacesModel mViewContact = null;
	EditText mEtName;
	EditText mEtMail;
	EditText mEtPhone;
	ImageButton mBtnCall;
	ImageButton mBtnEmail;
	ImageButton mBtnSMS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");
		mLId = Long.parseLong(mID);
		mImageDS = new DataSource(this);
		mViewContact = mImageDS.singlePlaceData(mLId);
		mEtName = (EditText) this.findViewById(R.id.etContact);
		mEtMail = (EditText) this.findViewById(R.id.etEmail);
		mEtPhone = (EditText) this.findViewById(R.id.etPhone);
		mBtnCall = (ImageButton) findViewById(R.id.ibCall);
		mBtnEmail = (ImageButton) findViewById(R.id.ibMail);
		mBtnSMS = (ImageButton) findViewById(R.id.ibChat);

		String mName = mViewContact.getmContactName();
		final String mEmail = mViewContact.getmContactMail();
		final String mPhone = mViewContact.getmContactPhone();
		mEtName.setText(mName);
		mEtName.setClickable(false);
		mEtName.setFocusable(false);
		mEtMail.setText(mEmail);
		mEtMail.setClickable(false);
		mEtMail.setFocusable(false);
		mEtPhone.setText(mPhone);
		mEtPhone.setClickable(false);
		mEtPhone.setFocusable(false);
		mBtnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ mPhone));
				startActivity(intent);

			}
		});
		mBtnEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri
						.fromParts("mailto", mEmail, null));
				// emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
				startActivity(Intent
						.createChooser(emailIntent, "Send email..."));

			}
		});
		mBtnSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("address", mPhone);
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS faild!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});

	}

}
