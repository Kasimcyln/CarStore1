package com.kasimkartal866.carstore;

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
import com.kasimkartal866.carstore.common.Utilities;
import com.kasimkartal866.carstore.db.Dao;
import com.kasimkartal866.carstore.db.User;


public class RegisterFragment extends Fragment {
    View view;
    private EditText etMail;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnSave;
    Dao dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_register, container, false);
        dao = App.dao;
        findViewByIdMethods();
        onClickMethods();
        return view;
    }

    public void findViewByIdMethods() {
        etMail = view.findViewById(R.id.etMail);
        etPhone = view.findViewById(R.id.etPhone);
        etPassword = view.findViewById(R.id.etPassword);
        etPassword2 = view.findViewById(R.id.etPassword2);
        btnSave = view.findViewById(R.id.btnSave);
    }

    public void onClickMethods () {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean state = false;
                if (TextUtils.isEmpty(etMail.getText().toString()) ) {
                    etMail.setError("enter e-mail");
                    state = true;
                }
                if (!Utilities.isValidEmail(etMail.getText().toString()) ) {
                    etMail.setError("enter .gmail.com");
                    state = true;
                }
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    etPhone.setError("enter phone");
                    state = true;
                }

                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    etPassword.setError("enter password");
                    state = true;
                }
                if (!Utilities.isValidPassword(etPassword.getText().toString())) {
                    etPassword.setError("must contain uppercase, lowercase, numbers and punctuation marks");
                    state = true;
                }
                if (TextUtils.isEmpty(etPassword2.getText().toString())) {
                    etPassword2.setError("try enter password");
                    state = true;
                } else if (!etPassword2.getText().toString().contentEquals(etPassword.getText().toString())) {
                    etPassword2.setError("passwords are incompatible");
                    state = true;
                }
                if (state){
                    return;
                }

                User user = new User();
                user.setEmail(etMail.getText().toString());
                user.setPhone(etPhone.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setPassword2(etPassword2.getText().toString());
                dao.addUser(user);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(App.getContext(), "User Registered", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
    }


}