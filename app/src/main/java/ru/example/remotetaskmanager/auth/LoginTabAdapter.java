package ru.example.remotetaskmanager.auth;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.example.remotetaskmanager.R;

/**
 * Created by 95tox on 11.10.2016.
 */

class LoginTabAdapter extends FragmentPagerAdapter {

     private Context mContext;

     LoginTabAdapter(FragmentManager fm, Context context) {
        super(fm);
         mContext=context;
    }

    /**
     * Return fragment with respect to Position .
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new LoginFragment();
            case 1 : return new RegisterFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * This method returns the title of the tab according to the position.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return mContext.getString(R.string.label_login);
            case 1 :
                return mContext.getString(R.string.label_register);
        }
        return null;
    }
}
