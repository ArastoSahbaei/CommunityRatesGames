package com.communityratesgames.user;

public class LoginResponse {
    private Long token;

    protected LoginResponse() { }

    public LoginResponse(Long token) {
        this.token = token;
    }

    public Long getToken() {
        return this.token;
    }

    public void setCode(Long token) {
        this.token = token;
    }
}
