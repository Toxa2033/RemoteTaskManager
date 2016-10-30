package ru.example.remotetaskmanager.interfaces;

import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.User;

/**
 * Created by 95tox on 30.10.2016.
 */

public interface SelectNavDrawerListner {
    void onSelectUser(User user);
    void onSelectPc(PC pc);
}
