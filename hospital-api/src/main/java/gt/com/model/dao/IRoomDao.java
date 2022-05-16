package gt.com.model.dao;

import gt.com.model.entity.RoomEntity;

import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public interface IRoomDao {


    public List<RoomEntity> getRoomsByPatient(long searchParam) throws  SQLException;
    public List<RoomEntity> getRoomsByStatus(boolean status)throws SQLException;
    public int updateRoomStatus(int idRoom,boolean status)throws SQLException;
    public List<RoomEntity> getRoomsAll() throws SQLException;

}