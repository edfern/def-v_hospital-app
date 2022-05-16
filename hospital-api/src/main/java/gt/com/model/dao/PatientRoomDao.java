package gt.com.model.dao;

import gt.com.model.entity.PatientRoomEntity;
import gt.com.model.entity.RoomEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static gt.com.model.configurationApp.ConfigurationApp.INSERT_QUERY_PATIENT_ROOM;
import static gt.com.model.configurationApp.ConfigurationApp.*;

public class PatientRoomDao implements IPatientRoomDao{
    private ConexionSingleton conection = ConexionSingleton.getInstance();
    private PreparedStatement parametro;
    @Override
    public boolean savePatientRoom(PatientRoomEntity patientRoomEntity) throws SQLException {

        boolean rs;

        try{
            conection.abrirConexion();

            String query = String.format(INSERT_QUERY_PATIENT_ROOM,patientRoomEntity.getIdPatient(),patientRoomEntity.getIdRoom());
            System.out.println(query);
            rs = conection.conexionBD.createStatement().execute(query);

        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return rs;
    }

    @Override
    public int updatePatientRoom(int idPatient,boolean status) throws SQLException {

        try {
            conection.abrirConexion();
            parametro = (PreparedStatement)conection.conexionBD.prepareStatement(UPDATE_QUERY_PATIENT_ROOM);
            parametro.setBoolean(1, status);
            parametro.setInt(2, idPatient);
            parametro.executeUpdate();

        } catch (SQLException ex ){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return 1;
    }

    @Override
    public List<RoomEntity> getRoomsPatient(int idPatient, boolean status) throws SQLException {
        List<RoomEntity> rooms = new ArrayList<>();
        String query = String.format(LIST_ROOMS_PATIENTS_STATUS,idPatient,status);
        try {
            conection.abrirConexion();
            ResultSet rs = conection.conexionBD.createStatement().executeQuery(query);
            while (rs.next()){
                RoomEntity room = new RoomEntity();
                room.setIdRoom(rs.getInt("idRoom"));
                room.setNumberRoom(rs.getInt("numberRoom"));
                room.setCapacityRoom(rs.getInt("capacityRoom"));
                room.setStatusRoom(rs.getBoolean("statusRoom"));
                rooms.add(room);
            }
        }catch (SQLException ex){
            throw ex;
        }finally {
            conection.cerrarConexion();
        }
        return rooms;
    }
}
