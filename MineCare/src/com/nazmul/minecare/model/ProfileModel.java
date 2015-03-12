package com.nazmul.minecare.model;

public class ProfileModel {

	public int mId;
	public int mProfileId;
	public String mName;
	public String mGender;
	public String mBloodGroup;
	public String mAge;
	public String mWeight;
	public String mHeight;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	public String getmBloodGroup() {
		return mBloodGroup;
	}

	public void setmBloodGroup(String mBloodGroup) {
		this.mBloodGroup = mBloodGroup;
	}

	public String getmAge() {
		return mAge;
	}

	public void setmAge(String mAge) {
		this.mAge = mAge;
	}

	public String getmWeight() {
		return mWeight;
	}

	public void setmWeight(String mWeight) {
		this.mWeight = mWeight;
	}

	public String getmHeight() {
		return mHeight;
	}

	public void setmHeight(String mHeight) {
		this.mHeight = mHeight;
	}

	public ProfileModel(int mId, int mProfileId, String mName, String mGender,
			String mBloodGroup, String mAge, String mWeight, String mHeight) {
		super();
		this.mId = mId;
		this.mProfileId = mProfileId;
		this.mName = mName;
		this.mGender = mGender;
		this.mBloodGroup = mBloodGroup;
		this.mAge = mAge;
		this.mWeight = mWeight;
		this.mHeight = mHeight;
	}

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

	public ProfileModel(String mName, String mGender, String mBloodGroup,
			String mAge, String mWeight, String mHeight) {
		super();
		this.mName = mName;
		this.mGender = mGender;
		this.mBloodGroup = mBloodGroup;
		this.mAge = mAge;
		this.mWeight = mWeight;
		this.mHeight = mHeight;
	}

	public ProfileModel() {
		super();
	}

}