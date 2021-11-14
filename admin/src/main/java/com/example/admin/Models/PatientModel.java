package com.example.admin.Models;

public class PatientModel {
    String PatientName,PatientNumber,PatientId,PatientComplaint;

    public PatientModel() {

    }

    public PatientModel(String patientName, String patientNumber, String patientId, String patientComplaint) {
        PatientName = patientName;
        PatientNumber = patientNumber;
        PatientId = patientId;
        PatientComplaint = patientComplaint;
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

    public String getPatientComplaint() {
        return PatientComplaint;
    }

    public void setPatientComplaint(String patientComplaint) {
        PatientComplaint = patientComplaint;
    }
}
