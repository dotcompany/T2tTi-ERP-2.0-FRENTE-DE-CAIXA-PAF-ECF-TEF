/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela SINTEGRA_60M</p>
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

import java.sql.Date;

public class Sintegra60MVO {
 
    private Integer id;
    private Date dataEmissao;
    private String numeroSerieEcf;
    private Integer numeroEquipamento;
    private String modeloDocumentoFiscal;
    private Integer cooInicial;
    private Integer cooFinal;
    private Integer crz;
    private Integer cro;
    private Double valorVendaBruta;
    private Double valorGrandeTotal;

    public Sintegra60MVO() {
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
     * @return the numeroSerieEcf
     */
    public String getNumeroSerieEcf() {
        return numeroSerieEcf;
    }

    /**
     * @param numeroSerieEcf the numeroSerieEcf to set
     */
    public void setNumeroSerieEcf(String numeroSerieEcf) {
        this.numeroSerieEcf = numeroSerieEcf;
    }

    /**
     * @return the numeroEquipamento
     */
    public Integer getNumeroEquipamento() {
        return numeroEquipamento;
    }

    /**
     * @param numeroEquipamento the numeroEquipamento to set
     */
    public void setNumeroEquipamento(Integer numeroEquipamento) {
        this.numeroEquipamento = numeroEquipamento;
    }

    /**
     * @return the modeloDocumentoFiscal
     */
    public String getModeloDocumentoFiscal() {
        return modeloDocumentoFiscal;
    }

    /**
     * @param modeloDocumentoFiscal the modeloDocumentoFiscal to set
     */
    public void setModeloDocumentoFiscal(String modeloDocumentoFiscal) {
        this.modeloDocumentoFiscal = modeloDocumentoFiscal;
    }

    /**
     * @return the cooInicial
     */
    public Integer getCooInicial() {
        return cooInicial;
    }

    /**
     * @param cooInicial the cooInicial to set
     */
    public void setCooInicial(Integer cooInicial) {
        this.cooInicial = cooInicial;
    }

    /**
     * @return the cooFinal
     */
    public Integer getCooFinal() {
        return cooFinal;
    }

    /**
     * @param cooFinal the cooFinal to set
     */
    public void setCooFinal(Integer cooFinal) {
        this.cooFinal = cooFinal;
    }

    /**
     * @return the crz
     */
    public Integer getCrz() {
        return crz;
    }

    /**
     * @param crz the crz to set
     */
    public void setCrz(Integer crz) {
        this.crz = crz;
    }

    /**
     * @return the cro
     */
    public Integer getCro() {
        return cro;
    }

    /**
     * @param cro the cro to set
     */
    public void setCro(Integer cro) {
        this.cro = cro;
    }

    /**
     * @return the valorVendaBruta
     */
    public Double getValorVendaBruta() {
        return valorVendaBruta;
    }

    /**
     * @param valorVendaBruta the valorVendaBruta to set
     */
    public void setValorVendaBruta(Double valorVendaBruta) {
        this.valorVendaBruta = valorVendaBruta;
    }

    /**
     * @return the valorGrandeTotal
     */
    public Double getValorGrandeTotal() {
        return valorGrandeTotal;
    }

    /**
     * @param valorGrandeTotal the valorGrandeTotal to set
     */
    public void setValorGrandeTotal(Double valorGrandeTotal) {
        this.valorGrandeTotal = valorGrandeTotal;
    }

 
}