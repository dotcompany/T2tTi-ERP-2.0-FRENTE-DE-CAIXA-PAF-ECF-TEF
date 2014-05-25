/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela CONTAS_PAGAR_RECEBER</p>
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

public class ContasPagarReceberVO {

    private Integer id;
    private Integer idEcfVendaCabecalho;
    private Integer idPlanoContas;
    private Integer idTipoDocumento;
    private Integer idPessoa;
    private String tipo;
    private String numeroDocumento;
    private Double valor;
    private Date dataLancamento;
    private Date primeiroVencimento;
    private String naturezaLancamento;
    private Integer quantidadeParcela;
    

    public ContasPagarReceberVO() {
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
     * @return the idEcfVendaCabecalho
     */
    public Integer getIdEcfVendaCabecalho() {
        return idEcfVendaCabecalho;
    }

    /**
     * @param idEcfVendaCabecalho the idEcfVendaCabecalho to set
     */
    public void setIdEcfVendaCabecalho(Integer idEcfVendaCabecalho) {
        this.idEcfVendaCabecalho = idEcfVendaCabecalho;
    }

    /**
     * @return the idPlanoContas
     */
    public Integer getIdPlanoContas() {
        return idPlanoContas;
    }

    /**
     * @param idPlanoContas the idPlanoContas to set
     */
    public void setIdPlanoContas(Integer idPlanoContas) {
        this.idPlanoContas = idPlanoContas;
    }

    /**
     * @return the idTipoDocumento
     */
    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    /**
     * @param idTipoDocumento the idTipoDocumento to set
     */
    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    /**
     * @return the idPessoa
     */
    public Integer getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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
     * @return the dataLancamento
     */
    public Date getDataLancamento() {
        return dataLancamento;
    }

    /**
     * @param dataLancamento the dataLancamento to set
     */
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * @return the primeiroVencimento
     */
    public Date getPrimeiroVencimento() {
        return primeiroVencimento;
    }

    /**
     * @param primeiroVencimento the primeiroVencimento to set
     */
    public void setPrimeiroVencimento(Date primeiroVencimento) {
        this.primeiroVencimento = primeiroVencimento;
    }

    /**
     * @return the naturezaLancamento
     */
    public String getNaturezaLancamento() {
        return naturezaLancamento;
    }

    /**
     * @param naturezaLancamento the naturezaLancamento to set
     */
    public void setNaturezaLancamento(String naturezaLancamento) {
        this.naturezaLancamento = naturezaLancamento;
    }

    /**
     * @return the quantidadeParcela
     */
    public Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }

    /**
     * @param quantidadeParcela the quantidadeParcela to set
     */
    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }


}