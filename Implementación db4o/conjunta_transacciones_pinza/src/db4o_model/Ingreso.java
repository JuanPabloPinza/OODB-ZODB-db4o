package db4o_model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Ingreso extends Transaccion {
    private String motivoIngreso;

    // Relaciones específicas de Ingreso
    private Cliente clienteRegistra;
    private Set<Producto> contiene = new HashSet<>();

    public Ingreso() {
        super();
    }

    public Ingreso(String idTrans, float monto, Date fechaTransaccion, String motivoIngreso) {
        super(idTrans, monto, fechaTransaccion);
        this.motivoIngreso = motivoIngreso;
    }

    // Getters & Setters
    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public Cliente getClienteRegistra() {
        return clienteRegistra;
    }

    public void setClienteRegistra(Cliente clienteRegistra) {
        this.clienteRegistra = clienteRegistra;
    }

    public Set<Producto> getContiene() {
        return contiene;
    }

    public void setContiene(Set<Producto> contiene) {
        this.contiene = contiene;
    }

    // Add a product and maintain inverse relationship
    public void addProducto(Producto producto) {
        contiene.add(producto);
        producto.addIngresoContenedor(this);
    }

    // Remove a product and maintain inverse relationship
    public void removeProducto(Producto producto) {
        contiene.remove(producto);
        producto.removeIngresoContenedor(this);
    }

    // Métodos
    public static Set<Ingreso> filtrarIngresos(Set<Ingreso> ingresos, Date inicio, Date fin) {
        Set<Ingreso> filtrados = new HashSet<>();
        for (Ingreso i : ingresos) {
            if (!i.getFechaTransaccion().before(inicio) && !i.getFechaTransaccion().after(fin)) {
                filtrados.add(i);
            }
        }
        return filtrados;
    }

    public static float totalIngresos(Set<Ingreso> ingresos, Date inicio, Date fin) {
        float total = 0.0f;
        for (Ingreso i : ingresos) {
            if (!i.getFechaTransaccion().before(inicio) && !i.getFechaTransaccion().after(fin)) {
                total += i.getMonto();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Ingreso [" + super.toString() + ", motivoIngreso=" + motivoIngreso + "]";
    }
}