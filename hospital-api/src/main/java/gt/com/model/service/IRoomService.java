package gt.com.model.service;

import gt.com.model.dto.ResponseRoomDto;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface IRoomService {


    public ResponseRoomDto getRoomByPatient(int searchParam) throws  SQLException;
    public ResponseRoomDto getRoomsByStatus(HttpServletRequest request) throws SQLException;
    public ResponseRoomDto getAllRooms() throws SQLException;

}
