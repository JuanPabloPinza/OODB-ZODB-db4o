package db4o_model;

import java.util.HashSet;
import java.util.Set;

public class Trabajador extends Persona {
    private String correoElectronico;
    private String telefono;
    private float salario;

    // Relación: set de Transaccion (registra)
    private Set<Transaccion> registra = new HashSet<>();

    public Trabajador() {
        super();
    }

    public Trabajador(String cedula, String nombre, String apellido, String correoElectronico, String telefono, float salario) {
        super(cedula, nombre, apellido);
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.salario = salario;
    }

    // Getters & Setters
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

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Set<Transaccion> getRegistra() {
        return registra;
    }

    public void setRegistra(Set<Transaccion> registra) {
        this.registra = registra;
    }

    // Add a transaction and maintain inverse relationship
    public void addTransaccion(Transaccion transaccion) {
        registra.add(transaccion);
        transaccion.setTrabajadorRegistra(this);
    }

    // Remove a transaction and maintain inverse relationship
    public void removeTransaccion(Transaccion transaccion) {
        registra.remove(transaccion);
        transaccion.setTrabajadorRegistra(null);
    }

    // Métodos
    public String aumentarSalario(float aumento) {
        this.salario += aumento;
        return "Nuevo salario: " + this.salario;
    }

    public float obtenerSalario(String cedula) {
        if(this.getCedula().equals(cedula)) {
            return this.salario;
        }
        return 0.0f;
    }

    @Override
    public String toString() {
        return "Trabajador [" + super.toString() + ", correoElectronico=" + correoElectronico
                + ", telefono=" + telefono + ", salario=" + salario + "]";
    }
}