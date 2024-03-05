package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (!(opcion.equals(Opcion.SALIR)));

    }

    public void terminar() {
        System.out.println("El programa ha terminado");
    }

    private void ejecutar(Opcion opcion) {
        try{
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BUSCAR_REVISION -> buscarRevision();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_CLIENTES -> listarClientes();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case SALIR -> salir();
            }
        } catch (Exception e){
            System.out.printf("ERROR: %s%n", e.getMessage());
        }

    }

    private void insertarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Insertar cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("El usuario ha sido insertado con éxito.");
    }

    private void insertarVehiculo() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Insertar vehículo");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("El vehiculo ha sido insertado con éxito.");
    }

    private void insertarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Insertar revisión");
        controlador.insertar(Consola.leerRevision());
        System.out.println("La revisión ha sido insertada con éxito.");
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con dicho DNI");
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehículo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehiculo con dicha matrícula");
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar revisión");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println((revision != null) ? revision : "No existe dicha revisión");
    }

    private void modificarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Modificar el cliente");
        System.out.println(controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono()) ? "El cliente ha sido modificado": "El cliente no ha sido modificado");
    }

    private void anadirHoras() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Añadir horas");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        System.out.println("Las horas han sido añadidas perfectamente a la revisión.");
    }

    private void anadirPrecioMaterial() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Añadir precio del material");
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        System.out.println("El precio del material ha sido añadido correctamente.");
    }

    private void cerrarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Cerrar revisión");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        System.out.println("La revisión ha sido cerrada correctamente.");
    }

    private void borrarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Borrar cliente");
        controlador.borrar(Consola.leerClienteDni());
        System.out.println("Se ha borrado el cliente.");
    }

    private void borrarVehiculo() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Borrar vehículo");
        controlador.borrar(Consola.leerVehiculoMatricula());
        System.out.println("Se ha borrado el vehiculo.");
    }

    private void borrarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Borrar revisión");
        controlador.borrar(Consola.leerRevision());
        System.out.println("Se ha borrado la revisión.");
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        List<Cliente> clientes = controlador.getClientes();
        if(!clientes.isEmpty()){
            for(Cliente cliente: clientes){
                System.out.println(cliente);
            }
        }else{
           System.out.println("No hay clientes que mostrar.");
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if(!vehiculos.isEmpty()){
            for(Vehiculo vehiculo: vehiculos){
                System.out.println(vehiculo);
            }
        }else{
            System.out.println("No hay vehículos que mostrar.");
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if(!revisiones.isEmpty()){
            for(Revision revision: revisiones){
                System.out.println(revision);
            }
        }else{
            System.out.println("No hay revisiones que mostrar.");
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar revisiones de un cliente");
        List<Revision> revisionesClientes = controlador.getRevisiones(Consola.leerClienteDni());
        if(!revisionesClientes.isEmpty()){
            for(Revision revision: revisionesClientes){
                System.out.println(revision);
            }
        }else{
            System.out.println("No hay revisiones que mostrar para dicho cliente.");
        }
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar revisiones de un vehículo");
        List<Revision> revisionesVehiculo = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if(!revisionesVehiculo.isEmpty()){
            for(Revision revision: revisionesVehiculo){
                System.out.println(revision);
            }
        }else{
            System.out.println("No hay revisiones que mostrar para dicho vehiculo.");
        }
    }

    private void salir() {
        Consola.mostrarCabecera("Salir");
        controlador.terminar();
    }
}
