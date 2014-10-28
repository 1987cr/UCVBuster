
package Controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Client {

    public static void main(String[] args) throws IOException{
        UCVBuster b = UCVBuster.Instance();
        b.seleccionarOpcion(1);    
    }

}
