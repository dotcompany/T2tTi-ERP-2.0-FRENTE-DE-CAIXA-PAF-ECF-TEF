/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_FUNCIONARIO</p>
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

public class FuncionarioVO {
    private Integer id;
    private String nome;
    private String telefone;
    private String celular;
    private String email;
    private Double comissaoVista;
    private Double comissaoPrazo;
    private String nivelAutorizacao;

    public FuncionarioVO() {
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the comissaoVista
     */
    public Double getComissaoVista() {
        return comissaoVista;
    }

    /**
     * @param comissaoVista the comissaoVista to set
     */
    public void setComissaoVista(Double comissaoVista) {
        this.comissaoVista = comissaoVista;
    }

    /**
     * @return the comissaoPrazo
     */
    public Double getComissaoPrazo() {
        return comissaoPrazo;
    }

    /**
     * @param comissaoPrazo the comissaoPrazo to set
     */
    public void setComissaoPrazo(Double comissaoPrazo) {
        this.comissaoPrazo = comissaoPrazo;
    }

    /**
     * @return the nivelAutorizacao
     */
    public String getNivelAutorizacao() {
        return nivelAutorizacao;
    }

    /**
     * @param nivelAutorizacao the nivelAutorizacao to set
     */
    public void setNivelAutorizacao(String nivelAutorizacao) {
        this.nivelAutorizacao = nivelAutorizacao;
    }

}