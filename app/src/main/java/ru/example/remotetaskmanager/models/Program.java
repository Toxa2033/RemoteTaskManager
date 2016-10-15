package ru.example.remotetaskmanager.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 95tox on 15.10.2016.
 */

public class Program implements Serializable {
    private String programName;
    private List<Path> paths ;
    private String displayVersion;


    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public String getDisplayVersion() {
        return displayVersion;
    }

    public void setDisplayVersion(String displayVersion) {
        this.displayVersion = displayVersion;
    }
}
