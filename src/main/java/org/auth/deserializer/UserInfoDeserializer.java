package org.auth.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.auth.model.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDto> {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoDeserializer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Nothing to configure
    }

    @Override
    public UserInfoDto deserialize(String topic, byte[] data) {
        if (data == null) {
            logger.info("Null received during deserialization");
            return null;
        }

        try {
            return objectMapper.readValue(data, UserInfoDto.class);
        } catch (IOException e) {
            logger.error("Error deserializing UserInfoDto: ", e);
            throw new RuntimeException("Error deserializing UserInfoDto", e);
        }
    }

    @Override
    public UserInfoDto deserialize(String topic, Headers headers, byte[] data) {
        return deserialize(topic, data);
    }

    @Override
    public UserInfoDto deserialize(String topic, Headers headers, ByteBuffer data) {
        byte[] bytes;
        if (data.hasArray()) {
            bytes = data.array();
        } else {
            bytes = new byte[data.remaining()];
            data.get(bytes);
        }
        return deserialize(topic, bytes);
    }

    @Override
    public void close() {
        // Nothing to close
    }
}