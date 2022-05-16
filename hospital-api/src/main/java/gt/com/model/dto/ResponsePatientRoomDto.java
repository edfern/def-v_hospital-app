package gt.com.model.dto;

import gt.com.model.entity.PatientRoomEntity;

import java.util.List;

public class ResponsePatientRoomDto {

    private String message;
    private List<PatientRoomEntity> responsePatientRoom;
    private boolean status;
    private Object responsePatientRoomObject;
    private int typeMessage; // 1 succes   2 warning  3 fail

    public ResponsePatientRoomDto(String message, boolean status, int typeMessage) {
        this.message = message;
        this.status = status;
        this.typeMessage = typeMessage;
    }

    public ResponsePatientRoomDto(String message, List<PatientRoomEntity> responsePatientRoom, boolean status, int typeMessage) {
        this.message = message;
        this.responsePatientRoom = responsePatientRoom;
        this.status = status;
        this.typeMessage = typeMessage;
    }

    public ResponsePatientRoomDto(String message, boolean status, Object responsePatientRoomObject, int typeMessage) {
        this.message = message;
        this.status = status;
        this.responsePatientRoomObject = responsePatientRoomObject;
        this.typeMessage = typeMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PatientRoomEntity> getResponsePatientRoom() {
        return responsePatientRoom;
    }

    public void setResponsePatientRoom(List<PatientRoomEntity> responsePatientRoom) {
        this.responsePatientRoom = responsePatientRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getResponsePatientRoomObject() {
        return responsePatientRoomObject;
    }

    public void setResponsePatientRoomObject(Object responsePatientRoomObject) {
        this.responsePatientRoomObject = responsePatientRoomObject;
    }

    public int getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(int typeMessage) {
        this.typeMessage = typeMessage;
    }
}
