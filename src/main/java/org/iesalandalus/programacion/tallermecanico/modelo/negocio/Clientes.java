package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import java.util.*;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;

public class Clientes {
    private Collection<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    public ArrayList<Cliente> get(){
        return new ArrayList<>(coleccionClientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if(coleccionClientes.contains(cliente)){
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        boolean haSidoModificado = false;
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        if(!coleccionClientes.contains(cliente)){
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        for(Cliente clienteActual:coleccionClientes){
            if(clienteActual.equals(buscar(cliente))){
                if (nombre != null) {
                    clienteActual.setNombre(nombre);
                    haSidoModificado = true;
                }
                if (telefono != null) {
                    clienteActual.setTelefono(telefono);
                    haSidoModificado = true;
                }
            }
        }
        return haSidoModificado;
    }

    public Cliente buscar(Cliente cliente){
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        Cliente clienteBuscado = null;
        if (coleccionClientes.contains(cliente)) {
            clienteBuscado = cliente;
        }
        return clienteBuscado;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if(!coleccionClientes.contains(cliente)){
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        Iterator<Cliente> iterator = coleccionClientes.iterator();
        while (iterator.hasNext()) {
            Cliente clienteActual = iterator.next();
            if (clienteActual.getDni().equals(cliente.getDni())) {
                iterator.remove();
            }
        }
    }
}
