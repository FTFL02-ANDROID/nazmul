package com.nazmul.minecare.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FTFLICareFunctions {

	public FTFLICareFunctions(){
		
	}
	public String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String mCurrentDate = dateFormat.format(date);
		return mCurrentDate;
	}
}
