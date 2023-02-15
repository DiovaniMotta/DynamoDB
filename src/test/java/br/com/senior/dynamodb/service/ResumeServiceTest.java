package br.com.senior.dynamodb.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.dynamodb.config.DynamoDBConfig;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RunWith(SpringRunner.class)
@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResumeServiceTest {
    @Autowired
    ResumeService service;

    @Before
    public void init(){
        service.loadAllByDocument();
    }

    @Test
    public void testPerformanceWhenIs0001Request() {
        service.prepareExecution(1);
    }
}
