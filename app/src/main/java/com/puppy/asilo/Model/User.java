package com.puppy.asilo.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String mUserID;
    private String mUserTypeID;
    private String mShelterID;
    private String mFirstName;
    private String mLastName;
    private String mPassword;
    private String mEmail;
    private String mPhone;
    private String mAddress;
    private String mCounty;
    private Boolean mGiveFood;
    private Boolean mGiveMoney;
    private Boolean mVolunteer;
    private Boolean mHelp;
    private Boolean mAdopt;
    private Boolean mDogPerson;
    private Boolean mCatPerson;

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }

    public String getmUserTypeID() {
        return mUserTypeID;
    }

    public void setmUserTypeID(String mUserTypeID) {
        this.mUserTypeID = mUserTypeID;
    }

    public String getmShelterID() {
        return mShelterID;
    }

    public void setmShelterID(String mShelterID) {
        this.mShelterID = mShelterID;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmCounty() {
        return mCounty;
    }

    public void setmCounty(String mCounty) {
        this.mCounty = mCounty;
    }

    public Boolean getmGiveFood() {return mGiveFood;}

    public void setmGiveFood(Boolean mGiveFood) {this.mGiveFood = mGiveFood;}

    public Boolean getmGiveMoney() {return mGiveMoney;}

    public void setmGiveMoney(Boolean mGiveMoney) {this.mGiveMoney = mGiveMoney;}

    public Boolean getmVolunteer() {return mVolunteer;}

    public void setmVolunteer(Boolean mVolunteer) {this.mVolunteer = mVolunteer;}

    public Boolean getmHelp() {return mHelp;}

    public void setmHelp(Boolean mHelp) {this.mHelp = mHelp;}

    public Boolean getmAdopt() {return mAdopt;}

    public void setmAdopt(Boolean mAdopt) {this.mAdopt = mAdopt;}

    public Boolean getmDogPerson() {return mDogPerson;}

    public void setmDogPerson(Boolean mDogPerson) {this.mDogPerson = mDogPerson;}

    public Boolean getmCatPerson() {return mCatPerson;}

    public void setmCatPerson(Boolean mCatPerson) {this.mCatPerson = mCatPerson;}
}
