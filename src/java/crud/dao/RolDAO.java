/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import crud.model.Rol;
import crud.servlets.ClassConnection;

/**
 *
 * @author Dell_1
 */
public class RolDAO {
    
  private ClassConnection con;
    private Connection connection;
    
    public RolDAO()
    {
        con = new ClassConnection();
    }
    
    public boolean insert (Rol rol) throws SQLException{
    
        String sql ="INSERT INTO `rol` (`id_rol`,`nombre_rol`) VALUES (?,?)";
        System.out.println(rol.getNombre());
        con.connect();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, rol.getId());
        statement.setString(2, rol.getNombre());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.disconnect();
        return rowInserted;
        
    }
    
    public List<Rol> listRol() throws SQLException{
    
        //creación de lista para guardar los roles
        List<Rol> listRol = new ArrayList<Rol>();
        //string con la sentencia sql
        String sql = "select *from rol";
        //abrimos la conexión 
        con.connect();
        //guardando en conexión nuestra conexión
        connection =con.getJdbcConnection();
        //definimos un statament
        Statement statement = connection.createStatement();
        //se ejecuta la consulta
        ResultSet resultSet =statement.executeQuery(sql);
        
        while(resultSet.next()){
        
            int id =resultSet.getInt("id");
            String nombre =resultSet.getString("nombre");
            
            Rol rol = new Rol (id,nombre);
            listRol.add(rol);
            
            
        }
        
        con.disconnect();
        return listRol;
                
    }
    
    public boolean delete (Rol rol ) throws SQLException
    {
        boolean rowDelete =false;
        String sql ="DELETE FROM rol WHERE id=?";
        con.connect();
        connection=con.getJdbcConnection();
        PreparedStatement statement =connection.prepareStatement(sql);
        statement.setInt(1,rol.getId());
        rowDelete = statement.executeUpdate()>0;
        statement.close();
        con.disconnect();
        return rowDelete;
    }
    
    public Rol getRolId (int id) throws SQLException
    {
        Rol rol = null;
        String sql ="SELECT *FROM rol WHERE =" +id;
        con.connect();
        connection=con.getJdbcConnection();
        PreparedStatement statement =connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
        rol = new Rol  
        (res.getInt("id"),res.getString("nombre"));
            
        }
        statement.close();
        con.disconnect();
        return rol;
    }
    
    public boolean update (Rol rol) throws SQLException
    {
        boolean rowActualizar =false;
        String sql = "UPDATE Rol SET id=?,nombre=? where id=?" ;
        con.connect();
        connection=con.getJdbcConnection();
        PreparedStatement statement =connection.prepareStatement(sql);
        statement.setInt(1,rol.getId());
        statement.setString(1,rol.getNombre());
        rowActualizar =statement.executeUpdate()>0;
        statement.close();
        con.disconnect();
        return rowActualizar;
                
                
    }
    
}
