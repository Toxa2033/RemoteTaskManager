package ru.example.remotetaskmanager.utills;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class NetworkStateReceiver extends BroadcastReceiver {
    static List<Listener> listeners = new ArrayList<>();

    public interface Listener {
        void onStatusConnected();
        void onStatusDisconnected();
    }

    public static void setListener(Listener listener) {
        if (!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public static void removeListener(Listener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {

        int status = NetworkStateChecker.getConnectivityStatusString(context);
        if (!"android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            if(status==NetworkStateChecker.NETWORK_STATUS_NOT_CONNECTED){
                if(listeners!=null) {
                    for (int i=listeners.size()-1; i>=0; i--) {
                        listeners.get(i).onStatusDisconnected();
                    }
                }
            }else{
                if(listeners!=null) {
                    for (int i=listeners.size()-1; i>=0; i--) {
                        listeners.get(i).onStatusConnected();
                    }
                }
            }
        }
    }
}