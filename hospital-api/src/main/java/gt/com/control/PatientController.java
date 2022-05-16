package gt.com.control;

import gt.com.model.dto.ResponsePatientDto;
import gt.com.model.service.IPatientService;
import gt.com.model.service.PatientService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PatientController {

    @CrossOrigin
    @GetMapping(value = "/patient", produces = "application/json")
    public ResponsePatientDto readPatient(HttpServletRequest request){
        IPatientService patientService = new PatientService();

        return patientService.getPatients(request);
    }

    @CrossOrigin
    @PostMapping(value = "/patient", produces = "application/json")
    public ResponsePatientDto savePatient(HttpServletRequest request) {
        IPatientService patientService = new PatientService();

        try {
            return patientService.savePatient(request);
        } catch (Exception e) {
            return  new ResponsePatientDto(e.getMessage());
        }

    }

    @CrossOrigin
    @PutMapping(value = "/patient", produces = "application/json")
    public ResponsePatientDto updatePatientInfo(HttpServletRequest request) {
        IPatientService patientService = new PatientService();

       return patientService.updatePatient(request);
    }

    @CrossOrigin
    @DeleteMapping(value = "/patient", produces = "application/json")
    public ResponsePatientDto deletePatientInfo(HttpServletRequest request){
        IPatientService patientService = new PatientService();

        return patientService.deletePatient(Integer.parseInt(request.getParameter("idPatient")));
    }

}
