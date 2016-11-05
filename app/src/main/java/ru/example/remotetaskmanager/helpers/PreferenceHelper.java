package ru.example.remotetaskmanager.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.example.remotetaskmanager.models.User;

/**
 * Created by 95tox on 11.10.2016.
 */

public class PreferenceHelper {
    private static final String PREF_USER_EMAIL = "pref_user_email";

    private static final String PREF_USER_LOGIN = "pref_user_login";

    private static final String PREF_USER_ID = "pref_user_id";

    private static final String PREF_USER_TOKEN = "pref_user_token";


    public static final String PREF_USER_PASSWORD="pref_user_password";

    private static final String PREF_SELECTED_PC="pref_select_pc";


    public static void saveProfile(final Context context, User user) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        sp.edit().putString(PREF_USER_ID, user.get_id()).apply();
        sp.edit().putString(PREF_USER_EMAIL, user.getEmail()).apply();
        sp.edit().putString(PREF_USER_LOGIN, user.getLogin()).apply();
        sp.edit().putString(PREF_USER_PASSWORD,user.getPassword()).apply();
        sp.edit().putString(PREF_USER_TOKEN,user.getToken()).apply();
    }


    public static User getProfile(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        User user = new User();
        user.setEmail(sp.getString(PREF_USER_EMAIL, ""));
        user.setLogin(sp.getString(PREF_USER_LOGIN, ""));
        user.set_id(sp.getString(PREF_USER_ID, ""));
        user.setPassword(sp.getString(PREF_USER_PASSWORD,""));
        user.setToken(sp.getString(PREF_USER_TOKEN,""));
        return user;
    }

    public static void clearProfile(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(PREF_USER_ID).apply();
        sp.edit().remove(PREF_USER_EMAIL).apply();
        sp.edit().remove(PREF_USER_LOGIN).apply();
        sp.edit().remove(PREF_USER_PASSWORD).apply();
        sp.edit().remove(PREF_USER_TOKEN).apply();
    }

    public static void saveIdSelectPc(String pcId, Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        sp.edit().putString(PREF_SELECTED_PC, pcId).apply();
    }

    public static String getIdSelectPc(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

       return sp.getString(PREF_SELECTED_PC,"");
    }
}
