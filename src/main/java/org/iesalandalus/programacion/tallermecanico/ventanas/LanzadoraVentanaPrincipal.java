package org.iesalandalus.programacion.tallermecanico.ventanas;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.ventanas.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Dialogos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

public class LanzadoraVentanaPrincipal extends Application{
        @Override
        public void start(Stage escenarioPrincipal) {
                VentanaPrincipal ventanaPrincipal = (VentanaPrincipal) Controladores.get("/vistas/VentanaPrincipal.fxml", "Taller", null);
                VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
                ventanaPrincipal.inicializar();
                Dialogos.setHojaEstilos("/estilos/estilo.css");
                ventanaPrincipal.getEscenario().show();
        }

        public static void comenzar(){launch(LanzadoraVentanaPrincipal.class);}

        public static void main(String[] args) {
            launch(args);
        }
}
