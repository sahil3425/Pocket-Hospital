package com.example.pockethospital.Models;

public class ShareAndCare { String Name,Number,Address,ShareAbleItem;

    public ShareAndCare() {
    }

    public ShareAndCare(String name, String number, String address, String shareAbleItem) {
        Name = name;
        Number = number;
        Address = address;
        ShareAbleItem = shareAbleItem;
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

    public String getShareAbleItem() {
        return ShareAbleItem;
    }

    public void setShareAbleItem(String shareAbleItem) {
        ShareAbleItem = shareAbleItem;
    }
}
