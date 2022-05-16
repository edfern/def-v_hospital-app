package gt.com.model.dao;

import gt.com.model.configurationApp.ConfigurationApp;
import gt.com.model.entity.MenuEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements IMenuDao{

    @Override
    public List<MenuEntity> getMenu() throws SQLException {

        ConexionSingleton conexion = ConexionSingleton.getInstance();

        ConfigurationApp config = new ConfigurationApp();

        String query = String.format("select idMenu,name,url,icon from menu;");
        List<MenuEntity> menusEntity = new ArrayList<>();
        try {
            conexion.abrirConexion();

            ResultSet rs = conexion.conexionBD.createStatement().executeQuery(query);


            while (rs.next()) {
                MenuEntity nuevo = new MenuEntity();

                nuevo.setIdMenu(rs.getInt("idMenu"));
                nuevo.setName(rs.getString("name"));
                nuevo.setUrl(rs.getString("url"));
                nuevo.setIcon(rs.getString("icon"));
                menusEntity.add(nuevo);
            }

        } catch (SQLException e){
            throw e;

        } finally {
            conexion.cerrarConexion();
        }
        return menusEntity;
    }
}
