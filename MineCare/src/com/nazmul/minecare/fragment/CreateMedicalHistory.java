package com.nazmul.minecare.fragment;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ftflproject.ftflicareapplication.R;

public class CreateMedicalHistory extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_create_medical_history, container, false);
		
		/* Button button = (Button) rootView.findViewById(R.id.button1);
			   button.setOnClickListener(new OnClickListener()
			   {
			             @Override
			             public void onClick(View v)
			             {
			                // do something
			            	 
			            	 CreateProfileFragment ldf = new CreateProfileFragment ();
			           	  Bundle args = new Bundle();
			           	  args.putString("YourKey", "YourValue");
			           	  ldf.setArguments(args);

			           	  //Inflate the fragment
			           	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
			             } 
			   }); */
        
        return rootView;
	}
  /*public void click(View v){
	  GrowthFragment ldf = new GrowthFragment ();
	  Bundle args = new Bundle();
	  args.putString("YourKey", "YourValue");
	  ldf.setArguments(args);

	  //Inflate the fragment
	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
  }*/
}
