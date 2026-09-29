/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.GUI;

import ProyectoFinal.Configuracion.Configuracion;
import ProyectoFinal.Configuracion.GestorConfiguracion;
import ProyectoFinal.Configuracion.JConfiguracion;
import javax.swing.WindowConstants;
import ProyectoFinal.GUI.PantallaPrincipal;

/**
 *
 * @author Denis Gr
 */
public class Starter {

    public static void main(String[] args) {

        try {
            GestorConfiguracion gestorConf = GestorConfiguracion.getInstance();
            Configuracion conf = gestorConf.cargarConfiguracion();
            
            if ( conf == null  || conf.getNombreApp().equals("")) {
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        JConfiguracion f = new JConfiguracion();
                        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        f.setVisible(true);
                    }
                });
            } else {
                abrirPantallaPrincipal(conf);
            }
        } catch (Exception ex) {
            System.getLogger(Starter.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public static void abrirPantallaPrincipal(Configuracion conf) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                PantallaPrincipal pantalla = new PantallaPrincipal();

                pantalla.setTitle(conf.getNombreApp());
                pantalla.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                pantalla.setVisible(true);
            }

        });

    }

}
