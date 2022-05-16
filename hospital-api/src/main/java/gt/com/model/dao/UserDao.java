package gt.com.model.dao;

import gt.com.model.entity.UserEntity;
import gt.com.model.configurationApp.ConfigurationApp;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao implements IUserDao {

    public UserDao() {
    }

    @Override
    public UserEntity getUser(String email, String password) throws SQLException {
        ConexionSingleton conexion = ConexionSingleton.getInstance();

        ConfigurationApp config = new ConfigurationApp();

        String query = String.format("select idUser,userName,password,role,email from users where email = '%s' and password = '%s';",email,password);
        UserEntity entity = new UserEntity();
        try {
            conexion.abrirConexion();

            ResultSet rs = conexion.conexionBD.createStatement().executeQuery(query);


            while (rs.next()) {
                entity.setIdUser(rs.getInt("idUser"));
                entity.setFullName(rs.getString("userName"));
                entity.setPassword(rs.getString("password"));
                entity.setRole(rs.getString("role"));
                entity.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            throw e;

        } finally {
            conexion.cerrarConexion();
        }
        return entity;

    }
}