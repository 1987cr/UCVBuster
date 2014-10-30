/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ClienteBean implements DTO {
    
    private int id_cliente ; 
    private String nombre ;
    private String  direccion ; 
    private int  salario_mensual ;
    private String telefono ; 
    private String potencial;
    private String  email ;
    private String suscripto;
    
    public ClienteBean() {}//constructor
    
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSalario_mensual(int salario_mensual) {
        this.salario_mensual = salario_mensual;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPotencial(String potencial) {
        this.potencial = potencial;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSuscripto(String suscripto) {
        this.suscripto = suscripto;
    }
   
    
    public String getDireccion() {
        return direccion;
    }

    public int getSalario_mensual() {
        return salario_mensual;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPotencial() {
        return potencial;
    }

    public String getEmail() {
        return email;
    }

    public String getSuscripto() {
        return suscripto;
    }
        
    public int getId_cliente(){
        return this.id_cliente;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
       
}
