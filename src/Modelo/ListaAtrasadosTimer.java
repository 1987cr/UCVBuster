package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class ListaAtrasadosTimer {
    
    TimerTask timerTask;

    public ListaAtrasadosTimer(){
        timerTask = new TimerTask() {

            @Override
            public void run() {
                java.util.Date date = new java.util.Date();
                DateFormat df = new SimpleDateFormat("h m");
                df.setTimeZone(TimeZone.getDefault()); 
                String delim = "[ ]+";
                String[] tokens = df.format(date).split(delim);                
                
                int hour = Integer.parseInt(tokens[0]);
                int min = Integer.parseInt(tokens[1]);
                
                if(hour == 9 && min < 5){
                    // Código lista atrasados
                }
            }
        };

        Timer timer = new Timer("AtrasadosTimer");

        // Revisa cada 5 min
        timer.scheduleAtFixedRate(timerTask, 0, 1000*60*5);
    }        
}
