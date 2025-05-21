package com.example.notificationService.service;

import com.example.notificationService.dto.NotificationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ObjectMapper mapper;
    private final EmailService emailService;


    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group-id}")
    public void receive(@Payload String message) throws IOException {
            NotificationDto notificationDto = mapper.readValue(message, NotificationDto.class);
            log.info("Количество уведомлений '{}'", notificationDto.getRecords().size());
            emailService.sendNotification(notificationDto.getRecords());
            log.info("Рассылка завершена");
    }

}
