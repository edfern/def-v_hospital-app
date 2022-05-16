package gt.com.control;

import gt.com.model.dto.ResponseRoomDto;
import gt.com.model.service.IRoomService;
import gt.com.model.service.RoomService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import static gt.com.model.configurationApp.MessagesErrorApp.*;

@RestController
public class RoomController {

    @CrossOrigin
    @GetMapping(value = "/room", produces = "application/json")
    public ResponseRoomDto readPatient(HttpServletRequest request) throws SQLException {
        IRoomService roomService = new RoomService();   

        try{
            if(!request.getParameter("type").isEmpty()){
                System.out.println(request.getParameter("type"));
                if(request.getParameter("type").equals("roomAll")){
                    return roomService.getAllRooms();
                }else if(request.getParameter("type").equals("status")){
                    return roomService.getRoomsByStatus(request);
                }else{
                    return new ResponseRoomDto(OPTION_INCORRET,false,3);
                }
            }else{
                return new ResponseRoomDto(PARAMETER_EMPTY,false,3);
            }

        }catch (NullPointerException ex){
            return new ResponseRoomDto(PARAMETER_NOTFOUND+" 'type'",false,3);
        }
    }
}
