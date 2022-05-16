package gt.com.hospital;

import gt.com.model.dao.AppointmentsDao;
import gt.com.model.dao.IAppointmentsDao;
import gt.com.model.entity.AppointmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class RoomTest {

    @Test
    public void validateResponseGetAppointmentById(){
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        AppointmentEntity appointmentEntity1 = new AppointmentEntity();
        Date datePrueba = null;

        try {
            datePrueba = new SimpleDateFormat("yy-MM-dd").parse("2020-10-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        appointmentEntity.setIdAppointment(5);
        appointmentEntity.setIdPatient(75);
        appointmentEntity.setDateAppointment(datePrueba);
        appointmentEntity.setIdClinic(1);
        appointmentEntity.setTimeAppointment(Time.valueOf("13:00:00"));

        IAppointmentsDao appointmentsDao = new AppointmentsDao();
        try {
            appointmentEntity1 = appointmentsDao.getAppointmentById(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (appointmentEntity.getDateAppointment().equals(appointmentEntity1.getDateAppointment())){
            System.out.println("PRUEBA CORRECTA AMIGO");;
        }else {
            System.out.println("PRUEBA FALLIDA :(");
        }
        if (appointmentEntity.getIdClinic() == appointmentEntity1.getIdClinic()){
            System.out.println("PRUEBA CORRECTA AMIGO");;
        }else {
            System.out.println("PRUEBA FALLIDA :(");;
        }
        if (appointmentEntity.getIdPatient() == appointmentEntity1.getIdPatient()){
            System.out.println("PRUEBA CORRECTA AMIGO");;
        }else {
            System.out.println("PRUEBA FALLIDA :(");;
        }
        if (appointmentEntity.getTimeAppointment().equals(appointmentEntity1.getTimeAppointment())){
            System.out.println("PRUEBA CORRECTA AMIGO");;
        }else {
            System.out.println("PRUEBA FALLIDA :(");;
        }
        if (appointmentEntity.getIdAppointment() == appointmentEntity1.getIdAppointment()){
            System.out.println("PRUEBA CORRECTA AMIGO");;
        }else {
            System.out.println("PRUEBA FALLIDA :(");;
        }
    }
}
