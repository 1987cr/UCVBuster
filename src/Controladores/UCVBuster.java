package Controladores;

import Interfaces.IAcceso;
import Interfaces.IAdmin;
import Interfaces.IEmpleado;
import Interfaces.IRegDevolucion;
import Interfaces.IRegAlquiler;
import Interfaces.IConsultar_alquileres;
import Interfaces.IRegCliente;
import Modelo.ListaAtrasadosTimer;
import Modelo.CarteleraTimer;
import java.io.IOException;


public class UCVBuster {
    
    private static UCVBuster uniqueInstance;
    private IAcceso acceso;
    private IAdmin rolAdmin;
    private IEmpleado rolEmpleado;
    private IRegAlquiler rolRegAlquiler;
    private IRegDevolucion rolRegDevolucion;
    private IConsultar_alquileres rolConsAlq;
    private IRegCliente rolRegCliente;
    private ListaAtrasadosTimer laTimer;
    private CarteleraTimer cTimer;

    private UCVBuster (){
        uniqueInstance = this;//
    }
    
    public static UCVBuster Instance() {
        if(uniqueInstance==null){
            uniqueInstance = new UCVBuster();
        }
        return uniqueInstance;
    }                                                                 
     
    public void seleccionarOpcion(int opc) throws IOException{
        
        switch(opc){
            case 0:
                System.exit(0);
                break;
                
            case 1:
                laTimer = new ListaAtrasadosTimer();
                cTimer = new CarteleraTimer();
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
                
            case 4: // Registrar Alquileres
                rolRegAlquiler = new IRegAlquiler();
                rolRegAlquiler.setLocationRelativeTo(null);
                rolRegAlquiler.setVisible(true);
                rolRegAlquiler.setResizable(false);
                rolEmpleado.setEnabled(false);
                break;
                
            case 5: // Cancelar: Registrar Alquileres
                rolRegAlquiler.setVisible(false);
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 6: // Aceptar: Registrar Alquileres
                
                break;
                
            case 7: // Buscar Cédula: Registrar Alquileres
                
                break;
                
            case 8: // Buscar ID: Registrar Alquileres
                
                break;
                
            case 9: // Registrar Devolución
                rolRegDevolucion = new IRegDevolucion();
                rolRegDevolucion.setLocationRelativeTo(null);
                rolRegDevolucion.setVisible(true);
                rolRegDevolucion.setResizable(false);
                rolEmpleado.setEnabled(false);
                break;
                
            case 10: // Cancelar: Registrar Devolución
                rolRegDevolucion.setVisible(false);
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 11: // Aceptar: Registrar Devolución
                
                break;
                
            case 12: // Buscar Cédula: Registrar Devolución
                
                break;
                
            case 13: // Buscar ID: Registrar Devolución
                
                break;
                
            case 14: // Consultar Alquileres
                rolConsAlq = new IConsultar_alquileres();
                rolConsAlq.setLocationRelativeTo(null);
                rolConsAlq.setVisible(true);
                rolConsAlq.setResizable(false);
                rolEmpleado.setEnabled(false);
                break;
                
            case 15: // Regresar: Consultar Alquileres
                rolConsAlq.setVisible(false);
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 16: // Buscar Cédula: Consultar Alquileres
                
                break;
                
            case 17: // Registrar Cliente
                rolRegCliente = new IRegCliente();
                rolRegCliente.setLocationRelativeTo(null);
                rolRegCliente.setVisible(true);
                rolRegCliente.setResizable(false);
                rolEmpleado.setEnabled(false);
                break;
                
            case 18: // Cancelar: Registrar Cliente
                rolRegCliente.setVisible(false);
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 19: // Aceptar: Registrar Cliente
                // cuando se verifica si el cliente ya está registrado?
                break;
                
        }        
         
     }
    
    public void corroborar_clave(String text, int usuario) throws IOException {
       if (usuario == 0){
           acceso.setVisible(false);
           seleccionarOpcion(2);
       }else{
           if (usuario == 1){
                acceso.setVisible(false);
                seleccionarOpcion(3);
           }else{
                System.out.println("caiste!!! ");
           }//fin else
       }//fin else
    }//fin corroborar_clave
       
    
}
