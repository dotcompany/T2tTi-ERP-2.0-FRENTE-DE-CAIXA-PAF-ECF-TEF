/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_TOTAL_TIPO_PGTO</p>
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

public class TotalTipoPagamentoVO {
 
    private Integer id;
    private Integer idVendaCabecalho;
    private Integer idTipoPagamento;
    private String serieEcf;
    private Integer coo;
    private Integer ccf;
    private Integer gnf;
    private Double valor;
    private String nsu;
    private String estorno;
    private String nomeRede;
    private String cartaoDc;
    private String sincronizado;
    private String hashTripa;
    private Integer hashIncremento;
    private String dataTransacao;
    private String horaTransacao;

    private String descricao;
    private TipoPagamentoVO tipoPagamentoVO;

    public TotalTipoPagamentoVO() {
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
     * @return the gnf
     */
    public Integer getGnf() {
        return gnf;
    }

    /**
     * @param gnf the gnf to set
     */
    public void setGnf(Integer gnf) {
        this.gnf = gnf;
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
     * @return the nsu
     */
    public String getNsu() {
        return nsu;
    }

    /**
     * @param nsu the nsu to set
     */
    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    /**
     * @return the estorno
     */
    public String getEstorno() {
        return estorno;
    }

    /**
     * @param estorno the estorno to set
     */
    public void setEstorno(String estorno) {
        this.estorno = estorno;
    }

    /**
     * @return the nomeRede
     */
    public String getNomeRede() {
        return nomeRede;
    }

    /**
     * @param nomeRede the nomeRede to set
     */
    public void setNomeRede(String nomeRede) {
        this.nomeRede = nomeRede;
    }

    /**
     * @return the cartaoDc
     */
    public String getCartaoDc() {
        return cartaoDc;
    }

    /**
     * @param cartaoDc the cartaoDc to set
     */
    public void setCartaoDc(String cartaoDc) {
        this.cartaoDc = cartaoDc;
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
     * @return the dataTransacao
     */
    public String getDataTransacao() {
        return dataTransacao;
    }

    /**
     * @param dataTransacao the dataTransacao to set
     */
    public void setDataTransacao(String dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    /**
     * @return the horaTransacao
     */
    public String getHoraTransacao() {
        return horaTransacao;
    }

    /**
     * @param horaTransacao the horaTransacao to set
     */
    public void setHoraTransacao(String horaTransacao) {
        this.horaTransacao = horaTransacao;
    }

    /**
     * @return the tipoPagamentoVO
     */
    public TipoPagamentoVO getTipoPagamentoVO() {
        return tipoPagamentoVO;
    }

    /**
     * @param tipoPagamentoVO the tipoPagamentoVO to set
     */
    public void setTipoPagamentoVO(TipoPagamentoVO tipoPagamentoVO) {
        this.tipoPagamentoVO = tipoPagamentoVO;
    }

    /**
     * @return the idTipoPagamento
     */
    public Integer getIdTipoPagamento() {
        return idTipoPagamento;
    }

    /**
     * @param idTipoPagamento the idTipoPagamento to set
     */
    public void setIdTipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
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


}