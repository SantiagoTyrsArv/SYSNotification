package u.sysnotification.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificacion_sms")
public class NotificacionSMS extends Notificacion {

    private String operadora;

    public NotificacionSMS() {
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }
}
