package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import javax.naming.OperationNotSupportedException;
import javax.naming.ldap.Control;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Controlador {
    private Modelo modelo;

    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo");
        Objects.requireNonNull(vista, "La vista no puede ser nula");
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        vista.comenzar();
    }

    public void terminar() {
        vista.terminar();
    }

    public void insertar(Cliente cliente) {
        try {
            modelo.insertar(cliente);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void insertar(Vehiculo vehiculo) {
        try {
            modelo.insertar(vehiculo);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void insertar(Revision revision) {
        try {
            modelo.insertar(revision);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public Cliente buscar(Cliente cliente) {
        return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modelo.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return modelo.buscar(revision);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) {
        boolean esModificado = false;
        try {
            esModificado = modelo.modificar(cliente, nombre, telefono);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
        return esModificado;
    }

    public void anadirHoras(Revision revision, int horas) {
        try {
            modelo.anadirHoras(revision, horas);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) {
        try {
            modelo.anadirPrecioMaterial(revision, precioMaterial);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void cerrar(Revision revision, LocalDate fechaFin) {
        try {
            modelo.cerrar(revision, fechaFin);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void borrar(Cliente cliente) {
        try {
            modelo.borrar(cliente);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void borrar(Vehiculo vehiculo) {
        try {
            modelo.borrar(vehiculo);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public void borrar(Revision revision) {
        try {
            modelo.borrar(revision);
        } catch (OperationNotSupportedException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Cliente> getClientes() {
        return modelo.getClientes();
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return modelo.getVehiculos();
    }

    public ArrayList<Revision> getRevisiones() {
        return modelo.getRevisiones();
    }

    public ArrayList<Revision> getRevisiones(Cliente cliente) {
        return modelo.getRevisiones(cliente);
    }

    public ArrayList<Revision> getRevisiones(Vehiculo vehiculo) {
        return modelo.getRevisiones(vehiculo);
    }


}
