package com.example.pockethospital.Models;

public class BedModel {
    String hsname,hsnmbr,hsadrs,bdtype,bdavl;

    public BedModel(String hsname, String hsnmbr, String hsadrs, String bdtype, String bdavl) {
        this.hsname = hsname;
        this.hsnmbr = hsnmbr;
        this.hsadrs = hsadrs;
        this.bdtype = bdtype;
        this.bdavl = bdavl;
    }

    public String getHsname() {
        return hsname;
    }

    public void setHsname(String hsname) {
        this.hsname = hsname;
    }

    public String getHsnmbr() {
        return hsnmbr;
    }

    public void setHsnmbr(String hsnmbr) {
        this.hsnmbr = hsnmbr;
    }

    public String getHsadrs() {
        return hsadrs;
    }

    public void setHsadrs(String hsadrs) {
        this.hsadrs = hsadrs;
    }

    public String getBdtype() {
        return bdtype;
    }

    public void setBdtype(String bdtype) {
        this.bdtype = bdtype;
    }

    public String getBdavl() {
        return bdavl;
    }

    public void setBdavl(String bdavl) {
        this.bdavl = bdavl;
    }

    public BedModel() {

    }
}
