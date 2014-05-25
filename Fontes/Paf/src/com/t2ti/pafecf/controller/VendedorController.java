/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do vendedor</p>
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
import com.t2ti.pafecf.vo.FuncionarioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public FuncionarioVO consultaVendedor(Integer id) {

        FuncionarioVO vendedor = new FuncionarioVO();
        consultaSQL = "select * from ECF_FUNCIONARIO where "
                + "ID=" + id;

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                vendedor.setId(rs.getInt("ID"));
                vendedor.setNome(rs.getString("NOME"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
        return vendedor;
    }

    public List<FuncionarioVO> consultaVendedores() {
        List<FuncionarioVO> listaFuncionario = new ArrayList<FuncionarioVO>();
        try {
            consultaSQL = "select * from ECF_FUNCIONARIO";
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            FuncionarioVO vendedor;
            while (rs.next()) {
                vendedor = new FuncionarioVO();
                vendedor.setId(rs.getInt("ID"));
                vendedor.setNome(rs.getString("NOME"));

                listaFuncionario.add(vendedor);
            }
            return listaFuncionario;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }

    public boolean consultaIdFuncionario(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_FUNCIONARIO "
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

    public Boolean gravaCargaFuncionario(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdFuncionario(id)) {
                consultaSQL =
                        "insert into"
                        + " ECF_FUNCIONARIO "
                        + " (ID, "
                        + "NOME, "
                        + "TELEFONE, "
                        + "CELULAR, "
                        + "EMAIL, "
                        + "COMISSAO_VISTA, "
                        + "COMISSAO_PRAZO, "
                        + "NIVEL_AUTORIZACAO) "
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //    NOME               VARCHAR(100),
                        + tupla[3] + ", " //    TELEFONE           VARCHAR(10),
                        + tupla[4] + ", " //    CELULAR            VARCHAR(10),
                        + tupla[5] + ", " //    EMAIL              VARCHAR(250),
                        + tupla[6] + ", " //    COMISSAO_VISTA     DECIMAL(18,6),
                        + tupla[7] + ", " //    COMISSAO_PRAZO     DECIMAL(18,6),
                        + tupla[8] + ")";   //    NIVEL_AUTORIZACAO  CHAR(1)
            } else {
                consultaSQL =
                        "update "
                        + " ECF_FUNCIONARIO "
                        + "set "
                        + " NOME = " + tupla[2] + ", " //    NOME               VARCHAR(100),
                        + " TELEFONE =" + tupla[3] + ", " //    TELEFONE           VARCHAR(10),
                        + " CELULAR =" + tupla[4] //    CELULAR            VARCHAR(10),
                        + " EMAIL =" + tupla[5] //    EMAIL              VARCHAR(250),
                        + " COMISSAO_VISTA =" + tupla[6] //    COMISSAO_VISTA     DECIMAL(18,6),
                        + " COMISSAO_PRAZO =" + tupla[7] //    COMISSAO_PRAZO     DECIMAL(18,6),
                        + " NIVEL_AUTORIZACAO =" + tupla[8] //    NIVEL_AUTORIZACAO  CHAR(1)
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
