package com.server.workordersystem.util.image;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.server.workordersystem.util.properties.AliyunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;

@Component
public class AliyunOssUtil {

    private static AliyunOssUtil aliyunOssUtil;
    private static OSS ossClient;

    @Autowired
    private AliyunProperties properties;

    public static String uploadFile(byte[] bytes, String key) {

        try {
            ossClient.putObject(
                    aliyunOssUtil.properties.bucketName,
                    "image/" + key,
                    new ByteArrayInputStream(bytes)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return packUrl("image/" + key);
    }

    public static String packUrl(String filename) {
        return "https://" + aliyunOssUtil.properties.bucketName + "." + aliyunOssUtil.properties.endpoint + "/" + filename;
    }

    @PostConstruct
    public void init() {
        aliyunOssUtil = this;
        aliyunOssUtil.properties = this.properties;
        ossClient = new OSSClientBuilder().build(
                "https://" + aliyunOssUtil.properties.endpoint,
                aliyunOssUtil.properties.accessKey,
                aliyunOssUtil.properties.secretKey
        );
    }
}
