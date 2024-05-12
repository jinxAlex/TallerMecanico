package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class VentanaPrincipal extends Controlador {

    public static List<Object> datos = new ArrayList<>();

    @FXML
    private Button btCinco;

    @FXML
    private Button btCuatro;

    @FXML
    private Button btDos;

    @FXML
    private ImageView btImagenCinco;

    @FXML
    private ImageView btImagenCliente;

    @FXML
    private ImageView btImagenCuatro;

    @FXML
    private ImageView btImagenDos;

    @FXML
    private ImageView btImagenSeis;

    @FXML
    private ImageView btImagenTrabajos;

    @FXML
    private ImageView btImagenTres;

    @FXML
    private ImageView btImagenUno;

    @FXML
    private ImageView btImagenVehiculos;

    @FXML
    private Button btSeis;

    @FXML
    private Button btTres;

    @FXML
    private Button btUno;

    @FXML
    private TableView<Object> tabla = new TableView<>();

    private static final Image CLIENTES = new Image(VentanaPrincipal.class.getResourceAsStream("/imagenes/clientes/clientes.png"), 50, 50, true, true);

    private static final Image VEHICULOS = new Image(VentanaPrincipal.class.getResourceAsStream("/imagenes/vehiculos/vehiculos.png"),50, 50, true, true);

    private static final Image TRABAJOS = new Image(VentanaPrincipal.class.getResourceAsStream("/imagenes/trabajos/trabajos.png"),50, 50, true, true);


    private void ocultarBotones(Button ... botones){
        for(Button boton: botones){
            boton.setOpacity(0);
            boton.setOnAction(null);
        }
    }

    private void mostrarBotones(Map<Button,String[]> mapaBotones){
        for(Map.Entry<Button, String[]> entrada : mapaBotones.entrySet()){
            Button boton = entrada.getKey();
            String[] parametros = entrada.getValue();

            boton.setOpacity(100);
            boton.setTooltip(new Tooltip(parametros[0]));
            boton.setGraphic(new ImageView(new Image(VentanaPrincipal.class.getResourceAsStream(String.format("/imagenes/%s", parametros[1])), 40, 40, true, true)));

        }
    }

    @FXML
    void initialize(){
        btImagenCliente.setImage(CLIENTES);
        btImagenVehiculos.setImage(VEHICULOS);
        btImagenTrabajos.setImage(TRABAJOS);
        mostrarMenuCliente();
    }

    public void mostrarTabla(List<Object> lista){
        ObservableList<Object> rellenarTabla = FXCollections.observableArrayList(lista);
        tabla.setItems(rellenarTabla);
        datos.clear();
    }

    private void mostrarClientes(){
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_CLIENTES);
        mostrarTabla(datos);
    }

    private void mostrarVehiculos(){
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_VEHICULOS);
        mostrarTabla(datos);
    }


    @FXML
    void mostrarMenuCliente() {
        tabla.getColumns().clear();
        tabla.getItems().clear();

        ocultarBotones(btUno, btDos, btTres, btCuatro,btCinco,btSeis);

        Map<Button, String[]> mapaBotonesClientes = Map.ofEntries(
                Map.entry(btUno, new String[]{"Insertar cliente", "clientes/insertarCliente.png"}),
                Map.entry(btDos, new String[]{"Borrar cliente", "clientes/borrarCliente.png"}),
                Map.entry(btTres, new String[]{"Modificar cliente", "clientes/modificarCliente.png"}),
                Map.entry(btCuatro, new String[]{"Listar clientes", "listar.png"})
        );

        mostrarBotones(mapaBotonesClientes);

        TableColumn<Object, String> columnaDni = new TableColumn<>("Dni");
        columnaDni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        TableColumn<Object, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Object, String> columnaTelefono = new TableColumn<>("Telefono");
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        tabla.getColumns().add(columnaDni);
        tabla.getColumns().add(columnaNombre);
        tabla.getColumns().add(columnaTelefono);

        btUno.setOnAction(e -> insertarCliente());
        btCuatro.setOnAction(e -> mostrarClientes());

    }

    private void insertarCliente() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
    }

    @FXML
    void mostrarMenuVehiculo() {
        tabla.getColumns().clear();
        tabla.getItems().clear();

        ocultarBotones(btUno, btDos, btTres, btCuatro,btCinco,btSeis);
        Map<Button, String[]> mapaBotonesVehiculos = Map.ofEntries(
                Map.entry(btUno, new String[]{"Insetar vehiculo", "clientes/insertarCliente.png"}),
                Map.entry(btDos, new String[]{"Borrar vehiculo", "clientes/borrarCliente.png"}),
                Map.entry(btTres, new String[]{"Listar clientes", "listar.png"})
        );

        mostrarBotones(mapaBotonesVehiculos);
        TableColumn<Object, String> columnaMarca = new TableColumn<>("Marca");
        columnaMarca.setCellValueFactory(e -> {Object obj = e.getValue();
            Vehiculo vehiculo = (Vehiculo) obj; return new SimpleStringProperty(vehiculo.marca());});

        TableColumn<Object, String> columnaModelo = new TableColumn<>("Modelo");
        columnaModelo.setCellValueFactory(e -> {Object obj = e.getValue();
            Vehiculo vehiculo = (Vehiculo) obj; return new SimpleStringProperty(vehiculo.modelo());});

        TableColumn<Object, String> columnaMatricula = new TableColumn<>("Matrícula");
        columnaMatricula.setCellValueFactory(e -> {Object obj = e.getValue();
            Vehiculo vehiculo = (Vehiculo) obj; return new SimpleStringProperty(vehiculo.matricula());});

        tabla.getColumns().add(columnaMarca);
        tabla.getColumns().add(columnaModelo);
        tabla.getColumns().add(columnaMatricula);

        mostrarVehiculos();

        btTres.setOnAction(e -> mostrarVehiculos());

    }

    @FXML
    void mostrarBotonesVehiculo(ActionEvent event) {

    }

}
