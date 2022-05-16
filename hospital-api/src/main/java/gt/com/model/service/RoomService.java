package gt.com.model.service;

import gt.com.model.dao.IRoomDao;
import gt.com.model.dao.RoomDao;
import gt.com.model.dto.ResponseRoomDto;
import gt.com.model.entity.RoomEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static gt.com.model.configurationApp.MessagesErrorApp.*;

public class RoomService implements IRoomService{

    @Override
    public ResponseRoomDto getRoomsByStatus(HttpServletRequest request) throws SQLException {
        IRoomDao roomDao = new RoomDao();
        List<RoomEntity> roomEntities;
        try {
            if(request.getParameter("status").isEmpty()){
                return new ResponseRoomDto("status"+PARAMETER_EMPTY,false,2);
            }else{
                try {
                    roomEntities = roomDao.getRoomsByStatus(Boolean.parseBoolean(request.getParameter("status")));
                } catch (SQLException ex) {
                    System.out.println(ex);
                    return new ResponseRoomDto(SQL_ERROR_QUERY+ex.getMessage(),false,3);
                }
                if (roomEntities.isEmpty()) {
                    return new ResponseRoomDto(ARRAY_ROOMS_EMPTY,false,3);
                }else{
                    return new ResponseRoomDto(SUCCESS,roomEntities,true,1);
                }
            }
        }catch (NullPointerException ex){
            return new ResponseRoomDto(PARAMETER_NOTFOUND+" status",false,2);
        }
    }
    @Override
    public ResponseRoomDto getRoomByPatient(int searchParam) throws SQLException {
        IRoomDao roomDao = new RoomDao();
        List<RoomEntity> roomEntities;

        try {
            roomEntities = roomDao.getRoomsByPatient(searchParam);
        } catch (SQLException ex) {
            return new ResponseRoomDto(SQL_ERROR_QUERY,false,3);
        }
        if (roomEntities.isEmpty()) {
            return new ResponseRoomDto("Este paciente no tiene habiataciones a su nombre",false,3);
        }else{
            return new ResponseRoomDto(SUCCESS,roomEntities,true,1);
        }
    }
    @Override
    public ResponseRoomDto getAllRooms() throws SQLException {
        IRoomDao dao = new RoomDao();
        List<RoomEntity> rooms = new ArrayList<>();
        try {
            rooms = dao.getRoomsAll();
            if(!rooms.isEmpty()){
                return new ResponseRoomDto(SUCCESS,rooms,true,1);
            }else{
                return new ResponseRoomDto(ARRAY_ROOMS_EMPTY,false,3);
            }
        }catch (SQLException ex){
            return new ResponseRoomDto(SQL_ERROR_QUERY,false,3);
        }
    }
}
