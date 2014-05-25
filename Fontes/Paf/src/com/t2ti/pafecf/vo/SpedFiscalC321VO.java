/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO transiente que representa o registro 321 do Sped Fiscal</p>
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

public class SpedFiscalC321VO {

    private Integer idProduto;
    private Double somaQuantidade;
    private String descricaoUnidade;
    private Double somaValor;
    private Double somaDesconto;
    private Double somaBaseIcms;
    private Double somaIcms;
    private Double somaPis;
    private Double somaCofins;
    
    public SpedFiscalC321VO() {
    }

    /**
     * @return the idProduto
     */
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
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
     * @return the descricaoUnidade
     */
    public String getDescricaoUnidade() {
        return descricaoUnidade;
    }

    /**
     * @param descricaoUnidade the descricaoUnidade to set
     */
    public void setDescricaoUnidade(String descricaoUnidade) {
        this.descricaoUnidade = descricaoUnidade;
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
     * @return the somaDesconto
     */
    public Double getSomaDesconto() {
        return somaDesconto;
    }

    /**
     * @param somaDesconto the somaDesconto to set
     */
    public void setSomaDesconto(Double somaDesconto) {
        this.somaDesconto = somaDesconto;
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

    /**
     * @return the somaPis
     */
    public Double getSomaPis() {
        return somaPis;
    }

    /**
     * @param somaPis the somaPis to set
     */
    public void setSomaPis(Double somaPis) {
        this.somaPis = somaPis;
    }

    /**
     * @return the somaCofins
     */
    public Double getSomaCofins() {
        return somaCofins;
    }

    /**
     * @param somaCofins the somaCofins to set
     */
    public void setSomaCofins(Double somaCofins) {
        this.somaCofins = somaCofins;
    }

}