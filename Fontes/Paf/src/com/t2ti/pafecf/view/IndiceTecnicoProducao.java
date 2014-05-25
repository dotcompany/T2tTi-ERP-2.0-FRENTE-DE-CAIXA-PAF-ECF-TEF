/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Janela para produção de produtos - ficha técnica</p>
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

import com.t2ti.pafecf.controller.FichaTecnicaController;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.MenuFiscalAction;
import com.t2ti.pafecf.vo.FichaTecnicaVO;
import com.t2ti.pafecf.vo.ProdutoVO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class IndiceTecnicoProducao extends javax.swing.JDialog {

    private ProdutoVO produtoProprio;
    private ProdutoVO produtoTerceiro;
    private List<FichaTecnicaVO> listaFichaTecnica;
    private DefaultListModel modeloLista;

    public IndiceTecnicoProducao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        listaFichaTecnica = new ArrayList<FichaTecnicaVO>();
        modeloLista = new DefaultListModel();
        listProdutosComposicao.setModel(modeloLista);

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        jPanel1.setBackground(new Color(r, g, b));
        jPanel2.setBackground(new Color(r, g, b));

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        LocalizaProdutoProprioAction localizaProdutoProprioAction = new LocalizaProdutoProprioAction();
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "localizaAction");
        jPanel1.getActionMap().put("localizaAction", localizaProdutoProprioAction);

        LocalizaProdutoTerceiroAction localizaProdutoTerceiroAction = new LocalizaProdutoTerceiroAction();
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "localizaTerceiroAction");
        jPanel1.getActionMap().put("localizaTerceiroAction", localizaProdutoTerceiroAction);

        RemoveAction removeAction = new RemoveAction();
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "removeAction");
        jPanel1.getActionMap().put("removeAction", removeAction);

        MenuFiscalAction menuFiscalAction = new MenuFiscalAction(this);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "menuFiscal");
        jPanel1.getActionMap().put("menuFiscal", menuFiscalAction);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        editGtin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        editDescricao = new javax.swing.JTextField();
        editUnidade = new javax.swing.JTextField();
        editValorVenda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProdutosComposicao = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        editQuantidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tabela de Índice Técnico de Produção");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Produto a ser Produzido: (F2 - Localiza)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        editGtin.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel1.add(editGtin, gridBagConstraints);

        jLabel2.setText("GTIN:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Descrição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Unidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Valor de Venda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel5, gridBagConstraints);

        editDescricao.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel1.add(editDescricao, gridBagConstraints);

        editUnidade.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel1.add(editUnidade, gridBagConstraints);

        editValorVenda.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel1.add(editValorVenda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Produtos de Composição");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        jScrollPane1.setViewportView(listProdutosComposicao);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Adiciona Produto (F3)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Remove Produto (F5)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        editQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editQuantidade.setText("1,000");
        editQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editQuantidadeFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(editQuantidade, gridBagConstraints);

        jLabel9.setText("Quantidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setText("DESCRIÇÃO | QTDE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        jPanel3.add(botaoConfirma);

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoCancelar.png"))); // NOI18N
        botaoCancela.setText("Cancela (ESC)");
        jPanel3.add(botaoCancela);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editQuantidadeFocusGained
        editQuantidade.setSelectionStart(0);
        editQuantidade.setSelectionEnd(editQuantidade.getText().length());
    }//GEN-LAST:event_editQuantidadeFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                IndiceTecnicoProducao dialog = new IndiceTecnicoProducao(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JTextField editDescricao;
    private javax.swing.JTextField editGtin;
    private javax.swing.JTextField editQuantidade;
    private javax.swing.JTextField editUnidade;
    private javax.swing.JTextField editValorVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listProdutosComposicao;
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
            //cancelado = true;
            dispose();
        }
    }

    private class LocalizaProdutoProprioAction extends AbstractAction {

        public LocalizaProdutoProprioAction() {
        }

        public void actionPerformed(ActionEvent e) {
            localizaProdutoProprio();
        }
    }

    private class RemoveAction extends AbstractAction {

        public RemoveAction() {
        }

        public void actionPerformed(ActionEvent e) {
            removeProduto();
        }
    }

    private class LocalizaProdutoTerceiroAction extends AbstractAction {

        public LocalizaProdutoTerceiroAction() {
        }

        public void actionPerformed(ActionEvent e) {
            localizaProdutoTerceiro();
        }
    }

    private void localizaProdutoProprio() {
        ImportaProduto janelaImportaProduto = new ImportaProduto(Caixa.getCaixa(), true, true, false);
        janelaImportaProduto.setLocationRelativeTo(null);
        janelaImportaProduto.setVisible(true);
        produtoProprio = janelaImportaProduto.getProduto();

        if (produtoProprio != null) {
            editGtin.setText(produtoProprio.getGtin());
            editDescricao.setText(produtoProprio.getDescricaoPDV());
            editUnidade.setText(produtoProprio.getUnidadeProdutoVO().getNome());
            editValorVenda.setText(Biblioteca.formatoDecimal("V", produtoProprio.getValorVenda()));

            FichaTecnicaController fichaControl = new FichaTecnicaController();
            listaFichaTecnica = fichaControl.consultaFichaTecnica(produtoProprio.getId());

            for (int i = 0; i < listaFichaTecnica.size(); i++) {
                modeloLista.addElement(listaFichaTecnica.get(i).getDescricao() + " | " + Biblioteca.formatoDecimal("Q", listaFichaTecnica.get(i).getQuantidade()));
            }
            listProdutosComposicao.requestFocus();
        } else {
            editGtin.setText("");
            editDescricao.setText("");
            editUnidade.setText("");
            editValorVenda.setText("");
        }
    }

    private void localizaProdutoTerceiro() {
        if (produtoProprio != null) {
            try {
                double quantidade = Double.parseDouble(editQuantidade.getText().replace(",", "."));
                ImportaProduto janelaImportaProduto = new ImportaProduto(Caixa.getCaixa(), true, false, true);
                janelaImportaProduto.setLocationRelativeTo(null);
                janelaImportaProduto.setVisible(true);

                produtoTerceiro = janelaImportaProduto.getProduto();

                if (produtoTerceiro != null) {
                    FichaTecnicaVO fichaTecnica = new FichaTecnicaVO();
                    fichaTecnica.setIdProdutoFilho(produtoTerceiro.getId());
                    fichaTecnica.setDescricao(produtoTerceiro.getDescricaoPDV());
                    fichaTecnica.setQuantidade(quantidade);
                    listaFichaTecnica.add(fichaTecnica);

                    modeloLista.addElement(fichaTecnica.getDescricao() + " | " + Biblioteca.formatoDecimal("Q", fichaTecnica.getQuantidade()));

                    editQuantidade.setText(Biblioteca.formatoDecimal("Q", 1));
                    listProdutosComposicao.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto de produção própria!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeProduto() {
        if (listProdutosComposicao.getSelectedIndex() != -1) {
            listaFichaTecnica.remove(listProdutosComposicao.getSelectedIndex());
            modeloLista.removeElementAt(listProdutosComposicao.getSelectedIndex());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void confirma() {
        if (produtoProprio == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto de produção própria!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
        } else if (listaFichaTecnica.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione itens de composição!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 0; i < listaFichaTecnica.size(); i++){
                listaFichaTecnica.get(i).setIdProduto(produtoProprio.getId());
            }
            FichaTecnicaController fichaControl = new FichaTecnicaController();
            if (fichaControl.gravaFichaTecnica(listaFichaTecnica)){
                JOptionPane.showMessageDialog(this, "Dados gravados com sucesso", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao gravar os dados!", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}