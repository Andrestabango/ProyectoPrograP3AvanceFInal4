import java.time.LocalDateTime;
import java.time.Duration;

public class RegistroParqueo {
    private String placaVehiculo;
    private int  horaIngreso;
    private int  horaSalida;
    private double valorPorHora;
    private double recargoPorHoraExtra;
    private int horasReservadas;
    private int piso;
    private int espacio;


    // Constructor
    public RegistroParqueo(String placaVehiculo, int horaIngreso, int horasReservadas, double valorPorHora, double recargoPorHoraExtra, int piso, int espacio) {
        this.placaVehiculo = placaVehiculo;
        this.horaIngreso = horaIngreso;
        this.horasReservadas = horasReservadas;
        this.valorPorHora = valorPorHora;
        this.recargoPorHoraExtra = recargoPorHoraExtra;
        this.piso = piso;
        this.espacio = espacio;
        this.horaSalida = 0; // Se establece cuando el vehículo sale
    }

    // Métodos para registrar la salida del vehículo
    public void registrarSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    // Método para calcular el valor a pagar
    public double calcularValorPago() {
        if (horaSalida == 0) {
            throw new IllegalStateException("El vehículo aún no ha salido.");
        }

        int duracion = (horaSalida - horaIngreso);
        long horasEstacionadas = duracion;

        double valorTotal = 0.0;

        if (horasEstacionadas <= horasReservadas) {
            valorTotal = horasEstacionadas * valorPorHora;
        } else {
            long horasExtra = horasEstacionadas - horasReservadas;
            valorTotal = (horasReservadas * valorPorHora) + (horasExtra * recargoPorHoraExtra);
        }

        return valorTotal;
    }

    // Getters
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public int getHoraIngreso() {
        return horaIngreso;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public int getHorasReservadas() {
        return horasReservadas;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public double getRecargoPorHoraExtra() {
        return recargoPorHoraExtra;
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
