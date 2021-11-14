package com.example.pockethospital.Models;

public class BgroupModel {
    String PatientName,PatientNumber,PatientId,Patientgroup;

    public BgroupModel(String patientName, String patientNumber, String patientId, String patientgroup) {
        PatientName = patientName;
        PatientNumber = patientNumber;
        PatientId = patientId;
        Patientgroup = patientgroup;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientNumber() {
        return PatientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        PatientNumber = patientNumber;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPatientgroup() {
        return Patientgroup;
    }

    public void setPatientgroup(String patientgroup) {
        Patientgroup = patientgroup;
    }
}
