package com.yiful.ecommerceproject.fragment.shopping;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.model.Categories;
import com.yiful.ecommerceproject.model.User;
import com.yiful.ecommerceproject.network.APIService;
import com.yiful.ecommerceproject.network.ApiClient;
import com.yiful.ecommerceproject.utility.shopping.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingFragment extends Fragment {
    private static final String TAG = "shoppingFragment";
    private static User user;
    private RecyclerView recyclerView;
    private List<Categories.CategoryBean> categoryList;
    private CategoryAdapter adapter;

    public ShoppingFragment(){}

    public static ShoppingFragment newInstance(User user){
        ShoppingFragment fragment = new ShoppingFragment();
        ShoppingFragment.user = user;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromApi();

    }

    private void loadDataFromApi() {
        categoryList = new ArrayList<>();
        APIService apiService = ApiClient.getClient().create(APIService.class);
        Call<Categories> call = apiService.getCategoryResponse(user.getAppApiKey(), user.getUserID());
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                categoryList = response.body().getCategory();
//                Log.i(TAG, "success: "+categoryList.get(2).getCatagoryDiscription());
                adapter = new CategoryAdapter(getActivity(), categoryList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.i(TAG, "failed");
            }
        });
    }

}
