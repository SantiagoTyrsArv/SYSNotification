package u.sysnotification.service;

import u.sysnotification.model.Notificacion;
import u.sysnotification.model.enums.EstadoNotificacion;
import u.sysnotification.repository.NotificacionRepository;
import u.sysnotification.service.factory.EnviadorFactory;
import u.sysnotification.service.strategy.EnviadorNotificacion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;
    private final EnviadorFactory enviadorFactory;

    // Inyección de dependencias por constructor
    public NotificacionService(NotificacionRepository repository, EnviadorFactory enviadorFactory) {
        this.repository = repository;
        this.enviadorFactory = enviadorFactory;
    }

    public void procesarNotificacion(Notificacion notificacion) {
        // 1. Inicializar datos de control
        if (notificacion.getCodigo() == null || notificacion.getCodigo().isEmpty()) {
            notificacion.setCodigo(UUID.randomUUID().toString()); // Generar un ID único
        }
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setEstado(EstadoNotificacion.PENDIENTE);

        // Guardamos el estado inicial en la base de datos
        notificacion = repository.save(notificacion);

        try {
            // 2. Obtener el enviador correcto según la clase de la notificación (Polimorfismo puro)
            EnviadorNotificacion<?> enviador = enviadorFactory.obtenerEnviador(notificacion.getClass());

            // 3. Ejecutar el envío solucionando el tipado genérico
            ejecutarEnvio(enviador, notificacion);

            // 4. Si no hubo excepciones, actualizamos a éxito
            notificacion.setEstado(EstadoNotificacion.ENVIADA);

        } catch (Exception e) {
            // 5. Si algo falló, actualizamos a error
            notificacion.setEstado(EstadoNotificacion.ERROR);
            System.err.println("Fallo al procesar la notificación: " + e.getMessage());
        }

        // 6. Persistir el estado final
        repository.save(notificacion);
    }

    /**
     * Método auxiliar para resolver el problema de Type Erasure (Borrado de Tipos) en Java.
     * Convence al compilador de que el enviador y la notificación comparten el mismo tipo genérico <T>.
     */
    @SuppressWarnings("unchecked")
    private <T extends Notificacion> void ejecutarEnvio(EnviadorNotificacion<?> enviador, Notificacion notificacion) {
        EnviadorNotificacion<T> enviadorTipado = (EnviadorNotificacion<T>) enviador;
        enviadorTipado.enviar((T) notificacion);
    }
}