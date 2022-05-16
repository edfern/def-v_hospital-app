package gt.com.model.service;

import gt.com.model.dto.ResponseGenericDto;

import javax.servlet.http.HttpServletRequest;

public interface IGuestAppointmentService {

    public ResponseGenericDto getGuestAppointments();
    public ResponseGenericDto searchGuestAppointmentByName(HttpServletRequest request);
    public ResponseGenericDto chooseOneGetOperation(HttpServletRequest request);
    public ResponseGenericDto deleteGuestAppointment(HttpServletRequest request);
    public ResponseGenericDto updateGuestAppointment(HttpServletRequest request);
    public ResponseGenericDto saveGuestAppointment(HttpServletRequest request);
}
