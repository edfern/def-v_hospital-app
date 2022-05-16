package gt.com.model.dao;


import gt.com.model.entity.RoomEntity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static gt.com.model.configurationApp.ConfigurationApp.*;

/**
 * 
 */
public class RoomDao implements IRoomDao {
    private PreparedStatement pr;
    private ConexionSingleton conexion = ConexionSingleton.getInstance();
    List<RoomEntity> roomEntities = new ArrayList<>();

    public List<RoomEntity> getRoomsByStatus(boolean status) throws SQLException {

        try {
            conexion.abrirConexion();
            ResultSet rs = conexion.conexionBD.createStatement().executeQuery(String.format(SELECT_ROOMS_STATUS,status));

            while (rs.next()) {
                RoomEntity room = new RoomEntity();
                room.setIdRoom(rs.getInt("idRoom"));
                room.setCapacityRoom(rs.getInt("capacityRoom"));
                room.setNumberRoom(rs.getInt("numberRoom"));
                room.setStatusRoom(rs.getBoolean("statusRoom"));
                roomEntities.add(room);
            }
        }catch(SQLException e){
                throw e;

        }finally{
                conexion.cerrarConexion();
        }
        return roomEntities;

        }

    @Override
    public int updateRoomStatus(int idRoom,boolean status) throws SQLException {

        int rs;
        try {
            conexion.abrirConexion();
            pr = (PreparedStatement) conexion.conexionBD.prepareStatement(UPDATE_QUERY_ROOMS_STATUS);
            pr.setBoolean(1,status);
            pr.setInt(2,idRoom);
            pr.executeUpdate();
            rs = pr.executeUpdate();

        }catch (SQLException ex){
            throw ex;
        }finally {
            conexion.cerrarConexion();
        }
        return rs;
    }

    @Override
    public List<RoomEntity> getRoomsByPatient(long searchParam) throws SQLException {
        ConexionSingleton conexion = ConexionSingleton.getInstance();

        List<RoomEntity> roomEntities = new ArrayList<>();
        try {
            conexion.abrirConexion();

            PreparedStatement preparedStatement = conexion.conexionBD.prepareStatement(SELECT_REGISTER_BY_PATIENT);
            preparedStatement.setLong(1,searchParam);
            preparedStatement.setLong(2,searchParam);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                RoomEntity room = new RoomEntity();
                room.setIdRoom(rs.getInt("idRoom"));
                room.setCapacityRoom(rs.getInt("capacityRoom"));
                room.setNumberRoom(rs.getInt("numberRoom"));
                room.setStatusRoom(rs.getBoolean("statusRoom"));
                roomEntities.add(room);
            }
        }catch(SQLException e){
            throw e;

        }finally{
            conexion.cerrarConexion();
        }
        return roomEntities;
    }


    public List<RoomEntity> getRoomsAll() throws SQLException {

        try {
            conexion.abrirConexion();
            ResultSet rs = conexion.conexionBD.createStatement().executeQuery(SELECT_ROOMS_ALL);

            while (rs.next()) {
                RoomEntity room = new RoomEntity();

                room.setIdRoom(rs.getInt("idRoom"));
                room.setCapacityRoom(rs.getInt("capacityRoom"));
                room.setNumberRoom(rs.getInt("numberRoom"));
                room.setStatusRoom(rs.getBoolean("statusRoom"));
                roomEntities.add(room);
            }
        }catch(SQLException e){
            throw e;

        }finally{
            conexion.cerrarConexion();
        }
        return roomEntities;
    }


}