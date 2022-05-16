package gt.com.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {

    public static ConexionSingleton conection;
    public Connection conexionBD;

    private ConexionSingleton(){

    }
    public static ConexionSingleton getInstance(){
        if(conection == null){
            conection = new ConexionSingleton();
        }
        return conection;
    }


    public void abrirConexion() {

        try {
            String jdbc = "com.mysql.cj.jdbc.Driver";
            Class.forName(jdbc);
            String urlConexion = "";
            String usuario = "";
            String contra = "";
            conexionBD = DriverManager.getConnection(urlConexion, usuario, contra);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cerrarConexion()
    {
        try
        {
            conexionBD.close();
        }
        catch (Exception ex)
        {

        }
    }


}
