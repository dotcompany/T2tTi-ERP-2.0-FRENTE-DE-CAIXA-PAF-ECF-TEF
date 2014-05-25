/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela PRODUTO</p>
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

import java.util.Date;

public class ProdutoVO {

    private Integer id;
    private Integer idUnidade;
    private String gtin;
    private String codigoInterno;
    private String nome;
    private String descricao;
    private String descricaoPDV;
    private Double valorVenda;
    private Double quantidadeEstoque;
    private Double quantidadeEstoqueAnterior;
    private Double estoqueMinimo;
    private Double estoqueMaximo;
    private String iat;
    private String ippt;
    private String ncm;
    private String tipoItemSped;
    private Date dataEstoque;
    private Double taxaIpi;
    private Double taxaIssqn;
    private Double taxaPis;
    private Double taxaCofins;
    private Double taxaIcms;
    private String cst;
    private String csosn;
    private String totalizadorParcial;
    private String ecfIcmsSt;
    private Integer codigoBalanca;
    private String pafProdutoSt;
    private String hashTripa;
    private Integer hashIncremento;
    
    private UnidadeProdutoVO unidadeProdutoVO;

    public ProdutoVO() {
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
     * @return the codigoInterno
     */
    public String getCodigoInterno() {
        return codigoInterno;
    }

    /**
     * @param codigoInterno the codigoInterno to set
     */
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the descricaoPDV
     */
    public String getDescricaoPDV() {
        return descricaoPDV;
    }

    /**
     * @param descricaoPDV the descricaoPDV to set
     */
    public void setDescricaoPDV(String descricaoPDV) {
        this.descricaoPDV = descricaoPDV;
    }

    /**
     * @return the valorVenda
     */
    public Double getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    /**
     * @return the quantidadeEstoque
     */
    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /**
     * @param quantidadeEstoque the quantidadeEstoque to set
     */
    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    /**
     * @return the quantidadeEstoqueAnterior
     */
    public Double getQuantidadeEstoqueAnterior() {
        return quantidadeEstoqueAnterior;
    }

    /**
     * @param quantidadeEstoqueAnterior the quantidadeEstoqueAnterior to set
     */
    public void setQuantidadeEstoqueAnterior(Double quantidadeEstoqueAnterior) {
        this.quantidadeEstoqueAnterior = quantidadeEstoqueAnterior;
    }

    /**
     * @return the estoqueMinimo
     */
    public Double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    /**
     * @param estoqueMinimo the estoqueMinimo to set
     */
    public void setEstoqueMinimo(Double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    /**
     * @return the estoqueMaximo
     */
    public Double getEstoqueMaximo() {
        return estoqueMaximo;
    }

    /**
     * @param estoqueMaximo the estoqueMaximo to set
     */
    public void setEstoqueMaximo(Double estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
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
     * @return the ncm
     */
    public String getNcm() {
        return ncm;
    }

    /**
     * @param ncm the ncm to set
     */
    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    /**
     * @return the tipoItemSped
     */
    public String getTipoItemSped() {
        return tipoItemSped;
    }

    /**
     * @param tipoItemSped the tipoItemSped to set
     */
    public void setTipoItemSped(String tipoItemSped) {
        this.tipoItemSped = tipoItemSped;
    }

    /**
     * @return the dataEstoque
     */
    public Date getDataEstoque() {
        return dataEstoque;
    }

    /**
     * @param dataEstoque the dataEstoque to set
     */
    public void setDataEstoque(Date dataEstoque) {
        this.dataEstoque = dataEstoque;
    }

    /**
     * @return the taxaIpi
     */
    public Double getTaxaIpi() {
        return taxaIpi;
    }

    /**
     * @param taxaIpi the taxaIpi to set
     */
    public void setTaxaIpi(Double taxaIpi) {
        this.taxaIpi = taxaIpi;
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
     * @return the csosn
     */
    public String getCsosn() {
        return csosn;
    }

    /**
     * @param csosn the csosn to set
     */
    public void setCsosn(String csosn) {
        this.csosn = csosn;
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
     * @return the codigoBalanca
     */
    public Integer getCodigoBalanca() {
        return codigoBalanca;
    }

    /**
     * @param codigoBalanca the codigoBalanca to set
     */
    public void setCodigoBalanca(Integer codigoBalanca) {
        this.codigoBalanca = codigoBalanca;
    }

    /**
     * @return the pafProdutoSt
     */
    public String getPafProdutoSt() {
        return pafProdutoSt;
    }

    /**
     * @param pafProdutoSt the pafProdutoSt to set
     */
    public void setPafProdutoSt(String pafProdutoSt) {
        this.pafProdutoSt = pafProdutoSt;
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
     * @return the unidadeProdutoVO
     */
    public UnidadeProdutoVO getUnidadeProdutoVO() {
        return unidadeProdutoVO;
    }

    /**
     * @param unidadeProdutoVO the unidadeProdutoVO to set
     */
    public void setUnidadeProdutoVO(UnidadeProdutoVO unidadeProdutoVO) {
        this.unidadeProdutoVO = unidadeProdutoVO;
    }

}