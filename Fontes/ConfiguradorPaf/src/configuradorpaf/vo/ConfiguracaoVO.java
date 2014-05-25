/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_CONFIGURACAO</p>
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
package configuradorpaf.vo;

import java.util.Date;

public class ConfiguracaoVO {
    private Integer id;
    private Integer idCaixa;
    private Integer idEmpresa;
    private Integer idResolucao;
    private Integer idImpressora;
    private String mensagemCupom;
    private String portaEcf;
    private String ipServidor;
    private String ipSitef;
    private String tipoTef;
    private String tituloTelaCaixa;
    private String caminhoImagensProdutos;
    private String caminhoImagensMarketing;
    private String caminhoImagensLayout;
    private String corJanelasInternas;
    private String marketingAtivo;
    private Integer cfopEcf;
    private Integer cfopNf2;
    private Integer timeOutEcf;
    private Integer intervaloEcf;
    private String descricaoSuprimento;
    private String descricaoSangria;
    private Integer tefTipoGp;
    private Integer tefTempoEspera;
    private Integer tefEsperaSts;
    private Integer tefNumeroVias;
    private Integer decimaisQuantidade;
    private Integer decimaisValor;
    private Integer bitsPorSegundo;
    private Integer qtdeMaximaCartoes;
    private String pesquisaParte;
    private String configuracaoBalanca;
    private String parametrosDiversos;
    private Integer ultimaExclusao;
    private String laudo;
    private String indiceGerencial;
    private Date dataAtualizacaoEstoque;
    private String sincronizado;
    private ResolucaoVO resolucaoVO;
    private ImpressoraVO impressoraVO;

    /*
     * campos transientes
     */
    private String nomeCaixa;
    private String modeloImpressora;

    // Montados a partir do campo configuracaoBalanca
    private String balancaIdentificadorBalanca;
    private Integer balancaModelo;
    private Integer balancaHandShaking;
    private Integer balancaParity;
    private Integer balancaStopBits;
    private Integer balancaDataBits;
    private Integer balancaBaudRate;
    private String balancaPortaSerial;
    private Integer balancaTimeOut;
    private String balancaTipoConfiguracaoBalanca;
    
    // Montados a partir do campo indiceGerencial
    private Integer gerencialX;
    private Integer meiosDePagamento;
    private Integer davEmitidos;
    private Integer identificacaoPaf;
    private Integer parametrosDeConfiguracao;
    private Integer relatorio;

    // Montados a partir do campo parametrosDiversos
    private Integer pedeCPFCupom;
    private Integer usaIntegracao;
    private Integer timerIntegracao;
    private Integer gavetaDinheiro;
    private Integer sinalInvertido;
    private Integer qtdeMaximaParcelas;
    private Integer imprimeParcelas;
    private Integer tecladoReduzido;
    private Integer usaLeitorSerial;
    private String portaLeitorSerial;
    private String baudLeitorSerial;
    private String sufixoLeitorSerial;
    private String intervaloLeitorSerial;
    private String dataLeitorSerial;
    private Integer paridadeLeitorSerial;
    private Integer hardFlowLeitorSerial;
    private Integer softFlowLeitorSerial;
    private Integer handShakeLeitorSerial;
    private Integer stopLeitorSerial;
    private Integer filaLeitorSerial;
    private Integer excluiSufixoLeitorSerial;
    private Integer lancamentoNotasManuais;
    private String numSerieEcf;
    
    public ConfiguracaoVO() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idCaixa
     */
    public Integer getIdCaixa() {
        return idCaixa;
    }

    /**
     * @param idCaixa the idCaixa to set
     */
    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    /**
     * @return the idEmpresa
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the mensagemCupom
     */
    public String getMensagemCupom() {
        return mensagemCupom;
    }

    /**
     * @param mensagemCupom the mensagemCupom to set
     */
    public void setMensagemCupom(String mensagemCupom) {
        this.mensagemCupom = mensagemCupom;
    }

    /**
     * @return the portaEcf
     */
    public String getPortaEcf() {
        return portaEcf;
    }

    /**
     * @param portaEcf the portaEcf to set
     */
    public void setPortaEcf(String portaEcf) {
        this.portaEcf = portaEcf;
    }

    /**
     * @return the ipServidor
     */
    public String getIpServidor() {
        return ipServidor;
    }

    /**
     * @param ipServidor the ipServidor to set
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    /**
     * @return the ipSitef
     */
    public String getIpSitef() {
        return ipSitef;
    }

    /**
     * @param ipSitef the ipSitef to set
     */
    public void setIpSitef(String ipSitef) {
        this.ipSitef = ipSitef;
    }

    /**
     * @return the tipoTef
     */
    public String getTipoTef() {
        return tipoTef;
    }

    /**
     * @param tipoTef the tipoTef to set
     */
    public void setTipoTef(String tipoTef) {
        this.tipoTef = tipoTef;
    }

    /**
     * @return the tituloTelaCaixa
     */
    public String getTituloTelaCaixa() {
        return tituloTelaCaixa;
    }

    /**
     * @param tituloTelaCaixa the tituloTelaCaixa to set
     */
    public void setTituloTelaCaixa(String tituloTelaCaixa) {
        this.tituloTelaCaixa = tituloTelaCaixa;
    }

    /**
     * @return the caminhoImagensProdutos
     */
    public String getCaminhoImagensProdutos() {
        return caminhoImagensProdutos;
    }

    /**
     * @param caminhoImagensProdutos the caminhoImagensProdutos to set
     */
    public void setCaminhoImagensProdutos(String caminhoImagensProdutos) {
        this.caminhoImagensProdutos = caminhoImagensProdutos;
    }

    /**
     * @return the caminhoImagensMarketing
     */
    public String getCaminhoImagensMarketing() {
        return caminhoImagensMarketing;
    }

    /**
     * @param caminhoImagensMarketing the caminhoImagensMarketing to set
     */
    public void setCaminhoImagensMarketing(String caminhoImagensMarketing) {
        this.caminhoImagensMarketing = caminhoImagensMarketing;
    }

    /**
     * @return the caminhoImagensLayout
     */
    public String getCaminhoImagensLayout() {
        return caminhoImagensLayout;
    }

    /**
     * @param caminhoImagensLayout the caminhoImagensLayout to set
     */
    public void setCaminhoImagensLayout(String caminhoImagensLayout) {
        this.caminhoImagensLayout = caminhoImagensLayout;
    }

    /**
     * @return the corJanelasInternas
     */
    public String getCorJanelasInternas() {
        return corJanelasInternas;
    }

    /**
     * @param corJanelasInternas the corJanelasInternas to set
     */
    public void setCorJanelasInternas(String corJanelasInternas) {
        this.corJanelasInternas = corJanelasInternas;
    }

    /**
     * @return the marketingAtivo
     */
    public String getMarketingAtivo() {
        return marketingAtivo;
    }

    /**
     * @param marketingAtivo the marketingAtivo to set
     */
    public void setMarketingAtivo(String marketingAtivo) {
        this.marketingAtivo = marketingAtivo;
    }

    /**
     * @return the cfopEcf
     */
    public Integer getCfopEcf() {
        return cfopEcf;
    }

    /**
     * @param cfopEcf the cfopEcf to set
     */
    public void setCfopEcf(Integer cfopEcf) {
        this.cfopEcf = cfopEcf;
    }

    /**
     * @return the cfopNf2
     */
    public Integer getCfopNf2() {
        return cfopNf2;
    }

    /**
     * @param cfopNf2 the cfopNf2 to set
     */
    public void setCfopNf2(Integer cfopNf2) {
        this.cfopNf2 = cfopNf2;
    }

    /**
     * @return the timeOutEcf
     */
    public Integer getTimeOutEcf() {
        return timeOutEcf;
    }

    /**
     * @param timeOutEcf the timeOutEcf to set
     */
    public void setTimeOutEcf(Integer timeOutEcf) {
        this.timeOutEcf = timeOutEcf;
    }

    /**
     * @return the intervaloEcf
     */
    public Integer getIntervaloEcf() {
        return intervaloEcf;
    }

    /**
     * @param intervaloEcf the intervaloEcf to set
     */
    public void setIntervaloEcf(Integer intervaloEcf) {
        this.intervaloEcf = intervaloEcf;
    }

    /**
     * @return the descricaoSuprimento
     */
    public String getDescricaoSuprimento() {
        return descricaoSuprimento;
    }

    /**
     * @param descricaoSuprimento the descricaoSuprimento to set
     */
    public void setDescricaoSuprimento(String descricaoSuprimento) {
        this.descricaoSuprimento = descricaoSuprimento;
    }

    /**
     * @return the descricaoSangria
     */
    public String getDescricaoSangria() {
        return descricaoSangria;
    }

    /**
     * @param descricaoSangria the descricaoSangria to set
     */
    public void setDescricaoSangria(String descricaoSangria) {
        this.descricaoSangria = descricaoSangria;
    }

    /**
     * @return the tefTipoGp
     */
    public Integer getTefTipoGp() {
        return tefTipoGp;
    }

    /**
     * @param tefTipoGp the tefTipoGp to set
     */
    public void setTefTipoGp(Integer tefTipoGp) {
        this.tefTipoGp = tefTipoGp;
    }

    /**
     * @return the tefTempoEspera
     */
    public Integer getTefTempoEspera() {
        return tefTempoEspera;
    }

    /**
     * @param tefTempoEspera the tefTempoEspera to set
     */
    public void setTefTempoEspera(Integer tefTempoEspera) {
        this.tefTempoEspera = tefTempoEspera;
    }

    /**
     * @return the tefEsperaSts
     */
    public Integer getTefEsperaSts() {
        return tefEsperaSts;
    }

    /**
     * @param tefEsperaSts the tefEsperaSts to set
     */
    public void setTefEsperaSts(Integer tefEsperaSts) {
        this.tefEsperaSts = tefEsperaSts;
    }

    /**
     * @return the tefNumeroVias
     */
    public Integer getTefNumeroVias() {
        return tefNumeroVias;
    }

    /**
     * @param tefNumeroVias the tefNumeroVias to set
     */
    public void setTefNumeroVias(Integer tefNumeroVias) {
        this.tefNumeroVias = tefNumeroVias;
    }

    /**
     * @return the decimaisQuantidade
     */
    public Integer getDecimaisQuantidade() {
        return decimaisQuantidade;
    }

    /**
     * @param decimaisQuantidade the decimaisQuantidade to set
     */
    public void setDecimaisQuantidade(Integer decimaisQuantidade) {
        this.decimaisQuantidade = decimaisQuantidade;
    }

    /**
     * @return the decimaisValor
     */
    public Integer getDecimaisValor() {
        return decimaisValor;
    }

    /**
     * @param decimaisValor the decimaisValor to set
     */
    public void setDecimaisValor(Integer decimaisValor) {
        this.decimaisValor = decimaisValor;
    }

    /**
     * @return the bitsPorSegundo
     */
    public Integer getBitsPorSegundo() {
        return bitsPorSegundo;
    }

    /**
     * @param bitsPorSegundo the bitsPorSegundo to set
     */
    public void setBitsPorSegundo(Integer bitsPorSegundo) {
        this.bitsPorSegundo = bitsPorSegundo;
    }

    /**
     * @return the qtdeMaximaCartoes
     */
    public Integer getQtdeMaximaCartoes() {
        return qtdeMaximaCartoes;
    }

    /**
     * @param qtdeMaximaCartoes the qtdeMaximaCartoes to set
     */
    public void setQtdeMaximaCartoes(Integer qtdeMaximaCartoes) {
        this.qtdeMaximaCartoes = qtdeMaximaCartoes;
    }

    /**
     * @return the pesquisaParte
     */
    public String getPesquisaParte() {
        return pesquisaParte;
    }

    /**
     * @param pesquisaParte the pesquisaParte to set
     */
    public void setPesquisaParte(String pesquisaParte) {
        this.pesquisaParte = pesquisaParte;
    }

    /**
     * @return the configuracaoBalanca
     */
    public String getConfiguracaoBalanca() {
        return configuracaoBalanca;
    }

    /**
     * @param configuracaoBalanca the configuracaoBalanca to set
     */
    public void setConfiguracaoBalanca(String configuracaoBalanca) {
        this.configuracaoBalanca = configuracaoBalanca;
    }

    /**
     * @return the parametrosDiversos
     */
    public String getParametrosDiversos() {
        return parametrosDiversos;
    }

    /**
     * @param parametrosDiversos the parametrosDiversos to set
     */
    public void setParametrosDiversos(String parametrosDiversos) {
        this.parametrosDiversos = parametrosDiversos;
    }

    /**
     * @return the ultimaExclusao
     */
    public Integer getUltimaExclusao() {
        return ultimaExclusao;
    }

    /**
     * @param ultimaExclusao the ultimaExclusao to set
     */
    public void setUltimaExclusao(Integer ultimaExclusao) {
        this.ultimaExclusao = ultimaExclusao;
    }

    /**
     * @return the laudo
     */
    public String getLaudo() {
        return laudo;
    }

    /**
     * @param laudo the laudo to set
     */
    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    /**
     * @return the indiceGerencial
     */
    public String getIndiceGerencial() {
        return indiceGerencial;
    }

    /**
     * @param indiceGerencial the indiceGerencial to set
     */
    public void setIndiceGerencial(String indiceGerencial) {
        this.indiceGerencial = indiceGerencial;
    }

    /**
     * @return the dataAtualizacaoEstoque
     */
    public Date getDataAtualizacaoEstoque() {
        return dataAtualizacaoEstoque;
    }

    /**
     * @param dataAtualizacaoEstoque the dataAtualizacaoEstoque to set
     */
    public void setDataAtualizacaoEstoque(Date dataAtualizacaoEstoque) {
        this.dataAtualizacaoEstoque = dataAtualizacaoEstoque;
    }

    /**
     * @return the sincronizado
     */
    public String getSincronizado() {
        return sincronizado;
    }

    /**
     * @param sincronizado the sincronizado to set
     */
    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    /**
     * @return the resolucaoVO
     */
    public ResolucaoVO getResolucaoVO() {
        return resolucaoVO;
    }

    /**
     * @param resolucaoVO the resolucaoVO to set
     */
    public void setResolucaoVO(ResolucaoVO resolucaoVO) {
        this.resolucaoVO = resolucaoVO;
    }

    /**
     * @return the impressoraVO
     */
    public ImpressoraVO getImpressoraVO() {
        return impressoraVO;
    }

    /**
     * @param impressoraVO the impressoraVO to set
     */
    public void setImpressoraVO(ImpressoraVO impressoraVO) {
        this.impressoraVO = impressoraVO;
    }

    /**
     * @return the nomeCaixa
     */
    public String getNomeCaixa() {
        return nomeCaixa;
    }

    /**
     * @param nomeCaixa the nomeCaixa to set
     */
    public void setNomeCaixa(String nomeCaixa) {
        this.nomeCaixa = nomeCaixa;
    }

    /**
     * @return the balancaIdentificadorBalanca
     */
    public String getBalancaIdentificadorBalanca() {
        return balancaIdentificadorBalanca;
    }

    /**
     * @param balancaIdentificadorBalanca the balancaIdentificadorBalanca to set
     */
    public void setBalancaIdentificadorBalanca(String balancaIdentificadorBalanca) {
        this.balancaIdentificadorBalanca = balancaIdentificadorBalanca;
    }

    /**
     * @return the balancaModelo
     */
    public Integer getBalancaModelo() {
        return balancaModelo;
    }

    /**
     * @param balancaModelo the balancaModelo to set
     */
    public void setBalancaModelo(Integer balancaModelo) {
        this.balancaModelo = balancaModelo;
    }

    /**
     * @return the balancaHandShaking
     */
    public Integer getBalancaHandShaking() {
        return balancaHandShaking;
    }

    /**
     * @param balancaHandShaking the balancaHandShaking to set
     */
    public void setBalancaHandShaking(Integer balancaHandShaking) {
        this.balancaHandShaking = balancaHandShaking;
    }

    /**
     * @return the balancaParity
     */
    public Integer getBalancaParity() {
        return balancaParity;
    }

    /**
     * @param balancaParity the balancaParity to set
     */
    public void setBalancaParity(Integer balancaParity) {
        this.balancaParity = balancaParity;
    }

    /**
     * @return the balancaStopBits
     */
    public Integer getBalancaStopBits() {
        return balancaStopBits;
    }

    /**
     * @param balancaStopBits the balancaStopBits to set
     */
    public void setBalancaStopBits(Integer balancaStopBits) {
        this.balancaStopBits = balancaStopBits;
    }

    /**
     * @return the balancaDataBits
     */
    public Integer getBalancaDataBits() {
        return balancaDataBits;
    }

    /**
     * @param balancaDataBits the balancaDataBits to set
     */
    public void setBalancaDataBits(Integer balancaDataBits) {
        this.balancaDataBits = balancaDataBits;
    }

    /**
     * @return the balancaBaudRate
     */
    public Integer getBalancaBaudRate() {
        return balancaBaudRate;
    }

    /**
     * @param balancaBaudRate the balancaBaudRate to set
     */
    public void setBalancaBaudRate(Integer balancaBaudRate) {
        this.balancaBaudRate = balancaBaudRate;
    }

    /**
     * @return the balancaPortaSerial
     */
    public String getBalancaPortaSerial() {
        return balancaPortaSerial;
    }

    /**
     * @param balancaPortaSerial the balancaPortaSerial to set
     */
    public void setBalancaPortaSerial(String balancaPortaSerial) {
        this.balancaPortaSerial = balancaPortaSerial;
    }

    /**
     * @return the balancaTimeOut
     */
    public Integer getBalancaTimeOut() {
        return balancaTimeOut;
    }

    /**
     * @param balancaTimeOut the balancaTimeOut to set
     */
    public void setBalancaTimeOut(Integer balancaTimeOut) {
        this.balancaTimeOut = balancaTimeOut;
    }

    /**
     * @return the balancaTipoConfiguracaoBalanca
     */
    public String getBalancaTipoConfiguracaoBalanca() {
        return balancaTipoConfiguracaoBalanca;
    }

    /**
     * @param balancaTipoConfiguracaoBalanca the balancaTipoConfiguracaoBalanca to set
     */
    public void setBalancaTipoConfiguracaoBalanca(String balancaTipoConfiguracaoBalanca) {
        this.balancaTipoConfiguracaoBalanca = balancaTipoConfiguracaoBalanca;
    }

    /**
     * @return the gerencialX
     */
    public Integer getGerencialX() {
        return gerencialX;
    }

    /**
     * @param gerencialX the gerencialX to set
     */
    public void setGerencialX(Integer gerencialX) {
        this.gerencialX = gerencialX;
    }

    /**
     * @return the meiosDePagamento
     */
    public Integer getMeiosDePagamento() {
        return meiosDePagamento;
    }

    /**
     * @param meiosDePagamento the meiosDePagamento to set
     */
    public void setMeiosDePagamento(Integer meiosDePagamento) {
        this.meiosDePagamento = meiosDePagamento;
    }

    /**
     * @return the davEmitidos
     */
    public Integer getDavEmitidos() {
        return davEmitidos;
    }

    /**
     * @param davEmitidos the davEmitidos to set
     */
    public void setDavEmitidos(Integer davEmitidos) {
        this.davEmitidos = davEmitidos;
    }

    /**
     * @return the identificacaoPaf
     */
    public Integer getIdentificacaoPaf() {
        return identificacaoPaf;
    }

    /**
     * @param identificacaoPaf the identificacaoPaf to set
     */
    public void setIdentificacaoPaf(Integer identificacaoPaf) {
        this.identificacaoPaf = identificacaoPaf;
    }

    /**
     * @return the parametrosDeConfiguracao
     */
    public Integer getParametrosDeConfiguracao() {
        return parametrosDeConfiguracao;
    }

    /**
     * @param parametrosDeConfiguracao the parametrosDeConfiguracao to set
     */
    public void setParametrosDeConfiguracao(Integer parametrosDeConfiguracao) {
        this.parametrosDeConfiguracao = parametrosDeConfiguracao;
    }

    /**
     * @return the relatorio
     */
    public Integer getRelatorio() {
        return relatorio;
    }

    /**
     * @param relatorio the relatorio to set
     */
    public void setRelatorio(Integer relatorio) {
        this.relatorio = relatorio;
    }

    /**
     * @return the pedeCPFCupom
     */
    public Integer getPedeCPFCupom() {
        return pedeCPFCupom;
    }

    /**
     * @param pedeCPFCupom the pedeCPFCupom to set
     */
    public void setPedeCPFCupom(Integer pedeCPFCupom) {
        this.pedeCPFCupom = pedeCPFCupom;
    }

    /**
     * @return the usaIntegracao
     */
    public Integer getUsaIntegracao() {
        return usaIntegracao;
    }

    /**
     * @param usaIntegracao the usaIntegracao to set
     */
    public void setUsaIntegracao(Integer usaIntegracao) {
        this.usaIntegracao = usaIntegracao;
    }

    /**
     * @return the timerIntegracao
     */
    public Integer getTimerIntegracao() {
        return timerIntegracao;
    }

    /**
     * @param timerIntegracao the timerIntegracao to set
     */
    public void setTimerIntegracao(Integer timerIntegracao) {
        this.timerIntegracao = timerIntegracao;
    }

    /**
     * @return the gavetaDinheiro
     */
    public Integer getGavetaDinheiro() {
        return gavetaDinheiro;
    }

    /**
     * @param gavetaDinheiro the gavetaDinheiro to set
     */
    public void setGavetaDinheiro(Integer gavetaDinheiro) {
        this.gavetaDinheiro = gavetaDinheiro;
    }

    /**
     * @return the sinalInvertido
     */
    public Integer getSinalInvertido() {
        return sinalInvertido;
    }

    /**
     * @param sinalInvertido the sinalInvertido to set
     */
    public void setSinalInvertido(Integer sinalInvertido) {
        this.sinalInvertido = sinalInvertido;
    }

    /**
     * @return the qtdeMaximaParcelas
     */
    public Integer getQtdeMaximaParcelas() {
        return qtdeMaximaParcelas;
    }

    /**
     * @param qtdeMaximaParcelas the qtdeMaximaParcelas to set
     */
    public void setQtdeMaximaParcelas(Integer qtdeMaximaParcelas) {
        this.qtdeMaximaParcelas = qtdeMaximaParcelas;
    }

    /**
     * @return the imprimeParcelas
     */
    public Integer getImprimeParcelas() {
        return imprimeParcelas;
    }

    /**
     * @param imprimeParcelas the imprimeParcelas to set
     */
    public void setImprimeParcelas(Integer imprimeParcelas) {
        this.imprimeParcelas = imprimeParcelas;
    }

    /**
     * @return the tecladoReduzido
     */
    public Integer getTecladoReduzido() {
        return tecladoReduzido;
    }

    /**
     * @param tecladoReduzido the tecladoReduzido to set
     */
    public void setTecladoReduzido(Integer tecladoReduzido) {
        this.tecladoReduzido = tecladoReduzido;
    }

    /**
     * @return the usaLeitorSerial
     */
    public Integer getUsaLeitorSerial() {
        return usaLeitorSerial;
    }

    /**
     * @param usaLeitorSerial the usaLeitorSerial to set
     */
    public void setUsaLeitorSerial(Integer usaLeitorSerial) {
        this.usaLeitorSerial = usaLeitorSerial;
    }

    /**
     * @return the portaLeitorSerial
     */
    public String getPortaLeitorSerial() {
        return portaLeitorSerial;
    }

    /**
     * @param portaLeitorSerial the portaLeitorSerial to set
     */
    public void setPortaLeitorSerial(String portaLeitorSerial) {
        this.portaLeitorSerial = portaLeitorSerial;
    }

    /**
     * @return the baudLeitorSerial
     */
    public String getBaudLeitorSerial() {
        return baudLeitorSerial;
    }

    /**
     * @param baudLeitorSerial the baudLeitorSerial to set
     */
    public void setBaudLeitorSerial(String baudLeitorSerial) {
        this.baudLeitorSerial = baudLeitorSerial;
    }

    /**
     * @return the sufixoLeitorSerial
     */
    public String getSufixoLeitorSerial() {
        return sufixoLeitorSerial;
    }

    /**
     * @param sufixoLeitorSerial the sufixoLeitorSerial to set
     */
    public void setSufixoLeitorSerial(String sufixoLeitorSerial) {
        this.sufixoLeitorSerial = sufixoLeitorSerial;
    }

    /**
     * @return the intervaloLeitorSerial
     */
    public String getIntervaloLeitorSerial() {
        return intervaloLeitorSerial;
    }

    /**
     * @param intervaloLeitorSerial the intervaloLeitorSerial to set
     */
    public void setIntervaloLeitorSerial(String intervaloLeitorSerial) {
        this.intervaloLeitorSerial = intervaloLeitorSerial;
    }

    /**
     * @return the dataLeitorSerial
     */
    public String getDataLeitorSerial() {
        return dataLeitorSerial;
    }

    /**
     * @param dataLeitorSerial the dataLeitorSerial to set
     */
    public void setDataLeitorSerial(String dataLeitorSerial) {
        this.dataLeitorSerial = dataLeitorSerial;
    }

    /**
     * @return the paridadeLeitorSerial
     */
    public Integer getParidadeLeitorSerial() {
        return paridadeLeitorSerial;
    }

    /**
     * @param paridadeLeitorSerial the paridadeLeitorSerial to set
     */
    public void setParidadeLeitorSerial(Integer paridadeLeitorSerial) {
        this.paridadeLeitorSerial = paridadeLeitorSerial;
    }

    /**
     * @return the hardFlowLeitorSerial
     */
    public Integer getHardFlowLeitorSerial() {
        return hardFlowLeitorSerial;
    }

    /**
     * @param hardFlowLeitorSerial the hardFlowLeitorSerial to set
     */
    public void setHardFlowLeitorSerial(Integer hardFlowLeitorSerial) {
        this.hardFlowLeitorSerial = hardFlowLeitorSerial;
    }

    /**
     * @return the softFlowLeitorSerial
     */
    public Integer getSoftFlowLeitorSerial() {
        return softFlowLeitorSerial;
    }

    /**
     * @param softFlowLeitorSerial the softFlowLeitorSerial to set
     */
    public void setSoftFlowLeitorSerial(Integer softFlowLeitorSerial) {
        this.softFlowLeitorSerial = softFlowLeitorSerial;
    }

    /**
     * @return the handShakeLeitorSerial
     */
    public Integer getHandShakeLeitorSerial() {
        return handShakeLeitorSerial;
    }

    /**
     * @param handShakeLeitorSerial the handShakeLeitorSerial to set
     */
    public void setHandShakeLeitorSerial(Integer handShakeLeitorSerial) {
        this.handShakeLeitorSerial = handShakeLeitorSerial;
    }

    /**
     * @return the stopLeitorSerial
     */
    public Integer getStopLeitorSerial() {
        return stopLeitorSerial;
    }

    /**
     * @param stopLeitorSerial the stopLeitorSerial to set
     */
    public void setStopLeitorSerial(Integer stopLeitorSerial) {
        this.stopLeitorSerial = stopLeitorSerial;
    }

    /**
     * @return the filaLeitorSerial
     */
    public Integer getFilaLeitorSerial() {
        return filaLeitorSerial;
    }

    /**
     * @param filaLeitorSerial the filaLeitorSerial to set
     */
    public void setFilaLeitorSerial(Integer filaLeitorSerial) {
        this.filaLeitorSerial = filaLeitorSerial;
    }

    /**
     * @return the excluiSufixoLeitorSerial
     */
    public Integer getExcluiSufixoLeitorSerial() {
        return excluiSufixoLeitorSerial;
    }

    /**
     * @param excluiSufixoLeitorSerial the excluiSufixoLeitorSerial to set
     */
    public void setExcluiSufixoLeitorSerial(Integer excluiSufixoLeitorSerial) {
        this.excluiSufixoLeitorSerial = excluiSufixoLeitorSerial;
    }

    /**
     * @return the lancamentoNotasManuais
     */
    public Integer getLancamentoNotasManuais() {
        return lancamentoNotasManuais;
    }

    /**
     * @param lancamentoNotasManuais the lancamentoNotasManuais to set
     */
    public void setLancamentoNotasManuais(Integer lancamentoNotasManuais) {
        this.lancamentoNotasManuais = lancamentoNotasManuais;
    }

    /**
     * @return the idResolucao
     */
    public Integer getIdResolucao() {
        return idResolucao;
    }

    /**
     * @param idResolucao the idResolucao to set
     */
    public void setIdResolucao(Integer idResolucao) {
        this.idResolucao = idResolucao;
    }

    /**
     * @return the idImpressora
     */
    public Integer getIdImpressora() {
        return idImpressora;
    }

    /**
     * @param idImpressora the idImpressora to set
     */
    public void setIdImpressora(Integer idImpressora) {
        this.idImpressora = idImpressora;
    }

    /**
     * @return the modeloImpressora
     */
    public String getModeloImpressora() {
        return modeloImpressora;
    }

    /**
     * @param modeloImpressora the modeloImpressora to set
     */
    public void setModeloImpressora(String modeloImpressora) {
        this.modeloImpressora = modeloImpressora;
    }

    /**
     * @return the numSerieEcf
     */
    public String getNumSerieEcf() {
        return numSerieEcf;
    }

    /**
     * @param numSerieEcf the numSerieEcf to set
     */
    public void setNumSerieEcf(String numSerieEcf) {
        this.numSerieEcf = numSerieEcf;
    }

}