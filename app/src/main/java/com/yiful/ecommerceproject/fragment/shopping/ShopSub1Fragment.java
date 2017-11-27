package com.yiful.ecommerceproject.fragment.shopping;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.model.Categories;
import com.yiful.ecommerceproject.model.SubCategories;
import com.yiful.ecommerceproject.model.User;
import com.yiful.ecommerceproject.network.APIService;
import com.yiful.ecommerceproject.network.ApiClient;
import com.yiful.ecommerceproject.utility.shopping.SubCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopSub1Fragment extends Fragment {
    private List<SubCategories.SubCategoryBean> subCategories;
    static private Categories.CategoryBean category;

    static private User user;

    private RecyclerView recyclerView;
    private TextView tvTitle;
    public ShopSub1Fragment() {
        // Required empty public constructor
    }
    public static ShopSub1Fragment newInstance(Categories.CategoryBean category, User user){
        ShopSub1Fragment fragment = new ShopSub1Fragment();
        ShopSub1Fragment.category = category;
        ShopSub1Fragment.user = user;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_sub1, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvTitle = view.findViewById(R.id.title);
        tvTitle.setText(category.getCatagoryName());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        subCategories = new ArrayList<>();
        APIService apiService = ApiClient.getClient().create(APIService.class);
        Call<SubCategories> call = apiService.getSubCategoryResponse(category.getId(), user.getAppApiKey(), user.getUserID());
        call.enqueue(new Callback<SubCategories>() {
            @Override
            public void onResponse(Call<SubCategories> call, Response<SubCategories> response) {
                subCategories = response.body().getSubCategory();
                SubCategoryAdapter adapter = new SubCategoryAdapter(getActivity(), subCategories);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<SubCategories> call, Throwable t) {

            }
        });
    }

}
