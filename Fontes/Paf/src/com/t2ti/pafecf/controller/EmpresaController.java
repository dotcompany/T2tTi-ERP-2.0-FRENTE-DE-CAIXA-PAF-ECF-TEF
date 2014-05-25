/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da empresa</p>
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
import com.t2ti.pafecf.vo.EmpresaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpresaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public EmpresaVO pegaEmpresa(Integer pId) {
        EmpresaVO empresa = new EmpresaVO();
        consultaSQL =
                "select * from ECF_EMPRESA where ID=" + pId;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                empresa.setId(rs.getInt("ID"));
                empresa.setCnpj(rs.getString("CNPJ"));
                empresa.setInscricaoEstadual(rs.getString("INSCRICAO_ESTADUAL"));
                empresa.setInscricaoMunicipal(rs.getString("INSCRICAO_MUNICIPAL"));
                empresa.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
                empresa.setNomeFantasia(rs.getString("NOME_FANTASIA"));
                empresa.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                empresa.setLogradouro(rs.getString("LOGRADOURO"));
                empresa.setComplemento(rs.getString("COMPLEMENTO"));
                empresa.setBairro(rs.getString("BAIRRO"));
                empresa.setCidade(rs.getString("CIDADE"));
                empresa.setCep(rs.getString("CEP"));
                empresa.setCodigoIbgeCidade(rs.getInt("CODIGO_IBGE_CIDADE"));
                empresa.setCodigoIbgeUf(rs.getInt("CODIGO_IBGE_UF"));
                empresa.setFone(rs.getString("FONE"));
                empresa.setFax(rs.getString("FAX"));
                empresa.setContato(rs.getString("CONTATO"));
                empresa.setUf(rs.getString("UF"));
                return empresa;
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

    public boolean consultaIdEmpresa(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_EMPRESA "
                + "where "
                + " ID = "+pId;

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

    public Boolean gravaCargaEmpresa(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (consultaIdEmpresa(id)) {
                consultaSQL =
                        "update "
                        + " ECF_EMPRESA "
                        + "set "
                        + "ID_EMPRESA =" + tupla[2] + "," //   ID_EMPRESA                 INTEGER
                        + "RAZAO_SOCIAL =" + tupla[3] + "," //   RAZAO_SOCIAL               VARCHAR(150),
                        + "NOME_FANTASIA =" + tupla[4] + "," //   NOME_FANTASIA              VARCHAR(150),
                        + "CNPJ =" + tupla[5] + "," //   CNPJ                       VARCHAR(14),
                        + "INSCRICAO_ESTADUAL =" + tupla[6] + "," //   INSCRICAO_ESTADUAL         VARCHAR(30),
                        + "INSCRICAO_ESTADUAL_ST =" + tupla[7] + "," //   INSCRICAO_ESTADUAL_ST      VARCHAR(30),
                        + "INSCRICAO_MUNICIPAL =" + tupla[8] + "," //   INSCRICAO_MUNICIPAL        VARCHAR(30),
                        + "INSCRICAO_JUNTA_COMERCIAL =" + tupla[9] + "," //   INSCRICAO_JUNTA_COMERCIAL  VARCHAR(30),
                        + "DATA_INSC_JUNTA_COMERCIAL =" + tupla[10] + "," //   DATA_INSC_JUNTA_COMERCIAL  DATE,
                        + "MATRIZ_FILIAL =" + tupla[11] + "," //   MATRIZ_FILIAL              CHAR(1),
                        + "DATA_CADASTRO =" + tupla[12] + "," //   DATA_CADASTRO              DATE,
                        + "DATA_INICIO_ATIVIDADES =" + tupla[13] + "," //   DATA_INICIO_ATIVIDADES     DATE,
                        + "SUFRAMA =" + tupla[14] + "," //   SUFRAMA                    VARCHAR(9),
                        + "EMAIL =" + tupla[15] + "," //   EMAIL                      VARCHAR(250),
                        + "IMAGEM_LOGOTIPO =" + tupla[16] + "," //   IMAGEM_LOGOTIPO            VARCHAR(250),
                        + "CRT =" + tupla[17] + "," //   CRT                        CHAR(1),
                        + "TIPO_REGIME =" + tupla[18] + "," //   TIPO_REGIME                CHAR(1),
                        + "ALIQUOTA_PIS =" + tupla[19] + "," //   ALIQUOTA_PIS               DECIMAL(18,6),
                        + "ALIQUOTA_COFINS =" + tupla[20] + "," //   ALIQUOTA_COFINS            DECIMAL(18,6),
                        + "LOGRADOURO =" + tupla[21] + "," //   LOGRADOURO                 VARCHAR(250),
                        + "NUMERO =" + tupla[22] + "," //   NUMERO                     VARCHAR(6),
                        + "COMPLEMENTO =" + tupla[23] + "," //   COMPLEMENTO                VARCHAR(100),
                        + "CEP =" + tupla[24] + "," //   CEP                        VARCHAR(8),
                        + "BAIRRO =" + tupla[25] + "," //   BAIRRO                     VARCHAR(100),
                        + "CIDADE =" + tupla[26] + "," //   CIDADE                     VARCHAR(100),
                        + "UF =" + tupla[27] + "," //   UF                         CHAR(2),
                        + "FONE =" + tupla[28] + "," //   FONE                       VARCHAR(10),
                        + "FAX =" + tupla[29] + "," //   FAX                        VARCHAR(10),
                        + "CONTATO =" + tupla[30] + "," //   CONTATO                    VARCHAR(30),
                        + "CODIGO_IBGE_CIDADE =" + tupla[31] + "," //   CODIGO_IBGE_CIDADE         INTEGER,
                        + "CODIGO_IBGE_UF =" + tupla[32] + ")" //   CODIGO_IBGE_UF             INTEGER,
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