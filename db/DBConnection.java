/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icet.thogaKade.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chamu
 */
public class DBConnection {
    private Connection connection;
    private static DBConnection dbConnection;
    
    private DBConnection() throws ClassNotFoundException, SQLException {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection=DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234");
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }
    
    
}
