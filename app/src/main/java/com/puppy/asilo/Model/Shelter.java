package com.puppy.asilo.Model;

public class Shelter {
    private String Shelter_ID;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String ContactName;
    private String City;
    private String FaxNumber;

    public String getShelter_ID() {
        return Shelter_ID;
    }

    public void setShelter_ID(String shelter_ID) {
        this.Shelter_ID = shelter_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        this.ContactName = contactName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getFaxNumber() {
        return FaxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.FaxNumber = faxNumber;
    }
}
