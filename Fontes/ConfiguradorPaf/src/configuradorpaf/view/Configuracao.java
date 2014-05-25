/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Janela de configuração</p>
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
package configuradorpaf.view;

import configuradorpaf.controller.ConfiguracaoController;
import configuradorpaf.infra.Biblioteca;
import configuradorpaf.infra.ComponentesColumnModel;
import configuradorpaf.infra.ComponentesTableModel;
import configuradorpaf.infra.ConfiguracaoColumnModel;
import configuradorpaf.infra.ConfiguracaoTableModel;
import configuradorpaf.vo.ConfiguracaoVO;
import configuradorpaf.vo.PosicaoComponentesVO;
import jACBr.ACBrECF;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class Configuracao extends javax.swing.JDialog {

    public static ACBrECF aCBrECF;
    public static ConfiguracaoVO configuracao;
    List<ConfiguracaoVO> listaConfiguracao = new ArrayList<ConfiguracaoVO>();
    List<PosicaoComponentesVO> listaPosicaoComponentes = new ArrayList<PosicaoComponentesVO>();
    ConfiguracaoController configuracaoControl = new ConfiguracaoController();

    public Configuracao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        configuracao = new ConfiguracaoController().pegaConfiguracao();
        
        try {
            aCBrECF = new ACBrECF();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(rootPane, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        configuraACBr();

        try {
            jtfGrandeTotal.setText(String.valueOf(aCBrECF.getGrandeTotal()));
            jtfNumeroSerie.setText(aCBrECF.getNumSerie());
            
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(rootPane, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        
        int r = Integer.valueOf(configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(configuracao.getCorJanelasInternas().substring(8, 11));

        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelGridCabecalho.setBackground(new Color(r, g, b));
        panelGridDetalhe.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));

        listaConfiguracao.add(configuracao);
        configuraGridCabecalho(listaConfiguracao);
        configuraGridDetalhe(configuracaoControl.verificaPosicaoTamanho());

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoGravaArquivoAuxiliar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoGravaArquivoAuxiliar.getActionMap().put("confirmaAction", confirmaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridCabecalho.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridDetalhe.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        gridCabecalho.setRowSelectionInterval(0, 0);
        gridCabecalho.requestFocus();

        this.setPreferredSize(new Dimension(700, 500));
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelBotoes = new javax.swing.JPanel();
        botaoGravaArquivoAuxiliar = new javax.swing.JButton();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panelGridCabecalho = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridCabecalho = new javax.swing.JTable();
        panelGridDetalhe = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridDetalhe = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtfGrandeTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfNumeroSerie = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurador");
        setModalExclusionType(null);
        setModalityType(null);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuradorpaf/view/telaMonitor04.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelBotoes.setBackground(new Color(255,255,255,0));

        botaoGravaArquivoAuxiliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuradorpaf/view/botaoConfirmar.png"))); // NOI18N
        botaoGravaArquivoAuxiliar.setText("Gravar Arquivo Auxiliar");
        botaoGravaArquivoAuxiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGravaArquivoAuxiliarActionPerformed(evt);
            }
        });
        panelBotoes.add(botaoGravaArquivoAuxiliar);

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuradorpaf/view/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        botaoConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmaActionPerformed(evt);
            }
        });
        panelBotoes.add(botaoConfirma);

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuradorpaf/view/botaoCancelar.png"))); // NOI18N
        botaoCancela.setText("Cancela (ESC)");
        botaoCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelaActionPerformed(evt);
            }
        });
        panelBotoes.add(botaoCancela);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        panelGridCabecalho.setBackground(new Color(255,255,255,0));
        panelGridCabecalho.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações"));
        panelGridCabecalho.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 80));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 80));

        gridCabecalho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        gridCabecalho.setMaximumSize(null);
        gridCabecalho.setMinimumSize(null);
        gridCabecalho.setPreferredSize(null);
        jScrollPane1.setViewportView(gridCabecalho);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGridCabecalho.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel1.add(panelGridCabecalho, gridBagConstraints);

        panelGridDetalhe.setBackground(new Color(255,255,255,0));
        panelGridDetalhe.setBorder(javax.swing.BorderFactory.createTitledBorder("Posição dos componentes"));
        panelGridDetalhe.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(452, 150));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 150));

        gridDetalhe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(gridDetalhe);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGridDetalhe.add(jScrollPane2, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgBotoes/preferences_24.png"))); // NOI18N
        jButton1.setText("Posicionar os componentes de forma visual");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panelGridDetalhe.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel1.add(panelGridDetalhe, gridBagConstraints);

        jTabbedPane1.addTab("Configurações", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jtfGrandeTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 540, -1));

        jLabel2.setText("Grande Total:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel3.setText("Número de Série:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));
        jPanel2.add(jtfNumeroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 540, -1));

        jTabbedPane1.addTab("Arquivo Auxiliar", jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 400;
        panelComponentes.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelPrincipal.add(panelComponentes, gridBagConstraints);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        System.exit(0);
}//GEN-LAST:event_botaoCancelaActionPerformed

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        confirma();
    }//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoGravaArquivoAuxiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGravaArquivoAuxiliarActionPerformed
        gravaArquivoAuxiliar();
    }//GEN-LAST:event_botaoGravaArquivoAuxiliarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Caixa caixa = new Caixa(this, false);
        caixa.setLocationRelativeTo(null);
        caixa.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Configuracao dialog = new Configuracao(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JButton botaoGravaArquivoAuxiliar;
    private javax.swing.JTable gridCabecalho;
    private javax.swing.JTable gridDetalhe;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jtfGrandeTotal;
    private javax.swing.JTextField jtfNumeroSerie;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelGridCabecalho;
    private javax.swing.JPanel panelGridDetalhe;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            confirma();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private void configuraGridCabecalho(List<ConfiguracaoVO> listaConfiguracao) {
        this.listaConfiguracao = listaConfiguracao;

        gridCabecalho.setModel(new ConfiguracaoTableModel(listaConfiguracao));
        gridCabecalho.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridCabecalho";
            }
        });

        gridCabecalho.setAutoCreateColumnsFromModel(false);
        gridCabecalho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridCabecalho.getFontMetrics(gridCabecalho.getFont());
        gridCabecalho.setColumnModel(new ConfiguracaoColumnModel(fm));
    }

    private void configuraGridDetalhe(List<PosicaoComponentesVO> listaPosicaoComponentes) {
        this.listaPosicaoComponentes = listaPosicaoComponentes;

        gridDetalhe.setModel(new ComponentesTableModel(listaPosicaoComponentes));
        gridDetalhe.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridDetalhe";
            }
        });

        gridDetalhe.setAutoCreateColumnsFromModel(false);
        gridDetalhe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridDetalhe.getFontMetrics(gridDetalhe.getFont());
        gridDetalhe.setColumnModel(new ComponentesColumnModel(fm));
    }

    private void confirma() {
        String[] opcoes = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(null, "Tem certeza que deseja mesclar os DAV selecionados?", "Mesclar DAV",
                JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, null);
        if (escolha == 0) {
            dispose();
        }
    }
    
    private void configuraACBr() {
        try {
            aCBrECF.setPorta(configuracao.getPortaEcf());
            aCBrECF.setTimeOut(configuracao.getTimeOutEcf());
            aCBrECF.setIntervaloAposComando(configuracao.getIntervaloEcf());
            aCBrECF.setModelo(Integer.valueOf(configuracao.getImpressoraVO().getModeloAcbr()));
            aCBrECF.ativar();
            aCBrECF.carregaAliquotas();
            aCBrECF.carregaFormasPagamento();

            if (aCBrECF.getAliquotas().length < 1) {
                JOptionPane.showMessageDialog(rootPane, "ECF sem aliquotas cadastradas. A aplicação será encerrada!", "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            if (aCBrECF.getFormasPagamento().length < 1) {
                JOptionPane.showMessageDialog(rootPane, "ECF sem formas de pagamento cadastradas. A aplicação será encerrada!", "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

        } catch (Throwable t) {
            JOptionPane.showMessageDialog(rootPane, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void gravaArquivoAuxiliar() {
        try {
            Properties arquivoAuxiliar = new Properties();
            arquivoAuxiliar.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties")));

            String gtEcf = String.valueOf(aCBrECF.getGrandeTotal());
            gtEcf = Biblioteca.MD5String(gtEcf);
            arquivoAuxiliar.setProperty("ecf.gt", gtEcf);
            
            String serieEcf = aCBrECF.getNumSerie();
            serieEcf = Biblioteca.MD5String(serieEcf);
            arquivoAuxiliar.setProperty("ecf.serie", serieEcf);

            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties"));

            arquivoAuxiliar.store(out, "Arquivo auxiliar do PAF");

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
