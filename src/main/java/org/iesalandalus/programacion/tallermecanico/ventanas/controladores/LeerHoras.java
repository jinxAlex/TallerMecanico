package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;


import javafx.scene.Node;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LeerHoras extends Controlador {
    @FXML
    private TextField tfHoras;

    public int getHoras() {
        return Integer.parseInt(tfHoras.getText());
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

    @FXML
    void leerMatricula(ActionEvent event) {
        if(getHoras() > 0){
            Node origen = (Node) event.getSource();
            Stage escenario = (Stage) origen.getScene().getWindow();
            escenario.close();
        }else{
            cancelarAccion(event);
        }

    }
}
