package Capa_Persistencia;

import DTO.AlquilerBean;
import DTO.ClienteBean;
import DTO.DTO;
import DTO.VideoBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Israel
 */
public class Oracle implements DAO {

    @Override
    public Connection conectar() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "PATRONES", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Empezo54 ");
            System.out.println(e);
        }
        return con;
    }

    public int obtenerSeqVideo() {
        int numero_seq = 0;
// System.out.println(numero_seq);
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
// String query = "SELECT video_seq.CURRVAL FROM DUAL";
            String query = " select last_number from user_sequences where sequence_name = 'VIDEO_SEQ'";
//stmt.executeQuery("exec del_cliente("+cedula+")");
            ResultSet rset = stmt.executeQuery(query);
            rset.next();
            numero_seq = rset.getInt(1);
//System.out.println(rset.getInt(1));
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("error en obtenerSeqVideo ");
            System.out.println(e);
        }
        return numero_seq;
    }

    @Override
    public void del_cliente(int cedula) {
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "delete from clientes where id_cliente =" + cedula;
//stmt.executeQuery("exec del_cliente("+cedula+")");
            stmt.executeQuery(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en del_cliente ");
            System.out.println(e);
        }
    }

    @Override
    public void del_video(int id_video) {
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "delete from video where id_video =" + id_video;
// stmt.executeQuery("exec del_video("+id_video+")");
            stmt.executeQuery(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en del_video ");
            System.out.println(e);
        }
    }

    @Override
    public void add_cliente(int cedula, String nombre, String direccion, int salario_mensual, String telefono, String protencial, String email, String suscripto) {
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO clientes (id_cliente,nombre,direccion,salario_mensual,telefono,potencial,email,suscrito) VALUES("
                    + cedula + ",'" + nombre + "','" + direccion + "'," + salario_mensual + ","
                    + telefono + ",'" + protencial + "','" + email + "','" + suscripto + "')";
// System.out.println(query);
            stmt.executeQuery(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("error en add_cliente ");
            System.out.println(e);
        }
    }

    @Override
    public void add_video(int id_video, String nombre, String clasificacion, String genero, String resumen, int locales_id_local) {
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO video ( id_video, nombre, clasificacion, genero, resumen, locales_id_local) VALUES("
                    + id_video + " ,'" + nombre + "','" + clasificacion + "','" + genero + "','" + resumen + "'," + locales_id_local + ")";
            /* stmt.executeQuery("exec add_video("+ id_video +","+ nombre +","+ clasificacion +","+ genero +","+
             resumen +","+ locales_id_local +")");*/
            System.out.println(query);
            stmt.executeQuery(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en add_video");
            System.out.println(e);
        }
    }

    @Override
    public void add_alquiler(String fecha_alquiler, String fecha_planeada_entrega, int video_id_video, int clientes_id_cliente) {
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO alquileres ( id_alquiler, fecha_alquiler, fecha_planeada_entrega, video_id_video, clientes_id_cliente) VALUES("
                    + " alquileres_seq.NEXTVAL ,'" + fecha_alquiler + "','" + fecha_planeada_entrega + "'," + video_id_video + "," + clientes_id_cliente + ")";
            /* String query = "exec add_alquiler("+ id_alquiler +",'29-oct-2014','31-oct-2014',"+ video_id_video +","+
             clientes_id_cliente +")";*/
            System.out.println(query);
            stmt.executeQuery(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en add_alquiler ");
            System.out.println(e);
        }
    }

    public void upd_cliente(int cedula, String nombre, String direccion, int salario_mensual, String telefono, String protencial, String email, String suscripto) {
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "UPDATE clientes SET nombre = '" + nombre + "',direccion = '" + direccion + "',salario_mensual = " + salario_mensual
                    + ",telefono = '" + telefono + "',potencial = '" + protencial + "',email = '" + email + "',suscrito = '" + suscripto + "' WHERE id_cliente=" + cedula;
//System.out.println(query);
            stmt.executeQuery(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("error en upd_cliente ");
            System.out.println(e);
        }
    }

    @Override
    public void devolver_video(int video, int cliente) {
        try (Connection con = conectar();) {
            /*Obtener valores */
            System.out.println("fase0");
            Statement stmt = con.createStatement();
            String query = "select id_alquiler,fecha_alquiler,fecha_planeada_entrega from alquileres "
                    + "where video_id_video= " + video + " and clientes_id_cliente = " + cliente;
            ResultSet rset = stmt.executeQuery(query);
// System.out.println(query);
            while (rset.next()) {
                System.out.println(rset.getInt(1));
                System.out.println(rset.getString(2));
                System.out.println(rset.getString(3));
                int id_alquiler = rset.getInt(1);
                String fecha_alquiler = rset.getString(2);
                String fecha_planeada_entrega = rset.getString(3);
                System.out.println("fase1");
                /* registar en Historico */
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                Calendar c1 = Calendar.getInstance();
                c1.setTime(new Date()); // Now use today date.
                String ini = sdf.format(c1.getTime());
                String date_s = fecha_alquiler;
                SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
                Date date = dt.parse(date_s);
                SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMM-yyyy");
                fecha_alquiler = dt1.format(date);
                String date_s2 = fecha_planeada_entrega;
                SimpleDateFormat dt2 = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
                Date date2 = dt2.parse(date_s2);
                SimpleDateFormat dt3 = new SimpleDateFormat("dd-MMM-yyyy");
                fecha_planeada_entrega = dt3.format(date2);
                query = "insert into historico_alquileres"
                        + "(id_alquiler, fecha_alquiler, fecha_planeada_devolucion, fecha_real_devolucion, video_id, cliente_id )"
                        + "values"
                        + "(" + id_alquiler + ",'" + fecha_alquiler + "','" + fecha_planeada_entrega + "','" + ini + "'," + video + "," + cliente + ")";
                System.out.println(query);
                stmt.executeQuery(query);
                System.out.println("fase2");
                /*Eliminar de alquiler */
                query = "delete from alquileres where id_alquiler =" + id_alquiler;
                System.out.println(query);
                stmt.executeQuery(query);
                System.out.println("fase3");
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en devolver_video ");
            System.out.println(e);
        } catch (ParseException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ClienteBean> get_atrasados() {
        ArrayList lista = new ArrayList<ClienteBean>();
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "select * from atrasos";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                ClienteBean cli = new ClienteBean();
                cli.setId_cliente(rset.getInt(1));
                cli.setNombre(rset.getString(2));
                cli.setEmail(rset.getString(3));
                cli.setTelefono(rset.getString(4));
                lista.add(cli);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(" error en get_catalago ");
            System.out.println(e);
        }
        return lista;
    }

    public ArrayList<String> get_suscritos() {
        ArrayList lista = new ArrayList<String>();
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "select * from suscripcion";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                String video = rset.getString(1);
//video.setId_video(rset.getInt(1));
                lista.add(video);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(" error en get_suscritos ");
            System.out.println(e);
        }
        return lista;
    }

    public ArrayList<VideoBean> get_catalago() {
        ArrayList lista = new ArrayList<VideoBean>();
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "select * from catalogo";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                VideoBean video = new VideoBean();
//video.setId_video(rset.getInt(0));
                video.setNombre(rset.getString(1));
                video.setClasificacion(rset.getString(2));
                video.setGenero(rset.getString(3));
                video.setResumen(rset.getString(4));
                video.setCantidad_existencias(rset.getInt(5));
                lista.add(video);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(" error en get_catalago ");
            System.out.println(e);
        }
        return lista;
    }

    public ClienteBean get_cliente(int id_cliente) {
        ClienteBean persona = new ClienteBean();
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "select * from clientes where id_cliente =" + id_cliente;
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
// System.out.println("hola");
// System.out.println(rset.getInt(1));
                persona.setId_cliente(rset.getInt(1));
                persona.setNombre(rset.getString(2));
                persona.setDireccion(rset.getString(3));
                persona.setSalario_mensual(rset.getInt(4));
                persona.setTelefono(rset.getString(5));
                persona.setPotencial(rset.getString(6));
                persona.setEmail(rset.getString(7));
                persona.setSuscripto(rset.getString(8));
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("error en get_cliente ");
            System.out.println(e);
        }
        return persona;
    }

    public VideoBean get_video(int id_video) {
        VideoBean video = new VideoBean();
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "select * from video where id_video =" + id_video;
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                video.setId_video(rset.getInt(1));
                video.setNombre(rset.getString(2));
                video.setClasificacion(rset.getString(3));
                video.setGenero(rset.getString(4));
                video.setResumen(rset.getString(5));
                video.setLocales_id_local(rset.getInt(6));
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(" error en get_video ");
            System.out.println(e);
        }
        return video;
    }

    @Override
    public ArrayList<AlquilerBean> get_alquiler(int id_cliente) {
        ArrayList lista = new ArrayList<AlquilerBean>();
        try (Connection con = conectar();) {
            Statement stmt = con.createStatement();
            String query = "select * from alquileres where clientes_id_cliente =" + id_cliente;
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                AlquilerBean alquiler = new AlquilerBean();
                alquiler.setId_alquiler(rset.getInt(1));
                alquiler.setFecha_alquiler(rset.getDate(2));
                alquiler.SetFecha_planeada_entrega(rset.getDate(3));
                alquiler.getVideo_id_video(rset.getInt(4));
                alquiler.getClientes_id_cliente(rset.getInt(5));
                lista.add(alquiler);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(" error en get_alquiler ");
            System.out.println(e);
        }
        return lista;
    }
}
