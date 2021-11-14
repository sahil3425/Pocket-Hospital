package com.example.admin.Models;

public class MealsModel {
    String Name, Number, Address, Covid;

    public MealsModel() {

    }

    public MealsModel(String Name, String Number, String Address, String Covid) {
        this.Name = Name;
        this.Number = Number;
        this.Address = Address;
        this.Covid = Covid;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCovid() {
        return Covid;
    }

    public void setCovid(String covid) {
        Covid = covid;
    }
}

