package gt.com.model.dao;

import gt.com.model.entity.UserEntity;

import java.sql.SQLException;

public interface IUserDao {

    public UserEntity getUser(String gmail, String password) throws SQLException;

}