/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela NOTA_FISCAL_CABECALHO</p>
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

public class NotaFiscalCabecalhoVO {

    private Integer id;
    private Integer idFuncionario;
    private Integer idCliente;
    private Integer cfop;
    private String numero;
    private Date dataEmissao;
    private String horaEmissao;
    private String serie;
    private String subserie;
    private Double totalProdutos;
    private Double totalNf;
    private Double baseIcms;
    private Double icms;
    private Double icmsOutras;
    private Double issqn;
    private Double pis;
    private Double cofins;
    private Double ipi;
    private Double taxaAcrescimo;
    private Double acrescimo;
    private Double acrescimoItens;
    private Double taxaDesconto;
    private Double desconto;
    private Double descontoItens;
    private String cancelada;
    private String sincronizado;
    private String tipoNota;

    private String cpfCnpjCliente;
    private Integer numOrdemInicial;
    private Integer numOrdemFinal;
    
    public NotaFiscalCabecalhoVO() {
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
     * @return the idFuncionario
     */
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * @param idFuncionario the idFuncionario to set
     */
    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
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
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the horaEmissao
     */
    public String getHoraEmissao() {
        return horaEmissao;
    }

    /**
     * @param horaEmissao the horaEmissao to set
     */
    public void setHoraEmissao(String horaEmissao) {
        this.horaEmissao = horaEmissao;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the subserie
     */
    public String getSubserie() {
        return subserie;
    }

    /**
     * @param subserie the subserie to set
     */
    public void setSubserie(String subserie) {
        this.subserie = subserie;
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
     * @return the totalNf
     */
    public Double getTotalNf() {
        return totalNf;
    }

    /**
     * @param totalNf the totalNf to set
     */
    public void setTotalNf(Double totalNf) {
        this.totalNf = totalNf;
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
     * @return the ipi
     */
    public Double getIpi() {
        return ipi;
    }

    /**
     * @param ipi the ipi to set
     */
    public void setIpi(Double ipi) {
        this.ipi = ipi;
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
     * @return the cancelada
     */
    public String getCancelada() {
        return cancelada;
    }

    /**
     * @param cancelada the cancelada to set
     */
    public void setCancelada(String cancelada) {
        this.cancelada = cancelada;
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
     * @return the tipoNota
     */
    public String getTipoNota() {
        return tipoNota;
    }

    /**
     * @param tipoNota the tipoNota to set
     */
    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    /**
     * @return the numOrdemInicial
     */
    public Integer getNumOrdemInicial() {
        return numOrdemInicial;
    }

    /**
     * @param numOrdemInicial the numOrdemInicial to set
     */
    public void setNumOrdemInicial(Integer numOrdemInicial) {
        this.numOrdemInicial = numOrdemInicial;
    }

    /**
     * @return the numOrdemFinal
     */
    public Integer getNumOrdemFinal() {
        return numOrdemFinal;
    }

    /**
     * @param numOrdemFinal the numOrdemFinal to set
     */
    public void setNumOrdemFinal(Integer numOrdemFinal) {
        this.numOrdemFinal = numOrdemFinal;
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

}
