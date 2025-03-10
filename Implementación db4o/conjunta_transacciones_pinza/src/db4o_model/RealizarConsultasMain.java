// File: RealizarConsultasMain.java
package db4o_model;

import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RealizarConsultasMain {
    public static void main(String[] args) {
        // Configure db4o
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().activationDepth(5); // Set deeper activation depth
        config.common().updateDepth(5);     // Set deeper update depth

        // Open database
        ObjectContainer db = Db4oEmbedded.openFile(config, "base_datos.db4o");

        try {
            // Inserción de datos de ejemplo
            insertarDatos(db);

            // Realizar algunas consultas
            listarClientes(db);
            listarTransaccionesPorFecha(db, "2025-02-01", "2025-02-28");
        } finally {
            // Close database
            db.close();
        }
    }

    // Inserta algunos registros de ejemplo
    private static void insertarDatos(ObjectContainer db) {
        // Clientes
        Cliente cliente1 = new Cliente("001", "Juan", "Perez", "Av. Siempre Viva 123", "juan@mail.com", "123456789");
        Cliente cliente2 = new Cliente("002", "Maria", "Lopez", "Calle Falsa 456", "maria@mail.com", "987654321");
        db.store(cliente1);
        db.store(cliente2);

        // Trabajador
        Trabajador trabajador1 = new Trabajador("003", "Carlos", "Sanchez", "carlos@mail.com", "5555555", 1200.0f);
        db.store(trabajador1);

        // Administrador
        Administrador admin1 = new Administrador("004", "Ana", "Gomez", "ana@mail.com", "4444444", 2000.0f);
        db.store(admin1);

        // Productos
        Producto producto1 = new Producto("Vino Tinto", 15.99f);
        Producto producto2 = new Producto("Cerveza", 5.99f);
        db.store(producto1);
        db.store(producto2);

        // Transacciones (Ingresos y Egresos)
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha1 = sdf.parse("2025-02-15");
            Date fecha2 = sdf.parse("2025-02-20");

            // Crear ingreso
            Ingreso ingreso1 = new Ingreso("T001", 500.0f, fecha1, "Venta de producto X");

            // Establecer relación con cliente
            cliente1.addIngreso(ingreso1);

            // Establecer relación con trabajador que registra
            trabajador1.addTransaccion(ingreso1);

            // Establecer relación con productos
            ingreso1.addProducto(producto1);
            ingreso1.addProducto(producto2);

            // Establecer relación con administrador que revisa
            admin1.addTransaccionRevisa(ingreso1);

            db.store(ingreso1);
            db.store(cliente1);  // Store updated relationships
            db.store(trabajador1);
            db.store(producto1);
            db.store(producto2);
            db.store(admin1);

            // Crear egreso
            Egreso egreso1 = new Egreso("T002", 300.0f, fecha2, "Pago a proveedor Y");

            // Establecer relación con trabajador que registra
            trabajador1.addTransaccion(egreso1);

            // Establecer relación con administrador que revisa
            admin1.addTransaccionRevisa(egreso1);

            db.store(egreso1);
            db.store(trabajador1);  // Store updated relationships
            db.store(admin1);

        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    // Consulta: Listar todos los clientes
    private static void listarClientes(ObjectContainer db) {
        System.out.println("Listado de Clientes:");
        ObjectSet<Cliente> clientes = db.query(Cliente.class);
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    // Consulta: Listar transacciones entre dos fechas (formato yyyy-MM-dd)
    private static void listarTransaccionesPorFecha(ObjectContainer db, String inicioStr, String finStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = sdf.parse(inicioStr);
            Date fin = sdf.parse(finStr);

            System.out.println("\nTransacciones entre " + inicioStr + " y " + finStr + ":");
            Query query = db.query();
            query.constrain(Transaccion.class);
            query.descend("fechaTransaccion").constrain(inicio).greater().equal();
            query.descend("fechaTransaccion").constrain(fin).smaller().equal();
            ObjectSet<Transaccion> transacciones = query.execute();
            for (Transaccion t : transacciones) {
                System.out.println(t);
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }
}