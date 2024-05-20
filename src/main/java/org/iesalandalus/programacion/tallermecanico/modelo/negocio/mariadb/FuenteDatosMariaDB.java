package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

public class FuenteDatosMariaDB implements IFuenteDatos {
    public IClientes crearClientes(){
        return Clientes.getInstancia();
    }

    public IVehiculos crearVehiculos(){
        return Vehiculos.getInstancia();
    }

    public ITrabajos crearTrabajos(){
        return Trabajos.getInstancia();
    }
}
