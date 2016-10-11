package ru.example.remotetaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.example.remotetaskmanager.auth.AuthActivity;
import ru.example.remotetaskmanager.helpers.PreferenceHelper;

public class MainActivity extends AppCompatActivity {

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
