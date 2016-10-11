package ru.example.remotetaskmanager.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.utills.NetworkStateChecker;

/**
 * Created by 95tox on 11.10.2016.
 */
public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        setCancelable(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    @Override
    public void show() {
        if(NetworkStateChecker.isInternetOn(getContext())) {
            super.show();
        }
    }

    public static void show(LoadingDialog dialog){
        if(dialog!=null) dialog.show();
    }

    public static void dismiss(LoadingDialog dialog){
        if(dialog!=null) dialog.dismiss();
    }
}
