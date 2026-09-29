/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.BaseDeDatos;

import java.sql.*;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Denis Gr
 */
public class UsuarioDAO {

    public boolean RegistrarUsuarios(String nombre, String apellido, String pass, java.sql.Date FechaNac, String telefono) {

        String sql = "INSERT INTO usuarios(nombre,apellido,pass,fecha_nacimiento,telefono) VALUES (?,?,?,?,?)";

        try (Connection con = ConexionBBDD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, pass);
            ps.setDate(4, FechaNac);
            ps.setString(5, telefono);

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }

    }

    public int obtenerIdCliente(String nombre, String apellido) {
        String sql = "SELECT id_cliente FROM usuarios WHERE nombre = ? AND apellido = ?";

        try (Connection con = ConexionBBDD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_cliente");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    public boolean validarLogin(String nombre, String apellido, String pass) {

        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND apellido = ? AND pass = ?";

        try (Connection con = ConexionBBDD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, pass);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error en el login: " + e.getMessage());
            return false;
        }

    }
    
    public int obtenerIdHabitacion(String tipo) {
    String sql = "SELECT id_habitacion FROM habitaciones WHERE tipo = ? LIMIT 1";
    
    try (Connection con = ConexionBBDD.getConexion(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, tipo);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("id_habitacion");
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    }
    return -1;
}

  public boolean guardarReserva(int id, String nombre, java.util.Date entrada, java.util.Date salida, String personas, String tipo, double precioTotal) {

      String tipoLimpio = tipo;
        if (tipo.contains("Simple")) tipoLimpio = "Simple";
        else if (tipo.contains("Doble")) tipoLimpio = "Doble";
        else if (tipo.contains("Suite")) tipoLimpio = "Suite";
      
    int idHabitacion = obtenerIdHabitacion(tipoLimpio);
    
    if (idHabitacion == -1) {
        System.err.println("Error: No existe habitación de tipo: " + tipo);
        return false;
    }

    String sql = "INSERT INTO reservas (id_cliente, id_habitacion, nombre_usuario, "
               + "fecha_entrada, fecha_salida, num_personas, tipo, precio_total) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection con = ConexionBBDD.getConexion(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.setInt(2, idHabitacion);       
        ps.setString(3, nombre);
        ps.setDate(4, new java.sql.Date(entrada.getTime()));
        ps.setDate(5, new java.sql.Date(salida.getTime()));
        ps.setString(6, personas);
        ps.setString(7, tipoLimpio);
        ps.setDouble(8, precioTotal);

        return ps.executeUpdate() > 0;
        
    } catch (SQLException e) {
        System.out.println("ERROR AL GUARDAR RESERVA: " + e.getMessage());
        return false;
    }
}

    public DefaultTableModel obtenerReservasTabla(int idCliente) {

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID Reserva");
        modelo.addColumn("Cliente");
        modelo.addColumn("Entrada");
        modelo.addColumn("Salida");
        modelo.addColumn("Numero Personas");
        modelo.addColumn("Habitación");
        modelo.addColumn("Precio Total");

        String sql;

     

           if (idCliente == -1) {
  
    sql = "SELECT r.id_reserva, CONCAT(u.nombre, ' ', u.apellido) AS cliente, r.fecha_entrada, r.fecha_salida, r.num_personas, h.tipo AS tipo_habitacion, r.precio_total FROM reservas r INNER JOIN usuarios u ON r.id_cliente = u.id_cliente INNER JOIN habitaciones h ON r.id_habitacion = h.id_habitacion WHERE r.fecha_salida >= CURDATE() AND r.estado = 'Activa'";
} else {

    sql = "SELECT r.id_reserva, CONCAT(u.nombre, ' ', u.apellido) AS cliente, r.fecha_entrada, r.fecha_salida, r.num_personas, h.tipo AS tipo_habitacion, r.precio_total FROM reservas r INNER JOIN usuarios u ON r.id_cliente = u.id_cliente INNER JOIN habitaciones h ON r.id_habitacion = h.id_habitacion WHERE r.id_cliente = ? AND r.fecha_salida >= CURDATE() AND r.estado = 'Activa'";
}

        try (Connection con = ConexionBBDD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            if (idCliente != -1) {
                ps.setInt(1, idCliente);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_reserva");
                fila[1] = rs.getString("cliente");
                fila[2] = rs.getDate("fecha_entrada");
                fila[3] = rs.getDate("fecha_salida");
                fila[4] = rs.getString("num_personas");
                fila[5] = rs.getString("tipo_habitacion");
                fila[6] = rs.getDouble("precio_total") + " €";
                modelo.addRow(fila);

            }

        } catch (Exception e) {
            System.out.println("Error al cargar tabla: " + e.getMessage());
        }
        return modelo;
    }

    public boolean CancelarReserva(int idReserva) {

        String sql = "UPDATE reservas  SET estado = 'Cancelada ' WHERE id_reserva = ? ";

        try (Connection con = ConexionBBDD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al cancelar reserva: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarReserva(int idReserva) {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        try (Connection con = ConexionBBDD.getConexion(); 
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }


        public boolean modificarReserva(int idReserva, java.sql.Date nuevaEntrada, java.sql.Date nuevaSalida, String nuevasPersonas, String tipoHab, double precioTotal) {
            
        String tipoLimpio = tipoHab;
        if (tipoHab.contains("Simple")) tipoLimpio = "Simple";
        else if (tipoHab.contains("Doble")) tipoLimpio = "Doble";
        else if (tipoHab.contains("Suite")) tipoLimpio = "Suite";

        int idHabitacionEncontrada = obtenerIdHabitacion(tipoLimpio);
        if (idHabitacionEncontrada == -1) {
            System.out.println("Error: No se encontró ninguna habitación de tipo: " + tipoLimpio);
            return false;
        }

        String sqlUpdate = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, num_personas = ?, id_habitacion = ?, tipo = ?, precio_total = ? WHERE id_reserva = ?";

        try (Connection con = ConexionBBDD.getConexion(); 
             PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
            
            psUpdate.setDate(1, nuevaEntrada);
            psUpdate.setDate(2, nuevaSalida);
            psUpdate.setString(3, nuevasPersonas);
            psUpdate.setInt(4, idHabitacionEncontrada);
            psUpdate.setString(5, tipoLimpio);
            psUpdate.setDouble(6, precioTotal);
            psUpdate.setInt(7, idReserva);

            return psUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar la reserva: " + e.getMessage());
            return false;
        }
    }

    

}
