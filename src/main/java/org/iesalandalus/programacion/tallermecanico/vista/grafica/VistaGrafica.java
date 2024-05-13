package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Trabajos;
import org.iesalandalus.programacion.tallermecanico.ventanas.LanzadoraVentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.InsertarCliente;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.InsertarVehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.LeerMatricula;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {

    private static VistaGrafica instancia;

    private Controlador ventanaPrincipal;

    private InsertarCliente insertarCliente;

    private InsertarVehiculo insertarVehiculo;

    private LeerMatricula leerMatricula;

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    public static VistaGrafica getInstancia(){
        if(instancia == null){
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    private VistaGrafica(){}


    public void setVentanaPrincipal(Controlador ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public Controlador getVentanaPrincipal(){
        return ventanaPrincipal;
    }


    @Override
    public GestorEventos getGestorEventos(){
        return gestorEventos;
    }

    @Override
    public Cliente leerCliente(){
        insertarCliente = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Insertar Cliente", ventanaPrincipal.getEscenario());
        insertarCliente.limpiarCampos();
        insertarCliente.getEscenario().showAndWait();
        if(insertarCliente.esCerrado){
            System.out.println(insertarCliente.getCliente());
        }else{
            throw new IllegalArgumentException("Operación cancelada por el usuario");
        }
        return null;
    }

    @Override
    public Cliente leerClienteDni() {
        return null;
    }

    @Override
    public String leerNuevoNombre() {
        return "";
    }

    @Override
    public String leerNuevoTelefono() {
        return "";
    }

    @Override
    public Vehiculo leerVehiculo() {
        insertarVehiculo = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml", "Insertar Vehiculo", ventanaPrincipal.getEscenario());
        insertarVehiculo.limpiarCampos();
        insertarVehiculo.getEscenario().showAndWait();
        if(insertarVehiculo.esCerrado){
            System.out.println(insertarVehiculo.getVehiculo());
        }else{
            throw new IllegalArgumentException("Operación cancelada por el usuario");
        }
        return null;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        leerMatricula = (LeerMatricula) Controladores.get("/vistas/LeerMatricula.fxml", "Insertar Vehiculo", ventanaPrincipal.getEscenario());
        leerMatricula.limpiarCampos();
        leerMatricula.getEscenario().showAndWait();
        System.out.println(leerMatricula.getVehiculo());
        return null;
    }

    @Override
    public Revision leerRevision() {
        return null;
    }

    @Override
    public Mecanico leerMecanico() {
        return null;
    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        return null;
    }

    @Override
    public void comenzar() {
        LanzadoraVentanaPrincipal.comenzar();
    }

    @Override
    public void terminar() {

    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {

    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        VentanaPrincipal.datos = new ArrayList<>(clientes);
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        VentanaPrincipal.datos = new ArrayList<>(vehiculos);
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        VentanaPrincipal.datos = new ArrayList<>(trabajos);

    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }
}
