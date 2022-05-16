package gt.com.model.entity;

import java.util.Date;

public class GuestAppointmentEntity {

    private int idGuestAppointment;
    private String fullName;
    private String surName;
    private String address;
    private Date date;
    private Date hour;
    private int idClinic;

    public int getIdClinic() {
        return idClinic;
    }

    public void setIdClinic(int idClinic) {
        this.idClinic = idClinic;
    }

    public int getIdGuestAppointment() {
        return idGuestAppointment;
    }

    public void setIdGuestAppointment(int idGuestAppointment) {
        this.idGuestAppointment = idGuestAppointment;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }
}
