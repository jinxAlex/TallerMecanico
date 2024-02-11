package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import java.util.*;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

public class Vehiculos {
    private Collection<Vehiculo> coleccionVehiculos;

    public Vehiculos(){
        coleccionVehiculos = new ArrayList<>();
    }

    public ArrayList<Vehiculo> get(){
        return new ArrayList<>(coleccionVehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if(coleccionVehiculos.contains(vehiculo)){
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        Vehiculo vehiculoBuscado = null;
        if (coleccionVehiculos.contains(vehiculo)) {
            vehiculoBuscado = vehiculo;
        }
        return vehiculoBuscado;
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if(!coleccionVehiculos.contains(vehiculo)){
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
        Iterator<Vehiculo> iterator = coleccionVehiculos.iterator();
        while (iterator.hasNext()) {
            Vehiculo vehiculoActual = iterator.next();
            if (vehiculoActual.matricula().equals(vehiculo.matricula())) {
                iterator.remove();
            }
        }
    }

}
