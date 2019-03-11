package com.puppy.asilo.Model;

public class AdoptionRequest {
    private String mAdoptionRequestID;
    private String mAnimalID;
    private String mDate;
    private String mTime;
    private String mComment;
    private String mAccepted;

    public String getmAdoptionRequestID() {
        return mAdoptionRequestID;
    }

    public void setmAdoptionRequestID(String mAdoptionRequestID) {
        this.mAdoptionRequestID = mAdoptionRequestID;
    }

    public String getmAnimalID() {
        return mAnimalID;
    }

    public void setmAnimalID(String mAnimalID) {
        this.mAnimalID = mAnimalID;
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

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    public String getmAccepted() {
        return mAccepted;
    }

    public void setmAccepted(String mAccepted) {
        this.mAccepted = mAccepted;
    }
}
