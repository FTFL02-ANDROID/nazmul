package com.nazmul.minecare.model;

public class MedicalHistoryModel {
	public int mId;
	public int mProfileId;
	public String mPhotopath;
	public String mDate;
	public String mDoctor;
	public String mPurpose;

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public int getmProfileId() {
		return mProfileId;
	}

	public void setmProfileId(int mProfileId) {
		this.mProfileId = mProfileId;
	}

	public String getmPhotopath() {
		return mPhotopath;
	}

	public void setmPhotopath(String mPhotopath) {
		this.mPhotopath = mPhotopath;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getmDoctor() {
		return mDoctor;
	}

	public void setmDoctor(String mDoctor) {
		this.mDoctor = mDoctor;
	}

	public String getmPurpose() {
		return mPurpose;
	}

	public void setmPurpose(String mPurpose) {
		this.mPurpose = mPurpose;
	}

	public MedicalHistoryModel(int eId, int eProfileId, String ePhotopath,
			String eDate, String eDoctor, String ePurpose) {
		super();
		this.mId = eId;
		this.mProfileId = eProfileId;
		this.mPhotopath = ePhotopath;
		this.mDate = eDate;
		this.mDoctor = eDoctor;
		this.mPurpose = ePurpose;
	}

	public MedicalHistoryModel(String ePhotopath, String eDate, String eDoctor,
			String ePurpose) {
		super();
		this.mPhotopath = ePhotopath;
		this.mDate = eDate;
		this.mDoctor = eDoctor;
		this.mPurpose = ePurpose;
	}

	public MedicalHistoryModel() {
		super();
	}

}
