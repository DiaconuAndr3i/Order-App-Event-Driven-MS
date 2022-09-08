package com.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name.producer}")
    private String topicName;

    private Logger LOGGER = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Bean
    public NewTopic createTopic(){
        LOGGER.info(String.format("Topic created => %s", topicName));
        return TopicBuilder.name(topicName).build();
    }
}
