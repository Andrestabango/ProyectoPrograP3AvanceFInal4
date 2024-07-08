import java.util.ArrayList;
import java.util.List;

public class ListaReservaLavado {
    private List<ReservaLavado> reservasLavado;
    private ListaReservaParqueadero listaReservaParqueadero;

    public ListaReservaLavado(ListaReservaParqueadero listaReservaParqueadero) {
        this.reservasLavado = new ArrayList<>();
        this.listaReservaParqueadero = listaReservaParqueadero;
    }
    public ListaReservaLavado() {
        this.reservasLavado = new ArrayList<>();
    }
    public List<ReservaLavado> getReservasLavado() {
        return reservasLavado;
    }

    public void setReservasLavado(List<ReservaLavado> reservasLavado) {
        this.reservasLavado = reservasLavado;
    }

    public ReservaLavado buscarReservaLavado(String placaVehiculo, String nombrePersona) {
        for (ReservaLavado reservaLavado : reservasLavado) {
            if (reservaLavado.getVehiculo().getPlaca().equals(placaVehiculo) &&
                    reservaLavado.getPersona().getNombre().equals(nombrePersona)) {
                return reservaLavado;
            }
        }
        return null;
    }

    public void agregarReservaLavado(ReservaLavado reservaLavado) throws Exception {
        // Verificar si existe una reserva de parqueadero correspondiente
        ReservaParqueadero reservaParqueadero = (ReservaParqueadero) listaReservaParqueadero.buscarReserva(
                reservaLavado.getVehiculo().getPlaca(), reservaLavado.getPersona().getNombre()
        );

        if (reservaParqueadero != null) {
            if (buscarReservaLavado(reservaLavado.getVehiculo().getPlaca(), reservaLavado.getPersona().getNombre()) == null) {
                reservasLavado.add(reservaLavado);
            } else {
                throw new Exception("La reserva de lavado para el vehículo con placa " + reservaLavado.getVehiculo().getPlaca() +
                        " y persona " + reservaLavado.getPersona().getNombre() + " ya existe.");
            }
        } else {
            throw new Exception("No existe una reserva de parqueadero para el vehículo con placa " + reservaLavado.getVehiculo().getPlaca() +
                    " y persona " + reservaLavado.getPersona().getNombre() + ".");
        }
    }

    public List<ReservaLavado> listarReservasLavado() {
        return new ArrayList<>(reservasLavado);
    }
}
