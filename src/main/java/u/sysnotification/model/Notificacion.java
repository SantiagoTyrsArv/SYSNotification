package u.sysnotification.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import u.sysnotification.model.enums.EstadoNotificacion;
import u.sysnotification.model.enums.TipoSituacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoMedio",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NotificacionEmail.class, name = "EMAIL"),
        @JsonSubTypes.Type(value = NotificacionSMS.class, name = "SMS"),
        @JsonSubTypes.Type(value = NotificacionApp.class, name = "APP")
})
public abstract class Notificacion {

    @Id
    protected String codigo;

    @Column(name = "tipo_medio")
    protected String tipoMedio;

    protected String destinatario;

    protected String mensaje;

    @Column(name = "fecha_envio")
    protected LocalDateTime fechaEnvio;

    @Enumerated(EnumType.STRING)
    protected EstadoNotificacion estado = EstadoNotificacion.PENDIENTE; // Estado por defecto

    @Enumerated(EnumType.STRING)
    protected TipoSituacion situacion;

    // Constructor vacío obligatorio para JPA
    public Notificacion() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoMedio() {
        return tipoMedio;
    }

    public void setTipoMedio(String tipoMedio) {
        this.tipoMedio = tipoMedio;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public EstadoNotificacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoNotificacion estado) {
        this.estado = estado;
    }

    public TipoSituacion getSituacion() {
        return situacion;
    }

    public void setSituacion(TipoSituacion situacion) {
        this.situacion = situacion;
    }
}