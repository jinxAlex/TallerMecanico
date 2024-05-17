package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

public class ModificarCliente extends Controlador {
    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTlf;

    @FXML
    void cancelarAccion(ActionEvent event) {

    }

    public Cliente getClienteDni(){
        return Cliente.get(tfDni.getText());
    }

    public String getNuevoNombre(){
        return tfNombre.getText();
    }

    public String getNuevoTelefono(){
        return tfTlf.getText();
    }

    @FXML
    void modificarCliente(ActionEvent event) {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.MODIFICAR_CLIENTE);

    }


}
