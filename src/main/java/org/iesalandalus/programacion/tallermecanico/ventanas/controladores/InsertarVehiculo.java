package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;

import javax.naming.OperationNotSupportedException;

public class InsertarVehiculo extends Controlador {

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    public boolean esCerrado = false;

    public void limpiarCampos(){
        tfMarca.setText("");
        tfMatricula.setText("");
        tfModelo.setText("");
    }

    public Vehiculo getVehiculo(){
        String marca = tfMarca.getText();
        String matricula = tfMatricula.getText();
        String modelo = tfModelo.getText();
        return new Vehiculo(marca,modelo,matricula);
    }


    @FXML
    void crearVehiculo(ActionEvent event) throws OperationNotSupportedException {
        esCerrado = true;
        if(getVehiculo() != null){
            Node origen = (Node) event.getSource();
            Stage escenario = (Stage) origen.getScene().getWindow();
            escenario.close();
        }else{
            throw new OperationNotSupportedException("El vehiculo introducido es incorrecto");
        }
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenario = (Stage) origen.getScene().getWindow();
        escenario.close();
    }

}
