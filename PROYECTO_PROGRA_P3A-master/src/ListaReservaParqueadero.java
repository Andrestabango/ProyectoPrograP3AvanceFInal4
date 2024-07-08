import java.util.ArrayList;
import java.util.List;

public class ListaReservaParqueadero {
    private List<ReservaParqueadero> reservas;

    public ListaReservaParqueadero() {
        this.reservas = new ArrayList<>();
    }

    public List<ReservaParqueadero> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaParqueadero> reservas) {
        this.reservas = reservas;
    }

    public Reserva buscarReserva(String placaVehiculo, String nombrePersona) {
        for (Reserva reserva : reservas) {
            if (reserva.getVehiculo().getPlaca().equals(placaVehiculo) &&
                    reserva.getPersona().getNombre().equals(nombrePersona)) {
                return reserva;
            }
        }
        return null;
    }

    public List<ReservaParqueadero> listarReservas() {
        List<ReservaParqueadero> lista = new ArrayList<>();
        for (ReservaParqueadero reserva : reservas) {
            lista.add(reserva);
        }
        return lista;
    }

    public void agregarReserva(ReservaParqueadero reserva) throws Exception {
        if (buscarReserva(reserva.getVehiculo().getPlaca(), reserva.getPersona().getNombre()) == null) {
            reservas.add(reserva);
        } else {
            throw new Exception("La reserva para el vehículo con placa " + reserva.getVehiculo().getPlaca() +
                    " y persona " + reserva.getPersona().getNombre() + " ya existe.");
        }
    }

    public void eliminarReserva(String placaVehiculo, String nombrePersona) throws Exception {
        ReservaParqueadero reservaEliminar = null;
        for (ReservaParqueadero reserva : reservas) {
            if (reserva.getVehiculo().getPlaca().equals(placaVehiculo) &&
                    reserva.getPersona().getNombre().equals(nombrePersona)) {
                reservaEliminar = reserva;
                break;
            }
        }

        if (reservaEliminar != null) {
            reservas.remove(reservaEliminar);
        } else {
            throw new Exception("No se encontró ninguna reserva para el vehículo con placa " +
                    placaVehiculo + " y persona " + nombrePersona);
        }
    }


    public void editarReserva(String placaVehiculo, String nombrePersona, int nuevasHorasReserva) throws Exception {
        Reserva reserva = buscarReserva(placaVehiculo, nombrePersona);
        if (reserva != null) {
            reserva.setHorasReserva(nuevasHorasReserva);
        } else {
            throw new Exception("No se encontró ninguna reserva para el vehículo con placa " +
                    placaVehiculo + " y persona " + nombrePersona);
        }
    }

    public double calcularCostoReserva(Reserva reserva) {
        double costoBase = 0.75; // Costo base por hora
        return costoBase * reserva.getHorasReserva();
    }

    public int obtenerPuestosLibres(Parqueadero parqueadero) {
        return obtenerPuestosLibresRecursivo(parqueadero, 0, 0);
    }

    private int obtenerPuestosLibresRecursivo(Parqueadero parqueadero, int nivel, int espacio) {
        if (nivel == parqueadero.getEspacio().getNivel()) {
            return 0;
        }
        if (espacio == parqueadero.getCantidadEspacio()) {
            return obtenerPuestosLibresRecursivo(parqueadero, nivel + 1, 0);
        }
        if (parqueadero.getDisponibilidad()[nivel][espacio]) {
            return 1 + obtenerPuestosLibresRecursivo(parqueadero, nivel, espacio + 1);
        } else {
            return obtenerPuestosLibresRecursivo(parqueadero, nivel, espacio + 1);
        }
    }
}