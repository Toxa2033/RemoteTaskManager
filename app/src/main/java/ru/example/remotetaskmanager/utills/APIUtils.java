package ru.example.remotetaskmanager.utills;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.example.remotetaskmanager.helpers.ApiHelper;
import ru.example.remotetaskmanager.helpers.UserHolder;
import ru.example.remotetaskmanager.interfaces.ApiListner;
import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.Task;

/**
 * Created by 95tox on 30.10.2016.
 */

public class APIUtils {

    private static APIUtils instance;
    private Context mContext;
    private APIUtils(Context context){
        mContext=context;
    }

    public static APIUtils getInstance(Context context){
        if(instance==null) instance=new APIUtils(context);
        return instance;
    }

  public void getPC(final ApiListner listner){
        ApiHelper.getInstance(mContext).getPCs(UserHolder.getInstance(mContext).getUser().get_id(), new Callback<List<PC>>() {
            @Override
            public void onResponse(Call<List<PC>> call, Response<List<PC>> response) {
               responsed(listner,response);
            }

            @Override
            public void onFailure(Call<List<PC>> call, Throwable t) {
                if(listner!=null) listner.onError(t.getLocalizedMessage());
            }

        });
    }

    public void addTask(Task task, final ApiListner listner){
        ApiHelper.getInstance(mContext).addTask(task, new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                responsed(listner,response);
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                if(listner!=null) listner.onError(t.getLocalizedMessage());
            }
        });
    }

 private  void responsed(ApiListner listner, Response<?>response){
        if(response.isSuccessful()){
            if(listner!=null)  listner.onLoad(response.body());
        } else {
            if(listner!=null) listner.onError(response.message());
        }
    }

}
