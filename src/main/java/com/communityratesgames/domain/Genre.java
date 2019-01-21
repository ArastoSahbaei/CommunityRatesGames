package com.communityratesgames.domain;

public enum Genre {
    SHOOTER("Shooter"),
    RPG("RPG"),
    RTS("RTS"),
    TBS("TBS");

    private final String text;

    Genre(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
