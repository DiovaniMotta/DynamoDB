package br.com.senior.dynamodb.converter;

import org.json.JSONObject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class JSONDataConverter implements DynamoDBTypeConverter<String, JSONObject> {

    @Override
    public String convert(JSONObject json) {
        return json.toString();
    }

    @Override
    public JSONObject unconvert(String value) {
        return new JSONObject(value);
    }
}
