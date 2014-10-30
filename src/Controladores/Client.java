
package Controladores;

import Capa_Persistencia.Oracle;
import DTO.AlquilerBean;

import DTO.ClienteBean;

import java.io.IOException;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) throws IOException{
        
        
       /* Oracle ora= new Oracle();
        ClienteBean perso = new ClienteBean();
        perso = ora.get_cliente(20911444);
      //  System.out.println(perso.getNombre());
        
      //  System.out.println("anadir");
        ora.add_cliente(222222, "senor peligro", "dire", 5000, 0414322222, "si", "nombrecool@gmail.com", "no");
        ora.add_video( "nombre guay", "csificaicon guay ","gnero guay" , "resumen guar", 1);
        
      //  System.out.println("eliminar");
        ora.del_cliente(222222);
        ora.del_video(99999);
        
     //   System.out.println("alquilar ");
        ora.add_alquiler( null,null, 1, 20911444);  
        ArrayList<AlquilerBean> lista = new ArrayList<AlquilerBean>();
        
        lista=ora.get_alquiler(20911444);
    //    System.out.println(lista.get(0).getClientes_id_cliente());
        
        ora.devolver_video(1, 20911444);
        
        lista=ora.get_alquiler(20911444);
      //  System.out.println(lista.isEmpty());
        */
        UCVBuster b = UCVBuster.Instance();
        b.seleccionarOpcion(1);
        

    }

}
