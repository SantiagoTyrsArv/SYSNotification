package u.sysnotification.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificacion_app")
public class NotificacionApp extends Notificacion {

    @Column(name = "token_dispositivo")
    private String tokenDispositivo;

    public NotificacionApp() {
    }

    public String getTokenDispositivo() {
        return tokenDispositivo;
    }

    public void setTokenDispositivo(String tokenDispositivo) {
        this.tokenDispositivo = tokenDispositivo;
    }
}
