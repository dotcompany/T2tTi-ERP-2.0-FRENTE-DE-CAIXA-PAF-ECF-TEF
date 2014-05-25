/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da impressora</p>
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
import com.t2ti.pafecf.vo.ImpressoraVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImpressoraController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ImpressoraVO pegaImpressora(Integer pId) {
        ImpressoraVO impressora = new ImpressoraVO();
        consultaSQL =
                "select * from ECF_IMPRESSORA where ID=" + pId;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                impressora.setId(rs.getInt("ID"));
                impressora.setNumero(rs.getInt("NUMERO"));
                impressora.setCodigo(rs.getString("CODIGO"));
                impressora.setSerie(rs.getString("SERIE"));
                impressora.setIdentificacao(rs.getString("IDENTIFICACAO"));
                impressora.setMc(rs.getString("MC"));
                impressora.setMd(rs.getString("MD"));
                impressora.setVr(rs.getString("VR"));
                impressora.setTipo(rs.getString("TIPO"));
                impressora.setMarca(rs.getString("MARCA"));
                impressora.setModelo(rs.getString("MODELO"));
                impressora.setModeloAcbr(rs.getString("MODELO_ACBR"));
                impressora.setModeloDocumentoFiscal(rs.getString("MODELO_DOCUMENTO_FISCAL"));
                impressora.setVersao(rs.getString("VERSAO"));
                impressora.setLe(rs.getString("LE"));
                impressora.setLef(rs.getString("LEF"));
                impressora.setMfd(rs.getString("MFD"));
                impressora.setLacreNaMfd(rs.getString("LACRE_NA_MFD"));
                impressora.setDocto(rs.getString("DOCTO"));
                impressora.setNumeroEcf(rs.getString("ECF_IMPRESSORA"));
                impressora.setDataInstalacaoSb(rs.getDate("DATA_INSTALACAO_SB"));
                impressora.setHoraInstalacaoSb(rs.getString("HORA_INSTALACAO_SB"));
                return impressora;
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

    public List<ImpressoraVO> tabelaImpressora() {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from ECF_IMPRESSORA");
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ImpressoraVO> listaImpressora = new ArrayList<ImpressoraVO>();
                consultaSQL = "select * from ECF_IMPRESSORA";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ImpressoraVO impressora = new ImpressoraVO();
                    impressora.setId(rs.getInt("ID"));
                    impressora.setNumero(rs.getInt("NUMERO"));
                    impressora.setCodigo(rs.getString("CODIGO"));
                    impressora.setSerie(rs.getString("SERIE"));
                    impressora.setIdentificacao(rs.getString("IDENTIFICACAO"));
                    impressora.setMc(rs.getString("MC"));
                    impressora.setMd(rs.getString("MD"));
                    impressora.setVr(rs.getString("VR"));
                    impressora.setTipo(rs.getString("TIPO"));
                    impressora.setMarca(rs.getString("MARCA"));
                    impressora.setModelo(rs.getString("MODELO"));
                    impressora.setModeloAcbr(rs.getString("MODELO_ACBR"));
                    impressora.setModeloDocumentoFiscal(rs.getString("MODELO_DOCUMENTO_FISCAL"));
                    impressora.setVersao(rs.getString("VERSAO"));
                    impressora.setLe(rs.getString("LE"));
                    impressora.setLef(rs.getString("LEF"));
                    impressora.setMfd(rs.getString("MFD"));
                    impressora.setLacreNaMfd(rs.getString("LACRE_NA_MFD"));
                    impressora.setDocto(rs.getString("DOCTO"));
                    impressora.setNumeroEcf(rs.getString("ECF_IMPRESSORA"));
                    impressora.setDataInstalacaoSb(rs.getDate("DATA_INSTALACAO_SB"));
                    impressora.setHoraInstalacaoSb(rs.getString("HORA_INSTALACAO_SB"));
                    listaImpressora.add(impressora);
                }
                return listaImpressora;
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

    public boolean consultaIdImpressora(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_IMPRESSORA "
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

    public Boolean gravaCargaImpressora(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdImpressora(id)) {
                consultaSQL =
                        "insert into"
                        + " ECF_IMPRESSORA "
                        + " (ID, "
                        + "NUMERO, "
                        + "CODIGO, "
                        + "SERIE, "
                        + "IDENTIFICACAO, "
                        + "MC, "
                        + "MD, "
                        + "VR, "
                        + "TIPO, "
                        + "MARCA, "
                        + "MODELO, "
                        + "MODELO_ACBR, "
                        + "MODELO_DOCUMENTO_FISCAL, "
                        + "VERSAO, "
                        + "LE, "
                        + "LEF, "
                        + "MFD, "
                        + "LACRE_NA_MFD, "
                        + "DOCTO, "
                        + "DATA_INSTALACAO_SB, "
                        + "HORA_INSTALACAO_SB)"
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    NUMERO                   INTEGER,
                        + tupla[3] + ", " //    CODIGO                   VARCHAR(10),
                        + tupla[4] + ", " //    SERIE                    VARCHAR(20),
                        + tupla[5] + ", " //    IDENTIFICACAO            VARCHAR(250),
                        + tupla[6] + ", " //    MC                       CHAR(2),
                        + tupla[7] + ", " //    MD                       CHAR(2),
                        + tupla[8] + ", " //    VR                       CHAR(2),
                        + tupla[9] + ", " //    TIPO                     VARCHAR(7),
                        + tupla[10] + ", " //    MARCA                    VARCHAR(30),
                        + tupla[11] + ", " //    MODELO                   VARCHAR(30),
                        + tupla[12] + ", " //    MODELO_ACBR              VARCHAR(30),
                        + tupla[13] + ", " //    MODELO_DOCUMENTO_FISCAL  CHAR(2),
                        + tupla[14] + ", " //    VERSAO                   VARCHAR(30),
                        + tupla[15] + ", " //    LE                       CHAR(1),
                        + tupla[16] + ", " //    LEF                      CHAR(1),
                        + tupla[17] + ", " //    MFD                      CHAR(1),
                        + tupla[18] + ", " //    LACRE_NA_MFD             CHAR(1),
                        + tupla[19] + ", " //    DOCTO                    VARCHAR(60),
                        + tupla[20] + ", " //    DATA_INSTALACAO_SB       date
                        + tupla[21] + ")"; //    HORA_INSTALACAO_SB       varchar(8)
            } else {
                consultaSQL =
                        "update "
                        + " ECF_IMPRESSORA "
                        + "set "
                        + "NUMERO =" + tupla[2] + ", " //    NUMERO                   INTEGER,
                        + "CODIGO =" + tupla[3] + ", " //    CODIGO                   VARCHAR(10),
                        + "SERIE =" + tupla[4] + ", " //    SERIE                    VARCHAR(20),
                        + "IDENTIFICACAO =" + tupla[5] + ", " //    IDENTIFICACAO            VARCHAR(250),
                        + "MC =" + tupla[6] + ", " //    MC                       CHAR(2),
                        + "MD =" + tupla[7] + ", " //    MD                       CHAR(2),
                        + "VR =" + tupla[8] + ", " //    VR                       CHAR(2),
                        + "TIPO =" + tupla[9] + ", " //    TIPO                     VARCHAR(7),
                        + "MARCA =" + tupla[10] + ", " //    MARCA                    VARCHAR(30),
                        + "MODELO =" + tupla[11] + ", " //    MODELO                   VARCHAR(30),
                        + "MODELO_ACBR =" + tupla[12] + ", " //    MODELO_ACBR              VARCHAR(30),
                        + "MODELO_DOCUMENTO_FISCAL =" + tupla[13] + ", " //    MODELO_DOCUMENTO_FISCAL  CHAR(2),
                        + "VERSAO =" + tupla[14] + ", " //    VERSAO                   VARCHAR(30),
                        + "LE =" + tupla[15] + ", " //    LE                       CHAR(1),
                        + "LEF =" + tupla[16] + ", " //    LEF                      CHAR(1),
                        + "MFD =" + tupla[17] + ", " //    MFD                      CHAR(1),
                        + "LACRE_NA_MFD =" + tupla[18] + ", " //    LACRE_NA_MFD             CHAR(1),
                        + "DOCTO =" + tupla[19] + ", " //    DOCTO                    VARCHAR(60)
                        + "DATA_INSTALACAO_SB =" + tupla[20] + ", " //    DATA_INSTALACAO_SB       date
                        + "HORA_INSTALACAO_SB =" + tupla[21] //    HORA_INSTALACAO_SB       varchar(8)
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
