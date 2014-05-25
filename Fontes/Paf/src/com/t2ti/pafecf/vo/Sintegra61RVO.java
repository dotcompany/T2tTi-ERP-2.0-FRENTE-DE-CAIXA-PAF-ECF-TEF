/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO transiente que representa o registro 61R do Sintegra</p>
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

public class Sintegra61RVO {

    private String gtin;
    private Date dataEmissao;
    private String mesEmissao;
    private String anoEmissao;
    private Double somaQuantidade;
    private Double somaValor;
    private Double somaBaseIcms;
    private Double situacaoTributaria;
    
    public Sintegra61RVO() {
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
     * @return the mesEmissao
     */
    public String getMesEmissao() {
        return mesEmissao;
    }

    /**
     * @param mesEmissao the mesEmissao to set
     */
    public void setMesEmissao(String mesEmissao) {
        this.mesEmissao = mesEmissao;
    }

    /**
     * @return the anoEmissao
     */
    public String getAnoEmissao() {
        return anoEmissao;
    }

    /**
     * @param anoEmissao the anoEmissao to set
     */
    public void setAnoEmissao(String anoEmissao) {
        this.anoEmissao = anoEmissao;
    }

    /**
     * @return the somaQuantidade
     */
    public Double getSomaQuantidade() {
        return somaQuantidade;
    }

    /**
     * @param somaQuantidade the somaQuantidade to set
     */
    public void setSomaQuantidade(Double somaQuantidade) {
        this.somaQuantidade = somaQuantidade;
    }

    /**
     * @return the somaValor
     */
    public Double getSomaValor() {
        return somaValor;
    }

    /**
     * @param somaValor the somaValor to set
     */
    public void setSomaValor(Double somaValor) {
        this.somaValor = somaValor;
    }

    /**
     * @return the somaBaseIcms
     */
    public Double getSomaBaseIcms() {
        return somaBaseIcms;
    }

    /**
     * @param somaBaseIcms the somaBaseIcms to set
     */
    public void setSomaBaseIcms(Double somaBaseIcms) {
        this.somaBaseIcms = somaBaseIcms;
    }

    /**
     * @return the situacaoTributaria
     */
    public Double getSituacaoTributaria() {
        return situacaoTributaria;
    }

    /**
     * @param situacaoTributaria the situacaoTributaria to set
     */
    public void setSituacaoTributaria(Double situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

}