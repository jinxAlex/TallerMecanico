package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Trabajos;
import org.iesalandalus.programacion.tallermecanico.ventanas.LanzadoraVentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {

    private static VistaGrafica instancia;

    private Controlador ventanaPrincipal;

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
    public Cliente leerCliente() {
        Controlador ventanaInsetarCliente = Controladores.get("/vistas/InsertarCliente.fxml", "Insertar Cliente", ventanaPrincipal.getEscenario());
        ventanaInsetarCliente.getEscenario().show();
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
        return null;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
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
        for(Cliente cliente: clientes){
            VentanaPrincipal.datos.add(cliente);
        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        for(Vehiculo vehiculo: vehiculos){
            VentanaPrincipal.datos.add(vehiculo);
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }
}
