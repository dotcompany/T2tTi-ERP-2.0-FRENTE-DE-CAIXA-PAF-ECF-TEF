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

import com.t2ti.pafecf.vo.DAVDetalheVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DAVDetalheTableModel extends AbstractTableModel {

    private List<DAVDetalheVO> listaDAVDetalhe;

    public DAVDetalheTableModel(List<DAVDetalheVO> listaDAVDetalhe) {
        this.listaDAVDetalhe = listaDAVDetalhe;
    }

    /**
     * Obtem o valor na linha e coluna.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        DAVDetalheVO davDetalhe = listaDAVDetalhe.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return davDetalhe.getSelecao();
            case 1:
                return davDetalhe.getNomeProduto();
            case 2:
                return davDetalhe.getQuantidade();
            case 3:
                return davDetalhe.getValorUnitario();
            case 4:
                return davDetalhe.getValorTotal();
        }
        return null;
    }

    /**
     * Retorna o numero de linhas no modelo.
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return listaDAVDetalhe.size();
    }

    /**
     * Retorna o numero de colunas no modelo.
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return 5;
    }

    public DAVDetalheVO getValues(int rowIndex) {
        return listaDAVDetalhe.get(rowIndex);
    }

    public void setValueAt(Object value, int row, int col) {
        if (value.toString().equals("")) {
            value = "";
        }
        listaDAVDetalhe.get(row).setSelecao(value.toString());
        fireTableCellUpdated(row, col);
    }
}