package com.yiful.ecommerceproject.utility.cart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.fragment.cart.CartFragment;
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
    private AdapterInterface adapterInterface;
    public CartItemAdapter(Context context, AdapterInterface adapterInterface){
        this.context = context;
        cartItems = MainActivity.cartItems;
        this.adapterInterface = adapterInterface;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_item,parent,false);
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
        private Button btnUp, btnDown;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivCart);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            tvName = itemView.findViewById(R.id.tvNameCart);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuan = itemView.findViewById(R.id.tvQuan);
            cardView = itemView.findViewById(R.id.card_view);
            btnDown = itemView.findViewById(R.id.btnDown);
            btnUp = itemView.findViewById(R.id.btnUp);
            btnUp.setOnClickListener(this);
            btnDown.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnUp:
                    CartItem cartItem = cartItems.get(getPosition());
                    String qunCur = cartItem.getQuantity();
                    int qunNew = Integer.parseInt(qunCur)+1;
                    if(qunNew <= Integer.parseInt(cartItems.get(getPosition()).getProductBean().getQuantity())) {
                        cartItem.setQuantity(String.valueOf(qunNew));
                        tvQuan.setText(String.valueOf(Integer.parseInt(tvQuan.getText().toString())+1));
                        adapterInterface.updateTotalAmount();
                    }else {
                        Toast.makeText(context, "Maximum value already!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnDown:
                    cartItem = cartItems.get(getPosition());
                    qunCur = cartItem.getQuantity();
                    qunNew = Integer.parseInt(qunCur)-1;
                    if(qunNew > 0) {
                        cartItem.setQuantity(String.valueOf(qunNew));
                        notifyDataSetChanged();
                        adapterInterface.updateTotalAmount();
                    }else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Do you want to remove this item from your cart?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cartItems.remove(getPosition());
                                notifyItemRemoved(getPosition());
                                adapterInterface.updateTotalAmount();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                     //   Toast.makeText(context, "Its the maximum value already!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    public void showAlert(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public interface AdapterInterface{
        void updateTotalAmount();
    }

}
