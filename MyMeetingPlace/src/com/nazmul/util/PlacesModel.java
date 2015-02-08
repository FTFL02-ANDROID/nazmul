package com.nazmul.util;

public class PlacesModel {

	String mId;
	String mDate;
	String mTime;
	String mLatitude;
	String mLongitude;
	String mRemarks;
	String mPhotoPath;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmTime() {
		return mTime;
	}
	public void setmTime(String mTime) {
		this.mTime = mTime;
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
	public PlacesModel(String mId, String mDate, String mTime,
			String mLatitude, String mLongitude, String mRemarks,
			String mPhotoPath) {
		super();
		this.mId = mId;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mPhotoPath = mPhotoPath;
	}
	public PlacesModel(String mDate, String mTime, String mLatitude,
			String mLongitude, String mRemarks, String mPhotoPath) {
		super();
		this.mDate = mDate;
		this.mTime = mTime;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mPhotoPath = mPhotoPath;
	}
	public PlacesModel() {
		super();
	}
	
	
}
