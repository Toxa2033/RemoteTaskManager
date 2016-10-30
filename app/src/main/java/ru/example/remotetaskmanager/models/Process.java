package ru.example.remotetaskmanager.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 95tox on 15.10.2016.
 */

public class Process implements Serializable {

    private String startTime;
    private String totalPocessorTime;
    private long memory;
    private String machineName;
    private String priorityClass;
    private boolean priorityBoostEnabled;
    private String name;
    private int processId;

    public Date getStartTime() {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.getDefault());
        try {
            date=format.parse(startTime);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    public String getStartTimeString() {

        return startTime;
    }

    public String getTotalPocessorTime() {
        return totalPocessorTime;
    }

    public void setTotalPocessorTime(String totalPocessorTime) {
        this.totalPocessorTime = totalPocessorTime;
    }

    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getPriorityClass() {
        return priorityClass;
    }

    public void setPriorityClass(String priorityClass) {
        this.priorityClass = priorityClass;
    }

    public boolean isPriorityBoostEnabled() {
        return priorityBoostEnabled;
    }

    public void setPriorityBoostEnabled(boolean priorityBoostEnabled) {
        this.priorityBoostEnabled = priorityBoostEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }
}
