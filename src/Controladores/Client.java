
package Controladores;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{
        UCVBuster b = UCVBuster.Instance();
        b.desplegar_Interfaces(1);        
    }

}
