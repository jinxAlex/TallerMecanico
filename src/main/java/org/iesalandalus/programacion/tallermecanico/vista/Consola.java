package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n",mensaje);
        String formatoStr = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(formatoStr,0).replace("0","-"));
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
            try{
                System.out.print("Elige una opción: ");
                opcion = Opcion.get(Entrada.entero());
            }catch (IllegalArgumentException e){
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
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
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        mensaje = String.format("%s (%s):", mensaje, CADENA_FORMATO_FECHA);
        try{
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);
        } catch (DateTimeException e) {
            fecha = null;
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
