
package com.communityratesgames.util.json;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import static java.nio.charset.StandardCharsets.*;
import com.fasterxml.jackson.core.*;

public class JsonString implements JsonThing {
    public String s;

    public JsonString(String s) {
        this.s = s;
    }

    @Override
    public void parse(JsonParser parser) throws IOException, ParseException {
        throw new UnsupportedOperationException("String cannot be unmarshaled.");
    }

    @Override
    public String toString() {
        return this.s;
    }

    @Override
    public Type getType() {
        return Type.JSON_STRING;
    }

    @Override
    public String build() throws IOException {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator gen = factory.createGenerator(stream);

        gen.writeString(this.s);

        gen.flush();
        gen.close();
        String out = stream.toString(UTF_8.toString());
        stream.close();
        return out;
    }

    @Override
    public void build(JsonGenerator gen) throws IOException {
        gen.writeString(this.s);
    }
}
