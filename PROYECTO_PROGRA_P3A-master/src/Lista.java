import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lista {

    private List<Persona> personas;

    public Lista() {
        personas = new ArrayList<>();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void adicionarElementos(Persona p) throws Exception {
        if (buscarPersona(p.getId()) == null) {
            personas.add(p);
        } else {
            throw new Exception("Persona con este ID de banner ya existe más de dos veces");
        }
    }

    public List<Persona> listarPersonas() {
        List<Persona> lista = new LinkedList<>();
        for (Persona e : personas)
            lista.add(e);
        return lista;
    }

    public Persona buscarPersona(String id) {
        for (Persona p : personas)
            if (p.getId().equals(id))
                return p;
        return null;
    }

    public void editar(String nuevoNombre, String id, String nuevoTipoPersona) throws Exception {
        Persona persona = buscarPersona(id);
        if (persona != null) {
            persona.setNombre(nuevoNombre);
            persona.setTipoPersona(nuevoTipoPersona);
        } else {
            throw new Exception("No se encontró ninguna perona con el id : " + id);
        }
    }

    public void eliminarPersona(String id) throws Exception {
        Persona persona = buscarPersona(id);
        if (persona != null) {
            personas.remove(persona);
        } else {
            throw new Exception("La persona con el ID " + id + " no está en la lista.");
        }
    }

    public void eliminarVehiculoPorPlaca(String placa) throws Exception {
        boolean vehiculoEliminado = false;
        for (Persona persona : personas) {
            List<Vehiculo> vehiculos = persona.getVehiculos();
            for (int i = 0; i < vehiculos.size(); i++) {
                if (vehiculos.get(i).getPlaca().equals(placa)) {
                    vehiculos.remove(i);
                    vehiculoEliminado = true;
                }
            }
        }
        if (!vehiculoEliminado) {
            throw new Exception("No se encontró ningún vehículo con la placa: " + placa);
        }
    }


    public void agregarVehiculoAPersona(String idPersona, Vehiculo vehiculo) throws Exception {
        if (!validarPlaca(vehiculo.getPlaca())){
            throw new Exception("LA placa no es valida");
        }
        Persona persona = buscarPersona(idPersona);
        if (persona != null) {
            persona.addVehiculo(vehiculo);
        } else {
            throw new Exception("La persona con el ID " + idPersona + " no está en la lista.");
        }
    }
    public boolean validarPlaca(String placa){
        String regex = "^[A-Za-z]{3}\\d{4}$";
        return  placa.matches(regex);
    }

    public Persona buscarPersonaNombre(String nombre) {
        for (Persona p : personas)
            if (p.getNombre().equals(nombre))
                return p;
        return null;
    }
}
