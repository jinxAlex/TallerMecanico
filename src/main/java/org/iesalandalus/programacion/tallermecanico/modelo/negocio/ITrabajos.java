package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ITrabajos {

    void comenzar();

    void terminar();

    List<Trabajo> get();

    List<Trabajo> get(Cliente cliente);

    List<Trabajo> get(Vehiculo vehiculo);

    void insertar(Trabajo trabajo) throws OperationNotSupportedException;

    Map<TipoTrabajo, Integer> inicialicarEstadisticas();

    Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes);

    Trabajo buscar(Trabajo trabajo);

    void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException;

    void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException;

    void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException;

    void borrar(Trabajo trabajo) throws OperationNotSupportedException;
}
