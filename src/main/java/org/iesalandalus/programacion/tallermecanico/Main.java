package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;

public class Main {
    public static void main(String[] args) {
        Vista vista = FabricaVista.GRAFICA.crear();
        Modelo modelo = FabricaModelo.CASCADA.crear(FabricaFuenteDatos.FICHEROS);
        IControlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }
}
