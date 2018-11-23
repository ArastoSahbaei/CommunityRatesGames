
package com.communityratesgames.user;

public class LoginModel {
    private String userName;
    private String password;

    protected LoginModel() { }

    public String getUsername() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
