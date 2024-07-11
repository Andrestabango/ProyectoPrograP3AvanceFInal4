import java.util.ArrayList;
import java.util.List;

class ArbolBinarioReserva {
    private NodoArbol raiz;

    public ArbolBinarioReserva() {
        this.raiz = null;
    }

    public void agregarReserva(ReservaParqueadero reserva) {
        raiz = agregarReservaRecursivo(raiz, reserva);
    }

    private NodoArbol agregarReservaRecursivo(NodoArbol nodo, ReservaParqueadero reserva) {
        if (nodo == null) {
            return new NodoArbol(reserva);
        }

        if (reserva.getHoraDeReserva() < nodo.reserva.getHoraDeReserva()) {
            nodo.izquierdo = agregarReservaRecursivo(nodo.izquierdo, reserva);
        } else if (reserva.getHoraDeReserva() > nodo.reserva.getHoraDeReserva()) {
            nodo.derecho = agregarReservaRecursivo(nodo.derecho, reserva);
        } else {
            // Si la hora de reserva es la misma, decidir según otro criterio o no hacer nada.
        }

        return nodo;
    }

    public List<ReservaParqueadero> obtenerReservasInOrden() {
        List<ReservaParqueadero> listaReservas = new ArrayList<>();
        obtenerReservasInOrdenRecursivo(raiz, listaReservas);
        return listaReservas;
    }

    private void obtenerReservasInOrdenRecursivo(NodoArbol nodo, List<ReservaParqueadero> listaReservas) {
        if (nodo != null) {
            obtenerReservasInOrdenRecursivo(nodo.izquierdo, listaReservas);
            listaReservas.add(nodo.reserva);
            obtenerReservasInOrdenRecursivo(nodo.derecho, listaReservas);
        }
    }
}
