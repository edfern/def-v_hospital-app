package gt.com.model.dao;

import gt.com.model.entity.GuestAppointmentEntity;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public interface IGuestAppointmentDao {

    public List<GuestAppointmentEntity> getGuestAppointments() throws SQLException;
    public List<GuestAppointmentEntity> searchGuestAppointmentByName(String fullName, String surName) throws SQLException;
    public int deleteGuestAppointment(int idGuestAppointment)throws SQLException;
    public int updateGuestAppointent(GuestAppointmentEntity guestAppointmentEntity)throws SQLException;
    public boolean saveGuestAppointment(GuestAppointmentEntity guestAppointmentEntity)throws SQLException;
}
