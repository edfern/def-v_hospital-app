package gt.com.model.entity;

import java.util.*;

public class PatientEntity {

    public PatientEntity() {
    }

    private int idPatient;
    private String dpi;
    private String nit;
    private Date birthday;
    private char gender;// F or M
    private String fullName;
    private String surName;
    private String address;
    private int phone;
    private int emergencyContact;
    private boolean statePatient;
    private boolean statusWait;

    public boolean isStatusWait() {
        return statusWait;
    }

    public void setStatusWait(boolean statusWait) {
        this.statusWait = statusWait;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(int emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public boolean isStatePatient() {
        return statePatient;
    }

    public void setStatePatient(boolean statePatient) {
        this.statePatient = statePatient;
    }
}