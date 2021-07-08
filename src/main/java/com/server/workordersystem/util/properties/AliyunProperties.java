package com.server.workordersystem.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunProperties {
    @Value("${aliyun.domain}")
    public String domain;

    @Value("${aliyun.endpoint}")
    public String endpoint;

    @Value("${aliyun.access-key}")
    public String accessKey;

    @Value("${aliyun.secret-key}")
    public String secretKey;

    @Value("${aliyun.bucket-name}")
    public String bucketName;

    @Value("${aliyun.region}")
    public String region;
}

