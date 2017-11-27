package com.yiful.ecommerceproject.utility.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.OrderHistory;
import com.yiful.ecommerceproject.model.OrderStatus;
import com.yiful.ecommerceproject.model.User;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yifu on 11/26/2017.
 */

public class OrderAdapter extends ArrayAdapter<OrderHistory.OrderHistoryBean> {
    private Context context;
    private List<OrderHistory.OrderHistoryBean> orderHistoryBeanList;

    private Button btnTrack;

    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<OrderHistory.OrderHistoryBean> objects) {
        super(context, resource, objects);
        orderHistoryBeanList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tvName, tvOrderId, tvPrice, tvQuan;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.order_list_item, null);
        OrderHistory.OrderHistoryBean order = orderHistoryBeanList.get(position);
        tvName = view.findViewById(R.id.tvName);
        tvOrderId = view.findViewById(R.id.tvOrderId);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvQuan = view.findViewById(R.id.tvQuan);
        btnTrack = view.findViewById(R.id.button);
        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDataFromApi(position);
            }
        });
        tvName.setText(order.getItemName());
        tvOrderId.setText(order.getOrderID());
        tvPrice.setText(order.getFinalPrice());
        tvQuan.setText(order.getItemQuantity());
        return view;
    }

    private void loadDataFromApi(int position) {
        User user = MainActivity.getUser();
        Call<List<OrderStatus>> call = MainActivity.apiService.getOrderStatus(
                orderHistoryBeanList.get(position).getOrderID(),user.getAppApiKey(),user.getUserID());
        call.enqueue(new Callback<List<OrderStatus>>() {
            @Override
            public void onResponse(Call<List<OrderStatus>> call, Response<List<OrderStatus>> response) {
                Log.i("status","sucess");
                String[] msg = {"Order confirmed.", "Order dispatched.", "Order on the way.", "Order delivered."};
                Log.i("status", response.body().get(0).getOrderStatus());
                String orderStatus = msg[Integer.valueOf(response.body().get(0).getOrderStatus())-1];

                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Order Status");
                alertDialog.setMessage(orderStatus);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<List<OrderStatus>> call, Throwable t) {
                Log.i("status",t.toString());
            }
        });
    }
}
