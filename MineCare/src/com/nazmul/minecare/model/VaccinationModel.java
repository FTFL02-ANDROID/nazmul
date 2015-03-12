package com.nazmul.minecare.model;

public class VaccinationModel {
	public int mId;
	public int mProfileId;
	public String mVaccine;
	public String mDate;
	public String mNotes;

	public String getmVaccine() {
		return mVaccine;
	}

	public void setmVaccine(String mVaccine) {
		this.mVaccine = mVaccine;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getmNotes() {
		return mNotes;
	}

	public void setmNotes(String mNotes) {
		this.mNotes = mNotes;
	}

	public VaccinationModel(int mId, int mProfileId, String mVaccine,
			String mDate, String mNotes) {
		super();
		this.mId = mId;
		this.mProfileId = mProfileId;
		this.mVaccine = mVaccine;
		this.mDate = mDate;
		this.mNotes = mNotes;
	}

	public VaccinationModel(int mProfileId,String mVaccine, String mDate, String mNotes) {
		super();
		this.mProfileId=mProfileId;
		this.mVaccine = mVaccine;
		this.mDate = mDate;
		this.mNotes = mNotes;
	}

	public VaccinationModel() {
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

}
