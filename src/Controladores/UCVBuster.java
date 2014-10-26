package Controladores;

import Interfaces.IAcceso;
import Interfaces.IAdmin;
import Interfaces.IEmpleado;
import Interfaces.IregAlquiler;
import java.io.IOException;


public class UCVBuster {
    
    private static UCVBuster uniqueInstance;
    private IAcceso acceso;
    private IAdmin rolAdmin;
    private IEmpleado rolEmpleado;
    private IregAlquiler rolRegAlquiler;

    private UCVBuster (){
        uniqueInstance = this;//
    }
    
    public static UCVBuster Instance() {
        if(uniqueInstance==null){
            uniqueInstance = new UCVBuster();
        }
        return uniqueInstance;
    }     
     
    public void desplegar_Interfaces(int opc) throws IOException{
        
        switch(opc){
            case 0:
                System.exit(0);
                break;
                
            case 1:
                acceso = new IAcceso();
                acceso.setLocationRelativeTo(null);
                acceso.setVisible(true);
                acceso.setResizable(false);
                break;
                
            case 2: // Mostrar Panel Administrador
                if(rolAdmin == null)
                    rolAdmin = new IAdmin();
                rolAdmin.setLocationRelativeTo(null);
                rolAdmin.setVisible(true);
                rolAdmin.setResizable(false);
                break;
                
            case 3: // Mostrar Panel Empleado
                if(rolEmpleado == null)
                    rolEmpleado = new IEmpleado();
                rolEmpleado.setLocationRelativeTo(null);
                rolEmpleado.setVisible(true);
                rolEmpleado.setResizable(false);
                break;
                
            case 4: // Mostrar Registrar Alquileres
                rolRegAlquiler = new IregAlquiler();
                rolRegAlquiler.setLocationRelativeTo(null);
                rolRegAlquiler.setVisible(true);
                rolRegAlquiler.setResizable(false);
                rolEmpleado.setEnabled(false);
        }        
         
     }
    
    public void corroborar_clave(String text, int usuario) throws IOException {
       if (usuario == 0 && text.contentEquals("admin")){
           acceso.setVisible(false);
           desplegar_Interfaces(2);
       }else{
           if (usuario == 1 && text.contentEquals("empleado")){
                acceso.setVisible(false);
                desplegar_Interfaces(3);
           }else{
                System.out.println("caiste!!! ");
           }//fin else
       }//fin else
    }//fin corroborar_clave
       
    
}
