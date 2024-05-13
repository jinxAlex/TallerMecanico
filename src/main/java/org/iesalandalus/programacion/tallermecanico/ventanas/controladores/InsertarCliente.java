package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.naming.OperationNotSupportedException;

public class InsertarCliente extends Controlador {

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTlf;

    public boolean esCerrado = false;

    public Cliente getCliente(){
        String dni = tfDni.getText();
        String tlf = tfTlf.getText();
        String nombre = tfNombre.getText();
        return new Cliente(nombre,dni,tlf);
    }

    public void limpiarCampos(){
        tfDni.setText("");
        tfNombre.setText("");
        tfTlf.setText("");
    }

    @FXML
    void crearCliente(ActionEvent event) throws OperationNotSupportedException {
        esCerrado = true;
        if(getCliente() != null){
            Node origen = (Node) event.getSource();
            Stage escenario = (Stage) origen.getScene().getWindow();
            escenario.close();
        }else{
            throw new OperationNotSupportedException("El cliente introducido es incorrecto");
        }
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

}
