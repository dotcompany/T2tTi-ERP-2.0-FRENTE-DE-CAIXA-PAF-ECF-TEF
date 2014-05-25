/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da configuração</p>
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
package configuradorpaf.controller;

import configuradorpaf.bd.AcessoBanco;
import configuradorpaf.vo.ConfiguracaoVO;
import configuradorpaf.vo.ImpressoraVO;
import configuradorpaf.vo.PosicaoComponentesVO;
import configuradorpaf.vo.ResolucaoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracaoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<PosicaoComponentesVO> verificaPosicaoTamanho() {
        List<PosicaoComponentesVO> listaPosicoes = new ArrayList<PosicaoComponentesVO>();
        consultaSQL =
                " select "
                + "P.ID,"
                + "P.NOME, "
                + "P.ALTURA, "
                + "P.LARGURA, "
                + "P.TOPO, "
                + "P.ESQUERDA, "
                + "P.TAMANHO_FONTE, "
                + "P.TEXTO, "
                + "C.ID_ECF_RESOLUCAO "
                + " from "
                + "ECF_POSICAO_COMPONENTES P, ECF_CONFIGURACAO C "
                + " where "
                + "P.ID_ECF_RESOLUCAO=C.ID_ECF_RESOLUCAO";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            rs.beforeFirst();
            while (rs.next()) {
                PosicaoComponentesVO posicao = new PosicaoComponentesVO();
                posicao.setId(rs.getInt("ID"));
                posicao.setNomeComponente(rs.getString("NOME"));
                posicao.setAltura(rs.getInt("ALTURA"));
                posicao.setLargura(rs.getInt("LARGURA"));
                posicao.setTopo(rs.getInt("TOPO"));
                posicao.setEsquerda(rs.getInt("ESQUERDA"));
                posicao.setTamanhoFonte(rs.getInt("TAMANHO_FONTE"));
                posicao.setTextoComponente(rs.getString("TEXTO"));
                listaPosicoes.add(posicao);
            }
            return listaPosicoes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public ConfiguracaoVO pegaConfiguracao() {
        ConfiguracaoVO configuracao = new ConfiguracaoVO();
        ImpressoraVO impressora = new ImpressoraVO();
        configuracao.setImpressoraVO(impressora);
        ResolucaoVO resolucao = new ResolucaoVO();
        configuracao.setResolucaoVO(resolucao);

        consultaSQL =
                "select "
                + " C.ID_ECF_IMPRESSORA, "
                + " C.ID_ECF_RESOLUCAO, "
                + " C.ID_ECF_CAIXA, "
                + " C.ID_ECF_EMPRESA, "
                + " C.MENSAGEM_CUPOM, "
                + " C.PORTA_ECF, "
                + " C.IP_SERVIDOR, "
                + " C.IP_SITEF, "
                + " C.TIPO_TEF, "
                + " C.TITULO_TELA_CAIXA, "
                + " C.CAMINHO_IMAGENS_PRODUTOS, "
                + " C.CAMINHO_IMAGENS_MARKETING, "
                + " C.CAMINHO_IMAGENS_LAYOUT, "
                + " C.COR_JANELAS_INTERNAS, "
                + " C.MARKETING_ATIVO, "
                + " C.CFOP_ECF, "
                + " C.CFOP_NF2, "
                + " C.TIMEOUT_ECF, "
                + " C.INTERVALO_ECF, "
                + " C.DESCRICAO_SUPRIMENTO, "
                + " C.DESCRICAO_SANGRIA, "
                + " C.TEF_TIPO_GP, "
                + " C.TEF_TEMPO_ESPERA, "
                + " C.TEF_ESPERA_STS, "
                + " C.TEF_NUMERO_VIAS, "
                + " C.DECIMAIS_QUANTIDADE, "
                + " C.DECIMAIS_VALOR, "
                + " C.BITS_POR_SEGUNDO, "
                + " C.QTDE_MAXIMA_CARTOES, "
                + " C.PESQUISA_PARTE, "
                + " C.CONFIGURACAO_BALANCA, "
                + " C.PARAMETROS_DIVERSOS, "
                + " C.ULTIMA_EXCLUSAO, "
                + " C.LAUDO, "
                + " C.INDICE_GERENCIAL, "
                + " C.DATA_ATUALIZACAO_ESTOQUE, "
                + " C.SINCRONIZADO, "
                + " R.RESOLUCAO_TELA, "
                + " R.LARGURA, "
                + " R.ALTURA, "
                + " R.IMAGEM_TELA, "
                + " R.IMAGEM_MENU, "
                + " R.IMAGEM_SUBMENU, "
                + " R.HOTTRACK_COLOR, "
                + " R.ITEM_STYLE_FONT_NAME, "
                + " R.ITEM_STYLE_FONT_COLOR, "
                + " R.ITEM_SEL_STYLE_COLOR, "
                + " R.LABEL_TOTAL_GERAL_FONT_COLOR, "
                + " R.ITEM_STYLE_FONT_STYLE,"
                + " R.EDITS_COLOR, "
                + " R.EDITS_FONT_COLOR, "
                + " R.EDITS_DISABLED_COLOR, "
                + " R.EDITS_FONT_NAME, "
                + " R.EDITS_FONT_STYLE, "
                + " (select nome from ECF_CAIXA where ECF_CAIXA.id=c.ID_ECF_CAIXA) AS NOME_CAIXA,"
                + " I.MODELO_ACBR, "
                + " I.SERIE "
                + "from "
                + " ECF_RESOLUCAO R, ECF_CONFIGURACAO C, ECF_IMPRESSORA I "
                + "where "
                + " C.ID_ECF_RESOLUCAO=R.ID and C.ID_ECF_IMPRESSORA=I.ID";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                configuracao.setIdImpressora(rs.getInt("ID_ECF_IMPRESSORA"));
                configuracao.setIdCaixa(rs.getInt("ID_ECF_CAIXA"));
                configuracao.setIdEmpresa(rs.getInt("ID_ECF_EMPRESA"));
                configuracao.setMensagemCupom(rs.getString("MENSAGEM_CUPOM"));
                configuracao.setPortaEcf(rs.getString("PORTA_ECF"));
                configuracao.setIpServidor(rs.getString("IP_SERVIDOR"));
                configuracao.setIpSitef(rs.getString("IP_SITEF"));
                configuracao.setTipoTef(rs.getString("TIPO_TEF"));
                configuracao.setTituloTelaCaixa(rs.getString("TITULO_TELA_CAIXA"));
                configuracao.setCaminhoImagensProdutos(rs.getString("CAMINHO_IMAGENS_PRODUTOS"));
                configuracao.setCaminhoImagensMarketing(rs.getString("CAMINHO_IMAGENS_MARKETING"));
                configuracao.setCaminhoImagensLayout(rs.getString("CAMINHO_IMAGENS_LAYOUT"));
                configuracao.setCorJanelasInternas(rs.getString("COR_JANELAS_INTERNAS"));
                configuracao.setMarketingAtivo(rs.getString("MARKETING_ATIVO"));
                configuracao.setCfopEcf(rs.getInt("CFOP_ECF"));
                configuracao.setCfopNf2(rs.getInt("CFOP_NF2"));
                configuracao.setTimeOutEcf(rs.getInt("TIMEOUT_ECF"));
                configuracao.setIntervaloEcf(rs.getInt("INTERVALO_ECF"));
                configuracao.setDescricaoSuprimento(rs.getString("DESCRICAO_SUPRIMENTO"));
                configuracao.setDescricaoSangria(rs.getString("DESCRICAO_SANGRIA"));
                configuracao.setTefTipoGp(rs.getInt("TEF_TIPO_GP"));
                configuracao.setTefTempoEspera(rs.getInt("TEF_TEMPO_ESPERA"));
                configuracao.setTefEsperaSts(rs.getInt("TEF_ESPERA_STS"));
                configuracao.setTefNumeroVias(rs.getInt("TEF_NUMERO_VIAS"));
                configuracao.setDecimaisQuantidade(rs.getInt("DECIMAIS_QUANTIDADE"));
                configuracao.setDecimaisValor(rs.getInt("DECIMAIS_VALOR"));
                configuracao.setBitsPorSegundo(rs.getInt("BITS_POR_SEGUNDO"));
                configuracao.setQtdeMaximaCartoes(rs.getInt("QTDE_MAXIMA_CARTOES"));
                configuracao.setPesquisaParte(rs.getString("PESQUISA_PARTE"));
                configuracao.setConfiguracaoBalanca(rs.getString("CONFIGURACAO_BALANCA"));
                configuracao.setParametrosDiversos(rs.getString("PARAMETROS_DIVERSOS"));
                configuracao.setUltimaExclusao(rs.getInt("ULTIMA_EXCLUSAO"));
                configuracao.setLaudo(rs.getString("LAUDO"));
                configuracao.setIndiceGerencial(rs.getString("INDICE_GERENCIAL"));
                configuracao.setDataAtualizacaoEstoque(rs.getDate("DATA_ATUALIZACAO_ESTOQUE"));

                configuracao.getImpressoraVO().setId(rs.getInt("ID_ECF_IMPRESSORA"));
                configuracao.getImpressoraVO().setModeloAcbr(rs.getString("MODELO_ACBR"));
                configuracao.getImpressoraVO().setSerie(rs.getString("SERIE"));

                configuracao.getResolucaoVO().setId(rs.getInt("ID_ECF_RESOLUCAO"));
                configuracao.getResolucaoVO().setResolucaoTela(rs.getString("RESOLUCAO_TELA"));
                configuracao.getResolucaoVO().setLargura(rs.getInt("LARGURA"));
                configuracao.getResolucaoVO().setAltura(rs.getInt("ALTURA"));
                configuracao.getResolucaoVO().setImagemTela(rs.getString("IMAGEM_TELA"));
                /*
                 * Exercício: Complete o VO da resolução caso queira fazer uso das opções abaixo no sistema
                 configuracao.getResolucaoVO().setImagemMenu(rs.getString("IMAGEM_MENU"));
                 configuracao.getResolucaoVO().setImagemSubMenu(rs.getString("IMAGEM_SUBMENU"));
                 configuracao.getResolucaoVO().setHotTrackColor(rs.getString("HOTTRACK_COLOR"));
                 configuracao.getResolucaoVO().setItemStyleFontName(rs.getString("ITEM_STYLE_FONT_NAME"));
                 configuracao.getResolucaoVO().setItemStyleFontColor(rs.getString("ITEM_STYLE_FONT_COLOR"));
                 configuracao.getResolucaoVO().setItemSelStyleColor(rs.getString("ITEM_SEL_STYLE_COLOR"));
                 configuracao.getResolucaoVO().setLabelTotalGeralFontColor(rs.getString("LABEL_TOTAL_GERAL_FONT_COLOR"));
                 configuracao.getResolucaoVO().setItemStyleFontStyle(rs.getString("ITEM_STYLE_FONT_STYLE"));
                 configuracao.getResolucaoVO().setEditColor(rs.getString("EDITS_COLOR"));
                 configuracao.getResolucaoVO().setEditFontColor(rs.getString("EDITS_FONT_COLOR"));
                 configuracao.getResolucaoVO().setEditDisabledColor(rs.getString("EDITS_DISABLED_COLOR"));
                 configuracao.getResolucaoVO().setEditFontName(rs.getString("EDITS_FONT_NAME"));
                 configuracao.getResolucaoVO().setEditFontStyle(rs.getString("EDITS_FONT_STYLE"));
                 */
                configuracao.setNomeCaixa(rs.getString("NOME_CAIXA"));
                configuracao.setModeloImpressora(rs.getString("MODELO_ACBR"));
                configuracao.setNumSerieEcf(rs.getString("SERIE"));

                //******* Balança ********************************************
                String[] linha = configuracao.getConfiguracaoBalanca().split("\\|");

                configuracao.setBalancaModelo(Integer.valueOf(linha[0]));
                configuracao.setBalancaIdentificadorBalanca(linha[1]);
                configuracao.setBalancaHandShaking(Integer.valueOf(linha[2]));
                configuracao.setBalancaParity(Integer.valueOf(linha[3]));
                configuracao.setBalancaStopBits(Integer.valueOf(linha[4]));
                configuracao.setBalancaDataBits(Integer.valueOf(linha[5]));
                configuracao.setBalancaBaudRate(Integer.valueOf(linha[6]));
                configuracao.setBalancaPortaSerial(linha[7]);
                configuracao.setBalancaTimeOut(Integer.valueOf(linha[8]));
                configuracao.setBalancaTipoConfiguracaoBalanca(linha[9]);
                //*******  Fim Balança ***************************************

                //******* Índice Gerencial ***********************************************
                linha = configuracao.getIndiceGerencial().split("\\|");

                configuracao.setGerencialX(Integer.valueOf(linha[0]));
                configuracao.setMeiosDePagamento(Integer.valueOf(linha[1]));
                configuracao.setDavEmitidos(Integer.valueOf(linha[2]));
                configuracao.setIdentificacaoPaf(Integer.valueOf(linha[3]));
                configuracao.setParametrosDeConfiguracao(Integer.valueOf(linha[4]));
                configuracao.setRelatorio(Integer.valueOf(linha[5]));
                //******* Fim Índice Gerencial ********************************************

                //******* Parâmetros Diversos *********************************************
                linha = configuracao.getParametrosDiversos().split("\\|");

                configuracao.setPedeCPFCupom(Integer.valueOf(linha[0]));
                configuracao.setUsaIntegracao(Integer.valueOf(linha[0]));
                configuracao.setTimerIntegracao(Integer.valueOf(linha[0]));
                configuracao.setGavetaDinheiro(Integer.valueOf(linha[0]));
                configuracao.setSinalInvertido(Integer.valueOf(linha[0]));
                configuracao.setQtdeMaximaParcelas(Integer.valueOf(linha[0]));
                configuracao.setImprimeParcelas(Integer.valueOf(linha[0]));
                configuracao.setTecladoReduzido(Integer.valueOf(linha[0]));
                //Leitor Serial
                configuracao.setUsaLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setPortaLeitorSerial(linha[0]);
                configuracao.setBaudLeitorSerial(linha[0]);
                configuracao.setSufixoLeitorSerial(linha[0]);
                configuracao.setIntervaloLeitorSerial(linha[0]);
                configuracao.setDataLeitorSerial(linha[0]);
                configuracao.setParidadeLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setHardFlowLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setSoftFlowLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setHandShakeLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setStopLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setFilaLeitorSerial(Integer.valueOf(linha[0]));
                configuracao.setExcluiSufixoLeitorSerial(Integer.valueOf(linha[0]));
                //Lançamento Notas Manuais
                configuracao.setLancamentoNotasManuais(Integer.valueOf(linha[0]));
                //******* Fim Parâmetros Diversos *****************************************

                return configuracao;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void gravarPosicaoComponentes(List<PosicaoComponentesVO>  pListaPosicaoComponentes) {
        consultaSQL =
              "update ECF_POSICAO_COMPONENTES set " +
              " NOME = ?, " +
              " ALTURA = ?, " +
              " LARGURA = ?, " +
              " TOPO = ?, " +
              " ESQUERDA = ?, " +
              " TAMANHO_FONTE = ?, " +
              " TEXTO = ? " +
              " where ID_ECF_RESOLUCAO = ? and NOME = ?";

        PosicaoComponentesVO posicaoComponente;
        
        try {
            for (int i = 0; i <= pListaPosicaoComponentes.size() - 1; i++)
            {
                posicaoComponente = pListaPosicaoComponentes.get(i);
                
                pstm = bd.conectar().prepareStatement(consultaSQL);

                pstm.setString(1, posicaoComponente.getNomeComponente());
                pstm.setInt(2, posicaoComponente.getAltura());
                pstm.setInt(3, posicaoComponente.getLargura());
                pstm.setInt(4, posicaoComponente.getTopo());
                pstm.setInt(5, posicaoComponente.getEsquerda());
                pstm.setInt(6, posicaoComponente.getTamanhoFonte());
                pstm.setString(7, posicaoComponente.getTextoComponente());
                pstm.setInt(8, posicaoComponente.getIdResolucao());
                pstm.setString(9, posicaoComponente.getNomeComponente());
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }
    
}