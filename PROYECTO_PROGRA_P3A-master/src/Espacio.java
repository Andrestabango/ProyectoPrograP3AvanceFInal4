public class Espacio {
    private int nivel;
    private int numeracion;
    private Vehiculo vehiculo;

    public Espacio() {

    }

    public Espacio(int nivel) {
        this.nivel = nivel;
    }

    public Espacio(int nivel, int numeracion) {
        this.nivel = nivel;
        this.numeracion = numeracion;
    }

    public Espacio(int nivel, int numeracion, Vehiculo vehiculo) {
        this.nivel = nivel;
        this.numeracion = numeracion;
        this.vehiculo = vehiculo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}