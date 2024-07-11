import java.util.Date;
import java.util.Calendar;

public class Reserva {
    protected int tiempoReserva;
    protected int horaDeReserva;
    protected int dia;
    protected int mes;
    protected int anio;
    protected Parqueadero parqueadero;
    protected Vehiculo vehiculo;
    protected Persona persona;

    public Reserva(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Reserva(int tiempoReserva, int horaDeReserva, int dia, int mes, int anio, Parqueadero parqueadero, Vehiculo vehiculo, Persona persona) {
        this.tiempoReserva = tiempoReserva;
        this.horaDeReserva = horaDeReserva;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.parqueadero = parqueadero;
        this.vehiculo = vehiculo;
        this.persona = persona;
    }

    public int getHorasReserva() {
        return tiempoReserva;
    }

    public void setHorasReserva(int tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getHoraDeReserva() {
        return horaDeReserva;
    }

    public void setHoraDeReserva(int horaDeReserva) {
        this.horaDeReserva = horaDeReserva;
    }

    public int getTiempoReserva() {
        return tiempoReserva;
    }

    public void setTiempoReserva(int tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
    }


}
