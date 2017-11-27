package com.yiful.ecommerceproject.fragment.account;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yiful.ecommerceproject.R;
import com.yiful.ecommerceproject.activity.MainActivity;
import com.yiful.ecommerceproject.model.ResetPwResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {
    private EditText etOld, etNew1, etNew2;
    private Button button;
    private AlertDialog alertDialog;
    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        alertDialog = new AlertDialog.Builder(getActivity()).create();

        etOld = view.findViewById(R.id.etOldPw);
        etNew1 = view.findViewById(R.id.etNew1);
        etNew2 = view.findViewById(R.id.etNew2);
        button = view.findViewById(R.id.btnConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPw = etOld.getText().toString();
                String newPw1 = etNew1.getText().toString();
                String newPw2 = etNew2.getText().toString();
                if(!newPw1.equals(newPw2)){
                    alertShowMsg("New passwords don't match!");
                    return;
                }
                Call<ResetPwResponse> call = MainActivity.apiService.getResetPwResponse(
                        MainActivity.getUser().getUserMobile(),oldPw,newPw1);
                call.enqueue(new Callback<ResetPwResponse>() {
                    @Override
                    public void onResponse(Call<ResetPwResponse> call, Response<ResetPwResponse> response) {
                        String msg = response.body().getMsg().get(0);
                        alertShowMsg(msg);
                        etOld.setText("");
                        etNew1.setText("");
                        etNew2.setText("");
                    }

                    @Override
                    public void onFailure(Call<ResetPwResponse> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }

    private void submitNewPw() {

    }

    private void alertShowMsg(String msg){
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
