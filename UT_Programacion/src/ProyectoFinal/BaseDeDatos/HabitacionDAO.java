/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.BaseDeDatos;

import ProyectoFinal.Hotel.Habitacion;  
import ProyectoFinal.Hotel.HabitacionSimple;  
import ProyectoFinal.Hotel.HabitacionDoble;  
import ProyectoFinal.Hotel.Suite;  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Denis Gr
 */
public class HabitacionDAO {
    

    public Habitacion obtenerHabitacionPorId(int idHabitacion) {
       
        String sql = "SELECT numero, precio_base, tipo FROM habitaciones WHERE id_habitacion = ?";
        Habitacion habitacion = null;
        ArrayList<String> fotosVacias = new ArrayList<>(); 
        
        try (Connection con = ConexionBBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idHabitacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int numero = rs.getInt("numero");
                    double precio = rs.getDouble("precio_base");
                    String tipoStr = rs.getString("tipo"); 
                    
          
                    if ("Simple".equalsIgnoreCase(tipoStr)) {
                        habitacion = new HabitacionSimple(numero, precio, fotosVacias);
                    } else if ("Doble".equalsIgnoreCase(tipoStr)) {
                        habitacion = new HabitacionDoble(numero, precio, fotosVacias);
                    } else if ("Suite".equalsIgnoreCase(tipoStr)) {
                        habitacion = new Suite(numero, precio, fotosVacias, true, 20.0, true);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener habitación por ID: " + e.getMessage());
        }
        
        return habitacion;  
    }
}