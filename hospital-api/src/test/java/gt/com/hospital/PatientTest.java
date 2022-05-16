package gt.com.hospital;

import gt.com.model.dao.*;
import gt.com.model.entity.AppointmentEntity;
import gt.com.model.entity.PatientEntity;
import gt.com.model.entity.RoomEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PatientTest {
    @Test
    public void validateResponseGetAppointmentById(){
        PatientEntity patientEntity = new PatientEntity();
        PatientEntity patientEntity1 = new PatientEntity();
        Date datePrueba = null;

        try {
            datePrueba = new SimpleDateFormat("yy-MM-dd").parse("2020-10-12");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patientEntity.setIdPatient(75);

        IPatientDao patientDao = new PatientDao();
        IRoomDao roomDao = new RoomDao();
        List<RoomEntity> roomEntity = new ArrayList<>();
        try {
            //patientEntity1 = patientDao.getPatientById(6);
            roomEntity = roomDao.getRoomsByPatient(80);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(patientEntity1);
    }
}
