package ru.example.remotetaskmanager;

import android.content.Intent;
import android.os.Bundle;

import ru.example.remotetaskmanager.auth.AuthActivity;
import ru.example.remotetaskmanager.helpers.PreferenceHelper;
import ru.example.remotetaskmanager.navdrawer.NavDrawerActivity;

public class MainActivity extends NavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(PreferenceHelper.getProfile(this).get_id().isEmpty()){
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }
    }
}
