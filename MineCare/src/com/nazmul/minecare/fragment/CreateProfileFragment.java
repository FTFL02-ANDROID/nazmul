package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ftflproject.ftflicareapplication.R;
import com.google.gson.Gson;
import com.nazmul.minecare.model.ProfileModel;

public class CreateProfileFragment extends Fragment implements OnClickListener {
	Editor prefsEditor;
	SharedPreferences myPref;

	private EditText mNameEditText;
	private Spinner mGenderSpinner;
	private EditText mBloodGroupEditText;
	private EditText mAgeEditText;
	private EditText mWeightEditText;
	private EditText mHeightEditText;

	private String mName, mGender, mBloodGroup, mAge, mWeight, mHeight;
	private ProfileModel profileModelObject;
	// private ProfileTableDataSource profileTableObject;
	private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		rootView = inflater.inflate(R.layout.fragment_create_profile,
				container, false);
		mCreateButton = (Button) rootView.findViewById(R.id.buttonSubmit);
		mCreateButton.setOnClickListener(this);
		initialize();
		return rootView;
	}

	public void initialize() {
		myPref = this.getActivity().getSharedPreferences("MyPref",
				Context.MODE_PRIVATE);
		mGenderSpinner = (Spinner) rootView.findViewById(R.id.gender);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				thiscontext, R.array.genderListSpinner,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mGenderSpinner.setAdapter(adapter);
		mNameEditText = (EditText) rootView.findViewById(R.id.editName);
		// mGenderSpinner = (Spinner) rootView.findViewById(R.id.gender);
		mBloodGroupEditText = (EditText) rootView
				.findViewById(R.id.editBloodGroup);
		mAgeEditText = (EditText) rootView.findViewById(R.id.editAge);
		mWeightEditText = (EditText) rootView.findViewById(R.id.editWeight);
		mHeightEditText = (EditText) rootView.findViewById(R.id.editHeight);
		if (myPref.contains("myProfile")) {

			Gson gson = new Gson();
			String json = myPref.getString("myProfile", "");
			profileModelObject = gson.fromJson(json, ProfileModel.class);
			mNameEditText.setText(profileModelObject.getmName());
			String compareValue = profileModelObject.getmGender();
			if (!compareValue.equals(null)) {
				int spinnerPostion = adapter.getPosition(compareValue);
				mGenderSpinner.setSelection(spinnerPostion);
				spinnerPostion = 0;
			}
			mBloodGroupEditText.setText(profileModelObject.getmBloodGroup());
			mAgeEditText.setText(profileModelObject.getmAge());
			mWeightEditText.setText(profileModelObject.getmWeight());
			mHeightEditText.setText(profileModelObject.getmHeight());

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mName = mNameEditText.getText().toString();

		mGender = String.valueOf(mGenderSpinner.getSelectedItem());

		mBloodGroup = mBloodGroupEditText.getText().toString();
		mAge = mAgeEditText.getText().toString();
		mWeight = mWeightEditText.getText().toString();
		mHeight = mHeightEditText.getText().toString();

		profileModelObject = new ProfileModel(mName, mGender, mBloodGroup,
				mAge, mWeight, mHeight);

		// saving data to shared preference
		Editor prefsEditor = myPref.edit();
		Gson gson = new Gson();
		String json = gson.toJson(profileModelObject);
		prefsEditor.putString("myProfile", json);
		prefsEditor.commit();
		
		
		Intent intent = getActivity().getIntent();
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
		 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 getActivity().overridePendingTransition(0, 0);
		 getActivity().finish();
		  
		 getActivity().overridePendingTransition(0, 0);
		 startActivity(intent); 

		//Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
		/*
		 * Toast.makeText(getActivity(), "sfsdsfsdf",
		 * Toast.LENGTH_SHORT).show();
		 */
	}
}
