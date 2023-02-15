package br.com.senior.dynamodb.builder;

import org.json.JSONObject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JSONObjectBuilder {

    JSONObject json;

    public static JSONObjectBuilder oneJSONObject() {
        JSONObjectBuilder builder = new JSONObjectBuilder();
        builder.json = new JSONObject();
        return builder;
    }

    public JSONObjectBuilder put(String key, Object value) {
        this.json.put(key, value);
        return this;
    }

    public JSONObject toJSON() {
        return json;
    }
}
