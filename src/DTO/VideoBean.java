/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author Admin
 */
public class VideoBean  implements DTO {
    
    private int id_video ;
    private String nombre;
    private String clasificacion ;
    private String  genero ;
    private String resumen;
    private int locales_id_local;
    
    public VideoBean(){}
    
    public void setId_video(int id_video) {
        this.id_video = id_video;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setLocales_id_local(int locales_id_local) {
        this.locales_id_local = locales_id_local;
    }

    public int getId_video() {
        return id_video;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getGenero() {
        return genero;
    }

    public String getResumen() {
        return resumen;
    }

    public int getLocales_id_local() {
        return locales_id_local;
    }
    
    
}
