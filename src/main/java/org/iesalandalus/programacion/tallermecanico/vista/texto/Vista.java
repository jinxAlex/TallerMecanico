package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Vista {
    Cliente leerCliente();

    Cliente leerClienteDni();

    String leerNuevoNombre();

    String leerNuevoTelefono();

    Vehiculo leerVehiculo();
    Vehiculo leerVehiculoMatricula();

    Revision leerRevision();

    Mecanico leerMecanico();

    LocalDate leerMes();

    int leerHoras();

    float leerPrecioMaterial();

    LocalDate leerFechaCierre();

    GestorEventos getGestorEventos();

    void comenzar();

    void terminar();

    Trabajo leerTrabajoVehiculo();

    void notificarResultado(Evento evento, String texto, boolean exito);

    void mostrarCliente(Cliente cliente);

    void mostrarVehiculo(Vehiculo vehiculo);

    void mostrarTrabajo(Trabajo trabajo);

    void mostrarClientes(List<Cliente> clientes);

    void mostrarVehiculos(List<Vehiculo> vehiculos);

    void mostrarTrabajos(List<Trabajo> trabajos);

    void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas);
}
