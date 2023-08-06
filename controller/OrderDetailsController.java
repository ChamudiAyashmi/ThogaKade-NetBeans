/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icet.thogaKade.controller;

import icet.thogaKade.db.DBConnection;
import icet.thogaKade.model.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author chamu
 */
class OrderDetailsController {

    public static boolean addOrderDetails(ArrayList<OrderDetails> orderDetailList) throws ClassNotFoundException, SQLException {
         for (OrderDetails orderDetails : orderDetailList) {
            boolean isAdded=addOrderDetails(orderDetails);
            if(!isAdded){
                return false;
            }
        }
        return true;
    }
    public static boolean addOrderDetails(OrderDetails orderDetails) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("Insert into orderDetails values (?,?,?,?)");
        pstm.setObject(1, orderDetails.getOrderId());
        pstm.setObject(2, orderDetails.getItemCode());
        pstm.setObject(3, orderDetails.getQty());
        pstm.setObject(4, orderDetails.getUnitPrice());
        
        return pstm.executeUpdate()>0;
    }
   
    
}
