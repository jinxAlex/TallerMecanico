package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Revision {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private static final float PRECIO_HORA = 30F;
    private static final float PRECIO_DIA = 10F;
    private static final float PRECIO_MATERIAL = 1.50F;

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
    private LocalDate fechaInicio;

    private LocalDate fechaFin;
    private int horas = 0;
    private float precioMaterial= 0;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);

    }


    public Revision(Revision revision){
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        setCliente(new Cliente(revision.cliente));
        setVehiculo(revision.vehiculo);
        setFechaInicio(revision.fechaInicio);
        fechaFin = revision.fechaFin;
        horas += revision.getHoras();
        precioMaterial += revision.getPrecioMaterial();
    }
    public Cliente getCliente(){
        return cliente;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    public LocalDate getFechaInicio(){
        return fechaInicio;
    }



    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }


    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }


    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if(fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }



    private void setFechaFin(LocalDate fechaFin){
        Objects.requireNonNull(fechaFin,"La fecha no puede ser nula.");
        if(fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if(fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras(){
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if(estaCerrada()){
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        if(horas <= 0){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial(){
        return precioMaterial;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }


    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(estaCerrada()){
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada(){
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");
        if(estaCerrada()){
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio(){
        return (getHoras() * PRECIO_HORA) + (getDias() * PRECIO_DIA) + (getPrecioMaterial() * PRECIO_MATERIAL);
    }

    private float getDias(){
        float dias = 0;
        if(fechaFin != null){
            dias = fechaInicio.until(getFechaFin()).getDays();
        }
        return dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo) && Objects.equals(fechaInicio, revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }

    @Override
    public String toString() {
        String salida;
        if(getFechaFin() == null){
            salida = String.format("%s - %s (%s) - %s %s - %s: (%s - ), %d horas, %.2f € en material",cliente.getNombre(),cliente.getDni(),cliente.getTelefono(),vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(),getFechaInicio().format(FORMATO_FECHA), getHoras(), getPrecioMaterial());
        }else{
            salida = String.format("%s - %s (%s) - %s %s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",cliente.getNombre(),cliente.getDni(),cliente.getTelefono(),vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(),getFechaInicio().format(FORMATO_FECHA),getFechaFin().format(FORMATO_FECHA) , getHoras(), getPrecioMaterial(), getPrecio());
        }
        return salida;
    }
}
