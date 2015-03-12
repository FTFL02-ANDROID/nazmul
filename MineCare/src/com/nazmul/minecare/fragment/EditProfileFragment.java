package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.database.DatabaseHelperClass;
import com.nazmul.minecare.database.ProfileTableDataSource;
import com.nazmul.minecare.model.ProfileModel;
import com.nazmul.minecare.util.FTFLConstants;

public class EditProfileFragment extends Fragment implements OnClickListener {
	private EditText mNameEditText;
	private Spinner mGenderSpinner;
	private EditText mBloodGroupEditText;
	private EditText mAgeEditText;
	private EditText mWeightEditText;
	private EditText mHeightEditText;
	private int selectedId;
	private String mName, mGender, mBloodGroup, mAge, mWeight, mHeight;
	public int mIsAlarmCecked = 0;
	private DatabaseHelperClass dbHelper;
	private ProfileModel profileModelObject;
	private ProfileTableDataSource profileTableObject;
	private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		selectedId = Integer.valueOf(getArguments().getString(
				FTFLConstants.SELECTED_ID));
		rootView = inflater.inflate(R.layout.fragment_create_profile,
				container, false);
		mCreateButton = (Button) rootView.findViewById(R.id.buttonSubmit);
		mCreateButton.setOnClickListener(this);
		initialize();
		setValues();
		return rootView;
	}

	public void initialize() {
		mNameEditText = (EditText) rootView.findViewById(R.id.editName);
		mGenderSpinner = (Spinner) rootView.findViewById(R.id.gender);
		mBloodGroupEditText = (EditText) rootView
				.findViewById(R.id.editBloodGroup);
		mAgeEditText = (EditText) rootView.findViewById(R.id.editAge);
		mWeightEditText = (EditText) rootView.findViewById(R.id.editWeight);
		mHeightEditText = (EditText) rootView.findViewById(R.id.editHeight);

		profileTableObject = new ProfileTableDataSource(thiscontext);
	}

	public void setValues() {

		// profileModelObject = profileTableObject.getDoctorById(selectedId);
		mNameEditText.setText(profileModelObject.getmName());
		// mGenderSpinner.setText(profileModelObject.getmGender());
		mBloodGroupEditText.setText(profileModelObject.getmBloodGroup());
		mAgeEditText.setText(profileModelObject.getmAge());
		mWeightEditText.setText(profileModelObject.getmWeight());

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mName = mNameEditText.getText().toString();

		// mGender = mGenderSpinner.getText().toString();

		mBloodGroup = mBloodGroupEditText.getText().toString();
		mAge = mAgeEditText.getText().toString();
		mWeight = mWeightEditText.getText().toString();
		mHeight = mHeightEditText.getText().toString();

		profileModelObject = new ProfileModel(mName, mGender, mBloodGroup,
				mAge, mWeight, mHeight);
		long edited = profileTableObject.editProfile(selectedId,
				profileModelObject);
		// Toast.makeText(thiscontext,
		// mVaccineName+"d"+mVaccineDate+"f"+mVaccinationNotes, 1000).show();
		Toast.makeText(thiscontext, "" + edited, 1000).show();

		// doctorModelObject.setmProfileId(mProfileId);
		long numberOfRowAdded = profileTableObject
				.createProfile(profileModelObject);

		Toast.makeText(getActivity(), String.valueOf(numberOfRowAdded),
				Toast.LENGTH_SHORT).show();
		/*
		 * Toast.makeText(getActivity(), "sfsdsfsdf",
		 * Toast.LENGTH_SHORT).show();
		 */
	}
}
