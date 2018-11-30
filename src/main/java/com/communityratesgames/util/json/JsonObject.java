
package com.communityratesgames.util.json;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import static java.nio.charset.StandardCharsets.*;
import com.fasterxml.jackson.core.*;

public class JsonObject implements JsonThing {
    public Map<String, JsonThing> fields;

    public JsonObject() {
        this.fields = new HashMap<String, JsonThing>();
    }

    /**
     * Parses a JSON string to JsonObject.
     * @Throws ParseException if any semantical errors are found in the JSON
     * string
     */
    public JsonObject(String json) throws IOException, ParseException {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);

        this.fields = new HashMap<String, JsonThing>();
        JsonToken token = parser.nextToken();
        if (token != JsonToken.START_OBJECT) {
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
            if (token == JsonToken.END_OBJECT) {
                // Stop when we find the end of the object.
                return;
            }

            if (token != JsonToken.FIELD_NAME) {
                throw new ParseException("Expected field name in object",
                    (int)parser.getTokenLocation().getCharOffset());
            }

            // Save the field key; this must be a string.
            key = parser.getValueAsString();
            token = parser.nextToken();
            if (token == null) {
                throw new ParseException("Expected value after field name",
                    (int)parser.getTokenLocation().getCharOffset());
            }

            switch (token) {
                case START_ARRAY:
                    JsonArray array = new JsonArray();
                    array.parse(parser);
                    this.fields.put(key, array);
                    break;

                case START_OBJECT:
                    JsonObject object = new JsonObject();
                    object.parse(parser);
                    this.fields.put(key, object);
                    break;

                case VALUE_FALSE:
                    this.fields.put(key, new JsonBoolean(false));
                    break;

                case VALUE_TRUE:
                    this.fields.put(key, new JsonBoolean(true));
                    break;

                case VALUE_NULL:
                    this.fields.put(key, null);
                    break;

                case VALUE_NUMBER_FLOAT:
                    JsonNumber f = new JsonNumber(parser.getValueAsDouble());
                    this.fields.put(key, f);
                    break;

                case VALUE_NUMBER_INT:
                    JsonNumber i = new JsonNumber(parser.getValueAsLong());
                    this.fields.put(key, i);
                    break;

                case VALUE_STRING:
                    JsonString string = new JsonString(parser.getValueAsString());
                    this.fields.put(key, string);
                    break;

                default:
                    throw new ParseException("Unexpected token " + token.toString(),
                        (int)parser.getTokenLocation().getCharOffset());
            }
        }
    }

    public JsonObject append(String key, JsonThing value) {
        this.fields.put(key, value);
        return this;
    }

    public JsonThing get(String key) throws JsonGetException {
        JsonThing thing = this.fields.get(key);
        if (thing == null) {
            throw new JsonGetException("No such key '" + key + "'");
        }
        return thing;
    }

    public String getString(String key) throws JsonGetException {
        JsonThing thing = this.fields.get(key);
        if (thing == null) {
            throw new JsonGetException("No such key '" + key + "'");
        } else if (thing.getType() != Type.JSON_STRING) {
            throw new JsonGetException("Invalid type; expected string for key '" + key + "'");
        }
        return thing.toString();
    }

    public double getNumber(String key) throws JsonGetException {
        JsonThing thing = this.fields.get(key);
        if (thing == null) {
            throw new JsonGetException("No such key '" + key + "'");
        } else if (thing.getType() != Type.JSON_NUMBER) {
            throw new JsonGetException("Invalid type; expected number for key '" + key + "'");
        }
        return ((JsonNumber)thing).number;
    }

    public boolean getBoolean(String key) throws JsonGetException {
        JsonThing thing = this.fields.get(key);
        if (thing == null) {
            throw new JsonGetException("No such key '" + key + "'");
        } else if (thing.getType() != Type.JSON_BOOLEAN) {
            throw new JsonGetException("Invalid type; expected boolean for key '" + key + "'");
        }
        return ((JsonBoolean)thing).state;
    }

    @Override
    public Type getType() {
        return Type.JSON_OBJECT;
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
        gen.writeStartObject();
        for (Map.Entry entry : this.fields.entrySet()) {
            gen.writeFieldName((String)entry.getKey());
            ((JsonThing)entry.getValue()).build(gen);
        }
        gen.writeEndObject();
    }
}
