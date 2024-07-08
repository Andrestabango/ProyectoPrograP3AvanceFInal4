import java.util.Arrays;
import java.util.Date;

public class Bitacora {
    private Date fecha;
    private Vehiculo[][] vehiculoMatriz;
    public Bitacora(){

    }
    public Bitacora(Date fecha, Vehiculo[][] vehiculoMatriz) {
        this.fecha = fecha;
        this.vehiculoMatriz = vehiculoMatriz;
    }
    public Bitacora(Date fecha, int espacios) {
        this.fecha = fecha;
        this.vehiculoMatriz = new Vehiculo[10][espacios];
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Vehiculo[][] getVehiculoMatriz() {
        return vehiculoMatriz;
    }

    public void setVehiculoMatriz(Vehiculo[][] vehiculoMatriz) {
        this.vehiculoMatriz = vehiculoMatriz;
    }

    @Override
    public String toString() {
        return "Bitacora\n" +
                "Fecha= " + fecha +
                "Vehiculos Matriz= " + Arrays.toString(vehiculoMatriz);
    }
}
