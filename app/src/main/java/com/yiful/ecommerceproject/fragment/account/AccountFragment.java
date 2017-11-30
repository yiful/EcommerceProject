package com.yiful.ecommerceproject.fragment.account;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private List<String> settingList;
    private TextView tvName, tvMobile, tvEmail;
    private ListView listView;
    private Button btnLogout;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingList = new ArrayList<>();
        settingList.add("Order History");
        settingList.add("Reset Password");
        settingList.add("Settings");
        settingList.add("Send Feedback");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        User user = MainActivity.getUser();
        tvName = view.findViewById(R.id.tvName);
        tvName.setText(user.getUserName());
        tvMobile = view.findViewById(R.id.tvMobile);
        tvMobile.setText(user.getUserMobile());
        tvEmail = view.findViewById(R.id.tvEmail);
        tvEmail.setText(user.getUserEmail());
        listView = view.findViewById(R.id.listView);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, settingList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (i){
                    case 0:
                        OrderHistoryFragment fragment = new OrderHistoryFragment();

                                transaction.add(R.id.fragmentContainer, fragment, "orderHistory");
                                transaction.addToBackStack("addOrderHistory");
                                transaction.commit();
                                break;
                    case 1:
                        ResetPasswordFragment fragment1 = new ResetPasswordFragment();
                        transaction.add(R.id.fragmentContainer, fragment1, "resetPw");
                        transaction.addToBackStack("addResetPw");
                        transaction.commit();
                }
            }
        });
        return view;
    }

}
