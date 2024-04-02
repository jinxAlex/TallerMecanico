package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {
    private static final float FACTOR_HORA = 30F;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;

    private float precioMaterial= 0;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente,vehiculo,fechaInicio);
    }


    public Mecanico(Mecanico mecanico){
        super(mecanico);
        this.precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial(){
        return precioMaterial;
    }

    public float getPrecioEspecifico() {
        return (estaCerrado()) ? FACTOR_HORA * getHoras() + FACTOR_PRECIO_MATERIAL * getPrecioMaterial() : 0;
    }



    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(estaCerrado()){
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    @Override
    public String toString() {
        String salida;
        if(!estaCerrado()){
            salida = String.format("Mecánico -> %s - %s (%s) - %s %s - %s (%s - ): %d horas, %.2f € en material",cliente.getNombre(),cliente.getDni(),cliente.getTelefono(),vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(),getFechaInicio().format(FORMATO_FECHA), getHoras(), getPrecioMaterial());
        }else{
            salida = String.format("Mecánico -> %s - %s (%s) - %s %s - %s (%s - %s): %d horas, %.2f € en material, %.2f € total",cliente.getNombre(),cliente.getDni(),cliente.getTelefono(),vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(),getFechaInicio().format(FORMATO_FECHA),getFechaFin().format(FORMATO_FECHA) , getHoras(), getPrecioMaterial(), getPrecio());
        }
        return salida;
    }
}
