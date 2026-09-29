/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.Configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author Denis Gr
 */
public class GestorConfiguracion {

    public  final String FICHERO_CONFIGURACION = "config.dat";
    
    public boolean configExists() {
        return new File(FICHERO_CONFIGURACION).exists();
    }

    private static GestorConfiguracion gestor;

    public static GestorConfiguracion getInstance() {

        if (gestor == null) {
            gestor = new GestorConfiguracion();
        }
        return gestor;
    }

    public Configuracion cargarConfiguracion() throws Exception {

        Configuracion confLeida = null;
        try {
            FileInputStream FIS = new FileInputStream(FICHERO_CONFIGURACION);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            confLeida = (Configuracion) OIS.readObject();
            OIS.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo de configuración no encontrado en " + FICHERO_CONFIGURACION);
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("Error de entrada/salida al leer la configuración");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase Configuracion no encontrada");
            e.printStackTrace();
        }

        return confLeida;
    }

    public  void guardarConfiguracion(Configuracion config) throws Exception {

        try {
            FileOutputStream fos = new FileOutputStream(FICHERO_CONFIGURACION);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(config);
            oos.close();

            System.out.println("Configuración guardada correctamente en " + FICHERO_CONFIGURACION);

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se puede crear/acceder al archivo de configuración en " + FICHERO_CONFIGURACION);
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("Error de entrada/salida al guardar la configuración");
            e.printStackTrace();

        }
        
    }
}
