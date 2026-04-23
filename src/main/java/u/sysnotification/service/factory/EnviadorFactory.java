package u.sysnotification.service.factory;

import u.sysnotification.model.NotificacionApp;
import u.sysnotification.model.NotificacionEmail;
import u.sysnotification.model.NotificacionSMS;
import u.sysnotification.service.strategy.EnviadorApp;
import u.sysnotification.service.strategy.EnviadorEmail;
import u.sysnotification.service.strategy.EnviadorNotificacion;
import u.sysnotification.service.strategy.EnviadorSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviadorFactory {

    private final Map<Class<?>, EnviadorNotificacion<?>> enviadores;

    /**
     * Spring inyecta automáticamente todos los enviadores creados gracias a @Component.
     * Nosotros simplemente los registramos en el Map vinculándolos a su clase de modelo.
     */
    @Autowired
    public EnviadorFactory(EnviadorEmail enviadorEmail,
                           EnviadorSMS enviadorSMS,
                           EnviadorApp enviadorApp) {
        enviadores = new HashMap<>();
        enviadores.put(NotificacionEmail.class, enviadorEmail);
        enviadores.put(NotificacionSMS.class, enviadorSMS);
        enviadores.put(NotificacionApp.class, enviadorApp);
    }

    /**
     * Obtiene la estrategia de envío correcta basándose en el tipo de notificación.
     * * @param tipo La clase hija de Notificacion (Ej: NotificacionEmail.class)
     * @return El enviador correspondiente, o lanza una excepción si no existe.
     */
    public EnviadorNotificacion<?> obtenerEnviador(Class<?> tipo) {
        EnviadorNotificacion<?> enviador = enviadores.get(tipo);
        if (enviador == null) {
            throw new IllegalArgumentException("No se encontró un enviador para el tipo: " + tipo.getSimpleName());
        }
        return enviador;
    }
}