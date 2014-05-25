/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do fechamento do movimento</p>
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
import com.t2ti.pafecf.vo.FechamentoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FechamentoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public Boolean gravaFechamento(FechamentoVO pFechamento) {
        consultaSQL =
                "insert into "
                + "ECF_FECHAMENTO ( "
                + "ID_ECF_MOVIMENTO,"
                + "TIPO_PAGAMENTO,"
                + "VALOR)"
                + " values ("
                + "?,"
                + "?,"
                + "?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, pFechamento.getIdMovimento());
            pstm.setString(2, pFechamento.getTipoPagamento());
            pstm.setDouble(3, pFechamento.getValor());
            pstm.executeUpdate();

            try {
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from ECF_FECHAMENTO");
                rs.first();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public Boolean excluiFechamento(Integer pId) {
        consultaSQL =
                "delete from ecf_fechamento where id = ?";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, pId);
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