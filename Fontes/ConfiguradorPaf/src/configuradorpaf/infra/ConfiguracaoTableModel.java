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
package configuradorpaf.infra;

import configuradorpaf.vo.ConfiguracaoVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ConfiguracaoTableModel extends AbstractTableModel {

    private List<ConfiguracaoVO> listaConfiguracao;

    public ConfiguracaoTableModel(List<ConfiguracaoVO> listaConfiguracao) {
        this.listaConfiguracao = listaConfiguracao;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        ConfiguracaoVO configuracao = listaConfiguracao.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return configuracao.getIdEmpresa();
            case 1:
                return configuracao.getIdCaixa();
            case 2:
                return configuracao.getImpressoraVO().getId();
            case 3:
                return configuracao.getResolucaoVO().getId();
            case 4:
                return configuracao.getMensagemCupom();
            case 5:
                return configuracao.getPortaEcf();
            case 6:
                return 0;
            case 7:
                return 0;
            case 8:
                return configuracao.getIpServidor();
            case 9:
                return configuracao.getIpSitef();
            case 10:
                return configuracao.getTipoTef();
            case 11:
                return configuracao.getTituloTelaCaixa();
            case 12:
                return configuracao.getCaminhoImagensProdutos();
            case 13:
                return configuracao.getCaminhoImagensMarketing();
            case 14:
                return configuracao.getCaminhoImagensLayout();
            case 15:
                return configuracao.getCorJanelasInternas();
            case 16:
                return configuracao.getMarketingAtivo();
            case 17:
                return configuracao.getCfopEcf();
            case 18:
                return configuracao.getCfopNf2();
            case 19:
                return configuracao.getTimeOutEcf();
            case 20:
                return configuracao.getIntervaloEcf();
            case 21:
                return configuracao.getDescricaoSuprimento();
            case 22:
                return configuracao.getDescricaoSangria();
            case 23:
                return configuracao.getIndiceGerencial();
        }
        return null;
    }

    public int getRowCount() {

        return listaConfiguracao.size();
    }

    public int getColumnCount() {
        return 23;
    }

    public ConfiguracaoVO getValues(int rowIndex) {
        return listaConfiguracao.get(rowIndex);
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {

        String valor = "";
        if (value.toString().equals("")) {
            valor = "0";
        } else {
            valor = value.toString();
        }

        switch (col) {
            case 0:
                listaConfiguracao.get(row).setIdEmpresa(Integer.valueOf(valor));
                break;
            case 1:
                listaConfiguracao.get(row).setIdCaixa(Integer.valueOf(valor));
                break;
            case 2:
                listaConfiguracao.get(row).getImpressoraVO().setId(Integer.valueOf(valor));
                break;
            case 3:
                listaConfiguracao.get(row).getResolucaoVO().setId(Integer.valueOf(valor));
                break;
            case 4:
                listaConfiguracao.get(row).setMensagemCupom(value.toString());
                break;
            case 5:
                listaConfiguracao.get(row).setPortaEcf(value.toString());
                break;
            case 6:
                //listaConfiguracao.get(row).setPortaPinPad(value.toString());
                break;
            case 7:
                //listaConfiguracao.get(row).setPortaBalanca(value.toString());
                break;
            case 8:
                listaConfiguracao.get(row).setIpServidor(value.toString());
                break;
            case 9:
                listaConfiguracao.get(row).setIpSitef(value.toString());
                break;
            case 10:
                listaConfiguracao.get(row).setTipoTef(value.toString());
                break;
            case 11:
                listaConfiguracao.get(row).setTituloTelaCaixa(value.toString());
                break;
            case 12:
                listaConfiguracao.get(row).setCaminhoImagensProdutos(value.toString());
                break;
            case 13:
                listaConfiguracao.get(row).setCaminhoImagensMarketing(value.toString());
                break;
            case 14:
                listaConfiguracao.get(row).setCaminhoImagensLayout(value.toString());
                break;
            case 15:
                listaConfiguracao.get(row).setCorJanelasInternas(value.toString());
                break;
            case 16:
                listaConfiguracao.get(row).setMarketingAtivo(value.toString());
                break;
            case 17:
                listaConfiguracao.get(row).setCfopEcf(Integer.valueOf(valor));
                break;
            case 18:
                listaConfiguracao.get(row).setCfopNf2(Integer.valueOf(valor));
                break;
            case 19:
                listaConfiguracao.get(row).setTimeOutEcf(Integer.valueOf(valor));
                break;
            case 20:
                listaConfiguracao.get(row).setIntervaloEcf(Integer.valueOf(valor));
                break;
            case 21:
                listaConfiguracao.get(row).setDescricaoSuprimento(value.toString());
                break;
            case 22:
                listaConfiguracao.get(row).setDescricaoSangria(value.toString());
                break;
            case 23:
                listaConfiguracao.get(row).setIndiceGerencial(value.toString());
                break;
        }
        fireTableCellUpdated(row, col);
    }
}