package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vehiculos implements IVehiculos {

    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";

    private Connection conexion;
    private static Vehiculos instancia;

    static Vehiculos getInstancia(){
        if(instancia == null){
            instancia = new Vehiculos();
        }
        return instancia;
    }

    private void prepararSentencia(PreparedStatement sentencia, Vehiculo vehiculo) throws SQLException {
        sentencia.setString(1, vehiculo.marca());
        sentencia.setString(2, vehiculo.modelo());
        sentencia.setString(3, vehiculo.matricula());
    }

    @Override
    public void terminar() {

    }

    @Override
    public void comenzar() {

    }

    @Override
    public List<Vehiculo> get() {
        List<Cliente> clientes = new ArrayList<>();
        try (Statement sentencia = conexion.createStatement()){

        }
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return null;
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }
}
