package gt.com.model.service;

import gt.com.model.dto.ResponseGenericDto;

import javax.servlet.http.HttpServletRequest;

public interface IPrescriptionService {

    public ResponseGenericDto getPrescriptions(HttpServletRequest request);
    public ResponseGenericDto savePrescription(HttpServletRequest request);
    public ResponseGenericDto seachrPrescriptionFiltred(HttpServletRequest request);
}
