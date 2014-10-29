/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Capa_Persistencia;

import DTO.AlquilerBean;
import DTO.ClienteBean;
import DTO.DTO;
import DTO.VideoBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Israel
 */
public class Oracle implements DAO {

    @Override
    public Connection conectar() {
            Connection con=null;
        try{ 
              Class.forName("oracle.jdbc.driver.OracleDriver");
              con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "PATRONES", "1234");

        }catch(  ClassNotFoundException | SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
        return con;
    }

    @Override
    public void del_cliente(int cedula) {
         try ( Connection con = conectar();) {
                 Statement stmt = con.createStatement();
                 stmt.executeQuery("exec del_cliente("+cedula+")");
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
    }

    @Override
    public void del_video(int id_video) {
        try ( Connection con = conectar();) {
                 Statement stmt = con.createStatement();
                 stmt.executeQuery("exec del_video("+id_video+")");
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
    }

    @Override
    public void add_cliente(int cedula, String nombre, String direccion, int salario_mensual, int telefono, String protencial, String email, String suscripto) {
        try ( Connection con = conectar();) {
                 Statement stmt = con.createStatement();
                 String query = "INSERT INTO clientes (id_cliente,nombre,direccion,salario_mensual,telefono,potencial,email,suscrito) VALUES("
                         + cedula +",'"+  nombre +"','"+  direccion +"',"+ salario_mensual +"," 
                         + telefono +",'"+ protencial +"','"+ email +"','"+ suscripto+"')";

                 System.out.println(query);
                 stmt.executeQuery(query);
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
    }

    @Override
    public void add_video(int id_video, String nombre, String clasificacion, String genero, String resumen, int locales_id_local) {
         try ( Connection con = conectar();) {
                 Statement stmt = con.createStatement();
                 stmt.executeQuery("exec add_video("+ id_video +","+  nombre +","+  clasificacion +","+ genero +","+ 
                                                        resumen +","+ locales_id_local +")");
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
    }

    @Override
    public void add_alquiler(int id_alquiler, Date fecha_alquiler, Date fecha_planeada_entrega, int video_id_video, int clientes_id_cliente) {
       try ( Connection con = conectar();) {
           
           
           
                 Statement stmt = con.createStatement();
                 String query = "exec add_alquiler("+ id_alquiler +",'29-oct-2014','31-oct-2014',"+ video_id_video +","+ 
                                                        clientes_id_cliente +")";
                 System.out.println(query);
                 stmt.executeQuery(query);
                 
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Error en add_alquiler "); System.out.println(e);}
    }

    @Override
    public void devolver_video(int video, int cliente) {
         try ( Connection con = conectar();) {
                 Statement stmt = con.createStatement();
                 stmt.executeQuery("exec devolver_video("+ video +","+  cliente +")");
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
    }
    
    public ClienteBean get_cliente(int id_cliente){    
         
        ClienteBean persona= new ClienteBean();
        
        try ( Connection con = conectar();){
            Statement stmt = con.createStatement();
            String query ="select * from clientes where id_cliente ="+id_cliente;
            ResultSet rset =stmt.executeQuery(query);
           
            
            while (rset.next()){
               // System.out.println("hola");
               // System.out.println(rset.getInt(1));
                persona.setId_cliente(rset.getInt(1));
                persona.setNombre(rset.getString(2));
                persona.setDireccion(rset.getString(3));
                persona.setSalario_mensual(rset.getInt(4));
                persona.setTelefono(rset.getInt(5));
                persona.setPotencial(rset.getString(6));
                persona.setEmail(rset.getString(7));
                persona.setSuscripto(rset.getString(8));
             }
         
            stmt.close();
            con.close();
       }catch( SQLException e ) { System.out.println("error en get_cliente "); System.out.println(e);}
        
        return persona;
    }
    
    public VideoBean get_video(int id_video){    
        
        VideoBean video= new VideoBean();
        
        try ( Connection con = conectar();){
            Statement stmt = con.createStatement();
            String query ="select * from video where id_video ="+id_video;
            ResultSet rset =stmt.executeQuery(query);
             
             while (rset.next()){
                video.setId_video(rset.getInt(1));
                video.setNombre(rset.getString(2));
                video.setClasificacion(rset.getString(3));
                video.setGenero(rset.getString(4));
                video.setResumen(rset.getString(5));
                video.setLocales_id_local(rset.getInt(6));                
             }
           
            stmt.close();
            con.close();
       }catch( SQLException e ) { System.out.println(" error en get_video "); System.out.println(e);}
        
        return video;
    }
    
    @Override
    public ArrayList<AlquilerBean> get_alquiler(int id_cliente){ 
        
          ArrayList lista = new ArrayList<AlquilerBean>();
                  
        try ( Connection con = conectar();){
            Statement stmt = con.createStatement();
            String query ="select * from alquiler where clientes_id_cliente ="+id_cliente;
            ResultSet rset =stmt.executeQuery(query);
            
            while (rset.next()){
                AlquilerBean alquiler= new AlquilerBean();
                alquiler.setId_alquiler(rset.getInt(1));
                alquiler.setFecha_alquiler(rset.getDate(2));
                alquiler.SetFecha_planeada_entrega(rset.getDate(3));
                alquiler.getVideo_id_video(rset.getInt(4));
                alquiler.getClientes_id_cliente(rset.getInt(5));  
                lista.add(alquiler);
             }
           
            stmt.close();
            con.close();
       }catch( SQLException e ) { System.out.println(" error en get_alquiler "); System.out.println(e);}
        
        return lista;
    }
}
