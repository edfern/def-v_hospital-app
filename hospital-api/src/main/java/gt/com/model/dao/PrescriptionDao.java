package gt.com.model.dao;

import gt.com.model.entity.PatientRoomEntity;
import gt.com.model.entity.PrescriptionEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static gt.com.model.configurationApp.ConfigurationApp.*;

public class PrescriptionDao implements  IPrescriptionsDao{
    private ConexionSingleton conection = ConexionSingleton.getInstance();

    @Override
    public List<PrescriptionEntity> getPrescriptions() throws SQLException {


        List<PrescriptionEntity> prescriptionEntities = new ArrayList<>();
        try{
            conection.abrirConexion();

            ResultSet rs = conection.conexionBD.createStatement().executeQuery(SELECT_PRESCRIPTIONS);

            while (rs.next()){
                PrescriptionEntity nuevo = new PrescriptionEntity();

                nuevo.setIdPrescriptiosn(rs.getInt("idPrescription"));
                nuevo.setPrescription(rs.getString("prescription"));
                nuevo.setIdPatient(rs.getInt("idPatient"));
                nuevo.setDatePrescription(rs.getDate("datePrescription"));
                nuevo.setIdDoctor(rs.getInt("idDoctor"));
                prescriptionEntities.add(nuevo);
            }

        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }
        return prescriptionEntities;
    }

    @Override
    public int savePrescription(PrescriptionEntity prescriptionEntity) throws SQLException {
        int rs;

        try {

            conection.abrirConexion();

            PreparedStatement parametro = (PreparedStatement) conection.conexionBD.prepareStatement(INSERT_PRESCRIPTIONS);
            parametro.setString(1,prescriptionEntity.getPrescription());
            parametro.setInt(2, prescriptionEntity.getIdPatient());
            parametro.setDate(3, (Date) prescriptionEntity.getDatePrescription());
            parametro.setInt(4, prescriptionEntity.getIdDoctor());
                rs = parametro.executeUpdate();

        } catch (SQLException  ex ){
            throw ex;
        }catch (Exception ex){
            throw  ex;
        }
        finally {
            conection.cerrarConexion();
        }
        return rs;
    }

    @Override
    public List<PrescriptionEntity> searchPrescriptionFiltred(long searchParameter) throws SQLException {
        List<PrescriptionEntity> prescriptionEntities = new ArrayList<>();
        try{
            conection.abrirConexion();

            ResultSet rs;// = conection.conexionBD.createStatement().executeQuery(SELECT_PRESCRIPTIONS);

            Connection con = null;
            PreparedStatement parametro = (PreparedStatement) conection.conexionBD.prepareStatement(SELECT_PRESCRIPTIONS_FILTRED);
            parametro.setLong(1,searchParameter);
            parametro.setLong(2, searchParameter);
            rs=parametro.executeQuery();

            while (rs.next()){
                PrescriptionEntity nuevo = new PrescriptionEntity();

                nuevo.setIdPrescriptiosn(rs.getInt("idPrescription"));
                nuevo.setPrescription(rs.getString("prescription"));
                nuevo.setIdPatient(rs.getInt("idPatient"));
                nuevo.setDatePrescription(rs.getDate("datePrescription"));
                nuevo.setIdDoctor(rs.getInt("idDoctor"));
                prescriptionEntities.add(nuevo);
            }

        }catch (SQLException ex){
            throw ex;

        }finally {
            conection.cerrarConexion();
        }
        return prescriptionEntities;


    }
}
