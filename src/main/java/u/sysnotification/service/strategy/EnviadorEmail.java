package u.sysnotification.service.strategy;

import u.sysnotification.model.NotificacionEmail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviadorEmail implements EnviadorNotificacion<NotificacionEmail> {

    private final JavaMailSender mailSender;

    // Spring inyecta automáticamente el JavaMailSender
    public EnviadorEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviar(NotificacionEmail notificacion) {
        System.out.println("Preparando envío de Email a: " + notificacion.getDestinatario());

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(notificacion.getDestinatario());
            mailMessage.setSubject(notificacion.getAsunto()); // ¡Sin casteos gracias al genérico!
            mailMessage.setText(notificacion.getMensaje());

            // mailSender.send(mailMessage); // Descomentar para enviar correos reales

            System.out.println("Email enviado exitosamente con asunto: " + notificacion.getAsunto());
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            throw new RuntimeException("Fallo el envío por Email");
        }
    }
}
