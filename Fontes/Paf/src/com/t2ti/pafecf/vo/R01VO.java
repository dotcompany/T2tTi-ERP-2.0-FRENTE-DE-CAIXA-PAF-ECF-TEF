/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela R01</p>
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

import java.io.Serializable;
import java.util.Date;

public class R01VO implements Serializable {

    private Integer id;
    private String serieEcf;
    private String cnpjEmpresa;
    private String cnpjSh;
    private String inscricaoEstadualSh;
    private String inscricaoMunicipalSh;
    private String denominacaoSh;
    private String nomePafEcf;
    private String versaoPafEcf;
    private String md5PafEcf;
    private Date dataInicial;
    private Date dataFinal;
    private String versaoEr;
    private String numeroLaudoPaf;
    private String razaoSocialSh;
    private String enderecoSh;
    private String numeroSh;
    private String complementoSh;
    private String bairroSh;
    private String cidadeSh;
    private String cepSh;
    private String ufSh;
    private String telefoneSh;
    private String contatoSh;
    private String principalExecutavel;
    private String hashTripa;
    private Integer hashIncremento;

    public R01VO() {
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
     * @return the cnpjEmpresa
     */
    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    /**
     * @param cnpjEmpresa the cnpjEmpresa to set
     */
    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    /**
     * @return the cnpjSh
     */
    public String getCnpjSh() {
        return cnpjSh;
    }

    /**
     * @param cnpjSh the cnpjSh to set
     */
    public void setCnpjSh(String cnpjSh) {
        this.cnpjSh = cnpjSh;
    }

    /**
     * @return the inscricaoEstadualSh
     */
    public String getInscricaoEstadualSh() {
        return inscricaoEstadualSh;
    }

    /**
     * @param inscricaoEstadualSh the inscricaoEstadualSh to set
     */
    public void setInscricaoEstadualSh(String inscricaoEstadualSh) {
        this.inscricaoEstadualSh = inscricaoEstadualSh;
    }

    /**
     * @return the inscricaoMunicipalSh
     */
    public String getInscricaoMunicipalSh() {
        return inscricaoMunicipalSh;
    }

    /**
     * @param inscricaoMunicipalSh the inscricaoMunicipalSh to set
     */
    public void setInscricaoMunicipalSh(String inscricaoMunicipalSh) {
        this.inscricaoMunicipalSh = inscricaoMunicipalSh;
    }

    /**
     * @return the denominacaoSh
     */
    public String getDenominacaoSh() {
        return denominacaoSh;
    }

    /**
     * @param denominacaoSh the denominacaoSh to set
     */
    public void setDenominacaoSh(String denominacaoSh) {
        this.denominacaoSh = denominacaoSh;
    }

    /**
     * @return the nomePafEcf
     */
    public String getNomePafEcf() {
        return nomePafEcf;
    }

    /**
     * @param nomePafEcf the nomePafEcf to set
     */
    public void setNomePafEcf(String nomePafEcf) {
        this.nomePafEcf = nomePafEcf;
    }

    /**
     * @return the versaoPafEcf
     */
    public String getVersaoPafEcf() {
        return versaoPafEcf;
    }

    /**
     * @param versaoPafEcf the versaoPafEcf to set
     */
    public void setVersaoPafEcf(String versaoPafEcf) {
        this.versaoPafEcf = versaoPafEcf;
    }

    /**
     * @return the md5PafEcf
     */
    public String getMd5PafEcf() {
        return md5PafEcf;
    }

    /**
     * @param md5PafEcf the md5PafEcf to set
     */
    public void setMd5PafEcf(String md5PafEcf) {
        this.md5PafEcf = md5PafEcf;
    }

    /**
     * @return the dataInicial
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * @return the versaoEr
     */
    public String getVersaoEr() {
        return versaoEr;
    }

    /**
     * @param versaoEr the versaoEr to set
     */
    public void setVersaoEr(String versaoEr) {
        this.versaoEr = versaoEr;
    }

    /**
     * @return the numeroLaudoPaf
     */
    public String getNumeroLaudoPaf() {
        return numeroLaudoPaf;
    }

    /**
     * @param numeroLaudoPaf the numeroLaudoPaf to set
     */
    public void setNumeroLaudoPaf(String numeroLaudoPaf) {
        this.numeroLaudoPaf = numeroLaudoPaf;
    }

    /**
     * @return the razaoSocialSh
     */
    public String getRazaoSocialSh() {
        return razaoSocialSh;
    }

    /**
     * @param razaoSocialSh the razaoSocialSh to set
     */
    public void setRazaoSocialSh(String razaoSocialSh) {
        this.razaoSocialSh = razaoSocialSh;
    }

    /**
     * @return the enderecoSh
     */
    public String getEnderecoSh() {
        return enderecoSh;
    }

    /**
     * @param enderecoSh the enderecoSh to set
     */
    public void setEnderecoSh(String enderecoSh) {
        this.enderecoSh = enderecoSh;
    }

    /**
     * @return the numeroSh
     */
    public String getNumeroSh() {
        return numeroSh;
    }

    /**
     * @param numeroSh the numeroSh to set
     */
    public void setNumeroSh(String numeroSh) {
        this.numeroSh = numeroSh;
    }

    /**
     * @return the complementoSh
     */
    public String getComplementoSh() {
        return complementoSh;
    }

    /**
     * @param complementoSh the complementoSh to set
     */
    public void setComplementoSh(String complementoSh) {
        this.complementoSh = complementoSh;
    }

    /**
     * @return the bairroSh
     */
    public String getBairroSh() {
        return bairroSh;
    }

    /**
     * @param bairroSh the bairroSh to set
     */
    public void setBairroSh(String bairroSh) {
        this.bairroSh = bairroSh;
    }

    /**
     * @return the cidadeSh
     */
    public String getCidadeSh() {
        return cidadeSh;
    }

    /**
     * @param cidadeSh the cidadeSh to set
     */
    public void setCidadeSh(String cidadeSh) {
        this.cidadeSh = cidadeSh;
    }

    /**
     * @return the cepSh
     */
    public String getCepSh() {
        return cepSh;
    }

    /**
     * @param cepSh the cepSh to set
     */
    public void setCepSh(String cepSh) {
        this.cepSh = cepSh;
    }

    /**
     * @return the ufSh
     */
    public String getUfSh() {
        return ufSh;
    }

    /**
     * @param ufSh the ufSh to set
     */
    public void setUfSh(String ufSh) {
        this.ufSh = ufSh;
    }

    /**
     * @return the telefoneSh
     */
    public String getTelefoneSh() {
        return telefoneSh;
    }

    /**
     * @param telefoneSh the telefoneSh to set
     */
    public void setTelefoneSh(String telefoneSh) {
        this.telefoneSh = telefoneSh;
    }

    /**
     * @return the contatoSh
     */
    public String getContatoSh() {
        return contatoSh;
    }

    /**
     * @param contatoSh the contatoSh to set
     */
    public void setContatoSh(String contatoSh) {
        this.contatoSh = contatoSh;
    }

    /**
     * @return the principalExecutavel
     */
    public String getPrincipalExecutavel() {
        return principalExecutavel;
    }

    /**
     * @param principalExecutavel the principalExecutavel to set
     */
    public void setPrincipalExecutavel(String principalExecutavel) {
        this.principalExecutavel = principalExecutavel;
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
