package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.database.DoctorTableDataSource;
import com.nazmul.minecare.model.DoctorModel;
import com.nazmul.minecare.util.FTFLConstants;

public class ViewDoctorsProfileFragment extends Fragment {
	private TextView mDoctortNameTextView;
	private TextView mSpecialityTextView;
	private TextView mPhoneTextView;
	private TextView mEmailTextView;
	private TextView mChamberTextView;
	private int selectedId;

	public int mIsAlarmCecked = 0;

	private DoctorModel doctorModelObject;
	private DoctorTableDataSource doctorTableObject;
	private Button mCreateButton;
	private ImageButton call;
	private ImageButton mail;

	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		selectedId = Integer.valueOf(getArguments().getString(
				FTFLConstants.SELECTED_ID));
		rootView = inflater.inflate(R.layout.fragment_view_doctors_profile,
				container, false);
		call = (ImageButton) rootView.findViewById(R.id.callIB);
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ doctorModelObject.getmPhone()));
				startActivity(intent);
			}
		});

		mail = (ImageButton) rootView.findViewById(R.id.mailIB);
		mail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri
						.fromParts("mailto", doctorModelObject.getmEmail(),
								null));
				// emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
				startActivity(Intent
						.createChooser(emailIntent, "Send email..."));
			}
		});

		initialize();
		setValues();
		return rootView;
	}

	public void initialize() {
		mDoctortNameTextView = (TextView) rootView
				.findViewById(R.id.doctorNameShowTextView);
		mSpecialityTextView = (TextView) rootView
				.findViewById(R.id.specialityShowTextView);
		mPhoneTextView = (TextView) rootView
				.findViewById(R.id.phoneShowTextView);
		mEmailTextView = (TextView) rootView
				.findViewById(R.id.emailShowTextView);
		mChamberTextView = (TextView) rootView
				.findViewById(R.id.chamberShowTextView);

		doctorTableObject = new DoctorTableDataSource(thiscontext);
	}

	public void setValues() {

		doctorModelObject = doctorTableObject.getDoctorById(selectedId);
		mDoctortNameTextView.setText(doctorModelObject.getmName());
		mSpecialityTextView.setText(doctorModelObject.getmSpeciality());
		mPhoneTextView.setText(doctorModelObject.getmPhone());
		mEmailTextView.setText(doctorModelObject.getmEmail());
		mChamberTextView.setText(doctorModelObject.getmChamber());

	}

}
