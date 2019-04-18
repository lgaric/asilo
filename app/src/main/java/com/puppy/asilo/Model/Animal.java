package com.puppy.asilo.Model;

public class Animal {
    private String AnimalID;
    private String Shelter_ID;
    private String AnimalType;
    private String Name;
    private String Sex;
    private String Age;
    private String Size;
    private String VaccinesTaken;
    private String Weight;
    private boolean Castrated;
    private String ArrivalDate;
    private boolean Adopted;
    private boolean HasChip;
    private String Description;


    public String getAnimalID() {
        return AnimalID;
    }

    public void setAnimalID(String animalID) {
        this.AnimalID = animalID;
    }

    public String getShelter_ID() {
        return Shelter_ID;
    }

    public void setShelter_ID(String shelter_ID) {
        this.Shelter_ID = shelter_ID;
    }

    public String getAnimalType() {
        return AnimalType;
    }

    public void setAnimalType(String animalType) {
        this.AnimalType = animalType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        this.Age = age;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        this.Size = size;
    }

    public String getVaccinesTaken() {
        return VaccinesTaken;
    }

    public void setVaccinesTaken(String vaccinesTaken) {
        this.VaccinesTaken = vaccinesTaken;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        this.Weight = weight;
    }

    public boolean getCastrated() {
        return Castrated;
    }

    public void setCastrated(boolean castrated) {
        this.Castrated = castrated;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.ArrivalDate = arrivalDate;
    }

    public boolean getAdopted() {
        return Adopted;
    }

    public void setAdopted(boolean adopted) {
        this.Adopted = adopted;
    }

    public boolean getHasChip() {
        return HasChip;
    }

    public void setHasChip(boolean hasChip) {
        this.HasChip = hasChip;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
