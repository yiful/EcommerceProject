package com.yiful.ecommerceproject.model;

import java.util.List;

/**
 * Created by Yifu on 11/25/2017.
 */

public class Categories {

    private List<CategoryBean> Category;

    public List<CategoryBean> getCategory() {
        return Category;
    }

    public void setCategory(List<CategoryBean> Category) {
        this.Category = Category;
    }

    public static class CategoryBean {
        /**
         * Id : 107
         * CatagoryName : Electronics
         * CatagoryDiscription : Online directory of electrical goods manufacturers, electronic goods suppliers and electronic product manufacturers. Get details of electronic products.
         * CatagoryImage : https://rjtmobile.com/ansari/shopingcart/admin/uploads/category_images/images.jpg
         */

        private String Id;
        private String CatagoryName;
        private String CatagoryDiscription;
        private String CatagoryImage;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getCatagoryName() {
            return CatagoryName;
        }

        public void setCatagoryName(String CatagoryName) {
            this.CatagoryName = CatagoryName;
        }

        public String getCatagoryDiscription() {
            return CatagoryDiscription;
        }

        public void setCatagoryDiscription(String CatagoryDiscription) {
            this.CatagoryDiscription = CatagoryDiscription;
        }

        public String getCatagoryImage() {
            return CatagoryImage;
        }

        public void setCatagoryImage(String CatagoryImage) {
            this.CatagoryImage = CatagoryImage;
        }

    }
}
