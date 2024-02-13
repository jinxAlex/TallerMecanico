package org.iesalandalus.programacion.tallermecanico.modelo;


import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;
import java.util.*;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;

public class Modelo {
    private Revisiones revisiones;
    private Clientes clientes;
    private Vehiculos vehiculos;
    public Modelo(){
        comenzar();
    }

    public void comenzar(){
        revisiones = new Revisiones();
        clientes = new Clientes();
        vehiculos = new Vehiculos();
    }

    public void terminar(){
        System.out.println("El modelo ha terminado");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException{
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException{
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException{
        revisiones.insertar(new Revision(clientes.buscar(revision.getCliente()),vehiculos.buscar(revision.getVehiculo()),revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente){
        return new Cliente(clientes.buscar(cliente));
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision){
        return new Revision(revisiones.buscar(revision));
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException{
        return clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException{
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException{
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException{
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException{
        Iterator<Revision> iterator = revisiones.get(cliente).iterator();
        while (iterator.hasNext()) {
            Revision revisionActual = iterator.next();
            revisiones.borrar(revisionActual);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException{
        ArrayList<Revision> coleccionVehiculos = revisiones.get(vehiculo);
        for(Revision revision: coleccionVehiculos){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException{
        revisiones.borrar(revision);
    }

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> coleccionClientes = new ArrayList<>();
        for(Cliente cliente: clientes.get()){
            coleccionClientes.add(new Cliente(cliente));
        }
        return coleccionClientes;
    }

    public ArrayList<Vehiculo> getVehiculos(){
        return new ArrayList<Vehiculo>(vehiculos.get());
    }

    public ArrayList<Revision> getRevisiones(){
        ArrayList<Revision> coleccionRevisiones = new ArrayList<>();
        for(Revision revision: revisiones.get()){
            coleccionRevisiones.add(new Revision(revision));
        }
        return coleccionRevisiones;
    }

    public ArrayList<Revision> getRevisiones(Cliente cliente){
        ArrayList<Revision> coleccionRevisionesCliente = new ArrayList<>();
        for(Revision revision: revisiones.get(cliente)){
            coleccionRevisionesCliente.add(new Revision(revision));
        }
        return coleccionRevisionesCliente;
    }

    public ArrayList<Revision> getRevisiones(Vehiculo vehiculo){
        ArrayList<Revision> coleccionRevisionesVehiculo = new ArrayList<>();
        for(Revision revision: revisiones.get(vehiculo)){
            coleccionRevisionesVehiculo.add(new Revision(revision));
        }
        return coleccionRevisionesVehiculo;
    }


}
