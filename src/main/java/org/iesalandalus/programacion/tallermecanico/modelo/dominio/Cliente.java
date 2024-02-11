package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "[A-Z]([a-z]|[áéíóú])+( [A-Z]([a-z]|[áéíóú])+)*";
    private static final String ER_DNI = "\\d{8}[A-Z]";
    private static final String ER_TELEFONO = "\\d{9}";
    private String nombre;

    private String telefono;

    private String dni;

    public Cliente(String nombre, String dni, String telefono){
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }


    public Cliente(Cliente cliente){
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        setNombre(cliente.getNombre());
        setDni(cliente.getDni());
        setTelefono(cliente.getTelefono());
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if(!nombre.matches(ER_NOMBRE)){
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni,"El DNI no puede ser nulo.");
        if(!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if(!comprobarLetraDni(dni)){
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni){
        boolean condicion = false;
        char[] letrasDni = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        int resto = Integer.parseInt(dni.substring(0, 8)) % 23;
        char letraCalculada = letrasDni[resto];
        char letra = dni.charAt(8);
        if(letra == letraCalculada){
            condicion = true;
        }
        return condicion;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");
        if(!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDni() {
        return dni;
    }

    public static Cliente get(String dni){
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        return new Cliente("Busqueda", dni,"123456789");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", this.nombre, this.dni,  this.telefono);
    }
}
