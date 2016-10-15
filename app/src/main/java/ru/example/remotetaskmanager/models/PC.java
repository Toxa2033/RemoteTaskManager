package ru.example.remotetaskmanager.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 95tox on 15.10.2016.
 */

public class PC  implements Serializable {

    private String _id;
    private String pcName;
    private List<Process> startedProcess;
    private String machineId;
    private User owner;
    private List<Program> installSoftware;
    private boolean online;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public List<Process> getStartedProcess() {
        return startedProcess;
    }

    public void setStartedProcess(List<Process> startedProcess) {
        this.startedProcess = startedProcess;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Program> getInstallSoftware() {
        return installSoftware;
    }

    public void setInstallSoftware(List<Program> installSoftware) {
        this.installSoftware = installSoftware;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
