package com.example.publicuniversity_v2.util;

public class PublicUniversity {

	String mId = "";
	String mName = "";
	String mDescription = "";
	String mAddress = "";
	String mLatitude = "";
	String mLongitude = "";
	String mCourse = "";
	String mStudent = "";
	String mTeachers = "";

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
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

	public String getmCourse() {
		return mCourse;
	}

	public void setmCourse(String mCourse) {
		this.mCourse = mCourse;
	}

	public String getmStudent() {
		return mStudent;
	}

	public void setmStudent(String mStudent) {
		this.mStudent = mStudent;
	}

	public String getmTeachers() {
		return mTeachers;
	}

	public void setmTeachers(String mTeachers) {
		this.mTeachers = mTeachers;
	}

	public PublicUniversity() {

	}

	public PublicUniversity(String mId, String mName, String mDescription,
			String mAddress, String mLatitude, String mLongitude,
			String mCourse, String mStudent, String mTeachers) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mDescription = mDescription;
		this.mAddress = mAddress;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mCourse = mCourse;
		this.mStudent = mStudent;
		this.mTeachers = mTeachers;
	}

	
}
