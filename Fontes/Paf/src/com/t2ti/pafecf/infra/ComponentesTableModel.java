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

import com.t2ti.pafecf.vo.PosicaoComponentesVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ComponentesTableModel extends AbstractTableModel {

    private List<PosicaoComponentesVO> listaPosicaoComponentes;

    public ComponentesTableModel(List<PosicaoComponentesVO> listaPosicaoComponentes) {
        this.listaPosicaoComponentes = listaPosicaoComponentes;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        PosicaoComponentesVO posicaoComponente = listaPosicaoComponentes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return posicaoComponente.getNomeComponente();
            case 1:
                return posicaoComponente.getAltura();
            case 2:
                return posicaoComponente.getLargura();
            case 3:
                return posicaoComponente.getTopo();
            case 4:
                return posicaoComponente.getEsquerda();
            case 5:
                return posicaoComponente.getTamanhoFonte();
            case 6:
                return posicaoComponente.getTextoComponente();
        }
        return null;
    }

    public int getRowCount() {

        return listaPosicaoComponentes.size();
    }

    public int getColumnCount() {
        return 7;
    }

    public PosicaoComponentesVO getValues(int rowIndex) {
        return listaPosicaoComponentes.get(rowIndex);
    }

    public boolean isCellEditable(int row, int col) {
        //configura a edição das colunas
        if (col == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {

        String valor = "";
        if (value.toString().equals("")) {
            valor = "0";
        } else {
            valor = value.toString();
        }

        switch (col) {
            case 1:
                listaPosicaoComponentes.get(row).setAltura(Integer.valueOf(valor));
                break;
            case 2:
                listaPosicaoComponentes.get(row).setLargura(Integer.valueOf(valor));
                break;
            case 3:
                listaPosicaoComponentes.get(row).setTopo(Integer.valueOf(valor));
                break;
            case 4:
                listaPosicaoComponentes.get(row).setEsquerda(Integer.valueOf(valor));
                break;
            case 5:
                listaPosicaoComponentes.get(row).setTamanhoFonte(Integer.valueOf(valor));
                break;
            case 6:
                listaPosicaoComponentes.get(row).setTextoComponente(value.toString());
                break;
        }
        fireTableCellUpdated(row, col);
    }
}
