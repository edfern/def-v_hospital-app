package gt.com.control;


import gt.com.model.dto.ResponseGenericDto;
import gt.com.model.service.GuestAppointmentService;
import gt.com.model.service.IGuestAppointmentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GuestAppointmentController {

    @CrossOrigin
    @GetMapping(value = "/guestAppointment", produces = "application/json")
    public ResponseGenericDto getGuestAppointments(HttpServletRequest request){
        IGuestAppointmentService guestAppointmentService = new GuestAppointmentService();

            try{
                return guestAppointmentService.chooseOneGetOperation(request);
            } catch (Exception ex){
                return new ResponseGenericDto("No se pudo completar la peticion :(",3,false);
            }
    }

    @CrossOrigin
    @DeleteMapping(value = "/guestAppointment", produces = "application/json")
    public ResponseGenericDto deleteGuestAppointments(HttpServletRequest request){
        IGuestAppointmentService guestAppointmentService = new GuestAppointmentService();

        try{
            return guestAppointmentService.deleteGuestAppointment(request);
        } catch (Exception ex){
            return new ResponseGenericDto("No se pudo completar la peticion :(",3,false);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/guestAppointment", produces = "application/json")
    public ResponseGenericDto updateGuestAppointments(HttpServletRequest request){
        IGuestAppointmentService guestAppointmentService = new GuestAppointmentService();

        try{
            return guestAppointmentService.updateGuestAppointment(request);
        } catch (Exception ex){
            return new ResponseGenericDto("No se pudo completar la peticion :(",3,false);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/guestAppointment", produces = "application/json")
    public ResponseGenericDto saveGuestAppointments(HttpServletRequest request){
        IGuestAppointmentService guestAppointmentService = new GuestAppointmentService();

        try{
            return guestAppointmentService.saveGuestAppointment(request);
        } catch (Exception ex){
            return new ResponseGenericDto("No se pudo completar la peticion :(",3,false);
        }
    }

}
