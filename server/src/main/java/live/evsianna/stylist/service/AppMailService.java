package live.evsianna.stylist.service;

import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.dto.UserOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void sendEmailToStylist(final UserOrderDTO dto) throws MailException {
        final User user = dto.getUser();
        final Order order = dto.getOrder();
        final String text = "Клиент: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                      "телефон: " + user.getPhone() + "\n" +
                      "почта: " + user.getEmail() + "\n\n" +
                      "Заказал: " + order.getTitle() + "\n" +
                      "Пояснение: " + order.getMessage();
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(stylistEmail);
        mail.setFrom("<" + smtpSenderEmail + ">");
        mail.setSubject("Новый заказ услуги стилиста");
        mail.setText(text);
        mailSender.send(mail);
    }

    public void sendEmailToClient(final UserOrderDTO dto) throws MailException {
        final User user = dto.getUser();
        final Order order = dto.getOrder();
        final String text =
                user.getFirstName() + " " + user.getLastName() +
                        ",\n Вы только что заказали услугу стилиста: '" + order.getTitle() + "'.\n" +
                        "Так же, теперь у вас есть личный кабинет на " + siteUrl + "\n" +
                        "Вот ваши логин: " + user.getEmail() + " и пароль: " + user.getPassword();
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("<" + smtpSenderEmail + ">");
        mail.setSubject("Заказ успешно создан.");
        mail.setText(text);
        mailSender.send(mail);
    }

    public void sendEmailToClient(final User user) throws MailException {
        final String text =
                user.getFirstName() + " " + user.getLastName() +
                        ",\n Вас только что зарегистрировали'\n" +
                        "Теперь у вас есть личный кабинет на " + siteUrl + "\n" +
                        "Вот ваши логин: " + user.getEmail() + " и пароль: " + user.getPassword();
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("<" + smtpSenderEmail + ">");
        mail.setSubject("Регистрация.");
        mail.setText(text);
        mailSender.send(mail);
    }
}
