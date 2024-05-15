package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VentanaPrincipal extends Controlador {

    public static List<Object> clientes = new ArrayList<>();

    public static List<Object> vehiculos = new ArrayList<>();

    public static List<Object> trabajos = new ArrayList<>();

    public static InsertarTrabajo insertarTrabajo;


    @FXML
    private Button btCinco;

    @FXML
    private Button btCuatro;

    @FXML
    private Button btDos;

    @FXML
    private ImageView btImagenCliente;

    @FXML
    private ImageView btImagenTrabajos;

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

    @FXML
    void initialize(){
        btImagenCliente.setImage(CLIENTES);
        btImagenVehiculos.setImage(VEHICULOS);
        btImagenTrabajos.setImage(TRABAJOS);
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_CLIENTES);
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_VEHICULOS);
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_TRABAJOS);
        mostrarMenuCliente();
    }


    private void ocultarBotones(Button ... botones){
        for(Button boton: botones){
            boton.setVisible(false);
            boton.setOnAction(null);
            boton.setGraphic(null);
        }
    }

    private void mostrarBotones(Map<Button,String[]> mapaBotones){
        for(Map.Entry<Button, String[]> entrada : mapaBotones.entrySet()){
            Button boton = entrada.getKey();
            String[] parametros = entrada.getValue();

            boton.setVisible(true);
            boton.setTooltip(new Tooltip(parametros[0]));
            boton.setGraphic(new ImageView(new Image(VentanaPrincipal.class.getResourceAsStream(String.format("/imagenes/%s", parametros[1])), 40, 40, true, true)));

        }
    }


    public void mostrarTabla(List<Object> lista){
        ObservableList<Object> rellenarTabla = FXCollections.observableArrayList(lista);
        tabla.setItems(rellenarTabla);
    }

    private void insertarCliente() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        mostrarClientes();
    }

    private void borrarCliente() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_CLIENTE);
        mostrarClientes();
    }

    private void mostrarClientes(){
        mostrarTabla(clientes);
    }

    private void modificarCliente(){
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.MODIFICAR_CLIENTE);
        mostrarClientes();
    }

    private void insertarVehiculo() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_VEHICULO);
        mostrarVehiculos();
    }

    private void borrarVehiculo() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_VEHICULO);
        mostrarVehiculos();
    }

    private void mostrarVehiculos(){
        mostrarTabla(vehiculos);
    }


    private void insertarTrabajo() {
        insertarTrabajo = (InsertarTrabajo) Controladores.get("/vistas/InsertarTrabajo.fxml", "Insertar Trabajo", this.getEscenario());
        insertarTrabajo.getEscenario().showAndWait();

    }

    private void borrarTrabajo() {}

    private void anadirHoras() {}

    private void anadirPrecio() {}

    private void cerrarTrabajo() {}

    private void mostrarTrabajos() {
        mostrarTabla(trabajos);
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

        TableColumn<Object, String> columnaTelefono = new TableColumn<>("Teléfono");
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        tabla.getColumns().add(columnaDni);
        tabla.getColumns().add(columnaNombre);
        tabla.getColumns().add(columnaTelefono);

        mostrarClientes();

        btUno.setOnAction(e -> insertarCliente());
        btDos.setOnAction(e -> borrarCliente());
        btTres.setOnAction(e -> modificarCliente());
        btCuatro.setOnAction(e -> mostrarClientes());

    }



    @FXML
    void mostrarMenuVehiculo() {
        tabla.getColumns().clear();
        tabla.getItems().clear();

        ocultarBotones(btUno, btDos, btTres, btCuatro,btCinco,btSeis);
        Map<Button, String[]> mapaBotonesVehiculos = Map.ofEntries(
                Map.entry(btUno, new String[]{"Insertar vehiculo", "vehiculos/insertarVehiculo.png"}),
                Map.entry(btDos, new String[]{"Borrar vehiculo", "vehiculos/borrarVehiculo.png"}),
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

        btUno.setOnAction(e -> insertarVehiculo());
        btDos.setOnAction(e -> borrarVehiculo());
        btTres.setOnAction(e -> mostrarVehiculos());

    }


    @FXML
    void mostrarMenuTrabajo() {
        tabla.getColumns().clear();
        tabla.getItems().clear();

        ocultarBotones(btUno, btDos, btTres, btCuatro,btCinco,btSeis);
        Map<Button, String[]> mapaBotonesTrabajo= Map.ofEntries(
                Map.entry(btUno, new String[]{"Insertar trabajo", "listar.png"}),
                Map.entry(btDos, new String[]{"Borrar trabajo", "listar.png"}),
                Map.entry(btTres, new String[]{"Añadir horas", "listar.png"}),
                Map.entry(btCuatro, new String[]{"Añadir precio material", "listar.png"}),
                Map.entry(btCinco, new String[]{"Cerrar trabajo", "listar.png"}),
                Map.entry(btSeis, new String[]{"Listar trabajos", "listar.png"})
        );

        mostrarBotones(mapaBotonesTrabajo);
        TableColumn<Object, String> columnaDni = new TableColumn<>("Dni");
        columnaDni.setCellValueFactory(e -> {Object obj = e.getValue();
            Trabajo trabajo = (Trabajo) obj; return new SimpleStringProperty(trabajo.getCliente().getDni());});

        TableColumn<Object, String> columnaMatricula = new TableColumn<>("Matrícula");
        columnaMatricula.setCellValueFactory(e -> {Object obj = e.getValue();
            Trabajo trabajo = (Trabajo) obj; return new SimpleStringProperty(trabajo.getVehiculo().matricula());});

        TableColumn<Object, String> columnaFechaInicio = new TableColumn<>("Fecha Inicio");
        columnaFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));

        TableColumn<Object, String> columnaFechaFin = new TableColumn<>("Fecha Fin");
        columnaFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));

        tabla.getColumns().add(columnaDni);
        tabla.getColumns().add(columnaMatricula);
        tabla.getColumns().add(columnaFechaInicio);
        tabla.getColumns().add(columnaFechaFin);

        mostrarTrabajos();

        btUno.setOnAction(e -> insertarTrabajo());
        btDos.setOnAction(e -> borrarTrabajo());
        btTres.setOnAction(e -> anadirHoras());
        btCuatro.setOnAction(e -> anadirPrecio());
        btCinco.setOnAction(e -> cerrarTrabajo());
        btSeis.setOnAction(e -> mostrarTrabajos());

    }




}
