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
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.Products;
import com.yiful.ecommerceproject.model.SubCategories;
import com.yiful.ecommerceproject.network.APIService;
import com.yiful.ecommerceproject.network.ApiClient;
import com.yiful.ecommerceproject.utility.shopping.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    private List<Products.ProductBean> productBeanList;
    static private SubCategories.SubCategoryBean subCategoryBean;
    private RecyclerView recyclerView;
    private TextView tvTitle;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(SubCategories.SubCategoryBean subCategoryBean){
        ProductFragment fragment = new ProductFragment();
        ProductFragment.subCategoryBean = subCategoryBean;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvTitle = view.findViewById(R.id.title);
        tvTitle.setText(subCategoryBean.getSubCatagoryName());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        productBeanList = new ArrayList<>();
        APIService apiService = ApiClient.getClient().create(APIService.class);
        Call<Products> call = apiService.getProductResponse(subCategoryBean.getId(),
                MainActivity.getUser().getAppApiKey(),
                MainActivity.getUser().getUserID());
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                productBeanList = response.body().getProduct();
                ProductAdapter adapter = new ProductAdapter(getActivity(),productBeanList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });
    }

}
