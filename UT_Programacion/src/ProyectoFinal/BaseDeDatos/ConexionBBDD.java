/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.BaseDeDatos;


import ProyectoFinal.Configuracion.Configuracion;
import java.sql.*;
import java.sql.SQLException;
import java.sql.DriverManager;
/**
 *
 * @author Denis Gr
 */
public class ConexionBBDD {
    
private static String URL;
    private static String USER;
    private static String PASS;
    private static boolean inicializada = false;


    public static void Inicializar(Configuracion conf) {
        if (!inicializada) {
            if(conf != null) {
            URL = "jdbc:mysql://" + conf.getIp_bbdd() + ":" + conf.getPuerto_bbdd() + "/bbdd_proyectofinal_programación";
            USER = conf.getUsuario_bbdd();
           PASS = conf.getContrasena_bbdd();
        } else {
         
            URL = "jdbc:mysql://localhost:3306/bbdd_proyectofinal_programación";
         USER = "root";
           PASS = "denis1234";
        }
        
        inicializada = true;
        System.out.println("Conexión inicializada: " + URL);
        } else {
            System.out.println("La conexión ya fue inicializada anteriormente");
             }
}
    
 
    
    
    
    public static Connection getConexion() throws SQLException {
        
        if(!inicializada) {
            
            throw new SQLException ("La conexión no ha sido inicializada");
            
        }
        if(URL == null || PASS == null) {
            throw new SQLException("Parámetros de conexión no configurados correctamente");
        }
        
        
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static boolean estaInicializada() {
        
        return inicializada;
    }
    
    
    public static void main(String[] args) throws SQLException {
        Inicializar(null);
        try {
        Connection cn = getConexion();
        System.out.println("¡Conexión exitosa!: " + cn);
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error de conexión: " + e.getMessage());
    }


        
        
    }
    
}
