import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Persona {

    private String nombre;
    private String id;
    private String tipoPersona;
    private List<Vehiculo> vehiculos;

    public Persona(String nombre, String id, String tipoPersona, List<Vehiculo> vehiculos) {
        this.nombre = nombre;
        this.id = id;
        this.tipoPersona = tipoPersona;
        this.vehiculos = vehiculos;
    }

    public Persona(String nombre, String id, String tipoPersona) {
        this.nombre = nombre;
        this.id = id;
        this.tipoPersona = tipoPersona;
        this.vehiculos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public boolean addVehiculo(Vehiculo vehiculo) {
        if (vehiculos.size() < 2) {
            vehiculos.add(vehiculo);
            return true;
        } else {
            System.out.println("No se pueden agregar más de 2 vehículos.");
            return false;
        }
    }

    public void actualizarComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems(); // Limpiar el ComboBox
        for (Vehiculo vehiculo : vehiculos) {
            comboBox.addItem(vehiculo.getPlaca()); // Agregar cada placa al ComboBox
        }
    }

    @Override
    public String toString() {
        String result = " \n\nNombre: " + nombre +
                " \nId: " + id +
                " \nTipo de Persona: " + tipoPersona;
        for (int i = 0; i < vehiculos.size(); i++) {
            result += " \nVehículo " + (i + 1) +
                    " \n  Placa: " + vehiculos.get(i).getPlaca() +
                    " \n  Tipo de Vehículo: " + vehiculos.get(i).getTipoVehiculo();
        }
        return result;
    }
}
