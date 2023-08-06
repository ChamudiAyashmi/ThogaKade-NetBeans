/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icet.thogaKade.model;

import java.util.ArrayList;

/**
 *
 * @author chamu
 */
public class Order {
    public String id;
    public String date;
    public String customerId;
    public ArrayList<OrderDetails>orderDetailList;  

    public Order() {
    }

    public Order(String id, String date, String customerId, ArrayList<OrderDetails> orderDetailList) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.orderDetailList = orderDetailList;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderDetailList
     */
    public ArrayList<OrderDetails> getOrderDetailList() {
        return orderDetailList;
    }

    /**
     * @param orderDetailList the orderDetailList to set
     */
    public void setOrderDetailList(ArrayList<OrderDetails> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
    

   
}
