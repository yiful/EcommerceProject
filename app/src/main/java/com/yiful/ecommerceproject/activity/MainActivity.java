package com.yiful.ecommerceproject.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.fragment.account.AccountFragment;
import com.yiful.ecommerceproject.fragment.cart.CartFragment;
import com.yiful.ecommerceproject.fragment.shopping.ShoppingFragment;
import com.yiful.ecommerceproject.model.CartItem;
import com.yiful.ecommerceproject.model.User;
import com.yiful.ecommerceproject.network.APIService;
import com.yiful.ecommerceproject.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String FRAGMENT_SHOP = "shopping fragment";
    private static final String FRAGMENT_CART = "cart fragment";
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    static User user;
    public static List<CartItem> cartItems;
    public static APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getParcelableExtra("userInfo");
        apiService = ApiClient.getClient().create(APIService.class);
        cartItems = new ArrayList<>();

        Log.i(TAG,"user parcel: "+user.getAppApiKey());

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.shopping:
                        if(getFragmentManager().findFragmentByTag(FRAGMENT_SHOP) != null){
                            fragment = getFragmentManager().findFragmentByTag(FRAGMENT_SHOP);
                            Log.i(TAG, "reload fragment shop");
                        }else {
                            fragment = ShoppingFragment.newInstance(user);
                        }
                        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, FRAGMENT_SHOP).commit();
                        break;
                    case R.id.cart:
                        if(getFragmentManager().findFragmentByTag(FRAGMENT_CART) != null){
                            fragment = getFragmentManager().findFragmentByTag(FRAGMENT_CART);
                            Log.i(TAG, "reload fragment cart");
                        }else {
                            fragment = new CartFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, FRAGMENT_CART).commit();
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.shopping);
    }

    public static User getUser(){
        return user;
    }

}
