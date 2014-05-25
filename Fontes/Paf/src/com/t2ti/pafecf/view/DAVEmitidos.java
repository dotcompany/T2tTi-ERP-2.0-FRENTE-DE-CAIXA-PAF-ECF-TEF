/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Janela para geração dos DAV emitidos</p>
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
import com.t2ti.pafecf.controller.EmpresaController;
import com.t2ti.pafecf.controller.ImpressoraController;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.MenuFiscalAction;
import com.t2ti.pafecf.infra.Paf;
import com.t2ti.pafecf.vo.DAVCabecalhoVO;
import com.t2ti.pafecf.vo.DAVDetalheVO;
import com.t2ti.pafecf.vo.EmpresaVO;
import com.t2ti.pafecf.vo.ImpressoraVO;
import com.t2ti.plugins.paf.PafD;
import com.t2ti.plugins.paf.pafd.RegistroD2;
import com.t2ti.plugins.paf.pafd.RegistroD3;
import com.t2ti.plugins.util.RegistroEAD;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class DAVEmitidos extends javax.swing.JDialog {

    public DAVEmitidos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        defineFormatoData();

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelFiltro.setBackground(new Color(r, g, b));
        panelPeriodo.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        MenuFiscalAction menuFiscalAction = new MenuFiscalAction(this);
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "menuFiscal");
        panelPrincipal.getActionMap().put("menuFiscal", menuFiscalAction);

        this.setPreferredSize(new Dimension(500, 170));
        this.pack();
    }

    private void defineFormatoData() {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            DefaultFormatterFactory formatter = new DefaultFormatterFactory(mascara);
            dataFinal.setFormatterFactory(formatter);
            dataInicial.setFormatterFactory(formatter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelFiltro = new javax.swing.JPanel();
        radioGerencial = new javax.swing.JRadioButton();
        radioArquivo = new javax.swing.JRadioButton();
        panelPeriodo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dataInicial = new javax.swing.JFormattedTextField();
        dataFinal = new javax.swing.JFormattedTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DAV Emitidos");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaRegistradora01.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelFiltro.setBackground(new Color(255,255,255,0));
        panelFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Filtro:"));
        panelFiltro.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(radioGerencial);
        radioGerencial.setSelected(true);
        radioGerencial.setText("Relatório Gerencial");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panelFiltro.add(radioGerencial, gridBagConstraints);

        buttonGroup1.add(radioArquivo);
        radioArquivo.setText("Geração de Arquivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panelFiltro.add(radioArquivo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelFiltro, gridBagConstraints);

        panelPeriodo.setBackground(new Color(255,255,255,0));
        panelPeriodo.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Data inicial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelPeriodo.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Data final:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelPeriodo.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        panelPeriodo.add(dataInicial, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        panelPeriodo.add(dataFinal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelPeriodo, gridBagConstraints);

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
        gridBagConstraints.gridy = 0;
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoCancela, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

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
        validaPeriodo();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DAVEmitidos dialog = new DAVEmitidos(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField dataFinal;
    private javax.swing.JFormattedTextField dataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelPeriodo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JRadioButton radioArquivo;
    private javax.swing.JRadioButton radioGerencial;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            validaPeriodo();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public void validaPeriodo() {
        try {
            dataInicial.commitEdit();
            dataFinal.commitEdit();
            if (!Biblioteca.isDataValida(dataInicial.getText())) {
                JOptionPane.showMessageDialog(this, "Data inicial inválida!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                dataInicial.requestFocus();
            } else if (!Biblioteca.isDataValida(dataFinal.getText())) {
                JOptionPane.showMessageDialog(this, "Data final inválida!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                dataFinal.requestFocus();
            } else {
                confirma();
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Problemas com os valores informados!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirma() {
        DAVController davControl = new DAVController();
        String numero, dataEmissao, titulo, valor = "";

        SimpleDateFormat formataData;
        Date dataIni = null;
        Date dataFim = null;
        try {
            formataData = new SimpleDateFormat("dd/MM/yyyy");
            dataIni = formataData.parse((String) dataInicial.getValue());
            dataFim = formataData.parse((String) dataFinal.getValue());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        //relatorio gerencial
        if (radioGerencial.isSelected()) {
            String[] opcoes = {"Sim", "Não"};
            int escolha = JOptionPane.showOptionDialog(null, "Deseja imprimir o relatório DAV EMITIDOS?", "Pergunta do Sistema",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, opcoes, null);
            if (escolha == JOptionPane.YES_OPTION) {
                //formata a data para realizar o filtro
                formataData = new SimpleDateFormat("yyyy-MM-dd");
                String strDataIni = formataData.format(dataIni);
                String strDataFim = formataData.format(dataFim);

                List<DAVCabecalhoVO> listaDAV = davControl.listaDAVPeriodo(strDataIni, strDataFim);
                if (listaDAV != null) {
                    try {
                        Caixa.aCBrECF.abreRelatorioGerencial(0);
                        Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
                        Caixa.aCBrECF.linhaRelatorioGerencial("DAV EMITIDOS", 0);
                        Caixa.aCBrECF.linhaRelatorioGerencial("PERIODO: " + strDataIni + " A " + strDataFim, 0);
                        Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
                        Caixa.aCBrECF.linhaRelatorioGerencial("NUMERO      DATA EMISSAO TITULO       VALOR    ", 0);
                        Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
                        for (int i = 0; i < listaDAV.size(); i++) {
                            numero = listaDAV.get(i).getNumeroDav() + "  ";
                            dataEmissao = listaDAV.get(i).getDataEmissao().toString() + "   ";
                            titulo = "ORCAMENTO ";
                            NumberFormat formatter = new DecimalFormat("0.00");
                            valor = formatter.format(listaDAV.get(i).getValor());
                            valor = Biblioteca.repete(" ", 13 - valor.length()) + valor;
                            Caixa.aCBrECF.linhaRelatorioGerencial(numero + dataEmissao + titulo + valor, 0);
                        }
                        Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
                        Caixa.aCBrECF.fechaRelatorio();

                        Paf.gravaR06("RG");
                    } catch (Throwable t) {
                        t.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Não existe DAV para o período informado.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //geração de arquivo
        // TODO: -verifique com calma se o procedimento abaixo está correto. Confira o layout. Ajuste o que for necessário.
        if (radioArquivo.isSelected()) {
            String[] opcoes = {"Sim", "Não"};
            int escolha = JOptionPane.showOptionDialog(null, "Deseja gerar o arquivo de DAV EMITIDOS?", "Pergunta do Sistema",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, opcoes, null);
            if (escolha == 0) {
                PafD pafD = new PafD();
                try {
                    formataData = new SimpleDateFormat("yyyy-MM-dd");
                    String strDataIni = formataData.format(dataIni);
                    String strDataFim = formataData.format(dataFim);

                    EmpresaVO empresa = new EmpresaController().pegaEmpresa(Caixa.configuracao.getIdEmpresa());
                    ImpressoraVO impressora = new ImpressoraController().pegaImpressora(Caixa.configuracao.getIdImpressora());

                    List<DAVCabecalhoVO> listaDAV = davControl.listaDAVPeriodo(strDataIni, strDataFim);
                    if (listaDAV != null) {
                        String tripa = "";
                        //Registro D1
                        pafD.getRegistroD().getRegistroD1().setCnpj(empresa.getCnpj());
                        pafD.getRegistroD().getRegistroD1().setInscricaoEstadual(empresa.getInscricaoEstadual());
                        pafD.getRegistroD().getRegistroD1().setInscricaoMunicipal(empresa.getInscricaoMunicipal());
                        pafD.getRegistroD().getRegistroD1().setRazaoSocial(empresa.getRazaoSocial());

                        //Registro D9
                        pafD.getRegistroD().getRegistroD9().setCnpj(empresa.getCnpj());
                        pafD.getRegistroD().getRegistroD9().setInscricaoEstadual(empresa.getInscricaoEstadual());
                        
                        for (int i = 0; i < listaDAV.size() - 1; i++) {
                            tripa = ""
                                    + listaDAV.get(i).getId()
                                    + listaDAV.get(i).getIdPessoa()
                                    + listaDAV.get(i).getCcf()
                                    + listaDAV.get(i).getCoo()
                                    + listaDAV.get(i).getNomeDestinatario()
                                    + listaDAV.get(i).getCpfCnpjDestinatario()
                                    + formataData.format(listaDAV.get(i).getDataEmissao())
                                    + listaDAV.get(i).getHoraEmissao()
                                    + listaDAV.get(i).getSituacao()
                                    + Biblioteca.formatoDecimal("V", listaDAV.get(i).getTaxaAcrescimo())
                                    + Biblioteca.formatoDecimal("V", listaDAV.get(i).getAcrescimo())
                                    + Biblioteca.formatoDecimal("V", listaDAV.get(i).getTaxaDesconto())
                                    + Biblioteca.formatoDecimal("V", listaDAV.get(i).getDesconto())
                                    + Biblioteca.formatoDecimal("V", listaDAV.get(i).getSubTotal())
                                    + Biblioteca.formatoDecimal("V", listaDAV.get(i).getValor())
                                    + listaDAV.get(i).getNumeroDav()
                                    + listaDAV.get(i).getNumeroEcf()
                                    + "0";

                            RegistroD2 registroD2 = new RegistroD2();
                            registroD2.setCnpjEstabelecimento(empresa.getCnpj());
                            registroD2.setNumeroFabricacao(impressora.getSerie());
                            registroD2.setMfAdicional(impressora.getMfd());
                            registroD2.setTipoEcf(impressora.getTipo());
                            registroD2.setMarcaEcf(impressora.getMarca());
                            if (Biblioteca.MD5String(tripa).equals(listaDAV.get(i).getHashTripa())) {
                                registroD2.setModeloEcf(impressora.getModelo());
                            } else {
                                registroD2.setModeloEcf(Biblioteca.repete("?", 20));
                            }
                            registroD2.setCoo(listaDAV.get(i).getCoo() == null ? "" : listaDAV.get(i).getCoo().toString());
                            registroD2.setNumeroFabricacao(listaDAV.get(i).getNumeroEcf());
                            registroD2.setNomeAdquirente(listaDAV.get(i).getNomeDestinatario());
                            registroD2.setCpfCnpjAdquirente(listaDAV.get(i).getCpfCnpjDestinatario());
                            registroD2.setNumeroDav(listaDAV.get(i).getNumeroDav());
                            registroD2.setDataDav(listaDAV.get(i).getDataEmissao());
                            registroD2.setTituloDav("ORCAMENTO");
                            registroD2.setValorTotalDav(listaDAV.get(i).getValor());

                            pafD.getRegistroD().getListaRegistroD2().add(registroD2);

                            List<DAVDetalheVO> listaDavDetalhe = davControl.listaDavDetalhe(listaDAV.get(i).getId());
                            for (int j = 0; j < listaDavDetalhe.size() - 1; j++) {
                                tripa = ""
                                        + listaDavDetalhe.get(j).getId()
                                        + listaDavDetalhe.get(j).getIdDavCabecalho()
                                        + listaDavDetalhe.get(j).getIdProduto()
                                        + listaDavDetalhe.get(j).getNumeroDav()
                                        + formataData.format(listaDavDetalhe.get(j).getDataEmissao())
                                        + listaDavDetalhe.get(j).getItem()
                                        + Biblioteca.formatoDecimal("Q", listaDavDetalhe.get(j).getQuantidade())
                                        + Biblioteca.formatoDecimal("V", listaDavDetalhe.get(j).getValorUnitario())
                                        + Biblioteca.formatoDecimal("V", listaDavDetalhe.get(j).getValorTotal())
                                        + listaDavDetalhe.get(j).getCancelado()
                                        + listaDavDetalhe.get(j).getMesclaProduto()
                                        + listaDavDetalhe.get(j).getGtinProduto()
                                        + listaDavDetalhe.get(j).getNomeProduto()
                                        + listaDavDetalhe.get(j).getTotalizadorParcial()
                                        + listaDavDetalhe.get(j).getUnidadeProduto()
                                        + "0";

                                RegistroD3 registroD3 = new RegistroD3();
                                registroD3.setNumeroDav(listaDAV.get(i).getNumeroDav());
                                registroD3.setDataInclusao(listaDavDetalhe.get(j).getDataEmissao());
                                registroD3.setNumeroItem(listaDavDetalhe.get(j).getItem().toString());
                                registroD3.setCodigoProduto(listaDavDetalhe.get(j).getGtinProduto());
                                if (Biblioteca.MD5String(tripa).equals(listaDavDetalhe.get(j).getHashTripa())) {
                                    registroD3.setDescricaoProduto(listaDavDetalhe.get(j).getNomeProduto());
                                } else {
                                    registroD3.setDescricaoProduto(Biblioteca.repete("?", 100));
                                }
                                registroD3.setQuantidade(listaDavDetalhe.get(j).getQuantidade());
                                registroD3.setUnidade(listaDavDetalhe.get(j).getUnidadeProduto());
                                registroD3.setValorUnitario(listaDavDetalhe.get(j).getValorUnitario());
                                registroD3.setValorDesconto(0D);
                                registroD3.setValorAcrescimo(0D);
                                registroD3.setValorTotalLiquido(listaDavDetalhe.get(j).getValorTotal());
                                registroD3.setIndicadorCancelamento(listaDavDetalhe.get(j).getCancelado());
                                registroD3.setCasasDecimaisQuantidade(3);
                                registroD3.setCasasDecimaisValorUnitario(2);

                                pafD.getRegistroD().getListaRegistroD3().add(registroD3);
                            }
                        }
                    }

                    try {
                        // TODO: -Gere o arquivo com o nome exigido pela espeficicação
                        pafD.geraArquivoTxt(new File("PAF_D.txt"));
                        RegistroEAD.assinarArquivo("PAF_D.txt");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // TODO: -formate a mensagem de acordo com as exigências da especificação de requisitos
                    JOptionPane.showMessageDialog(null, "Arquivo Gerado com Sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Não existe DAV para o período informado.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
