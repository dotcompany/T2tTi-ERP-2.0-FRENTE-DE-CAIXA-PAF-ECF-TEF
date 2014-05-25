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

import com.t2ti.pafecf.vo.DAVCabecalhoVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DAVCabecalhoTableModel extends AbstractTableModel {

    private List<DAVCabecalhoVO> listaDAVCabecalho;

    public DAVCabecalhoTableModel(List<DAVCabecalhoVO> listaDAVCabecalho) {
        this.listaDAVCabecalho = listaDAVCabecalho;
    }

    /**
     * Obtem o valor na linha e coluna.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        DAVCabecalhoVO davCabecalho = listaDAVCabecalho.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return davCabecalho.getSelecao();
            case 1:
                return davCabecalho.getId();
            case 2:
                return davCabecalho.getNumeroDav();
            case 3:
                return davCabecalho.getNomeDestinatario();
            case 4:
                return davCabecalho.getCpfCnpjDestinatario();
            case 5:
                return davCabecalho.getDataEmissao();
            case 6:
                return davCabecalho.getValor();
        }
        return null;
    }

    /**
     * Retorna o numero de linhas no modelo.
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return listaDAVCabecalho.size();
    }

    /**
     * Retorna o numero de colunas no modelo.
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return 6;
    }

    public DAVCabecalhoVO getValues(int rowIndex) {
        return listaDAVCabecalho.get(rowIndex);
    }

    public boolean isCellEditable(int row, int col) {
        //informa as colunas que não desejamos edição
        return false;
    }

    public void setValueAt(Object value, int row, int col) {
        if (value.toString().equals("")) {
            value = "";
        }
        listaDAVCabecalho.get(row).setSelecao(value.toString());
        fireTableCellUpdated(row, col);
    }
}
