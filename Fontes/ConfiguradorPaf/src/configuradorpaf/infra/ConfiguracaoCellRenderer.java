/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Classe para personalizar o visual da tabela</p>
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
package configuradorpaf.infra;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class ConfiguracaoCellRenderer extends DefaultTableCellRenderer {

    public ConfiguracaoCellRenderer() {
        super();
    }

    public Component getTableCellRendererComponent(javax.swing.JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        Color corFundoZebrado = new Color(240, 240, 240);
        Color corFundoNormal = new Color(250, 250, 230);

        label.setFont(new java.awt.Font("Tahoma", 0, 12));

        if ((row % 2) == 0) {
            label.setBackground(corFundoNormal);
        } else {
            label.setBackground(corFundoZebrado);
        }

        if (isSelected) {
            label.setBackground(Color.lightGray);
        }

        if (column == 0) {
            //ID_ECF_EMPRESA
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 1) {
            //ID_ECF_CAIXA
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 2) {
            //ID_ECF_IMPRESSORA
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 3) {
            //ID_ECF_RESOLUCAO
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 4) {
            //MENSAGEM_CUPOM
            label.setHorizontalAlignment(LEFT);
        } else if (column == 5) {
            //PORTA_ECF
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 6) {
            //PORTA_PINPAD
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 7) {
            //PORTA_BALANCA
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 8) {
            //IP_SERVIDOR
            label.setHorizontalAlignment(LEFT);
        } else if (column == 9) {
            //IP_SITEF
            label.setHorizontalAlignment(LEFT);
        } else if (column == 10) {
            //TIPO_TEF
            label.setHorizontalAlignment(LEFT);
        } else if (column == 11) {
            //TITULO_TELA_CAIXA
            label.setHorizontalAlignment(LEFT);
        } else if (column == 12) {
            //CAMINHO_IMAGENS_PRODUTOS
            label.setHorizontalAlignment(LEFT);
        } else if (column == 13) {
            //CAMINHO_IMAGENS_MARKETING
            label.setHorizontalAlignment(LEFT);
        } else if (column == 14) {
            //CAMINHO_IMAGENS_LAYOUT
            label.setHorizontalAlignment(LEFT);
        } else if (column == 15) {
            //COR_JANELAS_INTERNAS
            label.setHorizontalAlignment(LEFT);
        } else if (column == 16) {
            //MARKETING_ATIVO
            label.setHorizontalAlignment(LEFT);
        } else if (column == 17) {
            //CFOP_ECF
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 18) {
            //CFOP_NF2
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 19) {
            //TIMEOUT_ECF
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 20) {
            //INTERVALO_ECF
            label.setHorizontalAlignment(RIGHT);
        } else if (column == 21) {
            //DESCRICAO_SUPRIMENTO
            label.setHorizontalAlignment(LEFT);
        } else if (column == 22) {
            //DESCRICAO_SANGRIA
            label.setHorizontalAlignment(LEFT);
        } else if (column == 23) {
            //INDICE_GERENCIAL_DAV
            label.setHorizontalAlignment(RIGHT);
        }
        return label;
    }
}
