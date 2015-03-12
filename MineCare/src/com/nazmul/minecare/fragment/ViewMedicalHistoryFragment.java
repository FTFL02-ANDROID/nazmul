package com.nazmul.minecare.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.database.DatabaseHelperClass;
import com.nazmul.minecare.database.DoctorTableDataSource;
import com.nazmul.minecare.database.MedicalHistoryTableDataSource;
import com.nazmul.minecare.model.DoctorModel;
import com.nazmul.minecare.model.MedicalHistoryModel;
import com.nazmul.minecare.util.FTFLConstants;
import com.nazmul.minecare.util.SetDateOnClickDialog;

public class ViewMedicalHistoryFragment extends Fragment {
	private ImageView mIvPhotoView = null;
	private String mCurrentPhotoPath;
	private Bitmap mBitmap;
	private int mProfileId=1;
	private DatabaseHelperClass dbHelper;
    private DoctorModel doctorModelObject;
    private DoctorTableDataSource doctorTableObject;
    
    private MedicalHistoryModel medicalHistoryObject;
    private MedicalHistoryTableDataSource medicalHistoryTableObject;
	
	
	 Context thiscontext;
	View rootView;

	private ArrayList<DoctorModel> doctorList;
	private List<String> doctorNames;

	private EditText mDoctorNameEditText;
	private SetDateOnClickDialog datePickerObj;
	private EditText mDateEditText;
	private EditText mPurposeEditText;
	private String mDoctorName;
	private String mDate;
	private String mPurpose;
	private MedicalHistoryTableDataSource history_tbl_obj;
	private Integer selectedId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		 selectedId = Integer.valueOf(getArguments().getString(FTFLConstants.SELECTED_ID));
		 rootView = inflater.inflate(R.layout.fragment_view_medical_history, container, false);
		
	  
	     initialize();
	     setValue();
	     
        return rootView;
	}
	
	public void initialize(){
		mIvPhotoView = (ImageView) rootView.findViewById(R.id.ivTakePhoto);
		doctorTableObject = new DoctorTableDataSource(thiscontext);
		doctorNames=doctorTableObject.getAllNames();
		
		
		
		
		mDoctorNameEditText=(EditText) rootView.findViewById(R.id.doctorEditText);
		mDoctorNameEditText.setFocusable(false);
		mDoctorNameEditText.setClickable(false);
		
		mDateEditText=(EditText) rootView.findViewById(R.id.dateEditText);
		mDateEditText.setFocusable(false);
		mDateEditText.setFocusable(false);
		mPurposeEditText=(EditText) rootView.findViewById(R.id.purposeEditText);
		mPurposeEditText.setFocusable(false);
		mPurposeEditText.setFocusable(false);
		//spinner.setOnItemSelectedListener(this);
		medicalHistoryTableObject= new MedicalHistoryTableDataSource(thiscontext);
		medicalHistoryObject=medicalHistoryTableObject.getMedicalHistoryId(selectedId);
		
		
		
	}
	public void setValue(){
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 4;
		mBitmap = BitmapFactory.decodeFile(medicalHistoryObject.getmPhotopath()
				, bmOptions);// bmOptions
		mIvPhotoView.setImageBitmap(mBitmap);
		mDoctorNameEditText.setText(medicalHistoryObject.getmDoctor());
		
		mDateEditText.setText(medicalHistoryObject.getmDate());
		mPurposeEditText.setText(medicalHistoryObject.getmPurpose());
	}
   
}
