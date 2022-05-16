package gt.com.model.service;

import gt.com.model.dao.AppointmentsDao;
import gt.com.model.dao.IAppointmentsDao;
import gt.com.model.dto.ResponseAppointmentsDto;
import gt.com.model.entity.AppointmentEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static gt.com.model.configurationApp.MessagesErrorApp.*;

public class AppointmentService implements IAppointmentService {


    @Override
    public ResponseAppointmentsDto getAppointments(HttpServletRequest request) {
        IAppointmentsDao appointmentsDao = new AppointmentsDao();
        List<AppointmentEntity>  appointmentEntities = null;
        AppointmentEntity appointmentEntity =null;


        if (request.getParameter("type") == null){
            return new ResponseAppointmentsDto("Senecesita el parametro TYPE",false,3);
        }


        if (request.getParameter("type").equals("appointments")){
            try {
                appointmentEntities = appointmentsDao.getAppointments();
            } catch (SQLException throwables) {
                return new ResponseAppointmentsDto(SQL_ERROR_QUERY,false,3);
            }

            if (appointmentEntities.isEmpty()) {
                return new ResponseAppointmentsDto(SQL_NOT_PATIETN,false,3);
            }else{
                return new ResponseAppointmentsDto(SUCCESS,appointmentEntities,true,1);
            }

        } else if (request.getParameter("type").equals("appointment")){
            if (request.getParameter("idAppointment") == null){
                return  new ResponseAppointmentsDto("Se necesita el parametro idPatient",false,3);
            }
            try {
                appointmentEntity = appointmentsDao.getAppointmentById(Integer.parseInt(request.getParameter("idAppointment")));
            } catch (SQLException throwables) {
                return new ResponseAppointmentsDto(SUCCESS,true,SQL_ERROR_QUERY,1);
            }

            if (appointmentEntity == null) {
                return new ResponseAppointmentsDto(SQL_NOT_PATIETN,false,3);
            }else{
                return new ResponseAppointmentsDto(SUCCESS,true, appointmentEntity,1);
            }
        }else if(request.getParameter("type").equals("appointmentByName")){
            if(request.getParameter("search") == null){
                return  new ResponseAppointmentsDto(PARAMETER_NOTFOUND+" search",false,3);
            }else{
                String[] dataResponse = responseFullNameSurName(request.getParameter("search"));
                if (dataResponse[0] == null){
                    dataResponse[0]="";
                }
                if (dataResponse[1] == null){
                    dataResponse[1] ="";
                }
                try{
                    appointmentEntities= appointmentsDao.getAppointmentsByNamePatient(dataResponse[0],dataResponse[1]);

                }catch (SQLException ex){
                    return new ResponseAppointmentsDto(SQL_ERROR_QUERY,false,3);
                }
                if(!appointmentEntities.isEmpty()){
                   return new ResponseAppointmentsDto(SUCCESS,appointmentEntities,true,1);
                }else{
                    return new ResponseAppointmentsDto(ARRAY_EMPTY+" appointment",false,3);
                }
            }
        }

        else{
            return new ResponseAppointmentsDto("Problema con la peticion",false,3);
        }

    }

    @Override
    public ResponseAppointmentsDto saveAppointment(HttpServletRequest request) {
        IAppointmentsDao appointmentsDao = new AppointmentsDao();
        boolean  patientlocal = false;


        Date dateAppointment = null;
        try {
            dateAppointment = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
        } catch (ParseException e) {
            return new ResponseAppointmentsDto("La fecha es invalida",false,3);
        }
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setIdPatient(Integer.parseInt(request.getParameter("idPatient")));
        appointmentEntity.setDateAppointment(dateAppointment);
        appointmentEntity.setTimeAppointment(Time.valueOf(request.getParameter("time")));
        appointmentEntity.setIdClinic(Integer.parseInt(request.getParameter("idClinic")));

        try {
            patientlocal = appointmentsDao.saveAppointment(appointmentEntity);
        } catch (SQLException ex) {
            return new ResponseAppointmentsDto(SQL_ERROR_QUERY,false,4);
        }
        if (patientlocal == true) {
            return new ResponseAppointmentsDto(SQL_TIME_OUT,false,3);
        }
        else{
            return new ResponseAppointmentsDto(SUCCESS,true,1);
        }
    }

    @Override
    public ResponseAppointmentsDto deleteAppointment(int idAppointment) {

        IAppointmentsDao appointmentDao = new AppointmentsDao();
        int patientDelete = 0;
        try {
            patientDelete = appointmentDao.deleteAppointment(idAppointment);
        } catch (SQLException ex) {
            return new ResponseAppointmentsDto(SQL_ERROR_QUERY,false,3);
        }
        if (patientDelete == 0) {
            return new ResponseAppointmentsDto(SQL_UPDATE_ERROR_APPOINTMENT,false,3);
        } else {
            return new ResponseAppointmentsDto(SUCCESS,true,1);
        }
    }

    @Override
    public ResponseAppointmentsDto updateAppointment(HttpServletRequest request) {

        IAppointmentsDao appointmentDao = new AppointmentsDao();
        AppointmentEntity appointment = new AppointmentEntity();

        Date dateParameter = null;
        Date timeParameter = null;
        if (!request.getParameter("date").isEmpty()) {

            try {
                dateParameter = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));

            } catch (ParseException e) {
                return new ResponseAppointmentsDto("La fecha es invalida",false,3);
            }
            appointment.setDateAppointment(dateParameter);
        }

        if (!request.getParameter("time").isEmpty()) {

            try {
                timeParameter =Time.valueOf(request.getParameter("time"));

            } catch (Exception e) {
                return new ResponseAppointmentsDto("la hora no es valida",false,3);
            }
            appointment.setTimeAppointment(timeParameter);
        }


        if (request.getParameter("idPatient").isEmpty()) {
            return new ResponseAppointmentsDto("Se necesita el parametro idPatient",false,3);
        } else {
            appointment.setIdPatient(Integer.parseInt(request.getParameter("idPatient")));
        }


        if (request.getParameter("idClinic").isEmpty()) {
            return new ResponseAppointmentsDto("Se necesita el parametro idClinic",false,3);
        } else {
            appointment.setIdClinic(Integer.parseInt(request.getParameter("idClinic")));
        }
        if (request.getParameter("idAppointment").isEmpty()) {
            return new ResponseAppointmentsDto("Se necesita el parametro idClinic",false,3);
        } else {
            appointment.setIdAppointment(Integer.parseInt(request.getParameter("idAppointment")));
        }


        int appointmentlocal;
        try {
            appointmentlocal = appointmentDao.updateAppointment(appointment);
        } catch (SQLException ex) {
            return new ResponseAppointmentsDto(SQL_ERROR_QUERY,false,3);
        }
        if (appointmentlocal == 0) {
            return new ResponseAppointmentsDto(SQL_UPDATE_ERROR_PATIENT,false,3);
        } else {
            return new ResponseAppointmentsDto(SUCCESS,true,1);
        }
    }

    @Override
    public ResponseAppointmentsDto getAppointmentById(int idAppointment) {
        IAppointmentsDao appointmentsDao = new AppointmentsDao();
        AppointmentEntity  appointment = null;


        try {
            appointment = appointmentsDao.getAppointmentById(idAppointment);
        } catch (SQLException throwables) {
            return new ResponseAppointmentsDto(SQL_ERROR_QUERY,false,3);
        }

        if (appointment == null){
            return new ResponseAppointmentsDto(SQL_NOT_PATIETN,false,3);
        }
        else{

            return new ResponseAppointmentsDto(SUCCESS,true,appointment,1);
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
