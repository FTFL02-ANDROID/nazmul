package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
import com.nazmul.minecare.database.DatabaseHelperClass;
import com.nazmul.minecare.database.VaccineTableDataSource;
import com.nazmul.minecare.model.VaccinationModel;
import com.nazmul.minecare.util.SetDateOnClickDialog;
import com.nazmul.minecare.util.setTimeOnclickDialog;

public class CreateVaccinationFragment extends Fragment implements
		OnClickListener {

	private Spinner spinner;
	private EditText mVaccinNameEditText;
	private EditText mVaccinationDateEditText;
	private EditText mVaccinationNotesEditText;
	


	
	private String mVaccineName, mVaccineDate,mVaccinationNotes;
	public int mIsAlarmCecked = 0;
	private DatabaseHelperClass dbHelper;
	private int profileId = 1;
	private VaccineTableDataSource vac_tbl_obj;
	private SetDateOnClickDialog datePickerObj;
	private setTimeOnclickDialog timePickerObj;
	private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();

		rootView = inflater.inflate(R.layout.fragment_create_vaccination,
				container, false);

		mCreateButton = (Button) rootView.findViewById(R.id.createVaccine);
		mCreateButton.setOnClickListener(this);
		initialize();
		return rootView;

	}

	public void initialize() {
		spinner = (Spinner) rootView.findViewById(R.id.planets);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				thiscontext, R.array.sp, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		mVaccinationDateEditText= (EditText) rootView.findViewById(R.id.pickDayTextView);
		mVaccinationNotesEditText= (EditText) rootView.findViewById(R.id.vaccineStatusEditText);

		datePickerObj = new SetDateOnClickDialog(mVaccinationDateEditText, thiscontext);

		

		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		mVaccineDate = mVaccinationDateEditText.getText().toString();
		mVaccineName=String.valueOf(spinner.getSelectedItem());
        mVaccinationNotes=mVaccinationNotesEditText.getText().toString();
		
        
        VaccinationModel vaccineModelObject = new VaccinationModel(profileId,mVaccineName,mVaccineDate,
        		mVaccinationNotes);
       

		vac_tbl_obj = new VaccineTableDataSource(thiscontext);

		long numberOfRowAdded = vac_tbl_obj.AddVaccineInformation(vaccineModelObject);
		
		Intent intent = getActivity().getIntent();
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
		 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 getActivity().overridePendingTransition(0, 0);
		 getActivity().finish();
		  
		 getActivity().overridePendingTransition(0, 0);
		 startActivity(intent); 
       // Toast.makeText(thiscontext, mVaccineName+"d"+mVaccineDate+"f"+mVaccinationNotes, 1000).show();
		 //Toast.makeText(thiscontext,""+numberOfRowAdded , 1000).show();
	}
}
