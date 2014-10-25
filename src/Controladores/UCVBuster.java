package Controladores;

import Interfaces.IAcceso;
import Interfaces.IAdmin;
import Interfaces.IEmpleado;
import java.io.IOException;


public class UCVBuster {
    
    private static UCVBuster uniqueInstance;
    private IAcceso acceso;
    private IAdmin admin;
    private IEmpleado emp;

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
                break;
                
            case 2:
                admin = new IAdmin();
                admin.setLocationRelativeTo(null);
                admin.setVisible(true);
                break;
                
            case 3:
                emp = new IEmpleado();
                emp.setLocationRelativeTo(null);
                emp.setVisible(true);
                break;
        }        
         
     }
    
    public void corroborar_clave(String text) throws IOException {
       if (text.contentEquals("admin")){
           acceso.setVisible(false);
           desplegar_Interfaces(2);
       }else{
           if (text.contentEquals("empleado")){
                acceso.setVisible(false);
                desplegar_Interfaces(3);
           }else{
                System.out.println("caiste!!! ");
           }//fin else
       }//fin else
    }//fin corroborar_clave
       
    
}
