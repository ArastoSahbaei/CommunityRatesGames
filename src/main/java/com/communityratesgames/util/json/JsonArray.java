
package com.communityratesgames.util.json;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import static java.nio.charset.StandardCharsets.*;
import com.fasterxml.jackson.core.*;

public class JsonArray implements JsonThing {
    public List<JsonThing> items;

    public JsonArray() {
        this.items = new ArrayList<JsonThing>();
    }

    /**
     * Parses a JSON string to JsonObject.
     * @Throws ParseException if any semantical errors are found in the JSON
     * string
     */
    public JsonArray(String json) throws IOException, ParseException {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);

        this.items = new ArrayList<JsonThing>();
        JsonToken token = parser.nextToken();
        if (token != JsonToken.START_ARRAY) {
            throw new ParseException("Unexpected token " + token.toString(),
                (int)parser.getTokenLocation().getCharOffset());
        }
        this.parse(parser);

        parser.close();
    }

    @Override
    public void parse(JsonParser parser) throws IOException, ParseException {
        String key;
        JsonToken token;
        while (true) {
            token = parser.nextToken();
            if (token == JsonToken.END_ARRAY) {
                // Stop when we find the end of the array.
                return;
            }

            switch (token) {
                case START_ARRAY:
                    JsonArray array = new JsonArray();
                    array.parse(parser);
                    this.items.add(array);
                    break;

                case START_OBJECT:
                    JsonObject object = new JsonObject();
                    object.parse(parser);
                    this.items.add(object);
                    break;

                case VALUE_FALSE:
                    this.items.add(new JsonBoolean(false));
                    break;

                case VALUE_TRUE:
                    this.items.add(new JsonBoolean(true));
                    break;

                case VALUE_NULL:
                    this.items.add(null);
                    break;

                case VALUE_NUMBER_FLOAT:
                    JsonNumber f = new JsonNumber(parser.getValueAsDouble());
                    this.items.add(f);
                    break;

                case VALUE_NUMBER_INT:
                    JsonNumber i = new JsonNumber(parser.getValueAsLong());
                    this.items.add(i);
                    break;

                case VALUE_STRING:
                    JsonString string = new JsonString(parser.getValueAsString());
                    this.items.add(string);
                    break;

                default:
                    throw new ParseException("Unexpected token " + token.toString(),
                        (int)parser.getTokenLocation().getCharOffset());
            }
        }
    }

    public JsonArray append(JsonThing value) {
        this.items.add(value);
        return this;
    }

    public JsonThing get(int index) throws JsonGetException {
        JsonThing thing = this.items.get(index);
        if (thing == null) {
            throw new JsonGetException(22, "No such index '" + index + "'");
        }
        return thing;
    }

    public String getString(int index) throws JsonGetException {
        JsonThing thing = this.items.get(index);
        if (thing == null) {
            throw new JsonGetException(22, "No such index '" + index + "'");
        } else if (thing.getType() != Type.JSON_STRING) {
            throw new JsonGetException(21, "Invalid type; expected string for index '" + index + "'");
        }
        return thing.toString();
    }

    public double getNumber(int index) throws JsonGetException {
        JsonThing thing = this.items.get(index);
        if (thing == null) {
            throw new JsonGetException(22, "No such index '" + index + "'");
        } else if (thing.getType() != Type.JSON_NUMBER) {
            throw new JsonGetException(21, "Invalid type; expected number for index '" + index + "'");
        }
        return ((JsonNumber)thing).number;
    }

    public boolean getBoolean(int index) throws JsonGetException {
        JsonThing thing = this.items.get(index);
        if (thing == null) {
            throw new JsonGetException(22, "No such index '" + index + "'");
        } else if (thing.getType() != Type.JSON_BOOLEAN) {
            throw new JsonGetException(21, "Invalid type; expected boolean for index '" + index + "'");
        }
        return ((JsonBoolean)thing).state;
    }

    @Override
    public Type getType() {
        return Type.JSON_ARRAY;
    }

    @Override
    public String build() throws IOException {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator gen = factory.createGenerator(stream);

        this.build(gen);

        gen.flush();
        gen.close();
        String out = stream.toString(UTF_8.toString());
        stream.close();
        return out;
    }

    @Override
    public void build(JsonGenerator gen) throws IOException {
        gen.writeStartArray();
        for (JsonThing entry : this.items) {
            entry.build(gen);
        }
        gen.writeEndArray();
    }
}
