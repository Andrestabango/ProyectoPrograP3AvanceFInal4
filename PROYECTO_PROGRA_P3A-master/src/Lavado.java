public class Lavado {
    private String tipoServicio;
    private double tarifa;
    public Lavado(){

    }

    public Lavado(String tipoServicio, double tarifa) {
        this.tipoServicio = tipoServicio;
        this.tarifa = tarifa;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
}
