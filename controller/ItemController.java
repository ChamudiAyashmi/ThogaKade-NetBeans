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
public class ItemController {
     public static boolean addItem(Item item) throws ClassNotFoundException, SQLException{
        String SQL = "Insert into Item Values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getUnitPrice());
        pstm.setObject(4, item.getQtyOnHand());
        return pstm.executeUpdate() > 0;      
     }
     public static Item searchItem(String code) throws ClassNotFoundException, ClassNotFoundException, SQLException{
         Connection connection=DBConnection.getInstance().getConnection();
         Statement stm=connection.createStatement();
         String SQL="Select * From Item where code='"+code+"'";
         ResultSet rst=stm.executeQuery(SQL);
         return rst.next() ? new Item(code,rst.getString("description"),rst.getDouble("unitPrice"),rst.getInt("qtyOnHand")):null;
     }
     public static boolean updateItem(Item item) throws ClassNotFoundException, SQLException{
        String SQL = "Update Item set description=?,unitPrice=?,qtyOnHand=? where code=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
       
        pstm.setObject(1, item.getDescription());
        pstm.setObject(2, item.getUnitPrice());
        pstm.setObject(3, item.getQtyOnHand());
        pstm.setObject(4, item.getCode());
        return pstm.executeUpdate() > 0;      
     }
     public static boolean deleteItem(String code) throws ClassNotFoundException, SQLException{
         return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Item where code='"+code+"'")>0;
     }
     public static ArrayList<Item> getAllItems() throws ClassNotFoundException, SQLException{
        String SQL="Select * From Item";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList <Item>itemList=new ArrayList<>();
        
        while(rst.next()){
            Item item=new Item(rst.getString("code"), rst.getString("description"), rst.getDouble("unitPrice"),rst.getInt("qtyOnHand"));
            itemList.add(item);
        }
        return itemList;
    }

    public static ArrayList<String> getAllItemCodes() throws ClassNotFoundException, SQLException {
         Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement pstm = connection.prepareStatement("SELECT code from Item");
         ResultSet rst = pstm.executeQuery();
         ArrayList<String> codeSet=new ArrayList<>();
         while(rst.next()){
             codeSet.add(rst.getString(1));
         }
         return codeSet;
    }
}
