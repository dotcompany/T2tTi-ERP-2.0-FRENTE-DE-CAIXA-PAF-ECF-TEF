/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela NOTA_FISCAL_DETALHE</p>
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

public class NotaFiscalDetalheVO {

    private Integer id;
    private Integer idNotaCabecalho;
    private Integer idProduto;
    private Integer cfop;
    private Integer item;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    private Double baseIcms;
    private Double taxaIcms;
    private Double icms;
    private Double icmsOutras;
    private Double icmsIsento;
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
    private Double taxaIpi;
    private Double ipi;
    private String cancelado;
    private String movimentaEstoque;
    private String sincronizado;
    private String cst;
    private String ecfIcmsSt;
    private String gtinProduto;
    private String descricaoProduto;

    private String descricaoUnidade;
    private String totalizadorParcial;
    
    public NotaFiscalDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getBaseIcms() {
        return baseIcms;
    }

    public void setBaseIcms(Double baseIcms) {
        this.baseIcms = baseIcms;
    }

    public Double getTaxaIcms() {
        return taxaIcms;
    }

    public void setTaxaIcms(Double taxaIcms) {
        this.taxaIcms = taxaIcms;
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    public Double getIcmsOutras() {
        return icmsOutras;
    }

    public void setIcmsOutras(Double icmsOutras) {
        this.icmsOutras = icmsOutras;
    }

    public Double getIcmsIsento() {
        return icmsIsento;
    }

    public void setIcmsIsento(Double icmsIsento) {
        this.icmsIsento = icmsIsento;
    }

    public Double getTaxaDesconto() {
        return taxaDesconto;
    }

    public void setTaxaDesconto(Double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTaxaIssqn() {
        return taxaIssqn;
    }

    public void setTaxaIssqn(Double taxaIssqn) {
        this.taxaIssqn = taxaIssqn;
    }

    public Double getIssqn() {
        return issqn;
    }

    public void setIssqn(Double issqn) {
        this.issqn = issqn;
    }

    public Double getTaxaPis() {
        return taxaPis;
    }

    public void setTaxaPis(Double taxaPis) {
        this.taxaPis = taxaPis;
    }

    public Double getPis() {
        return pis;
    }

    public void setPis(Double pis) {
        this.pis = pis;
    }

    public Double getTaxaCofins() {
        return taxaCofins;
    }

    public void setTaxaCofins(Double taxaCofins) {
        this.taxaCofins = taxaCofins;
    }

    public Double getCofins() {
        return cofins;
    }

    public void setCofins(Double cofins) {
        this.cofins = cofins;
    }

    public Double getTaxaAcrescimo() {
        return taxaAcrescimo;
    }

    public void setTaxaAcrescimo(Double taxaAcrescimo) {
        this.taxaAcrescimo = taxaAcrescimo;
    }

    public Double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public Double getTaxaIpi() {
        return taxaIpi;
    }

    public void setTaxaIpi(Double taxaIpi) {
        this.taxaIpi = taxaIpi;
    }

    public Double getIpi() {
        return ipi;
    }

    public void setIpi(Double ipi) {
        this.ipi = ipi;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getMovimentaEstoque() {
        return movimentaEstoque;
    }

    public void setMovimentaEstoque(String movimentaEstoque) {
        this.movimentaEstoque = movimentaEstoque;
    }

    public String getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getEcfIcmsSt() {
        return ecfIcmsSt;
    }

    public void setEcfIcmsSt(String ecfIcmsSt) {
        this.ecfIcmsSt = ecfIcmsSt;
    }

    /**
     * @return the idNotaCabecalho
     */
    public Integer getIdNotaCabecalho() {
        return idNotaCabecalho;
    }

    /**
     * @param idNotaCabecalho the idNotaCabecalho to set
     */
    public void setIdNotaCabecalho(Integer idNotaCabecalho) {
        this.idNotaCabecalho = idNotaCabecalho;
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
     * @return the gtinProduto
     */
    public String getGtinProduto() {
        return gtinProduto;
    }

    /**
     * @param gtinProduto the gtinProduto to set
     */
    public void setGtinProduto(String gtinProduto) {
        this.gtinProduto = gtinProduto;
    }

    /**
     * @return the descricaoProduto
     */
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    /**
     * @param descricaoProduto the descricaoProduto to set
     */
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * @return the descricaoUnidade
     */
    public String getDescricaoUnidade() {
        return descricaoUnidade;
    }

    /**
     * @param descricaoUnidade the descricaoUnidade to set
     */
    public void setDescricaoUnidade(String descricaoUnidade) {
        this.descricaoUnidade = descricaoUnidade;
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

}
