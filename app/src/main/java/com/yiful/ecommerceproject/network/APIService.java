package com.yiful.ecommerceproject.network;

import com.yiful.ecommerceproject.model.Categories;
import com.yiful.ecommerceproject.model.ForgotPwResponse;
import com.yiful.ecommerceproject.model.OrderHistory;
import com.yiful.ecommerceproject.model.OrderStatus;
import com.yiful.ecommerceproject.model.Products;
import com.yiful.ecommerceproject.model.ResetPwResponse;
import com.yiful.ecommerceproject.model.SubCategories;
import com.yiful.ecommerceproject.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Yifu on 11/24/2017.
 */

public interface APIService {
    //login call
    @GET("shop_login.php")
    Call<List<User>> getLoginResponse(@Query("mobile") String mobile,
                                      @Query("password") String password);
    //register call
    @GET("shop_reg.php")
    Call<String> getRegisterResponse(@Query("name") String name,
                                     @Query("email") String email,
                                     @Query("mobile") String mobile,
                                     @Query("password") String password);
    //category call
    @GET("cust_category.php")
    Call<Categories> getCategoryResponse(@Query("api_key") String api_key,
                                         @Query("user_id") String user_id);
    //sub category call
    @GET("cust_sub_category.php")
    Call<SubCategories> getSubCategoryResponse(@Query("Id") String id,
                                               @Query("api_key") String api_key,
                                               @Query("user_id") String user_id);
    //product call
    @GET("cust_product.php")
    Call<Products> getProductResponse(@Query("Id") String Id,
                                      @Query("api_key") String api_key,
                                      @Query("user_id") String user_id);

    //order call
    @GET("orders.php")
    Call<String> makeOrder(@QueryMap Map<String, String> options);

    //order history
    @GET("order_history.php")
    Call<OrderHistory> getOrderHistory(@Query("mobile") String mobile,
                                       @Query("api_key") String api_key,
                                       @Query("user_id") String user_id);
    @GET("order_track.php")
    Call<List<OrderStatus>> getOrderStatus(@Query("order_id") String order_id,
                                     @Query("api_key") String api_key,
                                     @Query("user_id") String user_id);

    @GET("shop_reset_pass.php")
    Call<ResetPwResponse> getResetPwResponse(@Query("mobile") String mobile,
                                             @Query("password") String password,
                                             @Query("newpassword") String newpassword);

    @GET("shop_fogot_pass.php")
    Call<List<ForgotPwResponse>> getForgotPwResponse(@Query("mobile") String mobile);
}
