package ru.example.remotetaskmanager.models;

import java.io.Serializable;

/**
 * Created by 95tox on 11.10.2016.
 */

public class User implements Serializable {
    private String login;
    private String email;
    private String password;
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
