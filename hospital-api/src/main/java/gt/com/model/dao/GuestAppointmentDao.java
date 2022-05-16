package gt.com.model.dao;

import gt.com.model.entity.AppointmentEntity;
import gt.com.model.entity.GuestAppointmentEntity;
import gt.com.model.entity.PatientEntity;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static gt.com.model.configurationApp.ConfigurationApp.*;

public class GuestAppointmentDao implements IGuestAppointmentDao{
    ConexionSingleton conection = ConexionSingleton.getInstance();

    @Override
    public List<GuestAppointmentEntity> getGuestAppointments() throws SQLException {

        List<GuestAppointmentEntity> guestAppointments = new ArrayList<>();

        try{
            ResultSet rs;
            conection.abrirConexion();

            rs = conection.conexionBD.createStatement().executeQuery(SELECT_ALL_GUEST_APPOINTMENTS);

            while (rs.next()){
                GuestAppointmentEntity nuevo = new GuestAppointmentEntity();

                nuevo.setIdGuestAppointment(rs.getInt("idGuestAppointment"));
                nuevo.setFullName(rs.getString("fullName"));
                nuevo.setSurName(rs.getString("surName"));
                nuevo.setAddress(rs.getString("address"));
                nuevo.setDate(rs.getDate("date"));
                nuevo.setHour(rs.getTime("hour"));
                guestAppointments.add(nuevo);
            }

        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }

        return guestAppointments;
    }


    @Override
    public List<GuestAppointmentEntity> searchGuestAppointmentByName(String fullName, String surName) throws SQLException {
        List<GuestAppointmentEntity> guestAppointmentEntities = new ArrayList<>();
        try{
            conection.abrirConexion();

            String query = "select ap.idGuestAppointment,ap.fullName, ap.surName,ap.address,ap.date,ap.hour from guestAppointments ap  where ((ap.fullName like '%"+fullName+"%' and ap.surName like '%"+surName+"%') || (ap.fullName like '%"+fullName+"%') || (ap.surName like '%"+fullName+"%') || (ap.fullName like '%"+fullName+" "+surName+"%')|| (ap.surName like '%"+surName+" "+fullName+"%'));";
            conection.abrirConexion();
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(query);


            while (rs.next()){
                GuestAppointmentEntity guestAppointmentEntity = new GuestAppointmentEntity();
                guestAppointmentEntity.setIdGuestAppointment(rs.getInt("idGuestAppointment"));
                guestAppointmentEntity.setFullName(rs.getString("fullName"));
                guestAppointmentEntity.setSurName(rs.getString("surName"));
                guestAppointmentEntity.setAddress(rs.getString("address"));
                guestAppointmentEntity.setDate(rs.getDate("date"));
                guestAppointmentEntity.setHour(rs.getTime("hour"));
                guestAppointmentEntities.add(guestAppointmentEntity);
            }
        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }

        return guestAppointmentEntities;
    }


    @Override
    public int deleteGuestAppointment(int idGuestAppointment) throws SQLException {
        PreparedStatement parametro;
        try {

            conection.abrirConexion();

            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(DELETE_GUEST_APPOINTMENT);
            parametro.setInt(1,idGuestAppointment);

            parametro.executeUpdate();

        } catch (SQLException ex )
        {
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return 1;
    }

    @Override
    public int updateGuestAppointent(GuestAppointmentEntity guestAppointmentEntity) throws SQLException {
        PreparedStatement parametro;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dataFormat2 = new SimpleDateFormat("hh:mm:ss");
        String dateParameter = dateFormat.format(guestAppointmentEntity.getDate());
        String timeParameter = dataFormat2.format(guestAppointmentEntity.getHour());
        try {

            conection.abrirConexion();

            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(UPDATE_GUEST_APPOINTMENT);
            parametro.setString(1,guestAppointmentEntity.getFullName());
            parametro.setString(2,guestAppointmentEntity.getSurName());
            parametro.setString(3,guestAppointmentEntity.getAddress());
            parametro.setString(4, dateParameter);
            parametro.setString(5, timeParameter);
            parametro.setInt(6,guestAppointmentEntity.getIdGuestAppointment());
            parametro.executeUpdate();

        } catch (SQLException ex )
        {
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return 1;
    }

    @Override
    public boolean saveGuestAppointment(GuestAppointmentEntity guestAppointmentEntity) throws SQLException {
        boolean rs;

        try{
            conection.abrirConexion();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dataFormat2 = new SimpleDateFormat("hh:mm:ss");
            String dateParameter = dateFormat.format(guestAppointmentEntity.getDate());
            String timeParameter = dataFormat2.format(guestAppointmentEntity.getHour());
            String query = String.format(INSERT_GUEST_APPOINTMENT,guestAppointmentEntity.getFullName(),guestAppointmentEntity.getSurName()
                    ,guestAppointmentEntity.getAddress(),dateParameter,timeParameter);
            rs = conection.conexionBD.createStatement().execute(query);

        }catch (SQLException ex){
            throw ex;
        } finally {
            conection.cerrarConexion();
        }
        return rs;
    }


}
