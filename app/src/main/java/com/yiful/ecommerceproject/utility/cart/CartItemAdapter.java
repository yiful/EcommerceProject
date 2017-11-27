package com.yiful.ecommerceproject.utility.cart;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.fragment.shopping.ShopCartFragment;
import com.yiful.ecommerceproject.model.CartItem;
import com.yiful.ecommerceproject.model.Products;

import java.util.List;

/**
 * Created by Yifu on 11/26/2017.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private Context context;
    private List<CartItem> cartItems;

    public CartItemAdapter(Context context){
        this.context = context;
        cartItems = MainActivity.cartItems;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Products.ProductBean productBean = cartItems.get(position).getProductBean();
        holder.tvQuan.setText(cartItems.get(position).getQuantity());
        holder.tvPrice.setText(productBean.getPrize());
        holder.tvName.setText(productBean.getProductName());
        holder.tvDesc.setText(productBean.getDiscription());
        Picasso.with(context).load(productBean.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CardView cardView;
        private ImageView imageView;
        private TextView tvName, tvDesc, tvQuan, tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivCart);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            tvName = itemView.findViewById(R.id.tvNameCart);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuan = itemView.findViewById(R.id.tvQuan);
            cardView = itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
