package com.nazmul.minecare.fragment;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ftflproject.ftflicareapplication.R;
import com.nazmul.minecare.adapter.ImageAdapter;



public class FragmentGallery extends Fragment {
	List<String> mImagePathList = new ArrayList<String>();

	public FragmentGallery() {
		/*File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				ICareConstants.IMAGE_DIRECTORY_NAME);*/
		
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		
		for (File imageFile : storageDir.listFiles()) {
			if (imageFile.isFile()) {
				mImagePathList.add(imageFile.getAbsolutePath());
			}
		}
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_gallary,
				container, false);

		GridView gridview = (GridView) view.findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(getActivity(), mImagePathList));
		/*gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showImage(position);
				
			}
		});*/

		return view;
	}
	
public void showImage(int ePosition ) {
		/*
		File imageFile = new File(mImagePathList.get(ePosition));
		Uri picUri = Uri.fromFile(imageFile);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(picUri, "image/*");
		startActivity(intent);*/
	}

}