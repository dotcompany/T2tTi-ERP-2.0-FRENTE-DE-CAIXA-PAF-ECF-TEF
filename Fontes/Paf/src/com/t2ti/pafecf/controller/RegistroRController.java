/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do registro R</p>
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
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.view.CargaPDV;
import com.t2ti.pafecf.vo.R01VO;
import com.t2ti.pafecf.vo.R02VO;
import com.t2ti.pafecf.vo.R03VO;
import com.t2ti.pafecf.vo.R04VO;
import com.t2ti.pafecf.vo.R05VO;
import com.t2ti.pafecf.vo.R06VO;
import com.t2ti.pafecf.vo.R07VO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * =============================================================================
 * Observações importantes
 *
 * Registro tipo R01 - Identificação do ECF, do Usuário, do PAF-ECF e da Empresa Desenvolvedora e Dados do Arquivo; 
 * Registro tipo R02 - Relação de Reduções Z;
 * Registro tipo R03 - Detalhe da Redução Z; 
 * Registro tipo R04 - Cupom Fiscal, Nota Fiscal de Venda a Consumidor ou Bilhete de Passagem; 
 * Registro tipo R05 - Detalhe do Cupom Fiscal, da Nota Fiscal de Venda a Consumidor ou do Bilhete de Passagem; 
 * Registro tipo R06 - Demais documentos emitidos pelo ECF;
 * Registro tipo R07 - Detalhe do Cupom Fiscal e do Documento Não Fiscal - Meio de Pagamento; 
 * Registro EAD - Assinatura digital.
 *
 * Numa venda com cartão teremos: 
 * 
 * - Um R04 referente ao Cupom Fiscal (já gravamos no venda_cabecalho) 
 * - Um R05 para cada item vendido (já gravamos no venda_detalhe) 
 * - Um R06 para o Comprovante de Crédito ou Débito (o CCD se encaixa como "outros documentos emitidos"); 
 * - Um R07 referente à forma de pagamento utilizada no Cupom Fiscal, no caso, Cartão.
 * =============================================================================
 */
public class RegistroRController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public R02VO gravaR02(R02VO pR02) {
        consultaSQL =
                "insert into R02 ("
                + "ID_ECF_CAIXA,"
                + "ID_OPERADOR,"
                + "ID_IMPRESSORA,"
                + "CRZ,"
                + "COO,"
                + "CRO,"
                + "DATA_MOVIMENTO,"
                + "DATA_EMISSAO,"
                + "HORA_EMISSAO,"
                + "VENDA_BRUTA,"
                + "GRANDE_TOTAL) values ("
                + "?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, pR02.getIdCaixa());
            pstm.setInt(2, pR02.getIdOperador());
            pstm.setInt(3, pR02.getIdImpressora());
            pstm.setInt(4, pR02.getCrz());
            pstm.setInt(5, pR02.getCoo());
            pstm.setInt(6, pR02.getCro());
            pstm.setDate(7, pR02.getDataMovimento());
            pstm.setDate(8, pR02.getDataEmissao());
            pstm.setString(9, pR02.getHoraEmissao());
            pstm.setDouble(10, pR02.getValorVendaBruta());
            pstm.setDouble(11, pR02.getValorGrandeTotal());
            pstm.executeUpdate();

            try {
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from R02");
                rs.first();
                pR02.setId(rs.getInt("ID"));

                SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                String tripa = ""
                        + pR02.getId()
                        + pR02.getCrz()
                        + pR02.getCoo()
                        + pR02.getCro()
                        + pR02.getDataMovimento()
                        + formatoData.format(pR02.getDataEmissao())
                        + pR02.getHoraEmissao()
                        + Biblioteca.formatoDecimal("V", pR02.getValorVendaBruta());
                pR02.setHashTripa(Biblioteca.MD5String(tripa));

                consultaSQL =
                        "update R02 set "
                        + "HASH_TRIPA=? "
                        + "where ID=? ";

                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setString(1, pR02.getHashTripa());
                pstm.setInt(2, pR02.getId());
                pstm.executeUpdate();

                return pR02;
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

    public void gravaR03(List<R03VO> pListaR03) {

        R03VO r03;
        String tripa;

        for (int i = 0; i < pListaR03.size() - 1; i++) {
            try {
                consultaSQL =
                        "insert into R03 ("
                        + "ID_R02,"
                        + "TOTALIZADOR_PARCIAL,"
                        + "VALOR_ACUMULADO,"
                        + "HASH_TRIPA) values ("
                        + "?,?,?,?)";

                r03 = pListaR03.get(i);
                tripa = r03.getTotalizadorParcial()
                        + Biblioteca.formatoDecimal("V", r03.getValorAcumulado());
                r03.setHashTripa(Biblioteca.MD5String(tripa));


                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setInt(1, r03.getIdR02());
                pstm.setString(2, r03.getTotalizadorParcial());
                pstm.setDouble(3, r03.getValorAcumulado());
                pstm.setString(4, r03.getHashTripa());
                pstm.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bd.desconectar();
            }
        }
    }

    public void gravaR06(R06VO pR06) {
        consultaSQL =
                "insert into R06 ("
                + "ID_ECF_CAIXA,"
                + "ID_OPERADOR,"
                + "ID_IMPRESSORA,"
                + "COO,"
                + "GNF,"
                + "GRG,"
                + "CDC,"
                + "DENOMINACAO,"
                + "DATA_EMISSAO,"
                + "HORA_EMISSAO,"
                + "SERIE_ECF, "
                + "HASH_TRIPA, "
                + "HASH_INCREMENTO) values ("
                + "?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, pR06.getIdCaixa());
            pstm.setInt(2, pR06.getIdOperador());
            pstm.setInt(3, pR06.getIdImpressora());
            pstm.setInt(4, pR06.getCoo());
            pstm.setInt(5, pR06.getGnf());
            if (pR06.getGrg() == null) {
                pstm.setNull(6, java.sql.Types.INTEGER);
            } else {
                pstm.setInt(6, pR06.getGrg());
            }
            if (pR06.getCdc() == null) {
                pstm.setNull(7, java.sql.Types.INTEGER);
            } else {
                pstm.setInt(7, pR06.getCdc());
            }
            pstm.setString(8, pR06.getDenominacao());
            pstm.setDate(9, pR06.getDataEmissao());
            pstm.setString(10, pR06.getHoraEmissao());

            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
            String tripa = ""
                    + pR06.getCoo()
                    + pR06.getGnf()
                    + pR06.getDenominacao()
                    + formatoData.format(pR06.getDataEmissao())
                    + pR06.getHoraEmissao()
                    + pR06.getSerieEcf()
                    + "0";
            pR06.setHashTripa(Biblioteca.MD5String(tripa));
            pstm.setString(11, pR06.getSerieEcf());
            pstm.setString(12, pR06.getHashTripa());
            pstm.setInt(13, 0);

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public void gravaR07(R07VO pR07) {
        consultaSQL =
                "insert into R07 ("
                + "MEIO_PAGAMENTO,"
                + "VALOR_PAGAMENTO,"
                + "ESTORNO,"
                + "VALOR_ESTORNO) values ("
                + "?,?,?,?)";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, pR07.getMeioPagamento());
            pstm.setDouble(2, pR07.getValorPagamento());
            pstm.setString(3, pR07.getIndicadorEstorno());
            pstm.setDouble(4, pR07.getValorEstorno());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }

    }

    public List<R02VO> tabelaR02() {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from R02");
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R02VO> listaR02 = new ArrayList<R02VO>();
                consultaSQL = "select * from R02";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R02VO R02 = new R02VO();
                    R02.setId(rs.getInt("ID"));
                    R02.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                    R02.setIdOperador(rs.getInt("ID_OPERADOR"));
                    R02.setIdImpressora(rs.getInt("ID_IMPRESSORA"));
                    R02.setCrz(rs.getInt("CRZ"));
                    R02.setCoo(rs.getInt("COO"));
                    R02.setCro(rs.getInt("CRO"));
                    R02.setDataMovimento(rs.getDate("DATA_MOVIMENTO"));
                    R02.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    R02.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    R02.setValorVendaBruta(rs.getDouble("VENDA_BRUTA"));
                    R02.setValorGrandeTotal(rs.getDouble("GRANDE_TOTAL"));
                    listaR02.add(R02);
                }
                return listaR02;
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

    public List<R02VO> tabelaR02Id(Integer pId) {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from R02 where ID_IMPRESSORA=" + pId);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R02VO> listaR02 = new ArrayList<R02VO>();
                consultaSQL = "select * from R02 where ID_IMPRESSORA=" + pId;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R02VO R02 = new R02VO();
                    R02.setId(rs.getInt("ID"));
                    R02.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                    R02.setIdOperador(rs.getInt("ID_OPERADOR"));
                    R02.setIdImpressora(rs.getInt("ID_IMPRESSORA"));
                    R02.setCrz(rs.getInt("CRZ"));
                    R02.setCoo(rs.getInt("COO"));
                    R02.setCro(rs.getInt("CRO"));
                    R02.setDataMovimento(rs.getDate("DATA_MOVIMENTO"));
                    R02.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    R02.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    R02.setValorVendaBruta(rs.getDouble("VENDA_BRUTA"));
                    R02.setValorGrandeTotal(rs.getDouble("GRANDE_TOTAL"));
                    listaR02.add(R02);
                }
                return listaR02;
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

    public List<R02VO> tabelaR02(String pDataInicio, String pDataFim, Integer pIdImpressora) {
        try {
            consultaSQL =
                    "select count(*) as TOTAL from R02 where "
                    + "ID_IMPRESSORA=" + pIdImpressora
                    + " and  (DATA_MOVIMENTO between '"
                    + pDataInicio + "' and '" + pDataFim + "')";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R02VO> listaR02 = new ArrayList<R02VO>();
                consultaSQL =
                        "select * from R02 where "
                        + "ID_IMPRESSORA=" + pIdImpressora
                        + " and  (DATA_MOVIMENTO between '"
                        + pDataInicio + "' and '" + pDataFim + "')";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R02VO R02 = new R02VO();
                    R02.setId(rs.getInt("ID"));
                    R02.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                    R02.setIdOperador(rs.getInt("ID_OPERADOR"));
                    R02.setIdImpressora(rs.getInt("ID_IMPRESSORA"));
                    R02.setCrz(rs.getInt("CRZ"));
                    R02.setCoo(rs.getInt("COO"));
                    R02.setCro(rs.getInt("CRO"));
                    R02.setDataMovimento(rs.getDate("DATA_MOVIMENTO"));
                    R02.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    R02.setSerieEcf(rs.getString("SERIE_ECF"));
                    R02.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    R02.setValorVendaBruta(rs.getDouble("VENDA_BRUTA"));
                    R02.setValorGrandeTotal(rs.getDouble("GRANDE_TOTAL"));
                    listaR02.add(R02);
                }
                return listaR02;
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

    public List<R03VO> tabelaR03(Integer pId) {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) AS TOTAL from R03 where ID_R02=" + pId);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R03VO> listaR03 = new ArrayList<R03VO>();
                consultaSQL = "select * from R03 where ID_R02=" + pId;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R03VO R03 = new R03VO();
                    R03.setId(rs.getInt("ID"));
                    R03.setIdR02(rs.getInt("ID_R02"));
                    R03.setSerieEcf(rs.getString("SERIE_ECF"));
                    R03.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    R03.setValorAcumulado(rs.getDouble("VALOR_ACUMULADO"));
                    R03.setCrz(rs.getInt("CRZ"));
                    R03.setHashTripa(rs.getString("HASH_TRIPA"));
                    R03.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    listaR03.add(R03);
                }
                return listaR03;
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

    public List<R04VO> tabelaR04() {
        try {
            consultaSQL =
                    "select count(*) as TOTAL "
                    + "from ECF_VENDA_CABECALHO VC, ECF_MOVIMENTO M "
                    + "where VC.ID_ECF_MOVIMENTO=M.ID";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R04VO> listaR04 = new ArrayList<R04VO>();
                consultaSQL =
                        "select VC.ID AS VCID, VC.ID_ECF_MOVIMENTO, VC.CCF, VC.COO, VC.DATA_VENDA, VC.VALOR_VENDA, "
                        + "VC.DESCONTO, VC.ACRESCIMO, VC.VALOR_FINAL, VC.STATUS_VENDA, VC.NOME_CLIENTE, "
                        + "VC.CPF_CNPJ_CLIENTE, M.ID AS MID, M.ID_ECF_IMPRESSORA, M.ID_ECF_OPERADOR "
                        + "from ECF_VENDA_CABECALHO VC, ECF_MOVIMENTO M "
                        + "where VC.ID_ECF_MOVIMENTO=M.ID";


                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R04VO R04 = new R04VO();
                    R04.setId(rs.getInt("VCID"));
                    R04.setIdOperador(rs.getInt("ID_ECF_OPERADOR"));
                    R04.setCcf(rs.getInt("CCF"));
                    R04.setCoo(rs.getInt("COO"));
                    R04.setDataEmissao(rs.getDate("DATA_VENDA"));
                    R04.setSubTotal(rs.getDouble("VALOR_VENDA"));
                    R04.setDesconto(rs.getDouble("DESCONTO"));
                    R04.setIndicadorDesconto("V");
                    R04.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    R04.setIndicadorAcrescimo("V");
                    R04.setValorLiquido(rs.getDouble("VALOR_FINAL"));
                    if (rs.getString("STATUS_VENDA").equals("C")) {
                        R04.setCancelado("S");
                    } else {
                        R04.setCancelado("N");
                    }
                    R04.setCancelamentoAcrescimo(0.0);
                    R04.setOrdemDescontoAcrescimo("D");
                    R04.setCliente(rs.getString("NOME_CLIENTE"));
                    R04.setCpfCnpj(rs.getString("CPF_CNPJ_CLIENTE"));
                    listaR04.add(R04);
                }
                return listaR04;
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

    public List<R04VO> tabelaR04(String pDataInicio, String pDataFim, Integer pIdImpressora) {
        try {
            consultaSQL =
                    "select count(*) as TOTAL "
                    + "from VIEW_R04 "
                    + "where ID_ECF_IMPRESSORA=" + pIdImpressora
                    + " and (DATA_VENDA between '"
                    + pDataInicio + "' and '" + pDataFim + "')";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R04VO> listaR04 = new ArrayList<R04VO>();

            consultaSQL =
                    "select * "
                    + "from VIEW_R04 "
                    + "where ID_ECF_IMPRESSORA=" + pIdImpressora
                    + " and (DATA_VENDA between '"
                    + pDataInicio + "' and '" + pDataFim + "')";
                
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R04VO R04 = new R04VO();
                    R04.setId(rs.getInt("VCID"));
                    R04.setIdOperador(rs.getInt("ID_ECF_OPERADOR"));
                    R04.setSerieEcf(rs.getString("SERIE_ECF"));
                    R04.setCcf(rs.getInt("CCF"));
                    R04.setCoo(rs.getInt("COO"));
                    R04.setDataEmissao(rs.getDate("DATA_VENDA"));
                    R04.setSubTotal(rs.getDouble("VALOR_VENDA"));
                    R04.setDesconto(rs.getDouble("DESCONTO"));
                    R04.setIndicadorDesconto("V");
                    R04.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    R04.setIndicadorAcrescimo("V");
                    R04.setValorLiquido(rs.getDouble("VALOR_FINAL"));
                    R04.setPis(rs.getDouble("PIS"));
                    R04.setCofins(rs.getDouble("COFINS"));
                    R04.setCancelado(rs.getString("CUPOM_CANCELADO"));
                    R04.setCancelamentoAcrescimo(0.0);
                    R04.setOrdemDescontoAcrescimo("D");
                    R04.setCliente(rs.getString("NOME_CLIENTE"));
                    R04.setCpfCnpj(rs.getString("CPF_CNPJ_CLIENTE"));
                    R04.setStatusVenda(rs.getString("STATUS_VENDA"));
                    R04.setHashTripa(rs.getString("HASH_TRIPA"));
                    R04.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    listaR04.add(R04);
                }
                return listaR04;
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

    public List<R04VO> tabelaR04(String pDataInicio, String pDataFim) {
        try {
            consultaSQL =
                    "select count(*) as TOTAL "
                    + "from VIEW_R04 "
                    + "where "
                    + " (DATA_VENDA between '"
                    + pDataInicio + "' and '" + pDataFim + "')";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R04VO> listaR04 = new ArrayList<R04VO>();

                consultaSQL =
                        "select * "
                        + "from VIEW_R04 "
                        + "where "
                        + " (DATA_VENDA between '"
                        + pDataInicio + "' and '" + pDataFim + "')";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R04VO R04 = new R04VO();
                    R04.setId(rs.getInt("VCID"));
                    R04.setIdOperador(rs.getInt("ID_ECF_OPERADOR"));
                    R04.setSerieEcf(rs.getString("SERIE_ECF"));
                    R04.setCcf(rs.getInt("CCF"));
                    R04.setCoo(rs.getInt("COO"));
                    R04.setDataEmissao(rs.getDate("DATA_VENDA"));
                    R04.setSubTotal(rs.getDouble("VALOR_VENDA"));
                    R04.setDesconto(rs.getDouble("DESCONTO"));
                    R04.setIndicadorDesconto("V");
                    R04.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    R04.setIndicadorAcrescimo("V");
                    R04.setValorLiquido(rs.getDouble("VALOR_FINAL"));
                    R04.setPis(rs.getDouble("PIS"));
                    R04.setCofins(rs.getDouble("COFINS"));
                    R04.setCancelado(rs.getString("CUPOM_CANCELADO"));
                    R04.setCancelamentoAcrescimo(0.0);
                    R04.setOrdemDescontoAcrescimo("D");
                    R04.setCliente(rs.getString("NOME_CLIENTE"));
                    R04.setCpfCnpj(rs.getString("CPF_CNPJ_CLIENTE"));
                    R04.setStatusVenda(rs.getString("STATUS_VENDA"));
                    R04.setHashTripa(rs.getString("HASH_TRIPA"));
                    R04.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    listaR04.add(R04);
                }
                return listaR04;
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
    
    public List<R05VO> tabelaR05(Integer pId) {
        consultaSQL =
                "select count(*) as TOTAL "
                + "from VIEW_R05 "
                + "where VCID=" + pId;

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R05VO> listaR05 = new ArrayList<R05VO>();
                consultaSQL =
                        "select * from VIEW_R05 where VCID=" + pId;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R05VO R05 = new R05VO();
                    R05.setId(rs.getInt("VID"));
                    R05.setItem(rs.getInt("ITEM"));
                    R05.setSerieEcf(rs.getString("SERIE_ECF"));
                    R05.setIdProduto(rs.getInt("ID_ECF_PRODUTO"));
                    R05.setGtin(rs.getString("GTIN"));
                    R05.setCcf(rs.getInt("CCF"));
                    R05.setCoo(rs.getInt("COO"));
                    R05.setDescricaoPdv(rs.getString("DESCRICAO_PDV"));
                    R05.setQuantidade(rs.getDouble("QUANTIDADE"));
                    R05.setIdUnidade(rs.getInt("ID_UNIDADE"));
                    R05.setSiglaUnidade(rs.getString("SIGLA_UNIDADE"));
                    R05.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    R05.setDesconto(rs.getDouble("DESCONTO"));
                    R05.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    R05.setTotalItem(rs.getDouble("TOTAL_ITEM"));
                    R05.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    R05.setIndicadorCancelamento(rs.getString("CANCELADO"));
                    if (R05.getIndicadorCancelamento().equals("S")) {
                        R05.setQuantidadeCancelada(1.0);
                        R05.setValorCancelado(rs.getDouble("TOTAL_ITEM"));
                    } else {
                        R05.setQuantidadeCancelada(0.0);
                        R05.setValorCancelado(0.0);
                    }
                    R05.setCancelamentoAcrescimo(0.0);
                    R05.setIat(rs.getString("IAT"));
                    R05.setIppt(rs.getString("IPPT"));
                    R05.setCasasDecimaisQuantidade(3);
                    R05.setCasasDecimaisValor(2);
                    R05.setCst(rs.getString("CST"));
                    R05.setCfop(rs.getInt("CFOP"));
                    R05.setAliquotaICMS(rs.getDouble("TAXA_ICMS"));
                    R05.setPis(rs.getDouble("PIS"));
                    R05.setCofins(rs.getDouble("COFINS"));
                    R05.setHashTripa(rs.getString("HASH_TRIPA"));
                    R05.setHashIncremento(rs.getInt("HASH_INCREMENTO"));

                    listaR05.add(R05);
                }
                return listaR05;
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

    public List<R06VO> tabelaR06(String pDataInicio, String pDataFim, Integer pIdImpressora) {
        try {
            consultaSQL =
                    "select count(*) as TOTAL "
                    + "from R06 "
                    + "where ID_IMPRESSORA=" + pIdImpressora
                    + " and (DATA_EMISSAO between '"
                    + pDataInicio + "' and '" + pDataFim + "')";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R06VO> listaR06 = new ArrayList<R06VO>();

            consultaSQL =
                    "select * "
                    + "from R06 "
                    + "where ID_IMPRESSORA=" + pIdImpressora
                    + " and (DATA_EMISSAO between '"
                    + pDataInicio + "' and '" + pDataFim + "')";
                
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R06VO R06 = new R06VO();
                    R06.setId(rs.getInt("ID"));
                    R06.setIdOperador(rs.getInt("ID_OPERADOR"));
                    R06.setIdImpressora(rs.getInt("ID_IMPRESSORA"));
                    R06.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                    R06.setCoo(rs.getInt("COO"));
                    R06.setGnf(rs.getInt("GNF"));
                    R06.setGrg(rs.getInt("GRG"));
                    R06.setCdc(rs.getInt("CDC"));
                    R06.setDenominacao(rs.getString("DENOMINACAO"));
                    R06.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    R06.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    R06.setSerieEcf(rs.getString("SERIE_ECF"));
                    R06.setHashTripa(rs.getString("HASH_TRIPA"));
                    R06.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    listaR06.add(R06);
                }
                return listaR06;
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

    public List<R06VO> tabelaR06() {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from R06");
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R06VO> listaR06 = new ArrayList<R06VO>();
                consultaSQL = "select * from R06";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R06VO R06 = new R06VO();
                    R06.setId(rs.getInt("ID"));
                    R06.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                    R06.setIdOperador(rs.getInt("ID_OPERADOR"));
                    R06.setIdImpressora(rs.getInt("ID_IMPRESSORA"));
                    R06.setCoo(rs.getInt("COO"));
                    R06.setGnf(rs.getInt("GNF"));
                    R06.setGrg(rs.getInt("GRG"));
                    R06.setCdc(rs.getInt("CDC"));
                    R06.setDenominacao(rs.getString("DENOMINACAO"));
                    R06.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    R06.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    listaR06.add(R06);
                }
                return listaR06;
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

    public List<R07VO> tabelaR07(Integer pId) {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from R07 where ID_R06=" + pId);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R07VO> listaR07 = new ArrayList<R07VO>();
                consultaSQL = "select * from R07 where ID_R06=" + pId;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R07VO R07 = new R07VO();
                    R07.setId(rs.getInt("ID"));
                    R07.setCcf(rs.getInt("CCF"));
                    R07.setMeioPagamento(rs.getString("MEIO_PAGAMENTO"));
                    R07.setValorPagamento(rs.getDouble("VALOR_PAGAMENTO"));
                    R07.setIndicadorEstorno(rs.getString("ESTORNO"));
                    R07.setValorEstorno(rs.getDouble("VALOR_ESTORNO"));
                    listaR07.add(R07);
                }
                return listaR07;
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

    public List<R07VO> tabelaR07IdR04(Integer pId) {
        try {
            consultaSQL =
                "select count(*) as TOTAL "+
                "from "+
                "ECF_VENDA_CABECALHO VC, ECF_TIPO_PAGAMENTO TP, ECF_TOTAL_TIPO_PGTO TTP "+
                "where "+
                "TTP.ID_ECF_VENDA_CABECALHO = VC.ID "+
                "and TTP.ID_ECF_TIPO_PAGAMENTO = TP.ID "+
                "and VC.ID = " + pId;
                    
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<R07VO> listaR07 = new ArrayList<R07VO>();
                consultaSQL = 
                    "select "+
                    "VC.ID AS VCID, TTP.HASH_TRIPA, TTP.HASH_INCREMENTO, TTP.CCF, TTP.COO, TTP.GNF, "+
                    "TTP.SERIE_ECF, TP.DESCRICAO, TTP.VALOR, TTP.ESTORNO "+
                    "from "+
                    "ECF_VENDA_CABECALHO VC, ECF_TIPO_PAGAMENTO TP, ECF_TOTAL_TIPO_PGTO TTP "+
                    "where "+
                    "TTP.ID_ECF_VENDA_CABECALHO = VC.ID "+
                    "and TTP.ID_ECF_TIPO_PAGAMENTO = TP.ID "+
                    "and VC.ID = " + pId;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    R07VO R07 = new R07VO();
                    R07.setCoo(rs.getInt("COO"));
                    R07.setCcf(rs.getInt("CCF"));
                    R07.setGnf(rs.getInt("GNF"));
                    R07.setSerieEcf(rs.getString("SERIE_ECF"));
                    R07.setHashTripa(rs.getString("HASH_TRIPA"));
                    R07.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    R07.setMeioPagamento(rs.getString("DESCRICAO"));
                    R07.setValorPagamento(rs.getDouble("VALOR"));
                    R07.setIndicadorEstorno(rs.getString("ESTORNO"));
                    R07.setValorEstorno(0D);
                    listaR07.add(R07);
                }
                return listaR07;
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

    public R01VO tabelaR01() {
        try {
            R01VO r01 = null;
            consultaSQL = "select * from R01";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            if (rs.first()) {
                r01 = new R01VO();
                r01.setId(rs.getInt("ID"));
                r01.setSerieEcf(rs.getString("SERIE_ECF"));
                r01.setCnpjEmpresa(rs.getString("CNPJ_EMPRESA"));
                r01.setCnpjSh(rs.getString("CNPJ_SH"));
                r01.setInscricaoEstadualSh(rs.getString("INSCRICAO_ESTADUAL_SH"));
                r01.setInscricaoMunicipalSh(rs.getString("INSCRICAO_MUNICIPAL_SH"));
                r01.setDenominacaoSh(rs.getString("DENOMINACAO_SH"));
                r01.setNomePafEcf(rs.getString("NOME_PAF_ECF"));
                r01.setVersaoPafEcf(rs.getString("VERSAO_PAF_ECF"));
                r01.setMd5PafEcf(rs.getString("MD5_PAF_ECF"));
                r01.setDataInicial(rs.getDate("DATA_INICIAL"));
                r01.setDataFinal(rs.getDate("DATA_FINAL"));
                r01.setVersaoEr(rs.getString("VERSAO_ER"));
                r01.setNumeroLaudoPaf(rs.getString("NUMERO_LAUDO_PAF"));
                r01.setRazaoSocialSh(rs.getString("RAZAO_SOCIAL_SH"));
                r01.setEnderecoSh(rs.getString("ENDERECO_SH"));
                r01.setNumeroSh(rs.getString("NUMERO_SH"));
                r01.setComplementoSh(rs.getString("COMPLEMENTO_SH"));
                r01.setBairroSh(rs.getString("BAIRRO_SH"));
                r01.setCidadeSh(rs.getString("CIDADE_SH"));
                r01.setCepSh(rs.getString("CEP_SH"));
                r01.setUfSh(rs.getString("UF_SH"));
                r01.setTelefoneSh(rs.getString("TELEFONE_SH"));
                r01.setContatoSh(rs.getString("CONTATO_SH"));
                r01.setPrincipalExecutavel(rs.getString("PRINCIPAL_EXECUTAVEL"));
                r01.setHashTripa(rs.getString("HASH_TRIPA"));
                r01.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
            }
            return r01;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public R06VO ultimoRegistroR06() {
        try {
            R06VO r06 = new R06VO();
            consultaSQL = "select * from R06 where ID = (select max(ID) as ULTIMO from R06)";

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                r06.setId(rs.getInt("ID"));
                r06.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                r06.setIdOperador(rs.getInt("ID_OPERADOR"));
                r06.setIdImpressora(rs.getInt("ID_IMPRESSORA"));
                r06.setCoo(rs.getInt("COO"));
                r06.setGnf(rs.getInt("GNF"));
                r06.setGrg(rs.getInt("GRG"));
                r06.setCdc(rs.getInt("CDC"));
                r06.setDenominacao(rs.getString("DENOMINACAO"));
                r06.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                r06.setHoraEmissao(rs.getString("HORA_EMISSAO"));
            }
            return r06;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public boolean verificaMovimentoPeriodo(Date dataInicio, Date dataFim) {
        try {
            consultaSQL = "select * from R02 where DATA_MOVIMENTO between ? and ?";

            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setDate(1, new java.sql.Date(dataInicio.getTime()));
            pstm.setDate(2, new java.sql.Date(dataFim.getTime()));
            rs = pstm.executeQuery();
            if (rs.first()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

}
