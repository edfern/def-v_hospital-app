package gt.com.model.dto;

public class ResponseLoginDto {

    private String message;
    private Object jsonResponse;
    private boolean statusLogin;
    private int typeMessage;


    public ResponseLoginDto(String message, boolean statusLogin, int typeMessage) {
        this.message = message;
        this.statusLogin = statusLogin;
        this.typeMessage = typeMessage;
    }

    public ResponseLoginDto(String message, Object jsonResponse, boolean statusLogin, int typeMessage) {
        this.message = message;
        this.jsonResponse = jsonResponse;
        this.statusLogin = statusLogin;
        this.typeMessage = typeMessage;
    }

    public boolean isStatusLogin() {
        return statusLogin;
    }
    public void setStatusLogin(boolean statusLogin) {
        this.statusLogin = statusLogin;
    }
    public String getErrorMessage() {
        return message;
    }

    public void setErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public Object getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(Object jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
}
