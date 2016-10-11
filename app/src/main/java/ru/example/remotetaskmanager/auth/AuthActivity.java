package ru.example.remotetaskmanager.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.example.remotetaskmanager.MainActivity;
import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.helpers.ApiHelper;
import ru.example.remotetaskmanager.helpers.PreferenceHelper;
import ru.example.remotetaskmanager.models.User;
import ru.example.remotetaskmanager.utills.Message;
import ru.example.remotetaskmanager.view.LoadingDialog;

public class AuthActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private LoadingDialog loadingDialog;
    public TabLayout tabLayout;
    public ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setupUI();
        setupViewPagerAndTabs();
        getActionBarToolbar();
    }


    private void setupUI(){
        loadingDialog=new LoadingDialog(this);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void setupViewPagerAndTabs(){
        viewPager.setAdapter(new LoginTabAdapter(getSupportFragmentManager(),this));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    private Toolbar getActionBarToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    public void login(User user){
        LoadingDialog.show(loadingDialog);
        ApiHelper.getInstance(this).login(user, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                signIn(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                LoadingDialog.dismiss(loadingDialog);
                if(!isFinishing()) Message.showToast(t.getLocalizedMessage(),AuthActivity.this);
            }
        });
    }


    public void register(User user){
        LoadingDialog.show(loadingDialog);
        ApiHelper.getInstance(this).register(user, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                 signIn(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if(!isFinishing()) {
                    LoadingDialog.dismiss(loadingDialog);
                    Message.showToast(t.getLocalizedMessage(),AuthActivity.this);
                }
            }
        });
    }



    void signIn(Response<User>response){
        if(!isFinishing()) {
            LoadingDialog.dismiss(loadingDialog);
            if (response.isSuccessful()) {
                PreferenceHelper.saveProfile(AuthActivity.this,response.body());
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
                finish();
            } else {
                Message.showToast(response.message(), AuthActivity.this);
            }
        }
    }
}
