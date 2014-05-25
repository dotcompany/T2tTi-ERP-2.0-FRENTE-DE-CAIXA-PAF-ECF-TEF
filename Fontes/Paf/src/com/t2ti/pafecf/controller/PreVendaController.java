/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da pré-venda</p>
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
import com.t2ti.pafecf.infra.Ecf;
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.vo.PreVendaCabecalhoVO;
import com.t2ti.pafecf.vo.PreVendaDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PreVendaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<PreVendaDetalheVO> carregaPreVenda(Integer pId) {
        try {
            //verifica se existe a pre-venda solicitada
            consultaSQL = "select count(*) as TOTAL from PRE_VENDA_CABECALHO where SITUACAO <> 'E' and SITUACAO <> 'M' and SITUACAO <> 'C' and ID = " + pId;
            stm = bd.conectarRetaguarda().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                //verifica se existem itens para a pre-venda
                consultaSQL = "select count(*) as TOTAL from PRE_VENDA_DETALHE where ID_PRE_VENDA_CABECALHO=" + pId;
                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);

                rs.first();
                totalRegistros = rs.getInt("TOTAL");

                //caso existam itens no detalhe
                if (totalRegistros > 0) {
                    List<PreVendaDetalheVO> listaPV = new ArrayList<PreVendaDetalheVO>();
                    consultaSQL = "select * from PRE_VENDA_DETALHE where ID_PRE_VENDA_CABECALHO=" + pId;
                    stm = bd.conectarRetaguarda().createStatement();
                    rs = stm.executeQuery(consultaSQL);

                    rs.beforeFirst();
                    while (rs.next()) {
                        PreVendaDetalheVO preVendaDetalhe = new PreVendaDetalheVO();
                        preVendaDetalhe.setId(rs.getInt("ID"));
                        preVendaDetalhe.setIdPreVendaCabecalho(pId);
                        preVendaDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                        preVendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                        preVendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                        preVendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                        listaPV.add(preVendaDetalhe);
                    }
                    return listaPV;
                } else {
                    //caso nao existam registros retorna nulo
                    return null;
                }
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public void fechaPreVenda(Integer pId, Integer pCCF) {
        consultaSQL =
                "update PRE_VENDA_CABECALHO set "
                + "SITUACAO=?, "
                + "CCF=? "
                + " where ID = ?";

        try {
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);

            pstm.setString(1, "E");
            pstm.setInt(2, pCCF);
            pstm.setInt(3, pId);
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public Integer mesclaPreVenda(List<PreVendaCabecalhoVO> pListaPreVendaCabecalho) {
        //inicia e configura a nova Pre-Venda
        PreVendaCabecalhoVO novaPreVenda = new PreVendaCabecalhoVO();
        Calendar hoje = Calendar.getInstance();
        novaPreVenda.setDataEmissao(hoje.getTime());
        novaPreVenda.setHoraEmissao(hoje.get(Calendar.HOUR_OF_DAY) + ":" + hoje.get(Calendar.MINUTE) + ":" + hoje.get(Calendar.SECOND));
        novaPreVenda.setSituacao("P");

        BigDecimal totalPreVenda = BigDecimal.ZERO;
        //atualiza a tabela de cabecalho
        for (int i = 0; i < pListaPreVendaCabecalho.size(); i++) {
            totalPreVenda = totalPreVenda.add(BigDecimal.valueOf(pListaPreVendaCabecalho.get(i).getValor()));
            totalPreVenda.setScale(Caixa.configuracao.getDecimaisValor(), RoundingMode.DOWN);

            //altera a situacao da PV selecionada para M de mesclada
            consultaSQL =
                    "update PRE_VENDA_CABECALHO set "
                    + "SITUACAO=? "
                    + " where ID = ?";

            try {
                pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
                pstm.setString(1, "M");
                pstm.setInt(2, pListaPreVendaCabecalho.get(i).getId());
                pstm.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bd.desconectarRetaguarda();
            }
        }
        novaPreVenda.setValor(totalPreVenda.doubleValue());

        //cria uma nova PV
        consultaSQL =
                "insert into PRE_VENDA_CABECALHO ("
                + "DATA_PV, "
                + "HORA_PV, "
                + "SITUACAO, "
                + "VALOR) values ("
                + "?,?,?,?)";

        try {
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.setDate(1, new java.sql.Date(novaPreVenda.getDataEmissao().getTime()));
            pstm.setString(2, novaPreVenda.getHoraEmissao());
            pstm.setString(3, novaPreVenda.getSituacao());
            pstm.setDouble(4, novaPreVenda.getValor());
            pstm.executeUpdate();

            try {
                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from PRE_VENDA_CABECALHO");
                rs.first();
                novaPreVenda.setId(rs.getInt("ID"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }

        try {
            //atualiza a tabela de detalhes
            List<PreVendaDetalheVO> listaPreVendaDetalhe = new ArrayList<PreVendaDetalheVO>();
            String insiraDetalhe =
                    "insert into PRE_VENDA_DETALHE ("
                    + "ID_PRODUTO,"
                    + "ID_PRE_VENDA_CABECALHO,"
                    + "QUANTIDADE,"
                    + "VALOR_UNITARIO,"
                    + "VALOR_TOTAL) values ("
                    + "?,?,?,?,?)";
            for (int j = 0; j < pListaPreVendaCabecalho.size(); j++) {
               if (pListaPreVendaCabecalho.get(j).getSelecao() != null) {
                    listaPreVendaDetalhe = listaPreVendaDetalhePendente(pListaPreVendaCabecalho.get(j).getId());
                    if (listaPreVendaDetalhe != null) {
                        for (int i = 0; i < listaPreVendaDetalhe.size(); i++) {
                            pstm = bd.conectarRetaguarda().prepareStatement(insiraDetalhe);
                            pstm.setInt(1, listaPreVendaDetalhe.get(i).getIdProduto());
                            pstm.setInt(2, novaPreVenda.getId());
                            pstm.setDouble(3, listaPreVendaDetalhe.get(i).getQuantidade());
                            pstm.setDouble(4, listaPreVendaDetalhe.get(i).getValorUnitario());
                            pstm.setDouble(5, listaPreVendaDetalhe.get(i).getValorTotal());
                            pstm.executeUpdate();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }
        return novaPreVenda.getId();
    }

    public List<PreVendaCabecalhoVO> cancelaPreVendasPendentes() {
        //verifica se existem PV pendentes
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
            String dataEcf = formatoData.format(Ecf.getDataHoraEcf());
            consultaSQL =
                    "select * from PRE_VENDA_CABECALHO where SITUACAO = 'P' "
                    + "and DATA_PV < '" + dataEcf + "'";
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            ResultSet rsPreVenda = pstm.executeQuery();

            List<PreVendaCabecalhoVO> listaPreVendas = new ArrayList<PreVendaCabecalhoVO>();
            PreVendaCabecalhoVO prevendaCabecalho;
            while (rsPreVenda.next()) {

                prevendaCabecalho = new PreVendaCabecalhoVO();

                prevendaCabecalho.setId(rsPreVenda.getInt("ID"));
                prevendaCabecalho.setDataEmissao(rsPreVenda.getDate("DATA_PV"));
                prevendaCabecalho.setHoraEmissao(rsPreVenda.getString("HORA_PV"));
                prevendaCabecalho.setCcf(rsPreVenda.getInt("CCF"));
                prevendaCabecalho.setSituacao(rsPreVenda.getString("SITUACAO"));
                prevendaCabecalho.setValor(rsPreVenda.getDouble("VALOR"));
                prevendaCabecalho.setPreVendaDetalhe(listaPreVendaDetalhePendente(rsPreVenda.getInt("ID")));

                listaPreVendas.add(prevendaCabecalho);
            }

            //atualiza situacao no banco de dados
            consultaSQL =
                    "update PRE_VENDA_CABECALHO set "
                    + "SITUACAO='C' "
                    + " where situacao = 'P' and DATA_PV < '" + dataEcf + "'";
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.executeUpdate();

            return listaPreVendas;
            //cancelaPreVendasPendentes(listsPreVendas);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }
        return null;
    }

    public void cancelaPreVendas(List<PreVendaCabecalhoVO> listaPreVendas) {
        try {
            for (int i = 0; i < listaPreVendas.size(); i++) {
                consultaSQL = "update PRE_VENDA_CABECALHO set "
                        + "SITUACAO = 'C' "
                        + " where ID = " + listaPreVendas.get(i).getId();
                pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PreVendaCabecalhoVO> listaPreVendaPendente() {
        consultaSQL =
                "select count(*) AS TOTAL from PRE_VENDA_CABECALHO where SITUACAO = 'P'";
        try {
            stm = bd.conectarRetaguarda().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<PreVendaCabecalhoVO> listaPreVenda = new ArrayList<PreVendaCabecalhoVO>();
                consultaSQL =
                        "select * from PRE_VENDA_CABECALHO where SITUACAO = 'P' ORDER BY ID";

                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    PreVendaCabecalhoVO preVendaCabecalho = new PreVendaCabecalhoVO();
                    preVendaCabecalho.setId(rs.getInt("ID"));
                    preVendaCabecalho.setDataEmissao(rs.getDate("DATA_PV"));
                    preVendaCabecalho.setHoraEmissao(rs.getString("HORA_PV"));
                    preVendaCabecalho.setValor(rs.getDouble("VALOR"));
                    listaPreVenda.add(preVendaCabecalho);
                }
                return listaPreVenda;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public List<PreVendaDetalheVO> listaPreVendaDetalhePendente(Integer IdPreVendaCabecalho) {
        consultaSQL =
                "SELECT count(*) as TOTAL FROM PRE_VENDA_DETALHE D JOIN PRODUTO P "
                + "ON D.ID_PRODUTO=P.ID where ID_PRE_VENDA_CABECALHO=" + IdPreVendaCabecalho;
        try {
            stm = bd.conectarRetaguarda().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<PreVendaDetalheVO> listaPreVendaDetalhe = new ArrayList<PreVendaDetalheVO>();
                consultaSQL =
                        "SELECT "
                        + "D.ID,D.ID_PRODUTO,D.ID_PRE_VENDA_CABECALHO,D.QUANTIDADE, "
                        + "D.VALOR_UNITARIO, D.VALOR_TOTAL, P.DESCRICAO_PDV "
                        + "FROM PRE_VENDA_DETALHE D JOIN PRODUTO P "
                        + "ON D.ID_PRODUTO=P.ID "
                        + "where ID_PRE_VENDA_CABECALHO=" + IdPreVendaCabecalho;

                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    PreVendaDetalheVO preVendaDetalhe = new PreVendaDetalheVO();
                    preVendaDetalhe.setId(rs.getInt("ID"));
                    preVendaDetalhe.setIdPreVendaCabecalho(rs.getInt("ID_PRE_VENDA_CABECALHO"));
                    preVendaDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    preVendaDetalhe.setNomeProduto(rs.getString("DESCRICAO_PDV"));
                    preVendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    preVendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    preVendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    listaPreVendaDetalhe.add(preVendaDetalhe);
                }
                return listaPreVendaDetalhe;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectarRetaguarda();
        }
    }
}
