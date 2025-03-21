package org.auth.eventConsumer;

import org.auth.model.UserInfoDto;

import org.auth.repository.UserInfoDtoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserInfoConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoConsumer.class);

    @Autowired
    private UserInfoDtoRepository userInfoDtoRepository;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(UserInfoDto userInfoDto) {
        logger.info("User info message received -> {}", userInfoDto);
        // Process the received message here
        processUserInfo(userInfoDto);
    }

    private void processUserInfo(UserInfoDto userInfoDto) {

        userInfoDtoRepository.save(userInfoDto);

        logger.info("Processing user info for user: {}", userInfoDto.getUsername());
    }
}