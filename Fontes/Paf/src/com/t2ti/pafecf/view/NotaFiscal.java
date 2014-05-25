/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Janela digitação de notas fiscais</p>
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

import com.t2ti.pafecf.controller.EmpresaController;
import com.t2ti.pafecf.controller.NotaFiscalController;
import com.t2ti.pafecf.controller.ProdutoController;
import com.t2ti.pafecf.controller.VendedorController;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.MenuFiscalAction;
import com.t2ti.pafecf.infra.NotaFiscalColumnModel;
import com.t2ti.pafecf.infra.NotaFiscalTableModel;
import com.t2ti.pafecf.vo.ClienteVO;
import com.t2ti.pafecf.vo.EmpresaVO;
import com.t2ti.pafecf.vo.FuncionarioVO;
import com.t2ti.pafecf.vo.NotaFiscalCabecalhoVO;
import com.t2ti.pafecf.vo.NotaFiscalDetalheVO;
import com.t2ti.pafecf.vo.ProdutoVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class NotaFiscal extends javax.swing.JDialog {

    private ClienteVO cliente;
    private Double desconto;
    private Double valorTotal;
    private Double subTotal;
    private ProdutoController produtoControl;
    private List<NotaFiscalDetalheVO> listaNotaDetalhe;
    private int item;
    private Date dataNota;
    private String horaNota;
    private List<FuncionarioVO> listaFuncionario;

    public NotaFiscal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        configuraEdits();
        desconto = 0.0;
        valorTotal = 0.0;
        subTotal = 0.0;
        produtoControl = new ProdutoController();
        listaNotaDetalhe = new ArrayList<NotaFiscalDetalheVO>();
        item = 0;
        importaVendedores();

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelDadosNota.setBackground(new Color(r, g, b));
        panelGridProdutos.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));

        //configuraGridCabecalho(preVendaControl.listaPreVendaPendente());

        CancelaAction cancelaAction = new CancelaAction();
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        panelPrincipal.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        panelPrincipal.getActionMap().put("confirmaAction", confirmaAction);

        DescontoAction descontoAction = new DescontoAction();
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "descontoAction");
        panelPrincipal.getActionMap().put("descontoAction", descontoAction);

        CancelaItemAction cancelaItemAction = new CancelaItemAction();
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "cancelaItemAction");
        panelPrincipal.getActionMap().put("cancelaItemAction", cancelaItemAction);

        ClienteAction clienteAction = new ClienteAction();
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "clienteAction");
        panelPrincipal.getActionMap().put("clienteAction", clienteAction);

        ProdutoAction produtoAction = new ProdutoAction();
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "produtoAction");
        panelPrincipal.getActionMap().put("produtoAction", produtoAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridProdutos.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        MenuFiscalAction menuFiscalAction = new MenuFiscalAction(this);
        panelBotoes.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "menuFiscal");
        panelBotoes.getActionMap().put("menuFiscal", menuFiscalAction);

        this.setPreferredSize(new Dimension(750, 600));
        this.pack();
    }

    private void configuraEdits() {
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            DefaultFormatterFactory formatterData = new DefaultFormatterFactory(mascaraData);
            editDataEmissao.setFormatterFactory(formatterData);

            MaskFormatter mascaraHora = new MaskFormatter("##:##:##");
            DefaultFormatterFactory formatterHora = new DefaultFormatterFactory(mascaraHora);
            editHoraEmissao.setFormatterFactory(formatterHora);

            MaskFormatter mascaraNumero = new MaskFormatter("######");
            DefaultFormatterFactory formatterNumero = new DefaultFormatterFactory(mascaraNumero);
            editNumero.setFormatterFactory(formatterNumero);

            editQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            editQuantidade.setText("1,000");
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
        panelDadosNota = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        editNumero = new javax.swing.JFormattedTextField();
        comboVendedor = new javax.swing.JComboBox();
        editDataEmissao = new javax.swing.JFormattedTextField();
        editHoraEmissao = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        editNomeDestinatario = new javax.swing.JTextField();
        editCpfCnpj = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        editCodigoProduto = new javax.swing.JTextField();
        editQuantidade = new javax.swing.JTextField();
        panelBotoes = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        panelGridProdutos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridProdutos = new javax.swing.JTable();
        panelResumo = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        labelSubTotal = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        labelDesconto = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emissão de Nota Fiscal");
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

        panelDadosNota.setBackground(new Color(255,255,255,0));
        panelDadosNota.setBorder(javax.swing.BorderFactory.createTitledBorder("Digitação de Notas Fiscais:"));
        panelDadosNota.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Número:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Vendedor / Funcionario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Data Emissão:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Hora Emissão:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editNumero, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(comboVendedor, gridBagConstraints);

        editDataEmissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editDataEmissaoFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editDataEmissao, gridBagConstraints);

        editHoraEmissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editHoraEmissaoFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editHoraEmissao, gridBagConstraints);

        jLabel6.setText("Nome Destinatário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel6, gridBagConstraints);

        jLabel8.setText("CPF/CNPJ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel8, gridBagConstraints);

        editNomeDestinatario.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editNomeDestinatario, gridBagConstraints);

        editCpfCnpj.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editCpfCnpj, gridBagConstraints);

        jLabel7.setText("Código Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel7, gridBagConstraints);

        jLabel9.setText("Quantidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDadosNota.add(jLabel9, gridBagConstraints);

        editCodigoProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                editCodigoProdutoFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editCodigoProduto, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelDadosNota.add(editQuantidade, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelDadosNota, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoCliente.png"))); // NOI18N
        jLabel12.setText("F1 - Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel12, gridBagConstraints);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoCancelaItem.png"))); // NOI18N
        jLabel13.setText("F8 - Cancela Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel13, gridBagConstraints);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoDesconto.png"))); // NOI18N
        jLabel14.setText("F10 - Desconto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel14, gridBagConstraints);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        jLabel15.setText("F12 - Gravar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel15, gridBagConstraints);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoCancelar.png"))); // NOI18N
        jLabel16.setText("ESC - Sair");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel16, gridBagConstraints);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoPesquisa.png"))); // NOI18N
        jLabel21.setText("F6 - Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelBotoes.add(jLabel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        panelGridProdutos.setBackground(new Color(255,255,255,0));
        panelGridProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos Lançados:"));
        panelGridProdutos.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(452, 150));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 150));

        gridProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(gridProdutos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGridProdutos.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelGridProdutos, gridBagConstraints);

        panelResumo.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumo da Operação:"));
        panelResumo.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("SubTotal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumo.add(jLabel10, gridBagConstraints);

        labelSubTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelSubTotal.setText("0,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumo.add(labelSubTotal, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumo.add(jLabel17, gridBagConstraints);

        labelDesconto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelDesconto.setText("0,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumo.add(labelDesconto, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumo.add(jLabel19, gridBagConstraints);

        labelTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTotal.setText("0,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumo.add(labelTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelResumo, gridBagConstraints);

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

    private void editCodigoProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editCodigoProdutoFocusLost
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                insereItem();
            }
        });
    }//GEN-LAST:event_editCodigoProdutoFocusLost

    private void editHoraEmissaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editHoraEmissaoFocusGained
        Date dataAtual = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        editHoraEmissao.setText(formatoHora.format(dataAtual));
    }//GEN-LAST:event_editHoraEmissaoFocusGained

    private void editDataEmissaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editDataEmissaoFocusGained
        Date dataAtual = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        editDataEmissao.setText(formatoData.format(dataAtual));
    }//GEN-LAST:event_editDataEmissaoFocusGained

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                NotaFiscal dialog = new NotaFiscal(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboVendedor;
    private javax.swing.JTextField editCodigoProduto;
    private javax.swing.JFormattedTextField editCpfCnpj;
    private javax.swing.JFormattedTextField editDataEmissao;
    private javax.swing.JFormattedTextField editHoraEmissao;
    private javax.swing.JTextField editNomeDestinatario;
    private javax.swing.JFormattedTextField editNumero;
    private javax.swing.JTextField editQuantidade;
    private javax.swing.JTable gridProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelSubTotal;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelDadosNota;
    private javax.swing.JPanel panelGridProdutos;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelResumo;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            gravaNota();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private class DescontoAction extends AbstractAction {

        public DescontoAction() {
        }

        public void actionPerformed(ActionEvent e) {
            desconto();
        }
    }

    private class CancelaItemAction extends AbstractAction {

        public CancelaItemAction() {
        }

        public void actionPerformed(ActionEvent e) {
            //gravaNota();
        }
    }

    private class ClienteAction extends AbstractAction {

        public ClienteAction() {
        }

        public void actionPerformed(ActionEvent e) {
            identificaCliente();
        }
    }

    private class ProdutoAction extends AbstractAction {

        public ProdutoAction() {
        }

        public void actionPerformed(ActionEvent e) {
            importaProduto();
        }
    }

    private void importaVendedores() {
        VendedorController vendedorControl = new VendedorController();
        listaFuncionario = vendedorControl.consultaVendedores();
        for (int i = 0; i < listaFuncionario.size(); i++) {
            comboVendedor.addItem(listaFuncionario.get(i).getNome());
        }
    }

    private void identificaCliente() {
        ImportaCliente janelaImportaCliente = new ImportaCliente(this, true);
        janelaImportaCliente.setLocationRelativeTo(null);
        janelaImportaCliente.setVisible(true);
        if (!janelaImportaCliente.cancelado) {
            cliente = janelaImportaCliente.getCliente();
            if (cliente != null) {
                editNomeDestinatario.setText(cliente.getNome());
                editCpfCnpj.setText(cliente.getCpfOuCnpj());
            }
        }
    }

    private void importaProduto() {
        editCodigoProduto.requestFocus();
        ImportaProduto janelaImportaProduto = new ImportaProduto(null, true);
        janelaImportaProduto.setLocationRelativeTo(null);
        janelaImportaProduto.setVisible(true);
        editCodigoProduto.setText(janelaImportaProduto.getGTIN());
        editCodigoProduto.requestFocus();
    }

    private void desconto() {
        ValorReal janelaDesconto = new ValorReal(null, true);
        janelaDesconto.setLocationRelativeTo(null);
        janelaDesconto.setVisible(true);
        desconto = Double.valueOf(janelaDesconto.retornaValor());
        if (desconto < 0) {
            JOptionPane.showMessageDialog(this, "O desconto não pode ser um valor negativo!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            desconto = 0.0;
        } else if (desconto.compareTo(valorTotal) == 1) {
            JOptionPane.showMessageDialog(this, "O desconto não pode ser maior que o valor da nota!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            desconto = 0.0;
        }
        atualizaTotais();
    }

    private void atualizaTotais() {
        subTotal = 0.0;
        for (int i = 0; i < listaNotaDetalhe.size(); i++) {
            subTotal = subTotal + listaNotaDetalhe.get(i).getValorTotal();
        }

        valorTotal = subTotal;
        valorTotal = valorTotal - desconto;

        labelSubTotal.setText(Biblioteca.formatoDecimal("V", subTotal.doubleValue()));
        labelDesconto.setText(Biblioteca.formatoDecimal("V", desconto.doubleValue()));
        labelTotal.setText(Biblioteca.formatoDecimal("V", valorTotal.doubleValue()));
    }

    private void insereItem() {
        if (!editCodigoProduto.getText().trim().equals("")) {
            ProdutoVO produto = produtoControl.consulta(editCodigoProduto.getText().trim());
            if (produto != null) {
                if (verificaQuantidade(produto.getUnidadeProdutoVO().getPodeFracionar())) {
                    NotaFiscalDetalheVO notaDetalhe = new NotaFiscalDetalheVO();
                    item++;
                    notaDetalhe.setIdProduto(produto.getId());
                    notaDetalhe.setValorUnitario(Double.valueOf(produto.getValorVenda()));
                    notaDetalhe.setItem(item);

                    Double quantidade = Double.valueOf(Double.valueOf(editQuantidade.getText().replace(",", ".")));
                    Double valorTotalItem = quantidade * notaDetalhe.getValorUnitario();
                    notaDetalhe.setQuantidade(quantidade);
                    notaDetalhe.setValorTotal(valorTotalItem);

                    notaDetalhe.setSincronizado("N");
                    notaDetalhe.setTaxaIcms(Double.valueOf(produto.getTaxaIcms()));
                    notaDetalhe.setTaxaIssqn(Double.valueOf(produto.getTaxaIssqn()));
                    notaDetalhe.setTaxaPis(Double.valueOf(produto.getTaxaPis()));
                    notaDetalhe.setTaxaCofins(Double.valueOf(produto.getTaxaCofins()));
                    notaDetalhe.setTaxaIpi(Double.valueOf(produto.getTaxaIpi()));
                    notaDetalhe.setTaxaAcrescimo(0.0);
                    notaDetalhe.setAcrescimo(0.0);
                    notaDetalhe.setTaxaDesconto(0.0);
                    notaDetalhe.setDesconto(0.0);
                    notaDetalhe.setCfop(Caixa.configuracao.getCfopNf2());
                    notaDetalhe.setCst(produto.getCst());
                    notaDetalhe.setBaseIcms(Double.valueOf(produto.getTaxaIcms()));
                    notaDetalhe.setIcms(0.0);
                    notaDetalhe.setIcmsOutras(0.0);
                    notaDetalhe.setIcmsIsento(0.0);
                    notaDetalhe.setIssqn(0.0);
                    notaDetalhe.setCofins(0.0);
                    notaDetalhe.setPis(0.0);
                    notaDetalhe.setIpi(0.0);
                    notaDetalhe.setCancelado("N");
                    notaDetalhe.setEcfIcmsSt(produto.getEcfIcmsSt());
                    notaDetalhe.setMovimentaEstoque("S");
                    notaDetalhe.setGtinProduto(produto.getGtin());
                    notaDetalhe.setDescricaoProduto(produto.getDescricaoPDV());

                    listaNotaDetalhe.add(notaDetalhe);
                    editCodigoProduto.setText("");
                    editQuantidade.setText("1,000");
                    //editCodigoProduto.requestFocus();
                    atualizaTotais();
                    configuraGridDetalhe();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Código do produto não encontrado!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        editCodigoProduto.requestFocus();
                    }
                });
            }
        }
    }

    private boolean verificaQuantidade(String podeFracionar) {
        try {
            if (podeFracionar.equals("N")) {
                String strQtde = editQuantidade.getText().replace(",", ".");
                double qtde = Double.valueOf(strQtde);
                if (qtde % 1 != 0) {
                    JOptionPane.showMessageDialog(this, "Este produto não pode ser vendido em quantidade fracionada!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void gravaNota() {
        NotaFiscalController notaControl = new NotaFiscalController();
        if (notaControl.consultaNota((String) editNumero.getValue()) != null) {
            JOptionPane.showMessageDialog(this, "Este número de nota já foi gravado!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            editNumero.requestFocus();
        } else if (validaInformacoes()) {
            NotaFiscalCabecalhoVO notaFiscalCabecalho = new NotaFiscalCabecalhoVO();
            EmpresaController empresaControl = new EmpresaController();
            EmpresaVO empresa = empresaControl.pegaEmpresa(Caixa.configuracao.getIdEmpresa());

            notaFiscalCabecalho.setTotalNf(valorTotal);
            notaFiscalCabecalho.setTotalProdutos(valorTotal);
            notaFiscalCabecalho.setDataEmissao(dataNota);
            notaFiscalCabecalho.setHoraEmissao(horaNota);
            notaFiscalCabecalho.setCancelada("N");
            notaFiscalCabecalho.setSincronizado("N");
            notaFiscalCabecalho.setTipoNota("2");
            notaFiscalCabecalho.setSerie("D");
            notaFiscalCabecalho.setSubserie("");
            notaFiscalCabecalho.setTaxaAcrescimo(0.0);
            notaFiscalCabecalho.setAcrescimo(0.0);
            notaFiscalCabecalho.setAcrescimoItens(0.0);
            notaFiscalCabecalho.setTaxaDesconto(0.0);
            notaFiscalCabecalho.setDesconto(desconto);
            notaFiscalCabecalho.setDescontoItens(0.0);
            if (cliente.getUf().equals(empresa.getUf())) {
                notaFiscalCabecalho.setCfop(5102);
            } else {
                notaFiscalCabecalho.setCfop(Caixa.configuracao.getCfopNf2());
            }
            notaFiscalCabecalho.setIdCliente(cliente.getId());
            notaFiscalCabecalho.setIdFuncionario(listaFuncionario.get(comboVendedor.getSelectedIndex()).getId());
            notaFiscalCabecalho.setNumero(editNumero.getText());
            notaFiscalCabecalho.setBaseIcms(0.0);
            notaFiscalCabecalho.setIcms(0.0);
            notaFiscalCabecalho.setIcmsOutras(0.0);
            notaFiscalCabecalho.setIssqn(0.0);
            notaFiscalCabecalho.setPis(0.0);
            notaFiscalCabecalho.setCofins(0.0);
            notaFiscalCabecalho.setIpi(0.0);

            if (notaControl.gravaNota(notaFiscalCabecalho, listaNotaDetalhe)) {
                JOptionPane.showMessageDialog(this, "Nota Fiscal gravada com sucesso!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível gravar a nota!", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validaInformacoes() {
        try {
            if (listaNotaDetalhe.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há itens na lista!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "É necessário identificar um cliente!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            if (editNumero.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Informe o número da nota!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                editNumero.requestFocus();
                return false;
            }
            if (editDataEmissao.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Informe a data de emissão", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                editDataEmissao.requestFocus();
                return false;
            } else {
                if (Biblioteca.isDataValida(editDataEmissao.getText())) {
                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                    dataNota = formatoData.parse(editDataEmissao.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Data de emissão inválida", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                }
            }
            if (editHoraEmissao.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Informe a hora de emissão!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                editHoraEmissao.requestFocus();
                return false;
            } else {
                horaNota = editHoraEmissao.getText();
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void configuraGridDetalhe() {
        gridProdutos.setModel(new NotaFiscalTableModel(listaNotaDetalhe));
        gridProdutos.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridDetalhe";
            }
        });

        gridProdutos.setAutoCreateColumnsFromModel(false);
        gridProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridProdutos.getFontMetrics(gridProdutos.getFont());
        gridProdutos.setColumnModel(new NotaFiscalColumnModel(fm));
    }
}
