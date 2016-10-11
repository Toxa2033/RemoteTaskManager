package ru.example.remotetaskmanager.utills;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 95tox on 11.10.2016.
 */

public class Message {
    public static void showToast(String msg, Context context){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
