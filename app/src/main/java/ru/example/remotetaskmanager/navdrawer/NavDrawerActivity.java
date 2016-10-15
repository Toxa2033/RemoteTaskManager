package ru.example.remotetaskmanager.navdrawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.helpers.ApiHelper;
import ru.example.remotetaskmanager.helpers.PreferenceHelper;
import ru.example.remotetaskmanager.helpers.UserHolder;
import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.ParentDrawer;
import ru.example.remotetaskmanager.models.User;
import ru.example.remotetaskmanager.utills.Message;
import ru.example.remotetaskmanager.view.LoadingDialog;

public class NavDrawerActivity extends AppCompatActivity {

    private static final int NAVDRAWER_LAUNCH_DELAY = 280;

    protected static final int NAVDRAWER_ITEM_INVALID = -1;
    protected static final int NAVDRAWER_ITEM_ADD_QUESTION = 0;
    protected static final int NAVDRAWER_ITEM_FEEDBACK = 1;
    protected static final int NAVDRAWER_ITEM_TOUR = 2;
    protected static final int NAVDRAWER_ITEM_WRITE_US = 3;
    protected static final int NAVDRAWER_ITEM_ABOUT = 4;
    protected static final int NAVDRAWER_ITEM_LOGOUT = 5;

    // fade in and fade out durations for the main content when switching between
    // different Activities of the app through the Nav Drawer
    private static final int MAIN_CONTENT_FADEOUT_DURATION = 150;

    private static final int MAIN_CONTENT_FADE_IN_DURATION = 200;

    protected Toolbar mToolbar;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    private Handler mHandler;
    private DrawerAdapter drawerAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        loadingDialog=new LoadingDialog(this);
        if(PreferenceHelper.getProfile(this).get_id()!=null) getPC();
    }

    void getPC(){
        LoadingDialog.show(loadingDialog);
        ApiHelper.getInstance(this).getPCs(UserHolder.getInstance(this).getUser().get_id(), new Callback<List<PC>>() {
            @Override
            public void onResponse(Call<List<PC>> call, Response<List<PC>> response) {
                if(!isFinishing()) {
                    if (response.isSuccessful()) {
                        setupNavDrawer(response.body(),null);
                    } else {
                        Message.showToast(response.message(), NavDrawerActivity.this);
                    }
                    LoadingDialog.dismiss(loadingDialog);
                }
            }

            @Override
            public void onFailure(Call<List<PC>> call, Throwable t) {
                if(!isFinishing()){
                    Message.showToast(t.getLocalizedMessage(),NavDrawerActivity.this);
                    LoadingDialog.dismiss(loadingDialog);
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fadeIn();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    protected Toolbar getActionBarToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    private void setupNavDrawer(List<PC>pcs, List<User>users) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (mDrawerLayout == null) {
            return;
        }
        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        NavigationView navDrawer = (NavigationView) mDrawerLayout.findViewById(R.id.drawer_menu);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Check if no view has focus:
                View view = NavDrawerActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        if (navDrawer != null) {
            setupDrawerContent(navDrawer,pcs,users);
        }
    }

    private void setupDrawerContent(final NavigationView navigationView,List<PC>pcs,List<User>users) {
        List<ParentDrawer>listParent=new ArrayList<>();
        ParentDrawer parentDrawer1;
        ParentDrawer parentDrawer2;
        if(pcs!=null) {
             parentDrawer1 = new ParentDrawer(pcs,getString(R.string.pc_title));
            listParent.add(parentDrawer1);
        }
        if(users!=null){
             parentDrawer2 = new ParentDrawer(users,getString(R.string.user_title));
             listParent.add(parentDrawer2);
        }

        RecyclerView drawerList = (RecyclerView)navigationView.findViewById(R.id.drawer_recycler);

        drawerList.setLayoutManager(new LinearLayoutManager(this));
        drawerAdapter = new DrawerAdapter(this, listParent);
        drawerList.setAdapter(drawerAdapter);

    }



    void openGroup(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    protected void goToNavDrawerItem(int position){
        switch (position){
            case NAVDRAWER_ITEM_ADD_QUESTION:

                break;
            case NAVDRAWER_ITEM_FEEDBACK:

                break;
            case NAVDRAWER_ITEM_TOUR:
                break;
            case NAVDRAWER_ITEM_WRITE_US:

                break;
            case NAVDRAWER_ITEM_ABOUT:
                break;
            case NAVDRAWER_ITEM_LOGOUT:
                  break;

        }
    }



    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected void setToolbarClickDrawer(final boolean isDrawer){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDrawer) {
                    openNavDrawer();
                } else {
                    onBackPressed();
                }
            }
        });
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    protected void openNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * Enables back navigation for activities that are launched from the NavBar. See
     * {@code AndroidManifest.xml} to find out the parent activity names for each activity.
     * @param intent
     */
    public void createBackStack(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(this);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();
        } else {
            startActivity(intent);
            finish();
        }
    }

    protected void fadeIn(){
        View mainContent = findViewById(R.id.mainContent);
        if (mainContent != null) {
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADE_IN_DURATION);
        }
    }

}

