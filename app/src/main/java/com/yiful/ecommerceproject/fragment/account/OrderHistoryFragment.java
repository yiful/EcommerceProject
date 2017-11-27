package com.yiful.ecommerceproject.fragment.account;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.OrderHistory;
import com.yiful.ecommerceproject.model.User;
import com.yiful.ecommerceproject.utility.account.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {
    private ListView listView;
    private OrderAdapter adapter;
    private List<OrderHistory.OrderHistoryBean> orderList;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  getActivity().setTitle("Order History");
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        listView = view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        orderList = new ArrayList<>();
        User user = MainActivity.getUser();
        Call<OrderHistory> call = MainActivity.apiService.getOrderHistory(
                user.getUserMobile(), user.getAppApiKey(), user.getUserID());
        call.enqueue(new Callback<OrderHistory>() {
            @Override
            public void onResponse(Call<OrderHistory> call, Response<OrderHistory> response) {
                orderList = response.body().getOrderHistoryBeanList();
                adapter = new OrderAdapter(getActivity(), R.layout.order_list_item, orderList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<OrderHistory> call, Throwable t) {

            }
        });
    }


}
