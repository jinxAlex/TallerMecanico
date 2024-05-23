package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class LeerDni extends Controlador {
    @FXML
    private TextField tfDni;

    public boolean esCerrado = false;

    public Cliente getCliente() {
        return Cliente.get(tfDni.getText());
    }

    public void limpiarCampos(){
        tfDni.setText("");
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
        if(getCliente() != null){
            Node origen = (Node) event.getSource();
            Stage escenario = (Stage) origen.getScene().getWindow();
            escenario.close();
        }
    }
}
