/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do tipo de pagamento</p>
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
import com.t2ti.pafecf.vo.TipoPagamentoVO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoController {

    Statement stm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<TipoPagamentoVO> consulta() {
        String consultaSQL = "select * from ECF_TIPO_PAGAMENTO order by TEF, ID";

        try {
            List<TipoPagamentoVO> listaTipoPagamento = new ArrayList<TipoPagamentoVO>();

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            while (rs.next()) {
                TipoPagamentoVO tipoPagamento = new TipoPagamentoVO();
                tipoPagamento.setId(rs.getInt("ID"));
                tipoPagamento.setCodigo(rs.getString("CODIGO"));
                tipoPagamento.setDescricao(rs.getString("DESCRICAO"));
                tipoPagamento.setTef(rs.getString("TEF"));
                tipoPagamento.setImprimeVinculado(rs.getString("IMPRIME_VINCULADO"));
                listaTipoPagamento.add(tipoPagamento);
            }
            return listaTipoPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public TipoPagamentoVO consultaPeloId(Integer pId) {
        String consultaSQL = "select * from ECF_TIPO_PAGAMENTO where ID=" + pId;

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            rs.next();
            TipoPagamentoVO tipoPagamento = new TipoPagamentoVO();
            tipoPagamento.setId(rs.getInt("ID"));
            tipoPagamento.setCodigo(rs.getString("CODIGO"));
            tipoPagamento.setDescricao(rs.getString("DESCRICAO"));
            tipoPagamento.setTef(rs.getString("TEF"));
            tipoPagamento.setImprimeVinculado(rs.getString("IMPRIME_VINCULADO"));
            return tipoPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public TipoPagamentoVO consultaPeloCodigo(String pCodigo) {
        String consultaSQL = "select * from ECF_TIPO_PAGAMENTO where CODIGO='" + pCodigo+"'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            rs.next();
            TipoPagamentoVO tipoPagamento = new TipoPagamentoVO();
            tipoPagamento.setId(rs.getInt("ID"));
            tipoPagamento.setCodigo(rs.getString("CODIGO"));
            tipoPagamento.setDescricao(rs.getString("DESCRICAO"));
            tipoPagamento.setTef(rs.getString("TEF"));
            tipoPagamento.setImprimeVinculado(rs.getString("IMPRIME_VINCULADO"));
            return tipoPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public TipoPagamentoVO consultaPeloNome(String pNome) {
        String consultaSQL = "select * from ECF_TIPO_PAGAMENTO where DESCRICAO='" + pNome + "'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            rs.next();
            TipoPagamentoVO tipoPagamento = new TipoPagamentoVO();
            tipoPagamento.setId(rs.getInt("ID"));
            tipoPagamento.setCodigo(rs.getString("CODIGO"));
            tipoPagamento.setDescricao(rs.getString("DESCRICAO"));
            tipoPagamento.setTef(rs.getString("TEF"));
            tipoPagamento.setImprimeVinculado(rs.getString("IMPRIME_VINCULADO"));
            return tipoPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

}
