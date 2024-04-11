package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

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

    protected static Trabajos getInstancia(){
        return null;
    }

    public void comenzar(){

    }

    public void terminar(){

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

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Trabajo trabajoAbierto = null;
        Iterator<Trabajo> iterador = coleccionTrabajo.iterator();
        while (iterador.hasNext() && trabajoAbierto == null) {
            Trabajo trabajoActual = iterador.next();
            if (trabajoActual.getVehiculo().equals(vehiculo) && !trabajoActual.estaCerrado()) {
                trabajoAbierto = trabajoActual;
            }
        }
        if (trabajoAbierto == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
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
        trabajoAbierto.cerrar(fechaFin);
    }

    @Override
    public Map<TipoTrabajo, Integer> inicialicarEstadisticas(){
        Map<TipoTrabajo, Integer> estadisticasMensuales = new EnumMap<>(TipoTrabajo.class);
        for(TipoTrabajo tipoTrabajo: TipoTrabajo.values()){
            estadisticasMensuales.put(tipoTrabajo,0);
        }
        return estadisticasMensuales;
    }
    @Override
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes){
        Objects.requireNonNull(mes,"El mes no puede ser nulo");
        Map<TipoTrabajo, Integer> estadisticasMensuales = inicialicarEstadisticas();
        for(Trabajo trabajo: coleccionTrabajo){
            if(trabajo.getFechaInicio().getMonth() == mes.getMonth() && trabajo.getFechaInicio().getYear() == mes.getYear()){
                TipoTrabajo tipoTrabajo = TipoTrabajo.get(trabajo);
                estadisticasMensuales.put(tipoTrabajo,estadisticasMensuales.get(tipoTrabajo) + 1);
            }
        }
        return estadisticasMensuales;
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (!coleccionTrabajo.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajo.remove(trabajo);
    }
}
