/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela R07</p>
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

public class R07VO {

    private Integer id;
    private Integer idR04;
    private Integer idR06;
    private String serieEcf;
    private Integer coo;
    private Integer ccf;
    private Integer gnf;
    private String meioPagamento;
    private Double valorPagamento;
    private String indicadorEstorno;
    private Double valorEstorno;
    private String sincronizado;
    private String hashTripa;
    private Integer hashIncremento;

    public R07VO() {
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
     * @return the idR04
     */
    public Integer getIdR04() {
        return idR04;
    }

    /**
     * @param idR04 the idR04 to set
     */
    public void setIdR04(Integer idR04) {
        this.idR04 = idR04;
    }

    /**
     * @return the idR06
     */
    public Integer getIdR06() {
        return idR06;
    }

    /**
     * @param idR06 the idR06 to set
     */
    public void setIdR06(Integer idR06) {
        this.idR06 = idR06;
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
     * @return the meioPagamento
     */
    public String getMeioPagamento() {
        return meioPagamento;
    }

    /**
     * @param meioPagamento the meioPagamento to set
     */
    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    /**
     * @return the valorPagamento
     */
    public Double getValorPagamento() {
        return valorPagamento;
    }

    /**
     * @param valorPagamento the valorPagamento to set
     */
    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    /**
     * @return the indicadorEstorno
     */
    public String getIndicadorEstorno() {
        return indicadorEstorno;
    }

    /**
     * @param indicadorEstorno the indicadorEstorno to set
     */
    public void setIndicadorEstorno(String indicadorEstorno) {
        this.indicadorEstorno = indicadorEstorno;
    }

    /**
     * @return the valorEstorno
     */
    public Double getValorEstorno() {
        return valorEstorno;
    }

    /**
     * @param valorEstorno the valorEstorno to set
     */
    public void setValorEstorno(Double valorEstorno) {
        this.valorEstorno = valorEstorno;
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

    
}