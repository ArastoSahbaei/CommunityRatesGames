
package com.communityratesgames.util.json;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import static java.nio.charset.StandardCharsets.*;
import com.fasterxml.jackson.core.*;

public class JsonNumber implements JsonThing {
    public double number;

    public JsonNumber(short i) {
        this.number = i;
    }

    public JsonNumber(int i) {
        this.number = i;
    }

    public JsonNumber(long i) {
        this.number = i;
    }

    public JsonNumber(float i) {
        this.number = i;
    }

    public JsonNumber(double i) {
        this.number = i;
    }

    @Override
    public void parse(JsonParser parser) throws IOException, ParseException {
        throw new UnsupportedOperationException("Number cannot be unmarshaled.");
    }

    @Override
    public Type getType() {
        return Type.JSON_NUMBER;
    }

    @Override
    public String build() throws IOException {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator gen = factory.createGenerator(stream);

        gen.writeNumber(this.number);

        gen.flush();
        gen.close();
        String out = stream.toString(UTF_8.toString());
        stream.close();
        return out;
    }

    @Override
    public void build(JsonGenerator gen) throws IOException {
        gen.writeNumber(this.number);
    }
}
