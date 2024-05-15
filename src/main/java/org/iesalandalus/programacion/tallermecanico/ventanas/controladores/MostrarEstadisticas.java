package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

import java.time.LocalDate;
import java.util.Map;

public class MostrarEstadisticas extends Controlador {

    @FXML
    private DatePicker dpMes;

    @FXML
    private PieChart grafico;


    @FXML
    void initialize(){
        dpMes.valueProperty().addListener((observable, oldValue, newValue) -> enviarEvento(oldValue, newValue));
        grafico.setLegendSide(Side.LEFT);
    }

    public LocalDate getMes(){
        return dpMes.getValue();
    }

    private void enviarEvento(LocalDate oldValue, LocalDate newValue) {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.MOSTRAR_ESTADISTICAS_MENSUALES);
    }

    public void mostrarGrafico(Map<TipoTrabajo, Integer> estadisticas){
        ObservableList<PieChart.Data> datosGrafico = FXCollections.observableArrayList();
        for (Map.Entry<TipoTrabajo, Integer> entry : estadisticas.entrySet()) {
            datosGrafico.add(new PieChart.Data(entry.getKey().toString(), entry.getValue()));
        }
        grafico.setData(datosGrafico);

        grafico.getData().forEach( data -> {
            String numeroTrabajos = String.format("%.0f",(data.getPieValue()));
            Tooltip texto = new Tooltip(numeroTrabajos);
            Tooltip.install(data.getNode(),texto);
        });
    }

}
