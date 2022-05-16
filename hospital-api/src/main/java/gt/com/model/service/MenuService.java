package gt.com.model.service;

import gt.com.model.dao.IMenuDao;
import gt.com.model.dao.MenuDao;
import gt.com.model.dto.ResponseMenuDto;
import gt.com.model.entity.MenuEntity;
import java.sql.SQLException;
import java.util.List;

import static gt.com.model.configurationApp.MessagesErrorApp.*;
import static gt.com.model.configurationApp.MessagesErrorApp.SERVICE_JSON_FAIL;

public class MenuService implements IMenuService {

    @Override
    public ResponseMenuDto getMenus() {

        IMenuDao menuDao = new MenuDao();
        List<MenuEntity> menusEntity = null;


        try {
            menusEntity = menuDao.getMenu();
        } catch (SQLException throwables) {
            return new ResponseMenuDto(SQL_ERROR_QUERY,false,3);
        }
        if (menusEntity != null) {
                return new ResponseMenuDto(menusEntity,SUCCESS,true,1);

        } else {
            return new ResponseMenuDto(SERVICE_JSON_FAIL,false,3);
        }
    }
}
