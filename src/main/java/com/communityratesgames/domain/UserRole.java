package com.communityratesgames.domain;

public enum UserRole{
    BANNED("Banned"), USER("User"), MODERATOR("Moderator"), ADMIN("Admin");

    private final String text;

    UserRole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
