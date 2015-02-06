package com.nazmul.util;

public class ImageModel {
	int mId = 0;
	String mLatitude = "";
	String mLongitude = "";
	String mRemarks = "";
	String mPhotoPath = "";
	String mDateTime = "";

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(String mLatitude) {
		this.mLatitude = mLatitude;
	}

	public String getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(String mLongitude) {
		this.mLongitude = mLongitude;
	}

	public String getmRemarks() {
		return mRemarks;
	}

	public void setmRemarks(String mRemarks) {
		this.mRemarks = mRemarks;
	}

	public String getmPhotoPath() {
		return mPhotoPath;
	}

	public void setmPhotoPath(String mPhotoPath) {
		this.mPhotoPath = mPhotoPath;
	}

	public String getmDateTime() {
		return mDateTime;
	}

	public void setmDateTime(String mDateTime) {
		this.mDateTime = mDateTime;
	}

	public ImageModel(int mId, String mLatitude, String mLongitude,
			String mRemarks,String mDateTime) {
		super();
		this.mId = mId;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mDateTime = mDateTime;
	}

	public ImageModel(String mLatitude, String mLongitude, String mRemarks,String mDateTime) {
		super();
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mDateTime = mDateTime;
	}

}
