/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do DAV</p>
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
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.vo.DAVCabecalhoVO;
import com.t2ti.pafecf.vo.DAVDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAVController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();
    java.util.Date d = new java.util.Date();

    public List<DAVDetalheVO> carregaDAV(Integer pId) {
        try {
            //verifica se existe o DAV solicitado
            consultaSQL = "select count(*) as TOTAL from DAV_CABECALHO where SITUACAO <> 'E' and SITUACAO <> 'M' and ID=" + pId;
            stm = bd.conectarRetaguarda().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                //verifica se existem itens para o DAV
                consultaSQL = "select count(*) as TOTAL from DAV_DETALHE where ID_DAV_CABECALHO = " + pId;
                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);

                rs.first();
                totalRegistros = rs.getInt("TOTAL");

                //caso existam itens no detalhe
                if (totalRegistros > 0) {
                    List<DAVDetalheVO> listaDAV = new ArrayList<DAVDetalheVO>();
                    consultaSQL = "select * from DAV_DETALHE where ID_DAV_CABECALHO = " + pId + " order by ITEM";
                    stm = bd.conectarRetaguarda().createStatement();
                    rs = stm.executeQuery(consultaSQL);

                    rs.beforeFirst();
                    while (rs.next()) {
                        DAVDetalheVO davDetalhe = new DAVDetalheVO();
                        davDetalhe.setId(rs.getInt("ID"));
                        davDetalhe.setIdDavCabecalho(pId);
                        davDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                        davDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                        davDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                        davDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                        davDetalhe.setItem(rs.getInt("ITEM"));
                        davDetalhe.setCancelado(rs.getString("CANCELADO"));
                        davDetalhe.setMesclaProduto(rs.getString("MESCLA_PRODUTO"));
                        davDetalhe.setGtinProduto(rs.getString("GTIN_PRODUTO"));
                        davDetalhe.setNomeProduto(rs.getString("NOME_PRODUTO"));
                        davDetalhe.setUnidadeProduto(rs.getString("UNIDADE_PRODUTO"));
                        davDetalhe.setNumeroDav(rs.getString("NUMERO_DAV"));
                        davDetalhe.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                        davDetalhe.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                        listaDAV.add(davDetalhe);
                    }
                    return listaDAV;
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

    public void fechaDAV(Integer pId, Integer pCCF, Integer pCOO) {
        try {
            Connection con = bd.conectarRetaguarda();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            DAVCabecalhoVO davCabecalho = consultaDAVCabecalho(pId);
            davCabecalho.setDataEmissao(Ecf.getDataHoraEcf());
            davCabecalho.setHoraEmissao(formatoHora.format(Ecf.getDataHoraEcf()));
            davCabecalho.setSituacao("E");
            String tripa = ""
                    + davCabecalho.getId()
                    + davCabecalho.getIdPessoa()
                    + pCCF
                    + pCOO
                    + davCabecalho.getNomeDestinatario()
                    + davCabecalho.getCpfCnpjDestinatario()
                    + formatoData.format(davCabecalho.getDataEmissao())
                    + davCabecalho.getHoraEmissao()
                    + davCabecalho.getSituacao()
                    + Biblioteca.formatoDecimal("V", davCabecalho.getTaxaAcrescimo())
                    + Biblioteca.formatoDecimal("V", davCabecalho.getAcrescimo())
                    + Biblioteca.formatoDecimal("V", davCabecalho.getTaxaDesconto())
                    + Biblioteca.formatoDecimal("V", davCabecalho.getDesconto())
                    + Biblioteca.formatoDecimal("V", davCabecalho.getSubTotal())
                    + Biblioteca.formatoDecimal("V", davCabecalho.getValor())
                    + davCabecalho.getNumeroDav()
                    + Caixa.aCBrECF.getNumECF()
                    + "0";
            davCabecalho.setHashTripa(Biblioteca.MD5String(tripa));

            consultaSQL =
                    "update DAV_CABECALHO set "
                    + "SITUACAO = ?, "
                    + "CCF = ?, "
                    + "COO = ?, "
                    + "NUMERO_ECF = ?,"
                    + "DATA_EMISSAO = ?,"
                    + "HORA_EMISSAO = ?,"
                    + "HASH_TRIPA = ?, "
                    + "HASH_INCREMENTO = -1 "
                    + " where ID = ?";

            pstm = con.prepareStatement(consultaSQL);

            pstm.setString(1, davCabecalho.getSituacao());
            pstm.setInt(2, pCCF);
            pstm.setInt(3, pCOO);
            pstm.setString(4, Caixa.aCBrECF.getNumECF());
            pstm.setDate(5, new java.sql.Date(davCabecalho.getDataEmissao().getTime()));
            pstm.setString(6, davCabecalho.getHoraEmissao());
            pstm.setString(7, davCabecalho.getHashTripa());
            pstm.setInt(8, pId);
            pstm.executeUpdate();

            DAVDetalheVO davDetalhe;
            for (int i = 0; i < davCabecalho.getListaDavDetalhe().size(); i++) {
                davDetalhe = davCabecalho.getListaDavDetalhe().get(i);
                tripa = ""
                        + davDetalhe.getId()
                        + davDetalhe.getIdDavCabecalho()
                        + davDetalhe.getIdProduto()
                        + davDetalhe.getNumeroDav()
                        + formatoData.format(davDetalhe.getDataEmissao())
                        + davDetalhe.getItem()
                        + Biblioteca.formatoDecimal("Q", davDetalhe.getQuantidade())
                        + Biblioteca.formatoDecimal("V", davDetalhe.getValorUnitario())
                        + Biblioteca.formatoDecimal("V", davDetalhe.getValorTotal())
                        + davDetalhe.getCancelado()
                        + davDetalhe.getMesclaProduto()
                        + davDetalhe.getGtinProduto()
                        + davDetalhe.getNomeProduto()
                        + davDetalhe.getTotalizadorParcial()
                        + davDetalhe.getUnidadeProduto()
                        + "0";
                davDetalhe.setHashTripa(Biblioteca.MD5String(tripa));

                consultaSQL = "update DAV_DETALHE set "
                        + "HASH_TRIPA = ?, "
                        + "HASH_INCREMENTO = ? "
                        + "where ID = ?";
                pstm = con.prepareStatement(consultaSQL);
                pstm.setString(1, davDetalhe.getHashTripa());
                pstm.setInt(2, -1);
                pstm.setInt(3, davDetalhe.getId());
                pstm.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public Integer mesclaDAV(List<DAVCabecalhoVO> pListaDAVCabecalho, String destinatario, String cnpjCpf) {
        //inicia e configura o novo DAV
        DAVCabecalhoVO novoDAV = new DAVCabecalhoVO();
        Date hoje = new Date();
        novoDAV.setDataEmissao(hoje);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        novoDAV.setHoraEmissao(formatoHora.format(hoje));
        novoDAV.setSituacao("P");
        novoDAV.setNomeDestinatario(destinatario);
        novoDAV.setCpfCnpjDestinatario(cnpjCpf);
        novoDAV.setIdPessoa(0);
        novoDAV.setTaxaAcrescimo(0.0);
        novoDAV.setAcrescimo(0.0);
        novoDAV.setTaxaDesconto(0.0);
        novoDAV.setDesconto(0.0);
        novoDAV.setSubTotal(0.0);

        String numeroUltimoDav = "";
        try {

            consultaSQL = "select NUMERO_DAV from DAV_CABECALHO where ID = (select max(ID) from DAV_CABECALHO)";
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            if (rs.first()) {
                numeroUltimoDav = rs.getString("NUMERO_DAV");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (numeroUltimoDav.equals("9999999999")) {
            novoDAV.setNumeroDav("0000000001");
        } else {
            int numeroNovoDav = Integer.valueOf(numeroUltimoDav) + 1;
            DecimalFormat formatoDav = new DecimalFormat("0000000000");
            novoDAV.setNumeroDav(formatoDav.format(numeroNovoDav));
        }

        //atualiza a tabela de cabecalho
        for (int i = 0; i < pListaDAVCabecalho.size(); i++) {
            if (pListaDAVCabecalho.get(i).getSelecao() != null) {
                if (pListaDAVCabecalho.get(i).getSelecao().equals("X")) {
                    //altera a situacao do DAV selecionado para M de mesclado
                    consultaSQL =
                            "update DAV_CABECALHO set "
                            + "SITUACAO = ? "
                            + " where ID = ?";

                    try {
                        pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
                        pstm.setString(1, "M");
                        pstm.setInt(2, pListaDAVCabecalho.get(i).getId());
                        pstm.executeUpdate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        bd.desconectarRetaguarda();
                    }
                }
            }
        }

        //cria um novo DAV
        consultaSQL =
                "insert into DAV_CABECALHO ("
                + "NOME_DESTINATARIO, "
                + "CPF_CNPJ_DESTINATARIO, "
                + "DATA_EMISSAO, "
                + "HORA_EMISSAO, "
                + "SITUACAO, "
                + "NUMERO_DAV, "
                + "ID_PESSOA, "
                + "TAXA_ACRESCIMO, "
                + "ACRESCIMO, "
                + "TAXA_DESCONTO, "
                + "DESCONTO, "
                + "SUBTOTAL, "
                + "HASH_INCREMENTO "
                + ") values ("
                + "?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.setString(1, novoDAV.getNomeDestinatario());
            pstm.setString(2, novoDAV.getCpfCnpjDestinatario());
            pstm.setDate(3, new java.sql.Date(novoDAV.getDataEmissao().getTime()));
            pstm.setString(4, novoDAV.getHoraEmissao());
            pstm.setString(5, novoDAV.getSituacao());
            pstm.setString(6, novoDAV.getNumeroDav());
            pstm.setInt(7, novoDAV.getIdPessoa());
            pstm.setDouble(8, novoDAV.getTaxaAcrescimo());
            pstm.setDouble(9, novoDAV.getAcrescimo());
            pstm.setDouble(10, novoDAV.getTaxaDesconto());
            pstm.setDouble(11, novoDAV.getDesconto());
            pstm.setDouble(12, novoDAV.getSubTotal());
            pstm.setInt(13, -1);
            pstm.executeUpdate();

            try {
                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from DAV_CABECALHO");
                rs.first();
                novoDAV.setId(rs.getInt("ID"));
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
            List<DAVDetalheVO> listaDAVDetalhe = new ArrayList<DAVDetalheVO>();
            String insiraDetalhe =
                    "insert into DAV_DETALHE ("
                    + "ID_PRODUTO, "
                    + "ID_DAV_CABECALHO, "
                    + "QUANTIDADE, "
                    + "VALOR_UNITARIO, "
                    + "VALOR_TOTAL, "
                    + "ITEM, "
                    + "CANCELADO, "
                    + "GTIN_PRODUTO, "
                    + "NOME_PRODUTO, "
                    + "UNIDADE_PRODUTO, "
                    + "MESCLA_PRODUTO, "
                    + "NUMERO_DAV, "
                    + "DATA_EMISSAO, "
                    + "TOTALIZADOR_PARCIAL, "
                    + "HASH_INCREMENTO"
                    + ") values ("
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            BigDecimal valorTotalNovoDav = BigDecimal.ZERO;

            int item = 0;
            for (int j = 0; j < pListaDAVCabecalho.size(); j++) {
                if (pListaDAVCabecalho.get(j).getSelecao() != null) {
                    listaDAVDetalhe = pListaDAVCabecalho.get(j).getListaDavDetalhe();
                    if (listaDAVDetalhe != null) {
                        for (int i = 0; i < listaDAVDetalhe.size(); i++) {
                            if (listaDAVDetalhe.get(i).getSelecao() != null) {
                                if (listaDAVDetalhe.get(i).getSelecao().equals("X")) {
                                    item++;
                                    valorTotalNovoDav = valorTotalNovoDav.add(BigDecimal.valueOf(listaDAVDetalhe.get(i).getValorTotal()));
                                    valorTotalNovoDav.setScale(2, RoundingMode.DOWN);
                                    pstm = bd.conectarRetaguarda().prepareStatement(insiraDetalhe);
                                    pstm.setInt(1, listaDAVDetalhe.get(i).getIdProduto());
                                    pstm.setInt(2, novoDAV.getId());
                                    pstm.setDouble(3, listaDAVDetalhe.get(i).getQuantidade());
                                    pstm.setDouble(4, listaDAVDetalhe.get(i).getValorUnitario());
                                    pstm.setDouble(5, listaDAVDetalhe.get(i).getValorTotal());
                                    pstm.setInt(6, item);
                                    pstm.setString(7, "N");
                                    pstm.setString(8, listaDAVDetalhe.get(i).getGtinProduto());
                                    pstm.setString(9, listaDAVDetalhe.get(i).getNomeProduto());
                                    pstm.setString(10, listaDAVDetalhe.get(i).getUnidadeProduto());
                                    pstm.setString(11, "N");
                                    pstm.setString(12, novoDAV.getNumeroDav());
                                    pstm.setDate(13, new java.sql.Date(listaDAVDetalhe.get(i).getDataEmissao().getTime()));
                                    pstm.setString(14, listaDAVDetalhe.get(i).getTotalizadorParcial());
                                    pstm.setInt(15, -1);
                                    pstm.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }
            novoDAV.setValor(valorTotalNovoDav.doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }

        consultaSQL = "update DAV_CABECALHO set "
                + "VALOR = ? "
                + " where ID = ?";

        try {
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.setDouble(1, novoDAV.getValor());
            pstm.setInt(2, novoDAV.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }

        return novoDAV.getId();
    }

    public List<DAVCabecalhoVO> listaDAVPeriodo(String pDataInicio, String pDataFim) {
        consultaSQL =
                "select count(*) AS TOTAL from DAV_CABECALHO where SITUACAO = 'E' and DATA_EMISSAO between '"
                + pDataInicio + "' and '" + pDataFim + "'";
        try {
            stm = bd.conectarRetaguarda().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<DAVCabecalhoVO> listaDAV = new ArrayList<DAVCabecalhoVO>();
                consultaSQL =
                        "select * from DAV_CABECALHO where SITUACAO = 'E' and DATA_EMISSAO between '"
                        + pDataInicio + "' and '" + pDataFim + "'";

                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    DAVCabecalhoVO davCabecalho = new DAVCabecalhoVO();
                    davCabecalho.setId(rs.getInt("ID"));
                    davCabecalho.setId(rs.getInt("ID_PESSOA"));
                    davCabecalho.setIdPessoa(rs.getInt("CCF"));
                    davCabecalho.setIdPessoa(rs.getInt("COO"));
                    davCabecalho.setNumeroDav(rs.getString("NUMERO_DAV"));
                    davCabecalho.setNumeroEcf(rs.getString("NUMERO_ECF"));
                    davCabecalho.setHoraEmissao(rs.getString("NOME_DESTINATARIO"));
                    davCabecalho.setHoraEmissao(rs.getString("CPF_CNPJ_DESTINATARIO"));
                    davCabecalho.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    davCabecalho.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    davCabecalho.setHoraEmissao(rs.getString("SITUACAO"));
                    davCabecalho.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                    davCabecalho.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    davCabecalho.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    davCabecalho.setDesconto(rs.getDouble("DESCONTO"));
                    davCabecalho.setSubTotal(rs.getDouble("SUBTOTAL"));
                    davCabecalho.setValor(rs.getDouble("VALOR"));
                    davCabecalho.setHoraEmissao(rs.getString("HASH_TRIPA"));
                    davCabecalho.setIdPessoa(rs.getInt("HASH_INCREMENTO"));
                    listaDAV.add(davCabecalho);
                }
                return listaDAV;
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

    public List<DAVDetalheVO> listaDavDetalhe(Integer pId) {
        consultaSQL =
                "select count(*) AS TOTAL from DAV_DETALHE where ID_DAV_CABECALHO = " + pId;

        try {
            stm = bd.conectarRetaguarda().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<DAVDetalheVO> listaDavDetalhe = new ArrayList<DAVDetalheVO>();
                consultaSQL =
                        "select * from DAV_DETALHE where ID_DAV_CABECALHO = " + pId;

                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    DAVDetalheVO davDetalhe = new DAVDetalheVO();

                    davDetalhe.setId(rs.getInt("ID"));
                    davDetalhe.setIdDavCabecalho(rs.getInt("ID_DAV_CABECALHO"));
                    davDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    davDetalhe.setNumeroDav(rs.getString("NUMERO_DAV"));
                    davDetalhe.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    davDetalhe.setGtinProduto(rs.getString("GTIN_PRODUTO"));
                    davDetalhe.setNomeProduto(rs.getString("NOME_PRODUTO"));
                    davDetalhe.setUnidadeProduto(rs.getString("UNIDADE_PRODUTO"));
                    davDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    davDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    davDetalhe.setItem(rs.getInt("ITEM"));
                    davDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    davDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    davDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    davDetalhe.setCancelado(rs.getString("CANCELADO"));
                    davDetalhe.setMesclaProduto(rs.getString("MESCLA_PRODUTO"));
                    davDetalhe.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    davDetalhe.setHashTripa(rs.getString("HASH_TRIPA"));
                    davDetalhe.setHashIncremento(rs.getInt("HASH_INCREMENTO"));

                    listaDavDetalhe.add(davDetalhe);
                }
                return listaDavDetalhe;
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

    public List<DAVCabecalhoVO> listaDAVPendente() {
        consultaSQL = "select count(*) AS TOTAL from DAV_CABECALHO where SITUACAO = 'P'";
        try {
            Connection con = bd.conectarRetaguarda();
            stm = con.createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<DAVCabecalhoVO> listaDAV = new ArrayList<DAVCabecalhoVO>();
                consultaSQL =
                        "select * from DAV_CABECALHO where SITUACAO = 'P'  ORDER BY ID";

                stm = bd.conectarRetaguarda().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();

                consultaSQL = "select * from DAV_DETALHE D JOIN PRODUTO P ON D.ID_PRODUTO = P.ID where CANCELADO <> 'S' and ID_DAV_CABECALHO = ?";
                ResultSet rs2;

                while (rs.next()) {
                    DAVCabecalhoVO davCabecalho = new DAVCabecalhoVO();
                    davCabecalho.setId(rs.getInt("ID"));
                    davCabecalho.setNomeDestinatario(rs.getString("NOME_DESTINATARIO"));
                    davCabecalho.setCpfCnpjDestinatario(rs.getString("CPF_CNPJ_DESTINATARIO"));
                    davCabecalho.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    davCabecalho.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                    davCabecalho.setValor(rs.getDouble("VALOR"));
                    davCabecalho.setNumeroDav(rs.getString("NUMERO_DAV"));
                    davCabecalho.setNumeroEcf(rs.getString("NUMERO_ECF"));
                    davCabecalho.setIdPessoa(rs.getInt("ID_PESSOA"));
                    davCabecalho.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                    davCabecalho.setAcrescimo(rs.getDouble("ACRESCIMO"));
                    davCabecalho.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    davCabecalho.setDesconto(rs.getDouble("DESCONTO"));
                    davCabecalho.setSubTotal(rs.getDouble("SUBTOTAL"));

                    pstm = con.prepareStatement(consultaSQL);
                    pstm.setInt(1, davCabecalho.getId());
                    rs2 = pstm.executeQuery();
                    List<DAVDetalheVO> listaDavDetalhe = new ArrayList<DAVDetalheVO>();
                    rs2.beforeFirst();
                    while (rs2.next()) {
                        DAVDetalheVO davDetalhe = new DAVDetalheVO();
                        davDetalhe.setId(rs2.getInt("d.id"));
                        davDetalhe.setIdDavCabecalho(davCabecalho.getId());
                        davDetalhe.setIdProduto(rs2.getInt("d.id_produto"));
                        davDetalhe.setNomeProduto(rs2.getString("p.descricao_pdv"));
                        davDetalhe.setItem(rs2.getInt("d.item"));
                        davDetalhe.setValorUnitario(rs2.getDouble("d.valor_unitario"));
                        davDetalhe.setValorTotal(rs2.getDouble("d.valor_total"));
                        davDetalhe.setNomeProduto(rs2.getString("d.nome_produto"));
                        davDetalhe.setGtinProduto(rs2.getString("d.gtin_produto"));
                        davDetalhe.setUnidadeProduto(rs2.getString("d.unidade_produto"));
                        davDetalhe.setQuantidade(rs2.getDouble("d.quantidade"));
                        davDetalhe.setNumeroDav(rs2.getString("d.numero_dav"));
                        davDetalhe.setTotalizadorParcial(rs2.getString("d.totalizador_parcial"));
                        davDetalhe.setDataEmissao(rs2.getDate("d.data_emissao"));
                        listaDavDetalhe.add(davDetalhe);
                    }
                    davCabecalho.setListaDavDetalhe(listaDavDetalhe);

                    listaDAV.add(davCabecalho);
                }
                return listaDAV;
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

    public DAVCabecalhoVO consultaDAVCabecalho(Integer idDav) {
        consultaSQL = "select * from DAV_CABECALHO where id = ?";
        DAVCabecalhoVO davCabecalho = null;
        try {
            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.setInt(1, idDav);

            rs = pstm.executeQuery();
            if (rs.first()) {
                davCabecalho = new DAVCabecalhoVO();
                davCabecalho.setId(rs.getInt("ID"));
                davCabecalho.setCcf(rs.getInt("CCF"));
                davCabecalho.setCoo(rs.getInt("COO"));
                davCabecalho.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                davCabecalho.setHoraEmissao(rs.getString("HORA_EMISSAO"));
                davCabecalho.setCpfCnpjDestinatario(rs.getString("CPF_CNPJ_DESTINATARIO"));
                davCabecalho.setNomeDestinatario(rs.getString("NOME_DESTINATARIO"));
                davCabecalho.setValor(rs.getDouble("VALOR"));
                davCabecalho.setSituacao(rs.getString("SITUACAO"));
                davCabecalho.setNumeroDav(rs.getString("NUMERO_DAV"));
                davCabecalho.setNumeroEcf(rs.getString("NUMERO_ECF"));
                davCabecalho.setIdPessoa(rs.getInt("ID_PESSOA"));
                davCabecalho.setTaxaAcrescimo(rs.getDouble("TAXA_ACRESCIMO"));
                davCabecalho.setAcrescimo(rs.getDouble("ACRESCIMO"));
                davCabecalho.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                davCabecalho.setDesconto(rs.getDouble("DESCONTO"));
                davCabecalho.setSubTotal(rs.getDouble("SUBTOTAL"));

                consultaSQL = "select * from DAV_DETALHE D JOIN PRODUTO P ON D.ID_PRODUTO = P.ID where ID_DAV_CABECALHO = ?";
                List<DAVDetalheVO> listaDavDetalhe = new ArrayList<DAVDetalheVO>();
                pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
                pstm.setInt(1, idDav);
                ResultSet rs2 = pstm.executeQuery();
                rs2.beforeFirst();
                while (rs2.next()) {
                    DAVDetalheVO davDetalhe = new DAVDetalheVO();
                    davDetalhe.setId(rs2.getInt("d.id"));
                    davDetalhe.setIdDavCabecalho(davCabecalho.getId());
                    davDetalhe.setIdProduto(rs2.getInt("d.id_produto"));
                    davDetalhe.setNomeProduto(rs2.getString("p.descricao_pdv"));
                    davDetalhe.setItem(rs2.getInt("d.item"));
                    davDetalhe.setValorUnitario(rs2.getDouble("d.valor_unitario"));
                    davDetalhe.setValorTotal(rs2.getDouble("d.valor_total"));
                    davDetalhe.setNomeProduto(rs2.getString("d.nome_produto"));
                    davDetalhe.setGtinProduto(rs2.getString("d.gtin_produto"));
                    davDetalhe.setUnidadeProduto(rs2.getString("d.unidade_produto"));
                    davDetalhe.setQuantidade(rs2.getDouble("d.quantidade"));
                    davDetalhe.setNumeroDav(rs2.getString("d.numero_dav"));
                    davDetalhe.setTotalizadorParcial(rs2.getString("d.totalizador_parcial"));
                    davDetalhe.setDataEmissao(rs2.getDate("d.data_emissao"));
                    davDetalhe.setCancelado(rs2.getString("d.cancelado"));
                    davDetalhe.setMesclaProduto(rs2.getString("d.mescla_produto"));
                    listaDavDetalhe.add(davDetalhe);
                }
                davCabecalho.setListaDavDetalhe(listaDavDetalhe);
            }
            return davCabecalho;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectarRetaguarda();
        }
        return davCabecalho;
    }
}
