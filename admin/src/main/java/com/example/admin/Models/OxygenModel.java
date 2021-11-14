package com.example.admin.Models;

public class OxygenModel {
    String name,number,address,totalUnits,unitsleft;

    public OxygenModel(String name, String number, String address, String totalUnits, String unitsleft) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.totalUnits = totalUnits;
        this.unitsleft = unitsleft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(String totalUnits) {
        this.totalUnits = totalUnits;
    }

    public String getUnitsleft() {
        return unitsleft;
    }

    public void setUnitsleft(String unitsleft) {
        this.unitsleft = unitsleft;
    }
}
