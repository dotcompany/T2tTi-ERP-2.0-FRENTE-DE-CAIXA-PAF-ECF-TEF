/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Janela para controlar as cargas - integração</p>
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
package com.t2ti.integradorpaf;

import com.t2ti.integradorpaf.controller.ImportaController;
import com.t2ti.integradorpaf.controller.LogImportacaoController;
import com.t2ti.integradorpaf.infra.Biblioteca;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CargaPDV extends javax.swing.JDialog {

    public static String tipo;
    public static String pathVenda, pathCarga, pathCargaRemoto = "";

    public CargaPDV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        ProcessaCarga p = new ProcessaCarga(this);
        new Thread(p).start();

        this.setPreferredSize(new Dimension(500, 60));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Integrador");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(jProgressBar1, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    public static Boolean exportaCarga(String pRemoteApp) {
        String localApp, linha, compara, logTupla;
        String[] tupla;
        int i = 0;

        localApp = System.getProperty("user.dir") + "\\script\\carga.txt";

        try {
            if (new File(pRemoteApp).exists()) {
                Biblioteca.copy(new File(pRemoteApp), new File(localApp), true);
                File arquivoCarga = new File(localApp);
                FileReader fileReader = new FileReader(arquivoCarga);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((linha = bufferedReader.readLine()) != null) {
                    i++;
                    if (jProgressBar1.getPercentComplete() == 1.0) {
                        i = 0;
                    }
                    jProgressBar1.setValue(i);
                    linha = linha.replace("'null'", "null");
                    tupla = linha.split("\\|");
                    logTupla = linha;
                    compara = tupla[0];

                    //TODO: -Verifique se todas as cargas estão corretas. Corrija o que for necessário e implemente as demais.

                    if (compara.equals("VCB")) {
                        if (!(new ImportaController().gravaCargaVendaCabecalho(linha))) {
                            new LogImportacaoController().gravaLogImportacao(logTupla);
                        }
                    } else if (compara.equals("VDT")) {
                        if (!(new ImportaController().gravaCargaVendaDetalhe(linha))) {
                            new LogImportacaoController().gravaLogImportacao(logTupla);
                        }
                    } else if (compara.equals("TTP")) {
                        if (!(new ImportaController().gravaCargaTotalTipoPagamento(linha))) {
                            new LogImportacaoController().gravaLogImportacao(logTupla);
                        }
                    } else if (compara.equals("SANGRIA")) {
                        if (!(new ImportaController().gravaCargaSangria(linha))) {
                            new LogImportacaoController().gravaLogImportacao(logTupla);
                        }
                    } else if (compara.equals("SUPRIMENTO")) {
                        if (!(new ImportaController().gravaCargaSuprimento(linha))) {
                            new LogImportacaoController().gravaLogImportacao(logTupla);
                        }
                    } else if (compara.equals("CANCELAVCB")) {
                    } else if (compara.equals("CANCELAVDT")) {
                    } else if (compara.equals("CANCELATTP")) {
                    } else if (compara.equals("CANCELANF2CAB")) {
                    } else if (compara.equals("INSERENF2CAB")) {
                    } else if (compara.equals("INSERENF2DET")) {
                    } else if (compara.equals("R02")) {
                    } else if (compara.equals("R03")) {
                    } else if (compara.equals("R06")) {
                    } else if (compara.equals("MOVIMENTOA")) {
                    } else if (compara.equals("MOVIMENTOF")) {
                    } else if (compara.equals("FECHAMENTO")) {
                    }
                }

                fileReader.close();
                bufferedReader.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}

class ProcessaCarga implements Runnable {

    javax.swing.JDialog janelaCarga;

    ProcessaCarga(CargaPDV pThis) {
        janelaCarga = pThis;
    }

    @Override
    public void run() {
        try {
            while (true) {
                File diretorio = new File(CargaPDV.pathCargaRemoto);
                String[] arquivos = diretorio.list();
                for (int i = 0; i < arquivos.length; i++) {
                    if (! new File(CargaPDV.pathCargaRemoto + arquivos[i]).isDirectory()) {
                        CargaPDV.exportaCarga(CargaPDV.pathCargaRemoto + arquivos[i]);
                        new File(CargaPDV.pathCargaRemoto + arquivos[i]).delete();
                    }
                }
                Thread.sleep(10);
            }
        } catch (Exception e) {
        }
    }
}