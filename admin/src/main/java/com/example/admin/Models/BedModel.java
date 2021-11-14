package com.example.admin.Models;

public class BedModel {
String HospitalName,HospitalNumber,HospitalAdrs,BedAvailable,Bedtype;

    public BedModel(String hospitalName, String hospitalNumber, String hospitalAdrs, String bedAvailable, String bedtype) {
        HospitalName = hospitalName;
        HospitalNumber = hospitalNumber;
        HospitalAdrs = hospitalAdrs;
        BedAvailable = bedAvailable;
        Bedtype = bedtype;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalNumber() {
        return HospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        HospitalNumber = hospitalNumber;
    }

    public String getHospitalAdrs() {
        return HospitalAdrs;
    }

    public void setHospitalAdrs(String hospitalAdrs) {
        HospitalAdrs = hospitalAdrs;
    }

    public String getBedAvailable() {
        return BedAvailable;
    }

    public void setBedAvailable(String bedAvailable) {
        BedAvailable = bedAvailable;
    }

    public String getBedtype() {
        return Bedtype;
    }

    public void setBedtype(String bedtype) {
        Bedtype = bedtype;
    }

    public BedModel() {
    }
}
