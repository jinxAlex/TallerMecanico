package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

public class FuenteDatosFicheros implements IFuenteDatos {

    public IClientes crearClientes(){
        return new Clientes();
    }

    public IVehiculos crearVehiculos(){
        return new Vehiculos();
    }

    public ITrabajos crearTrabajos(){
        return new Trabajos();
    }
}
