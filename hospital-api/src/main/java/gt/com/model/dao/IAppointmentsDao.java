package gt.com.model.dao;

import gt.com.model.entity.AppointmentEntity;

import java.sql.SQLException;
import java.util.*;


public interface IAppointmentsDao {

    public boolean saveAppointment(AppointmentEntity appointmentEntity) throws SQLException;
    public int updateAppointment(AppointmentEntity appointmentEntity) throws SQLException;
    public int deleteAppointment(int idAppointment) throws SQLException;
    public List<AppointmentEntity> getAppointments() throws SQLException;
    public AppointmentEntity getAppointmentById(int idAppointmet) throws SQLException;
    public List<AppointmentEntity> getAppointmentsByPatient(long searchParam) throws SQLException;
    public List<AppointmentEntity> getAppointmentsByNamePatient(String fullName, String surName) throws SQLException;

}