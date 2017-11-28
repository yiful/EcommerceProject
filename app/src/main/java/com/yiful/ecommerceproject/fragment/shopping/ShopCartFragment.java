package com.yiful.ecommerceproject.fragment.shopping;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.CartItem;
import com.yiful.ecommerceproject.model.Products;

import java.util.ArrayList;

/**
 * Created by Yifu on 11/26/2017.
 */

public class ShopCartFragment extends DialogFragment implements View.OnClickListener{
    private static Products.ProductBean productBean;
    private ImageView imageView;
    private TextView tvAsk, tvName, tvQuan;
    private Button btnCancel, btnYes;
    private Spinner spinner;

    public ShopCartFragment(){}
    public static ShopCartFragment newInstance(Products.ProductBean productBean){
        ShopCartFragment fragment = new ShopCartFragment();
        ShopCartFragment.productBean = productBean;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart, container,false);
        imageView = view.findViewById(R.id.ivCart);
        tvName = view.findViewById(R.id.tvNameCart);
        spinner = view.findViewById(R.id.spinner);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnYes = view.findViewById(R.id.btnYes);
        addSpinner(productBean.getQuantity());
        btnCancel.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        Picasso.with(getActivity()).load(productBean.getImage()).into(imageView);
        tvName.setText(productBean.getProductName());
        return view;
    }

    private void addSpinner(String quantity) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i<=Integer.valueOf(quantity); i++){
            list.add(""+i);
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnYes:
                String quantity = spinner.getSelectedItem().toString();
                if(quantity.equals("0")){
                    Toast.makeText(getActivity(), "Please select a quantity", Toast.LENGTH_SHORT).show();
                    return;
                }
                //add to cart
                CartItem cartItem = new CartItem(productBean, quantity);
                MainActivity.cartItems.add(cartItem);
                Toast.makeText(getActivity(),
                        quantity+" "+productBean.getProductName()+" added!",
                        Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }
}
