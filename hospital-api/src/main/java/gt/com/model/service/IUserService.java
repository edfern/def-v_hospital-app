package gt.com.model.service;

import gt.com.model.dto.ResponseLoginDto;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    public ResponseLoginDto validateUser(HttpServletRequest request) throws Exception;
}
