package db4o_model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Administrador extends Persona {
    private String correoElectronico;
    private String telefono;
    private float salario;

    // Relación: set de Transaccion (revisa)
    private Set<Transaccion> revisa = new HashSet<>();

    public Administrador() {
        super();
    }

    public Administrador(String cedula, String nombre, String apellido, String correoElectronico, String telefono, float salario) {
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

    public Set<Transaccion> getRevisa() {
        return revisa;
    }

    public void setRevisa(Set<Transaccion> revisa) {
        this.revisa = revisa;
    }

    // Add a transaction to review and maintain inverse relationship
    public void addTransaccionRevisa(Transaccion transaccion) {
        revisa.add(transaccion);
        transaccion.setEsRevisada(this);
    }

    // Remove a transaction and maintain inverse relationship
    public void removeTransaccionRevisa(Transaccion transaccion) {
        revisa.remove(transaccion);
        transaccion.setEsRevisada(null);
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

    public Set<Transaccion> obtenerReporte(Date inicio, Date fin) {
        Set<Transaccion> reporte = new HashSet<>();
        for(Transaccion t : revisa) {
            if(!t.getFechaTransaccion().before(inicio) && !t.getFechaTransaccion().after(fin)) {
                reporte.add(t);
            }
        }
        return reporte;
    }

    @Override
    public String toString() {
        return "Administrador [" + super.toString() + ", correoElectronico=" + correoElectronico
                + ", telefono=" + telefono + ", salario=" + salario + "]";
    }
}