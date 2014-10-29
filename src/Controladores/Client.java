
package Controladores;

import Capa_Persistencia.Oracle;
import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{
        UCVBuster b = UCVBuster.Instance();
        b.seleccionarOpcion(1);
        
        Oracle or = new Oracle();
        
        or.add_cliente(284, "3","df", 234, 432, "si", "e@ed", "si");
    }

}
