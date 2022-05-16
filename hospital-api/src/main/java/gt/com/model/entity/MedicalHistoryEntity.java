package gt.com.model.entity;

import java.util.*;


public class MedicalHistoryEntity {

    private PatientEntity patient;
    private List<AppointmentEntity> appointments;
    private List<PrescriptionEntity> prescriptions;
    private List<RoomEntity> rooms;


    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

    public List<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionEntity> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }
}