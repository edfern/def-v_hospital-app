package gt.com.model.service;

import gt.com.model.dao.*;
import gt.com.model.dto.ResponsePatientDto;
import gt.com.model.dto.ResponsePatientRoomDto;
import gt.com.model.entity.PatientEntity;
import gt.com.model.entity.PatientRoomEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static gt.com.model.configurationApp.MessagesErrorApp.*;

public class PatientRoomService implements IPatientRoomService{
    @Override
    public ResponsePatientRoomDto savePatientRoom(HttpServletRequest request) throws Exception {
        IPatientRoomDao patientRoomDao = new PatientRoomDao();
        boolean  patientlocal = false;
        PatientRoomEntity patientRoomEntity = new PatientRoomEntity();

        if (request.getParameter("idPatient").isEmpty()) {
            return new ResponsePatientRoomDto("Se necesita el parametro idPatient",false,3);
        } else {
            patientRoomEntity.setIdPatient(Integer.parseInt(request.getParameter("idPatient")));
        }
        if (request.getParameter("idRoom").isEmpty()) {
            return new ResponsePatientRoomDto("Se necesita el parametro idRoom",false,3);
        } else {
            patientRoomEntity.setIdRoom(Integer.parseInt(request.getParameter("idRoom")));
        }

        try {
            patientlocal = patientRoomDao.savePatientRoom(patientRoomEntity);
        } catch (SQLException ex) {
            System.out.println(ex);
            return new ResponsePatientRoomDto(SQL_ERROR_QUERY,false,3);
        }
        if (patientlocal == true) {
            return new ResponsePatientRoomDto(SQL_TIME_OUT,false,3);
        }
        else{
            IRoomDao roomUpdate = new RoomDao();
           try {
               roomUpdate.updateRoomStatus(Integer.parseInt(request.getParameter("idRoom")),false);
               return new ResponsePatientRoomDto(SUCCESS,true,1);
           }catch (SQLException ex){
               return new ResponsePatientRoomDto(SQL_ERROR_QUERY,false,3);
           }
        }
    }

    @Override
    public ResponsePatientRoomDto updatePatientRoom(HttpServletRequest request) throws Exception {
        IPatientRoomDao patientRoomDao = new PatientRoomDao();
        int patientlocal;
        String idPatient;
        Boolean status;

        try {
             idPatient = request.getParameter("idPatient");
             status = Boolean.parseBoolean(request.getParameter("status"));
            try {
                patientlocal = patientRoomDao.updatePatientRoom(Integer.parseInt(idPatient),status);

            } catch (SQLException ex) {
                System.out.println(ex);
                return new ResponsePatientRoomDto(SQL_ERROR_QUERY,false,4);
            }
            if (patientlocal == 0) {
                return new ResponsePatientRoomDto(SQL_TIME_OUT,false,3);
            }
            else{
                return new ResponsePatientRoomDto(SUCCESS,true,1);
            }
        }catch (Exception ex){
            return new ResponsePatientRoomDto("Se necesita el parametro idPatient",false,3);
        }

    }

}
