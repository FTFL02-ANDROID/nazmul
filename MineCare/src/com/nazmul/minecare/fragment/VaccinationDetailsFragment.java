package com.nazmul.minecare.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ftflproject.ftflicareapplication.R;

public class VaccinationDetailsFragment extends Fragment {

	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		rootView = inflater.inflate(R.layout.vaccination_details, container, false);

		return rootView;
	}

}
