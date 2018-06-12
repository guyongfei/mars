package com.witshare.mars.service.impl;


import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.witshare.mars.constant.EnumStorage;
import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.constant.QingObjStoreAWS3;
import com.witshare.mars.pojo.util.InputStreamAndMetadata;
import com.witshare.mars.pojo.util.QingyunStorageBean;
import com.witshare.mars.service.QingyunStorageService;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.witshare.mars.constant.EnumStorage.PDF;


/**
 * @see QingyunStorageService
 */
@Service
public class QingyunStorageServiceImpl implements QingyunStorageService {

    //    final String ObjectName = "test/%s/%s_%s.%s";
    final String CONTENT_TYPE_REGEX = "data:\\w+/\\w+;base64,";
    final String CONTENT_TYPE1_REGEX = "data:\\w+/\\w+";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private QingObjStoreAWS3 qingObjStoreAWS3;
    private Bucket bucket;


    /**
     * @see QingyunStorageService#uploadToQingyun(String, String, EnumStorage)
     */
    @Override
    public String uploadToQingyun(String dataStr, String token, EnumStorage type) {
        if (StringUtils.isEmpty(dataStr) || StringUtils.isEmpty(token) || type == null) {
            return null;
        }
        String contentType = getContentType(dataStr);
        if (StringUtils.isEmpty(contentType)) {
            return null;
        }
        QingyunStorageBean qingyunStorageBean = new QingyunStorageBean();

        qingyunStorageBean.setToken(token);
        qingyunStorageBean.setContent(dataStr);
        qingyunStorageBean.setContentType(contentType);
        qingyunStorageBean.setCreateTime(new Timestamp(new Date().getTime()));
        String objectName = getObjectName(qingyunStorageBean, type.getName());
        qingyunStorageBean.setObjectName(objectName);
//        qingObjStoreAWS3.deleteOneObject(propertiesConfig.qingyunBucket, objectName);
        InputStreamAndMetadata inputStreamAndMetadata = base64ToInputStreamAndMeta(qingyunStorageBean);
        uploadToQingyunStorage(qingyunStorageBean, inputStreamAndMetadata);
        objectName = objectName + "?_d=" + System.currentTimeMillis();
        return objectName;
    }

    /**
     * 获取文件类型
     *
     * @param content
     * @return
     */
    public String getContentType(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        Pattern pattern = Pattern.compile(CONTENT_TYPE1_REGEX);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(0).replaceAll("data:", "");
        }
        return null;
    }

    /**
     * 存储
     *
     * @param qingyunStorageBean
     * @param ismd
     */
    private void uploadToQingyunStorage(QingyunStorageBean qingyunStorageBean, InputStreamAndMetadata ismd) {
        qingObjStoreAWS3.uploadObject(propertiesConfig.qingyunBucket, qingyunStorageBean.getObjectName(), ismd.getInputStream(), ismd.getObjectMetadata());
    }


    /**
     * 获取存储对象名称
     *
     * @param qingyunStorageBean
     * @param type
     * @return
     */
    private String getObjectName(QingyunStorageBean qingyunStorageBean, String type) {
        return String.format(propertiesConfig.qingyunObjectName, type, qingyunStorageBean.getToken(), qingyunStorageBean.getContentType().split("/")[1]);
    }

    /**
     * 包装存青云的元素
     *
     * @param qingyunStorageBean
     * @return
     */
    private InputStreamAndMetadata base64ToInputStreamAndMeta(QingyunStorageBean qingyunStorageBean) {
        if (qingyunStorageBean == null) {
            return null;
        }
        String content = qingyunStorageBean.getContent();
        String contentType = qingyunStorageBean.getContentType();
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(contentType)) {
            return null;
        }
        if (!contentType.contains(PDF)) {
            content = content.replaceAll(CONTENT_TYPE_REGEX, "");
        }

        InputStreamAndMetadata inputStreamAndMetadata = new InputStreamAndMetadata();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        byte[] bytes = WitshareUtils.base64ToByte(content);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        InputStream inputStream = WitshareUtils.byte2Input(bytes);
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setContentType(contentType);
        inputStreamAndMetadata.setInputStream(inputStream);
        inputStreamAndMetadata.setObjectMetadata(objectMetadata);
        return inputStreamAndMetadata;
    }
}
