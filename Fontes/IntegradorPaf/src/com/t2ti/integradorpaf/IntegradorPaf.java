/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t2ti.integradorpaf;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Eije
 */
public class IntegradorPaf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Properties arquivoConexao = new Properties();
            arquivoConexao.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "conexao.properties")));
            CargaPDV.pathCargaRemoto = arquivoConexao.getProperty("importa.RemoteApp");
        } catch (Exception e) {
        }
        new CargaPDV(null, true);                    
    }
}
