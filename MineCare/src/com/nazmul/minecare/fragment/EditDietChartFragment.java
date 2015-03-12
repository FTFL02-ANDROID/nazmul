package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.database.DietTableDataSource;
import com.nazmul.minecare.model.DietModel;
import com.nazmul.minecare.util.FTFLConstants;
import com.nazmul.minecare.util.SetDateOnClickDialog;
import com.nazmul.minecare.util.setTimeOnclickDialog;

public class EditDietChartFragment extends Fragment implements OnClickListener {
	private EditText dietNameEditText;
	private EditText dateEditText;
	private EditText timeEditText;
	private EditText menuDescriptionEditText;
	private String mDietName, mDietDate, mDietTime, mDay, mDietMenuDescription;
	private int profileId = 1;
	private int mIsAlarmCecked = 0;
	private DietTableDataSource dietTableObject;
	private DietModel selectedDiet;
	private int selectedId;
	private SetDateOnClickDialog datePickerObj;
	private setTimeOnclickDialog timePickerObj;
	private Button mEditButton;
	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();

		selectedId = Integer.valueOf(getArguments().getString(
				FTFLConstants.SELECTED_ID));
		rootView = inflater.inflate(R.layout.fragment_edit_diet_chart,
				container, false);
		mEditButton = (Button) rootView.findViewById(R.id.editDietChartButton);
		mEditButton.setOnClickListener(this);
		initialize();
		setDateToview();

		return rootView;

	}

	public void initialize() {

		dietTableObject = new DietTableDataSource(thiscontext);
		// fetch value the selected diet
		selectedDiet = dietTableObject.getDietById(selectedId);

		dietNameEditText = (EditText) rootView
				.findViewById(R.id.dietNameEditText);

		dateEditText = (EditText) rootView.findViewById(R.id.dateEditText);
		/*
		 * passing editText to SetDateOnClickDialog class
		 */
		/*
		 * to show date on focus and put value into EditTExt
		 */
		datePickerObj = new SetDateOnClickDialog(dateEditText, thiscontext);

		int dayOfWeek = datePickerObj.dayOfWeek;

		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		timeEditText = (EditText) rootView.findViewById(R.id.timeEditText);

		setTimeOnclickDialog timePickerObj = new setTimeOnclickDialog(
				timeEditText, thiscontext);

		menuDescriptionEditText = (EditText) rootView
				.findViewById(R.id.menuEditText);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		mDietName = dietNameEditText.getText().toString();

		mDietDate = dateEditText.getText().toString();

		mDietTime = timeEditText.getText().toString();

		mDietMenuDescription = menuDescriptionEditText.getText().toString();

		DietModel dietModelObject = new DietModel(mDietName, mDietDate,
				mDietTime, mDay, mDietMenuDescription);
		dietModelObject.setmProfileId(profileId);

		dietTableObject = new DietTableDataSource(thiscontext);

		long notificationOnEdit = dietTableObject.editDiet(selectedId,
				dietModelObject);

		Intent intent = getActivity().getIntent();
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_NO_ANIMATION);
		getActivity().overridePendingTransition(0, 0);
		getActivity().finish();

		getActivity().overridePendingTransition(0, 0);
		startActivity(intent);

		/*
		 * Toast.makeText(getActivity(), String.valueOf(notificationOnEdit),
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void setDateToview() {

		dietNameEditText.setText(selectedDiet.getmDietName().toString());

		menuDescriptionEditText.setText(selectedDiet.getmDietMenu().toString());

		dateEditText.setText(selectedDiet.getmDietDate().toString());

		timeEditText.setText(selectedDiet.getmDietTime().toString());

	}

}
