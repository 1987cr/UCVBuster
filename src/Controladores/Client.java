
package Controladores;

import Capa_Persistencia.Oracle;
import DTO.ClienteBean;
import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{
        
        
        Oracle ora= new Oracle();
        ClienteBean perso = new ClienteBean();
        perso = ora.get_cliente(20911444);
        System.out.println(perso.getNombre());
        
        ora.add_alquiler(1, null,null, 1, 20911444);
        
        UCVBuster b = UCVBuster.Instance();
        b.seleccionarOpcion(1);
        
        
        
    }

}
