/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da unidade do produto</p>
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
package com.t2ti.integradorpaf.controller;

import com.t2ti.integradorpaf.bd.AcessoBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ImportaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

    public Boolean gravaCargaVendaCabecalho(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        try {
            consultaSQL =
                    "insert into "
                    + " ECF_VENDA_CABECALHO "
                    + "(NOME_CAIXA, "
                    + "ID_GERADO_CAIXA, "
                    + "ID_EMPRESA, "
                    + "ID_CLIENTE, "
                    + "ID_ECF_FUNCIONARIO, "
                    + "ID_ECF_MOVIMENTO, "
                    + "ID_ECF_DAV, "
                    + "ID_ECF_PRE_VENDA_CABECALHO, "
                    + "SERIE_ECF, "
                    + "CFOP, "
                    + "COO, "
                    + "CCF, "
                    + "DATA_VENDA, "
                    + "HORA_VENDA, "
                    + "VALOR_VENDA, "
                    + "TAXA_DESCONTO, "
                    + "DESCONTO, "
                    + "TAXA_ACRESCIMO, "
                    + "ACRESCIMO, "
                    + "VALOR_FINAL, "
                    + "VALOR_RECEBIDO, "
                    + "TROCO, "
                    + "VALOR_CANCELADO, "
                    + "TOTAL_PRODUTOS, "
                    + "TOTAL_DOCUMENTO, "
                    + "BASE_ICMS, "
                    + "ICMS, "
                    + "ICMS_OUTRAS, "
                    + "ISSQN, "
                    + "PIS, "
                    + "COFINS, "
                    + "ACRESCIMO_ITENS, "
                    + "DESCONTO_ITENS, "
                    + "STATUS_VENDA, "
                    + "NOME_CLIENTE, "
                    + "CPF_CNPJ_CLIENTE, "
                    + "CUPOM_CANCELADO, "
                    + "HASH_TRIPA, "
                    + "HASH_INCREMENTO, "
                    + "DATA_SINCRONIZACAO, "
                    + "HORA_SINCRONIZACAO)"
                    + " values ("
                    + tupla[2] + " ," //  "NOME_CAIXA, "+
                    + tupla[3] + " ," //  "ID_GERADO_CAIXA, "+
                    + tupla[4] + " ," //  "ID_EMPRESA, "+
                    + tupla[5] + " ," //  "ID_CLIENTE, "+
                    + tupla[6] + " ," //  "ID_ECF_FUNCIONARIO, "+
                    + tupla[7] + " ," //  "ID_ECF_MOVIMENTO, "+
                    + tupla[8] + " ," //  "ID_ECF_DAV, "+
                    + tupla[9] + " ," //  "ID_ECF_PRE_VENDA_CABECALHO, "+
                    + tupla[10] + " ," //  "SERIE_ECF, "+
                    + tupla[11] + " ," //  "CFOP, "+
                    + tupla[12] + " ," //  "COO, "+
                    + tupla[13] + " ," //  "CCF, "+
                    + tupla[14] + " ," //  "DATA_VENDA, "+
                    + tupla[15] + " ," //  "HORA_VENDA, "+
                    + tupla[16] + " ," //  "VALOR_VENDA, "+
                    + tupla[17] + " ," //  "TAXA_DESCONTO, "+
                    + tupla[18] + " ," //  "DESCONTO, "+
                    + tupla[19] + " ," //  "TAXA_ACRESCIMO, "+
                    + tupla[20] + " ," //  "ACRESCIMO, "+
                    + tupla[21] + " ," //  "VALOR_FINAL, "+
                    + tupla[22] + " ," //  "VALOR_RECEBIDO, "+
                    + tupla[23] + " ," //  "TROCO, "+
                    + tupla[24] + " ," //  "VALOR_CANCELADO, "+
                    + tupla[25] + " ," //  "TOTAL_PRODUTOS, "+
                    + tupla[26] + " ," //  "TOTAL_DOCUMENTO, "+
                    + tupla[27] + " ," //  "BASE_ICMS, "+
                    + tupla[28] + " ," //  "ICMS, "+
                    + tupla[29] + " ," //  "ICMS_OUTRAS, "+
                    + tupla[30] + " ," //  "ISSQN, "+
                    + tupla[31] + " ," //  "PIS, "+
                    + tupla[32] + " ," //  "COFINS, "+
                    + tupla[33] + " ," //  "ACRESCIMO_ITENS, "+
                    + tupla[34] + " ," //  "DESCONTO_ITENS, "+
                    + tupla[35] + " ," //  "STATUS_VENDA, "+
                    + tupla[36] + " ," //  "NOME_CLIENTE, "+
                    + tupla[37] + " ," //  "CPF_CNPJ_CLIENTE, "+
                    + tupla[38] + " ," //  "CUPOM_CANCELADO, "+
                    + tupla[39] + " ," //  "HASH_TRIPA, "+
                    + tupla[40] + " ," //  "HASH_TRIPA, "+
                    + "'" + new java.sql.Date(new java.util.Date().getTime()) + "' ," //  "DATA_SINCRONIZACAO, "+
                    + "'" + formatoHora.format(new java.util.Date().getTime()) + "')";               //  "HORA_SINCRONIZACAO[]"+

            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public Boolean gravaCargaVendaDetalhe(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        try {
            consultaSQL =
                    "insert into"
                    + " ECF_VENDA_DETALHE "
                    + "(NOME_CAIXA, "
                    + "ID_GERADO_CAIXA, "
                    + "ID_EMPRESA, "
                    + "ID_ECF_PRODUTO, "
                    + "ID_ECF_VENDA_CABECALHO, "
                    + "SERIE_ECF, "
                    + "GTIN, "
                    + "CCF, "
                    + "COO, "
                    + "CFOP, "
                    + "ITEM, "
                    + "QUANTIDADE, "
                    + "VALOR_UNITARIO, "
                    + "VALOR_TOTAL, "
                    + "TOTAL_ITEM, "
                    + "BASE_ICMS, "
                    + "TAXA_ICMS, "
                    + "ICMS, "
                    + "TAXA_DESCONTO, "
                    + "DESCONTO, "
                    + "TAXA_ISSQN, "
                    + "ISSQN, "
                    + "TAXA_PIS, "
                    + "PIS, "
                    + "TAXA_COFINS, "
                    + "COFINS, "
                    + "TAXA_ACRESCIMO, "
                    + "ACRESCIMO, "
                    + "ACRESCIMO_RATEIO, "
                    + "DESCONTO_RATEIO, "
                    + "TOTALIZADOR_PARCIAL, "
                    + "CST, "
                    + "CANCELADO, "
                    + "MOVIMENTA_ESTOQUE, "
                    + "ECF_ICMS_ST, "
                    + "HASH_TRIPA, "
                    + "HASH_INCREMENTO, "
                    + "DATA_SINCRONIZACAO, "
                    + "HORA_SINCRONIZACAO)"
                    + " values ("
                    + tupla[5] + " ," //  "NOME_CAIXA, "+
                    + tupla[6] + " ," //  "ID_GERADO_CAIXA, "+
                    + tupla[7] + " ," //  "ID_EMPRESA, "+
                    + tupla[8] + " ," //  "ID_ECF_PRODUTO, "+
                    + tupla[9] + " ," //  "ID_ECF_VENDA_CABECALHO, "+
                    + tupla[10] + " ," //  "SERIE_ECF, "+
                    + tupla[11] + " ," //  "GTIN, "+
                    + tupla[12] + " ," //  "CCF, "+
                    + tupla[13] + " ," //  "COO, "+
                    + tupla[14] + " ," //  "CFOP, "+
                    + tupla[15] + " ," //  "ITEM, "+
                    + tupla[16] + " ," //  "QUANTIDADE, "+
                    + tupla[17] + " ," //  "VALOR_UNITARIO, "+
                    + tupla[18] + " ," //  "VALOR_TOTAL, "+
                    + tupla[19] + " ," //  "TOTAL_ITEM, "+
                    + tupla[20] + " ," //  "BASE_ICMS, "+
                    + tupla[21] + " ," //  "TAXA_ICMS, "+
                    + tupla[22] + " ," //  "ICMS, "+
                    + tupla[23] + " ," //  "TAXA_DESCONTO, "+
                    + tupla[24] + " ," //  "DESCONTO, "+
                    + tupla[25] + " ," //  "TAXA_ISSQN, "+
                    + tupla[26] + " ," //  "ISSQN, "+
                    + tupla[27] + " ," //  "TAXA_PIS, "+
                    + tupla[28] + " ," //  "PIS, "+
                    + tupla[29] + " ," //  "TAXA_COFINS, "+
                    + tupla[30] + " ," //  "COFINS, "+
                    + tupla[31] + " ," //  "TAXA_ACRESCIMO, "+
                    + tupla[32] + " ," //  "ACRESCIMO, "+
                    + tupla[33] + " ," //  "ACRESCIMO_RATEIO, "+
                    + tupla[34] + " ," //  "DESCONTO_RATEIO, "+
                    + tupla[35] + " ," //  "TOTALIZADOR_PARCIAL, "+
                    + tupla[36] + " ," //  "CST, "+
                    + tupla[37] + " ," //  "CANCELADO, "+
                    + tupla[38] + " ," //  "MOVIMENTA_ESTOQUE, "+
                    + tupla[39] + " ," //  "ECF_ICMS_ST, "+
                    + tupla[40] + " ," //  "HASH_TRIPA, "+
                    + tupla[41] + " ," //  "HASH_INCREMENTO, "+
                    + "'" + new java.sql.Date(new java.util.Date().getTime()) + "' ," //  "DATA_SINCRONIZACAO, "+
                    + "'" + formatoHora.format(new java.util.Date().getTime()) + "')";               //  "HORA_SINCRONIZACAO[]"+

            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public Boolean gravaCargaTotalTipoPagamento(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        try {
            consultaSQL =
                    "insert into ECF_TOTAL_TIPO_PGTO "
                    + "(NOME_CAIXA, "
                    + "ID_GERADO_CAIXA, "
                    + "ID_EMPRESA, "
                    + "ID_ECF_VENDA_CABECALHO, "
                    + "ID_ECF_TIPO_PAGAMENTO, "
                    + "SERIE_ECF, "
                    + "COO, "
                    + "CCF, "
                    + "GNF, "
                    + "VALOR, "
                    + "NSU, "
                    + "ESTORNO, "
                    + "REDE, "
                    + "CARTAO_DC, "
                    + "HASH_TRIPA, "
                    + "HASH_INCREMENTO, "
                    + "DATA_SINCRONIZACAO, "
                    + "HORA_SINCRONIZACAO)"
                    + " values ("
                    + tupla[2] + " ," //  "NOME_CAIXA, "+
                    + tupla[3] + " ," //  "ID_GERADO_CAIXA, "+
                    + tupla[4] + " ," //  "ID_EMPRESA, "+
                    + tupla[5] + " ," //  "ID_ECF_VENDA_CABECALHO, "+
                    + tupla[6] + " ," //  "ID_ECF_TIPO_PAGAMENTO, "+
                    + tupla[7] + " ," //  "SERIE_ECF, "+
                    + tupla[8] + " ," //  "COO, "+
                    + tupla[9] + " ," //  "CCF, "+
                    + tupla[10] + " ," //  "GNF, "+
                    + tupla[11] + " ," //  "VALOR, "+
                    + tupla[12] + " ," //  "NSU, "+
                    + tupla[13] + " ," //  "ESTORNO, "+
                    + tupla[14] + " ," //  "REDE, "+
                    + tupla[15] + " ," //  "CARTAO_DC, "+
                    + tupla[16] + " ," //  "HASH_TRIPA, "+
                    + tupla[17] + " ," //  "HASH_INCREMENTO, "+
                    + "'" + new java.sql.Date(new java.util.Date().getTime()) + "' ," //  "DATA_SINCRONIZACAO, "+
                    + "'" + formatoHora.format(new java.util.Date().getTime()) + "')";               //  "HORA_SINCRONIZACAO[]"+

            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public Boolean gravaCargaSangria(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        try {
            consultaSQL =
                    "insert into"
                    + " ECF_SANGRIA "
                    + "(NOME_CAIXA, "
                    + "ID_GERADO_CAIXA, "
                    + "ID_ECF_MOVIMENTO, "
                    + "DATA_SANGRIA, "
                    + "VALOR, "
                    + "DATA_SINCRONIZACAO, "
                    + "HORA_SINCRONIZACAO)"
                    + " values ("
                    + tupla[2] + " ," //  "NOME_CAIXA, "+
                    + tupla[3] + " ," //  "ID_GERADO_CAIXA, "+
                    + tupla[4] + " ," //  "ID_ECF_MOVIMENTO, "+
                    + tupla[5] + " ," //  "DATA_SUPRIMENTO, "+
                    + tupla[6] + " ," //  "VALOR, "+
                    + "'" + new java.sql.Date(new java.util.Date().getTime()) + "' ," //  "DATA_SINCRONIZACAO, "+
                    + "'" + formatoHora.format(new java.util.Date().getTime()) + "')";               //  "HORA_SINCRONIZACAO[]"+

            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectarRetaguarda();
        }
    }

    public Boolean gravaCargaSuprimento(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        try {
            consultaSQL =
                    "insert into"
                    + " ECF_SUPRIMENTO "
                    + "(NOME_CAIXA, "
                    + "ID_GERADO_CAIXA, "
                    + "ID_ECF_MOVIMENTO, "
                    + "DATA_SUPRIMENTO, "
                    + "VALOR, "
                    + "DATA_SINCRONIZACAO, "
                    + "HORA_SINCRONIZACAO)"
                    + " values ("
                    + tupla[2] + " ," //  "NOME_CAIXA, "+
                    + tupla[3] + " ," //  "ID_GERADO_CAIXA, "+
                    + tupla[4] + " ," //  "ID_ECF_MOVIMENTO, "+
                    + tupla[5] + " ," //  "DATA_SUPRIMENTO, "+
                    + tupla[6] + " ," //  "VALOR, "+
                    + "'" + new java.sql.Date(new java.util.Date().getTime()) + "' ," //  "DATA_SINCRONIZACAO, "+
                    + "'" + formatoHora.format(new java.util.Date().getTime()) + "')";               //  "HORA_SINCRONIZACAO[]"+

            pstm = bd.conectarRetaguarda().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectarRetaguarda();
        }
    }
}