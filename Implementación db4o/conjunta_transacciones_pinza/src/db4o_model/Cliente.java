package db4o_model;

import java.util.HashSet;
import java.util.Set;

public class Cliente extends Persona {
    private String direccion;
    private String correoElectronico;
    private String telefono;

    // Relación: set de Ingreso (consta)
    private Set<Ingreso> consta = new HashSet<>();

    public Cliente() {
        super();
    }

    public Cliente(String cedula, String nombre, String apellido, String direccion, String correoElectronico, String telefono) {
        super(cedula, nombre, apellido);
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    // Getters & Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Ingreso> getConsta() {
        return consta;
    }

    public void setConsta(Set<Ingreso> consta) {
        this.consta = consta;
    }

    // Add a single ingreso to the consta collection and maintain the inverse relationship
    public void addIngreso(Ingreso ingreso) {
        consta.add(ingreso);
        ingreso.setClienteRegistra(this);
    }

    // Remove a single ingreso and maintain inverse relationship
    public void removeIngreso(Ingreso ingreso) {
        consta.remove(ingreso);
        ingreso.setClienteRegistra(null);
    }

    // Método: devuelve la dirección de este cliente, dada su cédula
    public String direccionCliente(String cedula) {
        if(this.getCedula().equals(cedula)) {
            return this.direccion;
        }
        return "";
    }

    @Override
    public String toString() {
        return "Cliente [" + super.toString() + ", direccion=" + direccion
                + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + "]";
    }
}