package ru.example.remotetaskmanager.helpers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ru.example.remotetaskmanager.interfaces.SelectNavDrawerListner;
import ru.example.remotetaskmanager.models.PC;

/**
 * Created by 95tox on 30.10.2016.
 */

public class SelectPcHolder {


    private static SelectPcHolder instance;
    private PC mPc;
    private List<SelectNavDrawerListner>listners;
    private Context mContext;
    private SelectPcHolder(Context context) {
        mContext=context;
    }

    public static SelectPcHolder getInstance(Context context) {
        if (instance == null) {
            instance = new SelectPcHolder(context);
        }
        return instance;
    }

    public void setSelectNavDrawListner(SelectNavDrawerListner listner){
        if(listners==null)
            listners=new ArrayList<>();
        if(listner!=null)
            listners.add(listner);
    }

    public PC getPc() {
        return mPc;
    }

    public void setPc(PC mPc) {
        this.mPc = mPc;
        PreferenceHelper.saveIdSelectPc(mPc.get_id(),mContext);
        if(listners!=null){
            for (SelectNavDrawerListner item:listners){
                item.onSelectPc(mPc);
            }
        }
    }
}
