/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Classe para tratamento das colunas da tabela</p>
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

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ConfiguracaoColumnModel extends DefaultTableColumnModel {

    public ConfiguracaoColumnModel(FontMetrics fm) {
        addColumn(criaColuna(0, 80, fm, false, "ID_ECF_EMPRESA"));
        addColumn(criaColuna(1, 80, fm, false, "ID_ECF_CAIXA"));
        addColumn(criaColuna(2, 80, fm, false, "ID_ECF_IMPRESSORA"));
        addColumn(criaColuna(3, 80, fm, false, "ID_ECF_RESOLUCAO"));
        addColumn(criaColuna(4, 80, fm, false, "MENSAGEM_CUPOM"));
        addColumn(criaColuna(5, 80, fm, false, "PORTA_ECF"));
        addColumn(criaColuna(6, 80, fm, false, "PORTA_PINPAD"));
        addColumn(criaColuna(7, 80, fm, false, "PORTA_BALANCA"));
        addColumn(criaColuna(8, 80, fm, false, "IP_SERVIDOR"));
        addColumn(criaColuna(9, 80, fm, false, "IP_SITEF"));
        addColumn(criaColuna(10, 80, fm, false, "TIPO_TEF"));
        addColumn(criaColuna(11, 80, fm, false, "TITULO_TELA_CAIXA"));
        addColumn(criaColuna(12, 80, fm, false, "CAMINHO_IMAGENS_PRODUTOS"));
        addColumn(criaColuna(13, 80, fm, false, "CAMINHO_IMAGENS_MARKETING"));
        addColumn(criaColuna(14, 80, fm, false, "CAMINHO_IMAGENS_LAYOUT"));
        addColumn(criaColuna(15, 80, fm, false, "COR_JANELAS_INTERNAS"));
        addColumn(criaColuna(16, 80, fm, false, "MARKETING_ATIVO"));
        addColumn(criaColuna(17, 80, fm, false, "CFOP_ECF"));
        addColumn(criaColuna(18, 80, fm, false, "CFOP_NF2"));
        addColumn(criaColuna(19, 80, fm, false, "TIMEOUT_ECF"));
        addColumn(criaColuna(20, 80, fm, false, "INTERVALO_ECF"));
        addColumn(criaColuna(21, 80, fm, false, "DESCRICAO_SUPRIMENTO"));
        addColumn(criaColuna(22, 80, fm, false, "DESCRICAO_SANGRIA"));
        addColumn(criaColuna(23, 80, fm, false, "INDICE_GERENCIAL_DAV"));
    }

    private TableColumn criaColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {
        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new ConfiguracaoCellRenderer());
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);
        if (!resizable) {
            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizable);
        return col;
    }
}