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
public class LoginFragment extends Fragment {

    private EditText etEmail;
    private EditText etPassword;
    private View tvLogin;
    private View tvForgot;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        setupUI(view);
        setClickListeners();
        return view;
    }

    private void setupUI(View view){
        tvLogin = view.findViewById(R.id.tvLogin);
        tvForgot = view.findViewById(R.id.tvForgot);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

    }

    private void setClickListeners(){
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etEmail.getText().toString().equals("")) {
                    //if (etEmail.getText().toString().contains("@")) {
                        if (!etPassword.getText().toString().equals("")) {
                            User user = new User();
                            user.setEmail(etEmail.getText().toString());
                            user.setPassword(etPassword.getText().toString());
                            user.setLogin(etEmail.getText().toString());
                            ((AuthActivity)getActivity()).login(user);
                        } else {
                            Message.showToast(getString(R.string.hint_password), getContext());
                        }
                    } else {
                        Message.showToast(getString(R.string.login_email), getContext());
                    }
              //  } else {
                 //   Message.showToast(getString(R.string.login_email), getContext());
             //   }

            }
        });
    }

}
