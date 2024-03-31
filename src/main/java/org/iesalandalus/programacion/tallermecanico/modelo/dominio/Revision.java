package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 35F;
    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente,vehiculo,fechaInicio);
    }

    public Revision(Revision revision){
        super(revision);
    }

    public float getPrecioEspecifico() {
        return (super.getPrecioEspecifico() + getHoras() * FACTOR_HORA ) ;
    }


    @Override
    public String toString() {
        String salida;
        if(!estaCerrado()){
            salida = String.format("Revisión -> %s - %s (%s) - %s %s - %s (%s - ): %d horas",cliente.getNombre(),cliente.getDni(),cliente.getTelefono(),vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(),getFechaInicio().format(FORMATO_FECHA), getHoras());
        }else{
            salida = String.format("Revisión -> %s - %s (%s) - %s %s - %s (%s - %s): %d horas, %.2f € total",cliente.getNombre(),cliente.getDni(),cliente.getTelefono(),vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(),getFechaInicio().format(FORMATO_FECHA),getFechaFin().format(FORMATO_FECHA) , getHoras(), getPrecio());
        }
        return salida;
    }
}
