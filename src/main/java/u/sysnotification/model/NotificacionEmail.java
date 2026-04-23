package u.sysnotification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificacion_email")
public class NotificacionEmail extends Notificacion {

    private String asunto;

    public NotificacionEmail() {
        // Constructor vacío requerido por JPA
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
}