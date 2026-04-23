package u.sysnotification.service.strategy;

import u.sysnotification.model.Notificacion;

public interface EnviadorNotificacion<T extends Notificacion> {

    void enviar(T notificacion);

}