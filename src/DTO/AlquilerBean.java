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
public class AlquilerBean implements DTO {
    private int id_alquiler ;          
    private Date fecha_alquiler ; 
    private Date fecha_planeada_entrega ;
    private int video_id_video ; 
    private int clientes_id_cliente ;
  
    
    public AlquilerBean() {}//constructor

    
    public int getId_alquiler(){
        return this.id_alquiler;
    }
    
    public Date getFecha_alquiler(){
        return this.fecha_alquiler;
    }
    
    public Date getFecha_planeada_entrega(){
        return this.fecha_planeada_entrega;
    }
    
    public int getVideo_id_video(){
        return this.video_id_video;
    }
    
    public int getClientes_id_cliente(){
        return this.clientes_id_cliente;
    }
    
    public int setId_alquiler(int var){
        return this.id_alquiler = var;
    }
    
    public Date setFecha_alquiler( Date var){
        return this.fecha_alquiler = var;
    }
    
    public Date SetFecha_planeada_entrega( Date var){
        return this.fecha_planeada_entrega = var;
    }
    
    public int getVideo_id_video(int var){
        return this.video_id_video = var;
    }
    
    public int getClientes_id_cliente(int var){
        return this.clientes_id_cliente = var;
    }
    
  }//fin clase  AlquilerBean
