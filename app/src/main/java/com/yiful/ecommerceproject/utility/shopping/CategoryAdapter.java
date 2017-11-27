package com.yiful.ecommerceproject.utility.shopping;

import android.app.Activity;
import android.app.Fragment;
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
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.fragment.shopping.ShopSub1Fragment;
import com.yiful.ecommerceproject.model.Categories;

import java.util.List;

/**
 * Created by Yifu on 11/25/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Categories.CategoryBean> categoryList;
    private Context context;

    public CategoryAdapter(Context context, List<Categories.CategoryBean> list){
        this.context = context;
        categoryList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categories.CategoryBean category = categoryList.get(position);
        holder.tvHeading.setText(category.getCatagoryName());
        holder.tvDescription.setText(category.getCatagoryDiscription());
        Picasso.with(context)
                .load(category.getCatagoryImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvHeading;
        public TextView tvDescription;
        public ImageView imageView;
        public CardView cardView;
        private ViewHolder(View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.ivCart);
            cardView = itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           // Toast.makeText(context, "clicked!! "+getPosition(), Toast.LENGTH_SHORT).show();
            Fragment fragment = ShopSub1Fragment.newInstance(categoryList.get(getPosition()), MainActivity.getUser());
            Activity activity = (Activity) context;

            FragmentManager manager = activity.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragmentContainer,fragment,"subCategory");
            transaction.addToBackStack("addSubCategory");
            transaction.commit();
            //fragmentTran.replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
        }
    }
}
