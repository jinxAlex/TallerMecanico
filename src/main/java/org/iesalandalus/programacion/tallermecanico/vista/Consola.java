package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Consola {
    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println(String.format("%0" + mensaje.length() + "d", 0).replace('0', '-'));
    }

    public static void mostrarMenu() {
        System.out.println();
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        mostrarMenu();
        Opcion opcion = null;
        do {
            System.out.print("Elige una opción: ");
            opcion = Opcion.get(Entrada.entero());
        } while (opcion == null);
        return opcion;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        System.out.println(mensaje);
        LocalDate fecha = null;
        while (fecha == null) {
            try {
                System.out.print("Introduce el día: ");
                Integer dia = Entrada.entero();
                System.out.print("Introduce el mes: ");
                Integer mes = Entrada.entero();
                System.out.print("Introduce el año: ");
                Integer anio = Entrada.entero();

                fecha = LocalDate.of(anio, mes, dia);
            } catch (DateTimeException e) {
                System.out.println("Fecha no válida. Por favor, ingresa una fecha válida.");
            }
        }
        return fecha;
    }

    public static Cliente leerCliente() {
        return new Cliente(leerCadena("Introduce el nombre del cliente: "), leerCadena("Introduce el dni del cliente: "), leerCadena("Introduce el teléfono del cliente: "));
    }

    public static Cliente leerClienteDni() {
        return Cliente.get(leerCadena("Introduce el dni del cliente: "));
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre del cliente: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono del cliente: ");
    }

    public static Vehiculo leerVehiculo() {
        return new Vehiculo(leerCadena("Introduce la marca del vehiculo: "), leerCadena("Introduce el modelo del vehiculo: "), leerCadena("Introduce la matrícula del vehiculo: "));
    }

    public static Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(leerCadena("Introduce la matrícula del vehículo: "));
    }

    public static Revision leerRevision() {
        return new Revision(leerClienteDni(), leerVehiculoMatricula(), leerFecha("Introduce la fecha de la primera revisión: "));
    }

    public static int leerHoras() {
        return leerEntero("Introduce el número de horas: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }


}
