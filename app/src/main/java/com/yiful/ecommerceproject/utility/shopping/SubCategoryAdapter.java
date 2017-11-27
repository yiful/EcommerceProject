package com.yiful.ecommerceproject.utility.shopping;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.yiful.ecommerceproject.fragment.shopping.ProductFragment;
import com.yiful.ecommerceproject.model.SubCategories;

import java.util.List;

/**
 * Created by Yifu on 11/25/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>{
    private Context context;
    private List<SubCategories.SubCategoryBean> categoryBeanList;
    public SubCategoryAdapter(Context context, List<SubCategories.SubCategoryBean> list){
        this.context = context;
        categoryBeanList = list;
    }
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SubCategoryAdapter.ViewHolder holder, int position) {
        holder.tvHeading.setText(categoryBeanList.get(position).getSubCatagoryName());
        holder.tvDesc.setText(categoryBeanList.get(position).getSubCatagoryDiscription());
        Picasso.with(context).load(categoryBeanList.get(position).getCatagoryImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView tvHeading;
        public TextView tvDesc;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivCart);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            cardView = itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ProductFragment fragment = ProductFragment.newInstance(categoryBeanList.get(getPosition()));
            Activity activity = (Activity)context;
            FragmentManager manager = activity.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragmentContainer, fragment, "product");
            transaction.addToBackStack("addProduct");
            transaction.commit();
        }
    }
}
