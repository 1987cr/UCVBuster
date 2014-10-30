
package Controladores;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{
<<<<<<< HEAD

=======
        
        
        Oracle ora= new Oracle();
      /*  ClienteBean perso = new ClienteBean();
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
        
        ora.add_cliente(8888888, null, null, 222, "04141422", "si", null, "i");
        ClienteBean cli = ora.get_cliente(8888888);
        System.o
       ut.println(cli.getTelefono());
        */
        ArrayList lista = new ArrayList<VideoBean>();
       lista= ora.get_catalago();
       Iterator i = lista.listIterator();
       VideoBean vid= new VideoBean();
       if(!lista.isEmpty()){
        vid = (VideoBean) i.next();
         System.out.println(vid.getNombre()); 
         vid = (VideoBean) i.next();
          System.out.println(vid.getNombre()); 
          vid = (VideoBean) i.next();
          System.out.println(vid.getNombre()); 

       }
       /*
         ArrayList lista = new ArrayList<String>();
       lista= ora.get_suscritos();
       Iterator i = lista.listIterator();
       String vid= "";
       if(!lista.isEmpty()){
        vid =  (String) i.next();
         System.out.println(vid); 
         vid =  (String) i.next();
          System.out.println(vid); 
          vid =  (String) i.next();
          System.out.println(vid); 
       }
       */

       
       /*ArrayList lista = new ArrayList<String>();
       lista = ora.get_suscritos();
       Iterator i = lista.listIterator();
       String vid= "";
       while(i.hasNext()){
            vid =  (String) i.next();
             System.out.println(vid); 
       }*/
        CarteleraTimer c = new CarteleraTimer();
        c.test();
       

       ora.upd_cliente(20911444, "rey patrones", "dir", 2700, "3152626", "si", "lwjirl", "si");
>>>>>>> e52c9971e2b72564a9ca49925fc28db44ac92916
        UCVBuster b = UCVBuster.Instance();
        b.seleccionarOpcion(1);
        
    }

}
