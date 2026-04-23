package u.sysnotification.service.strategy;

import u.sysnotification.model.NotificacionSMS;
import org.springframework.stereotype.Component;

@Component
public class EnviadorSMS implements EnviadorNotificacion<NotificacionSMS> {

    // Simulación del cliente (Ej: TwilioSmsClient)
    // private final SmsClient smsClient;

    @Override
    public void enviar(NotificacionSMS notificacion) {
        System.out.println("Preparando envío de SMS al número: " + notificacion.getDestinatario());

        try {
            // Lógica simulada del cliente SMS
            System.out.println("Conectando con la operadora: " + notificacion.getOperadora());
            System.out.println("SMS enviado exitosamente: " + notificacion.getMensaje());
        } catch (Exception e) {
            System.err.println("Error al enviar el SMS");
            throw new RuntimeException("Fallo el envío por SMS");
        }
    }
}
