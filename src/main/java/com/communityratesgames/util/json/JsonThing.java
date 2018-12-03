
package com.communityratesgames.util.json;

import java.io.IOException;
import java.text.ParseException;
import com.fasterxml.jackson.core.*;

public interface JsonThing {
    public enum Type {
        JSON_OBJECT,
        JSON_ARRAY,
        JSON_STRING,
        JSON_NUMBER,
        JSON_BOOLEAN
    }

    public void parse(JsonParser parser) throws IOException, ParseException;

    public Type getType();
    public String build() throws IOException;
    public void build(JsonGenerator gen) throws IOException;
}
