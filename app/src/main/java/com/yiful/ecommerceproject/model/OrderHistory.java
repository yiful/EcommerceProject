package com.yiful.ecommerceproject.model;

// FIXME generate failure  field _$OrderHistory204

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yifu on 11/26/2017.
 */

public class OrderHistory {
    @SerializedName("Order History")
    private List<OrderHistoryBean> orderHistoryBeanList;

    public static class OrderHistoryBean {
        /**
         * OrderID : 2969
         * ItemName : laptop
         * ItemQuantity : 1
         * FinalPrice : 100000
         * OrderStatus : 1
         */

        private String OrderID;
        private String ItemName;
        private String ItemQuantity;
        private String FinalPrice;
        private String OrderStatus;

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public String getItemQuantity() {
            return ItemQuantity;
        }

        public void setItemQuantity(String ItemQuantity) {
            this.ItemQuantity = ItemQuantity;
        }

        public String getFinalPrice() {
            return FinalPrice;
        }

        public void setFinalPrice(String FinalPrice) {
            this.FinalPrice = FinalPrice;
        }

        public String getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(String OrderStatus) {
            this.OrderStatus = OrderStatus;
        }
    }

    public List<OrderHistoryBean> getOrderHistoryBeanList(){
        return orderHistoryBeanList;
    }
}
