package gt.com.model.service;

import gt.com.model.dao.*;
import gt.com.model.dto.ResponseGenericDto;
import gt.com.model.entity.MedicalHistoryEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import static gt.com.model.configurationApp.MessagesErrorApp.*;

public class MedicalHistoryService implements IMedicalHistoryService{

    @Override
    public ResponseGenericDto getMedicalHistory(HttpServletRequest request) {

        //------------------------------------------------
        IPatientDao patientDao = new PatientDao();
        IRoomDao    roomDao = new RoomDao();
        IPrescriptionsDao prescriptionsDao = new PrescriptionDao();
        IAppointmentsDao appointmentsDao = new AppointmentsDao();
        IPatientRoomDao patientRoomDao = new PatientRoomDao();
        //-------------------------------------------------
        List<Object> respuestas = null;
        long searchParam;
        MedicalHistoryEntity medicalHistoryEntity = new MedicalHistoryEntity();
        //----------------------validacion del id---------------
        try{
            if (request.getParameter("searchParam").isEmpty()){
                return new ResponseGenericDto("El parametro esta vacio",2,false);
            }else {
                searchParam = Long.parseLong((request.getParameter("searchParam")));
            }
        }catch (NullPointerException ex){
            return new ResponseGenericDto("Se necesita el parametro de busqueda \n puede ser DPI o ID del paciente",2,false);
        }

        //------------------------- creacion  -----------------------
        try {

            medicalHistoryEntity.setPatient(patientDao.getPatientById(searchParam));

                if (medicalHistoryEntity.getPatient().getFullName() == null ){
                    return new ResponseGenericDto("No se ha econtrado a ningun paciente con ese dato",2,false);
                }

            medicalHistoryEntity.setRooms(roomDao.getRoomsByPatient(searchParam));
            medicalHistoryEntity.setPrescriptions(prescriptionsDao.searchPrescriptionFiltred(searchParam));
            medicalHistoryEntity.setAppointments(( appointmentsDao.getAppointmentsByPatient(searchParam)));

        } catch (SQLException ex) {
            return new ResponseGenericDto("Se ha producido un error al moemnto de ejecutar la consulta",3,false);
        }


        if (medicalHistoryEntity == null){
            return new ResponseGenericDto("No existen datos para esta consulta",2,false);
        }else {
            return new ResponseGenericDto(SUCCESS,1,true,medicalHistoryEntity);
        }
    }
}
