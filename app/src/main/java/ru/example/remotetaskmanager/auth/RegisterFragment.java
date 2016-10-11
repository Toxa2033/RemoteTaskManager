package ru.example.remotetaskmanager.auth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.models.User;
import ru.example.remotetaskmanager.utills.Message;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private View tvLogin;
    private EditText etLogin;

    public RegisterFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register, container, false);
        setupUI(view);
        setClickListeners();
        return view;
    }

    private void setupUI(View view){
        tvLogin = view.findViewById(R.id.tvLogin);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) view.findViewById(R.id.etPasswordConfirm);
        etLogin =(EditText)view.findViewById(R.id.etLogin);
    }


    private void setClickListeners() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etEmail.getText().toString().equals("")) {
                    if (etEmail.getText().toString().contains("@")) {
                        if (!etLogin.getText().toString().equals("")) {
                            if (!etPassword.getText().toString().equals("")) {
                                if (passwordsMatch()) {
                                    User user = new User();
                                    user.setEmail(etEmail.getText().toString());
                                    user.setPassword(etPassword.getText().toString());
                                    user.setLogin(etLogin.getText().toString());
                                    ((AuthActivity)getActivity()).register(user);
                                } else {
                                    Message.showToast(getString(R.string.passwords_dosent_match), getContext());
                                }
                            } else {
                                Message.showToast(getString(R.string.hint_password), getContext());
                            }
                        } else {
                            Message.showToast(getString(R.string.hint_login), getContext());
                        }
                    } else {
                        Message.showToast(getString(R.string.hint_email), getContext());
                    }
                } else {
                    Message.showToast(getString(R.string.hint_email), getContext());
                }
            }

        });
    }

    boolean passwordsMatch()
    {
        return etPassword.getText().toString().equals(etPasswordConfirm.getText().toString());
    }

}
