import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Horario {
    private HashMap<String, Boolean> disponibilidadPorHora;

    // Constructor
    public Horario() {
        disponibilidadPorHora = new HashMap<>();
        // Inicializar disponibilidad de 7am a 9pm
        for (int hora = 7; hora <= 21; hora++) {
            disponibilidadPorHora.put(String.valueOf(hora), true);
        }
    }

    // Método para verificar disponibilidad en una hora específica
    public boolean estaDisponible(String hora) {
        return disponibilidadPorHora.containsKey(hora) && disponibilidadPorHora.get(hora);
    }

    // Método para reservar una hora
    public void reservar(String hora) {
        if (disponibilidadPorHora.containsKey(hora)) {
            disponibilidadPorHora.put(hora, false);
        }
    }

    // Método para liberar una hora
    public void liberar(String hora) {
        if (disponibilidadPorHora.containsKey(hora)) {
            disponibilidadPorHora.put(hora, true);
        }
    }

    // Método para obtener las horas reservadas
    public List<String> getHorasReservadas() {
        List<String> horasReservadas = new ArrayList<>();
        for (String hora : disponibilidadPorHora.keySet()) {
            if (!disponibilidadPorHora.get(hora)) {
                horasReservadas.add(hora);
            }
        }
        return horasReservadas;
    }

}
