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

import com.t2ti.pafecf.vo.PreVendaCabecalhoVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PreVendaCabecalhoTableModel extends AbstractTableModel {

    private List<PreVendaCabecalhoVO> listaPreVendaCabecalho;

    public PreVendaCabecalhoTableModel(List<PreVendaCabecalhoVO> listaPreVendaCabecalho) {
        this.listaPreVendaCabecalho = listaPreVendaCabecalho;
    }

    /**
     * Obtem o valor na linha e coluna.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        PreVendaCabecalhoVO PreVendaCabecalho = listaPreVendaCabecalho.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return PreVendaCabecalho.getSelecao();
            case 1:
                return PreVendaCabecalho.getId();
            case 2:
                return PreVendaCabecalho.getDataEmissao();
            case 3:
                return PreVendaCabecalho.getValor();
        }
        return null;
    }

    /**
     * Retorna o numero de linhas no modelo.
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return listaPreVendaCabecalho.size();
    }

    /**
     * Retorna o numero de colunas no modelo.
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return 4;
    }

    public PreVendaCabecalhoVO getValues(int rowIndex) {
        return listaPreVendaCabecalho.get(rowIndex);
    }

    public boolean isCellEditable(int row, int col) {
        //informa as colunas que não desejamos edição
        return false;
    }

    public void setValueAt(Object value, int row, int col) {
        if (value.toString().equals("")) {
            value = "";
        }
        listaPreVendaCabecalho.get(row).setSelecao(value.toString());
        fireTableCellUpdated(row, col);
    }
}
