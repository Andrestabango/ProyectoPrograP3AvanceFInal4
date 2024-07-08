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

    public void generarBitacora(int horaDeReserva, int tiempoReserva, int dia, int mes, int anio, Vehiculo vehiculo) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anio, mes, dia);
        Date fechaReserva = calendar.getTime();

        // Buscar si existe una bitácora para la fecha dada
        Bitacora bitacora = null;
        for (Bitacora b : parqueadero.getMatricesBitacora()) {
            if (b.getFecha().equals(fechaReserva)) {
                bitacora = b;
                break;
            }
        }

        // Si no existe una bitácora, crear una nueva
        if (bitacora == null) {
            int horasDeApertura = 14; // Horario del parqueadero 7am a 9pm
            Vehiculo[][] vehiculoMatriz = new Vehiculo[horasDeApertura][parqueadero.getCantidadEspacio()];
            bitacora = new Bitacora(fechaReserva, vehiculoMatriz);
            parqueadero.getMatricesBitacora().add(bitacora);
        }

        // Asignar el espacio
        Vehiculo[][] vehiculoMatriz = bitacora.getVehiculoMatriz();
        int horaIndex = horaDeReserva - 7; // Convertir la hora de reserva al índice de la matriz
        if (horaIndex < 0 || horaIndex >= vehiculoMatriz.length) {
            throw new IllegalArgumentException("La hora de reserva está fuera del horario permitido");
        }

        // Verificar disponibilidad y asignar
        for (int i = 0; i < parqueadero.getCantidadEspacio(); i++) {
            boolean espacioDisponible = true;
            for (int j = 0; j < tiempoReserva; j++) {
                if (horaIndex + j >= vehiculoMatriz.length || vehiculoMatriz[horaIndex + j][i] != null) {
                    espacioDisponible = false;
                    break;
                }
            }
            if (espacioDisponible) {
                for (int j = 0; j < tiempoReserva; j++) {
                    vehiculoMatriz[horaIndex + j][i] = vehiculo;
                }
                System.out.println("Espacio asignado: Nivel " + (i / parqueadero.getCantidadEspacio() + 1) + ", Espacio " + (i % parqueadero.getCantidadEspacio() + 1));
                return;
            }
        }

        System.out.println("No hay espacios disponibles para la reserva solicitada");
    }
}
