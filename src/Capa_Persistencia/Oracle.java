/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Capa_Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
              con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "system", "123456");

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
                 stmt.executeQuery("exec add_cliente("+ cedula +","+  nombre +","+  direccion +","+ salario_mensual +","+ 
                                                        telefono +","+ protencial +","+ email +","+ suscripto+")");
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
                 stmt.executeQuery("exec add_alquiler("+ id_alquiler +","+  fecha_alquiler +","+  fecha_planeada_entrega +","+ video_id_video +","+ 
                                                        clientes_id_cliente +")");
                 stmt.close();
                 con.close();
          }catch( SQLException e ) { System.out.println("Empezo54 "); System.out.println(e);}
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
    
}
