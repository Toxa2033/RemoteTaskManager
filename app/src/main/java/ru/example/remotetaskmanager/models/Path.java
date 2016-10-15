package ru.example.remotetaskmanager.models;

import java.io.Serializable;

/**
 * Created by 95tox on 15.10.2016.
 */

public class Path implements Serializable {

    private String fileName;
    private String path;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
