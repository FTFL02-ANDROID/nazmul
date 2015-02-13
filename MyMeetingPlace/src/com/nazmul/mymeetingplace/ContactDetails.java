package com.nazmul.mymeetingplace;

import com.nazmul.database.DataSource;
import com.nazmul.util.PlacesModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ContactDetails extends Activity {
	String mID = "";
	Long mLId;
	DataSource mImageDS = null;
	PlacesModel mViewContact = null;
	EditText mEtName;
	EditText mEtMail;
	EditText mEtPhone;

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
		String mName = mViewContact.getmContactName();
		String mEmail = mViewContact.getmContactMail();
		String mPhone = mViewContact.getmContactPhone();
		mEtName.setText(mName);
		mEtName.setClickable(false);
		mEtName.setFocusable(false);
		mEtMail.setText(mEmail);
		mEtMail.setClickable(false);
		mEtMail.setFocusable(false);
		mEtPhone.setText(mPhone);
		mEtPhone.setClickable(false);
		mEtPhone.setFocusable(false);

	}

}
