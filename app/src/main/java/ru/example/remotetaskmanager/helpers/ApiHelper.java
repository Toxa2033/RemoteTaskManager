package ru.example.remotetaskmanager.helpers;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.models.User;
import ru.example.remotetaskmanager.utills.NetworkStateChecker;

/**
 * Created by 95tox on 11.10.2016.
 */

public class ApiHelper {

    private static ApiHelper instance;

    private Context context;
    private Api api;
    private ApiHelper(Context context){
        this.context = context;

        api = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);

    }

    public static ApiHelper getInstance(Context context){
        if (instance == null) {
            instance = new ApiHelper(context.getApplicationContext());
        }
        return instance;
    }

    private interface Api {

        @POST("authorization/register")
        Call<User> register(@Body User user);

        @POST("authorization/")
        Call<User> login(@Body User user);
    }

    public void login(User user,Callback<User> callback)
    {
        if(NetworkStateChecker.isInternetOn(context)) api.login(user).enqueue(callback);
    }

    public void register(User user,Callback<User> callback)
    {
        if(NetworkStateChecker.isInternetOn(context)) api.register(user).enqueue(callback);
    }
}
