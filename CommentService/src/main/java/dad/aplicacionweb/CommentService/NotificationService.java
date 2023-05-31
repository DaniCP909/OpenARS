package dad.aplicacionweb.CommentService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    @Autowired
    private JavaMailSender emailSender;

    @RabbitListener
    public void sendSimpleMessage(CommentInfoDto commentInfoDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@bealdung.com");    //¿Se puede cambiar?
        message.setTo(commentInfoDto.getEmaildest());
        message.setSubject("Nuevo comentario en tu recurso");
        message.setText(commentInfoDto.getSender() + " ha comentado en tu recurso "+ commentInfoDto.getResourcename());
        emailSender.send(message);
    }
}
