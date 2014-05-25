/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Classe de conexão com o Banco de Dados</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2013 T2Ti.COM</p>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * The author may be contacted at: alberteije@gmail.com</p>
 *
 * @author T2Ti.COM
 * @version 1.0
 */
package com.t2ti.pafecf.bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;

public class AcessoBanco {

    Connection con;
    Connection conRetaguarda;
    String bancoPaf;
    String bancoRetaguarda;

    public Connection conectar() {
        // Carrega as configurações de conexão
        try {
            Properties arquivoConexao = new Properties();
            arquivoConexao.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "conexao.properties")));
            bancoPaf = arquivoConexao.getProperty("sgbd.BD");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o arquivo de configuração!", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        // TODO: -Carregue os demais dados da conexão do arquivo conexao.properties [usuário, senha, etc]
        // TODO: -implemente a conexão para o Firebird ou outros bancos
        try {
            //Seta as conexões para o MySQL
            if (bancoPaf.equals("MySQL")) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pafecfjava?user=root&password=root");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar o Driver!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        return con;
    }

    public Connection conectarRetaguarda() {
        // Carrega as configurações de conexão
        try {
            Properties arquivoConexao = new Properties();
            arquivoConexao.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "conexao.properties")));
            bancoRetaguarda = arquivoConexao.getProperty("sgbd.BDRetaguarda");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o arquivo de configuração!", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        // TODO: -Carregue os demais dados da conexão do arquivo conexao.properties [usuário, senha, etc]
        // TODO: -implemente a conexão para o Firebird ou outros bancos
        try {
            //Seta as conexões para o MySQL
            if (bancoRetaguarda.equals("MySQL")) {
                Class.forName("com.mysql.jdbc.Driver");
                conRetaguarda = DriverManager.getConnection("jdbc:mysql://127.0.0.1/retaguarda?user=root&password=root");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar o Driver!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        return conRetaguarda;
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void desconectarRetaguarda() {
        try {
            conRetaguarda.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
