/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.Hotel;

import java.util.ArrayList;

/**
 *
 * @author Denis Gr
 */
public class Suite extends Habitacion  {

    /**
     * @return the minibar
     */
    public boolean isMinibar() {
        return minibar;
    }

    /**
     * @param minibar the minibar to set
     */
    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    /**
     * @return the metrosterraza
     */
    public double getMetrosterraza() {
        return metrosterraza;
    }

    /**
     * @param metrosterraza the metrosterraza to set
     */
    public void setMetrosterraza(double metrosterraza) {
        this.metrosterraza = metrosterraza;
    }

    /**
     * @return the jacuzzi
     */
    public boolean isJacuzzi() {
        return jacuzzi;
    }

    /**
     * @param jacuzzi the jacuzzi to set
     */
    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }
    
    private boolean minibar;
    private double metrosterraza;
    private boolean jacuzzi;
    
    

    public Suite(int numero, double precioBase,ArrayList<String> fotos, boolean minibar, double metrosterraza, boolean jacuzzi) {
        super(numero, precioBase,fotos);
        this.minibar = minibar;
        this.metrosterraza = metrosterraza;
        this.jacuzzi = jacuzzi;
    }


}
