package gt.com.model.dao;

import gt.com.model.entity.PatientEntity;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * 
 */
public interface IPatientDao {

    public boolean savePatient(PatientEntity patient) throws SQLException;
    public List<PatientEntity> getPatients() throws SQLException;
    public int updatePatient(PatientEntity patient) throws SQLException;
    public PatientEntity getPatientById(long id) throws  SQLException;
    public int deletePatient(int id) throws SQLException;
    public List<PatientEntity> getPatients(String fullName,String surName,boolean status) throws SQLException, ParseException;
    public int updateStatusPatient(int idPatient,boolean status) throws SQLException;
    public int getLastIdPatient() throws SQLException;
    public int updateStatusWait(int idPatient,boolean status) throws SQLException;
    public List<PatientEntity> getPatientsWait() throws SQLException;
    public int updateAllStatusPatient(int idPatient,boolean status) throws SQLException;
}