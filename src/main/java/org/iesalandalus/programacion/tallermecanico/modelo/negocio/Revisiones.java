package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;

public class Revisiones {

    private final List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        ArrayList<Revision> coleccionRevisionesCliente = new ArrayList<>();
        for (Revision revisionActual : coleccionRevisiones) {
            if (revisionActual.getCliente().equals(cliente)) {
                coleccionRevisionesCliente.add(revisionActual);
            }
        }
        return coleccionRevisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        ArrayList<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revisionActual : coleccionRevisiones) {
            if (revisionActual.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revisionActual);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Revision revisionActual : coleccionRevisiones) {
            if (vehiculo.equals(revisionActual.getVehiculo())) {
                if (!revisionActual.estaCerrada()) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
                } else if (!fechaRevision.isAfter(revisionActual.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
                }
            }
            if (cliente.equals(revisionActual.getCliente())) {
                if (!revisionActual.estaCerrada()) {
                    throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
                } else if (!fechaRevision.isAfter(revisionActual.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
                }
            }
        }
    }

    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        Revision revisionBuscada = buscar(revision);
        if (revisionBuscada == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        return revisionBuscada;
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int indice = coleccionRevisiones.indexOf(revision);

        return (indice == -1) ? null : coleccionRevisiones.get(indice);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        Revision revisionBuscada = getRevision(revision);
        revisionBuscada.anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        Revision revisionBuscada = getRevision(revision);
        revisionBuscada.anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        Revision revisionBuscada = getRevision(revision);
        revisionBuscada.cerrar(fechaFin);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        if (buscar(revision) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(revision);
    }
}
