package Controladores;

import Capa_Persistencia.Oracle;
import DTO.AlquilerBean;
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
import Interfaces.IConsultarCatalogo;
import Interfaces.IConsultar_alquileres;
import Interfaces.IDelCliente;
import Interfaces.IDelVideo;
import Interfaces.IDescripcionVideo;
import Interfaces.IDisponibilidad;
import Interfaces.IEmpleado;
import Interfaces.IListaAtrasados;
import Interfaces.IModCliente;
import Interfaces.IRegAlquiler;
import Interfaces.IRegCliente;
import Interfaces.IRegDevolucion;
import Interfaces.IRegVideo;
import Interfaces.ISelFoto;
import Interfaces.ISelOpciones;
import Modelo.CarteleraTimer;
import Modelo.ListaAtrasadosTimer;
import Modelo.ProcesarVideo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;


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
    private IConsultarCatalogo rolConsultarCatalogo;
    private IDelCliente rolDelCliente;
    private IDescripcionVideo rolDescVideo;
    private IDisponibilidad rolDisponible;
    private IListaAtrasados rolAtrasados;
    
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
    
    private ArrayList listaPeliculas;
    
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
                // Timer Lista de Atrasados
                laTimer = new ListaAtrasadosTimer();
                // Timer Correo Cartelera
                cTimer = new CarteleraTimer();
                // init BD
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
                rolRegAlquiler.dispose();
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
                rolRegDevolucion.dispose();
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
                rolConsAlq.dispose();
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 16: // Buscar Cédula: Consultar Alquileres
                
                if(!rolConsAlq.getCedula().equals("")){
                    ArrayList alquilerList = db.get_alquiler(Integer.parseInt(rolConsAlq.getCedula()));
                
                    Iterator j = alquilerList.iterator();

                    AlquilerBean ab;

                    while(j.hasNext()){
                        ab = (AlquilerBean) j.next();

                        rolConsAlq.setRow(Integer.toString(ab.getVideo_id_video()));
                    }
                }                
                        
                break;
                
            case 17: // Registrar Cliente
                rolRegCliente = new IRegCliente();
                rolRegCliente.setLocationRelativeTo(null);
                rolRegCliente.setVisible(true);
                rolRegCliente.setResizable(false);
                rolRegAlquiler.setEnabled(false);
                break;
                
            case 18: // Cancelar: Registrar Cliente
                rolRegCliente.dispose();
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
            
            case 20: // Salir: Empleado, Administrador, Cliente
                if(rolAdmin != null)
                    rolAdmin.setVisible(false);
                
                if(rolEmpleado != null)
                    rolEmpleado.setVisible(false);
                
                if(rolConsultarCatalogo != null)
                    rolConsultarCatalogo.setVisible(false);
                
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
                
                base = new ConcretePersonalizarVideo(rolSelFoto.getFoto(), rolSelFoto.getDestino(), rolSelFoto.getCedula());
                
                rolSelFoto.dispose();
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
                
                rolSelOpciones.dispose();
                rolEmpleado.setEnabled(true);
                rolEmpleado.setVisible(true);
                break;
                
            case 24: // Cancelar: Seleccionar Foto
                rolSelFoto.dispose();
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
                rolRegVideo.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 27: // Aceptar: Registrar Video
                db.add_video(Integer.parseInt(rolRegVideo.getId()),rolRegVideo.getNombre(),
                        rolRegVideo.getClasificacion(),rolRegVideo.getGenero(),
                        rolRegVideo.getResumen(),1);
                rolRegVideo.dispose();
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
                rolDelVideo.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 30: // Eliminar: Eliminar Video
                db.del_video(Integer.parseInt(rolDelVideo.getId()));
                rolDelVideo.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);                
                break;
                
            case 31: // Modificar Cliente
                rolModCliente = new IModCliente();
                rolModCliente.setLocationRelativeTo(null);
                rolModCliente.setVisible(true);
                rolModCliente.setResizable(false);
                rolAdmin.setEnabled(false);
                break;
                
            case 32: // Cancelar: Modificar Cliente
                rolModCliente.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 33: // Modificar: Modificar Cliente
                db.upd_cliente(Integer.parseInt(rolModCliente.getCedula()), 
                               rolModCliente.getNombre(), 
                               rolModCliente.getDireccion(), 
                               Integer.parseInt(rolModCliente.getSalario()), 
                               rolModCliente.getTelefono(), 
                               rolModCliente.getPotencial(), 
                               rolModCliente.getCorreo(), 
                               rolModCliente.getSuscribirse());
                
                rolModCliente.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
            
            case 34: // Buscar: Modificar Cliente
                if(!rolModCliente.getCedula().equals("")){
                    ClienteBean c = db.get_cliente(Integer.parseInt(rolModCliente.getCedula()));
                
                    if(c.getId_cliente() == 0){
                        JOptionPane.showMessageDialog(null, "Cliente no existe.","Información", JOptionPane.INFORMATION_MESSAGE); 
                    }else{
                        rolModCliente.disableCedula();
                        rolModCliente.setNombre(c.getNombre());
                        rolModCliente.setCorreo(c.getEmail());
                        rolModCliente.setDireccion(c.getDireccion());
                        rolModCliente.setTelefono(c.getTelefono());
                        rolModCliente.setSalario(Integer.toString(c.getSalario_mensual()));
                        rolModCliente.setPotencial(c.getPotencial());
                        rolModCliente.setSuscribir(c.getSuscripto());
                    }         
                }                     
                
                break;
                
            case 35: // Consultar Catalogo
                rolConsultarCatalogo = new IConsultarCatalogo();
                rolConsultarCatalogo.setLocationRelativeTo(null);
                rolConsultarCatalogo.setVisible(true);
                rolConsultarCatalogo.setResizable(false);
                acceso.setVisible(false);
                                                               
                listaPeliculas = db.get_catalago();
                Vector catalogo = new Vector<>();
                VideoBean pelicula = null;
                
                Iterator i = listaPeliculas.listIterator();
                
                
                while(i.hasNext()){
                    pelicula = (VideoBean) i.next();
                    catalogo.add(pelicula.getNombre()); 
                }
               
                rolConsultarCatalogo.addPelicula(catalogo);
                
                break;
                
            case 36: // Ver Detalles: Consultar Catalogo
                rolDescVideo = new IDescripcionVideo();
                rolDescVideo.setLocationRelativeTo(null);
                rolDescVideo.setVisible(true);
                rolDescVideo.setResizable(false);
                rolConsultarCatalogo.setEnabled(false);
                
                pelicula = null;
                
                i = listaPeliculas.listIterator();
                
                while(i.hasNext()){
                    pelicula = (VideoBean) i.next();
                    if(pelicula.getNombre().equals(rolConsultarCatalogo.getPelicula()))
                        break; 
                }
                
                rolDescVideo.setTitulo(pelicula.getNombre());
                rolDescVideo.setClas(pelicula.getClasificacion());
                rolDescVideo.setGenero(pelicula.getGenero());
                rolDescVideo.setResumen(pelicula.getResumen());
                
                break;
            
            case 37: // Consultar Disponibilidad: Consultar Catalogo
                rolDisponible = new IDisponibilidad();
                rolDisponible.setLocationRelativeTo(null);
                rolDisponible.setVisible(true);
                rolDisponible.setResizable(false);
                rolConsultarCatalogo.setEnabled(false);
                
                pelicula = null;
                
                i = listaPeliculas.listIterator();
                
                while(i.hasNext()){
                    pelicula = (VideoBean) i.next();
                    if(pelicula.getNombre().equals(rolConsultarCatalogo.getPelicula()))
                        break; 
                }
                
                rolDisponible.setDisponibilidad(Integer.toString(pelicula.getCantidad_existencias()));
                break;
                
            case 38: // Eliminar Cliente
                rolDelCliente = new IDelCliente();
                rolDelCliente.setLocationRelativeTo(null);
                rolDelCliente.setVisible(true);
                rolDelCliente.setResizable(false);
                rolAdmin.setEnabled(false);
                break;
                
            case 39: // Cancelar: Eliminar Cliente
                rolDelCliente.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
                
            case 40: // Eliminar: Eliminar Cliente
                db.del_cliente(Integer.parseInt(rolDelCliente.getCedula()));
                
                rolDelCliente.dispose();
                rolAdmin.setEnabled(true);
                rolAdmin.setVisible(true);
                break;
            
            case 41: // Buscar: Eliminar Cliente
                if(!rolDelCliente.getCedula().equals("")){
                    ClienteBean cl = db.get_cliente(Integer.parseInt(rolDelCliente.getCedula()));
                
                    if(cl.getId_cliente() == 0){
                        JOptionPane.showMessageDialog(null, "Cliente no existe.","Información", JOptionPane.INFORMATION_MESSAGE); 
                    }else{
                        rolDelCliente.enableEliminar();
                        rolDelCliente.setNombre(cl.getNombre());
                        rolDelCliente.setCorreo(cl.getEmail());
                        rolDelCliente.setDireccion(cl.getDireccion());
                        rolDelCliente.setTelefono(cl.getTelefono());
                        rolDelCliente.setSalario(Integer.toString(cl.getSalario_mensual()));
                        rolDelCliente.setPotencial(cl.getPotencial());
                        rolDelCliente.setSuscribir(cl.getSuscripto());
                    }
                }
                
                break;
                
            case 42: // Descripción Película: Aceptar
                rolDescVideo.dispose();
                rolConsultarCatalogo.setEnabled(true);
                rolConsultarCatalogo.setVisible(true);
                break;
                
            case 43: // Disponibilidad: Aceptar
                rolDisponible.dispose();
                rolConsultarCatalogo.setEnabled(true);
                rolConsultarCatalogo.setVisible(true);
                break;
                
            case 44: // Lista de Atrasados
                rolAtrasados = new IListaAtrasados();
                rolAtrasados.setVisible(true);
                rolAtrasados.setLocationRelativeTo(null);
                rolAtrasados.setResizable(false);
                
                ArrayList atrasados = db.get_atrasados();
                
                Iterator it = atrasados.iterator();
                
                ClienteBean aux;
                
                while(it.hasNext()){
                    aux = (ClienteBean) it.next();
                    rolAtrasados.setRow(Integer.toString(aux.getId_cliente()), 
                                        aux.getEmail(), 
                                        aux.getEmail(),
                                        aux.getTelefono());
                }
                
                break;
                
            case 45: //Lista de Atrasados: Aceptar
                rolAtrasados.dispose();
                break;
                
            case 46: // Eliminar video: Buscar
                if(!rolDelVideo.getId().equals("")){
                    VideoBean auxVideo = db.get_video(Integer.parseInt(rolDelVideo.getId()));

                    if(auxVideo.getId_video() == 0){
                        JOptionPane.showMessageDialog(null, "Video no existe.","Información", JOptionPane.INFORMATION_MESSAGE); 
                    }else{
                        rolDelVideo.enableEliminar();
                        rolDelVideo.setNombre(auxVideo.getNombre());
                        rolDelVideo.setClasificacion(auxVideo.getClasificacion());
                        rolDelVideo.setGenero(auxVideo.getGenero());
                        rolDelVideo.setResumen(auxVideo.getResumen());
                    }
                }             
               
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
                acceso.setVisible(false);
                seleccionarOpcion(35);
           }
       }
    }

}
