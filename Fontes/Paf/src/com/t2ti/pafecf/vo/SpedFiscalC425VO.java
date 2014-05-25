/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO transiente que representa o registro 425 do Sped Fiscal</p>
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

public class SpedFiscalC425VO {

    private Integer idProduto;
    private Integer idUnidade;
    private String descricaoUnidade;
    private Double somaQuantidade;
    private Double somaValor;
    private Double somaPis;
    private Double somaCofins;
    
    public SpedFiscalC425VO() {
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
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
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