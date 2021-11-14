package com.example.admin.Models;

public class BgroupModel {
    String PatientName,PatientNumber,PatientId,Patientgroup;

    public BgroupModel() {

    }
    public BgroupModel(String patientName, String patientNumber, String patientId, String patientgroup) {
        this.PatientName = patientName;
        this.PatientNumber = patientNumber;
        this.PatientId = patientId;
        this.Patientgroup = patientgroup;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        this.PatientName = patientName;
    }

    public String getPatientNumber() {
        return PatientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.PatientNumber = patientNumber;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        this.PatientId = patientId;
    }

    public String getPatientgroup() {
        return Patientgroup;
    }

    public void setPatientgroup(String patientgroup) {
        this.Patientgroup = patientgroup;
    }
}
