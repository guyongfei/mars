package com.witshare.mars.constant;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * amazon对象存储配置类
 */
@Component
public class QingObjStoreAWS3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(QingObjStoreAWS3.class);

    private static QingObjStoreAWS3 instance;
    @Autowired
    private PropertiesConfig propertiesConfig;

    private AmazonS3 s3Client;

    private QingObjStoreAWS3() {
    }

    public static QingObjStoreAWS3 getInstance() {
        if (instance == null) {
            synchronized (AmazonS3Client.class) {
                if (instance == null) {
                    instance = new QingObjStoreAWS3();
                }
            }
        }
        return instance;
    }


    /**
     * 初始化并配置AmazonS3Client
     */
    private void initAmazonS3Client() {
        String ak = propertiesConfig.qingyunS3AK;
        String sk = propertiesConfig.qingyunS3SK;
        String zoneKey = propertiesConfig.qingyunZoneKey;
        String endPoint = propertiesConfig.qingyunHost;
        LOGGER.info("initAmazonS3Client start,ak:{},sk:{},zoneKey:{},endpoint:{}", ak, sk, zoneKey, endPoint);
        BasicAWSCredentials credentials = new BasicAWSCredentials(ak, sk);
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endPoint, zoneKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpointConfiguration).build();
        LOGGER.info("initAmazonS3Client end,buckets:{}", s3Client.listBuckets());
    }

    /**
     * 获取client
     *
     * @return
     */
    private AmazonS3 getAmazonS3Client() {
        if (s3Client == null) {
            initAmazonS3Client();
        }
        return s3Client;
    }

    /**
     * create bucket
     *
     * @param bucketName
     * @return
     */
    public boolean createBucket(String bucketName) {
        boolean flag = false;
        AmazonS3 client = getAmazonS3Client();
        if (!client.doesBucketExistV2(bucketName)) { //不存在则新建
            Bucket bucket = client.createBucket(bucketName);
            LOGGER.info("create Bucket success:{} ", bucket.getName());
            flag = true;
        }
        return flag;
    }

    /**
     * delete bucket
     *
     * @param bucketName
     * @return
     */
    public Boolean deleteBucket(String bucketName) {
        boolean flag = false;
        AmazonS3 client = getAmazonS3Client();
        if (!client.doesBucketExistV2(bucketName)) { //不存在则新建
            client.deleteBucket(bucketName);
            LOGGER.info("deleteBucket success:{} ", bucketName);
            flag = true;
        }
        return flag;
    }

    /**
     * get objects
     *
     * @param bucketName
     * @param keyName
     * @return
     */
    public InputStream getObjects(String bucketName, String keyName) {
        try {
            AmazonS3 client = getAmazonS3Client();
            if (client.doesObjectExist(bucketName, keyName)) {
                S3Object object = client.getObject(new GetObjectRequest(bucketName, keyName));
                S3ObjectInputStream objStream = object.getObjectContent();
                InputStream is = new BufferedInputStream(objStream);
                LOGGER.info("get object success bucketName:{}, keyName:{}", bucketName, keyName);
                return is;
            } else {
                LOGGER.info("object does not exists!");
            }
        } catch (Exception e) {
            LOGGER.error("getObjects error.", e);
        }
        return null;
    }

    /**
     * Deleting an Object (Non-Versioned Bucket)
     *
     * @param bucketName
     * @param keyName    delete file：abc/c3p0-0.9.1.2.jar   delete folder：adc/
     */
    public Boolean deleteOneObject(String bucketName, String keyName) {
        boolean flag = false;
        try {
            AmazonS3 client = getAmazonS3Client();
            if (keyName.endsWith("/")) {
                List<String> list = listObjects(bucketName, keyName);
                if (list.isEmpty() || list.get(0).equals(keyName)) {
                    LOGGER.info("delete folder success. bucketName:{}, keyName:{}", bucketName, keyName);
                    client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
                    flag = true;
                } else {
                    LOGGER.info("only empty folder can be removed!");
                }
            } else {
                if (client.doesObjectExist(bucketName, keyName)) {
                    client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
                    flag = true;
                    LOGGER.info("delete object  success! bucketName:{}, keyName:{}", bucketName, keyName);
                }
            }
        } catch (AmazonServiceException e) {
            LOGGER.error("delete object error.", e);
        } catch (AmazonClientException e) {
            LOGGER.error("delete object error with AmazonClient.", e);
        }
        return flag;
    }


    /**
     * upload file form InputStream
     *
     * @param bucketName
     * @param keyName
     * @param fileInputStream
     * @param objectMetadata
     * @return
     */
    public Boolean uploadObject(String bucketName, String keyName, InputStream fileInputStream, ObjectMetadata objectMetadata) {
        boolean flag = true;
        try {
            AmazonS3 client = getAmazonS3Client();
            if (fileInputStream != null) {
                if (!client.doesBucketExistV2(bucketName)) {
                    return false;
                }
                objectMetadata.setContentEncoding("UTF-8");
                client.putObject(bucketName, keyName, fileInputStream, objectMetadata);
                LOGGER.info("Uploading a new object to  bucketName:{}, keyName:{}", bucketName, keyName);
            }
        } catch (AmazonServiceException e) {
            flag = false;
            LOGGER.error("upload object error   bucketName:{}, keyName:{},e:{}", bucketName, keyName, e);
        } catch (AmazonClientException e) {
            flag = false;
            LOGGER.error("upload object error with AmazonS3Client .", e);
        }
        return flag;
    }


    /**
     * list all objects in bucket
     *
     * @param bucketName
     * @param prefix     when list specified folder use this; list all set to "" or null
     * @return
     */
    public List<String> listObjects(String bucketName, String prefix) {
        List<String> keys = new ArrayList<>();
        try {
            AmazonS3 client = getAmazonS3Client();
            final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(prefix);
            ListObjectsV2Result result;
            do {
                result = client.listObjectsV2(req);
                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    keys.add(objectSummary.getKey());
                }
                req.setContinuationToken(result.getNextContinuationToken());
            } while (result.isTruncated() == true);
        } catch (AmazonServiceException e) {
            LOGGER.error("list bucket keys error .", e);
        } catch (AmazonClientException e) {
            LOGGER.error("list bucket keys error with AmazonS3Client", e);
        }
        return keys;
    }


}
