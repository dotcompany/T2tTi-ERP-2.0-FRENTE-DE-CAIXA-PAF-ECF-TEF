/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do turno</p>
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
import com.t2ti.pafecf.vo.TurnoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TurnoController {

    String consultaSQL;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<TurnoVO> tabelaTurno() {
        try {
            consultaSQL = "select count(*) as TOTAL from ECF_TURNO";
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                ArrayList<TurnoVO> listaTurno = new ArrayList<TurnoVO>();
                consultaSQL = "select * from ECF_TURNO";

                pstm = bd.conectar().prepareStatement(consultaSQL);
                rs = pstm.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    TurnoVO turno = new TurnoVO();
                    turno.setId(rs.getInt("ID"));
                    turno.setDescricao(rs.getString("DESCRICAO"));
                    turno.setHoraInicio(rs.getString("HORA_INICIO"));
                    turno.setHoraFim(rs.getString("HORA_FIM"));
                    listaTurno.add(turno);
                }
                return listaTurno;
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

    public boolean consultaIdTurno(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_TURNO "
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

    public Boolean gravaCargaTurno(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdTurno(id)) {
                consultaSQL =
                        "insert into"
                        + " ECF_TURNO "
                        + " (ID, "
                        + " DESCRICAO, "
                        + " HORA_INICIO, "
                        + " HORA_FIM) "
                        + " values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    DESCRICAO    VARCHAR(10),
                        + tupla[3] + ", " //    HORA_INICIO  VARCHAR(8),
                        + tupla[4] + ")";   //    HORA_FIM     VARCHAR(8)
            } else {
                consultaSQL =
                        "update "
                        + " ECF_TURNO "
                        + "set "
                        + " DESCRICAO = " + tupla[2] + ", " //    DESCRICAO    VARCHAR(10),
                        + " HORA_INICIO =" + tupla[3] + ", " //    HORA_INICIO  VARCHAR(8),
                        + " HORA_FIM =" + tupla[4] //    HORA_FIM     VARCHAR(8)
                        + " where "
                        + " ID =" + id;
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