package gt.com.model.entity;

import java.util.Date;

public class AppointmentEntity {

    public AppointmentEntity() {
    }

    private int idAppointment;
    private int idPatient;
    private Date dateAppointment; //date and time
    private int idClinic;
    private Date timeAppointment;
    private String namePatient;
    private String nameClinic;



    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getNameClinic() {
        return nameClinic;
    }

    public void setNameClinic(String nameClinic) {
        this.nameClinic = nameClinic;
    }

    public Date getTimeAppointment() {
        return timeAppointment;
    }

    public void setTimeAppointment(Date timeAppointment) {
        this.timeAppointment = timeAppointment;
    }

    public int getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public int getIdClinic() {
        return idClinic;
    }

    public void setIdClinic(int idClinic) {
        this.idClinic = idClinic;
    }
}