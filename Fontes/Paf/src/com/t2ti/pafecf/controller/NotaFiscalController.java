/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da nota fiscal - NF2</p>
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
import com.t2ti.pafecf.vo.NotaFiscalCabecalhoVO;
import com.t2ti.pafecf.vo.NotaFiscalDetalheVO;
import com.t2ti.pafecf.vo.ProdutoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotaFiscalController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<NotaFiscalCabecalhoVO> tabelaNotaFiscalCabecalho() {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from NOTA_FISCAL_CABECALHO");
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<NotaFiscalCabecalhoVO> listaNotaFiscalCabecalho = new ArrayList<NotaFiscalCabecalhoVO>();
                consultaSQL = "select * from NOTA_FISCAL_CABECALHO";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    NotaFiscalCabecalhoVO NotaFiscalCabecalho = new NotaFiscalCabecalhoVO();
                    NotaFiscalCabecalho.setId(rs.getInt("ID"));
                    NotaFiscalCabecalho.setCfop(rs.getInt("CFOP"));
                    NotaFiscalCabecalho.setIdCliente(rs.getInt("ID_CLIENTE"));
                    NotaFiscalCabecalho.setNumero(rs.getString("NUMERO"));
                    NotaFiscalCabecalho.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    NotaFiscalCabecalho.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    NotaFiscalCabecalho.setSerie(rs.getString("SERIE"));
                    NotaFiscalCabecalho.setSubserie(rs.getString("SUBSERIE"));
                    NotaFiscalCabecalho.setTotalProdutos(rs.getDouble("TOTAL_PRODUTOS"));
                    NotaFiscalCabecalho.setTotalNf(rs.getDouble("TOTAL_NF"));
                    NotaFiscalCabecalho.setBaseIcms(rs.getDouble("BASE_ICMS"));
                    NotaFiscalCabecalho.setIcms(rs.getDouble("ICMS"));
                    NotaFiscalCabecalho.setIcmsOutras(rs.getDouble("ICMS_OUTRAS"));
                    NotaFiscalCabecalho.setIssqn(rs.getDouble("ISSQN"));
                    NotaFiscalCabecalho.setPis(rs.getDouble("PIS"));
                    NotaFiscalCabecalho.setCofins(rs.getDouble("COFINS"));
                    NotaFiscalCabecalho.setIpi(rs.getDouble("IPI"));
                    NotaFiscalCabecalho.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                    NotaFiscalCabecalho.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    NotaFiscalCabecalho.setAcrescimoItens(rs.getDouble("ACRESCIMO_ITENS"));
                    NotaFiscalCabecalho.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    NotaFiscalCabecalho.setDesconto(rs.getDouble("DESCONTO"));
                    NotaFiscalCabecalho.setDescontoItens(rs.getDouble("DESCONTO_ITENS"));
                    NotaFiscalCabecalho.setCancelada(rs.getString("CANCELADA"));
                    listaNotaFiscalCabecalho.add(NotaFiscalCabecalho);
                }
                return listaNotaFiscalCabecalho;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public List<NotaFiscalDetalheVO> tabelaNotaFiscalDetalhe(Integer pId) {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from NotaFiscal_DETALHE where ID_NotaFiscal_CABECALHO=" + pId);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<NotaFiscalDetalheVO> listaNotaFiscalDetalhe = new ArrayList<NotaFiscalDetalheVO>();
                consultaSQL = "select * from NotaFiscal_DETALHE where ID_NotaFiscal_CABECALHO=" + pId;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    NotaFiscalDetalheVO NotaFiscalDetalhe = new NotaFiscalDetalheVO();
                    NotaFiscalDetalhe.setId(rs.getInt("ID"));
                    NotaFiscalDetalhe.setCfop(rs.getInt("CFOP"));
                    NotaFiscalDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    NotaFiscalDetalhe.setId(rs.getInt("ID_NOTA_FISCAL_CABECALHO"));
                    NotaFiscalDetalhe.setItem(rs.getInt("ITEM"));
                    NotaFiscalDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    NotaFiscalDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    NotaFiscalDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    NotaFiscalDetalhe.setBaseIcms(rs.getDouble("BASE_ICMS"));
                    NotaFiscalDetalhe.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                    NotaFiscalDetalhe.setIcms(rs.getDouble("ICMS"));
                    NotaFiscalDetalhe.setIcmsOutras(rs.getDouble("ICMS_OUTRAS"));
                    NotaFiscalDetalhe.setIcmsIsento(rs.getDouble("ICMS_ISENTO"));
                    NotaFiscalDetalhe.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    NotaFiscalDetalhe.setDesconto(rs.getDouble("DESCONTO"));
                    NotaFiscalDetalhe.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                    NotaFiscalDetalhe.setIssqn(rs.getDouble("ISSQN"));
                    NotaFiscalDetalhe.setTaxaPis(rs.getDouble("TAXA_PIS"));
                    NotaFiscalDetalhe.setPis(rs.getDouble("PIS"));
                    NotaFiscalDetalhe.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                    NotaFiscalDetalhe.setCofins(rs.getDouble("COFINS"));
                    NotaFiscalDetalhe.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                    NotaFiscalDetalhe.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    NotaFiscalDetalhe.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                    NotaFiscalDetalhe.setIpi(rs.getDouble("IPI"));
                    NotaFiscalDetalhe.setCancelado(rs.getString("CANCELADO"));
                    NotaFiscalDetalhe.setMovimentaEstoque(rs.getString("MOVIMENTA_ESTOQUE"));
                    listaNotaFiscalDetalhe.add(NotaFiscalDetalhe);
                }
                return listaNotaFiscalDetalhe;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public boolean gravaNota(NotaFiscalCabecalhoVO notaCabecalho, List<NotaFiscalDetalheVO> listaNotaDetalhe) {
        Connection con = null;
        try {
            consultaSQL = "insert into NOTA_FISCAL_CABECALHO ("
                    + "ID_ECF_FUNCIONARIO,"
                    + "ID_CLIENTE,"
                    + "CFOP,"
                    + "NUMERO,"
                    + "DATA_EMISSAO,"
                    + "HORA_EMISSAO,"
                    + "SERIE,"
                    + "SUBSERIE,"
                    + "TOTAL_PRODUTOS,"
                    + "TOTAL_NF,"
                    + "BASE_ICMS,"
                    + "ICMS,"
                    + "ICMS_OUTRAS,"
                    + "ISSQN,"
                    + "PIS,"
                    + "COFINS,"
                    + "IPI,"
                    + "TAXA_ACRESCIMO,"
                    + "ACRESCIMO,"
                    + "ACRESCIMO_ITENS,"
                    + "TAXA_DESCONTO,"
                    + "DESCONTO,"
                    + "DESCONTO_ITENS,"
                    + "CANCELADA,"
                    + "TIPO_NOTA)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            con = bd.conectar();
            pstm = con.prepareStatement(consultaSQL);
            pstm.setInt(1, notaCabecalho.getIdFuncionario());
            pstm.setInt(2, notaCabecalho.getIdCliente());
            pstm.setInt(3, notaCabecalho.getCfop());
            pstm.setString(4, notaCabecalho.getNumero());
            pstm.setDate(5, new java.sql.Date(notaCabecalho.getDataEmissao().getTime()));
            pstm.setString(6, notaCabecalho.getHoraEmissao());
            pstm.setString(7, notaCabecalho.getSerie());
            pstm.setString(8, notaCabecalho.getSubserie());
            pstm.setDouble(9, notaCabecalho.getTotalProdutos());
            pstm.setDouble(10, notaCabecalho.getTotalNf());
            pstm.setDouble(11, notaCabecalho.getBaseIcms());
            pstm.setDouble(12, notaCabecalho.getIcms());
            pstm.setDouble(13, notaCabecalho.getIcmsOutras());
            pstm.setDouble(14, notaCabecalho.getIssqn());
            pstm.setDouble(15, notaCabecalho.getPis());
            pstm.setDouble(16, notaCabecalho.getCofins());
            pstm.setDouble(17, notaCabecalho.getIpi());
            pstm.setDouble(18, notaCabecalho.getTaxaAcrescimo());
            pstm.setDouble(19, notaCabecalho.getAcrescimo());
            pstm.setDouble(20, notaCabecalho.getAcrescimoItens());
            pstm.setDouble(21, notaCabecalho.getTaxaDesconto());
            pstm.setDouble(22, notaCabecalho.getDesconto());
            pstm.setDouble(23, notaCabecalho.getDescontoItens());
            pstm.setString(24, notaCabecalho.getCancelada());
            pstm.setString(25, notaCabecalho.getTipoNota());

            pstm.executeUpdate();

            consultaSQL = "select max(ID) as ID from NOTA_FISCAL_CABECALHO";
            pstm = con.prepareStatement(consultaSQL);
            rs = pstm.executeQuery();

            if (rs.first()) {
                notaCabecalho.setId(rs.getInt("ID"));
            }

            consultaSQL = "insert into NOTA_FISCAL_DETALHE ("
                    + "ID_NF_CABECALHO,"
                    + "ID_PRODUTO,"
                    + "CFOP,"
                    + "ITEM,"
                    + "QUANTIDADE,"
                    + "VALOR_UNITARIO,"
                    + "VALOR_TOTAL,"
                    + "BASE_ICMS,"
                    + "TAXA_ICMS,"
                    + "ICMS,"
                    + "ICMS_OUTRAS,"
                    + "ICMS_ISENTO,"
                    + "TAXA_DESCONTO,"
                    + "DESCONTO,"
                    + "TAXA_ISSQN,"
                    + "ISSQN,"
                    + "TAXA_PIS,"
                    + "PIS,"
                    + "TAXA_COFINS,"
                    + "COFINS,"
                    + "TAXA_ACRESCIMO,"
                    + "ACRESCIMO,"
                    + "TAXA_IPI,"
                    + "IPI,"
                    + "CANCELADO,"
                    + "CST,"
                    + "ECF_ICMS_ST,"
                    + "MOVIMENTA_ESTOQUE) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            NotaFiscalDetalheVO notaDetalhe;
            for (int i = 0; i < listaNotaDetalhe.size(); i++) {
                notaDetalhe = listaNotaDetalhe.get(i);

                pstm = con.prepareStatement(consultaSQL);
                pstm.setInt(1, notaCabecalho.getId());
                pstm.setInt(2, notaDetalhe.getIdProduto());
                pstm.setInt(3, notaDetalhe.getCfop());
                pstm.setInt(4, notaDetalhe.getItem());
                pstm.setDouble(5, notaDetalhe.getQuantidade());
                pstm.setDouble(6, notaDetalhe.getValorUnitario());
                pstm.setDouble(7, notaDetalhe.getValorTotal());
                pstm.setDouble(8, notaDetalhe.getBaseIcms());
                pstm.setDouble(9, notaDetalhe.getTaxaIcms());
                pstm.setDouble(10, notaDetalhe.getIcms());
                pstm.setDouble(11, notaDetalhe.getIcmsOutras());
                pstm.setDouble(12, notaDetalhe.getIcmsIsento());
                pstm.setDouble(13, notaDetalhe.getTaxaDesconto());
                pstm.setDouble(14, notaDetalhe.getDesconto());
                pstm.setDouble(15, notaDetalhe.getTaxaIssqn());
                pstm.setDouble(16, notaDetalhe.getIssqn());
                pstm.setDouble(17, notaDetalhe.getTaxaPis());
                pstm.setDouble(18, notaDetalhe.getPis());
                pstm.setDouble(19, notaDetalhe.getTaxaCofins());
                pstm.setDouble(20, notaDetalhe.getCofins());
                pstm.setDouble(21, notaDetalhe.getTaxaAcrescimo());
                pstm.setDouble(22, notaDetalhe.getAcrescimo());
                pstm.setDouble(23, notaDetalhe.getTaxaIpi());
                pstm.setDouble(24, notaDetalhe.getIpi());
                pstm.setString(25, notaDetalhe.getCancelado());
                pstm.setString(26, notaDetalhe.getCst());
                pstm.setString(27, notaDetalhe.getEcfIcmsSt());
                pstm.setString(28, notaDetalhe.getMovimentaEstoque());

                pstm.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public NotaFiscalCabecalhoVO consultaNota(String numeroNota) {
        try {
            consultaSQL = "select * from NOTA_FISCAL_CABECALHO where NUMERO = ?";
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setString(1, numeroNota);
            rs = pstm.executeQuery();
            if (rs.first()) {
                NotaFiscalCabecalhoVO notaCabecalho = new NotaFiscalCabecalhoVO();
                notaCabecalho.setId(rs.getInt("ID"));
                notaCabecalho.setNumero(rs.getString("NUMERO"));
                return notaCabecalho;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }

    public List<NotaFiscalCabecalhoVO> consultaNotaCabecalhoSped(String pDataInicio, String pDataFim) {
        try {
            consultaSQL =
                    "select count(*) AS TOTAL from NOTA_FISCAL_CABECALHO, CLIENTE where "
                    + " NOTA_FISCAL_CABECALHO.ID_CLIENTE = CLIENTE.ID and "
                    + "(DATA_EMISSAO between '" + pDataInicio + "' and '" + pDataFim + "')";

            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<NotaFiscalCabecalhoVO> listaNotaCabecalho = new ArrayList<NotaFiscalCabecalhoVO>();

                consultaSQL =
                        "SELECT "
                        + "(SELECT g.numero FROM NOTA_FISCAL_CABECALHO g WHERE g.id IN "
                        + "(SELECT MIN(b.id) FROM NOTA_FISCAL_CABECALHO b WHERE "
                        + "(b.DATA_EMISSAO BETWEEN '" + pDataInicio + "' and '" + pDataFim + "'))) AS minimo, "
                        + "(SELECT F.NUMERO FROM NOTA_FISCAL_CABECALHO F WHERE F.ID IN "
                        + "(SELECT MAX(s.ID)  FROM NOTA_FISCAL_CABECALHO s "
                        + "WHERE (s.DATA_EMISSAO BETWEEN '" + pDataInicio + "' and '" + pDataFim + "'))) AS MAXIMO, N.*, C.* "
                        + "FROM NOTA_FISCAL_CABECALHO N, CLIENTE C WHERE N.ID_CLIENTE = C.ID "
                        + "AND (n.DATA_EMISSAO BETWEEN '" + pDataInicio + "' and '" + pDataFim + "')";

                pstm = bd.conectar().prepareStatement(consultaSQL);
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    NotaFiscalCabecalhoVO notaCabecalho = new NotaFiscalCabecalhoVO();
                    notaCabecalho.setId(rs.getInt("ID"));
                    notaCabecalho.setIdFuncionario(rs.getInt("ID_ECF_FUNCIONARIO"));
                    notaCabecalho.setIdCliente(rs.getInt("ID_CLIENTE"));
                    notaCabecalho.setCfop(rs.getInt("CFOP"));
                    notaCabecalho.setNumero(rs.getString("NUMERO"));
                    notaCabecalho.setNumOrdemInicial(rs.getInt("MINIMO"));
                    notaCabecalho.setNumOrdemFinal(rs.getInt("MAXIMO"));
                    notaCabecalho.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    notaCabecalho.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    notaCabecalho.setSerie(rs.getString("SERIE"));
                    notaCabecalho.setSubserie(rs.getString("SUBSERIE"));
                    notaCabecalho.setTotalProdutos(rs.getDouble("TOTAL_PRODUTOS"));
                    notaCabecalho.setTotalNf(rs.getDouble("TOTAL_NF"));
                    notaCabecalho.setBaseIcms(rs.getDouble("BASE_ICMS"));
                    notaCabecalho.setIcms(rs.getDouble("ICMS"));
                    notaCabecalho.setIcmsOutras(rs.getDouble("ICMS_OUTRAS"));
                    notaCabecalho.setIssqn(rs.getDouble("ISSQN"));
                    notaCabecalho.setPis(rs.getDouble("PIS"));
                    notaCabecalho.setCofins(rs.getDouble("COFINS"));
                    notaCabecalho.setIpi(rs.getDouble("IPI"));
                    notaCabecalho.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                    notaCabecalho.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    notaCabecalho.setAcrescimoItens(rs.getDouble("ACRESCIMO_ITENS"));
                    notaCabecalho.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    notaCabecalho.setDesconto(rs.getDouble("DESCONTO"));
                    notaCabecalho.setDescontoItens(rs.getDouble("DESCONTO_ITENS"));
                    notaCabecalho.setCancelada(rs.getString("CANCELADA"));
                    notaCabecalho.setCpfCnpjCliente(rs.getString("CPF_CNPJ"));
                    listaNotaCabecalho.add(notaCabecalho);
                }
                return listaNotaCabecalho;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }

    public List<NotaFiscalDetalheVO> consultaNotaDetalheSped(Integer pId) {
        try {
            consultaSQL =
                    "select count(*) AS TOTAL from NOTA_FISCAL_DETALHE where ID=?";

            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, pId);
            rs = pstm.executeQuery();

            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<NotaFiscalDetalheVO> listaNotaDetalhe = new ArrayList<NotaFiscalDetalheVO>();

                consultaSQL =
                        "select * from NOTA_FISCAL_DETALHE where ID=?";

                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setInt(1, pId);
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    produto = new ProdutoController().consultaId(rs.getInt("ID_PRODUTO"));
                    NotaFiscalDetalheVO notaDetalhe = new NotaFiscalDetalheVO();

                    notaDetalhe.setId(rs.getInt("ID"));
                    notaDetalhe.setIdNotaCabecalho(rs.getInt("ID_NF_CABECALHO"));
                    notaDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    notaDetalhe.setCfop(rs.getInt("CFOP"));
                    notaDetalhe.setItem(rs.getInt("ITEM"));
                    notaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    notaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    notaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    notaDetalhe.setBaseIcms(rs.getDouble("BASE_ICMS"));
                    notaDetalhe.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                    notaDetalhe.setIcms(rs.getDouble("ICMS"));
                    notaDetalhe.setIcmsOutras(rs.getDouble("ICMS_OUTRAS"));
                    notaDetalhe.setIcmsIsento(rs.getDouble("ICMS_ISENTO"));
                    notaDetalhe.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    notaDetalhe.setDesconto(rs.getDouble("DESCONTO"));
                    notaDetalhe.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                    notaDetalhe.setIssqn(rs.getDouble("ISSQN"));
                    notaDetalhe.setTaxaPis(rs.getDouble("TAXA_PIS"));
                    notaDetalhe.setPis(rs.getDouble("PIS"));
                    notaDetalhe.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                    notaDetalhe.setCofins(rs.getDouble("COFINS"));
                    notaDetalhe.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                    notaDetalhe.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    notaDetalhe.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                    notaDetalhe.setIpi(rs.getDouble("IPI"));
                    notaDetalhe.setCancelado(rs.getString("CANCELADO"));
                    notaDetalhe.setCst(rs.getString("CST"));
                    notaDetalhe.setMovimentaEstoque(rs.getString("MOVIMENTA_ESTOQUE"));
                    notaDetalhe.setDescricaoUnidade(produto.getUnidadeProdutoVO().getDescricao());
                    listaNotaDetalhe.add(notaDetalhe);
                }
                return listaNotaDetalhe;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }
}
