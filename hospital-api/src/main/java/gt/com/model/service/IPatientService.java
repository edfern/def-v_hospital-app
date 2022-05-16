package gt.com.model.service;

import gt.com.model.dto.ResponsePatientRoomDto;
import gt.com.model.entity.PatientEntity;
import gt.com.model.dto.ResponsePatientDto;

import javax.servlet.http.HttpServletRequest;

public interface IPatientService {

    public ResponsePatientDto getPatients(HttpServletRequest resq);
    public ResponsePatientDto savePatient(HttpServletRequest request) throws Exception;
    public ResponsePatientDto deletePatient(int idPatient);
    public ResponsePatientDto updatePatient(HttpServletRequest request);
    public ResponsePatientDto getPatientById(int id);
    public ResponsePatientDto updateStatusPatient(HttpServletRequest request) throws Exception;
    public ResponsePatientDto updateStatus(HttpServletRequest request) throws Exception;

}
