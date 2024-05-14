package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.ventanas.LanzadoraVentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.*;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {

    private static VistaGrafica instancia;

    private Controlador ventanaPrincipal;

    private InsertarCliente insertarCliente;

    private InsertarVehiculo insertarVehiculo;

    private LeerMatricula leerMatricula;

    private LeerDni leerDni;

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
        Cliente cliente;
        insertarCliente = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Insertar Cliente", ventanaPrincipal.getEscenario());
        insertarCliente.limpiarCampos();
        insertarCliente.getEscenario().showAndWait();
        if(insertarCliente.esCerrado){
            cliente = insertarCliente.getCliente();
            VentanaPrincipal.clientes.add(cliente);
        }else{
            throw new IllegalArgumentException("Operación cancelada por el usuario");
        }
        return cliente;
    }

    @Override
    public Cliente leerClienteDni() {
        Cliente cliente;
        leerDni = (LeerDni) Controladores.get("/vistas/LeerDni.fxml", "Leer DNI", ventanaPrincipal.getEscenario());
        leerDni.limpiarCampos();
        leerDni.getEscenario().showAndWait();
        if(leerDni.esCerrado){
            cliente = leerDni.getCliente();
        }else{
            throw new IllegalArgumentException("Operación cancelada por el usuario");
        }
        return cliente;
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
        Vehiculo vehiculo;
        insertarVehiculo = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml", "Insertar Vehiculo", ventanaPrincipal.getEscenario());
        insertarVehiculo.limpiarCampos();
        insertarVehiculo.getEscenario().showAndWait();
        if(leerMatricula.esCerrado){
            vehiculo = insertarVehiculo.getVehiculo();
            VentanaPrincipal.vehiculos.add(vehiculo);
        }else{
            throw new IllegalArgumentException("Operación cancelada por el usuario");
        }
        return vehiculo;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        Vehiculo vehiculo;
        leerMatricula = (LeerMatricula) Controladores.get("/vistas/LeerMatricula.fxml", "Insertar Vehiculo", ventanaPrincipal.getEscenario());
        leerMatricula.limpiarCampos();
        leerMatricula.getEscenario().showAndWait();
        if(leerMatricula.esCerrado){
            vehiculo = leerMatricula.getVehiculo();
        }else{
            throw new IllegalArgumentException("Operación cancelada por el usuario");
        }
        return vehiculo;
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
        VentanaPrincipal.clientes = new ArrayList<>(clientes);
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        VentanaPrincipal.vehiculos = new ArrayList<>(vehiculos);
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        VentanaPrincipal.trabajos = new ArrayList<>(trabajos);
    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }
}
