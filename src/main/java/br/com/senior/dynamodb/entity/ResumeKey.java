package br.com.senior.dynamodb.entity;

import java.util.Locale;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@DynamoDBTypeConvertedJson
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResumeKey {

    @NotNull
    @DynamoDBHashKey
    String document;

    @NotNull
    @DynamoDBRangeKey
    String typeDocument;

    @NotNull
    Locale locale;
}
