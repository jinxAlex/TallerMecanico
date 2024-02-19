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
        Opcion opcion;
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
        try {
            controlador.insertar(Consola.leerCliente());
            System.out.println("El cliente ha sido insertado con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("Insertar vehículo");
        try {
            controlador.insertar(Consola.leerVehiculo());
            System.out.println("El vehiculo ha sido insertado con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void insertarRevision() {
        Consola.mostrarCabecera("Insertar revisión");
        try {
            controlador.insertar(Consola.leerRevision());
            System.out.println("La revisión ha sido insertada con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        try {
            System.out.println(controlador.buscar(Consola.leerClienteDni()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehículo");
        try {
            System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar revisión");
        try {
            System.out.println(controlador.buscar(Consola.leerRevision()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("Modificar el cliente");
        try {
            controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
            System.out.println("Se ha modificado al cliente correctamente");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void anadirHoras() {
        Consola.mostrarCabecera("Añadir horas");
        try {
            controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
            System.out.println("Se han añadido las horas correctamente");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera("Añadir precio del material");
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
            System.out.println("Se ha añadido el precio del material correctamente");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void cerrarRevision() {
        Consola.mostrarCabecera("Cerrar revisión");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        System.out.println("Se ha cerrado la revisión correctamente");
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        try {
            controlador.borrar(Consola.leerClienteDni());
            System.out.println("Se ha borrado el cliente correctamente");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar vehículo");
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("Borrar revisión");
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        try {
            System.out.println(controlador.getClientes());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar vehículos");
        try {
            System.out.println(controlador.getVehiculos());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar revisiones");
        try {
            System.out.println(controlador.getRevisiones());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar revisiones de un cliente");
        try {
            System.out.println(controlador.getRevisiones(Consola.leerClienteDni()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar revisiones de un vehículo");
        try {
            System.out.println(controlador.getRevisiones(Consola.leerVehiculoMatricula()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void salir() {
        Consola.mostrarCabecera("Salir");
        terminar();
    }
}
