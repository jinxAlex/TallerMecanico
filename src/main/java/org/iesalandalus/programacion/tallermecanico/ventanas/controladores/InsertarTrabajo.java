package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

public class InsertarTrabajo extends Controlador {

    @FXML
    private ChoiceBox<String> cbTipoTrabajo;

    @FXML
    private TextField tfDni;

    @FXML
    private DatePicker tfFechaInicio;

    @FXML
    private TextField tfMatricula;

    public

    @FXML
    void initialize(){
        cbTipoTrabajo.setItems(FXCollections.observableArrayList("Mecánico","Revisión"));
        cbTipoTrabajo.setValue("Mecánico");
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

    @FXML
    void crearTrabajo() {
        if(cbTipoTrabajo.getValue().equals("Mecánico")){
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_MECANICO);
        }else if(cbTipoTrabajo.getValue().equals("Revisión")){
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_MECANICO);
        }

    }

}
