package db4o_model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Egreso extends Transaccion {
    private String motivoEgreso;

    public Egreso() {
        super();
    }

    public Egreso(String idTrans, float monto, Date fechaTransaccion, String motivoEgreso) {
        super(idTrans, monto, fechaTransaccion);
        this.motivoEgreso = motivoEgreso;
    }

    public String getMotivoEgreso() {
        return motivoEgreso;
    }

    public void setMotivoEgreso(String motivoEgreso) {
        this.motivoEgreso = motivoEgreso;
    }

    // Métodos - Fixed to return Set<Egreso> instead of Set<Ingreso> as per ODL
    public static Set<Egreso> filtrarEgresos(Set<Egreso> egresos, Date inicio, Date fin) {
        Set<Egreso> filtrados = new HashSet<>();
        for (Egreso e : egresos) {
            if (!e.getFechaTransaccion().before(inicio) && !e.getFechaTransaccion().after(fin)) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

    public static float totalEgresos(Set<Egreso> egresos, Date inicio, Date fin) {
        float total = 0.0f;
        for (Egreso e : egresos) {
            if (!e.getFechaTransaccion().before(inicio) && !e.getFechaTransaccion().after(fin)) {
                total += e.getMonto();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Egreso [" + super.toString() + ", motivoEgreso=" + motivoEgreso + "]";
    }
}