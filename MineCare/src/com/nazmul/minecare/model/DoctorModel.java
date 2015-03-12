package com.nazmul.minecare.model;

public class DoctorModel {
	public int mId;
	public int mProfileId;
	public String mName;
	public String mSpeciality;
	public String mPhone;
	public String mEmail;
	public String mChamber;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmSpeciality() {
		return mSpeciality;
	}

	public void setmSpeciality(String mSpeciality) {
		this.mSpeciality = mSpeciality;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmChamber() {
		return mChamber;
	}

	public void setmChamber(String mChamber) {
		this.mChamber = mChamber;
	}

	public DoctorModel() {
		super();
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

	public DoctorModel(int eId, int eProfileId, String eName,
			String eSpeciality, String ePhone, String eEmail, String eChamber) {
		super();
		this.mId = eId;
		this.mProfileId = eProfileId;
		this.mName = eName;
		this.mSpeciality = eSpeciality;
		this.mPhone = ePhone;
		this.mEmail = eEmail;
		this.mChamber = eChamber;
	}

	public DoctorModel(String eName, String eSpeciality, String ePhone,
			String eEmail, String eChamber) {
		super();
		this.mName = eName;
		this.mSpeciality = eSpeciality;
		this.mPhone = ePhone;
		this.mEmail = eEmail;
		this.mChamber = eChamber;
	}

}
