
package com.communityratesgames.util.json;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import static java.nio.charset.StandardCharsets.*;
import com.fasterxml.jackson.core.*;

public class JsonBoolean implements JsonThing {
    public boolean state;

    public JsonBoolean(boolean state) {
        this.state = state;
    }

    @Override
    public void parse(JsonParser parser) throws IOException, ParseException {
        throw new UnsupportedOperationException("Boolean cannot be unmarshaled.");
    }

    @Override
    public Type getType() {
        return Type.JSON_BOOLEAN;
    }

    @Override
    public String build() throws IOException {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator gen = factory.createGenerator(stream);

        gen.writeBoolean(this.state);

        gen.flush();
        gen.close();
        String out = stream.toString(UTF_8.toString());
        stream.close();
        return out;
    }

    @Override
    public void build(JsonGenerator gen) throws IOException {
        gen.writeBoolean(this.state);
    }
}
