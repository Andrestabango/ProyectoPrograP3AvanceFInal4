import javax.swing.*;
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


    // Método para llenar la JList con la lista de reservas ordenadas
    public void llenarJlistReserva(List<ReservaParqueadero> reservasOrdenadas) {
        DefaultListModel<ReservaParqueadero> dl1 = new DefaultListModel<>();
        for (ReservaParqueadero reserva : reservasOrdenadas) {
            dl1.addElement(reserva);
        }
        list3Reserva.setModel(dl1);
    }

    // Método para asignar el JList, debes asegurarte que list3Reserva esté inicializado
    private JList<ReservaParqueadero> list3Reserva;

    public void setList3Reserva(JList<ReservaParqueadero> list3Reserva) {
        this.list3Reserva = list3Reserva;
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
    public List<ReservaParqueadero> obtenerReservasOrdenadasPorHora() {
        ArbolBinarioReserva arbol = new ArbolBinarioReserva();
        for (ReservaParqueadero reserva : reservas) {
            arbol.agregarReserva(reserva);
        }
        return arbol.obtenerReservasInOrden();
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


    public void mostrarReservasEnOrdenPorHora() {
        ArbolBinarioReserva arbol = new ArbolBinarioReserva();
        for (ReservaParqueadero reserva : reservas) {
            arbol.agregarReserva(reserva);
        }
        List<ReservaParqueadero> reservasOrdenadas = arbol.obtenerReservasInOrden();
        imprimirReservas(reservasOrdenadas);
    }

    public void imprimirReservas(List<ReservaParqueadero> reservas) {
        for (ReservaParqueadero reserva : reservas) {
            System.out.println(reserva);
        }
    }


}