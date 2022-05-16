package gt.com.model.dto;

import gt.com.model.entity.MenuEntity;

import java.util.List;

public class ResponseMenuDto {
    private List<MenuEntity> menusEntities;
    private String message;
    private boolean statusMenus;
    private int typeMessage;

    public ResponseMenuDto(List<MenuEntity> menusEntities, String message, boolean statusMenus, int typeMessage) {
        this.menusEntities = menusEntities;
        this.message = message;
        this.statusMenus = statusMenus;
        this.typeMessage = typeMessage;
    }

    public ResponseMenuDto(String message, boolean statusMenus, int typeMessage) {
        this.message = message;
        this.statusMenus = statusMenus;
        this.typeMessage = typeMessage;
    }

    public List<MenuEntity> getMenusEntities() {
        return menusEntities;
    }

    public void setMenusEntities(List<MenuEntity> menusEntities) {
        this.menusEntities = menusEntities;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatusMenus() {
        return statusMenus;
    }

    public void setStatusMenus(boolean statusMenus) {
        this.statusMenus = statusMenus;
    }

    public int getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(int typeMessage) {
        this.typeMessage = typeMessage;
    }
}
