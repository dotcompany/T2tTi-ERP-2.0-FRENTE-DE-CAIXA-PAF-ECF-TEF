/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller ddo Cfop</p>
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

public class CfopController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public boolean consultaIdCfop(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " CFOP "
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

    public Boolean gravaCargaCfop(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdCfop(id)) {
                consultaSQL =
                        "insert into"
                        + " CFOP "
                        + " (ID, "
                        + " CFOP, "
                        + " DESCRICAO, "
                        + " APLICACAO)"
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    CFOP       INTEGER,
                        + tupla[3] + ", " //    DESCRICAO  VARCHAR(250),
                        + tupla[4] + ")";   //    APLICACAO  VARCHAR(250)
            } else {
                consultaSQL =
                        "update "
                        + " CFOP "
                        + "set "
                        + " CFOP = " + tupla[2] + ", " //    CFOP       INTEGER,
                        + " DESCRICAO =" + tupla[3] + ", " //    DESCRICAO  VARCHAR(250),
                        + " APLICACAO =" + tupla[4] //    APLICACAO  VARCHAR(250)
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