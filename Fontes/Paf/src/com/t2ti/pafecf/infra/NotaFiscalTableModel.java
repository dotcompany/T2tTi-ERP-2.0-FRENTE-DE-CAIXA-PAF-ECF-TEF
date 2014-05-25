/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Classe de modelo da tabela</p>
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
package com.t2ti.pafecf.infra;

import com.t2ti.pafecf.vo.NotaFiscalDetalheVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class NotaFiscalTableModel extends AbstractTableModel {

    private List<NotaFiscalDetalheVO> listaNotaDetalhe;

    public NotaFiscalTableModel(List<NotaFiscalDetalheVO> listaNota) {
        this.listaNotaDetalhe = listaNota;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        NotaFiscalDetalheVO notaDetalhe = listaNotaDetalhe.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return notaDetalhe.getItem();
            case 1:
                return notaDetalhe.getGtinProduto();
            case 2:
                return notaDetalhe.getDescricaoProduto();
            case 3:
                return Biblioteca.formatoDecimal("Q", notaDetalhe.getQuantidade().doubleValue());
            case 4:
                return Biblioteca.formatoDecimal("V", notaDetalhe.getValorUnitario().doubleValue());
            case 5:
                return Biblioteca.formatoDecimal("V", notaDetalhe.getValorTotal().doubleValue());
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listaNotaDetalhe.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public NotaFiscalDetalheVO getValues(int rowIndex) {
        return listaNotaDetalhe.get(rowIndex);
    }
}
