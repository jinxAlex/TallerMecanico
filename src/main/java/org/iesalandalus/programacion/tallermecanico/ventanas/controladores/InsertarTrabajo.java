package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
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

    public Trabajo getTrabajo(){
        Trabajo trabajo = null;
        if(cbTipoTrabajo.getValue().equals("Mec�nico")){
            trabajo = new Mecanico(Cliente.get(tfDni.getText()), Vehiculo.get(tfMatricula.getText()),tfFechaInicio.getValue());
        }else if(cbTipoTrabajo.getValue().equals("Revisi�n")){
            trabajo = new Revision(Cliente.get(tfDni.getText()), Vehiculo.get(tfMatricula.getText()),tfFechaInicio.getValue());
        }
        return trabajo;
    }

    @FXML
    void initialize(){
        cbTipoTrabajo.setItems(FXCollections.observableArrayList("Mec�nico","Revisi�n"));
        cbTipoTrabajo.setValue("Mec�nico");
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

    @FXML
    void crearTrabajo(ActionEvent event) {
        if(cbTipoTrabajo.getValue().equals("Mec�nico")){
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_MECANICO);
        }else if(cbTipoTrabajo.getValue().equals("Revisi�n")){
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_REVISION);
        }
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }
}
