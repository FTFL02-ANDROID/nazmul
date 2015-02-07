package com.nazmul.util;

public class ImageModel {

	String mId;
	String mDate;
	String mTime;
	String mLatitude;
	String mLongitude;
	String mRemarks;
	byte[] mImage;

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

	public byte[] getmImage() {
		return mImage;
	}

	public void setmImage(byte[] mImage) {
		this.mImage = mImage;
	}

	public ImageModel(String mId, String mDate, String mTime,
			String mLatitude, String mLongitude, String mRemarks, byte[] mImage) {
		super();
		this.mId = mId;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mImage = mImage;
	}

	public ImageModel(String mDate, String mTime, String mLatitude,
			String mLongitude, String mRemarks, byte[] mImage) {
		super();
		this.mDate = mDate;
		this.mTime = mTime;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mImage = mImage;
	}

}
