package u.sysnotification.service.strategy;

import u.sysnotification.model.NotificacionApp;
import org.springframework.stereotype.Component;

@Component
public class EnviadorApp implements EnviadorNotificacion<NotificacionApp> {

    // Simulación del cliente de Firebase
    // private final FirebaseClient firebaseClient;

    @Override
    public void enviar(NotificacionApp notificacion) {
        System.out.println("Preparando notificación Push para el token: " + notificacion.getTokenDispositivo());

        try {
            // Lógica simulada de Firebase Cloud Messaging
            System.out.println("Enviando a Firebase. Mensaje: " + notificacion.getMensaje());
            System.out.println("Notificación Push enviada exitosamente al dispositivo.");
        } catch (Exception e) {
            System.err.println("Error al comunicarse con Firebase");
            throw new RuntimeException("Fallo el envío por App");
        }
    }
}
