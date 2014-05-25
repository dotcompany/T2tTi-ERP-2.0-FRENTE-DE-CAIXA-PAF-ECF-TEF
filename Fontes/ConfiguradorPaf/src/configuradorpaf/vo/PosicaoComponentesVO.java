/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: VO relacionado à tabela ECF_POSICAO_COMPONENTES</p>
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
package configuradorpaf.vo;

public class PosicaoComponentesVO {
    private Integer id;
    private Integer idResolucao;
    private String nomeComponente;
    private Integer altura;
    private Integer largura;
    private Integer topo;
    private Integer esquerda;
    private Integer tamanhoFonte;
    private String textoComponente;

    public PosicaoComponentesVO() {
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
     * @return the idResolucao
     */
    public Integer getIdResolucao() {
        return idResolucao;
    }

    /**
     * @param idResolucao the idResolucao to set
     */
    public void setIdResolucao(Integer idResolucao) {
        this.idResolucao = idResolucao;
    }

    /**
     * @return the nomeComponente
     */
    public String getNomeComponente() {
        return nomeComponente;
    }

    /**
     * @param nomeComponente the nomeComponente to set
     */
    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

    /**
     * @return the altura
     */
    public Integer getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    /**
     * @return the largura
     */
    public Integer getLargura() {
        return largura;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(Integer largura) {
        this.largura = largura;
    }

    /**
     * @return the topo
     */
    public Integer getTopo() {
        return topo;
    }

    /**
     * @param topo the topo to set
     */
    public void setTopo(Integer topo) {
        this.topo = topo;
    }

    /**
     * @return the esquerda
     */
    public Integer getEsquerda() {
        return esquerda;
    }

    /**
     * @param esquerda the esquerda to set
     */
    public void setEsquerda(Integer esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * @return the tamanhoFonte
     */
    public Integer getTamanhoFonte() {
        return tamanhoFonte;
    }

    /**
     * @param tamanhoFonte the tamanhoFonte to set
     */
    public void setTamanhoFonte(Integer tamanhoFonte) {
        this.tamanhoFonte = tamanhoFonte;
    }

    /**
     * @return the textoComponente
     */
    public String getTextoComponente() {
        return textoComponente;
    }

    /**
     * @param textoComponente the textoComponente to set
     */
    public void setTextoComponente(String textoComponente) {
        this.textoComponente = textoComponente;
    }

}