package db4o_model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Producto {
    private String nombreProducto;
    private float precio;

    // Relación: Ingreso contiene Producto (inversa de Ingreso::contiene)
    private Set<Ingreso> esContenido = new HashSet<>();

    public Producto() {}

    public Producto(String nombreProducto, float precio) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    // Getters & Setters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Set<Ingreso> getEsContenido() {
        return esContenido;
    }

    public void setEsContenido(Set<Ingreso> esContenido) {
        this.esContenido = esContenido;
    }

    // Add a containing ingreso and maintain inverse relationship
    public void addIngresoContenedor(Ingreso ingreso) {
        esContenido.add(ingreso);
    }

    // Remove a containing ingreso and maintain inverse relationship
    public void removeIngresoContenedor(Ingreso ingreso) {
        esContenido.remove(ingreso);
    }

    // Métodos (fixed to match ODL but with implementation)
    public Set<Ingreso> filtrarEgresos(Date inicio, Date fin) {
        Set<Ingreso> filtrados = new HashSet<>();
        for (Ingreso i : esContenido) {
            if (!i.getFechaTransaccion().before(inicio) && !i.getFechaTransaccion().after(fin)) {
                filtrados.add(i);
            }
        }
        return filtrados;
    }

    public float totalEgresos(Date inicio, Date fin) {
        float total = 0.0f;
        for (Ingreso i : esContenido) {
            if (!i.getFechaTransaccion().before(inicio) && !i.getFechaTransaccion().after(fin)) {
                total += i.getMonto();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Producto [nombreProducto=" + nombreProducto + ", precio=" + precio + "]";
    }
}