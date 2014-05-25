/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO transiente para carregar dados do Sintegra</p>
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

public class SintegraVO {

    private Integer id;
    private Integer isSintegra60M;
    private String situacaoTributaria;
    private Double outras;
    private String situacao;
    private Double aliquota;
    private Double isentas;
    private Double icms;
    private Double valorContabil;
    private Double basedeCalculo;
    private String emissorDocumento;
    private String cfop;
    private String inscricao;
    private String uf;
    private String serie;
    private String cpfCnpj;
    private String modelo;
    private String numero;
    private Date dataDocumento;
    private String codigoAntecipacao;
    private Double baseSt;
    private String emitente;
    private Double despesas;
    private Double icmsRetido;
    private Double valorIpi;
    private Double valorIsentas;
    private Double valorOutras;
    private String numeroItem;
    private String descricao;
    private String cst;
    private String codigo;
    private Double quantidade;
    private Double valor;
    private Double valorDescontoDespesa;
    private String ncm;
    private String unidade;
    private Double aliquotaIpi;
    private Double aliquotaICMS;
    private Double reducao;
    
    public SintegraVO() {
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
     * @return the isSintegra60M
     */
    public Integer getIsSintegra60M() {
        return isSintegra60M;
    }

    /**
     * @param isSintegra60M the isSintegra60M to set
     */
    public void setIsSintegra60M(Integer isSintegra60M) {
        this.isSintegra60M = isSintegra60M;
    }

    /**
     * @return the situacaoTributaria
     */
    public String getSituacaoTributaria() {
        return situacaoTributaria;
    }

    /**
     * @param situacaoTributaria the situacaoTributaria to set
     */
    public void setSituacaoTributaria(String situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    /**
     * @return the outras
     */
    public Double getOutras() {
        return outras;
    }

    /**
     * @param outras the outras to set
     */
    public void setOutras(Double outras) {
        this.outras = outras;
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

    /**
     * @return the aliquota
     */
    public Double getAliquota() {
        return aliquota;
    }

    /**
     * @param aliquota the aliquota to set
     */
    public void setAliquota(Double aliquota) {
        this.aliquota = aliquota;
    }

    /**
     * @return the isentas
     */
    public Double getIsentas() {
        return isentas;
    }

    /**
     * @param isentas the isentas to set
     */
    public void setIsentas(Double isentas) {
        this.isentas = isentas;
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
     * @return the valorContabil
     */
    public Double getValorContabil() {
        return valorContabil;
    }

    /**
     * @param valorContabil the valorContabil to set
     */
    public void setValorContabil(Double valorContabil) {
        this.valorContabil = valorContabil;
    }

    /**
     * @return the basedeCalculo
     */
    public Double getBasedeCalculo() {
        return basedeCalculo;
    }

    /**
     * @param basedeCalculo the basedeCalculo to set
     */
    public void setBasedeCalculo(Double basedeCalculo) {
        this.basedeCalculo = basedeCalculo;
    }

    /**
     * @return the emissorDocumento
     */
    public String getEmissorDocumento() {
        return emissorDocumento;
    }

    /**
     * @param emissorDocumento the emissorDocumento to set
     */
    public void setEmissorDocumento(String emissorDocumento) {
        this.emissorDocumento = emissorDocumento;
    }

    /**
     * @return the cfop
     */
    public String getCfop() {
        return cfop;
    }

    /**
     * @param cfop the cfop to set
     */
    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    /**
     * @return the inscricao
     */
    public String getInscricao() {
        return inscricao;
    }

    /**
     * @param inscricao the inscricao to set
     */
    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
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
     * @return the cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * @param cpfCnpj the cpfCnpj to set
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
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
     * @return the dataDocumento
     */
    public Date getDataDocumento() {
        return dataDocumento;
    }

    /**
     * @param dataDocumento the dataDocumento to set
     */
    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    /**
     * @return the codigoAntecipacao
     */
    public String getCodigoAntecipacao() {
        return codigoAntecipacao;
    }

    /**
     * @param codigoAntecipacao the codigoAntecipacao to set
     */
    public void setCodigoAntecipacao(String codigoAntecipacao) {
        this.codigoAntecipacao = codigoAntecipacao;
    }

    /**
     * @return the baseSt
     */
    public Double getBaseSt() {
        return baseSt;
    }

    /**
     * @param baseSt the baseSt to set
     */
    public void setBaseSt(Double baseSt) {
        this.baseSt = baseSt;
    }

    /**
     * @return the emitente
     */
    public String getEmitente() {
        return emitente;
    }

    /**
     * @param emitente the emitente to set
     */
    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    /**
     * @return the despesas
     */
    public Double getDespesas() {
        return despesas;
    }

    /**
     * @param despesas the despesas to set
     */
    public void setDespesas(Double despesas) {
        this.despesas = despesas;
    }

    /**
     * @return the icmsRetido
     */
    public Double getIcmsRetido() {
        return icmsRetido;
    }

    /**
     * @param icmsRetido the icmsRetido to set
     */
    public void setIcmsRetido(Double icmsRetido) {
        this.icmsRetido = icmsRetido;
    }

    /**
     * @return the valorIpi
     */
    public Double getValorIpi() {
        return valorIpi;
    }

    /**
     * @param valorIpi the valorIpi to set
     */
    public void setValorIpi(Double valorIpi) {
        this.valorIpi = valorIpi;
    }

    /**
     * @return the valorIsentas
     */
    public Double getValorIsentas() {
        return valorIsentas;
    }

    /**
     * @param valorIsentas the valorIsentas to set
     */
    public void setValorIsentas(Double valorIsentas) {
        this.valorIsentas = valorIsentas;
    }

    /**
     * @return the valorOutras
     */
    public Double getValorOutras() {
        return valorOutras;
    }

    /**
     * @param valorOutras the valorOutras to set
     */
    public void setValorOutras(Double valorOutras) {
        this.valorOutras = valorOutras;
    }

    /**
     * @return the numeroItem
     */
    public String getNumeroItem() {
        return numeroItem;
    }

    /**
     * @param numeroItem the numeroItem to set
     */
    public void setNumeroItem(String numeroItem) {
        this.numeroItem = numeroItem;
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the valorDescontoDespesa
     */
    public Double getValorDescontoDespesa() {
        return valorDescontoDespesa;
    }

    /**
     * @param valorDescontoDespesa the valorDescontoDespesa to set
     */
    public void setValorDescontoDespesa(Double valorDescontoDespesa) {
        this.valorDescontoDespesa = valorDescontoDespesa;
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
     * @return the unidade
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * @return the aliquotaIpi
     */
    public Double getAliquotaIpi() {
        return aliquotaIpi;
    }

    /**
     * @param aliquotaIpi the aliquotaIpi to set
     */
    public void setAliquotaIpi(Double aliquotaIpi) {
        this.aliquotaIpi = aliquotaIpi;
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
     * @return the reducao
     */
    public Double getReducao() {
        return reducao;
    }

    /**
     * @param reducao the reducao to set
     */
    public void setReducao(Double reducao) {
        this.reducao = reducao;
    }


}