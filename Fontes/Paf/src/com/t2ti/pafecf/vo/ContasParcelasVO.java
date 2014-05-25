/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela CONTAS_PARCELAS</p>
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

public class ContasParcelasVO {

    private Integer id;
    private Integer idContasPagarReceber;
    private Integer idMeiosPagamento;
    private Integer idChequeEmitido;
    private Integer idContaCaixa;
    private Date dataEmissao;
    private Date dataVencimento;
    private Date dataPagamento;
    private Integer numeroParcela;
    private Double valor;
    private Double taxaJuros;
    private Double taxaMulta;
    private Double taxaDesconto;
    private Double valorJuros;
    private Double valorMulta;
    private Double valorDesconto;
    private Double totalParcela;
    private String historico;
    private String situacao;

    public ContasParcelasVO() {
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
     * @return the idContasPagarReceber
     */
    public Integer getIdContasPagarReceber() {
        return idContasPagarReceber;
    }

    /**
     * @param idContasPagarReceber the idContasPagarReceber to set
     */
    public void setIdContasPagarReceber(Integer idContasPagarReceber) {
        this.idContasPagarReceber = idContasPagarReceber;
    }

    /**
     * @return the idMeiosPagamento
     */
    public Integer getIdMeiosPagamento() {
        return idMeiosPagamento;
    }

    /**
     * @param idMeiosPagamento the idMeiosPagamento to set
     */
    public void setIdMeiosPagamento(Integer idMeiosPagamento) {
        this.idMeiosPagamento = idMeiosPagamento;
    }

    /**
     * @return the idChequeEmitido
     */
    public Integer getIdChequeEmitido() {
        return idChequeEmitido;
    }

    /**
     * @param idChequeEmitido the idChequeEmitido to set
     */
    public void setIdChequeEmitido(Integer idChequeEmitido) {
        this.idChequeEmitido = idChequeEmitido;
    }

    /**
     * @return the idContaCaixa
     */
    public Integer getIdContaCaixa() {
        return idContaCaixa;
    }

    /**
     * @param idContaCaixa the idContaCaixa to set
     */
    public void setIdContaCaixa(Integer idContaCaixa) {
        this.idContaCaixa = idContaCaixa;
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
     * @return the dataVencimento
     */
    public Date getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return the dataPagamento
     */
    public Date getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return the numeroParcela
     */
    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    /**
     * @param numeroParcela the numeroParcela to set
     */
    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the taxaJuros
     */
    public Double getTaxaJuros() {
        return taxaJuros;
    }

    /**
     * @param taxaJuros the taxaJuros to set
     */
    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    /**
     * @return the taxaMulta
     */
    public Double getTaxaMulta() {
        return taxaMulta;
    }

    /**
     * @param taxaMulta the taxaMulta to set
     */
    public void setTaxaMulta(Double taxaMulta) {
        this.taxaMulta = taxaMulta;
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
     * @return the valorJuros
     */
    public Double getValorJuros() {
        return valorJuros;
    }

    /**
     * @param valorJuros the valorJuros to set
     */
    public void setValorJuros(Double valorJuros) {
        this.valorJuros = valorJuros;
    }

    /**
     * @return the valorMulta
     */
    public Double getValorMulta() {
        return valorMulta;
    }

    /**
     * @param valorMulta the valorMulta to set
     */
    public void setValorMulta(Double valorMulta) {
        this.valorMulta = valorMulta;
    }

    /**
     * @return the valorDesconto
     */
    public Double getValorDesconto() {
        return valorDesconto;
    }

    /**
     * @param valorDesconto the valorDesconto to set
     */
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return the totalParcela
     */
    public Double getTotalParcela() {
        return totalParcela;
    }

    /**
     * @param totalParcela the totalParcela to set
     */
    public void setTotalParcela(Double totalParcela) {
        this.totalParcela = totalParcela;
    }

    /**
     * @return the historico
     */
    public String getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void setHistorico(String historico) {
        this.historico = historico;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


}