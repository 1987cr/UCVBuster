
package Controladores;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{
        
        UCVBuster b = UCVBuster.Instance();
        b.seleccionarOpcion(1);
        
    }

}
