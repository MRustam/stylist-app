package live.evsianna.stylist.service;

import live.evsianna.stylist.model.Favor;
import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.dto.UserFavorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppMailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String smtpSenderEmail;

    @Value("${spring.mail.stylist.email}")
    private String stylistEmail;

    @Value("${spring.mail.siteUrl}")
    private String siteUrl;

    public void sendEmailToStylist(final UserFavorDTO dto) {
        final User user = dto.getUser();
        final Favor favor = dto.getFavor();
        final String text = "Клиент: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                      "телефон: " + user.getPhone() + "\n" +
                      "почта: " + user.getEmail() + "\n\n" +
                      "Заказал: " + favor.getTitle() + "\n" +
                      "Пояснение: " + favor.getDescription();
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(stylistEmail);
        mail.setFrom("<" + smtpSenderEmail + ">");
        mail.setSubject("Новый заказ услуги стилиста");
        mail.setText(text);
        this.sendEmail(mail);
    }

    public void sendEmailToConsumer(final UserFavorDTO dto) {
        final User user = dto.getUser();
        final Favor favor = dto.getFavor();
        final String text =
                user.getFirstName() + ", Вы только что заказали услугу: '" + favor.getTitle() + "'.\n" +
                        "Так же, теперь у вас есть личный кабинет на " + siteUrl + ".\n" +
                        "Вот ваши логин: " + user.getEmail() + " и пароль: " + user.getPassword();
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("<" + smtpSenderEmail + ">");
        mail.setSubject("Заказ успешно создан.");
        mail.setText(text);
        this.sendEmail(mail);
    }

    public void sendEmailToConsumer(final User user) {
        final String text =
                user.getFirstName() + ", Вас только что зарегистрировали на " + siteUrl + ".\n" +
                        "Вот ваши логин: " + user.getEmail() + " и пароль: " + user.getPassword();
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("<" + smtpSenderEmail + ">");
        mail.setSubject("Регистрация.");
        mail.setText(text);
        this.sendEmail(mail);
    }

    @Async
    public void sendEmail(final SimpleMailMessage mail) {
        try {
            mailSender.send(mail);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
