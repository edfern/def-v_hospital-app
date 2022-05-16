package gt.com.control;

import gt.com.model.dto.ResponsePatientRoomDto;
import gt.com.model.service.IPatientRoomService;
import gt.com.model.service.PatientRoomService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PatientRoomController {

    @CrossOrigin
    @PostMapping(value = "/patientRoom", produces = "application/json")
    public ResponsePatientRoomDto savePatientRoom(HttpServletRequest request) throws Exception {
        IPatientRoomService patientRoomService = new PatientRoomService();

        return patientRoomService.savePatientRoom(request);
    }

    @CrossOrigin
    @PutMapping(value = "/patientRoom", produces = "application/json")
    public ResponsePatientRoomDto updatePatientRoom(HttpServletRequest request) throws Exception {
        IPatientRoomService patientRoomService = new PatientRoomService();
        return patientRoomService.updatePatientRoom(request);
    }
}
