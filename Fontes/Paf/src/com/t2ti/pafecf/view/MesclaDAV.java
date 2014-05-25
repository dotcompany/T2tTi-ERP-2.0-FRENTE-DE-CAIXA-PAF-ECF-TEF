/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Janela para mesclar DAVss</p>
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
package com.t2ti.pafecf.view;

import com.t2ti.pafecf.controller.DAVController;
import com.t2ti.pafecf.infra.DAVCabecalhoColumnModel;
import com.t2ti.pafecf.infra.DAVCabecalhoTableModel;
import com.t2ti.pafecf.infra.DAVDetalheColumnModel;
import com.t2ti.pafecf.infra.DAVDetalheTableModel;
import com.t2ti.pafecf.infra.MenuFiscalAction;
import com.t2ti.pafecf.vo.DAVCabecalhoVO;
import com.t2ti.pafecf.vo.DAVDetalheVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class MesclaDAV extends javax.swing.JDialog {

    List<DAVCabecalhoVO> listaDAVCabecalho = new ArrayList<DAVCabecalhoVO>();
    List<DAVDetalheVO> listaDAVDetalhe = new ArrayList<DAVDetalheVO>();
    DAVController davControl = new DAVController();
    public Integer idNovoDAV;

    public MesclaDAV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelGridCabecalho.setBackground(new Color(r, g, b));
        panelGridDetalhe.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));

        configuraGridCabecalho(davControl.listaDAVPendente());
        idNovoDAV = null;

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        TeclouBarraAction teclouBarraAction = new TeclouBarraAction();
        gridCabecalho.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "teclouBarraAction");
        gridCabecalho.getActionMap().put("teclouBarraAction", teclouBarraAction);

        SetaAcimaAction setaAcimaAction = new SetaAcimaAction();
        gridCabecalho.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "SetaAcimaAction");
        gridCabecalho.getActionMap().put("SetaAcimaAction", setaAcimaAction);

        SetaAbaixoAction setaAbaixoAction = new SetaAbaixoAction();
        gridCabecalho.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "SetaAbaixoAction");
        gridCabecalho.getActionMap().put("SetaAbaixoAction", setaAbaixoAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridCabecalho.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridDetalhe.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        MenuFiscalAction menuFiscalAction = new MenuFiscalAction(this);
        editCnpjCpf.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "menuFiscal");
        editCnpjCpf.getActionMap().put("menuFiscal", menuFiscalAction);

        if (listaDAVCabecalho != null) {
            gridCabecalho.setRowSelectionInterval(0, 0);
            gridCabecalho.requestFocus();
            teclouSetaAcimaAbaixo();
        } else {
            JOptionPane.showMessageDialog(null, "Não existem DAVs pendentes!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        this.setPreferredSize(new Dimension(750, 500));
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelGridCabecalho = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridCabecalho = new javax.swing.JTable();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        editNomeDestinatario = new javax.swing.JTextField();
        editCnpjCpf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelGridDetalhe = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridDetalhe = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mescla DAV");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaMesclar01.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelGridCabecalho.setBackground(new Color(255,255,255,0));
        panelGridCabecalho.setBorder(javax.swing.BorderFactory.createTitledBorder("DAVs disponíveis para mesclagem"));
        panelGridCabecalho.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 150));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 150));

        gridCabecalho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
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
        panelComponentes.add(panelGridCabecalho, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        botaoConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        panelBotoes.add(botaoConfirma, gridBagConstraints);

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoCancelar.png"))); // NOI18N
        botaoCancela.setText("Cancela (ESC)");
        botaoCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoCancela, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(editNomeDestinatario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(editCnpjCpf, gridBagConstraints);

        jLabel2.setText("Destinatário");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel2, gridBagConstraints);

        jLabel3.setText("CNPJ/CPF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        panelGridDetalhe.setBackground(new Color(255,255,255,0));
        panelGridDetalhe.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos do DAV selecionado"));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelGridDetalhe, gridBagConstraints);

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

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        confirma();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MesclaDAV dialog = new MesclaDAV(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField editCnpjCpf;
    private javax.swing.JTextField editNomeDestinatario;
    private javax.swing.JTable gridCabecalho;
    private javax.swing.JTable gridDetalhe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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

    private class TeclouBarraAction extends AbstractAction {

        public TeclouBarraAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouBarra();
        }
    }

    private class SetaAcimaAction extends AbstractAction {

        public SetaAcimaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouSetaAcimaAbaixo();
        }
    }

    private class SetaAbaixoAction extends AbstractAction {

        public SetaAbaixoAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouSetaAcimaAbaixo();
        }
    }

    private void teclouBarra() {
        if (this.getFocusOwner() == gridCabecalho) {
            AbstractTableModel modeloCabecalho = (AbstractTableModel) gridCabecalho.getModel();
            Object selecionado = modeloCabecalho.getValueAt(gridCabecalho.getSelectedRow(), 0);
            if (selecionado != null) {
                if (selecionado.toString().equals("")) {
                    modeloCabecalho.setValueAt("X", gridCabecalho.getSelectedRow(), 0);
                } else {
                    modeloCabecalho.setValueAt("", gridCabecalho.getSelectedRow(), 0);
                }
            } else {
                modeloCabecalho.setValueAt("X", gridCabecalho.getSelectedRow(), 0);
            }
        } else if (this.getFocusOwner() == gridDetalhe) {
            AbstractTableModel modeloDetalhe = (AbstractTableModel) gridDetalhe.getModel();
            Object selecionado = modeloDetalhe.getValueAt(gridDetalhe.getSelectedRow(), 0);
            if (selecionado != null) {
                if (selecionado.toString().equals("")) {
                    modeloDetalhe.setValueAt("X", gridDetalhe.getSelectedRow(), 0);
                } else {
                    modeloDetalhe.setValueAt("", gridDetalhe.getSelectedRow(), 0);
                }
            } else {
                modeloDetalhe.setValueAt("X", gridDetalhe.getSelectedRow(), 0);
            }
        }
    }

    private void teclouSetaAcimaAbaixo() {
        if (this.getFocusOwner() != gridDetalhe) {
            configuraGridDetalhe(listaDAVCabecalho.get(gridCabecalho.getSelectedRow()).getListaDavDetalhe());
        }
    }

    private void configuraGridCabecalho(List<DAVCabecalhoVO> listaDAVCabecalho) {
        this.listaDAVCabecalho = listaDAVCabecalho;

        if (listaDAVCabecalho != null) {
            gridCabecalho.setModel(new DAVCabecalhoTableModel(listaDAVCabecalho));
            gridCabecalho.setSelectionModel(new DefaultListSelectionModel() {

                public String toString() {
                    return "gridCabecalho";
                }
            });

            gridCabecalho.setAutoCreateColumnsFromModel(false);
            gridCabecalho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            FontMetrics fm = gridCabecalho.getFontMetrics(gridCabecalho.getFont());
            gridCabecalho.setColumnModel(new DAVCabecalhoColumnModel(fm));
        }
    }

    private void configuraGridDetalhe(List<DAVDetalheVO> listaDAVDetalhe) {
        this.listaDAVDetalhe = listaDAVDetalhe;

        gridDetalhe.setModel(new DAVDetalheTableModel(listaDAVDetalhe));
        gridDetalhe.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridDetalhe";
            }
        });

        gridDetalhe.setAutoCreateColumnsFromModel(false);
        gridDetalhe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridDetalhe.getFontMetrics(gridDetalhe.getFont());
        gridDetalhe.setColumnModel(new DAVDetalheColumnModel(fm));
    }

    private void confirma() {
        boolean itemSelecionado = false;
        if (listaDAVCabecalho != null) {
            for (int i = 0; i < listaDAVCabecalho.size(); i++) {
                for (int j = 0; j < listaDAVCabecalho.get(i).getListaDavDetalhe().size(); j++) {
                    if (listaDAVCabecalho.get(i).getListaDavDetalhe().get(j).getSelecao() != null) {
                        listaDAVCabecalho.get(i).setSelecao("X");
                        itemSelecionado = listaDAVCabecalho.get(i).getListaDavDetalhe().get(j).getSelecao().equals("X");
                    }
                }
            }
            if (!itemSelecionado) {
                JOptionPane.showMessageDialog(this, "Nenhum item foi selecionado!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            } else {
                if (editNomeDestinatario.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Informe o nome do destinatário!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                } else if (editCnpjCpf.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do destinatário!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] opcoes = {"Sim", "Não"};
                    int escolha = JOptionPane.showOptionDialog(null, "Tem certeza que deseja mesclar os DAV selecionados?", "Mesclar DAV",
                            JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, opcoes, null);
                    if (escolha == 0) {
                        idNovoDAV = davControl.mesclaDAV(listaDAVCabecalho, editNomeDestinatario.getText(), editCnpjCpf.getText());
                        dispose();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum item foi selecionado!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
