package br.com.senior.dynamodb.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.regions.Region;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableDynamoDBRepositories(basePackages = "br.com.senior.dynamodb.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.dynamodb.region}")
    private String amazonAWSRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials amazonAWSCredentials) {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials);
        amazonDynamoDB.setRegion(Region.getRegion(Regions.US_EAST_2));
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }


    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }


    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
                .withRegion(Regions.US_EAST_1).build();
    }

}
