package gt.com.model.service;


import gt.com.model.dao.IPrescriptionsDao;
import gt.com.model.dao.PrescriptionDao;
import gt.com.model.dto.ResponseGenericDto;
import gt.com.model.entity.PrescriptionEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static gt.com.model.configurationApp.MessagesErrorApp.*;

public class PrescriptionService implements IPrescriptionService{

    @Override
    public ResponseGenericDto getPrescriptions(HttpServletRequest request) {

        IPrescriptionsDao prescriptionDao = new PrescriptionDao();
        List<PrescriptionEntity> prescriptionEntities = null;
        int searchParam;
        String type;

            try {
                if (request.getParameter("type").isEmpty()) {
                    return new ResponseGenericDto("El campo type esta vacio ", 2, false);
                } else {
                    type = request.getParameter("type");
                }
            }catch (NullPointerException ex){
                return  new ResponseGenericDto("Se necesita el campo Type",2,false);
            }


            try {
                if (type.equals("searchParameter")){
                    return (ResponseGenericDto) seachrPrescriptionFiltred(request);
                }else  if (type.equals("prescriptions")){
                    prescriptionEntities = prescriptionDao.getPrescriptions();
                }else {
                    return new ResponseGenericDto("El tipo no es valido",2,false);
                }
            } catch (SQLException ex) {
                return new ResponseGenericDto(SQL_ERROR_QUERY,3,false);
            }

            if (prescriptionEntities.isEmpty()) {
                return new ResponseGenericDto("no se a encontrado ninguna prescripcion",3,false);
            }else{
                return new ResponseGenericDto(SUCCESS,1,true,prescriptionEntities);
            }

    }

    @Override
    public ResponseGenericDto savePrescription(HttpServletRequest request) {
        IPrescriptionsDao prescriptionsDao = new PrescriptionDao();
        PrescriptionEntity prescription = new PrescriptionEntity();
        int respuesta =0;

        try{

            if (request.getParameter("prescription").isEmpty()){
                return new ResponseGenericDto("Se necesita el parametro 'prescription'",3,false);
            }else{
                prescription.setPrescription(request.getParameter("prescription"));
            }

            if (request.getParameter("idPatient").isEmpty()){
                return new ResponseGenericDto("Se necesita el id del paciente'",3,false);
            }else{
                prescription.setIdPatient(Integer.parseInt(request.getParameter("idPatient")));
            }
            if (request.getParameter("idDoctor").isEmpty()){
                return new ResponseGenericDto("Se necesita el id del doctor'",3,false);
            }else{
                prescription.setIdDoctor(Integer.parseInt(request.getParameter("idDoctor")));
            }

            LocalDate localDate = LocalDate.now();
            Date parametro = java.sql.Date.valueOf(localDate);
            prescription.setDatePrescription(parametro);

        }catch (NullPointerException nullPointer){
            return new ResponseGenericDto("Falta informacion para ejecutar la funcion",2,false);
        }

        try {
            respuesta = prescriptionsDao.savePrescription(prescription);
        } catch (SQLException ex) {
            new ResponseGenericDto("Se produjo un eror al ejeutar la consulta",3,false);
        }
        if (respuesta > 0){
            return new ResponseGenericDto("Prescription save succes", 1, true);
        }
         return new ResponseGenericDto("Se produjo un eror al ejeutar la consulta",3,false);

    }

    @Override
    public ResponseGenericDto seachrPrescriptionFiltred(HttpServletRequest request) {
        IPrescriptionsDao prescriptionDao = new PrescriptionDao();
        List<PrescriptionEntity> prescriptionEntities = null;
        long searchParameter;

        try{
           if (request.getParameter("searchParameter").isEmpty()){
               return new ResponseGenericDto("El Parametro de busqueda esta vacio " ,2,false);
           }{
               searchParameter = Long.parseLong(request.getParameter("searchParameter"));
            }
        }catch (NullPointerException ex){
            return new ResponseGenericDto("Se necesita el parametro de busqueda",2,false);
        }catch (NumberFormatException ex){
            return new ResponseGenericDto("El parametro de busqueda es muy grande",2,false);
        }

        try{
            prescriptionEntities = prescriptionDao.searchPrescriptionFiltred(searchParameter);
        } catch (SQLException ex) {
            return new ResponseGenericDto(SQL_ERROR_QUERY,3,false);
        }

        if (prescriptionEntities.isEmpty()) {
            return new ResponseGenericDto("no se a encontrado ninguna prescripcion",2,false);
        }else{
            return new ResponseGenericDto(SUCCESS,1,true,prescriptionEntities);
        }


    }


}
