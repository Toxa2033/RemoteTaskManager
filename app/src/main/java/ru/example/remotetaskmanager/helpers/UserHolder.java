package ru.example.remotetaskmanager.helpers;

import android.content.Context;

import ru.example.remotetaskmanager.models.User;

/**
 * Created by 95tox on 15.10.2016.
 */

public class UserHolder {

    private static UserHolder instance;
    private User user;

    private UserHolder(Context context) {
        user = PreferenceHelper.getProfile(context);
    }

    public static UserHolder getInstance(Context context) {
        if (instance == null) {
            instance = new UserHolder(context);
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
