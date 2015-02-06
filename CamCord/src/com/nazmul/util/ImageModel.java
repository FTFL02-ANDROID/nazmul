package com.nazmul.util;

public class ImageModel {
	int mId = 0;
	String mLatitude = "";
	String mLongitude = "";
	String mRemarks = "";
	String mPhotoPath = "";
	String mDate = "";
	String mTime = "";

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmLatitude() {
		return mLatitude;
	}

	

	public String getmLongitude() {
		return mLongitude;
	}

	

	public String getmRemarks() {
		return mRemarks;
	}

	

	public String getmPhotoPath() {
		return mPhotoPath;
	}

	

	public String getmDate() {
		return mDate;
	}
	public String getmTime() {
		return mTime;
	}
	

	
public ImageModel(String eLatitude, String eLongitude, String eRemarks,String ePhotoPath,String eDate,String eTime) {
		super();
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mRemarks = eRemarks;
		this.mPhotoPath = ePhotoPath;
		this.mDate = eDate;
		this.mTime = eTime;
	}

public ImageModel(int mId, String mLatitude, String mLongitude,
		String mRemarks, String mPhotoPath, String mDate, String mTime) {
	super();
	this.mId = mId;
	this.mLatitude = mLatitude;
	this.mLongitude = mLongitude;
	this.mRemarks = mRemarks;
	this.mPhotoPath = mPhotoPath;
	this.mDate = mDate;
	this.mTime = mTime;
}


}