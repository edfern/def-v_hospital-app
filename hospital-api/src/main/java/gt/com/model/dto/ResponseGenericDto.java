package gt.com.model.dto;

import java.util.List;

public class ResponseGenericDto {

    private String message;
    private int type;
    private boolean status;
    private Object object;
    private List<Object> list;


    public ResponseGenericDto(String message, int type, boolean status, Object object) {
        this.message = message;
        this.type = type;
        this.status = status;
        this.object = object;
    }

    public ResponseGenericDto(String message, int type, boolean status, List<Object> list) {
        this.message = message;
        this.type = type;
        this.status = status;
        this.list = list;
    }

    public ResponseGenericDto(String message, int type, boolean status) {
        this.message = message;
        this.type = type;
        this.status = status;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
