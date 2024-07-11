    class NodoArbol {
        ReservaParqueadero reserva;
        NodoArbol izquierdo;
        NodoArbol derecho;

        public NodoArbol(ReservaParqueadero reserva) {
            this.reserva = reserva;
            this.izquierdo = null;
            this.derecho = null;
        }
    }