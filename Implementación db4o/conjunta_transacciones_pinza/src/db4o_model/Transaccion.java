package db4o_model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Transaccion {
    private String idTrans;
    private float monto;
    private Date fechaTransaccion;

    // Relaciones
    private Administrador esRevisada;
    private Trabajador trabajadorRegistra;

    public Transaccion() {}

    public Transaccion(String idTrans, float monto, Date fechaTransaccion) {
        this.idTrans = idTrans;
        this.monto = monto;
        this.fechaTransaccion = fechaTransaccion;
    }

    // Getters & Setters
    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Administrador getEsRevisada() {
        return esRevisada;
    }

    public void setEsRevisada(Administrador esRevisada) {
        this.esRevisada = esRevisada;
    }

    public Trabajador getTrabajadorRegistra() {
        return trabajadorRegistra;
    }

    public void setTrabajadorRegistra(Trabajador trabajadorRegistra) {
        this.trabajadorRegistra = trabajadorRegistra;
    }

    // Método de filtrado
    public static Set<Transaccion> filtrarTransacciones(Set<Transaccion> transacciones, Date inicio, Date fin) {
        Set<Transaccion> filtradas = new HashSet<>();
        for (Transaccion t : transacciones) {
            if (!t.getFechaTransaccion().before(inicio) && !t.getFechaTransaccion().after(fin)) {
                filtradas.add(t);
            }
        }
        return filtradas;
    }

    @Override
    public String toString() {
        return "Transaccion [idTrans=" + idTrans + ", monto=" + monto
                + ", fechaTransaccion=" + fechaTransaccion + "]";
    }
}
