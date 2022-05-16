package gt.com.control;

import gt.com.model.dto.ResponseAppointmentsDto;
import gt.com.model.service.AppointmentService;
import gt.com.model.service.IAppointmentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppointmentController {

    @CrossOrigin
    @GetMapping(value = "/appointment", produces = "application/json")
    public ResponseAppointmentsDto getAppointment(HttpServletRequest request){
        IAppointmentService appointmentService = new AppointmentService();
        return appointmentService.getAppointments(request);
    }

    @CrossOrigin
    @PostMapping(value = "/appointment", produces = "application/json")
    public ResponseAppointmentsDto saverAppointment(HttpServletRequest request) {
        IAppointmentService appointmentService = new AppointmentService();
        return  appointmentService.saveAppointment(request);
    }

    @CrossOrigin
    @PutMapping(value = "/appointment", produces = "application/json")
    public ResponseAppointmentsDto updateAppointment(HttpServletRequest request) {
        IAppointmentService appointmentService = new AppointmentService();
        return  appointmentService.updateAppointment(request);
    }

    @CrossOrigin
    @DeleteMapping(value = "/appointment", produces = "application/json")
    public ResponseAppointmentsDto deleteAppointment(HttpServletRequest request){
        IAppointmentService appointmentService = new AppointmentService();
        return appointmentService.deleteAppointment(Integer.parseInt(request.getParameter("idAppointment")));
    }

}
