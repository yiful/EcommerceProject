package com.yiful.ecommerceproject.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable{
	private String msg;
	private String UserMobile;
	private String UserName;
	@SerializedName("AppApiKey ")
	private String AppApiKey;
	private String UserID;
	private String UserEmail;

	public User(String msg, String userMobile, String userName, String appApiKey, String userID, String userEmail) {
		this.msg = msg;
		UserMobile = userMobile;
		UserName = userName;
		AppApiKey = appApiKey;
		UserID = userID;
		UserEmail = userEmail;
	}

	protected User(Parcel in) {
		msg = in.readString();
		UserMobile = in.readString();
		UserName = in.readString();
		AppApiKey = in.readString();
		UserID = in.readString();
		UserEmail = in.readString();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserMobile() {
		return UserMobile;
	}

	public void setUserMobile(String userMobile) {
		UserMobile = userMobile;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getAppApiKey() {
		return AppApiKey;
	}

	public void setAppApiKey(String appApiKey) {
		AppApiKey = appApiKey;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String toString(){
		String s = null;
		s = msg + " "+UserID +" "+UserName+" "+UserEmail+" "+UserMobile+" "+AppApiKey;
		return s;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(msg);
		parcel.writeString(UserMobile);
		parcel.writeString(UserName);
		parcel.writeString(AppApiKey);
		parcel.writeString(UserID);
		parcel.writeString(UserEmail);
	}
}