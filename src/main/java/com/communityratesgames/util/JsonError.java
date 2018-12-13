
package com.communityratesgames.util;

public class JsonError extends Exception {
    public int error;
    public String message;

    public JsonError() {
        super();
    }

    public JsonError(int error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("{\"error\":%d,\"message\":\"%s\"}", this.error, this.message);
    }
}
