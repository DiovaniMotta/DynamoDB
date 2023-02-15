package br.com.senior.dynamodb.entity;

import java.time.Instant;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;

import br.com.senior.dynamodb.converter.JSONDataConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedTimestamp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@DynamoDBTable(tableName = "resume")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Resume {

    @Id
    @DynamoDBHashKey
    ResumeKey key;

    @DynamoDBRangeKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    UUID identifier;

    @DynamoDBTypeConverted(converter = JSONDataConverter.class)
    @DynamoDBAttribute(attributeName = "details")
    JSONObject information;

    @DynamoDBAttribute
    String genericValue;
}
