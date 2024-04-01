package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;

public class Trabajos implements ITrabajos {

    private final List<Trabajo> coleccionTrabajo;

    public Trabajos() {
        coleccionTrabajo = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajo);
    }

    public void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws OperationNotSupportedException {
        for (Trabajo trabajoActual : coleccionTrabajo) {
            if (vehiculo.equals(trabajoActual.getVehiculo())) {
                if (!trabajoActual.estaCerrado()) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
                } else if (!fechaTrabajo.isAfter(trabajoActual.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
                }
            }
            if (cliente.equals(trabajoActual.getCliente())) {
                if (!trabajoActual.estaCerrado()) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
                } else if (!fechaTrabajo.isAfter(trabajoActual.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
                }
            }
        }
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "La revisión no puede ser nula.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Trabajo trabajoAbierto = null;
        Iterator<Trabajo> iterador = coleccionTrabajo.iterator();
        while (iterador.hasNext() && trabajoAbierto == null) {
            Trabajo trabajoActual = iterador.next();
            if (trabajoActual.getVehiculo().equals(vehiculo) && !trabajoActual.estaCerrado()) {
                trabajoAbierto = trabajoActual;
            }
        }
        return trabajoAbierto;
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        ArrayList<Trabajo> coleccionTrabajosCliente = new ArrayList<>();
        for (Trabajo trabajoActual : coleccionTrabajo) {
            if (trabajoActual.getCliente().equals(cliente)) {
                coleccionTrabajosCliente.add(trabajoActual);
            }
        }
        return coleccionTrabajosCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        ArrayList<Trabajo> revisionesVehiculo = new ArrayList<>();
        for (Trabajo trabajoActual : coleccionTrabajo) {
            if (trabajoActual.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(trabajoActual);
            }
        }
        return revisionesVehiculo;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajo.add(trabajo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajo.indexOf(trabajo);
        return (indice == -1) ? null : coleccionTrabajo.get(indice);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo añadir horas a un trabajo nulo.");
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if(trabajoAbierto == null){
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        if(trabajoAbierto instanceof Mecanico trabajoMecanico){
            trabajoMecanico.anadirHoras(horas);
        }else if (trabajoAbierto instanceof Revision trabajoRevision){
            trabajoRevision.anadirHoras(horas);
        }
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo añadir precio del material a un trabajo nulo.");
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if(trabajoAbierto == null){
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        if(trabajoAbierto instanceof Mecanico trabajoMecanico){
            trabajoMecanico.anadirPrecioMaterial(precioMaterial);
        }else if (trabajoAbierto instanceof Revision){
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo cerrar un trabajo nulo.");
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if(trabajoAbierto == null){
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        trabajoAbierto.cerrar(fechaFin);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        Mecanico trabajoMecanico = new Mecanico(trabajo.getCliente(),trabajo.getVehiculo(),trabajo.getFechaInicio());
        if (buscar(trabajo) == null) {
            if(buscar(trabajoMecanico) == null){
                throw new OperationNotSupportedException("No existe ningún trabajo igual.");
            }else{
                coleccionTrabajo.remove(trabajoMecanico);
            }
        }else{
            coleccionTrabajo.remove(trabajo);
        }
        
    }
}
