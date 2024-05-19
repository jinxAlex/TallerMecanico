package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Dialogos;

public class LeerMatricula extends Controlador {

    @FXML
    private TextField tfMatricula;

    public boolean esCerrado = false;

    public Vehiculo getVehiculo() {
        return Vehiculo.get(tfMatricula.getText());
    }


    public void limpiarCampos(){
        tfMatricula.setText("");
    }

    private void comprobarCampos(){

    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

    @FXML
    void leerMatricula(ActionEvent event) {
        esCerrado = true;
        if(getVehiculo() != null){
            Node origen = (Node) event.getSource();
            Stage escenario = (Stage) origen.getScene().getWindow();
            escenario.close();
        }
    }

}
