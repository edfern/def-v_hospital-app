package gt.com.model.dao;

import gt.com.model.entity.AppointmentEntity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import static gt.com.model.configurationApp.ConfigurationApp.*;


public class AppointmentsDao implements IAppointmentsDao {
    private ConexionSingleton conection = ConexionSingleton.getInstance();

    public AppointmentsDao() {
    }

    @Override
    public boolean saveAppointment(AppointmentEntity appointmentEntity) throws SQLException {
        ConexionSingleton conection = ConexionSingleton.getInstance();
        boolean rs;

        try{
            conection.abrirConexion();


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dataFormat2 = new SimpleDateFormat("hh:mm:ss");
            String dateParameter = dateFormat.format(appointmentEntity.getDateAppointment());
            String timeParameter = dataFormat2.format(appointmentEntity.getTimeAppointment());
            String query = String.format(INSERT_QUERY_APPOINTMENTS,appointmentEntity.getIdPatient()
            ,dateParameter,timeParameter,appointmentEntity.getIdClinic());
            System.out.println(query);
            rs = conection.conexionBD.createStatement().execute(query);

        }catch (SQLException ex){
            throw ex;
        } finally {
            conection.cerrarConexion();
        }
        return rs;

    }

    @Override
    public int updateAppointment(AppointmentEntity appointmentEntity) throws SQLException {

        ConexionSingleton conection = ConexionSingleton.getInstance();
        PreparedStatement parametro;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dataFormat2 = new SimpleDateFormat("hh:mm:ss");
        String dateParameter = dateFormat.format(appointmentEntity.getDateAppointment());
        String timeParameter = dataFormat2.format(appointmentEntity.getTimeAppointment());
        try {

            conection.abrirConexion();

            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(UPDATE_QUERY_APPOINTMENTS);
            parametro.setInt(1, appointmentEntity.getIdPatient());
            parametro.setString(2, dateParameter);
            parametro.setString(3, timeParameter);
            parametro.setInt(4, appointmentEntity.getIdClinic());
            parametro.setInt(5,appointmentEntity.getIdAppointment());
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
    public int deleteAppointment(int idAppointment) throws SQLException {

        ConexionSingleton conection = ConexionSingleton.getInstance();
        PreparedStatement parametro;
        try {

            conection.abrirConexion();

            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(DELETE_QUERY_APPOINTMENTS);
            parametro.setInt(1,idAppointment);
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
    public List<AppointmentEntity> getAppointments() throws SQLException {
        ConexionSingleton conection = ConexionSingleton.getInstance();

        List<AppointmentEntity> appointmentEntities = new ArrayList<>();
        try{
            conection.abrirConexion();
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(SELECT_QUERY_APPOINTMENTS);
            while (rs.next()){
                AppointmentEntity nuevo = new AppointmentEntity();
                nuevo.setIdAppointment(Integer.parseInt(rs.getString("idAppointment")));
                nuevo.setNamePatient(rs.getString("namePatient"));
                nuevo.setNameClinic(rs.getString("clinicName"));
                nuevo.setDateAppointment(rs.getDate("date"));
                nuevo.setTimeAppointment(rs.getTime("hour"));
                appointmentEntities.add(nuevo);
            }
        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }

        return appointmentEntities;
    }

    @Override
    public AppointmentEntity getAppointmentById(int idAppointmet) throws SQLException {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        try{
            conection.abrirConexion();

            String query = String.format("select idAppointment,idPatient,date,hour,idClinic from appointments where idAppointment = %d;",idAppointmet);
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(query);

            while (rs.next()){
                appointmentEntity.setIdAppointment(Integer.parseInt(rs.getString("idAppointment")));
                appointmentEntity.setIdPatient(Integer.parseInt(rs.getString("idPatient")));
                appointmentEntity.setDateAppointment(rs.getDate("date"));
                appointmentEntity.setIdClinic(Integer.parseInt(rs.getString("idClinic")));
                appointmentEntity.setTimeAppointment((rs.getTime("hour")));
            }

        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return appointmentEntity;
    }

    @Override
    public List<AppointmentEntity> getAppointmentsByPatient(long searchParam) throws SQLException {
        ConexionSingleton conection = ConexionSingleton.getInstance();
        List<AppointmentEntity> appointmentEntities = new ArrayList<>();


        try{
            conection.abrirConexion();

            PreparedStatement preparedStatement = conection.conexionBD.prepareStatement(SELECT_QUERY_APPOINTMENTS_BY_PATTIENT);
            preparedStatement.setLong(1,searchParam);
            preparedStatement.setLong(2,searchParam);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                AppointmentEntity nuevo = new AppointmentEntity();
                nuevo.setIdAppointment(Integer.parseInt(rs.getString("idAppointment")));
                nuevo.setIdPatient(Integer.parseInt(rs.getString("idPatient")));
                nuevo.setDateAppointment(rs.getDate(3));
                nuevo.setTimeAppointment(rs.getTime(4));
                nuevo.setIdClinic(Integer.parseInt(rs.getString("idClinic")));
                appointmentEntities.add(nuevo);
            }

        }catch (SQLException ex){
            throw ex;
        }
        finally {
            conection.cerrarConexion();
        }

        return appointmentEntities;
    }

    public List<AppointmentEntity> getAppointmentsByNamePatient(String fullName, String surName) throws SQLException {

        List<AppointmentEntity> appointmentEntities = new ArrayList<>();
        try{
            String query = "select ap.idAppointment,ap.idPatient,ap.idClinic,ap.date,ap.hour from appointments ap inner join patients as pa on ap.IdPatient = pa.idPatient where ((pa.fullName like '%"+fullName+"%' and pa.surName like '%"+surName+"%') || (pa.fullName like '%"+fullName+"%') || (pa.surName like '%"+fullName+"%') || (pa.fullName like '%"+fullName+" "+surName+"%')|| (pa.surName like '%"+surName+" "+fullName+"%'));";
            conection.abrirConexion();
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(query);
            while (rs.next()){
                AppointmentEntity appointmentEntity = new AppointmentEntity();
                appointmentEntity.setIdAppointment(Integer.parseInt(rs.getString("idAppointment")));
                appointmentEntity.setIdPatient(Integer.parseInt(rs.getString("idPatient")));
                appointmentEntity.setIdClinic(Integer.parseInt(rs.getString("idClinic")));
                appointmentEntity.setDateAppointment(rs.getDate(4));
                appointmentEntity.setTimeAppointment(rs.getTime(5));
                appointmentEntities.add(appointmentEntity);
            }
        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }

        return appointmentEntities;
    }
}