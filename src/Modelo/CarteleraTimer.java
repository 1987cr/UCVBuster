package Modelo;

import Capa_Persistencia.Oracle;
import DTO.VideoBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import Modelo.Correo;
import java.util.ArrayList;
import java.util.Iterator;

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
                    
                    String destinatarios = getListaCorreos();
                    
                    String cartelera = getCartelera();
                    
                    correo.sendMail(destinatarios, cartelera);
                }
            }
        };

        Timer timer = new Timer("CarteleraTimer");

        // Revisa cada 60 min
        timer.scheduleAtFixedRate(timerTask, 0, 1000*60*60);
    }  
    
        
    public String getListaCorreos(){
        String lista = "";
        Oracle db= new Oracle();
 
        ArrayList lc = new ArrayList<>();
        lc = db.get_suscritos();
        Iterator i = lc.listIterator();

        while(i.hasNext()){
             lista +=  (String) i.next();
             if(i.hasNext())
                 lista += ", ";
        }

        return lista;
    }
    
    
    public String getCartelera(){

        String cartelera = "Cartelera: \n\n";
        Oracle db= new Oracle();
        
        ArrayList lista = new ArrayList<VideoBean>();
        lista = db.get_catalago();
        Iterator i = lista.listIterator();
        
        VideoBean vid = new VideoBean();
        
        while(i.hasNext()){
            vid = (VideoBean) i.next();
            cartelera += "- " + vid.getNombre() + "\n";
        }
       
        return cartelera;
    }
    
    public void test(){
        Correo correo = new Correo();
                    
        String destinatarios = getListaCorreos();

        String cartelera = getCartelera();

        correo.sendMail(destinatarios, cartelera);
    }
}
