package gt.com.model.dao;

import gt.com.model.entity.MenuEntity;

import java.sql.SQLException;
import java.util.List;

public interface IMenuDao {

    public List<MenuEntity> getMenu() throws SQLException;
}
