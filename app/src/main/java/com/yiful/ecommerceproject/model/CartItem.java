package com.yiful.ecommerceproject.model;

/**
 * Created by Yifu on 11/26/2017.
 */

public class CartItem {
    private Products.ProductBean productBean;
    private String quantity;

    public CartItem(Products.ProductBean productBean, String quantity) {
        this.productBean = productBean;
        this.quantity = quantity;
    }

    public Products.ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(Products.ProductBean productBean) {
        this.productBean = productBean;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
