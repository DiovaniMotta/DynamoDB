package br.com.senior.dynamodb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.dynamodb.entity.Resume;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DynamoDBUtils {

    static final Logger log = LoggerFactory.getLogger(DynamoDBUtils.class);
    @Autowired
    AmazonDynamoDB amazonDynamoDB;
    @Autowired
    DynamoDBMapper mapper;

    public void deleteTable() {
        DeleteTableRequest dtr = mapper.generateDeleteTableRequest(Resume.class);
        TableUtils.deleteTableIfExists(amazonDynamoDB, dtr);
        log.info("Deleted table {}", dtr.getTableName());
    }

    public void createTables() throws InterruptedException {
        CreateTableRequest ctr = mapper.generateCreateTableRequest(Resume.class).withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);
        log.info("Created table {}", ctr.getTableName());
        TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());
    }
}
