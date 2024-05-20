package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void comenzar() {
        conexion = MariaDB.getConexion();
    }

    @Override
    public void terminar() {
        MariaDB.cerrarConexion();
    }


    private Vehiculo getVehiculo(ResultSet fila) throws SQLException {
        String marca = fila.getString(MARCA);
        String modelo = fila.getString(MODELO);
        String matricula = fila.getString(MATRICULA);
        return new Vehiculo(marca, modelo, matricula);
    }

    private void prepararSentencia(PreparedStatement sentencia, Vehiculo vehiculo) throws SQLException {
        sentencia.setString(1, vehiculo.marca());
        sentencia.setString(2, vehiculo.modelo());
        sentencia.setString(3, vehiculo.matricula());
    }

    @Override
    public List<Vehiculo> get() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (Statement sentencia = conexion.createStatement()){
            ResultSet filas = sentencia.executeQuery("select * from vehiculos");
            while (filas.next()){
                vehiculos.add(getVehiculo(filas));
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return vehiculos;
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehiculo nulo.");
        try (PreparedStatement sentencia = conexion.prepareStatement("insert into vehiculos values (?, ?, ?)")) {
            prepararSentencia(sentencia, vehiculo);
            sentencia.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new OperationNotSupportedException("Ya existe un vehiculo con esa matr�cula.");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehiculo nulo.");
        try (PreparedStatement sentencia = conexion.prepareStatement("select * from vehiculos where matricula = ? ")) {
            sentencia.setString(1, vehiculo.matricula());
            ResultSet filas = sentencia.executeQuery();
            vehiculo = filas.first() ? getVehiculo(filas) : null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehiculo nulo.");
        try (PreparedStatement sentencia = conexion.prepareStatement("delete from vehiculos where dni = ?")) {
            sentencia.setString(1, vehiculo.matricula());
            int filas = sentencia.executeUpdate();
            if (filas == 0) {
                throw new OperationNotSupportedException("No existe ning�n vehiculo con esa matricula.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
