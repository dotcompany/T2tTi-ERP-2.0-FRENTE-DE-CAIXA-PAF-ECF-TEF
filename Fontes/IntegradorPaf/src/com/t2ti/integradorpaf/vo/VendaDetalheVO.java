/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_VENDA_DETALHE</p>
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
package com.t2ti.integradorpaf.vo;

public class VendaDetalheVO {

    private Integer id;
    private Integer idProduto;
    private Integer idVendaCabecalho;
    private Integer cfop;
    private String gtin;
    private Integer ccf;
    private Integer coo;
    private String serieEcf;
    private Integer item;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    private Double totalItem;
    private Double baseIcms;
    private Double taxaIcms;
    private Double icms;
    private Double taxaDesconto;
    private Double desconto;
    private Double taxaIssqn;
    private Double issqn;
    private Double taxaPis;
    private Double pis;
    private Double taxaCofins;
    private Double cofins;
    private Double taxaAcrescimo;
    private Double acrescimo;
    private double acrescimoRateio;
    private double descontoRateio;
    private String totalizadorParcial;
    private String cst;
    private String cancelado;
    private String movimentaEstoque;
    private String ecfIcmsSt;
    private String hashTripa;
    private Integer hashIncremento;

    private String unidadeProduto;
    private String descricaoPdv;
    private String identificacaoCliente;

    public VendaDetalheVO() {
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
     * @return the idVendaCabecalho
     */
    public Integer getIdVendaCabecalho() {
        return idVendaCabecalho;
    }

    /**
     * @param idVendaCabecalho the idVendaCabecalho to set
     */
    public void setIdVendaCabecalho(Integer idVendaCabecalho) {
        this.idVendaCabecalho = idVendaCabecalho;
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
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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
     * @return the baseIcms
     */
    public Double getBaseIcms() {
        return baseIcms;
    }

    /**
     * @param baseIcms the baseIcms to set
     */
    public void setBaseIcms(Double baseIcms) {
        this.baseIcms = baseIcms;
    }

    /**
     * @return the taxaIcms
     */
    public Double getTaxaIcms() {
        return taxaIcms;
    }

    /**
     * @param taxaIcms the taxaIcms to set
     */
    public void setTaxaIcms(Double taxaIcms) {
        this.taxaIcms = taxaIcms;
    }

    /**
     * @return the icms
     */
    public Double getIcms() {
        return icms;
    }

    /**
     * @param icms the icms to set
     */
    public void setIcms(Double icms) {
        this.icms = icms;
    }

    /**
     * @return the taxaDesconto
     */
    public Double getTaxaDesconto() {
        return taxaDesconto;
    }

    /**
     * @param taxaDesconto the taxaDesconto to set
     */
    public void setTaxaDesconto(Double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
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
     * @return the taxaIssqn
     */
    public Double getTaxaIssqn() {
        return taxaIssqn;
    }

    /**
     * @param taxaIssqn the taxaIssqn to set
     */
    public void setTaxaIssqn(Double taxaIssqn) {
        this.taxaIssqn = taxaIssqn;
    }

    /**
     * @return the issqn
     */
    public Double getIssqn() {
        return issqn;
    }

    /**
     * @param issqn the issqn to set
     */
    public void setIssqn(Double issqn) {
        this.issqn = issqn;
    }

    /**
     * @return the taxaPis
     */
    public Double getTaxaPis() {
        return taxaPis;
    }

    /**
     * @param taxaPis the taxaPis to set
     */
    public void setTaxaPis(Double taxaPis) {
        this.taxaPis = taxaPis;
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
     * @return the taxaCofins
     */
    public Double getTaxaCofins() {
        return taxaCofins;
    }

    /**
     * @param taxaCofins the taxaCofins to set
     */
    public void setTaxaCofins(Double taxaCofins) {
        this.taxaCofins = taxaCofins;
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

    /**
     * @return the taxaAcrescimo
     */
    public Double getTaxaAcrescimo() {
        return taxaAcrescimo;
    }

    /**
     * @param taxaAcrescimo the taxaAcrescimo to set
     */
    public void setTaxaAcrescimo(Double taxaAcrescimo) {
        this.taxaAcrescimo = taxaAcrescimo;
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
     * @return the acrescimoRateio
     */
    public double getAcrescimoRateio() {
        return acrescimoRateio;
    }

    /**
     * @param acrescimoRateio the acrescimoRateio to set
     */
    public void setAcrescimoRateio(double acrescimoRateio) {
        this.acrescimoRateio = acrescimoRateio;
    }

    /**
     * @return the descontoRateio
     */
    public double getDescontoRateio() {
        return descontoRateio;
    }

    /**
     * @param descontoRateio the descontoRateio to set
     */
    public void setDescontoRateio(double descontoRateio) {
        this.descontoRateio = descontoRateio;
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
     * @return the cancelado
     */
    public String getCancelado() {
        return cancelado;
    }

    /**
     * @param cancelado the cancelado to set
     */
    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    /**
     * @return the movimentaEstoque
     */
    public String getMovimentaEstoque() {
        return movimentaEstoque;
    }

    /**
     * @param movimentaEstoque the movimentaEstoque to set
     */
    public void setMovimentaEstoque(String movimentaEstoque) {
        this.movimentaEstoque = movimentaEstoque;
    }

    /**
     * @return the ecfIcmsSt
     */
    public String getEcfIcmsSt() {
        return ecfIcmsSt;
    }

    /**
     * @param ecfIcmsSt the ecfIcmsSt to set
     */
    public void setEcfIcmsSt(String ecfIcmsSt) {
        this.ecfIcmsSt = ecfIcmsSt;
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
     * @return the unidadeProduto
     */
    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    /**
     * @param unidadeProduto the unidadeProduto to set
     */
    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
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
     * @return the identificacaoCliente
     */
    public String getIdentificacaoCliente() {
        return identificacaoCliente;
    }

    /**
     * @param identificacaoCliente the identificacaoCliente to set
     */
    public void setIdentificacaoCliente(String identificacaoCliente) {
        this.identificacaoCliente = identificacaoCliente;
    }

}