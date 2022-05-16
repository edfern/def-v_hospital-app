package gt.com.model.service;

import gt.com.model.dto.ResponsePatientDto;
import gt.com.model.dto.ResponsePatientRoomDto;

import javax.servlet.http.HttpServletRequest;

public interface IPatientRoomService {

    public ResponsePatientRoomDto savePatientRoom(HttpServletRequest request) throws Exception;
    public ResponsePatientRoomDto updatePatientRoom(HttpServletRequest request) throws Exception;


}
