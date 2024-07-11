import java.util.HashMap;

public class EspacioParqueadero {
    private boolean ocupado;
    private String tipoVehiculo;
    private String placaVehiculo; // Nueva variable para almacenar la placa del vehículo
    private HashMap<String, Horario> disponibilidadPorDia; // Disponibilidad por día

    // Constructor
    public EspacioParqueadero(String tipoVehiculo) {
        this.ocupado = false;
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = ""; // Inicialmente, no hay vehículo

        // Inicializar disponibilidad para cada día de la semana (lunes a domingo)
        this.disponibilidadPorDia = new HashMap<>();
        String[] diasSemana = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        for (String dia : diasSemana) {
            this.disponibilidadPorDia.put(dia, new Horario());
        }
    }

    // Métodos para consultar y cambiar el estado del espacio
    public boolean estaOcupado() {
        return ocupado;
    }

    public void ocupar(String dia, String hora, String placaVehiculo) {
        this.ocupado = true;
        this.placaVehiculo = placaVehiculo;
        Horario horarios = disponibilidadPorDia.get(dia);
        horarios.reservar(hora); // Marcar hora como no disponible
    }



    public boolean estaDisponible(String dia, String hora) {
        Horario horarios = disponibilidadPorDia.get(dia);
        return horarios.estaDisponible(hora);
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    // Método para obtener el horario de un día específico
    public Horario getHorario(String dia) {
        return this.disponibilidadPorDia.get(dia);
    }
}
