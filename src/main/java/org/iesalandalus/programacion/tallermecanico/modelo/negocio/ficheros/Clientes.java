package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import java.io.File;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;

public class Clientes implements IClientes {
    private static final String FICHERO_CLIENTES = String.format("%s%s%s", "ficheros", File.separator, "clientes.xml");
    private static final String RAIZ = "clientes";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";

    private final List<Cliente> coleccionClientes;
    private static Clientes instancia;


    private Clientes() {
        coleccionClientes = new ArrayList<>();
    }


    protected static Clientes getInstancia(){
        if(instancia == null){
            instancia = new Clientes();
        }
        return instancia;
    }

    public void comenzar(){
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_CLIENTES);
        if(documentoXml != null){
            procesarDocumentoXml(documentoXml);
        }
    }

    private void procesarDocumentoXml(Document documentoXml){
        NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);
        for(int i = 0; i < clientes.getLength(); i++){
            Node cliente = clientes.item(i);
            try{
                if(cliente.getNodeType() == Node.ELEMENT_NODE){
                    insertar(getCliente((Element)cliente));
                }
            }catch( Exception e){
                System.out.printf("ERROR: No se pudo leer el cliente %d, %s",i,e.getMessage());
            }
        }
    }

    private Cliente getCliente(Element elemento){
        String nombre = String.valueOf(elemento.getAttribute(NOMBRE));
        String dni = String.valueOf(elemento.getAttribute(DNI));
        String telefono = String.valueOf(elemento.getAttribute(TELEFONO));

        return new Cliente(nombre,dni,telefono);
    }

    public void terminar(){
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_CLIENTES);
    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if(constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for(Cliente cliente: coleccionClientes){
                Element elemento = getElemento(documentoXml,cliente);
                if(elemento.getNodeType() == Node.ELEMENT_NODE){
                    documentoXml.getDocumentElement().appendChild(elemento);
                }
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Cliente cliente){
        Element elemento = documentoXml.createElement(CLIENTE);
        elemento.setAttribute(DNI,cliente.getDni());
        elemento.setAttribute(NOMBRE,cliente.getNombre());
        elemento.setAttribute(TELEFONO,cliente.getTelefono());
        return elemento;
    }

    @Override
    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes);
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteBuscado = buscar(cliente);
        if (clienteBuscado == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        boolean haSidoModificado = false;
        if(nombre != null && !nombre.isBlank()){
            clienteBuscado.setNombre(nombre);
            haSidoModificado = true;
        }
        if(telefono != null && !telefono.isBlank()){
            clienteBuscado.setTelefono(telefono);
            haSidoModificado = true;
        }
        return haSidoModificado;
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return (indice == -1) ? null : coleccionClientes.get(indice);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (!coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
