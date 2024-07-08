import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListaParqueadero {
    private List<Parqueadero> parqueaderos;

    public ListaParqueadero() {
        parqueaderos = new ArrayList<Parqueadero>();
    }

    public List<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    public void setParqueaderos(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

    public Parqueadero buscarParqueadero(String lugar) {
        for (Parqueadero pa : parqueaderos) {
            if (pa.getLugar().equals(lugar)) {
                return pa;
            }
        }
        return null;
    }

    public List<Parqueadero> listarParqueadero() {
        List<Parqueadero> lista = new LinkedList<>();
        for (Parqueadero pa : parqueaderos) {
            lista.add(pa);
        }
        return lista;
    }

    public void agregarParqueaderoP(Parqueadero parqueadero) throws Exception {
        if (buscarParqueadero(parqueadero.getLugar()) == null) {
            parqueaderos.add(parqueadero);
        } else {
            throw new Exception("El lugar ya está registrado");
        }
    }

    public void editarParqueadero(String nombre, int nuevaCantidadEspacio, int nuevoNivel) throws Exception {
        Parqueadero parqueadero = buscarParqueadero(nombre);
        if (parqueadero != null) {
            parqueadero.setCantidadEspacio(nuevaCantidadEspacio);
            if (parqueadero.getEspacio() != null) {
                parqueadero.getEspacio().setNivel(nuevoNivel);
            } else {
                parqueadero.setEspacio(new Espacio(nuevoNivel));
            }
            // Recalcular los espacios y la disponibilidad
            parqueadero.setMatrizEspacios(parqueadero.calcularEspacios());
            parqueadero.setDisponibilidad(new boolean[nuevoNivel][nuevaCantidadEspacio]);
            for (boolean[] fila : parqueadero.getDisponibilidad()) {
                Arrays.fill(fila, true); // Todos los espacios están disponibles inicialmente
            }
        } else {
            throw new Exception("No se encontró ningún parqueadero con el nombre: " + nombre);
        }
    }

    public void eliminarParqueadero(String lugar) throws Exception {
        Parqueadero parqueadero = buscarParqueadero(lugar);
        if (parqueadero != null) {
            parqueaderos.remove(parqueadero);
        } else {
            throw new Exception("El parqueadero " + lugar + " no existe.");
        }
    }

    public void actualizarDisponibilidad(String nombreParqueadero, int numeracionEspacio, boolean ocupado) throws Exception {
        Parqueadero parqueadero = buscarParqueadero(nombreParqueadero);
        if (parqueadero != null) {
            actualizarEspacio(parqueadero, numeracionEspacio, ocupado);
        } else {
            throw new Exception("Parqueadero no encontrado");
        }
    }

    private void actualizarEspacio(Parqueadero parqueadero, int numeracionEspacio, boolean ocupado) throws Exception {
        Espacio espacio = buscarEspacio(parqueadero, numeracionEspacio);
        if (espacio != null) {
            if (ocupado) {
                marcarEspacioOcupado(parqueadero, espacio);
            } else {
                liberarEspacio(parqueadero, espacio);
            }
        } else {
            throw new Exception("Espacio no encontrado");
        }
    }

    private Espacio buscarEspacio(Parqueadero parqueadero, int numeracionEspacio) {
        Espacio[][] matrizEspacios = parqueadero.getMatrizEspacios();
        for (int i = 0; i < matrizEspacios.length; i++) {
            for (int j = 0; j < matrizEspacios[i].length; j++) {
                if (matrizEspacios[i][j].getNumeracion() == numeracionEspacio) {
                    return matrizEspacios[i][j];
                }
            }
        }
        return null;
    }

    private void marcarEspacioOcupado(Parqueadero parqueadero, Espacio espacioOcupado) {
        int nivel = espacioOcupado.getNivel() - 1; // Ajustar nivel a índice de matriz
        int numeracion = espacioOcupado.getNumeracion() - 1; // Ajustar numeración a índice de matriz
        parqueadero.getDisponibilidad()[nivel][numeracion] = false; // Marcar como no disponible
    }

    private void liberarEspacio(Parqueadero parqueadero, Espacio espacioLiberado) {
        int nivel = espacioLiberado.getNivel() - 1; // Ajustar nivel a índice de matriz
        int numeracion = espacioLiberado.getNumeracion() - 1; // Ajustar numeración a índice de matriz
        parqueadero.getDisponibilidad()[nivel][numeracion] = true; // Marcar como disponible
    }


    /*public void actualizarDisponibilidad(String nombreParqueadero, boolean ocupado) throws Exception {
        Parqueadero parqueadero = buscarParqueadero(nombreParqueadero);
        if (parqueadero != null) {
            parqueadero.actualizarEspacios(ocupado);
        } else {
            throw new Exception("Parqueadero no encontrado");
        }
    }*/
}
