package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import Modelo.Correo;

public class CarteleraTimer {
    TimerTask timerTask;
    
    public CarteleraTimer(){
        
        timerTask = new TimerTask() {

            @Override
            public void run() {
                java.util.Date date = new java.util.Date();
                DateFormat df = new SimpleDateFormat("EEE h");
                df.setTimeZone(TimeZone.getDefault()); 
                String delim = "[ ]+";
                String[] tokens = df.format(date).split(delim);                
                
                if(tokens[0].equals("lun") && tokens[1].equals("10")){
                    Correo correo = new Correo();
                    
                    String destinatarios = "";
                    // Obtener destinatarios. Ej: "correo1, correo2, correo3, correo4"
                    
                    String cartelera = "";
                    // Obtener cartelera
                    
                    correo.sendMail(destinatarios, cartelera);
                }
            }
        };

        Timer timer = new Timer("CarteleraTimer");

        // Revisa cada 60 min
        timer.scheduleAtFixedRate(timerTask, 0, 1000*60*60);
    }  
}
