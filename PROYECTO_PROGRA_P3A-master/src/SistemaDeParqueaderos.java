import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SistemaDeParqueaderos {
    private ArrayList<Parqueadero> parqueaderos;
    private ArrayList<RegistroParqueo> registros;
    // Constructor
    public SistemaDeParqueaderos() {
        this.parqueaderos = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    // Método para agregar un parqueadero
    public void agregarParqueadero(Parqueadero parqueadero) {
        this.parqueaderos.add(parqueadero);
    }

    // Método para buscar un parqueadero por su ID
    public Parqueadero buscarParqueaderoPorId(int idParqueadero) {
        for (Parqueadero parqueadero : parqueaderos) {
            if (parqueadero.getId() == idParqueadero) {
                return parqueadero;
            }
        }
        return null; // Parqueadero no encontrado
    }

    // Método para buscar un parqueadero por su ID
    public Parqueadero buscarParqueaderoPorLugar(String nombre) {
        for (Parqueadero parqueadero : parqueaderos) {
            if (Objects.equals(parqueadero.getLugar(), nombre)) {
                return parqueadero;
            }
        }
        return null; // Parqueadero no encontrado
    }

    // Método para reservar un espacio en un parqueadero específico
    public boolean reservarEspacio(int idParqueadero, int piso, int espacio, String dia, String hora, String placaVehiculo) {
        Parqueadero parqueadero = buscarParqueaderoPorId(idParqueadero);
        if (parqueadero != null) {
            return parqueadero.reservarEspacio(piso, espacio, dia, hora, placaVehiculo);
        }
        return false; // Parqueadero no encontrado
    }

    // Método para agendar un espacio automáticamente
    public int[] agendarEspacioAutomatico(int idParqueadero, String dia, String hora, String placaVehiculo) {
        Parqueadero parqueadero = buscarParqueaderoPorId(idParqueadero);
            int[] espacio = parqueadero.encontrarProximoEspacioDisponible(dia, hora);
            if (espacio != null) {
                boolean reservado = parqueadero.reservarEspacio(espacio[0], espacio[1], dia, hora, placaVehiculo);
                if (reservado) {
                    return espacio; // Retorna el piso y el espacio reservado
                }
            }

        return null; // No se pudo reservar un espacio
    }

    // Método para registrar la salida de un vehículo
    public boolean registrarSalida(String placaVehiculo, int fechaHoraSalida) {
        for (RegistroParqueo registro : registros) {
            if (registro.getPlacaVehiculo().equals(placaVehiculo) && registro.getHoraSalida() == 0) {
                registro.registrarSalida(fechaHoraSalida);
                return true;
            }
        }
        return false; // No se encontró el registro del vehículo o ya ha salido
    }

    // Método para calcular el valor a pagar de un vehículo
    public double calcularValorPago(String placaVehiculo) {
        for (RegistroParqueo registro : registros) {
            if (registro.getPlacaVehiculo().equals(placaVehiculo) && registro.getHoraSalida() != 0) {
                return registro.calcularValorPago();
            }
        }
        throw new IllegalArgumentException("No se encontró el registro del vehículo o el vehículo aún no ha salido.");
    }

    // Nuevo método para buscar las reservas por placa, ID del parqueadero y fecha
    public List<RegistroParqueo> buscarReservasPorPlaca(int idParqueadero, String placaVehiculo , String dia) {
        Parqueadero parqueadero = buscarParqueaderoPorId(idParqueadero);
        List<RegistroParqueo> reservas = new ArrayList<>();

        if (parqueadero != null) {
            int numPisos = parqueadero.getNumPisos();
            int numEspaciosPorPiso = parqueadero.getNumEspaciosPorPiso();

            for (int piso = 0; piso < numPisos; piso++) {
                for (int espacio = 0; espacio < numEspaciosPorPiso; espacio++) {
                    EspacioParqueadero espacioParqueadero = parqueadero.getEspacio(piso, espacio);
                    if (espacioParqueadero != null && espacioParqueadero.getPlacaVehiculo().equals(placaVehiculo)) {
                        Horario horario = espacioParqueadero.getHorario(dia);
                        List<String> horasReservadas = horario.getHorasReservadas();
                        for (String hora : horasReservadas) {
                            RegistroParqueo registroParqueo = new RegistroParqueo(placaVehiculo, Integer.parseInt(hora), 1, 0.75, 1.50,piso,espacio);
                            reservas.add(registroParqueo);
                            //reservas.add("Piso: " + piso + ", Espacio: " + espacio + ", Hora: " + hora);
                        }
                    }
                }
            }
        }

        return reservas;
    }



}
