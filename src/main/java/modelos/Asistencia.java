
package modelos;

import java.sql.Date;

/**
 * Modelo para las asistencias de los usuarios
 * @author MINEDUCYT
 */
public class Asistencia {
    private Long id;
    private Long idUsuario;
    private Date fecha; // Puedes cambiar esto a Timestamp si necesitas manejar fechas y horas completas
    private String horaEntrada;
    private String horaSalida;
    private String nombreUsuario;

    // Constructor vacío
    public Asistencia() {
    }

    // Constructor con todos los parámetros
    public Asistencia(Long id, Long idUsuario, Date fecha, String horaEntrada, String horaSalida) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    // Sobrescribir el método toString para facilitar la depuración
    @Override
    public String toString() {
        return "Asistencia [id=" + id + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + "]";
    }
}
