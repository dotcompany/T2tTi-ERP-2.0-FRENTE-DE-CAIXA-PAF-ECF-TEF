/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do movimento</p>
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
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.view.CargaPDV;
import com.t2ti.pafecf.vo.MovimentoVO;
import com.t2ti.pafecf.vo.SangriaVO;
import com.t2ti.pafecf.vo.SuprimentoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class MovimentoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public MovimentoVO verificaMovimento() {
        MovimentoVO movimento = new MovimentoVO();
        consultaSQL =
                "select "
                + "M.ID as MID, T.ID as TID, T.DESCRICAO, C.ID as CID, C.NOME, "
                + "O.ID as OID, O.LOGIN, I.ID as IID, I.IDENTIFICACAO, "
                + "M.DATA_ABERTURA, M.HORA_ABERTURA, M.ID_GERENTE_SUPERVISOR, M.STATUS_MOVIMENTO "
                + "from "
                + "ECF_MOVIMENTO M, ECF_TURNO T, ECF_CAIXA C, ECF_OPERADOR O, ECF_IMPRESSORA I "
                + "where "
                + "M.ID_ECF_TURNO = T.ID AND "
                + "M.ID_ECF_IMPRESSORA = I.ID AND "
                + "M.ID_ECF_OPERADOR = O.ID AND "
                + "M.ID_ECF_CAIXA = C.ID AND"
                + "(STATUS_MOVIMENTO='A' or STATUS_MOVIMENTO='T')";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                movimento.setId(rs.getInt("MID"));
                movimento.setIdTurno(rs.getInt("TID"));
                movimento.setIdImpressora(rs.getInt("IID"));
                movimento.setIdOperador(rs.getInt("OID"));
                movimento.setIdCaixa(rs.getInt("CID"));
                movimento.setIdGerenteSupervisor(rs.getInt("ID_GERENTE_SUPERVISOR"));
                movimento.setDataAbertura(rs.getDate("DATA_ABERTURA"));
                movimento.setHoraAbertura(rs.getString("HORA_ABERTURA"));
                movimento.setStatusMovimento(rs.getString("STATUS_MOVIMENTO"));
                movimento.setLoginOperador(rs.getString("LOGIN"));
                movimento.setNomeCaixa(rs.getString("NOME"));
                movimento.setDescricaoTurno(rs.getString("DESCRICAO"));
                movimento.setIdentificacaoImpressora(rs.getString("IDENTIFICACAO"));
                return movimento;
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

    public void encerraMovimento(MovimentoVO movimento) {
        consultaSQL =
                "update ECF_MOVIMENTO set DATA_FECHAMENTO=?, HORA_FECHAMENTO=?, STATUS_MOVIMENTO=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setDate(1, new java.sql.Date(movimento.getDataFechamento().getTime()));
            pstm.setString(2, movimento.getHoraFechamento());
            pstm.setString(3, movimento.getStatusMovimento());
            pstm.setInt(4, movimento.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public boolean insere(MovimentoVO movimento) {
        return true;
    }

    public MovimentoVO iniciaMovimento(MovimentoVO movimento) {

        consultaSQL =
                "insert into ECF_MOVIMENTO ("
                + "ID_ECF_TURNO,"
                + "ID_ECF_IMPRESSORA,"
                + "ID_ECF_OPERADOR,"
                + "ID_ECF_CAIXA,"
                + "ID_GERENTE_SUPERVISOR,"
                + "DATA_ABERTURA,"
                + "HORA_ABERTURA,"
                + "TOTAL_SUPRIMENTO,"
                + "STATUS_MOVIMENTO,"
                + "SINCRONIZADO, "
                + "ID_ECF_EMPRESA)"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, movimento.getIdTurno());
            pstm.setInt(2, movimento.getIdImpressora());
            pstm.setInt(3, movimento.getIdOperador());
            pstm.setInt(4, movimento.getIdCaixa());
            pstm.setInt(5, movimento.getIdGerenteSupervisor());
            pstm.setDate(6, new java.sql.Date(movimento.getDataAbertura().getTime()));
            pstm.setString(7, movimento.getHoraAbertura());
            pstm.setDouble(8, movimento.getTotalSuprimento());
            pstm.setString(9, movimento.getStatusMovimento());
            pstm.setString(10, movimento.getSincronizado());
            pstm.setInt(11, movimento.getIdEmpresa());
            pstm.executeUpdate();

            movimento = verificaMovimento();
            /*
             stm = bd.conectar().createStatement();
             rs = stm.executeQuery("select max(ID) as ID from ECF_MOVIMENTO");
             rs.first();
             movimento.setId(rs.getInt(1));
             *
             */
            return movimento;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void saidaTemporaria(MovimentoVO movimento) {
        consultaSQL =
                "update ECF_MOVIMENTO set STATUS_MOVIMENTO='T' "
                + " where ID = ?";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, movimento.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public void retornoOperador(MovimentoVO movimento) {
        consultaSQL =
                "update ECF_MOVIMENTO set STATUS_MOVIMENTO='A' "
                + " where ID = ?";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, movimento.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public boolean suprimento(SuprimentoVO suprimento) {
        String consultaSQL =
                "insert into ECF_SUPRIMENTO (ID_ECF_MOVIMENTO,DATA_SUPRIMENTO,VALOR)"
                + " values (?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, suprimento.getIdMovimento());
            pstm.setDate(2, suprimento.getDataSuprimento());
            pstm.setDouble(3, suprimento.getValor());
            pstm.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public boolean sangria(SangriaVO sangria) {
        String consultaSQL =
                "insert into ECF_SANGRIA (ID_ECF_MOVIMENTO,DATA_SANGRIA,VALOR)"
                + " values (?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, sangria.getIdMovimento());
            pstm.setDate(2, sangria.getDataSangria());
            pstm.setDouble(3, sangria.getValor());
            pstm.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public SangriaVO ultimaSangria() {
        consultaSQL = "select * from ECF_SANGRIA "
                + "where id = (select max(id) from ECF_SANGRIA)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            if (rs.first()) {
                SangriaVO sangria = new SangriaVO();
                sangria.setId(rs.getInt("ID"));
                sangria.setIdMovimento(rs.getInt("ID_ECF_MOVIMENTO"));
                sangria.setDataSangria(rs.getDate("DATA_SANGRIA"));
                sangria.setValor(rs.getDouble("VALOR"));

                return sangria;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }

    public SuprimentoVO ultimoSuprimento() {
        consultaSQL = "select * from ECF_SUPRIMENTO "
                + "where id = (select max(id) from ECF_SUPRIMENTO)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            if (rs.first()) {
                SuprimentoVO suprimento = new SuprimentoVO();
                suprimento.setId(rs.getInt("ID"));
                suprimento.setIdMovimento(rs.getInt("ID_ECF_MOVIMENTO"));
                suprimento.setDataSuprimento(rs.getDate("DATA_SUPRIMENTO"));
                suprimento.setValor(rs.getDouble("VALOR"));

                return suprimento;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return null;
    }
}
