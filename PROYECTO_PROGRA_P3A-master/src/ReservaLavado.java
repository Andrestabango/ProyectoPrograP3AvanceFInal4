public class ReservaLavado extends Reserva{
    private Lavado servicio;
    private String nombreServidor;

    public ReservaLavado(int dia, int mes, int anio, Lavado servicio, String nombreServidor) {
        super(dia, mes, anio);
        this.servicio = servicio;
        this.nombreServidor = nombreServidor;
    }

    public ReservaLavado(int tiempoReserva, int horaDeReserva, int dia, int mes, int anio, Parqueadero parqueadero, Vehiculo vehiculo, Persona persona, Lavado servicio, String nombreServidor) {
        super(tiempoReserva, horaDeReserva, dia, mes, anio, parqueadero, vehiculo, persona);
        this.servicio = servicio;
        this.nombreServidor = nombreServidor;
    }

    public Lavado getServicio() {
        return servicio;
    }

    public void setServicio(Lavado servicio) {
        this.servicio = servicio;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }

    @Override
    public String toString() {
        return "Reserva de Lavado" +
                "\nTipo de servicio: " + servicio.getTipoServicio() +
                "\nNombre Servidor: " + nombreServidor +
                "\nTarifa a pagar: " + servicio.getTarifa() +
                "\nHorasReserva: " + tiempoReserva +
                "\nDia: " + dia +
                "\nMes: " + mes +
                "\nAño: " + anio +
                "\nParqueadero: " + parqueadero +
                "\nVehiculo: " + vehiculo +
                "\nPersona: " + persona;
    }
}
