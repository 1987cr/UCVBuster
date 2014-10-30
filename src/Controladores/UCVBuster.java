package Controladores;

import Capa_Persistencia.Oracle;
import DTO.ClienteBean;
import DTO.VideoBean;
import Decorator.BordeNegro;
import Decorator.BordeRojo;
import Decorator.ConcretePersonalizarVideo;
import Decorator.LetrasGrandes;
import Decorator.LetrasPequenas;
import Decorator.MarcoBurbujas;
import Decorator.MarcoGrama;
import Interfaces.IAcceso;
import Interfaces.IAdmin;
import Interfaces.IConsultar_alquileres;
import Interfaces.IEmpleado;
import Interfaces.IRegAlquiler;
import Interfaces.IRegCliente;
import Interfaces.IRegDevolucion;
import Interfaces.IRegVideo;
import Interfaces.ISelFoto;
import Interfaces.ISelOpciones;
import Interfaces.IDelVideo;
import Interfaces.IModCliente;
import Modelo.CarteleraTimer;
import Modelo.ListaAtrasadosTimer;
import Modelo.ProcesarVideo;
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
    private ISelFoto rolSelFoto;
    private ISelOpciones rolSelOpciones;
    private IRegVideo rolRegVideo;
    private IDelVideo rolDelVideo;
    private IModCliente rolModCliente;
    
    private ConcretePersonalizarVideo base; 
    private ListaAtrasadosTimer laTimer;
    private CarteleraTimer cTimer;
    private LetrasGrandes lGra;
    private LetrasPequenas lPeq;
    private MarcoGrama mGra;
    private MarcoBurbujas mBub;
    private BordeRojo bRed;
    private BordeNegro bNeg;
    private ProcesarVideo procVid;

    private Oracle db;
    
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
                //laTimer = new ListaAtrasadosTimer();
                //cTimer = new CarteleraTimer();
                db = new Oracle();
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
                
                db.add_alquiler(null, null,Integer.parseInt(rolRegAlquiler.getId()), Integer.parseInt(rolRegAlquiler.getCedula()) );
                rolRegAlquiler.setTitulo("");
                rolRegAlquiler.setNombre("");
                break;
                
            case 7: // Buscar Cédula: Registrar Alquileres
                
                ClienteBean cli = db.get_cliente(Integer.parseInt(rolRegAlquiler.getCedula()));
                rolRegAlquiler.setNombre(cli.getNombre());
                break;
                
            case 8: // Buscar ID: Registrar Alquileres
                
                VideoBean vid = db.get_video(Integer.parseInt(rolRegAlquiler.getId()));
                rolRegAlquiler.setTitulo(vid.getNombre());
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
                rolRegAlquiler.setEnabled(false);
                break;
                
            case 18: // Cancelar: Registrar Cliente
                rolRegCliente.setVisible(false);
                rolRegAlquiler.setEnabled(true);
                rolRegAlquiler.setVisible(true);
                break;
                
            case 19: // Aceptar: Registrar Cliente

                db.add_cliente(Integer.parseInt(rolRegCliente.getCedula()), 
                               rolRegCliente.getName(), 
                               rolRegCliente.getDireccion(), 
                               Integer.parseInt(rolRegCliente.getSalario()), 
                               (rolRegCliente.getTelefono()),
                               rolRegCliente.getPotencial(), 
                               rolRegCliente.getCorreo(), 
                               rolRegCliente.getSuscribirse()
                );
                rolRegCliente.setVisible(false);
                rolRegAlquiler.setEnabled(true);
                rolRegAlquiler.setVisible(true);
                break;
            
            case 20: // Salir: Empleado, Administrador
                if(rolAdmin != null)
                    rolAdmin.setVisible(false);
                
                if(rolEmpleado != null)
                    rolEmpleado.setVisible(false);
                
                acceso.setLocationRelativeTo(null);
                acceso.setVisible(true);
                acceso.setResizable(false);
                break;
                
            case 21: // Personalizar Video
                rolSelFoto = new ISelFoto();
                rolSelFoto.setLocationRelativeTo(null);
                rolSelFoto.setVisible(true);
                rolSelFoto.setResizable(false);
                rolEmpleado.setEnabled(false);
                break;
                
            case 22: // Siguiente: Seleccionar Foto
                rolSelOpciones = new ISelOpciones();
                rolSelOpciones.setLocationRelativeTo(null);
                rolSelOpciones.setVisible(true);
                rolSelOpciones.setResizable(false);
                rolSelFoto.setVisible(false);
                
                base = new ConcretePersonalizarVideo(rolSelFoto.getFoto(), rolSelFoto.getDestino(), rolSelFoto.getCedula());

                break;
                
            case 23: // Finalizar: Seleccionar Opciones
                                
                if(rolSelOpciones.getLetra() == 1)
                   lPeq = new LetrasPequenas(base);
                    
                if(rolSelOpciones.getLetra() == 2)
                   lGra = new LetrasGrandes(base);
                
                if(rolSelOpciones.getMarco() == 1)
                   mGra = new MarcoGrama(base);
                    
                if(rolSelOpciones.getMarco() == 2)
                   mBub = new MarcoBurbujas(base);
                
                if(rolSelOpciones.getBorde() == 1)
                   bRed = new BordeRojo(base);
                    
                if(rolSelOpciones.getBorde() == 2)
                   bNeg = new BordeNegro(base);
                
                procVid = new ProcesarVideo(rolSelFoto.getDestino(), rolSelFoto.getCedula());
                
                rolSelOpciones.setVisible(false);
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 24: // Cancelar: Seleccionar Foto
                rolSelFoto.setVisible(false);
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 25: // Registrar Video
                rolRegVideo = new IRegVideo();
                rolRegVideo.setLocationRelativeTo(null);
                rolRegVideo.setVisible(true);
                rolRegVideo.setResizable(false);
                rolAdmin.setEnabled(false);
                break;
                
            case 26: // Cancelar: Registrar Video
                rolRegVideo.setVisible(false);
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 27: // Aceptar: Registrar Video
                db.add_video(Integer.parseInt(rolRegVideo.getId()),rolRegVideo.getNombre(),
                        rolRegVideo.getClasificacion(),rolRegVideo.getGenero(),
                        rolRegVideo.getResumen(),1);
                rolRegVideo.setVisible(false);
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 28: // Eliminar Video
                rolDelVideo = new IDelVideo();
                rolDelVideo.setLocationRelativeTo(null);
                rolDelVideo.setVisible(true);
                rolDelVideo.setResizable(false);
                rolAdmin.setEnabled(false);
                break;
                
            case 29: // Cancelar: Eliminar Video
                rolDelVideo.setVisible(false);
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 30: // Eliminar: Eliminar Video
                
                break;
                
            case 31: // Modificar Cliente
                rolModCliente = new IModCliente();
                rolModCliente.setLocationRelativeTo(null);
                rolModCliente.setVisible(true);
                rolModCliente.setResizable(false);
                rolAdmin.setEnabled(false);
                break;
                
            case 32: // Cancelar: Modificar Cliente
                rolModCliente.setVisible(false);
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 33: // Modificar: Modificar Cliente
                
                break;
            
            case 34: // Buscar: Modificar Cliente
                // se busca la ci en la bd y luego se coloca en gris
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

/*
    public void add_video(String id, String nombre, String clasificacion, String genero, String resumen) {
         db.add_video(nombre,clasificacion,genero,resumen,1);
    }

    public int obtenerSeqVideo() {
       return db.obtenerSeqVideo();
         
    }
*/
    
    
}
