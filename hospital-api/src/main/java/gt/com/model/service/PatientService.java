package gt.com.model.service;


import gt.com.model.dao.*;
import gt.com.model.dto.ResponsePatientRoomDto;
import gt.com.model.entity.PatientEntity;
import gt.com.model.dto.ResponsePatientDto;
import gt.com.model.entity.RoomEntity;

import javax.servlet.http.HttpServletRequest;
import static gt.com.model.configurationApp.MessagesErrorApp.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientService implements IPatientService{

    @Override
    public ResponsePatientDto getPatients(HttpServletRequest resq) {

        IPatientDao patientDao = new PatientDao();
        List<PatientEntity>  patients = null;
        PatientEntity patient =null;


        if (resq.getParameter("type") == null){
            return new ResponsePatientDto("Senecesita el parametro TYPE",false,3);
        }


        if (resq.getParameter("type").equals("patients")){

                try {
                    patients = patientDao.getPatients();
                } catch (SQLException throwables) {
                    return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
                }

                if (patients.isEmpty()) {
                    return new ResponsePatientDto(SQL_NOT_PATIETN,false,3);
                }else{
                    return new ResponsePatientDto(SUCCESS,patients,true,1);
                }

        } else if (resq.getParameter("type").equals("patient")){
                if (resq.getParameter("idPatient") == null){
                    return  new ResponsePatientDto("Se necesita el parametro idPatient",false,3);
                }
                try {
                    patient = patientDao.getPatientById(Integer.parseInt(resq.getParameter("idPatient")));
                } catch (SQLException throwables) {
                    return new ResponsePatientDto(ERROR,false,SQL_ERROR_QUERY,3);
                }

                if (patient.getIdPatient() != Integer.parseInt(resq.getParameter("idPatient"))) {
                    return new ResponsePatientDto("no se a encontrado ningun paciente con ese Id",false,3);
                }else{
                    return new ResponsePatientDto(SUCCESS,true, patient,1);
                }
        }else if (resq.getParameter("type").equals("patientsName")){ // fullName and surName

                if (resq.getParameter("fullName") == null){
                    return  new ResponsePatientDto("Se necesita el parametro fullName",false,3);
                }

                String[] dataResponse = responseFullNameSurName(resq.getParameter("fullName"));
                if (dataResponse[0] == null){
                    dataResponse[0]="";
                }
                if (dataResponse[1] == null){
                    dataResponse[1] ="";
                }

                try {
                    patients = patientDao.getPatients(dataResponse[0],dataResponse[1],Boolean.parseBoolean(resq.getParameter("status")));
                } catch (SQLException | ParseException throwables) {
                    return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
                }

                if (patients.isEmpty()) {
                    return new ResponsePatientDto("no se a encontrado ningun paciente con ese nombre",false,3);
                }else{
                    return new ResponsePatientDto(SUCCESS,true, patients,1);
                }
        }else if(resq.getParameter("type").equals("updateWait")){
                int rs;
                if (resq.getParameter("idPatient") == null){
                    return  new ResponsePatientDto("Se necesita el parametro idPatient",false,3);
                }else{
                    try {
                        rs = patientDao.updateStatusWait(Integer.parseInt(resq.getParameter("idPatient")),Boolean.parseBoolean(resq.getParameter("status")));
                    }catch (SQLException ex){
                        return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
                    }
                    if(rs == 1){
                        return new ResponsePatientDto(SUCCESS,true,1);
                    }else{
                        return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
                    }
                }
        }else if(resq.getParameter("type").equals("changeStatus")){
                try{
                    return updateStatus(resq);
                }catch (Exception ex){
                    return new ResponsePatientDto(SQL_ERROR_QUERY,false,2);
                }
        }else if(resq.getParameter("type").equals("patientsWait")){
                try {
                    patients = patientDao.getPatientsWait();
                } catch (SQLException throwables) {
                    return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
                }

                if (patients.isEmpty()) {
                    return new ResponsePatientDto(SQL_NOT_PATIETNWAIT,false,2);
                }else{
                    return new ResponsePatientDto(SUCCESS,patients,true,1);
                }
        }
        else{
                return new ResponsePatientDto("Problema con la peticionr",false,3);
        }

    }

    @Override
    public ResponsePatientDto savePatient(HttpServletRequest request) {

        IPatientDao patientDao = new PatientDao();
        boolean  patientlocal = false;
        Date birthdayParameter = null;
        int idPatient;
        PatientEntity patient = new PatientEntity();

        if (!request.getParameter("birthday").isEmpty()) {

            try {
                birthdayParameter = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));

            } catch (ParseException e) {
                return new ResponsePatientDto("La fecha es invalida",false,3);
            }
            patient.setBirthday(birthdayParameter);
        }else{
            return new ResponsePatientDto("se necesita la fecha ",false,2);
        }


        if (!request.getParameter("dpi").isEmpty()) {
            if (validateStringNumbers(request.getParameter("dpi"))){
                patient.setDpi(request.getParameter("dpi"));
            }else{
                return new ResponsePatientDto("El campo 'dpi' no puede tener campos alfabeticos ",false,2);
            }
        }
        if (!request.getParameter("nit").isEmpty()) {
            patient.setNit(request.getParameter("nit"));
        }
        if (!request.getParameter("gender").isEmpty()) {
            char genderParameter = Character.toLowerCase(request.getParameter("gender").charAt(0));
            if ((genderParameter == 'f') || (genderParameter == 'm')) {
                patient.setGender(genderParameter);
            }
        }
        if (!request.getParameter("fullName").isEmpty()) {
            patient.setFullName(request.getParameter("fullName"));
        }
        if (!request.getParameter("surName").isEmpty()) {
            patient.setSurName(request.getParameter("surName"));
        }
        if (!request.getParameter("address").isEmpty()) {
            patient.setAddress(request.getParameter("address"));
        }
        if (!request.getParameter("phone").isEmpty()) {
            if (validateStringNumbers(request.getParameter("phone"))){
                patient.setPhone(Integer.parseInt(request.getParameter("phone")));
            }else{
                return new ResponsePatientDto("El campo 'phone' no puede tener campos alfabeticos ",false,2);
            }
        }
        if (!request.getParameter("emergencyContact").isEmpty()) {
            if (validateStringNumbers(request.getParameter("emergencyContact"))){
                patient.setEmergencyContact(Integer.parseInt(request.getParameter("emergencyContact")));
            }else{
                return new ResponsePatientDto("El campo 'emergencyContact' no puede tener campos alfabeticos ",false,2);
            }
        }

        try {
            patientlocal = patientDao.savePatient(patient);
            idPatient = patientDao.getLastIdPatient();
        } catch (SQLException ex) {
            System.out.println(ex);
            return new ResponsePatientDto(SQL_ERROR_QUERY,false,4);
        }


        if (patientlocal == true && idPatient >=0) {
            return new ResponsePatientDto(SQL_TIME_OUT,false,3);
        }
        else{
            return new ResponsePatientDto(SUCCESS,true,idPatient,1);
        }

    }

    @Override
    public ResponsePatientDto deletePatient(int idPatient) {
        IPatientDao patientDao = new PatientDao();
        int patientDelete = 0;
        try {
            patientDelete = patientDao.deletePatient(idPatient);
        } catch (SQLException ex) {
            return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
        }
        if (patientDelete == 0) {
            return new ResponsePatientDto(SQL_UPDATE_ERROR_PATIENT,false,3);
        } else {
            return new ResponsePatientDto(SUCCESS,true,1);
        }
    }

    @Override
    public ResponsePatientDto updatePatient(HttpServletRequest request) {

        IPatientDao patientDao = new PatientDao();
        PatientEntity patient = new PatientEntity();

        Date birthdayParameter = null;
        if (!request.getParameter("birthday").isEmpty()) {

            try {
                birthdayParameter = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));

            } catch (ParseException e) {
                return new ResponsePatientDto("La fecha es invalida",false,3);
            }
            patient.setBirthday(birthdayParameter);
        }

        if (request.getParameter("idPatient").isEmpty()) {
            return new ResponsePatientDto("Se necesita el parametro idPatient",false,3);
        } else {
            patient.setIdPatient(Integer.parseInt(request.getParameter("idPatient")));
        }

        if (!request.getParameter("dpi").isEmpty()) {
            if (validateStringNumbers(request.getParameter("dpi"))){
                patient.setDpi(request.getParameter("dpi"));
            }else{
                return new ResponsePatientDto("El campo 'dpi' no puede tener campos alfabeticos ",false,2);
            }
        }
        if (!request.getParameter("nit").isEmpty()) {
            patient.setNit(request.getParameter("nit"));
        }
        if (!request.getParameter("gender").isEmpty()) {
            char genderParameter = Character.toLowerCase(request.getParameter("gender").charAt(0));
            if ((genderParameter == 'f') || (genderParameter == 'm')) {
                patient.setGender(genderParameter);
            }
        }
        if (!request.getParameter("fullName").isEmpty()) {
            patient.setFullName(request.getParameter("fullName"));
        }
        if (!request.getParameter("surName").isEmpty()) {
            patient.setSurName(request.getParameter("surName"));
        }
        if (!request.getParameter("address").isEmpty()) {
            patient.setAddress(request.getParameter("address"));
        }
        if (!request.getParameter("phone").isEmpty()) {
            if (validateStringNumbers(request.getParameter("phone"))){
                patient.setPhone(Integer.parseInt(request.getParameter("phone")));
            }else{
                return new ResponsePatientDto("El campo 'phone' no puede tener campos alfabeticos ",false,2);
            }
        }
        if (!request.getParameter("emergencyContact").isEmpty()) {
            if (validateStringNumbers(request.getParameter("emergencyContact"))){
                patient.setEmergencyContact(Integer.parseInt(request.getParameter("emergencyContact")));
            }else{
                return new ResponsePatientDto("El campo 'emergencyContact' no puede tener campos alfabeticos ",false,2);
            }
        }
            int patientlocal;
            try {
                patientlocal = patientDao.updatePatient(patient);
            } catch (SQLException ex) {
                return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
            }
            if (patientlocal == 0) {
                return new ResponsePatientDto(SQL_UPDATE_ERROR_PATIENT,false,3);
            } else {
                return new ResponsePatientDto(SUCCESS,true,1);
            }

        }

    @Override
    public ResponsePatientDto getPatientById(int id) {

        IPatientDao patientDao = new PatientDao();
        PatientEntity  patient = null;


        try {
            patient = patientDao.getPatientById(id);
        } catch (SQLException throwables) {
            return new ResponsePatientDto(SQL_ERROR_QUERY,false,3);
        }

        if (patient == null){
            return new ResponsePatientDto(SQL_NOT_PATIETN,false,3);
        }else{
            if(patient.getIdPatient() == id){
                return new ResponsePatientDto(SUCCESS,true,patient,1);
            }else{
                return new ResponsePatientDto("Usuario no encontrado",false,3);
            }
        }

    }

    @Override
    public ResponsePatientDto updateStatusPatient(HttpServletRequest request) throws Exception {
        IPatientDao patientDao = new PatientDao();
        int patientlocal;
        String idPatient;

        try {
            idPatient = request.getParameter("idPatient");
            try {
                patientlocal = patientDao.updateStatusPatient(Integer.parseInt(idPatient),false);
            } catch (SQLException ex) {
                System.out.println(ex);
                return new ResponsePatientDto(SQL_ERROR_QUERY,false,4);
            }
            if (patientlocal == 0) {
                return new ResponsePatientDto(SQL_TIME_OUT,false,3);
            }
            else{
                return new ResponsePatientDto(SUCCESS,true,1);
            }
        }catch (Exception ex){
            return new ResponsePatientDto("Se necesita el parametro idPatient",false,3);
        }
    }

    @Override
    public ResponsePatientDto updateStatus(HttpServletRequest request) throws Exception {
        List<RoomEntity> rooms = new ArrayList<>();
        IPatientDao patient = new PatientDao();

        if (request.getParameter("idPatient") == null){
            return  new ResponsePatientDto("Se necesita el parametro idPatient",false,3);
        }else{
            try {
                IPatientRoomDao dao = new PatientRoomDao();
                rooms = dao.getRoomsPatient(Integer.parseInt(request.getParameter("idPatient")),true);
            }catch (SQLException ex){
                return new ResponsePatientDto(ex.getMessage(),false,3);
            }
            if(!rooms.isEmpty()){
                IPatientRoomDao dao = new PatientRoomDao();
                int res;
                res = dao.updatePatientRoom(Integer.parseInt(request.getParameter("idPatient")),false);
                if(res == 1){
                    IRoomDao roomUpdate = new RoomDao();
                    int status;
                    status = roomUpdate.updateRoomStatus(rooms.get(0).getIdRoom(),true);
                    if(status == 1){

                        int status2;
                        status2 = patient.updateAllStatusPatient(Integer.parseInt(request.getParameter("idPatient")),false);
                        if(status2 == 1){
                            return new ResponsePatientDto(SUCCESS,true,1);
                        }else{
                            return new ResponsePatientDto(SQL_ERROR_QUERY,false,2);
                        }
                    }else{
                        return new ResponsePatientDto(SQL_ERROR_QUERY,false,2);
                    }
                }else{
                    return new ResponsePatientDto(SQL_ERROR_QUERY,false,2);
                }

            }else{
                int status2;
                status2 = patient.updateAllStatusPatient(Integer.parseInt(request.getParameter("idPatient")),false);
                if(status2 == 1){
                    return new ResponsePatientDto(SUCCESS,true,1);
                }else{
                    return new ResponsePatientDto(SQL_ERROR_QUERY,false,2);
                }
            }
        }
    }

    public boolean validateStringNumbers(String entrada){

        for (char c : entrada.toCharArray()) {
            if (Character.isAlphabetic(c)){
                return false;
            }
        }
        return true;
    }

    public String[] responseFullNameSurName(String data){
        String[] token = data.split(" ");
        String[] dataResponse =new String[2];
        for (int i =0; i < token.length; i++){
            if (i < 2) {
                dataResponse[i] = token[i];
            }
        }
        return dataResponse;
    }
}
