package ru.example.remotetaskmanager.models;

import java.io.Serializable;

/**
 * Created by 95tox on 15.10.2016.
 */

public class Request implements Serializable {

    private String _id ;
    private String task;
    private String cmd ;
    private String path;

    private String response;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
