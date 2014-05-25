/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_TIPO_PAGAMENTO</p>
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

public class TipoPagamentoVO {
 
    private Integer id;
    private String codigo;
    private String descricao;
    private String tef;
    private String imprimeVinculado;
    private String permiteTroco;
    private String tefTipoGp;
    private String geraParcelas;
    
    private Double valor;

    public TipoPagamentoVO() {
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
     * @return the tef
     */
    public String getTef() {
        return tef;
    }

    /**
     * @param tef the tef to set
     */
    public void setTef(String tef) {
        this.tef = tef;
    }

    /**
     * @return the imprimeVinculado
     */
    public String getImprimeVinculado() {
        return imprimeVinculado;
    }

    /**
     * @param imprimeVinculado the imprimeVinculado to set
     */
    public void setImprimeVinculado(String imprimeVinculado) {
        this.imprimeVinculado = imprimeVinculado;
    }

    /**
     * @return the permiteTroco
     */
    public String getPermiteTroco() {
        return permiteTroco;
    }

    /**
     * @param permiteTroco the permiteTroco to set
     */
    public void setPermiteTroco(String permiteTroco) {
        this.permiteTroco = permiteTroco;
    }

    /**
     * @return the tefTipoGp
     */
    public String getTefTipoGp() {
        return tefTipoGp;
    }

    /**
     * @param tefTipoGp the tefTipoGp to set
     */
    public void setTefTipoGp(String tefTipoGp) {
        this.tefTipoGp = tefTipoGp;
    }

    /**
     * @return the geraParcelas
     */
    public String getGeraParcelas() {
        return geraParcelas;
    }

    /**
     * @param geraParcelas the geraParcelas to set
     */
    public void setGeraParcelas(String geraParcelas) {
        this.geraParcelas = geraParcelas;
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

}