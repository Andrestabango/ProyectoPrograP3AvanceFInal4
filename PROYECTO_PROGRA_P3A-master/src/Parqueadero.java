import java.util.*;

public class Parqueadero {
    private String lugar;
    private int cantidadEspacio;
    private Espacio espacio;
    private Espacio[][] matrizEspacios;
    private boolean[][] disponibilidad;
    private List<Bitacora> matricesBitacora;

    private int id;
    private int numPisos;
    private int numEspaciosPorPiso;
    private EspacioParqueadero[][] espacios;


    public Parqueadero(String lugar, int cantidadEspacio, Espacio espacio) {
        this.lugar = lugar;
        this.cantidadEspacio = cantidadEspacio;
        this.espacio = espacio;
        this.matrizEspacios = calcularEspacios();
        this.disponibilidad = new boolean[espacio.getNivel()][cantidadEspacio];
        for (boolean[] fila : disponibilidad) {
            Arrays.fill(fila, true); // Todos los espacios están disponibles inicialmente
        }
    }

    // Getters y Setters
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCantidadEspacio() {
        return cantidadEspacio;
    }

    public void setCantidadEspacio(int cantidadEspacio) {
        this.cantidadEspacio = cantidadEspacio;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Espacio[][] getMatrizEspacios() {
        return matrizEspacios;
    }

    public void setMatrizEspacios(Espacio[][] matrizEspacios) {
        this.matrizEspacios = matrizEspacios;
    }

    public boolean[][] getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean[][] disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<Bitacora> getMatricesBitacora() {
        return matricesBitacora;
    }

    public void setMatricesBitacora(List<Bitacora> matricesBitacora) {
        this.matricesBitacora = matricesBitacora;
    }

    public Espacio[][] calcularEspacios() {
        int niveles = espacio.getNivel();
        int espaciosPorNivel = cantidadEspacio;
        Espacio[][] matrizEspacios = new Espacio[niveles][espaciosPorNivel];

        int numeracion = 1;
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < espaciosPorNivel; j++) {
                matrizEspacios[i][j] = new Espacio(i + 1, numeracion++);
            }
        }
        return matrizEspacios;
    }

    public void imprimirEspacios() {
        for (Espacio[] nivel : matrizEspacios) {
            for (Espacio espacio : nivel) {
                System.out.print(espacio.getNumeracion() + " ");
            }
            System.out.println();
        }
    }

    // Método para asignar un espacio disponible y marcarlo como ocupado

    /*public int asignarEspacioDisponible() {
        // Crear una lista de espacios disponibles
        List<Espacio> espaciosDisponibles = new ArrayList<>();

        // Agregar todos los espacios disponibles a la lista
        for (int i = 0; i < matrizEspacios.length; i++) {
            for (int j = 0; j < matrizEspacios[i].length; j++) {
                if (disponibilidad[i][j]) {
                    espaciosDisponibles.add(matrizEspacios[i][j]);
                }
            }
        }

        // Verificar si hay espacios disponibles
        if (espaciosDisponibles.isEmpty()) {
            // No hay espacios disponibles
            return -1; // Puedes devolver -1 o algún valor que indique no disponibilidad
        }

        // Elegir un espacio aleatorio de los disponibles
        Random random = new Random();
        int indiceEspacio = random.nextInt(espaciosDisponibles.size());
        Espacio espacioElegido = espaciosDisponibles.get(indiceEspacio);

        // Marcar el espacio elegido como ocupado
        marcarEspacioOcupado(espacioElegido);

        return espacioElegido.getNumeracion();
    }*/

    public Espacio asignarEspacioDisponible() {
        for (int i = 0; i < matrizEspacios.length; i++) {
            for (int j = 0; j < matrizEspacios[i].length; j++) {
                if (disponibilidad[i][j]) {
                    Espacio espacioAsignado = matrizEspacios[i][j];
                    disponibilidad[i][j] = false;
                    return espacioAsignado;
                }
            }
        }
        return null; // No hay espacios disponibles
    }


    // Método privado para marcar un espacio como ocupado
    private void marcarEspacioOcupado(Espacio espacioOcupado) {
        for (int i = 0; i < espacio.getNivel(); i++) {
            for (int j = 0; j < cantidadEspacio; j++) {
                if (matrizEspacios[i][j].getNumeracion() == espacioOcupado.getNumeracion()) {
                    disponibilidad[i][j] = false; // Marcar como no disponible
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return  "Nombre Parqueadero: " + lugar +
                "\nEspacio de parqueadero: " + cantidadEspacio +
                "\nNiveles: " + getEspacio().getNivel() +
                "\nNumero total de espacios: " + (cantidadEspacio * espacio.getNivel());
    }

    // Constructor
    public Parqueadero(int id, int numPisos, int numEspaciosPorPiso, String lugar ) {
        this.id = id;
        this.numPisos = numPisos;
        this.numEspaciosPorPiso = numEspaciosPorPiso;
        this.lugar = lugar;

        this.espacios = new EspacioParqueadero[numPisos][numEspaciosPorPiso];

        // Inicializar los espacios del parqueadero
        for (int i = 0; i < numPisos; i++) {
            for (int j = 0; j < numEspaciosPorPiso; j++) {
                this.espacios[i][j] = new EspacioParqueadero("Carro"); // Puedes ajustar según el tipo de vehículo permitido
            }
        }
    }

    // Método para verificar disponibilidad en un día y hora específica para un espacio específico
    public boolean espacioDisponible(int piso, int espacio, String dia, String hora) {
        if (piso < 0 || piso >= numPisos || espacio < 0 || espacio >= numEspaciosPorPiso) {
            return false; // Fuera de los límites del parqueadero
        }

        return espacios[piso][espacio].estaDisponible(dia, hora);
    }

    // Método para encontrar el próximo espacio disponible en un día y hora específicos
    public int[] encontrarProximoEspacioDisponible(String dia, String hora) {
        for (int i = 0; i < numPisos; i++) {
            for (int j = 0; j < numEspaciosPorPiso; j++) {
                if (espacios[i][j].estaDisponible(dia, hora)) {
                    return new int[]{i, j}; // Devuelve el piso y el espacio disponibles
                }
            }
        }
        return null; // No hay espacio disponible
    }

    // Método para reservar un espacio en un día y hora específica
    public boolean reservarEspacio(int piso, int espacio, String dia, String hora, String placaVehiculo) {
        if (piso < 0 || piso >= numPisos || espacio < 0 || espacio >= numEspaciosPorPiso) {
            return false; // Fuera de los límites del parqueadero
        }

        if (espacios[piso][espacio].estaDisponible(dia, hora)) {
            espacios[piso][espacio].ocupar(dia, hora, placaVehiculo);
            return true; // Espacio reservado exitosamente
        }

        return false; // Espacio ocupado o horario no disponible
    }

// Getters
    public int getId() {
        return id;
    }

    public int getNumPisos() {
        return numPisos;
    }

    public int getNumEspaciosPorPiso() {
        return numEspaciosPorPiso;
    }

    public EspacioParqueadero getEspacio(int piso, int espacio) {
        if (piso < 0 || piso >= numPisos || espacio < 0 || espacio >= numEspaciosPorPiso) {
            return null; // Fuera de los límites del parqueadero
        }
        return espacios[piso][espacio];
    }

}