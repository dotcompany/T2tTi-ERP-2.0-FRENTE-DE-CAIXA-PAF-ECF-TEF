/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do total de tipo de pagamento</p>
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
import com.t2ti.pafecf.vo.MeiosPagamentoVO;
import com.t2ti.pafecf.vo.TotalTipoPagamentoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TotalTipoPagamentoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public void gravaTotaisVenda(ArrayList<TotalTipoPagamentoVO> pListaTotalTipoPagamento) {

        TotalTipoPagamentoVO totalTipoPagamento;

        try {
            int coo = Integer.valueOf(Caixa.aCBrECF.getNumCOO());
            int ccf = Integer.valueOf(Caixa.aCBrECF.getNumCCF());
            int gnf = Integer.valueOf(Caixa.aCBrECF.getNumGNF());

            for (int i = 0; i < pListaTotalTipoPagamento.size(); i++) {
                totalTipoPagamento = pListaTotalTipoPagamento.get(i);
                consultaSQL =
                        "insert into ECF_TOTAL_TIPO_PGTO ("
                        + "ID_ECF_VENDA_CABECALHO, "
                        + "ID_ECF_TIPO_PAGAMENTO, "
                        + "VALOR, "
                        + "NSU, "
                        + "REDE, "
                        + "ESTORNO, "
                        + "CARTAO_DC, "
                        + "SERIE_ECF, "
                        + "COO, "
                        + "CCF, "
                        + "GNF, "
                        + "HASH_TRIPA, "
                        + "HASH_INCREMENTO) "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setInt(1, totalTipoPagamento.getIdVendaCabecalho());
                pstm.setInt(2, totalTipoPagamento.getTipoPagamentoVO().getId());
                pstm.setDouble(3, totalTipoPagamento.getValor());
                if (totalTipoPagamento.getNsu() != null) {
                    pstm.setString(4, totalTipoPagamento.getNsu());
                } else {
                    pstm.setNull(4, java.sql.Types.VARCHAR);
                }
                if (totalTipoPagamento.getNomeRede() != null) {
                    pstm.setString(5, totalTipoPagamento.getNomeRede());
                } else {
                    pstm.setNull(5, java.sql.Types.VARCHAR);
                }
                if (totalTipoPagamento.getEstorno() != null) {
                    pstm.setString(6, totalTipoPagamento.getEstorno());
                } else {
                    pstm.setNull(6, java.sql.Types.VARCHAR);
                }
                if (totalTipoPagamento.getCartaoDc() != null) {
                    pstm.setString(7, totalTipoPagamento.getCartaoDc());
                } else {
                    pstm.setNull(7, java.sql.Types.VARCHAR);
                }

                pstm.setString(8, Caixa.configuracao.getImpressoraVO().getSerie());
                pstm.setInt(9, coo);
                pstm.setInt(10, ccf);
                pstm.setInt(11, gnf);

                String tripa = Caixa.configuracao.getImpressoraVO().getSerie()
                        + coo
                        + ccf
                        + gnf
                        + "0";

                pstm.setString(12, Biblioteca.MD5String(tripa));
                pstm.setInt(13, 0);

                pstm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public List<MeiosPagamentoVO> meiosPagamento(String pDataInicio, String pDataFim, Integer pIdImpressora) {
        consultaSQL =
                "SELECT V.DATA_VENDA, M.ID_ECF_IMPRESSORA, P.DESCRICAO, SUM(TP.VALOR) AS TOTAL "
                + "FROM "
                + "ECF_VENDA_CABECALHO V, ECF_MOVIMENTO M, ECF_TIPO_PAGAMENTO P, ECF_TOTAL_TIPO_PGTO TP "
                + "WHERE "
                + "V.ID_ECF_MOVIMENTO = M.ID AND "
                + "TP.ID_ECF_VENDA_CABECALHO=V.ID AND "
                + "TP.ID_ECF_TIPO_PAGAMENTO = P.ID AND "
                + "M.ID_ECF_IMPRESSORA = " + pIdImpressora + " AND "
                + "(V.DATA_VENDA BETWEEN '" + pDataInicio + "' and '" + pDataFim
                + "') GROUP BY "
                + "P.DESCRICAO,V.DATA_VENDA,M.ID_ECF_IMPRESSORA";

        try {
            List<MeiosPagamentoVO> listaMeiosPagamento = new ArrayList<MeiosPagamentoVO>();

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            while (rs.next()) {
                MeiosPagamentoVO meiosPagamento = new MeiosPagamentoVO();
                meiosPagamento.setDescricao(rs.getString("DESCRICAO"));
                meiosPagamento.setDataHora(rs.getDate("DATA_VENDA"));
                meiosPagamento.setTotal(rs.getDouble("TOTAL"));
                listaMeiosPagamento.add(meiosPagamento);
            }
            return listaMeiosPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public List<TotalTipoPagamentoVO> retornaMeiosPagamentoDaUltimaVenda(Integer pIdCabecalho) {
        consultaSQL =
                "select "
                + " T.ID, "
                + " T.ID_ECF_VENDA_CABECALHO, "
                + " T.ID_ECF_TIPO_PAGAMENTO, "
                + " T.VALOR, "
                + " T.NSU, "
                + " T.ESTORNO, "
                + " T.REDE, "
                + " T.CARTAO_DC, "
                + " P.DESCRICAO "
                + "from "
                + " ECF_TIPO_PAGAMENTO  P, ECF_TOTAL_TIPO_PGTO T "
                + "where "
                + " (ID_ECF_VENDA_CABECALHO = " + pIdCabecalho + ")  "
                + " and (P.ID = T.ID_ECF_TIPO_PAGAMENTO) order by T.ID_ECF_TIPO_PAGAMENTO";

        try {
            List<TotalTipoPagamentoVO> listaTotalTipoPagamento = new ArrayList<TotalTipoPagamentoVO>();

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            while (rs.next()) {
                TotalTipoPagamentoVO totalTipoPagamento = new TotalTipoPagamentoVO();
                
                totalTipoPagamento.setId(rs.getInt("ID"));
                totalTipoPagamento.setIdVendaCabecalho(rs.getInt("ID_ECF_VENDA_CABECALHO"));
                totalTipoPagamento.setIdTipoPagamento(rs.getInt("ID_ECF_TIPO_PAGAMENTO"));
                totalTipoPagamento.setValor(rs.getDouble("VALOR"));
                totalTipoPagamento.setNsu(rs.getString("NSU"));
                totalTipoPagamento.setEstorno(rs.getString("ESTORNO"));
                totalTipoPagamento.setNomeRede(rs.getString("REDE"));
                totalTipoPagamento.setCartaoDc(rs.getString("CARTAO_DC"));
                totalTipoPagamento.setDescricao(rs.getString("DESCRICAO"));
                
                listaTotalTipoPagamento.add(totalTipoPagamento);
            }
            return listaTotalTipoPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }
}
