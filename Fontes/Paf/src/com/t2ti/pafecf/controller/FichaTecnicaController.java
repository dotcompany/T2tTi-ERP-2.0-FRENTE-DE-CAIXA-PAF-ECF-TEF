/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da ficha técnica</p>
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
import com.t2ti.pafecf.vo.FichaTecnicaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FichaTecnicaController {

    String consultaSQL;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<FichaTecnicaVO> consultaFichaTecnica(int idProdutoProprio) {
        List<FichaTecnicaVO> listaFichaTecnica = new ArrayList<FichaTecnicaVO>();
        FichaTecnicaVO fichaTecnica;
        consultaSQL = "select * from FICHA_TECNICA where ID_PRODUTO = ?";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, idProdutoProprio);

            rs = pstm.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                fichaTecnica = new FichaTecnicaVO();
                fichaTecnica.setId(rs.getInt("ID"));
                fichaTecnica.setIdProduto(rs.getInt("ID_PRODUTO"));
                fichaTecnica.setDescricao(rs.getString("DESCRICAO"));
                fichaTecnica.setIdProdutoFilho(rs.getInt("ID_PRODUTO_FILHO"));
                fichaTecnica.setQuantidade(rs.getDouble("QUANTIDADE"));

                listaFichaTecnica.add(fichaTecnica);
            }
            return listaFichaTecnica;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public boolean gravaFichaTecnica(List<FichaTecnicaVO> listaFichaTecnica) {
        Connection con = bd.conectar();
        try {
            consultaSQL = "delete from FICHA_TECNICA where ID_PRODUTO = ?";
            pstm = con.prepareStatement(consultaSQL);
            pstm.setInt(1, listaFichaTecnica.get(0).getIdProduto());
            pstm.executeUpdate();

            consultaSQL = "insert into FICHA_TECNICA ("
                    + "ID_PRODUTO, "
                    + "DESCRICAO, "
                    + "ID_PRODUTO_FILHO, "
                    + "QUANTIDADE)"
                    + " values(?,?,?,?)";

            for (int i = 0; i < listaFichaTecnica.size(); i++) {
                pstm = con.prepareStatement(consultaSQL);
                pstm.setInt(1, listaFichaTecnica.get(i).getIdProduto());
                pstm.setString(2, listaFichaTecnica.get(i).getDescricao());
                pstm.setInt(3, listaFichaTecnica.get(i).getIdProdutoFilho());
                pstm.setDouble(4, listaFichaTecnica.get(i).getQuantidade());
                pstm.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            try {
                con.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            bd.desconectar();
        }
        return false;
    }

    public boolean consultaIdFichaTecnica(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " FICHA_TECNICA "
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

    public Boolean gravaCargaFichaTecnica(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdFichaTecnica(id)) {
                consultaSQL =
                        "insert into"
                        + " FICHA_TECNICA "
                        + " (ID, "
                        + " ID_PRODUTO, "
                        + " DESCRICAO, "
                        + " ID_PRODUTO_FILHO, "
                        + " QUANTIDADE) "
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    ID_PRODUTO        INTEGER NOT NULL,
                        + tupla[3] + ", " //    DESCRICAO         VARCHAR(50),
                        + tupla[4] + ", " //    ID_PRODUTO_FILHO  INTEGER,
                        + tupla[5] + ")";   //    QUANTIDADE        DECIMAL(18,6)
            } else {
                consultaSQL =
                        "update "
                        + " FICHA_TECNICA "
                        + "set "
                        + "ID_PRODUTO =" + tupla[2] + ", " //    ID_PRODUTO        INTEGER NOT NULL,
                        + "DESCRICAO =" + tupla[3] + ", " //    DESCRICAO         VARCHAR(50),
                        + "ID_PRODUTO_FILHO =" + tupla[4] + ", " //    ID_PRODUTO_FILHO  INTEGER,
                        + "QUANTIDADE =" + tupla[5] //    QUANTIDADE        DECIMAL(18,6)
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
