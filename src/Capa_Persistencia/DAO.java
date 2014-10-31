/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Capa_Persistencia;

import DTO.AlquilerBean;
import DTO.ClienteBean;
import DTO.VideoBean;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Israel
 */
public interface DAO {
    
     public abstract Connection conectar ();
    
     public abstract void del_cliente(int cedula);
     public abstract void del_video(int id_video);
     
     public abstract void add_cliente
       (int cedula, String nombre, String direccion, int salario_mensual, 
               String telefono, String protencial, String email, String suscripto);
       
     public abstract void add_video
       ( int id_video, String nombre, String clasificacion, String genero, 
               String resumen, int locales_id_local);
       
     public abstract void add_alquiler
       ( String fecha_alquiler, String fecha_planeada_entrega, int video_id_video, 
              int clientes_id_cliente);
       
    public abstract void devolver_video (int video, int cliente);
    
    public abstract ClienteBean get_cliente(int id_cliente);
    
    public abstract VideoBean get_video(int id_video);
    
    public abstract ArrayList<AlquilerBean>  get_alquiler(int id_alquiler);
       
   
             
}//fin clase
