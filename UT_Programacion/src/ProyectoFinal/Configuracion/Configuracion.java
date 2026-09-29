/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.Configuracion;

import java.io.Serializable;

/**
 *
 * @author Denis Gr
 */
public class Configuracion implements Serializable {


    
    private String nombreApp;
    private String ip_bbdd;
    private int puerto_bbdd;
    private String usuario_bbdd;
    private String contrasena_bbdd;

    public Configuracion(String nombreApp, String ip, int puerto, String usuario, String contrasena) {
        this.nombreApp = nombreApp;
        this.ip_bbdd = ip;
        this.puerto_bbdd = puerto;
        this.usuario_bbdd = usuario;
        this.contrasena_bbdd = contrasena;
    }

    /**
     * @return the nombreApp
     */
    public String getNombreApp() {
        return nombreApp;
    }

    /**
     * @param nombreApp the nombreApp to set
     */
    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }

    /**
     * @return the ip_bbdd
     */
    public String getIp_bbdd() {
        return ip_bbdd;
    }

    /**
     * @param ip_bbdd the ip_bbdd to set
     */
    public void setIp_bbdd(String ip_bbdd) {
        this.ip_bbdd = ip_bbdd;
    }

    /**
     * @return the puerto_bbdd
     */
    public int getPuerto_bbdd() {
        return puerto_bbdd;
    }

    /**
     * @param puerto_bbdd the puerto_bbdd to set
     */
    public void setPuerto_bbdd(int puerto_bbdd) {
        this.puerto_bbdd = puerto_bbdd;
    }

    /**
     * @return the usuario_bbdd
     */
    public String getUsuario_bbdd() {
        return usuario_bbdd;
    }

    /**
     * @param usuario_bbdd the usuario_bbdd to set
     */
    public void setUsuario_bbdd(String usuario_bbdd) {
        this.usuario_bbdd = usuario_bbdd;
    }

    /**
     * @return the contrasena_bbdd
     */
    public String getContrasena_bbdd() {
        return contrasena_bbdd;
    }

    /**
     * @param contrasena_bbdd the contrasena_bbdd to set
     */
    public void setContrasena_bbdd(String contrasena_bbdd) {
        this.contrasena_bbdd = contrasena_bbdd;
    }

   
    
    
    
    
}
