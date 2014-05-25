/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_MOVIMENTO</p>
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

public class MovimentoVO {
 
    private Integer id;
    private Integer idEmpresa;
    private Integer idImpressora;
    private String identificacaoImpressora;
    private Integer idTurno;
    private String descricaoTurno;
    private Integer idOperador;
    private String loginOperador;
    private Integer idCaixa;
    private String nomeCaixa;
    private Integer idGerenteSupervisor;
    private Date dataAbertura;
    private String horaAbertura;
    private Date dataFechamento;
    private String horaFechamento;
    private Double totalSuprimento;
    private Double totalSangria;
    private Double totalNaoFiscal;
    private Double totalVenda;
    private Double totalDesconto;
    private Double totalAcrescimo;
    private Double totalFinal;
    private Double totalRecebido;
    private Double totalTroco;
    private Double totalCancelado;
    private String statusMovimento;
    private String sincronizado;

    public MovimentoVO() {
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
     * @return the identificacaoImpressora
     */
    public String getIdentificacaoImpressora() {
        return identificacaoImpressora;
    }

    /**
     * @param identificacaoImpressora the identificacaoImpressora to set
     */
    public void setIdentificacaoImpressora(String identificacaoImpressora) {
        this.identificacaoImpressora = identificacaoImpressora;
    }

    /**
     * @return the idTurno
     */
    public Integer getIdTurno() {
        return idTurno;
    }

    /**
     * @param idTurno the idTurno to set
     */
    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    /**
     * @return the descricaoTurno
     */
    public String getDescricaoTurno() {
        return descricaoTurno;
    }

    /**
     * @param descricaoTurno the descricaoTurno to set
     */
    public void setDescricaoTurno(String descricaoTurno) {
        this.descricaoTurno = descricaoTurno;
    }

    /**
     * @return the idOperador
     */
    public Integer getIdOperador() {
        return idOperador;
    }

    /**
     * @param idOperador the idOperador to set
     */
    public void setIdOperador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    /**
     * @return the loginOperador
     */
    public String getLoginOperador() {
        return loginOperador;
    }

    /**
     * @param loginOperador the loginOperador to set
     */
    public void setLoginOperador(String loginOperador) {
        this.loginOperador = loginOperador;
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
     * @return the idGerenteSupervisor
     */
    public Integer getIdGerenteSupervisor() {
        return idGerenteSupervisor;
    }

    /**
     * @param idGerenteSupervisor the idGerenteSupervisor to set
     */
    public void setIdGerenteSupervisor(Integer idGerenteSupervisor) {
        this.idGerenteSupervisor = idGerenteSupervisor;
    }

    /**
     * @return the totalSuprimento
     */
    public Double getTotalSuprimento() {
        return totalSuprimento;
    }

    /**
     * @param totalSuprimento the totalSuprimento to set
     */
    public void setTotalSuprimento(Double totalSuprimento) {
        this.totalSuprimento = totalSuprimento;
    }

    /**
     * @return the totalSangria
     */
    public Double getTotalSangria() {
        return totalSangria;
    }

    /**
     * @param totalSangria the totalSangria to set
     */
    public void setTotalSangria(Double totalSangria) {
        this.totalSangria = totalSangria;
    }

    /**
     * @return the totalNaoFiscal
     */
    public Double getTotalNaoFiscal() {
        return totalNaoFiscal;
    }

    /**
     * @param totalNaoFiscal the totalNaoFiscal to set
     */
    public void setTotalNaoFiscal(Double totalNaoFiscal) {
        this.totalNaoFiscal = totalNaoFiscal;
    }

    /**
     * @return the totalVenda
     */
    public Double getTotalVenda() {
        return totalVenda;
    }

    /**
     * @param totalVenda the totalVenda to set
     */
    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    /**
     * @return the totalDesconto
     */
    public Double getTotalDesconto() {
        return totalDesconto;
    }

    /**
     * @param totalDesconto the totalDesconto to set
     */
    public void setTotalDesconto(Double totalDesconto) {
        this.totalDesconto = totalDesconto;
    }

    /**
     * @return the totalAcrescimo
     */
    public Double getTotalAcrescimo() {
        return totalAcrescimo;
    }

    /**
     * @param totalAcrescimo the totalAcrescimo to set
     */
    public void setTotalAcrescimo(Double totalAcrescimo) {
        this.totalAcrescimo = totalAcrescimo;
    }

    /**
     * @return the totalFinal
     */
    public Double getTotalFinal() {
        return totalFinal;
    }

    /**
     * @param totalFinal the totalFinal to set
     */
    public void setTotalFinal(Double totalFinal) {
        this.totalFinal = totalFinal;
    }

    /**
     * @return the totalRecebido
     */
    public Double getTotalRecebido() {
        return totalRecebido;
    }

    /**
     * @param totalRecebido the totalRecebido to set
     */
    public void setTotalRecebido(Double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    /**
     * @return the totalTroco
     */
    public Double getTotalTroco() {
        return totalTroco;
    }

    /**
     * @param totalTroco the totalTroco to set
     */
    public void setTotalTroco(Double totalTroco) {
        this.totalTroco = totalTroco;
    }

    /**
     * @return the totalCancelado
     */
    public Double getTotalCancelado() {
        return totalCancelado;
    }

    /**
     * @param totalCancelado the totalCancelado to set
     */
    public void setTotalCancelado(Double totalCancelado) {
        this.totalCancelado = totalCancelado;
    }

    /**
     * @return the statusMovimento
     */
    public String getStatusMovimento() {
        return statusMovimento;
    }

    /**
     * @param statusMovimento the statusMovimento to set
     */
    public void setStatusMovimento(String statusMovimento) {
        this.statusMovimento = statusMovimento;
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
     * @return the dataAbertura
     */
    public Date getDataAbertura() {
        return dataAbertura;
    }

    /**
     * @param dataAbertura the dataAbertura to set
     */
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * @return the horaAbertura
     */
    public String getHoraAbertura() {
        return horaAbertura;
    }

    /**
     * @param horaAbertura the horaAbertura to set
     */
    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    /**
     * @return the dataFechamento
     */
    public Date getDataFechamento() {
        return dataFechamento;
    }

    /**
     * @param dataFechamento the dataFechamento to set
     */
    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    /**
     * @return the horaFechamento
     */
    public String getHoraFechamento() {
        return horaFechamento;
    }

    /**
     * @param horaFechamento the horaFechamento to set
     */
    public void setHoraFechamento(String horaFechamento) {
        this.horaFechamento = horaFechamento;
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

}