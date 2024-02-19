package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;

import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion = null;
        do {
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (!(opcion.equals(opcion.SALIR)));

    }

    public void terminar() {
        System.out.println("El programa ha terminado");
    }

    private void ejecutar(Opcion opcion) {
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
    }

    private void insertarCliente() {
        Consola.mostrarCabecera("Insertar cliente");
        controlador.insertar(Consola.leerCliente());
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("Insertar vehículo");
        controlador.insertar(Consola.leerVehiculo());
    }

    private void insertarRevision() {
        Consola.mostrarCabecera("Insertar revisión");
        controlador.insertar(Consola.leerRevision());
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        System.out.println(controlador.buscar(Consola.leerClienteDni()));

    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehículo");
        System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()));
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar revisión");
        System.out.println(controlador.buscar(Consola.leerRevision()));
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("Modificar el cliente");
        controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
    }

    private void anadirHoras() {
        Consola.mostrarCabecera("Añadir horas");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());

    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera("Añadir precio del material");
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
    }

    private void cerrarRevision() {
        Consola.mostrarCabecera("Cerrar revisión");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        controlador.borrar(Consola.leerClienteDni());
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar vehículo");
        controlador.borrar(Consola.leerVehiculoMatricula());
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("Borrar revisión");
        controlador.borrar(Consola.leerRevision());
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        System.out.println(controlador.getClientes());
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar vehículos");
        System.out.println(controlador.getVehiculos());
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar revisiones");
        System.out.println(controlador.getRevisiones());
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar revisiones de un cliente");
        System.out.println(controlador.getRevisiones(Consola.leerClienteDni()));
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar revisiones de un vehículo");
        System.out.println(controlador.getRevisiones(Consola.leerVehiculoMatricula()));
    }

    private void salir() {
        Consola.mostrarCabecera("Salir");
        terminar();
    }
}
