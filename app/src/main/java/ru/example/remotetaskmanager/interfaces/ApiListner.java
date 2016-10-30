package ru.example.remotetaskmanager.interfaces;

/**
 * Created by 95tox on 30.10.2016.
 */

public interface ApiListner {
    void onLoad(Object obj);
    void onError(String cause);
}
