/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do operador</p>
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
import com.t2ti.pafecf.vo.OperadorVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperadorController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public OperadorVO consultaUsuario(String pLogin, String pSenha) {
        OperadorVO operador = new OperadorVO();
        FuncionarioVO funcionario = new FuncionarioVO();
        operador.setFuncionarioVO(funcionario);
        consultaSQL =
                "select O.ID,O.ID_ECF_FUNCIONARIO,O.LOGIN,O.SENHA,F.NIVEL_AUTORIZACAO "
                + "from ECF_OPERADOR O, ECF_FUNCIONARIO F where O.ID_ECF_FUNCIONARIO=F.ID "
                + "AND LOGIN='" + pLogin + "' and SENHA='" + pSenha + "'";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                operador.setId(rs.getInt("ID"));
                operador.setLogin(rs.getString("LOGIN"));
                operador.setSenha(rs.getString("SENHA"));
                operador.getFuncionarioVO().setId(rs.getInt("ID_ECF_FUNCIONARIO"));
                operador.getFuncionarioVO().setNivelAutorizacao(rs.getString("NIVEL_AUTORIZACAO"));
                return operador;
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

    public String consultaFuncionario(Integer pId) {
        OperadorVO operador = new OperadorVO();
        FuncionarioVO funcionario = new FuncionarioVO();
        operador.setFuncionarioVO(funcionario);
        consultaSQL = "select ID, NOME  from ECF_FUNCIONARIO where ID =" + pId;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("NOME");
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

    public List<FuncionarioVO> retornaFuncionario(String pLogin, String pSenha) {
        consultaSQL = "select ID, NOME from ECF_FUNCIONARIO order by NOME ";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            List<FuncionarioVO> listaFuncionario = new ArrayList<FuncionarioVO>();
            while (rs.next()) {
                FuncionarioVO funcionario = new FuncionarioVO();
                funcionario.setId(rs.getInt("ID"));
                funcionario.setNome(rs.getString("NOME"));
                listaFuncionario.add(funcionario);
            }
            return listaFuncionario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public boolean consultaIdOperador(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " ECF_OPERADOR "
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

    public Boolean gravaCargaOperador(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdOperador(id)) {
                consultaSQL =
                        "insert into"
                        + " ECF_OPERADOR "
                        + " (ID, "
                        + " ID_ECF_FUNCIONARIO, "
                        + " LOGIN, "
                        + " SENHA) "
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //     ID_ECF_FUNCIONARIO  INTEGER NOT NULL,
                        + tupla[3] + ", " //     LOGIN               VARCHAR(20),
                        + tupla[4] + ")";   //     SENHA               VARCHAR(20)
            } else {
                consultaSQL =
                        "update "
                        + " ECF_OPERADOR "
                        + "set "
                        + " ID_ECF_FUNCIONARIO = " + tupla[2] + ", " //     ID_ECF_FUNCIONARIO  INTEGER NOT NULL,
                        + " LOGIN =" + tupla[3] + ", " //     LOGIN               VARCHAR(20),
                        + " SENHA =" + tupla[4] //     SENHA               VARCHAR(20)
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
