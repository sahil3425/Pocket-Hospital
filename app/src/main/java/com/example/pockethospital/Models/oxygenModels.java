package com.example.pockethospital.Models;

public class oxygenModels {
    String CompanyName,CompanyAddress,CompanyNumber,TotalUnits,Unitsleft;

    public oxygenModels(String companyName, String companyAddress, String companyNumber, String totalUnits, String unitsleft) {
        CompanyName = companyName;
        CompanyAddress = companyAddress;
        CompanyNumber = companyNumber;
        TotalUnits = totalUnits;
        Unitsleft = unitsleft;
    }

    public oxygenModels() {

    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyNumber() {
        return CompanyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        CompanyNumber = companyNumber;
    }

    public String getTotalUnits() {
        return TotalUnits;
    }

    public void setTotalUnits(String totalUnits) {
        TotalUnits = totalUnits;
    }

    public String getUnitsleft() {
        return Unitsleft;
    }

    public void setUnitsleft(String unitsleft) {
        Unitsleft = unitsleft;
    }
}
