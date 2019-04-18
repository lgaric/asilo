package com.puppy.asilo.Model;

public class Donation {
    private String DonationID;
    private String Shelter_ID;
    private String User_ID;
    private String Amount;
    private String Date;
    private String Comment;

    public String getDonationID() {
        return DonationID;
    }

    public void setDonationID(String donationID) {
        this.DonationID = donationID;
    }

    public String getShelter_ID() {
        return Shelter_ID;
    }

    public void setShelter_ID(String shelter_ID) {
        this.Shelter_ID = shelter_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        this.User_ID = user_ID;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }
}
