
package com.communityratesgames.util.json;

public class JsonGetException extends Exception {
    private int error;
    private String message;

    public JsonGetException() {
        super();
    }

    public JsonGetException(int error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("{\"error\":%d,\"message\":\"%s\"}", this.error, this.message);
    }
}
