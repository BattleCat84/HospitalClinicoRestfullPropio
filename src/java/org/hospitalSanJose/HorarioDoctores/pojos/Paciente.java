/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hospitalSanJose.HorarioDoctores.pojos;

/**
 *
 * @author Migueli Ramos
 */
public class Paciente {
    int PK_ID;
    String nombre;
    String apellidos;
    
    public Paciente(){
    }

    public Paciente(int PK_ID, String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getPK_ID() {
        return PK_ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setPK_ID(int PK_ID) {
        this.PK_ID = PK_ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    
    
    
}
