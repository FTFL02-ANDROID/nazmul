package com.nazmul.mymeetingplace;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Intents;
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
	ImageButton mBtnAddContact;

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
		mBtnAddContact = (ImageButton) findViewById(R.id.ibAddContact);

		final String mName = mViewContact.getmContactName();
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
		mBtnAddContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creates a new Intent to insert a contact
				Intent intent = new Intent(Intents.Insert.ACTION);
				// Sets the MIME type to match the Contacts Provider
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				intent.putExtra(Intents.Insert.EMAIL, mEmail);
				intent.putExtra(Intents.Insert.EMAIL_TYPE,
						CommonDataKinds.Email.TYPE_WORK);
				intent.putExtra(Intents.Insert.PHONE, mPhone);
				intent.putExtra(Intents.Insert.PHONE_TYPE, Phone.TYPE_WORK);
				startActivity(intent);
			}
		});

	}

}
