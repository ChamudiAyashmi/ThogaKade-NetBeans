/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icet.thogaKade.controller;

import icet.thogaKade.db.DBConnection;
import icet.thogaKade.model.Order;
import icet.thogaKade.model.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author chamu
 */
public class OrderController {
    
    public static String getLastOrderId() throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT id FROM Orders ORDER BY id DESC LIMIT 1");
        return rst.next() ? rst.getString("id") : null;
    }

    public static boolean placeOrder(Order order) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("Insert into orders values (?,?,?,?)");
        pstm.setObject(1, order.getId());
        pstm.setObject(2, order.getDate());
        pstm.setObject(3, order.getCustomerId());
        
        boolean orderIsAdded = pstm.executeUpdate()>0;
        
        if(orderIsAdded){
            boolean oderDetailsIsAdded = OrderDetailsController.addOrderDetails(order.getOrderDetailList());
            if(oderDetailsIsAdded){
                boolean itemIsUpdate=ItemController.updateStock(order.getOrderDetailList());
                if(itemIsUpdate){
                    return true;
                }
            }
        }
        return false;
    }
            
}
