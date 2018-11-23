
package com.communityratesgames.user;

public class LoginModel {
    private String username;
    private String password;

    protected LoginModel() { }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
