package com.yiful.ecommerceproject.model;

/**
 * Created by Yifu on 11/26/2017.
 */

public class ForgotPwResponse {
    /**
     * msg : User Mobile Number and Password
     * UserMobile : 3134478120
     * UserPassword : 123
     */

    private String msg;
    private String UserMobile;
    private String UserPassword;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String UserMobile) {
        this.UserMobile = UserMobile;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String UserPassword) {
        this.UserPassword = UserPassword;
    }
}
