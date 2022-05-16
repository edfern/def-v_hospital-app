package gt.com.model.dto;

import gt.com.model.entity.AppointmentEntity;
import java.util.List;

public class ResponseAppointmentsDto {

    private String message;
    private List<AppointmentEntity> list;

    private boolean status;
    private Object data;
    private int type; // 1 succes   2 warning  3 fail


    //constructor para getAppointments
    public ResponseAppointmentsDto(String message, List<AppointmentEntity> list, boolean statusPatient, int typeMessage) {
        this.message = message;
        this.list = list;
        this.status = statusPatient;
        this.type = typeMessage;
    }
    //contructor para create, update amd delete appointments

    public ResponseAppointmentsDto(String message, boolean statusPatient, Object responsePatientObject, int typeMessage) {
        this.message = message;
        this.status = statusPatient;
        this.data = responsePatientObject;
        this.type = typeMessage;
    }
    //contructor para mensajes de error

    public ResponseAppointmentsDto(String message, boolean statusPatient, int typeMessage) {
        this.message = message;
        this.status = statusPatient;
        this.type = typeMessage;
    }

    //getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<AppointmentEntity> getList() {
        return list;
    }

    public void setList(List<AppointmentEntity> list) {
        this.list = list;
    }

    public boolean isStatusPatient() {
        return status;
    }

    public void setStatusPatient(boolean statusPatient) {
        this.status = statusPatient;
    }

    public Object getResponsePatientObject() {
        return data;
    }

    public void setResponsePatientObject(Object responsePatientObject) {
        this.data = responsePatientObject;
    }

    public int getTypeMessage() {
        return type;
    }

    public void setTypeMessage(int typeMessage) {
        this.type = typeMessage;
    }
}
