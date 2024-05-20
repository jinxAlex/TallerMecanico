package org.iesalandalus.programacion.tallermecanico;

import javafx.util.Pair;
import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;

public class Main {
    public static void main(String[] args) {
        Pair<FabricaVista,FabricaFuenteDatos> fabricas = procesarArgumentos(args);
        Modelo modelo = FabricaModelo.CASCADA.crear(fabricas.getValue());
        IControlador controlador = new Controlador(modelo, fabricas.getKey().crear());
        controlador.comenzar();
    }

    private static Pair<FabricaVista, FabricaFuenteDatos> procesarArgumentos(String[] argumentos){
        FabricaVista vista = FabricaVista.GRAFICA;
        FabricaFuenteDatos datos = FabricaFuenteDatos.MARIADB;
        for(String argumento: argumentos){
            if(argumento.equals("-vTexto")){
                vista = FabricaVista.TEXTO;
            }else if(argumento.equals("-vGrafica")){
                vista = FabricaVista.GRAFICA;
            }else if(argumento.equals("-fMariaDB")){
                datos = FabricaFuenteDatos.MARIADB;
            }else if(argumento.equals("-fFicheros")){
                datos = FabricaFuenteDatos.FICHEROS;
            }
        }
        return new Pair<>(vista, datos);
    }
}
