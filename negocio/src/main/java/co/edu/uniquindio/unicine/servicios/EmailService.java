package co.edu.uniquindio.unicine.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmail(String asunto, String contenido, String destinatario) {

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setSubject(asunto);
            helper.setText(contenido, true);
            helper.setTo(destinatario);
            helper.setFrom("clientes.unicine@gmail.com");

            javaMailSender.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
