/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_CHEQUE_CLIENTE</p>
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

public class ChequeClienteVO  {
    
    private Integer id;
    private Integer numeroCheque;
    private java.util.Date dataCheque;
    private Double valorCheque;
    private String observacoes;
    private String agencia;
    private String conta;
    private String tipoCheque;
    private ClienteVO clienteVO;
    private BancoVO bancoVO;
    private MovimentoVO movimentoVO;

    public ChequeClienteVO() {
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
     * @return the numeroCheque
     */
    public Integer getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(Integer numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the clienteVO
     */
    public ClienteVO getClienteVO() {
        return clienteVO;
    }

    /**
     * @param clienteVO the clienteVO to set
     */
    public void setClienteVO(ClienteVO clienteVO) {
        this.clienteVO = clienteVO;
    }

    /**
     * @return the bancoVO
     */
    public BancoVO getBancoVO() {
        return bancoVO;
    }

    /**
     * @param bancoVO the bancoVO to set
     */
    public void setBancoVO(BancoVO bancoVO) {
        this.bancoVO = bancoVO;
    }

    /**
     * @return the dataCheque
     */
    public java.util.Date getDataCheque() {
        return dataCheque;
    }

    /**
     * @param dataCheque the dataCheque to set
     */
    public void setDataCheque(java.util.Date dataCheque) {
        this.dataCheque = dataCheque;
    }

    /**
     * @return the valorCheque
     */
    public Double getValorCheque() {
        return valorCheque;
    }

    /**
     * @param valorCheque the valorCheque to set
     */
    public void setValorCheque(Double valorCheque) {
        this.valorCheque = valorCheque;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the conta
     */
    public String getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(String conta) {
        this.conta = conta;
    }

    /**
     * @return the tipoCheque
     */
    public String getTipoCheque() {
        return tipoCheque;
    }

    /**
     * @param tipoCheque the tipoCheque to set
     */
    public void setTipoCheque(String tipoCheque) {
        this.tipoCheque = tipoCheque;
    }

    /**
     * @return the movimentoVO
     */
    public MovimentoVO getMovimentoVO() {
        return movimentoVO;
    }

    /**
     * @param movimentoVO the movimentoVO to set
     */
    public void setMovimentoVO(MovimentoVO movimentoVO) {
        this.movimentoVO = movimentoVO;
    }
}