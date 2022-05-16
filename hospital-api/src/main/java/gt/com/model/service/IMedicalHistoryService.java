package gt.com.model.service;

import gt.com.model.dto.ResponseGenericDto;

import javax.servlet.http.HttpServletRequest;

public interface IMedicalHistoryService {
    public ResponseGenericDto getMedicalHistory(HttpServletRequest request);
}
