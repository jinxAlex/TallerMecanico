package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import java.io.File;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {
    private final List<Vehiculo> coleccionVehiculos;
    private static final String FICHERO_VEHICULOS = String.format("%s%s%s", "ficheros",File.separator,"vehiculos.xml");
    private static final String RAIZ = "Vehiculos";
    private static final String VEHICULO = "vehiculo";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";
    private static Vehiculos instancia;

    private Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    static Vehiculos getInstancia(){
        if(instancia == null){
            instancia = new Vehiculos();
        }
        return instancia;
    }

    public void comenzar(){
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_VEHICULOS);
        if(documentoXml != null){
            procesarDocumentoXml(documentoXml);
        }
    }

    private void procesarDocumentoXml(Document documentoXml){
        NodeList vehiculos = documentoXml.getElementsByTagName(VEHICULO);
        for(int i = 0; i < vehiculos.getLength();i++){
            Node vehiculo = vehiculos.item(i);
            try{
                if(vehiculo.getNodeType() == Node.ELEMENT_NODE){
                    insertar(getVehiculo((Element) vehiculo));
                }
            }catch (Exception e){
                System.out.printf("ERROR: No se pudo leer el vehiculo %d, %s",i,e.getMessage());
            }
        }
    }

    private Vehiculo getVehiculo(Element elemento){
        String marca = elemento.getAttribute(MARCA);
        String modelo = elemento.getAttribute(MODELO);
        String matricula = elemento.getAttribute(MATRICULA);
        return new Vehiculo(marca, modelo, matricula);
    }

    public void terminar(){
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_VEHICULOS);
    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if(constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for(Vehiculo vehiculo: coleccionVehiculos){
                Element elemento = getElemento(documentoXml,vehiculo);
                if(elemento.getNodeType() == Node.ELEMENT_NODE){
                    documentoXml.getDocumentElement().appendChild(elemento);
                }
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Vehiculo vehiculo){
        Element elemento = documentoXml.createElement(VEHICULO);
        elemento.setAttribute(MARCA, vehiculo.marca());
        elemento.setAttribute(MODELO, vehiculo.modelo());
        elemento.setAttribute(MATRICULA, vehiculo.matricula());
        return elemento;
    }

    @Override
    public List<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculos);
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un veh�culo nulo.");
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("Ya existe un veh�culo con esa matr�cula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un veh�culo nulo.");
        int indice = coleccionVehiculos.indexOf(vehiculo);
        return (indice == -1) ? null : coleccionVehiculos.get(indice);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un veh�culo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("No existe ning�n veh�culo con esa matr�cula.");
        }
        coleccionVehiculos.remove(vehiculo);
    }

}
