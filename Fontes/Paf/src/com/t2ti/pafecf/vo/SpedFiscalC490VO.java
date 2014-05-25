/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO transiente que representa o registro 490 do Sped Fiscal</p>
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

public class SpedFiscalC490VO {

    private String cst;
    private Integer cfop;
    private Double taxaIcms;
    private Double somaValor;
    private Double somaBaseIcms;
    private Double somaIcms;
    
    public SpedFiscalC490VO() {
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
     * @return the taxaIcms
     */
    public Double getTaxaIcms() {
        return taxaIcms;
    }

    /**
     * @param taxaIcms the taxaIcms to set
     */
    public void setTaxaIcms(Double taxaIcms) {
        this.taxaIcms = taxaIcms;
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
     * @return the somaIcms
     */
    public Double getSomaIcms() {
        return somaIcms;
    }

    /**
     * @param somaIcms the somaIcms to set
     */
    public void setSomaIcms(Double somaIcms) {
        this.somaIcms = somaIcms;
    }

}