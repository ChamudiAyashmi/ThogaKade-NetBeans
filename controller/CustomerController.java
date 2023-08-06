/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icet.thogaKade.controller;

import icet.thogaKade.db.DBConnection;
import icet.thogaKade.model.Customer;
import icet.thogaKade.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author chamu
 */
public class CustomerController {
     public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        String SQL = "Insert into Customer Values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1, customer.getId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getSalary());
        return pstm.executeUpdate() > 0;      
     }
     public static Customer searchCustomer(String id) throws ClassNotFoundException, ClassNotFoundException, SQLException{
         Connection connection=DBConnection.getInstance().getConnection();
         Statement stm=connection.createStatement();
         String SQL="Select * From Customer where id='"+id+"'";
         ResultSet rst=stm.executeQuery(SQL);
         return rst.next() ? new Customer(id,rst.getString("name"),rst.getString("address"),rst.getDouble("salary")):null;
     }
     public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        String SQL = "Update Customer set name=?,address=?,salary=? where id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
       
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getSalary());
        pstm.setObject(4, customer.getId());
        return pstm.executeUpdate() > 0;      
     }
     public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
         return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where id='"+id+"'")>0;
     }
     public static ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException{
        String SQL="Select * From Customer";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList <Customer>customerList=new ArrayList<>();
        
        while(rst.next()){
            Customer customer=new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"),rst.getDouble("salary"));
            customerList.add(customer);
        }
        return customerList;
    }

    public static boolean updateItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<String> getAllCustomerIds() throws ClassNotFoundException, SQLException {
         Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement pstm = connection.prepareStatement("SELECT id from Customer");
         ResultSet rst = pstm.executeQuery();
         ArrayList<String> idSet=new ArrayList<>();
         while(rst.next()){
               idSet.add(rst.getString(1));
         }
         return idSet;
    }
}
