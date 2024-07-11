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


    public void editarReserva(String placaVehiculo, String nombrePersona, Parqueadero nuevoParqueadero, int nuevaHoraDeReserva, int nuevoDia, int nuevoMes, int nuevoAnio, int nuevaHoraDeIngreso) throws Exception {
        ReservaParqueadero reserva = (ReservaParqueadero) buscarReserva(placaVehiculo, nombrePersona);
        if (reserva != null) {
            reserva.setDia(nuevoDia);
            reserva.setMes(nuevoMes);
            reserva.setAnio(nuevoAnio);
            reserva.setHoraDeReserva(nuevaHoraDeIngreso);
            reserva.setParqueadero(nuevoParqueadero);
            reserva.setTiempoReserva(nuevaHoraDeReserva);
        } else {
            throw new Exception("No se encontró ninguna reserva para el vehículo con placa " + placaVehiculo + " y persona " + nombrePersona);
        }
    }


}