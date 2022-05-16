package gt.com.model.service;

import gt.com.model.dto.ResponseAppointmentsDto;
import javax.servlet.http.HttpServletRequest;

public interface IAppointmentService {
    public ResponseAppointmentsDto getAppointments(HttpServletRequest request);
    public ResponseAppointmentsDto saveAppointment(HttpServletRequest request);
    public ResponseAppointmentsDto deleteAppointment(int idAppointment);
    public ResponseAppointmentsDto updateAppointment(HttpServletRequest request);
    public ResponseAppointmentsDto getAppointmentById(int idAppointment);

}
