package ru.example.remotetaskmanager.interfaces;

import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.Task;

/**
 * Created by 95tox on 15.10.2016.
 */

public interface SocketListener {
    void onAddNewPC(PC pc);
    void onAddNewUserToPC(PC pc);
    void onAddNewTaskToPC(Task task);
    void onTaskExecuted(Task task);
}
