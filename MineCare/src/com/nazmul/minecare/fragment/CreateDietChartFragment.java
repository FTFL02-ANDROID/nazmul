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
import com.nazmul.minecare.database.DatabaseHelperClass;
import com.nazmul.minecare.database.DietTableDataSource;
import com.nazmul.minecare.model.DietModel;
import com.nazmul.minecare.util.SetDateOnClickDialog;
import com.nazmul.minecare.util.setTimeOnclickDialog;



public class CreateDietChartFragment extends Fragment implements OnClickListener{
	private EditText dietNameEditText;
	private EditText dateEditText;
	private EditText timeEditText;
	private EditText menuDescriptionEditText;
	private String mDietName, mDietDate, mDietTime, mDay, mDietMenuDescription;
	private DatabaseHelperClass dbHelper;
	private int profileId = 1;
	private DietTableDataSource dietTableObject;
	private SetDateOnClickDialog datePickerObj;
	private setTimeOnclickDialog timePickerObj;
	private Button mCreateButton;
	 Context thiscontext;
	View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		
	        
		rootView = inflater.inflate(R.layout.fragment_create_diet_chart, container, false);
		  mCreateButton=(Button) rootView.findViewById(R.id.addDietChartButton);
		     mCreateButton.setOnClickListener(this);
		initialize();
        return rootView;
        
	}
	public void initialize() {
       
		dietNameEditText = (EditText) rootView.findViewById(R.id.dietNameEditText);
		dateEditText = (EditText) rootView.findViewById(R.id.dateEditText);
		/*
		 *  passing editText to SetDateOnClickDialog class
		 */
		/*
		 *  to show date on focus and put value into EditTExt
		 */
		datePickerObj = new SetDateOnClickDialog(dateEditText, thiscontext);

		int dayOfWeek = datePickerObj.dayOfWeek;

		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		timeEditText = (EditText) rootView.findViewById(R.id.timeEditText);
		/*
		 *  passing editText to setTimeOnclickDialog class
		 */
		/*
		 *  to show date on focus and put value into EditTExt
		 */
		timePickerObj = new setTimeOnclickDialog(timeEditText, thiscontext);

		menuDescriptionEditText = (EditText) rootView.findViewById(R.id.menuEditText);


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
		
		
		

		long numberOfRowAdded = dietTableObject.createDiet(dietModelObject);
		
		
		
		Intent intent = getActivity().getIntent();
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
		 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 getActivity().overridePendingTransition(0, 0);
		 getActivity().finish();
		  
		 getActivity().overridePendingTransition(0, 0);
		 startActivity(intent); 

		/*Toast.makeText(getActivity(), String.valueOf(numberOfRowAdded),
				Toast.LENGTH_SHORT).show();
		Toast.makeText(this.getActivity(), 
	            "Button is clicked!", Toast.LENGTH_LONG).show(); */
	}


	
}
