package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos(){
        return gestorEventos;
    }


    @Override
    public void comenzar() {
        Evento evento;
        do {
            evento = Consola.elegirOpcion();
            ejecutar(evento);
        } while (!(evento.equals(Evento.SALIR)));

    }

    @Override
    public void terminar() {
        System.out.println("El programa ha terminado");
    }

    private void ejecutar(Evento evento) {
        Consola.mostrarCabecera(evento.toString());
        gestorEventos.notificar(evento);
    }

    @Override
    public Cliente leerCliente() {
        return new Cliente(Consola.leerCadena("Introduce el nombre del cliente: "), Consola.leerCadena("Introduce el dni del cliente: "), Consola.leerCadena("Introduce el teléfono del cliente: "));
    }

    @Override
    public Cliente leerClienteDni() {
        return Cliente.get(Consola.leerCadena("Introduce el dni del cliente: "));
    }

    @Override
    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduce el nuevo nombre del cliente: ");
    }

    @Override
    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el nuevo teléfono del cliente: ");
    }

    @Override
    public Vehiculo leerVehiculo() {
        return new Vehiculo(Consola.leerCadena("Introduce la marca del vehiculo: "), Consola.leerCadena("Introduce el modelo del vehiculo: "), Consola.leerCadena("Introduce la matrícula del vehiculo: "));
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula del vehículo: "));
    }

    @Override
    public Revision leerRevision() {
        return new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha de inicio de la  revisión: "));
    }

    @Override
    public Mecanico leerMecanico() {
        return new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha de inicio del trabajo mecánico: "));
    }


    @Override
    public Trabajo leerTrabajoVehiculo(){
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public int leerHoras() {
        return Consola.leerEntero("Introduce el número de horas: ");
    }
    @Override
    public float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio del material: ");
    }

    @Override
    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduce la fecha de cierre de la revisión: ");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        if(exito){
            System.out.println(texto);
        }else{
            System.out.printf("ERROR: %s ", texto);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente){
        System.out.println(cliente);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo){
        System.out.println(vehiculo);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo){
        System.out.println(trabajo);
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes){
        System.out.println(clientes);
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos){
        System.out.println(vehiculos);
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos){
        System.out.println(trabajos);
    }


}
