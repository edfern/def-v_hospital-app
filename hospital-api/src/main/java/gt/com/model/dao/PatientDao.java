package gt.com.model.dao;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import gt.com.model.entity.PatientEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static gt.com.model.configurationApp.ConfigurationApp.*;


public class PatientDao implements IPatientDao {
    private ConexionSingleton conection = ConexionSingleton.getInstance();
    private ResultSet rs;
    private PreparedStatement parametro;
    public PatientDao() {
    }

    public boolean savePatient(PatientEntity patient) throws SQLException {

            boolean rs;

        PatientEntity patients = new PatientEntity();
        IRoomDao room = new RoomDao();

        try{
            conection.abrirConexion();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateparameter = dateFormat.format(patient.getBirthday());
            String query = String.format("insert into patients(dpi,nit,birthday,gender,fullName,surName,address,phone,emergencyContact) values (%s,'%s','%s','%c','%s','%s','%s',%d,%d);",
                        patient.getDpi(),patient.getNit(),dateparameter,patient.getGender(),patient.getFullName(),patient.getSurName(),patient.getAddress(),patient.getPhone()
                        ,patient.getEmergencyContact());
            rs = conection.conexionBD.createStatement().execute(query);
        }catch (SQLException ex){
            throw ex;
        }finally {
        conection.cerrarConexion();
        }
        return rs;

    }

    public List<PatientEntity> getPatients() throws SQLException {


        List<PatientEntity>patients = new ArrayList<>();
        try{
            conection.abrirConexion();
            String query = "select idPatient,dpi,nit,birthday,gender,fullName,surName,address,phone,emergencyContact,status from patients limit 10;";

            rs = conection.conexionBD.createStatement().executeQuery(query);

            while (rs.next()){
                PatientEntity nuevo = new PatientEntity();

                nuevo.setIdPatient(Integer.parseInt(rs.getString("idPatient")));
                nuevo.setDpi(rs.getString("dpi"));
                nuevo.setNit(rs.getString("nit"));
                nuevo.setBirthday(rs.getDate("birthday"));
                nuevo.setGender(rs.getString("gender").charAt(0));
                nuevo.setFullName(rs.getString("fullName"));
                nuevo.setSurName(rs.getString("surName"));
                nuevo.setAddress(rs.getString("address"));
                nuevo.setPhone(Integer.parseInt(rs.getString("phone")));
                nuevo.setEmergencyContact(Integer.parseInt(rs.getString("emergencyContact")));
                nuevo.setStatePatient(Boolean.parseBoolean(rs.getString("status")));

                patients.add(nuevo);
            }

        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }

        return patients;
    }

    public int updatePatient(PatientEntity patient) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateparameter = dateFormat.format(patient.getBirthday());
        try {

            conection.abrirConexion();

            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(QUERY_PATIENTS_UPDATE);
            parametro.setString(1, patient.getDpi());
            parametro.setString(2, patient.getNit());
            parametro.setString(3, dateparameter);
            parametro.setString(4, String.valueOf(patient.getGender()));
            parametro.setString(5, patient.getFullName());
            parametro.setString(6, patient.getSurName());
            parametro.setString(7, patient.getAddress());
            parametro.setInt(8, patient.getPhone());
            parametro.setInt(9, patient.getEmergencyContact());
            parametro.setInt(10,patient.getIdPatient());

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
    public PatientEntity getPatientById(long id) throws SQLException {

        PatientEntity patient = new PatientEntity();
        try{
            conection.abrirConexion();

            String query = String.format("select idPatient,dpi,nit,birthday,gender,fullName,surName,address,phone,emergencyContact,status from patients where idPatient = %d || dpi = %d;",id,id);
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(query);
            while (rs.next()){
                patient.setIdPatient(Integer.parseInt(rs.getString("idPatient")));
                patient.setDpi(rs.getString("dpi"));
                patient.setNit(rs.getString("nit"));
                patient.setBirthday(rs.getDate("birthday"));
                patient.setGender(rs.getString("gender").charAt(0));
                patient.setFullName(rs.getString("fullName"));
                patient.setSurName(rs.getString("surName"));
                patient.setAddress(rs.getString("address"));
                patient.setPhone(Integer.parseInt(rs.getString("phone")));
                patient.setEmergencyContact(Integer.parseInt(rs.getString("emergencyContact")));
                patient.setStatePatient(Boolean.parseBoolean(rs.getString("status")));
            }

        }catch (SQLException ex){
            throw ex;

        }catch (NullPointerException ex){
            throw ex;
        }
        finally {
            conection.cerrarConexion();
        }

        return patient;
    }

    @Override
    public int deletePatient(int id) throws SQLException {
        try {
            conection.abrirConexion();

            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(QUERY_PATIENTS_DELETE);
            parametro.setInt(1,id);
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
    public List<PatientEntity> getPatients(String fullName,String surName,boolean status) throws SQLException, ParseException {
        List<PatientEntity>patients = new ArrayList<>();
        try{
            conection.abrirConexion();

            String query2 = "select idPatient,dpi,nit,birthday,gender,fullName,surName,address,phone,emergencyContact,status,statusWait from patients where (status = '"+Boolean.toString(status)+"' and ((fullName like '%"+fullName+"%' and surName like '%"+surName+"%') || (fullName like'%"+surName+"%' and surName like '%"+fullName+"%')||(fullName like '%"+fullName+" "+surName+"%')||(surName like '%"+surName+" "+fullName+"%')||(idPatient like '%"+fullName+"%')||(dpi like '%"+fullName+"%')));";
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(query2);
            getAtributtesPatient(patients, rs);

        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return patients;
    }

    @Override
    public int updateStatusPatient(int idPatient,boolean status) throws SQLException {
        try {

            conection.abrirConexion();
            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(QUERY_PATIENT_STATUS_UPDATE);
            parametro.setBoolean(1, status);
            parametro.setInt(2, idPatient);
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
    public int getLastIdPatient() throws SQLException {
        int idPatient=0;
        try{
            conection.abrirConexion();
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(QUERY_PATIENT_LAST_ID);
            while (rs.next()) {
                idPatient = rs.getInt("id");
            }
        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return idPatient;
    }

    @Override
    public int updateStatusWait(int idPatient,boolean status) throws SQLException {
        int rs;
        try {
            conection.abrirConexion();
            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(QUERY_PATIENT_UPDATE_STATUSWAIT);
            parametro.setBoolean(1,status);
            parametro.setInt(2,idPatient);
            rs = parametro.executeUpdate();

        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }


        return rs;
    }

    @Override
    public List<PatientEntity> getPatientsWait() throws SQLException {
        List<PatientEntity>patients = new ArrayList<>();
        try{
            conection.abrirConexion();
            rs = conection.conexionBD.createStatement().executeQuery(SELECT_FROM_PATIENT_STATUSWAIT);
            getAtributtesPatient(patients, rs);

        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }

        return patients;
    }

    @Override
    public int updateAllStatusPatient(int idPatient,boolean status) throws SQLException {
        int rs;
        try {
            conection.abrirConexion();
            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(QUERY_PATIENT_UPDATE_STATUSALL);
            parametro.setString(1,"false");
            parametro.setBoolean(2,status);
            parametro.setInt(3,idPatient);
            rs = parametro.executeUpdate();

        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return rs;
    }

    private void getAtributtesPatient(List<PatientEntity> patients, ResultSet rs) throws SQLException {
        while (rs.next()){
            PatientEntity nuevo = new PatientEntity();

            nuevo.setIdPatient(Integer.parseInt(rs.getString("idPatient")));
            nuevo.setDpi(rs.getString("dpi"));
            nuevo.setNit(rs.getString("nit"));
            nuevo.setBirthday(rs.getDate("birthday"));
            nuevo.setGender(rs.getString("gender").charAt(0));
            nuevo.setFullName(rs.getString("fullName"));
            nuevo.setSurName(rs.getString("surName"));
            nuevo.setAddress(rs.getString("address"));
            nuevo.setPhone(Integer.parseInt(rs.getString("phone")));
            nuevo.setEmergencyContact(Integer.parseInt(rs.getString("emergencyContact")));
            nuevo.setStatePatient(Boolean.parseBoolean(rs.getString("status")));
            nuevo.setStatusWait(rs.getBoolean("statusWait"));

            patients.add(nuevo);
        }
    }


}