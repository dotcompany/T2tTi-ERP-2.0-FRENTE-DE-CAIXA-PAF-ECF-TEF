/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do produto</p>
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
import com.t2ti.pafecf.vo.ProdutoVO;
import com.t2ti.pafecf.vo.UnidadeProdutoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ProdutoVO consulta(String codigo) {
        ProdutoVO produtoVO = new ProdutoVO();
        UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
        produtoVO.setUnidadeProdutoVO(unidadeProdutoVO);
        consultaSQL =
                "select P.ID, P.ID_UNIDADE_PRODUTO, P.GTIN, P.CODIGO_INTERNO, "
                + "P.NOME AS NOME_PRODUTO, P.DESCRICAO, P.DESCRICAO_PDV, P.VALOR_VENDA, P.QTD_ESTOQUE, "
                + "P.ESTOQUE_MIN, P.ESTOQUE_MAX, P.IAT, P.IPPT, P.NCM, U.NOME AS NOME_UNIDADE, "
                + "U.PODE_FRACIONAR, P.ECF_ICMS_ST, P.CST, P.TAXA_ICMS, P.TOTALIZADOR_PARCIAL, U.PODE_FRACIONAR, "
                + "P.TAXA_IPI, P.TAXA_COFINS, P.TAXA_PIS, P.TAXA_ISSQN "
                + "from "
                + "PRODUTO P, "
                + "UNIDADE_PRODUTO U "
                + "where "
                + "P.GTIN = '" + codigo + "' "
                + "and P.ID_UNIDADE_PRODUTO = U.ID ";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                produtoVO.setId(rs.getInt("ID"));
                produtoVO.setGtin(rs.getString("GTIN"));
                produtoVO.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                produtoVO.setValorVenda(rs.getDouble("VALOR_VENDA"));
                produtoVO.setIdUnidade(rs.getInt("ID_UNIDADE_PRODUTO"));
                produtoVO.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                produtoVO.setNome(rs.getString("NOME_PRODUTO"));
                produtoVO.setDescricao(rs.getString("DESCRICAO"));
                produtoVO.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                produtoVO.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                produtoVO.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                produtoVO.setIat(rs.getString("IAT"));
                produtoVO.setIppt(rs.getString("IPPT"));
                produtoVO.setNcm(rs.getString("NCM"));
                produtoVO.setCst(rs.getString("CST"));
                produtoVO.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                produtoVO.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                produtoVO.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                produtoVO.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                produtoVO.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                produtoVO.setTaxaPis(rs.getDouble("TAXA_PIS"));
                produtoVO.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                produtoVO.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                produtoVO.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                return produtoVO;
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

    public ProdutoVO consulta(String pCodigo, Integer pTipo) {
        ProdutoVO produtoVO = new ProdutoVO();
        UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
        produtoVO.setUnidadeProdutoVO(unidadeProdutoVO);

        String clausulaWhere = "";

        switch (pTipo) {
            case 1: //código interno ou GTIN
                clausulaWhere =
                        " where "
                        + "((P.CODIGO_INTERNO = '" + pCodigo + "')"
                        + " or (P.GTIN = '" + pCodigo + "'))"
                        + " and (P.ID_UNIDADE_PRODUTO = U.ID)";
                break;
            case 2: //id
                clausulaWhere =
                        " where "
                        + "(P.ID = '" + pCodigo + "')"
                        + " and (P.ID_UNIDADE_PRODUTO = U.ID)";
                break;
        }

        consultaSQL =
                "select P.ID, P.ID_UNIDADE_PRODUTO, P.GTIN, P.CODIGO_INTERNO, "
                + "P.NOME AS NOME_PRODUTO, P.DESCRICAO, P.DESCRICAO_PDV, P.VALOR_VENDA, P.QTD_ESTOQUE, "
                + "P.ESTOQUE_MIN, P.ESTOQUE_MAX, P.IAT, P.IPPT, P.NCM, U.NOME AS NOME_UNIDADE, "
                + "U.PODE_FRACIONAR, P.ECF_ICMS_ST, P.CST, P.TAXA_ICMS, P.TOTALIZADOR_PARCIAL, U.PODE_FRACIONAR, "
                + "P.TAXA_IPI, P.TAXA_COFINS, P.TAXA_PIS, P.TAXA_ISSQN "
                + "from "
                + "PRODUTO P, "
                + "UNIDADE_PRODUTO U "
                + clausulaWhere;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                produtoVO.setId(rs.getInt("ID"));
                produtoVO.setGtin(rs.getString("GTIN"));
                produtoVO.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                produtoVO.setValorVenda(rs.getDouble("VALOR_VENDA"));
                produtoVO.setIdUnidade(rs.getInt("ID_UNIDADE_PRODUTO"));
                produtoVO.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                produtoVO.setNome(rs.getString("NOME_PRODUTO"));
                produtoVO.setDescricao(rs.getString("DESCRICAO"));
                produtoVO.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                produtoVO.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                produtoVO.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                produtoVO.setIat(rs.getString("IAT"));
                produtoVO.setIppt(rs.getString("IPPT"));
                produtoVO.setNcm(rs.getString("NCM"));
                produtoVO.setCst(rs.getString("CST"));
                produtoVO.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                produtoVO.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                produtoVO.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                produtoVO.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                produtoVO.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                produtoVO.setTaxaPis(rs.getDouble("TAXA_PIS"));
                produtoVO.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                produtoVO.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                produtoVO.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                return produtoVO;
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

    public ProdutoVO consultaId(Integer pId) {
        ProdutoVO produto = new ProdutoVO();
        UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
        produto.setUnidadeProdutoVO(unidadeProdutoVO);
        consultaSQL =
                "select P.ID, P.ID_UNIDADE_PRODUTO, P.GTIN, P.CODIGO_INTERNO, "
                + "P.NOME AS NOME_PRODUTO, P.DESCRICAO, P.DESCRICAO_PDV, P.VALOR_VENDA, "
                + "P.QTD_ESTOQUE, P.ESTOQUE_MIN, P.DATA_ESTOQUE, "
                + "P.ESTOQUE_MAX, P.IAT, P.IPPT, P.NCM, P.TAXA_ICMS, U.NOME AS NOME_UNIDADE, "
                + "U.PODE_FRACIONAR, P.ECF_ICMS_ST, P.CST, P.TOTALIZADOR_PARCIAL "
                + "from "
                + "PRODUTO P, UNIDADE_PRODUTO U "
                + "where P.ID=" + pId + " "
                + "and P.ID_UNIDADE_PRODUTO = U.ID ";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                produto.setId(rs.getInt("ID"));
                produto.setGtin(rs.getString("GTIN"));
                produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                produto.setIdUnidade(rs.getInt("ID_UNIDADE_PRODUTO"));
                produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                produto.setNome(rs.getString("NOME_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                produto.setIat(rs.getString("IAT"));
                produto.setIppt(rs.getString("IPPT"));
                produto.setNcm(rs.getString("NCM"));
                produto.setCst(rs.getString("CST"));
                produto.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                produto.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                produto.setDataEstoque(rs.getDate("DATA_ESTOQUE"));
                produto.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                return produto;
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

    public List<ProdutoVO> tabelaProduto() {
        consultaSQL =
                "select count(*) as TOTAL "
                + "from PRODUTO P, UNIDADE_PRODUTO U "
                + "where P.ID_UNIDADE_PRODUTO = U.ID ";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "select "
                        + " P.ID, "
                        + " P.ID_UNIDADE_PRODUTO, "
                        + " P.GTIN, "
                        + " P.CODIGO_INTERNO, "
                        + " P.NOME AS NOME_PRODUTO, "
                        + " P.DESCRICAO, "
                        + " P.DESCRICAO_PDV, "
                        + " P.VALOR_VENDA, "
                        + " P.QTD_ESTOQUE, "
                        + " P.QTD_ESTOQUE_ANTERIOR, "
                        + " P.ESTOQUE_MIN, "
                        + " P.ESTOQUE_MAX, "
                        + " P.IAT, "
                        + " P.IPPT, "
                        + " P.NCM, "
                        + " P.TIPO_ITEM_SPED, "
                        + " P.DATA_ESTOQUE, "
                        + " P.TAXA_IPI, "
                        + " P.TAXA_ISSQN, "
                        + " P.TAXA_PIS, "
                        + " P.TAXA_COFINS, "
                        + " P.TAXA_ICMS, "
                        + " P.CST, "
                        + " P.CSOSN, "
                        + " P.TOTALIZADOR_PARCIAL, "
                        + " P.ECF_ICMS_ST, "
                        + " P.CODIGO_BALANCA, "
                        + " P.PAF_P_ST, "
                        + " P.HASH_TRIPA, "
                        + " P.HASH_INCREMENTO, "
                        + " U.NOME AS NOME_UNIDADE, "
                        + " U.PODE_FRACIONAR "
                        + "from "
                        + " PRODUTO P, UNIDADE_PRODUTO U "
                        + "where "
                        + " (P.ID_UNIDADE_PRODUTO = U.ID)";


                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
                    produto.setUnidadeProdutoVO(unidadeProdutoVO);
                    produto.setId(rs.getInt("ID"));
                    produto.getUnidadeProdutoVO().setId(rs.getInt("ID_UNIDADE_PRODUTO"));
                    produto.setGtin(rs.getString("GTIN"));
                    produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                    produto.setNome(rs.getString("NOME_PRODUTO"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setQuantidadeEstoqueAnterior(rs.getDouble("QTD_ESTOQUE_ANTERIOR"));
                    produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                    produto.setIat(rs.getString("IAT"));
                    produto.setIppt(rs.getString("IPPT"));
                    produto.setNcm(rs.getString("NCM"));
                    produto.setTipoItemSped(rs.getString("TIPO_ITEM_SPED"));
                    produto.setDataEstoque(rs.getDate("DATA_ESTOQUE"));
                    produto.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                    produto.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                    produto.setTaxaPis(rs.getDouble("TAXA_PIS"));
                    produto.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                    produto.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                    produto.setCst(rs.getString("CST"));
                    produto.setCsosn(rs.getString("CSOSN"));
                    produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    produto.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                    produto.setCodigoBalanca(rs.getInt("CODIGO_BALANCA"));
                    produto.setPafProdutoSt(rs.getString("PAF_P_ST"));
                    produto.setHashTripa(rs.getString("HASH_TRIPA"));
                    produto.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    produto.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                    produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public List<ProdutoVO> tabelaProduto(Integer pCodigoInicio, Integer pCodigoFim) {
        consultaSQL =
                "select count(*) as TOTAL "
                + "from PRODUTO P, UNIDADE_PRODUTO U "
                + "where (P.ID_UNIDADE_PRODUTO = U.ID) "
                + "and (P.ID between " + pCodigoInicio + " and " + pCodigoFim + ")";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "select "
                        + " P.ID, "
                        + " P.ID_UNIDADE_PRODUTO, "
                        + " P.GTIN, "
                        + " P.CODIGO_INTERNO, "
                        + " P.NOME AS NOME_PRODUTO, "
                        + " P.DESCRICAO, "
                        + " P.DESCRICAO_PDV, "
                        + " P.VALOR_VENDA, "
                        + " P.QTD_ESTOQUE, "
                        + " P.QTD_ESTOQUE_ANTERIOR, "
                        + " P.ESTOQUE_MIN, "
                        + " P.ESTOQUE_MAX, "
                        + " P.IAT, "
                        + " P.IPPT, "
                        + " P.NCM, "
                        + " P.TIPO_ITEM_SPED, "
                        + " P.DATA_ESTOQUE, "
                        + " P.TAXA_IPI, "
                        + " P.TAXA_ISSQN, "
                        + " P.TAXA_PIS, "
                        + " P.TAXA_COFINS, "
                        + " P.TAXA_ICMS, "
                        + " P.CST, "
                        + " P.CSOSN, "
                        + " P.TOTALIZADOR_PARCIAL, "
                        + " P.ECF_ICMS_ST, "
                        + " P.CODIGO_BALANCA, "
                        + " P.PAF_P_ST, "
                        + " P.HASH_TRIPA, "
                        + " P.HASH_INCREMENTO, "
                        + " U.NOME AS NOME_UNIDADE, "
                        + " U.PODE_FRACIONAR "
                        + "from "
                        + " PRODUTO P, UNIDADE_PRODUTO U "
                        + "where "
                        + " (P.ID_UNIDADE_PRODUTO = U.ID)"
                        + " and (P.ID between " + pCodigoInicio + " and " + pCodigoFim + ")";


                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
                    produto.setUnidadeProdutoVO(unidadeProdutoVO);
                    produto.setId(rs.getInt("ID"));
                    produto.getUnidadeProdutoVO().setId(rs.getInt("ID_UNIDADE_PRODUTO"));
                    produto.setGtin(rs.getString("GTIN"));
                    produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                    produto.setNome(rs.getString("NOME_PRODUTO"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setQuantidadeEstoqueAnterior(rs.getDouble("QTD_ESTOQUE_ANTERIOR"));
                    produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                    produto.setIat(rs.getString("IAT"));
                    produto.setIppt(rs.getString("IPPT"));
                    produto.setNcm(rs.getString("NCM"));
                    produto.setTipoItemSped(rs.getString("TIPO_ITEM_SPED"));
                    produto.setDataEstoque(rs.getDate("DATA_ESTOQUE"));
                    produto.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                    produto.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                    produto.setTaxaPis(rs.getDouble("TAXA_PIS"));
                    produto.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                    produto.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                    produto.setCst(rs.getString("CST"));
                    produto.setCsosn(rs.getString("CSOSN"));
                    produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    produto.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                    produto.setCodigoBalanca(rs.getInt("CODIGO_BALANCA"));
                    produto.setPafProdutoSt(rs.getString("PAF_P_ST"));
                    produto.setHashTripa(rs.getString("HASH_TRIPA"));
                    produto.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    produto.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                    produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public List<ProdutoVO> tabelaProduto(String pNomeInicio, String pNomeFim) {
        consultaSQL =
                "select count(*) as TOTAL "
                + "from PRODUTO P, UNIDADE_PRODUTO U "
                + "where (P.ID_UNIDADE_PRODUTO = U.ID) "
                + "and (P.NOME like '%" + pNomeInicio.trim() + "%' and '%" + pNomeFim.trim() + "%')";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "select "
                        + " P.ID, "
                        + " P.ID_UNIDADE_PRODUTO, "
                        + " P.GTIN, "
                        + " P.CODIGO_INTERNO, "
                        + " P.NOME AS NOME_PRODUTO, "
                        + " P.DESCRICAO, "
                        + " P.DESCRICAO_PDV, "
                        + " P.VALOR_VENDA, "
                        + " P.QTD_ESTOQUE, "
                        + " P.QTD_ESTOQUE_ANTERIOR, "
                        + " P.ESTOQUE_MIN, "
                        + " P.ESTOQUE_MAX, "
                        + " P.IAT, "
                        + " P.IPPT, "
                        + " P.NCM, "
                        + " P.TIPO_ITEM_SPED, "
                        + " P.DATA_ESTOQUE, "
                        + " P.TAXA_IPI, "
                        + " P.TAXA_ISSQN, "
                        + " P.TAXA_PIS, "
                        + " P.TAXA_COFINS, "
                        + " P.TAXA_ICMS, "
                        + " P.CST, "
                        + " P.CSOSN, "
                        + " P.TOTALIZADOR_PARCIAL, "
                        + " P.ECF_ICMS_ST, "
                        + " P.CODIGO_BALANCA, "
                        + " P.PAF_P_ST, "
                        + " P.HASH_TRIPA, "
                        + " P.HASH_INCREMENTO, "
                        + " U.NOME AS NOME_UNIDADE, "
                        + " U.PODE_FRACIONAR "
                        + "from "
                        + " PRODUTO P, UNIDADE_PRODUTO U "
                        + "where "
                        + " (P.ID_UNIDADE_PRODUTO = U.ID)"
                        + "and (P.NOME like '%" + pNomeInicio.trim() + "%' and '%" + pNomeFim.trim() + "%')";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
                    produto.setUnidadeProdutoVO(unidadeProdutoVO);
                    produto.setId(rs.getInt("ID"));
                    produto.getUnidadeProdutoVO().setId(rs.getInt("ID_UNIDADE_PRODUTO"));
                    produto.setGtin(rs.getString("GTIN"));
                    produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                    produto.setNome(rs.getString("NOME_PRODUTO"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setQuantidadeEstoqueAnterior(rs.getDouble("QTD_ESTOQUE_ANTERIOR"));
                    produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                    produto.setIat(rs.getString("IAT"));
                    produto.setIppt(rs.getString("IPPT"));
                    produto.setNcm(rs.getString("NCM"));
                    produto.setTipoItemSped(rs.getString("TIPO_ITEM_SPED"));
                    produto.setDataEstoque(rs.getDate("DATA_ESTOQUE"));
                    produto.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                    produto.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                    produto.setTaxaPis(rs.getDouble("TAXA_PIS"));
                    produto.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                    produto.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                    produto.setCst(rs.getString("CST"));
                    produto.setCsosn(rs.getString("CSOSN"));
                    produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    produto.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                    produto.setCodigoBalanca(rs.getInt("CODIGO_BALANCA"));
                    produto.setPafProdutoSt(rs.getString("PAF_P_ST"));
                    produto.setHashTripa(rs.getString("HASH_TRIPA"));
                    produto.setHashIncremento(rs.getInt("HASH_INCREMENTO"));
                    produto.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                    produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public List<ProdutoVO> produtoFiltro(String filtroNome) {
        String procurePor = "%" + filtroNome + "%";
        consultaSQL =
                "SELECT COUNT(*) as TOTAL FROM PRODUTO "
                + "WHERE NOME LIKE '" + procurePor + "'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "SELECT P.*, U.NOME AS NOME_UNIDADE, U.PODE_FRACIONAR FROM PRODUTO P JOIN UNIDADE_PRODUTO U "
                        + "ON P.ID_UNIDADE_PRODUTO = U.ID "
                        + "WHERE P.NOME LIKE '" + procurePor + "'"
                        + " ORDER BY P.NOME";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
                    produto.setUnidadeProdutoVO(unidadeProdutoVO);
                    produto.setNome(rs.getString("NOME"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setGtin(rs.getString("GTIN"));
                    produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                    produto.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                    produto.setCst(rs.getString("CST"));
                    produto.setIat(rs.getString("IAT"));
                    produto.setIppt(rs.getString("IPPT"));
                    produto.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                    produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public List<ProdutoVO> consultaProdutoSped(String pDataInicial, String pDataFinal, Integer pPerfilApresentacao) {

        String clausulaWhere = "";
        consultaSQL =
                " select count(*) as TOTAL "
                + " from  PRODUTO P, UNIDADE_PRODUTO U, ECF_VENDA_CABECALHO V, ECF_VENDA_DETALHE D"
                + " where (V.DATA_VENDA between '" + pDataInicial + "' and '" + pDataFinal + "') "
                + " and (P.ID_UNIDADE_PRODUTO = U.ID)"
                + " and (V.ID=D.ID_ECF_VENDA_CABECALHO)"
                + " and (D.ID_ECF_PRODUTO=P.ID) group by D.ID_ECF_PRODUTO";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();

                switch (pPerfilApresentacao) {
                    case 0:
                        // Perfil A
                        clausulaWhere =
                                " where (v.DATA_VENDA between '" + pDataInicial + "' and '" + pDataFinal + "')"
                                + " and D.CANCELADO <> 'S'"
                                + " and (P.ID_UNIDADE_PRODUTO = U.ID)"
                                + " and (v.id=d.id_ecf_venda_cabecalho)"
                                + " and (d.id_ecf_produto=p.id)";
                        break;
                    case 1:
                        // Perfil B
                        clausulaWhere =
                                " where (v.DATA_VENDA between '" + pDataInicial + "' and '" + pDataFinal + "')"
                                + " and (P.ID_UNIDADE_PRODUTO = U.ID)"
                                + " and (v.id=d.id_ecf_venda_cabecalho)"
                                + " and (d.id_ecf_produto=p.id)";
                        break;
                    case 2:
                        // Perfil C
                        clausulaWhere =
                                " where (v.DATA_VENDA between '" + pDataInicial + "' and '" + pDataFinal + "')"
                                + " and (P.ID_UNIDADE_PRODUTO = U.ID)"
                                + " and (v.id=d.id_ecf_venda_cabecalho)"
                                + " and (d.id_ecf_produto=p.id)";
                        break;
                }

                consultaSQL =
                        "select distinct "
                        + " P.ID, "
                        + " P.ID_UNIDADE_PRODUTO, "
                        + " P.GTIN, "
                        + " P.CODIGO_INTERNO, "
                        + " P.NOME AS NOME_PRODUTO, "
                        + " P.DESCRICAO, "
                        + " P.DESCRICAO_PDV, "
                        + " P.VALOR_VENDA, "
                        + " P.QTD_ESTOQUE, "
                        + " P.QTD_ESTOQUE_ANTERIOR, "
                        + " P.ESTOQUE_MIN, "
                        + " P.ESTOQUE_MAX, "
                        + " P.IAT, "
                        + " P.IPPT, "
                        + " P.NCM, "
                        + " P.TIPO_ITEM_SPED, "
                        + " P.DATA_ESTOQUE, "
                        + " P.TAXA_IPI, "
                        + " P.TAXA_ISSQN, "
                        + " P.TAXA_PIS, "
                        + " P.TAXA_COFINS, "
                        + " P.TAXA_ICMS, "
                        + " P.CST, "
                        + " P.CSOSN, "
                        + " P.TOTALIZADOR_PARCIAL, "
                        + " P.ECF_ICMS_ST, "
                        + " P.CODIGO_BALANCA, "
                        + " P.PAF_P_ST, "
                        + " P.HASH_TRIPA, "
                        + " U.NOME AS NOME_UNIDADE, "
                        + " U.PODE_FRACIONAR "
                        + "from "
                        + " PRODUTO P, UNIDADE_PRODUTO U, ECF_VENDA_CABECALHO V, ECF_VENDA_DETALHE D"
                        + clausulaWhere;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
                    produto.setUnidadeProdutoVO(unidadeProdutoVO);

                    produto.setId(rs.getInt("ID"));
                    produto.setIdUnidade(rs.getInt("ID_UNIDADE_PRODUTO"));
                    produto.setGtin(rs.getString("GTIN"));
                    produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                    produto.setNome(rs.getString("NOME_PRODUTO"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setQuantidadeEstoqueAnterior(rs.getDouble("QTD_ESTOQUE_ANTERIOR"));
                    produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                    produto.setIat(rs.getString("IAT"));
                    produto.setIppt(rs.getString("IPPT"));
                    produto.setNcm(rs.getString("NCM"));
                    produto.setTipoItemSped(rs.getString("TIPO_ITEM_SPED"));
                    produto.setDataEstoque(rs.getDate("DATA_ESTOQUE"));
                    produto.setTaxaIpi(rs.getDouble("TAXA_IPI"));
                    produto.setTaxaIssqn(rs.getDouble("TAXA_ISSQN"));
                    produto.setTaxaPis(rs.getDouble("TAXA_PIS"));
                    produto.setTaxaCofins(rs.getDouble("TAXA_COFINS"));
                    produto.setTaxaIcms(rs.getDouble("TAXA_ICMS"));
                    produto.setCst(rs.getString("CST"));
                    produto.setCsosn(rs.getString("CSOSN"));
                    produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    produto.setEcfIcmsSt(rs.getString("ECF_ICMS_ST"));
                    produto.setCodigoBalanca(rs.getInt("CODIGO_BALANCA"));
                    produto.setPafProdutoSt(rs.getString("PAF_P_ST"));
                    produto.setHashTripa(rs.getString("HASH_TRIPA"));
                    produto.getUnidadeProdutoVO().setNome(rs.getString("NOME_UNIDADE"));
                    produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));

                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public void atualizaEstoque() {
        /*
         * TODO: -crie um procedimento para solicitar ao Integrador que
         * Se forem muitos os produtos cadastrados na retaguarda, retorne apenas
         * aqueles cujos QTD_ESTOQUE <> QTD_ESTOQUE_ANTERIOR. Observe como esse
         * controle é realizado no Integrador.
         * 
         * Esse método deve ser chamado apenas se for necessário atualizar o 
         * estoque no meio do expediente, pois as regras do PAF exigem que o
         * estoque seja atualizado logo no início do expediente, quando da emissão
         * do primeiro comprovante fiscal ou não fiscal.
         */
    }

    public List<ProdutoVO> produtoFiltro(String filtroNome, String ippt) {
        String procurePor = "%" + filtroNome + "%";
        consultaSQL =
                "SELECT COUNT(*) as TOTAL FROM PRODUTO "
                + "WHERE NOME LIKE '" + procurePor + "' and IPPT = '" + ippt + "'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "SELECT P.*, U.NOME, U.PODE_FRACIONAR FROM PRODUTO P JOIN UNIDADE_PRODUTO U "
                        + "ON P.ID_UNIDADE_PRODUTO = U.ID "
                        + "WHERE P.NOME LIKE '" + procurePor + "' and IPPT = '" + ippt + "' "
                        + " ORDER BY P.NOME";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    UnidadeProdutoVO unidadeProdutoVO = new UnidadeProdutoVO();
                    produto.setUnidadeProdutoVO(unidadeProdutoVO);
                    produto.setId(rs.getInt("ID"));
                    produto.setNome(rs.getString("P.NOME"));
                    produto.setValorVenda(rs.getDouble("P.VALOR_VENDA"));
                    produto.setDescricaoPDV(rs.getString("P.DESCRICAO_PDV"));
                    produto.setGtin(rs.getString("P.GTIN"));
                    produto.setCodigoInterno(rs.getString("P.CODIGO_INTERNO"));
                    produto.setQuantidadeEstoque(rs.getDouble("P.QTD_ESTOQUE"));
                    produto.setEstoqueMinimo(rs.getDouble("P.ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("P.ESTOQUE_MAX"));
                    produto.setEcfIcmsSt(rs.getString("P.ECF_ICMS_ST"));
                    produto.setCst(rs.getString("P.CST"));
                    produto.setIat(rs.getString("P.IAT"));
                    produto.setIppt(rs.getString("P.IPPT"));
                    produto.getUnidadeProdutoVO().setNome(rs.getString("U.NOME"));
                    produto.getUnidadeProdutoVO().setPodeFracionar(rs.getString("PODE_FRACIONAR"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public boolean consultaIdProduto(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " PRODUTO "
                + "where "
                + " ID = " + pId;

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public Boolean gravaCargaProduto(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdProduto(id)) {
                consultaSQL =
                        "INSERT INTO PRODUTO ("
                        + "ID, "
                        + "ID_UNIDADE_PRODUTO, "
                        + "GTIN, "
                        + "CODIGO_INTERNO, "
                        + "NOME, "
                        + "DESCRICAO, "
                        + "DESCRICAO_PDV, "
                        + "VALOR_VENDA, "
                        + "QTD_ESTOQUE, "
                        + "QTD_ESTOQUE_ANTERIOR, "
                        + "ESTOQUE_MIN, "
                        + "ESTOQUE_MAX, "
                        + "IAT, "
                        + "IPPT, "
                        + "NCM, "
                        + "TIPO_ITEM_SPED, "
                        + "DATA_ESTOQUE, "
                        + "TAXA_IPI, "
                        + "TAXA_ISSQN, "
                        + "TAXA_PIS, "
                        + "TAXA_COFINS, "
                        + "TAXA_ICMS, "
                        + "CST, "
                        + "CSOSN, "
                        + "TOTALIZADOR_PARCIAL, "
                        + "ECF_ICMS_ST, "
                        + "CODIGO_BALANCA, "
                        + "PAF_P_ST, "
                        + "HASH_INCREMENTO)"
                        + " values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    ID_UNIDADE_PRODUTO    INTEGER NOT NULL,
                        + tupla[3] + ", " //    GTIN                  VARCHAR(14),
                        + tupla[4] + ", " //    CODIGO_INTERNO        VARCHAR(20),
                        + tupla[5] + ", " //    NOME                  VARCHAR(100),
                        + tupla[6] + ", " //    DESCRICAO             VARCHAR(250),
                        + tupla[7] + ", " //    DESCRICAO_PDV         VARCHAR(30),
                        + tupla[8] + ", " //    VALOR_VENDA           DECIMAL(18,6),
                        + tupla[9] + ", " //    QTD_ESTOQUE           DECIMAL(18,6),
                        + tupla[10] + ", " //    QTD_ESTOQUE_ANTERIOR  DECIMAL(18,6),
                        + tupla[11] + ", " //    ESTOQUE_MIN           DECIMAL(18,6),
                        + tupla[12] + ", " //    ESTOQUE_MAX           DECIMAL(18,6),
                        + tupla[13] + ", " //    IAT                   CHAR(1),
                        + tupla[14] + ", " //    IPPT                  CHAR(1),
                        + tupla[15] + ", " //    NCM                   VARCHAR(8),
                        + tupla[16] + ", " //    TIPO_ITEM_SPED        CHAR(2),
                        + tupla[17] + ", " //    DATA_ESTOQUE          DATE,
                        + tupla[18] + ", " //    TAXA_IPI              DECIMAL(18,6),
                        + tupla[19] + ", " //    TAXA_ISSQN            DECIMAL(18,6),
                        + tupla[20] + ", " //    TAXA_PIS              DECIMAL(18,6),
                        + tupla[21] + ", " //    TAXA_COFINS           DECIMAL(18,6),
                        + tupla[22] + ", " //    TAXA_ICMS             DECIMAL(18,6),
                        + tupla[23] + ", " //    CST                   CHAR(3),
                        + tupla[24] + ", " //    CSOSN                 CHAR(4),
                        + tupla[25] + ", " //    TOTALIZADOR_PARCIAL   VARCHAR(10),
                        + tupla[26] + ", " //    ECF_ICMS_ST           VARCHAR(4),
                        + tupla[27] + ", " //    CODIGO_BALANCA        INTEGER,
                        + tupla[28] + ", " //    PAF_P_ST              CHAR(1),
                        + "-1)";           //    HASH_INCREMENTO       INTEGER
            } else {
                consultaSQL =
                        "update "
                        + " PRODUTO "
                        + "set "
                        + "ID_UNIDADE_PRODUTO =" + tupla[2] + "," //    ID_UNIDADE_PRODUTO
                        + "GTIN =" + tupla[3] + "," //    GTIN
                        + "CODIGO_INTERNO =" + tupla[4] + "," //    CODIGO_INTERNO
                        + "NOME =" + tupla[5] + "," //    NOME
                        + "DESCRICAO =" + tupla[6] + "," //    DESCRICAO
                        + "DESCRICAO_PDV =" + tupla[7] + "," //    DESCRICAO_PDV
                        + "VALOR_VENDA =" + tupla[8] + "," //    VALOR_VENDA
                        + "QTD_ESTOQUE =" + tupla[9] + "," //    QTD_ESTOQUE
                        + "QTD_ESTOQUE_ANTERIOR =" + tupla[10] + "," //    QTD_ESTOQUE_ANTERIOR
                        + "ESTOQUE_MIN =" + tupla[11] + "," //    ESTOQUE_MIN
                        + "ESTOQUE_MAX =" + tupla[12] + "," //    ESTOQUE_MAX
                        + "IAT =" + tupla[13] + "," //    IAT
                        + "IPPT =" + tupla[14] + "," //    IPPT
                        + "NCM =" + tupla[15] + "," //    NCM
                        + "TIPO_ITEM_SPED =" + tupla[16] + "," //    TIPO_ITEM_SPED
                        + "DATA_ESTOQUE =" + tupla[17] + "," //    DATA_ESTOQUE
                        + "TAXA_IPI =" + tupla[18] + "," //    TAXA_IPI
                        + "TAXA_ISSQN =" + tupla[19] + "," //    TAXA_ISSQN
                        + "TAXA_PIS =" + tupla[20] + "," //    TAXA_PIS
                        + "TAXA_COFINS =" + tupla[21] + "," //    TAXA_COFINS
                        + "TAXA_ICMS =" + tupla[22] + "," //    TAXA_ICMS
                        + "CST =" + tupla[23] + "," //    CST
                        + "CSOSN =" + tupla[24] + "," //    CSOSN
                        + "TOTALIZADOR_PARCIAL =" + tupla[25] + "," //    TOTALIZADOR_PARCIAL
                        + "ECF_ICMS_ST =" + tupla[26] + "," //    ECF_ICMS_ST
                        + "CODIGO_BALANCA =" + tupla[27] + "," //    CODIGO_BALANCA
                        + "PAF_P_ST =" + tupla[28] + "," //    PAF_P_ST
                        + "HASH_INCREMENTO =-1" //    HASH_INCREMENTO
                        + " where "
                        + "ID =" + id;
            }
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }
}
