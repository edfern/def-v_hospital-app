package gt.com.model.entity;

public class PatientRoomEntity {

    private int idPatientRoom;
    private boolean status;
    private int idPatient;
    private int idRoom;

    public int getIdPatientRoom() {
        return idPatientRoom;
    }

    public void setIdPatientRoom(int idPatientRoom) {
        this.idPatientRoom = idPatientRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
