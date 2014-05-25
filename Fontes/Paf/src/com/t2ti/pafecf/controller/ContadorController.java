/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do contador</p>
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
import com.t2ti.pafecf.vo.ContadorVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContadorController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ContadorVO pegaContador() {
        ContadorVO contador = new ContadorVO();
        consultaSQL =
                "select * from ECF_CONTADOR";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                contador.setId(rs.getInt("ID"));
                contador.setCpf(rs.getString("CPF"));
                contador.setCnpj(rs.getString("CNPJ"));
                contador.setNome(rs.getString("NOME"));
                contador.setInscricaoCrc(rs.getString("INSCRICAO_CRC"));
                contador.setFone(rs.getString("FONE"));
                contador.setFax(rs.getString("FAX"));
                contador.setLogradouro(rs.getString("LOGRADOURO"));
                contador.setNumero(rs.getInt("NUMERO"));
                contador.setComplemento(rs.getString("COMPLEMENTO"));
                contador.setBairro(rs.getString("BAIRRO"));
                contador.setCep(rs.getString("CEP"));
                contador.setCodigoMunicipio(rs.getInt("CODIGO_MUNICIPIO"));
                contador.setUf(rs.getString("UF"));
                contador.setEmail(rs.getString("EMAIL"));
                return contador;
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

    public boolean consultaIdContador(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_CONTADOR "
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

    public Boolean gravaCargaContador(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdContador(id)) {
                consultaSQL =
                        "insert into"
                        + " ECF_CONTADOR "
                        + "(ID, "
                        + "ID_ECF_EMPRESA, "//2
                        + "CPF, "//3
                        + "CNPJ, "//4
                        + "NOME, "//5
                        + "INSCRICAO_CRC, "//6
                        + "FONE, "//7
                        + "FAX, "//8
                        + "LOGRADOURO, "//9
                        + "NUMERO, "//10
                        + "COMPLEMENTO, "//11
                        + "BAIRRO, "//12
                        + "CEP, "//13
                        + "CODIGO_MUNICIPIO, "//14
                        + "UF, "//15
                        + "EMAIL)"//16
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    ID_ECF_EMPRESA    INTEGER NOT NULL,
                        + tupla[3] + ", " //    CPF               VARCHAR(11),
                        + tupla[4] + ", " //    CNPJ              VARCHAR(14),
                        + tupla[5] + ", " //    NOME              VARCHAR(100),
                        + tupla[6] + ", " //    INSCRICAO_CRC     VARCHAR(15),
                        + tupla[7] + ", " //    FONE              VARCHAR(15),
                        + tupla[8] + ", " //    FAX               VARCHAR(15),
                        + tupla[9] + ", " //    LOGRADOURO        VARCHAR(100),
                        + tupla[10] + ", " //    NUMERO            INTEGER,
                        + tupla[11] + ", " //    COMPLEMENTO       VARCHAR(100),
                        + tupla[12] + ", " //    BAIRRO            VARCHAR(30),
                        + tupla[13] + ", " //    CEP               VARCHAR(8),
                        + tupla[14] + ", " //    CODIGO_MUNICIPIO  INTEGER,
                        + tupla[15] + ", " //    UF                CHAR(2),
                        + tupla[16] + ')';     //    EMAIL             VARCHAR(250)
            } else {
                consultaSQL =
                        "update "
                        + " ECF_CONTADOR "
                        + "set "
                        + "ID_ECF_EMPRESA =" + tupla[2] + "," //    ID_ECF_EMPRESA    INTEGER NOT NULL,
                        + "CPF =" + tupla[3] + "," //    CPF               VARCHAR(11),
                        + "CNPJ =" + tupla[4] + "," //    CNPJ              VARCHAR(14),
                        + "NOME =" + tupla[5] + "," //    NOME              VARCHAR(100),
                        + "INSCRICAO_CRC =" + tupla[6] + "," //    INSCRICAO_CRC     VARCHAR(15),
                        + "FONE =" + tupla[7] + "," //    FONE              VARCHAR(15),
                        + "FAX =" + tupla[8] + "," //    FAX               VARCHAR(15),
                        + "LOGRADOURO =" + tupla[9] + "," //    LOGRADOURO        VARCHAR(100),
                        + "NUMERO =" + tupla[10] + "," //    NUMERO            INTEGER,
                        + "COMPLEMENTO =" + tupla[11] + "," //    COMPLEMENTO       VARCHAR(100),
                        + "BAIRRO =" + tupla[12] + "," //    BAIRRO            VARCHAR(30),
                        + "CEP =" + tupla[13] + "," //    CEP               VARCHAR(8),
                        + "CODIGO_MUNICIPIO =" + tupla[14] + "," //    CODIGO_MUNICIPIO  INTEGER,
                        + "UF =" + tupla[15] + "," //    UF                CHAR(2),
                        + "EMAIL =" + tupla[16] + ")" //    EMAIL             VARCHAR(250)
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
