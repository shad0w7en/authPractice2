package org.auth.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.auth.model.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoDto> {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoSerializer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Nothing to configure
    }

    @Override
    public byte[] serialize(String topic, UserInfoDto data) {
        if (data == null) {
            logger.info("Null received at serializing");
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing UserInfoDto: ", e);
            throw new RuntimeException("Error serializing UserInfoDto", e);
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, UserInfoDto data) {
        return serialize(topic, data);
    }


}