/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_VENDA_CABECALHO</p>
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

public class VendaCabecalhoVO {

    private Integer id;
    private Integer idDav;
    private Integer idPreVenda;
    private Integer idCliente;
    private Integer idVendedor;
    private Integer idMovimento;
    private String serieEcf;
    private Integer cfop;
    private Integer coo;
    private Integer ccf;
    private Date dataVenda;
    private String horaVenda;
    private Double valorVenda;
    private Double taxaDesconto;
    private Double desconto;
    private Double taxaAcrescimo;
    private Double acrescimo;
    private Double valorFinal;
    private Double valorRecebido;
    private Double troco;
    private Double valorCancelado;
    private String sincronizado;
    private Double totalProdutos;
    private Double totalDocumento;
    private Double baseIcms;
    private Double icms;
    private Double icmsOutras;
    private Double issqn;
    private Double pis;
    private Double cofins;
    private Double acrescimoItens;
    private Double descontoItens;
    private String statusVenda;
    private String nomeCliente;
    private String cpfCnpjCliente;
    private String cupomCancelado;
    private String hashTripa;
    private Integer hashIncremento;

    public VendaCabecalhoVO() {
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
     * @return the idDav
     */
    public Integer getIdDav() {
        return idDav;
    }

    /**
     * @param idDav the idDav to set
     */
    public void setIdDav(Integer idDav) {
        this.idDav = idDav;
    }

    /**
     * @return the idPreVenda
     */
    public Integer getIdPreVenda() {
        return idPreVenda;
    }

    /**
     * @param idPreVenda the idPreVenda to set
     */
    public void setIdPreVenda(Integer idPreVenda) {
        this.idPreVenda = idPreVenda;
    }

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idVendedor
     */
    public Integer getIdVendedor() {
        return idVendedor;
    }

    /**
     * @param idVendedor the idVendedor to set
     */
    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    /**
     * @return the idMovimento
     */
    public Integer getIdMovimento() {
        return idMovimento;
    }

    /**
     * @param idMovimento the idMovimento to set
     */
    public void setIdMovimento(Integer idMovimento) {
        this.idMovimento = idMovimento;
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
     * @return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param dataVenda the dataVenda to set
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * @return the horaVenda
     */
    public String getHoraVenda() {
        return horaVenda;
    }

    /**
     * @param horaVenda the horaVenda to set
     */
    public void setHoraVenda(String horaVenda) {
        this.horaVenda = horaVenda;
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
     * @return the valorFinal
     */
    public Double getValorFinal() {
        return valorFinal;
    }

    /**
     * @param valorFinal the valorFinal to set
     */
    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    /**
     * @return the valorRecebido
     */
    public Double getValorRecebido() {
        return valorRecebido;
    }

    /**
     * @param valorRecebido the valorRecebido to set
     */
    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    /**
     * @return the troco
     */
    public Double getTroco() {
        return troco;
    }

    /**
     * @param troco the troco to set
     */
    public void setTroco(Double troco) {
        this.troco = troco;
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
     * @return the totalProdutos
     */
    public Double getTotalProdutos() {
        return totalProdutos;
    }

    /**
     * @param totalProdutos the totalProdutos to set
     */
    public void setTotalProdutos(Double totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    /**
     * @return the totalDocumento
     */
    public Double getTotalDocumento() {
        return totalDocumento;
    }

    /**
     * @param totalDocumento the totalDocumento to set
     */
    public void setTotalDocumento(Double totalDocumento) {
        this.totalDocumento = totalDocumento;
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
     * @return the icmsOutras
     */
    public Double getIcmsOutras() {
        return icmsOutras;
    }

    /**
     * @param icmsOutras the icmsOutras to set
     */
    public void setIcmsOutras(Double icmsOutras) {
        this.icmsOutras = icmsOutras;
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

    /**
     * @return the acrescimoItens
     */
    public Double getAcrescimoItens() {
        return acrescimoItens;
    }

    /**
     * @param acrescimoItens the acrescimoItens to set
     */
    public void setAcrescimoItens(Double acrescimoItens) {
        this.acrescimoItens = acrescimoItens;
    }

    /**
     * @return the descontoItens
     */
    public Double getDescontoItens() {
        return descontoItens;
    }

    /**
     * @param descontoItens the descontoItens to set
     */
    public void setDescontoItens(Double descontoItens) {
        this.descontoItens = descontoItens;
    }

    /**
     * @return the statusVenda
     */
    public String getStatusVenda() {
        return statusVenda;
    }

    /**
     * @param statusVenda the statusVenda to set
     */
    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @return the cpfCnpjCliente
     */
    public String getCpfCnpjCliente() {
        return cpfCnpjCliente;
    }

    /**
     * @param cpfCnpjCliente the cpfCnpjCliente to set
     */
    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        this.cpfCnpjCliente = cpfCnpjCliente;
    }

    /**
     * @return the cupomCancelado
     */
    public String getCupomCancelado() {
        return cupomCancelado;
    }

    /**
     * @param cupomCancelado the cupomCancelado to set
     */
    public void setCupomCancelado(String cupomCancelado) {
        this.cupomCancelado = cupomCancelado;
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

}
