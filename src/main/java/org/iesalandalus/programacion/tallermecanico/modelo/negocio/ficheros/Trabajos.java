package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Trabajos implements ITrabajos {
    private static final String FICHERO_TRABAJOS = String.format("%s%s%s", "ficheros", File.separator, "trabajos.xml");
    private static final String RAIZ = "trabajos";
    private static final String TRABAJO = "trabajo";
    private static final String CLIENTE = "cliente";
    private static final String VEHICULO = "vehiculo";
    private static final String FECHA_INICIO = "fechaInicio";
    private static final String FECHA_FIN = "fechaFin";
    private static final String HORAS = "horas";
    private static final String PRECIO_MATERIAL = "precioMaterial";
    private static final String TIPO = "tipo";
    private static final String REVISION = "revision";
    private static final String MECANICO = "mecanico";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final List<Trabajo> coleccionTrabajos;

    private static Trabajos instancia;

    private Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    protected static Trabajos getInstancia(){
        if(instancia == null){
            instancia = new Trabajos();
        }
        return instancia;
    }

    public void comenzar(){
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_TRABAJOS);
        if(documentoXml != null){
            procesarDocumentoXml(documentoXml);
        }
    }

    private void procesarDocumentoXml(Document documentoXml){
        NodeList trabajos = documentoXml.getElementsByTagName(TRABAJO);
        for(int i = 0; i < trabajos.getLength(); i++){
            Node trabajo = trabajos.item(i);
            try{
                if(trabajo.getNodeType() == Node.ELEMENT_NODE){
                    insertar(getTrabajo((Element)trabajo));
                }
            }catch( Exception e){
                System.out.printf("ERROR: No se pudo leer el trabajo %d, %s",i,e.getMessage());
            }
        }
    }

    private Trabajo getTrabajo(Element elemento) throws OperationNotSupportedException{
        Trabajo trabajo = null;
        String dni = elemento.getAttribute(CLIENTE);
        Cliente cliente = Cliente.get(dni);
        cliente = Clientes.getInstancia().buscar(cliente);
        Vehiculo vehiculo = Vehiculo.get(elemento.getAttribute(VEHICULO));
        vehiculo = Vehiculos.getInstancia().buscar(vehiculo);
        LocalDate fechaInicio = LocalDate.parse(elemento.getAttribute(FECHA_INICIO),FORMATO_FECHA);
        if(elemento.getAttribute(TIPO).equals(REVISION)){
            trabajo = new Revision(cliente, vehiculo, fechaInicio);
        }else if(elemento.getAttribute(TIPO).equals(MECANICO)){
            trabajo = new Mecanico(cliente, vehiculo, fechaInicio);
            if(elemento.hasAttribute(PRECIO_MATERIAL)){
               ((Mecanico) trabajo).anadirPrecioMaterial(Float.parseFloat(elemento.getAttribute(PRECIO_MATERIAL)));
            }
        }
        if(elemento.hasAttribute(HORAS) && trabajo != null){
            trabajo.anadirHoras(Integer.parseInt(elemento.getAttribute(HORAS)));
        }

        if(elemento.hasAttribute(FECHA_FIN) && trabajo != null){
            trabajo.cerrar(LocalDate.parse(elemento.getAttribute(FECHA_FIN),FORMATO_FECHA));
        }
        
        return trabajo;
    }

    public void terminar(){
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_TRABAJOS);
    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if(constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for(Trabajo trabajo: coleccionTrabajos){
                Element elemento = getElemento(documentoXml,trabajo);
                if(elemento.getNodeType() == Node.ELEMENT_NODE){
                    documentoXml.getDocumentElement().appendChild(elemento);
                }
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Trabajo trabajo){
        Element elemento = documentoXml.createElement(TRABAJO);
        elemento.setAttribute(CLIENTE, trabajo.getCliente().getDni());
        elemento.setAttribute(VEHICULO, trabajo.getVehiculo().matricula());
        elemento.setAttribute(FECHA_INICIO, trabajo.getFechaInicio().format(FORMATO_FECHA));
        if(trabajo.estaCerrado()){
            elemento.setAttribute(FECHA_FIN, trabajo.getFechaFin().format(FORMATO_FECHA));
        }
        if(trabajo.getHoras() > 0){
            elemento.setAttribute(HORAS, String.format("%d", trabajo.getHoras()));
        }
        if(trabajo instanceof Mecanico mecanico){
            elemento.setAttribute(TIPO,MECANICO);
            if(mecanico.getPrecioMaterial() > 0){
                elemento.setAttribute(PRECIO_MATERIAL, String.format(Locale.US,"%f",mecanico.getPrecioMaterial()));
            }
        }else if(trabajo instanceof Revision){
            elemento.setAttribute(TIPO, REVISION);
        }

        return elemento;
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }

    public void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws OperationNotSupportedException {
        for (Trabajo trabajoActual : coleccionTrabajos) {
            if (vehiculo.equals(trabajoActual.getVehiculo())) {
                if (!trabajoActual.estaCerrado()) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
                } else if (!fechaTrabajo.isAfter(trabajoActual.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
                }
            }
            if (cliente.equals(trabajoActual.getCliente())) {
                if (!trabajoActual.estaCerrado()) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
                } else if (!fechaTrabajo.isAfter(trabajoActual.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
                }
            }
        }
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Trabajo trabajoAbierto = null;
        Iterator<Trabajo> iterador = coleccionTrabajos.iterator();
        while (iterador.hasNext() && trabajoAbierto == null) {
            Trabajo trabajoActual = iterador.next();
            if (trabajoActual.getVehiculo().equals(vehiculo) && !trabajoActual.estaCerrado()) {
                trabajoAbierto = trabajoActual;
            }
        }
        if (trabajoAbierto == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajoAbierto;
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        ArrayList<Trabajo> coleccionTrabajossCliente = new ArrayList<>();
        for (Trabajo trabajoActual : coleccionTrabajos) {
            if (trabajoActual.getCliente().equals(cliente)) {
                coleccionTrabajossCliente.add(trabajoActual);
            }
        }
        return coleccionTrabajossCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        ArrayList<Trabajo> revisionesVehiculo = new ArrayList<>();
        for (Trabajo trabajoActual : coleccionTrabajos) {
            if (trabajoActual.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(trabajoActual);
            }
        }
        return revisionesVehiculo;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return (indice == -1) ? null : coleccionTrabajos.get(indice);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo añadir horas a un trabajo nulo.");
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if(trabajoAbierto == null){
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        if(trabajoAbierto instanceof Mecanico trabajoMecanico){
            trabajoMecanico.anadirHoras(horas);
        }else if (trabajoAbierto instanceof Revision trabajoRevision){
            trabajoRevision.anadirHoras(horas);
        }
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo añadir precio del material a un trabajo nulo.");
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if(trabajoAbierto instanceof Mecanico trabajoMecanico){
            trabajoMecanico.anadirPrecioMaterial(precioMaterial);
        }else if (trabajoAbierto instanceof Revision){
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo cerrar un trabajo nulo.");
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        trabajoAbierto.cerrar(fechaFin);
    }

    @Override
    public Map<TipoTrabajo, Integer> inicialicarEstadisticas(){
        Map<TipoTrabajo, Integer> estadisticasMensuales = new EnumMap<>(TipoTrabajo.class);
        for(TipoTrabajo tipoTrabajo: TipoTrabajo.values()){
            estadisticasMensuales.put(tipoTrabajo,0);
        }
        return estadisticasMensuales;
    }
    @Override
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes){
        Objects.requireNonNull(mes,"El mes no puede ser nulo.");
        Map<TipoTrabajo, Integer> estadisticasMensuales = inicialicarEstadisticas();
        for(Trabajo trabajo: coleccionTrabajos){
            if(trabajo.getFechaInicio().getMonth() == mes.getMonth() && trabajo.getFechaInicio().getYear() == mes.getYear()){
                TipoTrabajo tipoTrabajo = TipoTrabajo.get(trabajo);
                estadisticasMensuales.put(tipoTrabajo,estadisticasMensuales.get(tipoTrabajo) + 1);
            }
        }
        return estadisticasMensuales;
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}
