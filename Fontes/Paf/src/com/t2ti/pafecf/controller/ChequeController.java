/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do cheque</p>
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
import com.t2ti.pafecf.vo.ChequeClienteVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChequeController {

    String consultaSQL;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public boolean gravaCheque(List<ChequeClienteVO> pListaCheque) {
        Connection con = bd.conectar();
        try {

            consultaSQL = "insert into ECF_CHEQUE_CLIENTE ( "
                    + "ID_BANCO,"
                    + "ID_CLIENTE,"
                    + "ID_ECF_MOVIMENTO,"
                    + "NUMERO_CHEQUE,"
                    + "DATA_CHEQUE,"
                    + "AGENCIA,"
                    + "CONTA,"
                    + "OBSERVACOES,"
                    + "TIPO_CHEQUE,"
                    + "VALOR_CHEQUE) values ("
                    + "?," //1
                    + "?," //2
                    + "?," //3
                    + "?," //4
                    + "?," //5
                    + "?," //6
                    + "?," //7
                    + "?," //8
                    + "?," //9
                    + "?)"; //10

            for (int i = 0; i < pListaCheque.size(); i++) {
                pstm = con.prepareStatement(consultaSQL);
                pstm.setInt(1, pListaCheque.get(i).getBancoVO().getId());
                pstm.setInt(2, pListaCheque.get(i).getClienteVO().getId());
                pstm.setInt(3, pListaCheque.get(i).getMovimentoVO().getId());
                pstm.setInt(4, pListaCheque.get(i).getNumeroCheque());
                pstm.setDate(5, new java.sql.Date(pListaCheque.get(i).getDataCheque().getTime()));
                pstm.setString(6, pListaCheque.get(i).getAgencia());
                pstm.setString(7, pListaCheque.get(i).getConta());
                pstm.setString(8, pListaCheque.get(i).getObservacoes());
                pstm.setString(9, pListaCheque.get(i).getTipoCheque());
                pstm.setDouble(10, pListaCheque.get(i).getValorCheque());
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

    public void excluiCheque(Integer pId) {
        consultaSQL =
                "delete from "
                + " ECF_CHEQUE_CLIENTE "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, pId);
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }
}
