/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO transiente. Montará os dados necessários para o registro R05.</p>
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
package com.t2ti.pafecf.vo;

public class R05VO  {

    private Integer id;
    private Integer coo;
    private Integer ccf;
    private Integer item;
    private String serieEcf;
    private String gtin;
    private String descricaoPdv;
    private Double quantidade;
    private String siglaUnidade;
    private Double valorUnitario;
    private Double desconto;
    private Double acrescimo;
    private Double totalItem;
    private String totalizadorParcial;
    private String indicadorCancelamento;
    private Double quantidadeCancelada;
    private Double valorCancelado;
    private Double cancelamentoAcrescimo;
    private String iat;
    private String ippt;
    private Integer casasDecimaisQuantidade;
    private Integer casasDecimaisValor;
    private String hashTripa;
    private Integer hashIncremento;

    //Utilizados pelo Sped Fiscal
    private Integer idProduto;
    private Integer idUnidade;
    private String cst;
    private Integer cfop;
    private Double aliquotaICMS;
    private Double pis;
    private Double cofins;

    public R05VO() {
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
     * @return the coo
     */
    public Integer getCoo() {
        return coo;
    }

    /**
     * @param coo the coo to set
     */
    public void setCoo(Integer coo) {
        this.coo = coo;
    }

    /**
     * @return the ccf
     */
    public Integer getCcf() {
        return ccf;
    }

    /**
     * @param ccf the ccf to set
     */
    public void setCcf(Integer ccf) {
        this.ccf = ccf;
    }

    /**
     * @return the item
     */
    public Integer getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Integer item) {
        this.item = item;
    }

    /**
     * @return the serieEcf
     */
    public String getSerieEcf() {
        return serieEcf;
    }

    /**
     * @param serieEcf the serieEcf to set
     */
    public void setSerieEcf(String serieEcf) {
        this.serieEcf = serieEcf;
    }

    /**
     * @return the gtin
     */
    public String getGtin() {
        return gtin;
    }

    /**
     * @param gtin the gtin to set
     */
    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    /**
     * @return the descricaoPdv
     */
    public String getDescricaoPdv() {
        return descricaoPdv;
    }

    /**
     * @param descricaoPdv the descricaoPdv to set
     */
    public void setDescricaoPdv(String descricaoPdv) {
        this.descricaoPdv = descricaoPdv;
    }

    /**
     * @return the quantidade
     */
    public Double getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the siglaUnidade
     */
    public String getSiglaUnidade() {
        return siglaUnidade;
    }

    /**
     * @param siglaUnidade the siglaUnidade to set
     */
    public void setSiglaUnidade(String siglaUnidade) {
        this.siglaUnidade = siglaUnidade;
    }

    /**
     * @return the valorUnitario
     */
    public Double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the desconto
     */
    public Double getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    /**
     * @return the acrescimo
     */
    public Double getAcrescimo() {
        return acrescimo;
    }

    /**
     * @param acrescimo the acrescimo to set
     */
    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }

    /**
     * @return the totalItem
     */
    public Double getTotalItem() {
        return totalItem;
    }

    /**
     * @param totalItem the totalItem to set
     */
    public void setTotalItem(Double totalItem) {
        this.totalItem = totalItem;
    }

    /**
     * @return the totalizadorParcial
     */
    public String getTotalizadorParcial() {
        return totalizadorParcial;
    }

    /**
     * @param totalizadorParcial the totalizadorParcial to set
     */
    public void setTotalizadorParcial(String totalizadorParcial) {
        this.totalizadorParcial = totalizadorParcial;
    }

    /**
     * @return the indicadorCancelamento
     */
    public String getIndicadorCancelamento() {
        return indicadorCancelamento;
    }

    /**
     * @param indicadorCancelamento the indicadorCancelamento to set
     */
    public void setIndicadorCancelamento(String indicadorCancelamento) {
        this.indicadorCancelamento = indicadorCancelamento;
    }

    /**
     * @return the quantidadeCancelada
     */
    public Double getQuantidadeCancelada() {
        return quantidadeCancelada;
    }

    /**
     * @param quantidadeCancelada the quantidadeCancelada to set
     */
    public void setQuantidadeCancelada(Double quantidadeCancelada) {
        this.quantidadeCancelada = quantidadeCancelada;
    }

    /**
     * @return the valorCancelado
     */
    public Double getValorCancelado() {
        return valorCancelado;
    }

    /**
     * @param valorCancelado the valorCancelado to set
     */
    public void setValorCancelado(Double valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    /**
     * @return the cancelamentoAcrescimo
     */
    public Double getCancelamentoAcrescimo() {
        return cancelamentoAcrescimo;
    }

    /**
     * @param cancelamentoAcrescimo the cancelamentoAcrescimo to set
     */
    public void setCancelamentoAcrescimo(Double cancelamentoAcrescimo) {
        this.cancelamentoAcrescimo = cancelamentoAcrescimo;
    }

    /**
     * @return the iat
     */
    public String getIat() {
        return iat;
    }

    /**
     * @param iat the iat to set
     */
    public void setIat(String iat) {
        this.iat = iat;
    }

    /**
     * @return the ippt
     */
    public String getIppt() {
        return ippt;
    }

    /**
     * @param ippt the ippt to set
     */
    public void setIppt(String ippt) {
        this.ippt = ippt;
    }

    /**
     * @return the casasDecimaisQuantidade
     */
    public Integer getCasasDecimaisQuantidade() {
        return casasDecimaisQuantidade;
    }

    /**
     * @param casasDecimaisQuantidade the casasDecimaisQuantidade to set
     */
    public void setCasasDecimaisQuantidade(Integer casasDecimaisQuantidade) {
        this.casasDecimaisQuantidade = casasDecimaisQuantidade;
    }

    /**
     * @return the casasDecimaisValor
     */
    public Integer getCasasDecimaisValor() {
        return casasDecimaisValor;
    }

    /**
     * @param casasDecimaisValor the casasDecimaisValor to set
     */
    public void setCasasDecimaisValor(Integer casasDecimaisValor) {
        this.casasDecimaisValor = casasDecimaisValor;
    }

    /**
     * @return the hashTripa
     */
    public String getHashTripa() {
        return hashTripa;
    }

    /**
     * @param hashTripa the hashTripa to set
     */
    public void setHashTripa(String hashTripa) {
        this.hashTripa = hashTripa;
    }

    /**
     * @return the hashIncremento
     */
    public Integer getHashIncremento() {
        return hashIncremento;
    }

    /**
     * @param hashIncremento the hashIncremento to set
     */
    public void setHashIncremento(Integer hashIncremento) {
        this.hashIncremento = hashIncremento;
    }

    /**
     * @return the idProduto
     */
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the cst
     */
    public String getCst() {
        return cst;
    }

    /**
     * @param cst the cst to set
     */
    public void setCst(String cst) {
        this.cst = cst;
    }

    /**
     * @return the cfop
     */
    public Integer getCfop() {
        return cfop;
    }

    /**
     * @param cfop the cfop to set
     */
    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    /**
     * @return the aliquotaICMS
     */
    public Double getAliquotaICMS() {
        return aliquotaICMS;
    }

    /**
     * @param aliquotaICMS the aliquotaICMS to set
     */
    public void setAliquotaICMS(Double aliquotaICMS) {
        this.aliquotaICMS = aliquotaICMS;
    }

    /**
     * @return the pis
     */
    public Double getPis() {
        return pis;
    }

    /**
     * @param pis the pis to set
     */
    public void setPis(Double pis) {
        this.pis = pis;
    }

    /**
     * @return the cofins
     */
    public Double getCofins() {
        return cofins;
    }

    /**
     * @param cofins the cofins to set
     */
    public void setCofins(Double cofins) {
        this.cofins = cofins;
    }

}