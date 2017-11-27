package com.yiful.ecommerceproject.model;

import java.util.List;

/**
 * Created by Yifu on 11/25/2017.
 */

public class SubCategories {

    private List<SubCategoryBean> SubCategory;

    public List<SubCategoryBean> getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(List<SubCategoryBean> SubCategory) {
        this.SubCategory = SubCategory;
    }

    public static class SubCategoryBean {
        /**
         * Id : 205
         * SubCatagoryName : Laptops
         * SubCatagoryDiscription : Laptop Prices in India - Buy Laptops online at best prices on ecom.com. Choose wide range of Branded Laptops .Free Shipping, Cash on delivery at India's .
         * CatagoryImage : https://rjtmobile.com/ansari/shopingcart/admin/uploads/sub_category_images/download.jpg
         */

        private String Id;
        private String SubCatagoryName;
        private String SubCatagoryDiscription;
        private String CatagoryImage;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getSubCatagoryName() {
            return SubCatagoryName;
        }

        public void setSubCatagoryName(String SubCatagoryName) {
            this.SubCatagoryName = SubCatagoryName;
        }

        public String getSubCatagoryDiscription() {
            return SubCatagoryDiscription;
        }

        public void setSubCatagoryDiscription(String SubCatagoryDiscription) {
            this.SubCatagoryDiscription = SubCatagoryDiscription;
        }

        public String getCatagoryImage() {
            return CatagoryImage;
        }

        public void setCatagoryImage(String CatagoryImage) {
            this.CatagoryImage = CatagoryImage;
        }
    }
}
