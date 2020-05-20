/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servlets;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassConnection {

    private static Connection Conexion;
    
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost/gesprec?user=root&password=&useSSL=false");
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");

        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try{
            Conexion.close();
            System.out.println("Se ha finalizado la conexion con el servidro");
        }catch(SQLException ex){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getJdbcConnection(){
        return Conexion;
    }
    
}
