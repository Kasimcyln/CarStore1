package com.kasimkartal866.carstore;

import static com.kasimkartal866.carstore.common.App.dao;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kasimkartal866.carstore.common.App;
import com.kasimkartal866.carstore.common.MyPref;
import com.kasimkartal866.carstore.db.User;

public class LoginFragment extends Fragment {
    View view;
    private EditText email;
    private EditText pass;
    private Button btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_login, container, false);

        email =view.findViewById(R.id.email);
        pass = view.findViewById(R.id.pass);
        btnLogin = view.findViewById(R.id.btnLogin);
        onClickMethods();




        return view;
    }

    public  void onClickMethods () {
        btnLogin.setOnClickListener(v -> {

            String emailText = email.getText().toString();
            String passwordText = pass.getText().toString();

            Boolean state = false;
            if (TextUtils.isEmpty(email.getText().toString())) {
                email.setError("enter e-mail");
                state = true;
            } if (TextUtils.isEmpty(pass.getText().toString())) {
                pass.setError("enter password");
                state = true;
            }
            else {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        User user = dao.checkUserPass(emailText, passwordText);
                        if(user == null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(App.getContext(), "Geçersiz Giriş Bilgileri",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            String name = user.getEmail();
                            startActivity(new Intent(getActivity().getApplicationContext(),MainPageActivity.class)
                                    .putExtra("name",name));

                            MyPref.getInstance().saveUserName(name);
                            MyPref.getInstance().saveUserId(user.getId());
                            getActivity().finish();
                        }
                    }
                }).start();
            }
        });


    }
}