package gt.com.model.dto;

import java.util.List;

public class ResponseRoomDto {

    private String message;
    private Object data;
    private List<Object> dataList;
    private boolean status;
    private int typeMessage;

    public ResponseRoomDto(String message, List<Object> dataList, boolean status, int typeMessage) {
        this.message = message;
        this.dataList = dataList;
        this.status = status;
        this.typeMessage = typeMessage;
    }

    public ResponseRoomDto(String message, Object data, boolean status, int typeMessage) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.typeMessage = typeMessage;
    }

    public ResponseRoomDto(String message, boolean status, int typeMessage) {
        this.message = message;
        this.status = status;
        this.typeMessage = typeMessage;
    }

    public ResponseRoomDto(String errorMessage) {
        this.message = errorMessage;
    }

    public ResponseRoomDto(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return message;
    }

    public void setErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
