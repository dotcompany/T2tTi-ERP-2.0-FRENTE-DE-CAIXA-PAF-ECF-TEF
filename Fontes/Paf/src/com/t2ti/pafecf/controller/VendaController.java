/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da venda</p>
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
package com.t2ti.pafecf.controller;

import com.t2ti.pafecf.bd.AcessoBanco;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.Ecf;
import com.t2ti.pafecf.infra.Paf;
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.vo.VendaCabecalhoVO;
import com.t2ti.pafecf.vo.VendaDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public VendaCabecalhoVO iniciaVenda(VendaCabecalhoVO pVendaCabecalho) {
        consultaSQL =
                "insert into ECF_VENDA_CABECALHO ("
                + "STATUS_VENDA,"
                + "ID_ECF_MOVIMENTO,"
                + "CFOP,"
                + "COO,"
                + "CCF,"
                + "SERIE_ECF,"
                + "ID_CLIENTE,"
                + "NOME_CLIENTE,"
                + "CPF_CNPJ_CLIENTE,"
                + "DATA_VENDA,"
                + "HORA_VENDA) values ("
                + "?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, pVendaCabecalho.getStatusVenda());
            pstm.setInt(2, pVendaCabecalho.getIdMovimento());
            pstm.setInt(3, pVendaCabecalho.getCfop());
            pstm.setInt(4, pVendaCabecalho.getCoo());
            pstm.setInt(5, pVendaCabecalho.getCcf());
            pstm.setString(6, pVendaCabecalho.getSerieEcf());
            if (pVendaCabecalho.getIdCliente() != null) {
                pstm.setInt(7, pVendaCabecalho.getIdCliente());
            } else {
                pstm.setNull(7, java.sql.Types.INTEGER);
            }
            if (pVendaCabecalho.getNomeCliente() != null) {
                pstm.setString(8, pVendaCabecalho.getNomeCliente());
            } else {
                pstm.setNull(8, java.sql.Types.VARCHAR);
            }
            if (pVendaCabecalho.getCpfCnpjCliente() != null) {
                pstm.setString(9, pVendaCabecalho.getCpfCnpjCliente());
            } else {
                pstm.setNull(9, java.sql.Types.VARCHAR);
            }
            pstm.setDate(10, new java.sql.Date(pVendaCabecalho.getDataVenda().getTime()));
            pstm.setString(11, pVendaCabecalho.getHoraVenda());
            pstm.executeUpdate();

            try {
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from ECF_VENDA_CABECALHO");
                rs.first();
                pVendaCabecalho.setId(rs.getInt("ID"));
                return pVendaCabecalho;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public VendaDetalheVO inserirItem(VendaDetalheVO pVendaDetalhe) {

        consultaSQL =
                "insert into ECF_VENDA_DETALHE ("
                + "CFOP,"
                + "ID_ECF_PRODUTO,"
                + "ID_ECF_VENDA_CABECALHO,"
                + "ITEM,"
                + "QUANTIDADE,"
                + "VALOR_UNITARIO,"
                + "VALOR_TOTAL,"
                + "TOTAL_ITEM,"
                + "TOTALIZADOR_PARCIAL,"
                + "CST,"
                + "MOVIMENTA_ESTOQUE,"
                + "GTIN, "
                + "CCF, "
                + "COO, "
                + "SERIE_ECF, "
                + "ECF_ICMS_ST, "
                + "CANCELADO, "
                + "TAXA_ICMS, "
                + "ICMS, "
                + "BASE_ICMS, "
                + "HASH_TRIPA, "
                + "HASH_INCREMENTO) values ("
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, pVendaDetalhe.getCfop());
            pstm.setInt(2, pVendaDetalhe.getIdProduto());
            pstm.setInt(3, pVendaDetalhe.getIdVendaCabecalho());
            pstm.setInt(4, pVendaDetalhe.getItem());
            pstm.setDouble(5, pVendaDetalhe.getQuantidade());
            pstm.setDouble(6, pVendaDetalhe.getValorUnitario());
            pstm.setDouble(7, pVendaDetalhe.getValorTotal());
            pstm.setDouble(8, pVendaDetalhe.getValorTotal());
            pstm.setString(9, pVendaDetalhe.getTotalizadorParcial());
            pstm.setString(10, pVendaDetalhe.getCst());
            pstm.setString(11, pVendaDetalhe.getMovimentaEstoque());
            pstm.setString(12, pVendaDetalhe.getGtin());
            pstm.setInt(13, pVendaDetalhe.getCcf());
            pstm.setInt(14, pVendaDetalhe.getCoo());
            pstm.setString(15, pVendaDetalhe.getSerieEcf());

            if (pVendaDetalhe.getEcfIcmsSt().equals("NN")) {
                pstm.setString(16, "N");
            } else if (pVendaDetalhe.getEcfIcmsSt().equals("FF")) {
                pstm.setString(16, "F");
            } else if (pVendaDetalhe.getEcfIcmsSt().equals("II")) {
                pstm.setString(16, "I");
            } else {
                if (pVendaDetalhe.getTotalizadorParcial().substring(2, 3).equals("S")) {
                    pstm.setString(16, pVendaDetalhe.getTotalizadorParcial().substring(3, 7));
                } else if (pVendaDetalhe.getTotalizadorParcial().substring(2, 3).equals("T")) {
                    pstm.setString(16, pVendaDetalhe.getTotalizadorParcial().substring(3, 7));
                } else if (pVendaDetalhe.getTotalizadorParcial().equals("Can-T")) {
                    pstm.setString(16, "CANC");
                } else {
                    pstm.setString(16, "1700");
                }
            }

            pstm.setString(17, "N");

            if ((!pVendaDetalhe.getEcfIcmsSt().equals("II")) && (!pVendaDetalhe.getEcfIcmsSt().equals("NN"))) {
                pstm.setDouble(18, pVendaDetalhe.getTaxaIcms());
            } else {
                pstm.setDouble(18, 0.0);
                pVendaDetalhe.setTaxaIcms(0.0);
            }

            BigDecimal icms = BigDecimal.ZERO;
            icms = BigDecimal.valueOf(pVendaDetalhe.getValorTotal()).multiply(BigDecimal.valueOf(pVendaDetalhe.getTaxaIcms())).divide(BigDecimal.valueOf(100));
            icms = icms.setScale(Caixa.configuracao.getDecimaisValor(), RoundingMode.DOWN);
            pVendaDetalhe.setIcms(icms.doubleValue());
            pVendaDetalhe.setBaseIcms(pVendaDetalhe.getValorTotal());

            pstm.setDouble(19, pVendaDetalhe.getIcms());
            pstm.setDouble(20, pVendaDetalhe.getBaseIcms());

            String tripa = Caixa.configuracao.getImpressoraVO().getSerie()
                    + pVendaDetalhe.getCoo()
                    + pVendaDetalhe.getCcf()
                    + pVendaDetalhe.getGtin()
                    + Biblioteca.formatoDecimal("Q", pVendaDetalhe.getQuantidade())
                    + Biblioteca.formatoDecimal("V", pVendaDetalhe.getValorUnitario())
                    + Biblioteca.formatoDecimal("V", pVendaDetalhe.getTotalItem())
                    + pVendaDetalhe.getTotalizadorParcial() + "N" + "0";

            pVendaDetalhe.setHashTripa(Biblioteca.MD5String(tripa));
            pstm.setString(21, pVendaDetalhe.getHashTripa());
            pstm.setInt(22, 0);

            pstm.executeUpdate();

            pVendaDetalhe.setTotalItem(pVendaDetalhe.getValorTotal());

            consultaSQL = "select max(id) as id from ecf_venda_detalhe";

            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            if (rs.first()) {
                pVendaDetalhe.setId(rs.getInt("id"));
            }

            return pVendaDetalhe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void encerraVenda(VendaCabecalhoVO pVendaCabecalho) {
        pVendaCabecalho = calculaImpostos(pVendaCabecalho);
        consultaSQL =
                "update ECF_VENDA_CABECALHO set "
                + "VALOR_VENDA=?, "
                + "TOTAL_PRODUTOS=?, "
                + "TOTAL_DOCUMENTO=?, "
                + "BASE_ICMS=?, "
                + "VALOR_FINAL=?, "
                + "VALOR_RECEBIDO=?, "
                + "TAXA_DESCONTO=?, "
                + "DESCONTO=?, "
                + "TAXA_ACRESCIMO=?, "
                + "ACRESCIMO=?, "
                + "TROCO=?, "
                + "ID_ECF_DAV=?, "
                + "ID_ECF_PRE_VENDA_CABECALHO=?, "
                + "STATUS_VENDA=?, "
                + "ID_ECF_FUNCIONARIO=?, "
                + "CUPOM_CANCELADO=?, "
                + "ICMS_OUTRAS=?, "
                + "ICMS=?, "
                + "HASH_TRIPA=?, "
                + "HASH_INCREMENTO=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setDouble(1, pVendaCabecalho.getValorVenda());
            pstm.setDouble(2, pVendaCabecalho.getValorVenda());
            pstm.setDouble(3, pVendaCabecalho.getValorVenda());
            pstm.setDouble(4, pVendaCabecalho.getBaseIcms());
            pstm.setDouble(5, pVendaCabecalho.getValorFinal());
            pstm.setDouble(6, pVendaCabecalho.getValorRecebido());
            pstm.setDouble(7, pVendaCabecalho.getTaxaDesconto());
            pstm.setDouble(8, pVendaCabecalho.getDesconto());
            pstm.setDouble(9, pVendaCabecalho.getTaxaAcrescimo());
            pstm.setDouble(10, pVendaCabecalho.getAcrescimo());
            pstm.setDouble(11, pVendaCabecalho.getTroco());
            if (pVendaCabecalho.getIdDav() != null) {
                pstm.setInt(12, pVendaCabecalho.getIdDav());
                DAVController DAVControl = new DAVController();
                DAVControl.fechaDAV(pVendaCabecalho.getIdDav(), pVendaCabecalho.getCcf(), pVendaCabecalho.getCoo());
            } else {
                pstm.setNull(12, java.sql.Types.INTEGER);
            }
            if (pVendaCabecalho.getIdPreVenda() != null) {
                pstm.setInt(13, pVendaCabecalho.getIdPreVenda());
                PreVendaController preVendaControl = new PreVendaController();
                preVendaControl.fechaPreVenda(pVendaCabecalho.getIdPreVenda(), pVendaCabecalho.getCcf());
            } else {
                pstm.setNull(13, java.sql.Types.INTEGER);
            }
            pstm.setString(14, "F");
            if (pVendaCabecalho.getIdVendedor() != null) {
                pstm.setInt(15, pVendaCabecalho.getIdVendedor());
            } else {
                pstm.setNull(15, java.sql.Types.INTEGER);
            }

            String tripa = String.valueOf(pVendaCabecalho.getId())
                    + String.valueOf(pVendaCabecalho.getCcf())
                    + String.valueOf(pVendaCabecalho.getCoo())
                    + Biblioteca.formatoDecimal("V", pVendaCabecalho.getValorFinal())
                    + pVendaCabecalho.getSerieEcf()
                    + "FN0";

            pVendaCabecalho.setHashTripa(Biblioteca.MD5String(tripa));

            pstm.setString(16, "N");
            pstm.setDouble(17, pVendaCabecalho.getIcmsOutras());
            pstm.setDouble(18, pVendaCabecalho.getIcms());

            pstm.setString(19, pVendaCabecalho.getHashTripa());
            pstm.setInt(20, -1);

            pstm.setInt(21, pVendaCabecalho.getId());
            pstm.executeUpdate();

            Paf.gravaR06("RV");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public List<VendaDetalheVO> vendaAberta() {

        //verifica se existe alguma venda aberta
        consultaSQL =
                "select count(*) as TOTAL from ECF_VENDA_CABECALHO where STATUS_VENDA = 'A'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {

                //verifica se existem itens para a venda aberta
                consultaSQL =
                        "select C.ID as CID, D.ID as DID, C.STATUS_VENDA, D.ID_ECF_PRODUTO, "
                        + "D.QUANTIDADE, D.VALOR_UNITARIO, C.CPF_CNPJ_CLIENTE, "
                        + "D.VALOR_TOTAL, D.CFOP, P.GTIN, P.ID "
                        + "from ECF_VENDA_CABECALHO C LEFT JOIN ECF_VENDA_DETALHE D ON C.ID=D.ID_ECF_VENDA_CABECALHO, PRODUTO P "
                        + "where C.STATUS_VENDA = 'A' and D.CANCELADO is null and D.ID_ECF_PRODUTO=P.ID";

                List<VendaDetalheVO> listaVendaDetalhe = new ArrayList<VendaDetalheVO>();

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    VendaDetalheVO vendaDetalhe = new VendaDetalheVO();
                    vendaDetalhe.setId(rs.getInt("DID"));
                    vendaDetalhe.setIdVendaCabecalho(rs.getInt("CID"));
                    vendaDetalhe.setCfop(rs.getInt("CFOP"));
                    vendaDetalhe.setIdProduto(rs.getInt("ID_ECF_PRODUTO"));
                    vendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    vendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    vendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    vendaDetalhe.setGtin(rs.getString("GTIN"));
                    vendaDetalhe.setIdentificacaoCliente(rs.getString("CPF_CNPJ_CLIENTE"));
                    listaVendaDetalhe.add(vendaDetalhe);
                }
                //caso existam itens, continua com a recuperação da venda
                if (listaVendaDetalhe.size() > 0) {
                    return listaVendaDetalhe;
                } //caso tenha sido aberto um cupom, mas não tenha sido inserido nenhum item
                //altera o status da venda para cancelado e chama o método para cancelamento do cupom
                else {
                    consultaSQL =
                            "update ECF_VENDA_CABECALHO set STATUS_VENDA='C' "
                            + " where STATUS_VENDA = 'A'";
                    try {
                        pstm = bd.conectar().prepareStatement(consultaSQL);
                        pstm.executeUpdate();
                        Ecf.cancelaCupom();
                        return null;
                    } catch (Exception e) {
                        return null;
                    }
                }
                //caso não exista uma venda aberta, retorna um ponteiro nulo
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void cancelaVenda(VendaCabecalhoVO pVendaCabecalho) {
        try {
            Connection con = bd.conectar();
            boolean vendaFechada = false;
            Double valorFinal = null;

            consultaSQL =
                    "select STATUS_VENDA, VALOR_FINAL from ECF_VENDA_CABECALHO "
                    + " where ID = ?";
            pstm = con.prepareStatement(consultaSQL);
            pstm.setInt(1, pVendaCabecalho.getId());
            rs = pstm.executeQuery();
            
            if (rs.first()) {
                if (rs.getString("STATUS_VENDA").equals("F")) {
                    vendaFechada = true;

                }
                valorFinal = rs.getDouble("VALOR_FINAL");
            }

            if (valorFinal == null){
                valorFinal = 0.0;
            }

            consultaSQL =
                    "update ECF_VENDA_CABECALHO set "
                    + "STATUS_VENDA = ?, "
                    + "VALOR_VENDA = ?, "
                    + "VALOR_CANCELADO = ?, "
                    + "CUPOM_CANCELADO = ?, "
                    + "VALOR_FINAL = ?, "
                    + "HASH_TRIPA = ?, "
                    + "HASH_INCREMENTO = ? "
                    + "where ID = ?";

            String tripa = String.valueOf(pVendaCabecalho.getId())
                    + String.valueOf(pVendaCabecalho.getCcf())
                    + String.valueOf(pVendaCabecalho.getCoo())
                    + Biblioteca.formatoDecimal("V", valorFinal)
                    + pVendaCabecalho.getSerieEcf()
                    + "CS0";

            pstm = con.prepareStatement(consultaSQL);

            pstm.setString(1, "C");
            pstm.setDouble(2, pVendaCabecalho.getValorVenda());
            pstm.setDouble(3, pVendaCabecalho.getValorVenda());
            pstm.setString(4, "S");
            pstm.setDouble(5, valorFinal);
            pstm.setString(6, Biblioteca.MD5String(tripa));
            pstm.setInt(7, -1);
            pstm.setInt(8, pVendaCabecalho.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public void cancelaItem(VendaDetalheVO pVendaDetalhe) {
        consultaSQL =
                "update ECF_VENDA_DETALHE set "
                + "CANCELADO = ?, "
                + "TOTALIZADOR_PARCIAL = ?, "
                + "HASH_TRIPA = ?, "
                + "HASH_INCREMENTO = ? "
                + " where ID = ?";

        try {
            String tripa = Caixa.configuracao.getImpressoraVO().getSerie()
                    + pVendaDetalhe.getCoo()
                    + pVendaDetalhe.getCcf()
                    + pVendaDetalhe.getGtin()
                    + Biblioteca.formatoDecimal("Q", pVendaDetalhe.getQuantidade())
                    + Biblioteca.formatoDecimal("V", pVendaDetalhe.getValorUnitario())
                    + Biblioteca.formatoDecimal("V", pVendaDetalhe.getTotalItem())
                    + pVendaDetalhe.getTotalizadorParcial() + "S" + "0";
            pVendaDetalhe.setHashTripa(Biblioteca.MD5String(tripa));

            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, pVendaDetalhe.getCancelado());
            pstm.setString(2, pVendaDetalhe.getTotalizadorParcial());
            pstm.setString(3, pVendaDetalhe.getHashTripa());
            pstm.setInt(4, -1);
            pstm.setInt(5, pVendaDetalhe.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public VendaCabecalhoVO ultimaVenda() {
        consultaSQL = "select * from ECF_VENDA_CABECALHO "
                + "where id = (select max(id) from ECF_VENDA_CABECALHO)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            if (rs.first()) {
                VendaCabecalhoVO vendaCabecalho = new VendaCabecalhoVO();
                vendaCabecalho.setId(rs.getInt("ID"));
                vendaCabecalho.setValorVenda(rs.getDouble("VALOR_VENDA"));
                vendaCabecalho.setValorCancelado(rs.getDouble("VALOR_CANCELADO"));
                vendaCabecalho.setStatusVenda(rs.getString("STATUS_VENDA"));
                vendaCabecalho.setCoo(rs.getInt("COO"));
                vendaCabecalho.setCcf(rs.getInt("CCF"));
                vendaCabecalho.setSerieEcf(rs.getString("SERIE_ECF"));

                return vendaCabecalho;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }

    public void marcarVendaComProblema(VendaCabecalhoVO pVendaCabecalho) {
        consultaSQL =
                "update ECF_VENDA_CABECALHO set "
                + "STATUS_VENDA = ? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, "P");
            pstm.setInt(2, pVendaCabecalho.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public VendaCabecalhoVO verificaVendaComProblema() {
        consultaSQL =
                "select * from ECF_VENDA_CABECALHO "
                + " where STATUS_VENDA = 'P' ";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            VendaCabecalhoVO vendaCabecalho = new VendaCabecalhoVO();
            rs = pstm.executeQuery();
            if (rs.first()) {
                vendaCabecalho.setId(rs.getInt("id"));
                vendaCabecalho.setValorVenda(rs.getDouble("valor_venda"));
                vendaCabecalho.setValorCancelado(vendaCabecalho.getValorVenda());
            }
            return vendaCabecalho;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }

    private VendaCabecalhoVO calculaImpostos(VendaCabecalhoVO vendaCabecalho) {
        try {
            List<VendaDetalheVO> listaVendaDetalhe = vendaDetalhe(vendaCabecalho.getId());

            BigDecimal icmsCabecalho = BigDecimal.ZERO;
            BigDecimal icmsBase = BigDecimal.ZERO;
            BigDecimal icmsOutras = BigDecimal.ZERO;
            VendaDetalheVO vendaDetalhe;

            for (int i = 0; i < listaVendaDetalhe.size(); i++) {
                vendaDetalhe = listaVendaDetalhe.get(i);

                if (vendaDetalhe.getTotalizadorParcial().equals("N1") || vendaDetalhe.getTotalizadorParcial().equals("F1")) {
                    icmsOutras = icmsOutras.add(BigDecimal.valueOf(vendaDetalhe.getValorTotal()));
                    icmsOutras = icmsOutras.setScale(Caixa.configuracao.getDecimaisValor(), RoundingMode.DOWN);
                } else {
                    icmsBase = icmsBase.add(BigDecimal.valueOf(vendaDetalhe.getValorTotal()));
                    icmsBase = icmsBase.setScale(Caixa.configuracao.getDecimaisValor(), RoundingMode.DOWN);
                }

                icmsCabecalho = icmsCabecalho.add(BigDecimal.valueOf(vendaDetalhe.getIcms()));
                icmsCabecalho = icmsCabecalho.setScale(Caixa.configuracao.getDecimaisValor(), RoundingMode.DOWN);
            }

            vendaCabecalho.setBaseIcms(icmsBase.doubleValue());
            vendaCabecalho.setIcmsOutras(icmsOutras.doubleValue());
            vendaCabecalho.setIcms(icmsCabecalho.doubleValue());

            return vendaCabecalho;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<VendaDetalheVO> vendaDetalhe(int idVendaCabecalho) {
        try {
            consultaSQL = "select * from ECF_VENDA_DETALHE where ID_ECF_VENDA_CABECALHO = ?";
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, idVendaCabecalho);
            rs = pstm.executeQuery();

            List<VendaDetalheVO> listaVendaDetalhe = new ArrayList<VendaDetalheVO>();
            VendaDetalheVO vendaDetalhe;
            while (rs.next()) {
                vendaDetalhe = new VendaDetalheVO();
                vendaDetalhe.setId(rs.getInt("ID"));
                vendaDetalhe.setIdVendaCabecalho(rs.getInt("ID_ECF_VENDA_CABECALHO"));
                vendaDetalhe.setCfop(rs.getInt("CFOP"));
                vendaDetalhe.setGtin(rs.getString("GTIN"));
                vendaDetalhe.setIdProduto(rs.getInt("ID_ECF_PRODUTO"));
                vendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                vendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                vendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                vendaDetalhe.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                vendaDetalhe.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                vendaDetalhe.setIcms(rs.getDouble("ICMS"));
                vendaDetalhe.setBaseIcms(rs.getDouble("BASE_ICMS"));
                vendaDetalhe.setCancelado(rs.getString("CANCELADO"));

                listaVendaDetalhe.add(vendaDetalhe);
            }

            return listaVendaDetalhe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
