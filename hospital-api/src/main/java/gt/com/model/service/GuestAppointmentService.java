package gt.com.model.service;

import gt.com.model.dao.AppointmentsDao;
import gt.com.model.dao.GuestAppointmentDao;
import gt.com.model.dao.IAppointmentsDao;
import gt.com.model.dao.IGuestAppointmentDao;
import gt.com.model.dto.ResponseAppointmentsDto;
import gt.com.model.dto.ResponseGenericDto;
import gt.com.model.entity.AppointmentEntity;
import gt.com.model.entity.GuestAppointmentEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.GenericArrayType;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static gt.com.model.configurationApp.MessagesErrorApp.*;
import static gt.com.model.configurationApp.MessagesErrorApp.ARRAY_EMPTY;

public class GuestAppointmentService implements IGuestAppointmentService {
    @Override
    public ResponseGenericDto getGuestAppointments() {
        IGuestAppointmentDao guestAppointmentDao = new GuestAppointmentDao();
        List<GuestAppointmentEntity> guestAppointments;

            try{
                guestAppointments = guestAppointmentDao.getGuestAppointments();
            }catch (SQLException ex) {
                return new ResponseGenericDto(SQL_ERROR_QUERY,2,false);
            }

        return new ResponseGenericDto(SUCCESS,1,true,guestAppointments);
    }

    @Override
    public ResponseGenericDto searchGuestAppointmentByName(HttpServletRequest request) {

        IGuestAppointmentDao guestAppointmentDao = new GuestAppointmentDao();
        List<GuestAppointmentEntity> guestAppointmentEntities = new ArrayList<>();

        try {

            if (request.getParameter("search").isEmpty()) {
                return new ResponseGenericDto(PARAMETER_NOTFOUND + " search", 2, false);
            } else {
                String[] dataResponse = responseFullNameSurName(request.getParameter("search"));

                    try {
                        guestAppointmentEntities = guestAppointmentDao.searchGuestAppointmentByName(dataResponse[0], dataResponse[1]);

                    } catch (SQLException ex) {
                        return new ResponseGenericDto(SQL_ERROR_QUERY, 2, false);
                    }

                if (!guestAppointmentEntities.isEmpty()) {
                    return new ResponseGenericDto(SUCCESS, 2, true,guestAppointmentEntities);
                } else {
                    return new ResponseGenericDto(ARRAY_EMPTY + " appointment", 2, false);
                }
            }
        }catch (NullPointerException ex){
            return new ResponseGenericDto("Se necesita el parametro de busqueda",2,false);
        }

    }

    @Override
    public ResponseGenericDto chooseOneGetOperation(HttpServletRequest request)     {
        IGuestAppointmentDao guestAppointmentDao = new GuestAppointmentDao();

        try {
            if (request.getParameter("type").isEmpty()){
                return new ResponseGenericDto("Senecesita el parametro TYPE",2,false);
            }
        }catch (NullPointerException ex){
            return new ResponseGenericDto("Se necesita el parametro type",2,false);
        }

        if (request.getParameter("type").equals("guestAppointments")){
            return getGuestAppointments();
        }else if (request.getParameter("type").equals("searchGuestAppointment")){
            return searchGuestAppointmentByName(request);
        }else {
            return new ResponseGenericDto("El tipo de petecion no es valida", 2,false);
        }

    }

    @Override
    public ResponseGenericDto deleteGuestAppointment(HttpServletRequest request) {
        IGuestAppointmentDao guestAppointmentDao = new GuestAppointmentDao();
        int patientDelete = 0;
        int idGuestAppointent;

        try {
            if (request.getParameter("idGuestAppointment").isEmpty()) {
                return new ResponseGenericDto("El parametro esta vacio", 2, false);
            } else {
                idGuestAppointent = Integer.parseInt(request.getParameter("idGuestAppointment"));
            }
        } catch (NullPointerException ex) {
            return new ResponseGenericDto("Se necesita el parametro id", 2, false);
        }

        try {
            patientDelete = guestAppointmentDao.deleteGuestAppointment(idGuestAppointent);
        } catch (SQLException ex) {
            return new ResponseGenericDto(SQL_ERROR_QUERY, 2, false);
        }
        if (patientDelete == 0) {
            return new ResponseGenericDto(SQL_UPDATE_ERROR_APPOINTMENT, 2, false);
        } else {
            return new ResponseGenericDto(SUCCESS, 1, true);

        }
    }

    @Override
    public ResponseGenericDto updateGuestAppointment(HttpServletRequest request) {
        IGuestAppointmentDao guestAppointmentDao = new GuestAppointmentDao();
        GuestAppointmentEntity guestAppointmentEntity = new GuestAppointmentEntity();

        Date dateParameter = null;
        Date timeParameter = null;
        try{
            if (!request.getParameter("date").isEmpty()) {
                try {
                    dateParameter = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
                } catch (ParseException e) {
                    return new ResponseGenericDto("La fecha es invalida",2,false);
                }
                guestAppointmentEntity.setDate(dateParameter);
            }

            if (!request.getParameter("hour").isEmpty()) {
                try {
                    timeParameter = Time.valueOf(request.getParameter("hour"));
                } catch (Exception e) {
                    return new ResponseGenericDto("la hora no es valida",2,false);
                }
                guestAppointmentEntity.setHour(timeParameter);
            }

            if (request.getParameter("address").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro idClinic",2,false);
            } else {
                guestAppointmentEntity.setAddress((request.getParameter("address")));
            }
            if (request.getParameter("fullName").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro fullName",2,false);
            } else {
                guestAppointmentEntity.setFullName((request.getParameter("fullName")));
            }
            if (request.getParameter("surName").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro surName",2,false);
            } else {
                guestAppointmentEntity.setSurName((request.getParameter("surName")));
            }
            if (request.getParameter("idGuestAppointment").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro idGuestAppointment",2,false);
            } else {
                guestAppointmentEntity.setIdGuestAppointment(Integer.parseInt(request.getParameter("idGuestAppointment")));
            }

        }catch (NullPointerException ex){
            return new ResponseGenericDto("No esta enviando los parametros suficientes para la operacion",2,false);
        }


        int appointmentlocal;
        try {
            appointmentlocal = guestAppointmentDao.updateGuestAppointent(guestAppointmentEntity);
        } catch (SQLException ex) {
            return new ResponseGenericDto(SQL_ERROR_QUERY,2,true);
        }
        if (appointmentlocal > 0) {
            return new ResponseGenericDto(SUCCESS,1,true);

        } else {
            return new ResponseGenericDto(SQL_UPDATE_ERROR_PATIENT,2,true);
        }
    }

    @Override
    public ResponseGenericDto saveGuestAppointment(HttpServletRequest request) {
        IGuestAppointmentDao guestAppointmentDao = new GuestAppointmentDao();
        GuestAppointmentEntity guestAppointmentEntity = new GuestAppointmentEntity();

        Date dateParameter = null;
        Date timeParameter = null;
        try{
            if (!request.getParameter("date").isEmpty()) {
                try {
                    dateParameter = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
                } catch (ParseException e) {
                    return new ResponseGenericDto("La fecha es invalida",2,false);
                }
                guestAppointmentEntity.setDate(dateParameter);
            }

            if (!request.getParameter("hour").isEmpty()) {
                try {
                    timeParameter = Time.valueOf(request.getParameter("hour"));
                } catch (Exception e) {
                    return new ResponseGenericDto("la hora no es valida",2,false);
                }
                guestAppointmentEntity.setHour(timeParameter);
            }

            if (request.getParameter("address").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro idClinic",2,false);
            } else {
                guestAppointmentEntity.setAddress((request.getParameter("address")));
            }
            if (request.getParameter("fullName").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro fullName",2,false);
            } else {
                guestAppointmentEntity.setFullName((request.getParameter("fullName")));
            }
            if (request.getParameter("surName").isEmpty()) {
                return new ResponseGenericDto("Se necesita el parametro surName",2,false);
            } else {
                guestAppointmentEntity.setSurName((request.getParameter("surName")));
            }


        }catch (NullPointerException ex){
            return new ResponseGenericDto("No esta enviando los parametros suficientes para la operacion",2,false);
        }


        boolean appointmentlocal;
        try {
            appointmentlocal = guestAppointmentDao.saveGuestAppointment(guestAppointmentEntity);
        } catch (SQLException ex) {
            return new ResponseGenericDto(SQL_ERROR_QUERY,2,true);
        }
        if (appointmentlocal == true) {
            return new ResponseGenericDto(SUCCESS,1,true);
        } else {
            return new ResponseGenericDto(SUCCESS,1,true);
        }
    }

    //metodos propios de la clase
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
        if (dataResponse[0] == null) {
            dataResponse[0] = "";
        }
        if (dataResponse[1] == null) {
            dataResponse[1] = "";
        }

        return dataResponse;
    }

}
