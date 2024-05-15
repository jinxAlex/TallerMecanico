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
        Vista vista = getVista(args);
        Modelo modelo = FabricaModelo.CASCADA.crear(FabricaFuenteDatos.FICHEROS);
        IControlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }

    private static Vista getVista(String[] argumentos){
        Vista vista = null;
        for(int i = 0; i < argumentos.length; i++){
            if(argumentos[i].equals("-vTexto")){
                vista = FabricaVista.TEXTO.crear();
            }else if(argumentos[i].equals("-vGrafica")){
                vista = FabricaVista.GRAFICA.crear();
            }
        }
        return vista;
    }
}
