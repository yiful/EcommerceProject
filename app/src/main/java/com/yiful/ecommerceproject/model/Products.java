package com.yiful.ecommerceproject.model;

import java.util.List;

/**
 * Created by Yifu on 11/26/2017.
 */

public class Products {
    private List<ProductBean> Product;

    public List<ProductBean> getProduct() {
        return Product;
    }

    public void setProduct(List<ProductBean> Product) {
        this.Product = Product;
    }

    public static class ProductBean {
        /**
         * Id : 308
         * ProductName : i5-Laptop
         * Quantity : 1
         * Prize : 60000
         * Discription : Online directory of electrical goods manufacturers, electronic goods suppliers and electronic product manufacturers. Get details of electronic products
         * Image : https://rjtmobile.com/ansari/shopingcart/admin/uploads/product_images/images.jpg
         */

        private String Id;
        private String ProductName;
        private String Quantity;
        private String Prize;
        private String Discription;
        private String Image;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String Quantity) {
            this.Quantity = Quantity;
        }

        public String getPrize() {
            return Prize;
        }

        public void setPrize(String Prize) {
            this.Prize = Prize;
        }

        public String getDiscription() {
            return Discription;
        }

        public void setDiscription(String Discription) {
            this.Discription = Discription;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }
    }
}
