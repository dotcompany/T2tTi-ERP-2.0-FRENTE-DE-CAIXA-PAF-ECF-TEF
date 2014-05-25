/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller dos componentes</p>
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ComponentesController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public boolean consultaIdComponentes(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_POSICAO_COMPONENTES "
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

    public Boolean gravaCargaComponentes(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdComponentes(id)) {
                consultaSQL =
                        "insert into"
                        + " ECF_POSICAO_COMPONENTES "
                        + " (ID, "
                        + " ID_ECF_RESOLUCAO, "//1
                        + " NOME, "//2
                        + " ALTURA, "//3
                        + " LARGURA, "//4
                        + " TOPO, "//5
                        + " ESQUERDA, "//6
                        + " TAMANHO_FONTE, "//7
                        + " TEXTO)"//8
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    ID_ECF_RESOLUCAO  INTEGER NOT NULL,
                        + tupla[3] + ", " //    NOME              VARCHAR(100),
                        + tupla[4] + ", " //    ALTURA            INTEGER,
                        + tupla[5] + ", " //    LARGURA           INTEGER,
                        + tupla[6] + ", " //    TOPO              INTEGER,
                        + tupla[7] + ", " //    ESQUERDA          INTEGER,
                        + tupla[8] + ", " //    TAMANHO_FONTE     INTEGER DEFAULT 0,
                        + tupla[9] + ")";   //    TEXTO             VARCHAR(250)
            } else {
                consultaSQL =
                        "update "
                        + " ECF_POSICAO_COMPONENTES "
                        + "set "
                        + "ID_ECF_RESOLUCAO =" + tupla[2] + ", " //    ID_ECF_RESOLUCAO  INTEGER NOT NULL,
                        + "NOME =" + tupla[3] + ", " //    NOME              VARCHAR(100),
                        + "ALTURA =" + tupla[4] + ", " //    ALTURA            INTEGER,
                        + "LARGURA =" + tupla[5] + ", " //    LARGURA           INTEGER,
                        + "TOPO =" + tupla[6] + ", " //    TOPO              INTEGER,
                        + "ESQUERDA =" + tupla[7] + ", " //    ESQUERDA          INTEGER,
                        + "TAMANHO_FONTE =" + tupla[8] + ", " //    TAMANHO_FONTE     INTEGER DEFAULT 0,
                        + "TEXTO =" + tupla[9] //    TEXTO             VARCHAR(250)
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