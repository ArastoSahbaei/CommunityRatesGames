package com.communityratesgames.user;

public class LoginResponse {
    private Integer token;

    protected LoginResponse() { }

    public LoginResponse(Integer token) {
        this.token = token;
    }

    public Integer getToken() {
        return this.token;
    }

    public void setCode(Integer token) {
        this.token = token;
    }
}
