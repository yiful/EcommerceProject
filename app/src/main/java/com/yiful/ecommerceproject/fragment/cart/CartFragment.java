package com.yiful.ecommerceproject.fragment.cart;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.CartItem;
import com.yiful.ecommerceproject.model.Products;
import com.yiful.ecommerceproject.model.User;
import com.yiful.ecommerceproject.network.APIService;
import com.yiful.ecommerceproject.network.ApiClient;
import com.yiful.ecommerceproject.utility.cart.CartItemAdapter;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private Button btnCheckout, btnClear;
    private CartItemAdapter adapter;
    private List<CartItem> cartItems;
    private TextView tvHint;

    private User user;
    private CartItem cartItem;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        tvHint = view.findViewById(R.id.tvHint);
        btnClear = view.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(MainActivity.cartItems.size()==0)tvHint.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new CartItemAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCheckout:
                placeOrderFromCart();
                break;
            case R.id.btnClear:
                MainActivity.cartItems.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Your cart is cleared!", Toast.LENGTH_SHORT).show();
                if(MainActivity.cartItems.size()==0)tvHint.setVisibility(View.VISIBLE);
        }
    }

    private void placeOrderFromCart() {
        for(int i=0; i<MainActivity.cartItems.size(); i++){
            cartItem = MainActivity.cartItems.get(i);
            placeOrderForSingleItem(cartItem);
        }
    }

    private void placeOrderForSingleItem(CartItem cartItem){
        user = MainActivity.getUser();
        Map<String, String> order = new HashMap<>();
        order.put("item_id", cartItem.getProductBean().getId());
        order.put("item_names", cartItem.getProductBean().getProductName());
        order.put("final_price", cartItem.getProductBean().getPrize());
        order.put("item_quantity", cartItem.getQuantity());
        order.put("mobile", user.getUserMobile());
        order.put("api_key", user.getAppApiKey());
        order.put("user_id", user.getUserID());
        Call<String> call = MainActivity.apiService.makeOrder(order);
        final String name = cartItem.getProductBean().getProductName();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().contains("Order Confirmed")){
                    Toast.makeText(getActivity(), "Order confirmed for item(s): "+ name, Toast.LENGTH_SHORT).show();
                    MainActivity.cartItems.clear();
                    adapter.notifyDataSetChanged();
                    if(MainActivity.cartItems.size()==0)tvHint.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(getActivity(), "Error placing order", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("placeOrder", t.toString());
            }
        });
    }
}
