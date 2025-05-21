package com.example.notificationService.service;

import com.example.notificationService.dto.RecordDto;
import com.example.notificationService.email.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String NOTIFICATION_MESSAGE = """
                Здравствуйте, %s.
                Вы записаны на услугу: %s
                Мастер: %s
                Место проведения: %s
                Адрес проведения: %s
                Время проведения: %s""";

    private final JavaMailSender mailSender;

    public void sendNotification(List<RecordDto> list) {
        list.parallelStream()
                .map(recordDto ->
                        new EmailMessage(recordDto.getClientDto().getEmail(), this.getAnswer(recordDto)))
                .forEach(this::sendEmail);
    }

    private void sendEmail(EmailMessage emailMessage) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EmailMessage.SENDER);
        mailMessage.setTo(emailMessage.addressee());
        mailMessage.setSubject(EmailMessage.HEADER);
        mailMessage.setText(emailMessage.text());

        mailSender.send(mailMessage);
    }

    private String getAnswer(RecordDto recordDto) {
        return String.format(NOTIFICATION_MESSAGE,
                recordDto.getClientDto().getName(),
                recordDto.getPetServiceDto().getName(),
                recordDto.getMasterDto().getName(),
                recordDto.getPlaceDto().getName(),
                recordDto.getPlaceDto().getAddress(),
                recordDto.getTiming());
    }

}

