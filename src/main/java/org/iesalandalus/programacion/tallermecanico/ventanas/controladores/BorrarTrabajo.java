package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

public class BorrarTrabajo extends Controlador {

    @FXML
    private TextField tfDni;

    @FXML
    private DatePicker tfFechaInicio;

    @FXML
    private TextField tfMatricula;

    public Trabajo getTrabajo(){
        return new Revision(Cliente.get(tfDni.getText()), Vehiculo.get(tfMatricula.getText()),tfFechaInicio.getValue());
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

    @FXML
    void borrarTrabajo(ActionEvent event) {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_TRABAJO);
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }
}
