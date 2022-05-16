package gt.com.model.dto;

import gt.com.model.entity.PatientEntity;

import java.util.List;

public class ResponsePatientDto{

    private String message;
    private List<PatientEntity> responsePatient;
    private boolean status;
    private Object responsePatientObject;
    private int typeMessage; // 1 succes   2 warning  3 fail

    //constructor para getpatients

    public ResponsePatientDto(String message, List<PatientEntity> responsePatient, boolean statusPatient, int typeMessage) {
        this.message = message;
        this.responsePatient = responsePatient;
        this.status = statusPatient;
        this.typeMessage = typeMessage;
    }


    //constructor para getPatientById

    public ResponsePatientDto(String message, boolean statusPatient, Object responsePatientObject, int typeMessage) {
        this.message = message;
        this.status = statusPatient;
        this.responsePatientObject = responsePatientObject;
        this.typeMessage = typeMessage;
    }


    //constructor para  savePatient UpdatePatient DeletePaient

    public ResponsePatientDto(String message, boolean statusPatient, int typeMessage) {
        this.message = message;
        this.status = statusPatient;
        this.typeMessage = typeMessage;
    }

    public ResponsePatientDto(Object responsePatientObject) {
        this.responsePatientObject = responsePatientObject;
    }
    //getters and setters

    public List<PatientEntity> getResponsePatient() {
        return responsePatient;
    }

    public void setResponsePatient(List<PatientEntity> responsePatient) {
        this.responsePatient = responsePatient;
    }

    public String getErrorMessage() {
        return message;
    }

    public void setErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }


}
