package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.Map;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar clientes"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehículo"),
    BUSCAR_REVISION(7, "Buscar revisión"),
    BORRAR_REVISION(8, "Borrar revisión"),
    LISTAR_REVISIONES(9, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(10, "Listar revisiones de cliente"),
    LISTAR_REVISIONES_VEHICULO(11, "Listar revisiones de vehículo"),
    ANADIR_HORAS_REVISION(12, "Añadir horas a revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(13, "Añadir precio de material a revisión"),
    CERRAR_REVISION(14, "Cerrar revisión"),
    SALIR(15, "Salir");

    private static Map<Integer, String> opciones = new TreeMap<>();

    private  int numeroOpcion;

    private String mensaje;

    private  Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    static {
        for (Opcion opcion : Opcion.values()) {
            opciones.put(opcion.numeroOpcion, opcion.mensaje);
        }
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("La opción es incorrecta");
        }
        return Opcion.get(numeroOpcion);
    }

}
