/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.Hotel;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import ProyectoFinal.GUI.VentanaGaleria;

/**
 *
 * @author Denis Gr
 */
public abstract class Habitacion {

   private int numero;
    private double precioBase;
    private ArrayList<String> fotos;
    private TipoHabitacion tipo;
    
 
   
    public enum TipoHabitacion {
        SIMPLE("Simple"),
        DOBLE("Doble"),
        SUITE("Suite");
        
        private final String valorBBDD;
        
        TipoHabitacion(String valorBBDD) {
            this.valorBBDD = valorBBDD;
        }
        
        
        public String getValorBBDD() {
            return valorBBDD;
        }
  
        public static TipoHabitacion fromBBDD(String valor) {
            if (valor == null) return null;
            for (TipoHabitacion t : TipoHabitacion.values()) {
                if (t.valorBBDD.equalsIgnoreCase(valor)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Tipo de habitación desconocido: " + valor);
        }
    }
 
    public Habitacion(int numero, double precioBase, ArrayList<String> fotos) {
        this.numero = numero;
        this.precioBase = precioBase;
        this.fotos = (fotos != null) ? fotos : new ArrayList<>();
    }
 
    
    public int getNumero() {
        return numero;
    }
 
    public void setNumero(int numero) {
        this.numero = numero;
    }
 
    public double getPrecioBase() {
        return precioBase;
    }
 
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
 
    public ArrayList<String> getFotos() {
        return fotos;
    }
 
    public void setFotos(ArrayList<String> fotos) {
        if (fotos != null && fotos.size() <= 10) {
            this.fotos = fotos;
        }
    }
 
    public TipoHabitacion getTipo() {
        return tipo;
    }
 
    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }
 

    public void añadirFoto(String ruta) {
        if (fotos.size() < 10) {
            fotos.add(ruta);
        } else {
            System.err.println("No se pueden añadir más de 10 fotos a una habitación");
        }
    }
 
   
    
 
    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", precioBase=" + precioBase +
                ", tipo=" + tipo +
                ", fotos=" + fotos.size() +
                '}';
    }

}
    


