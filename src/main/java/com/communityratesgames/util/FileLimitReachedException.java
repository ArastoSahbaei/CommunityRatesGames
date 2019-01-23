package com.communityratesgames.util;

public class FileLimitReachedException extends Exception {
    public FileLimitReachedException() {
        super();
    }

    public FileLimitReachedException(String s) {
        super(s);
    }
}
