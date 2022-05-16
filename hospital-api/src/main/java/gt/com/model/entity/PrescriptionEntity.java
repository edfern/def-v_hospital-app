package gt.com.model.entity;

import java.util.Date;

public class PrescriptionEntity {

    public PrescriptionEntity() {
    }

    private int idPrescriptiosn;
    private String prescription;
    private int idPatient;
    private Date datePrescription;
    private int idDoctor;

    public Date getDatePrescription() {

        return datePrescription;
    }

    public void setDatePrescription(Date datePrescription) {
        this.datePrescription = datePrescription;
    }

    public int getIdPrescriptiosn() {
        return idPrescriptiosn;
    }

    public void setIdPrescriptiosn(int idPrescriptiosn) {
        this.idPrescriptiosn = idPrescriptiosn;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }
}