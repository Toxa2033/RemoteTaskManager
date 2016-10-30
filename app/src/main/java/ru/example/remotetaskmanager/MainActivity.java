package ru.example.remotetaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import ru.example.remotetaskmanager.auth.AuthActivity;
import ru.example.remotetaskmanager.helpers.PreferenceHelper;
import ru.example.remotetaskmanager.helpers.SelectPcHolder;
import ru.example.remotetaskmanager.interfaces.SelectNavDrawerListner;
import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.User;
import ru.example.remotetaskmanager.navdrawer.NavDrawerActivity;
import ru.example.remotetaskmanager.stopProcess.StopProcessFragment;

public class MainActivity extends NavDrawerActivity implements SelectNavDrawerListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelectPcHolder.getInstance(this).setSelectNavDrawListner(this);
        if(PreferenceHelper.getProfile(this).get_id().isEmpty()){
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContent,new StopProcessFragment()).commitAllowingStateLoss();
    }

    @Override
    public void onSelectPc(PC pc) {

    }

    @Override
    public void onSelectUser(User user) {

    }
}
