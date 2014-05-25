package jACBr;

import java.lang.String;
import java.util.Date;

/**
 * Classe que encapsula as fun��es de comunica��o com o ECF;
 * Esta classe utiliza chamadas nativas ao componente ACBr32
 * @see http://acbr.sourceforge.net/drupal
 * @author Rafael Batiati
 */
public final class ACBrECF {

    /**
     * Handle para o componente nativo criado pelo ACBr32
     * Este campo � utilizado apenas pela interface JNI.
     */
    @SuppressWarnings("unused")
    private int handle;
    private Aliquota[] aliquotas;
    private FormaPagamento[] formasPagamento;
    private ComprovanteNaoFiscal[] comprovantesNaoFiscais;

    static {
        //Carrega a biblioteca de chamadas nativas JNI
        System.loadLibrary("ACBr32_JNI");
    }

    public ACBrECF() throws ACBrException {
        this.create();
    }

    @Override
    protected void finalize() throws Throwable {
        this.destroy();
        this.handle = 0;
        super.finalize();
    }

    /**
     * Aloca mem�ria para o componente ACBr nativo e retorna handle do objeto criado
     * para o campo "handle" da classe.
     * M�todo chamado apenas pelo construtor da classe.
     */
    private native void create() throws ACBrException;

    /**
     * Libera a mem�ria utilizada pelo componente nativo do ACBr.
     */
    private native void destroy() throws ACBrException;

    /**
     * Ativa o componente, abrindo a porta de comunica��o com o ECF.
     * @throws ACBrException
     */
    public native void ativar() throws ACBrException;

    /**
     * Desativa o componente, fechando a porta de comunica��o com o ECF.
     * @throws ACBrException
     */
    public native void desativar() throws ACBrException;

    /**
     * Retorna o modelo do ECF.
     * @see jACBr.ModeloECF
     * @return Modelo do ECF
     * @throws ACBrException
     */
    public native int getModelo() throws ACBrException;

    /**
     * Define o modelo do ECF.
     * @see jACBr.ModeloECF
     * @param modelo
     * @throws ACBrException
     */
    public native void setModelo(int modelo) throws ACBrException;

    /**
     * Retorna a porta de comunica��o com o ECF.
     * @return Porta de comunica��o. Ex.: COM1, LPT1
     * @throws ACBrException
     */
    public native String getPorta() throws ACBrException;

    /**
     * Define a porta de comunica��o com o ECF.
     * @param porta Porta de comunica��o. Ex.: COM1, LPT1
     * @throws ACBrException
     */
    public native void setPorta(String porta) throws ACBrException;

    /**
     * Verifica se o componente est� ativo.
     * @see jACBr.ACBrECF.ativar
     * @return True caso o componente esteja ativo; False caso contr�tio.
     * @throws ACBrException
     */
    public native boolean getAtivo() throws ACBrException;

    /**
     * Retorna o n�mero de colunas do ECF.
     * @return N�mero de colunas do ECF.
     * @throws ACBrException
     */
    public native int getColunas() throws ACBrException;

    /**
     * Retorna o timeout de comunica��o com o ECF.
     * @return Tempo de timeout em milissegundos.
     * @throws ACBrException
     */
    public native int getTimeOut() throws ACBrException;

    /**
     * Define o timeout de comunica��o com o ECF.
     * @param timeout Tempo de timeout em milissegundos.
     * @throws ACBrException
     */
    public native void setTimeOut(int timeout) throws ACBrException;

    /**
     * Verifica se o componente est� aguardando resposta do ECF.
     * @return True caso esteja aguardando resposta; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getAguardandoResposta() throws ACBrException;

    /**
     * Retorna �ltimo comando enviado ao ECF.
     * @return Array de bytes contendo o �ltimo comando enviado ao ECF.
     * @throws ACBrException
     */
    public native byte[] getComandoEnviado() throws ACBrException;

    /**
     * Retorna a ultima resposta recebida do ECF.
     * @return Array de bytes contendo a �ltima resposta recebida do ECF.
     * @throws ACBrException
     */
    public native byte[] getRespostaComando() throws ACBrException;

    /**
     * Retorna o caminho do arquivo de logs dos comandos enviados e recebidos pelo ECF.
     * @return Caminho do arquivo de log.
     * @throws ACBrException
     */
    public native String getComandoLOG() throws ACBrException;

    /**
     * Define o caminho do arquivo de logs dos comandos enviados e recebidos pelo ECF.
     * @param comandoLog Caminho do arquivo de log
     * @throws ACBrException
     */
    public native void setComandoLOG(String comandoLog) throws ACBrException;

    /**
     * Verifica se o componente deve aguardar at� o fim da impress�o.
     * @return True caso o componente deva aguardar; False caso contr�tio.
     * @throws ACBrException
     */
    public native boolean getAguardaImpressao() throws ACBrException;

    /**
     * Define se o componente deve aguardar at� o fim da impress�o.
     * @param aguardaImpressao True caso o componente deva aguardar; False caso contr�rio.
     * @throws ACBrException
     */
    public native void setAguardaImpressao(boolean aguardaImpressao) throws ACBrException;

    /**
     * Retorna o nome do modelo do ECF.
     * @return String com o nome do modelo do ECF.
     * @throws ACBrException
     */
    public native String getModeloStr() throws ACBrException;

    /**
     * Retorna o RFDID
     * @return String contendo o RFDID
     * @throws ACBrException
     */
    public native String getRFDID() throws ACBrException;

    /**
     * Retorna a Data/Hora atual do ECF.
     * @return Data/Hora atual do ECF.
     * @throws ACBrException
     */
    public native Date getDataHora() throws ACBrException;

    /**
     * Retorna o n�mero do cupom atual.
     * @return String contendo o n�mero do cupom atual.
     * @throws ACBrException
     */
    public native String getNumCupom() throws ACBrException;

    /**
     * Retorna o COO do cupom atual.
     * @return String contendo o COO do cupom atual.
     * @throws ACBrException
     */
    public native String getNumCOO() throws ACBrException;

    /**
     * Retorna o n�mero da loja
     * @return String contendo o n�mero da loja.
     * @throws ACBrException
     */
    public native String getNumLoja() throws ACBrException;

    /**
     * Retorna o n�mero do ECF.
     * @return String contendo o n�mero do ECF.
     * @throws ACBrException
     */
    public native String getNumECF() throws ACBrException;

    /**
     * Retorna o n�mero de s�rie do ECF.
     * @return String contendo o n�mero de s�rie do ECF.
     * @throws ACBrException
     */
    public native String getNumSerie() throws ACBrException;

    /**
     * Retorna o n�mero da vers�o do ECF.
     * @return String contendo o n�mero da vers�o.
     * @throws ACBrException
     */
    public native String getNumVersao() throws ACBrException;

    /**
     * Retorna a Data/Hora do movimento.
     * @return Date/Hora do movimento.
     * @throws ACBrException
     */
    public native Date getDataMovimento() throws ACBrException;

    /**
     * Retorna a Data/Hora de instala��o do SB (Software B�sico do ECF).
     * @return Data/Hora do SB.
     * @throws ACBrException
     */
    public native Date getDataHoraSB() throws ACBrException;

    /**
     * Retorna o CNPJ do propriet�rio do ECF.
     * @return String contendo o CNPJ do propriet�rio do ECF.
     * @throws ACBrException
     */
    public native String getCNPJ() throws ACBrException;

    /**
     * Retorna a IE (Inscri��o Estadual) do propriet�rio do ECF.
     * @return String contendo a IE do propriet�rio do ECF.
     * @throws ACBrException
     */
    public native String getIE() throws ACBrException;

    /**
     * Retorna a IM (Inscri��o Municipal) do propriet�rio do ECF.
     * @return String contendo a IM do propriet�rio do ECF.
     * @throws ACBrException
     */
    public native String getIM() throws ACBrException;

    /**
     * Retorna o texto do Clich� do propriet�rio do ECF.
     * @return String contendo o texto do clich�.
     * @throws ACBrException
     */
    public native String getCliche() throws ACBrException;

    /**
     * Retorna o usu�rio atual.
     * @return String contendo o usu�rio atual.
     * @throws ACBrException
     */
    public native String getUsuarioAtual() throws ACBrException;

    /**
     * Retorna o modelo detalhado do ECF.
     * @return String contendo o modelo detalhado do ECF.
     * @throws ACBrException
     */
    public native String getSubModeloECF() throws ACBrException;

    /**
     * Retorna o nome do PAF (Programa Aplicativo Fiscal)
     * @return String contendo o nome do PAF.
     * @throws ACBrException
     */
    public native String getPAF() throws ACBrException;

    /**
     * Retorna o n�mero do CRZ (Contador de Redu��es Z).
     * @return String contendo o n�mero do CRZ.
     * @throws ACBrException
     */
    public native String getNumCRZ() throws ACBrException;

    /**
     * Retorna o n�mero do CRO (Contador de Rein�cio de Opera��es).
     * @return String contendo o n�mero do CRO.
     * @throws ACBrException
     */
    public native String getNumCRO() throws ACBrException;

    /**
     * Retorna o n�mero do CCF (C�digo do Cupom Fiscal).
     * @return String contendo o CCF.
     * @throws ACBrException
     */
    public native String getNumCCF() throws ACBrException;

    /**
     * Retorna o n�mero do GNF (Gerador N�o Fiscal).
     * @return String contendo o n�mero do GNF.
     * @throws ACBrException
     */
    public native String getNumGNF() throws ACBrException;

    /**
     * Retorna o n�mero do GRG (Gerador de Relat�rio Gerencial).
     * @return String contendo o GRG.
     * @throws ACBrException
     */
    public native String getNumGRG() throws ACBrException;

    /**
     * Retorna o n�mero do CDC (Comprovante de Cr�dito e D�bito).
     * @return String contendo o n�mero do CDC.
     * @throws ACBrException
     */
    public native String getNumCDC() throws ACBrException;

    /**
     * Retorna o n�mero do COO Inicial. (Contador de Ordem de Opera��es)
     * @return String contendo o COO Inicial.
     * @throws ACBrException
     */
    public native String getNumCOOInicial() throws ACBrException;

    /**
     * Retorna o valor de venda bruta registrado no ECF.
     * @return Double contendo o valor de venda bruta.
     * @throws ACBrException
     */
    public native double getVendaBruta() throws ACBrException;

    /**
     * Retorna o Grande Total registrado no ECF.
     * @return Double contendo o valor do grande total.
     * @throws ACBrException
     */
    public native double getGrandeTotal() throws ACBrException;

    /**
     * Retorna o total de cancelamentos registrados no ECF.
     * @return Double contendo o total de cancelamentos.
     * @throws ACBrException
     */
    public native double getTotalCancelamentos() throws ACBrException;

    /**
     * Retorna o total de descontos registrados no ECF.
     * @return Double contendo o total de descontos.
     * @throws ACBrException
     */
    public native double getTotalDescontos() throws ACBrException;

    /**
     * Retorna o total de acr�scimos registrados no ECF.
     * @return Double contendo o total de acr�scimos.
     * @throws ACBrException
     */
    public native double getTotalAcrescimos() throws ACBrException;

    /**
     * Retorna o total de troco registrado no ECF.
     * @return Double contendo o total de troco.
     * @throws ACBrException
     */
    public native double getTotalTroco() throws ACBrException;

    /**
     * Retorna o total de substitui��o tribut�ria registrado no ECF.
     * @return Double contendo o total de substitui��o tribut�ria.
     * @throws ACBrException
     */
    public native double getTotalSubstituicaoTributaria() throws ACBrException;

    /**
     * Retorna o total n�o tributado registrado no ECF.
     * @return Double contendo o total n�o tributado.
     * @throws ACBrException
     */
    public native double getTotalNaoTributado() throws ACBrException;

    /**
     * Retorna o total de isen��o registrado no ECF.
     * @return Double contendo o total de isen��o.
     * @throws ACBrException
     */
    public native double getTotalIsencao() throws ACBrException;

    /**
     * Retorna o total de cancelamentos sobre ISSQN (Imposto Sobre Servi�os de Qualquer Natureza) registrado no ECF.
     * @return Double contendo o total do ISSQN.
     * @throws ACBrException
     */
    public native double getTotalCancelamentosISSQN() throws ACBrException;

    /**
     * Retorna o total de descontos sobre ISSQN (Imposto Sobre Servi�os de Qualquer Natureza) registrado no ECF.
     * @return Double contendo o total de descontos de ISSQN.
     * @throws ACBrException
     */
    public native double getTotalDescontosISSQN() throws ACBrException;

    /**
     * Retorna o total de acr�scimos sobre ISSQN (Imposto Sobre Servi�os de Qualquer Natureza) registrado no ECF.
     * @return Double contendo o total de acr�scimos de ISSQN.
     * @throws ACBrException
     */
    public native double getTotalAcrescimosISSQN() throws ACBrException;

    /**
     * Retorna o total de substitui��o tribut�ria sobre ISSQN (Imposto Sobre Servi�os de Qualquer Natureza) registrado no ECF.
     * @return Double contendo o total de substitui��o tribut�ria sobre ISSQN.
     * @throws ACBrException
     */
    public native double getTotalSubstituicaoTributariaISSQN() throws ACBrException;

    /**
     * Retorna o total n�o tributado sobre ISSQN (Imposto Sobre Servi�os de Qualquer Natureza) registrado no ECF.
     * @return Double contendo o total n�o tributado sobre ISSQN.
     * @throws ACBrException
     */
    public native double getTotalNaoTributadoISSQN() throws ACBrException;

    /**
     * Retorna o total de isen��o sobre ISSQN (Imposto Sobre Servi�os de Qualquer Natureza) registrado no ECF.
     * @return Double contendo o total de isen��o sobre ISSQN.
     * @throws ACBrException
     */
    public native double getTotalIsencaoISSQN() throws ACBrException;

    /**
     * Retorna o total n�o fiscal registrado no ECF.
     * @return Double contendo o total n�o fiscal.
     * @throws ACBrException
     */
    public native double getTotalNaoFiscal() throws ACBrException;

    /**
     * Retorna o n�mero do �ltimo item vendido no ECF.
     * @return N�mero do ultimo item vendido.
     * @throws ACBrException
     */
    public native int getNumUltItem() throws ACBrException;

    /**
     * Verifica se o ECF est� em linha.
     * @return True caso ECF esteja em linha; False caso contr�rio.
     * @throws ACBrException
     */
    public boolean getEmLinha() throws ACBrException {
        final int DEFAULT_TIMEOUT = 3000;
        return getEmLinha(DEFAULT_TIMEOUT);
    }

    /**
     * Verifica se o ECF est� em linha.
     * @param timeOut Tempo em milissegundos para obter a resposta.
     * @return True caso ECF esteja em linha; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getEmLinha(int timeOut) throws ACBrException;

    /**
     * Verifica se o ECF est� com pouco papel.
     * @return True caso o ECF esteja com pouco papel; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getPoucoPapel() throws ACBrException;

    /**
     * Retorna o estado do ECF.
     * @see jACBr.EstadoECF
     * @return Int representando o estado do ECF.
     * @throws ACBrException
     */
    public native int getEstado() throws ACBrException;

    /**
     * Verifica se o ECF est� em hor�rio de ver�o.
     * @return True caso o ECF esteja em hor�rio de ver�o; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getHorarioVerao() throws ACBrException;

    /**
     * Verifica se o ECF deve arredondar.
     * @return True caso deva arredondar; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getArredonda() throws ACBrException;

    /**
     * Verifica se o ECF � uma impressora t�rmica.
     * @return True caso seja uma impressora t�rmica; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getTermica() throws ACBrException;

    /**
     * Verifica se o ECF possui MFD (Mem�ria Fita Detalhe).
     * @return True caso o ECF possua MFD; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getMFD() throws ACBrException;

    /**
     * Verifica se o ECF � capaz de identificar o consumidor no rodap� do ECF.
     * @return True caso o ECF possua a fun��o; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getIdentificaConsumidorRodape() throws ACBrException;

    /**
     * Retorna o subtotal do cupom atual.
     * @return Double contendo o valor do subtotal.
     * @throws ACBrException
     */
    public native double getSubTotal() throws ACBrException;

    /**
     * Retorna o total pago no cupom atual.
     * @return Double contendo o total pago.
     * @throws ACBrException
     */
    public native double getTotalPago() throws ACBrException;

    /**
     * Verifica se a gaveta est� aberta.
     * @return True caso a gaveta esteja aberta; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getGavetaAberta() throws ACBrException;

    /**
     * Verifica se o ECF possui a fun��o ChequePronto.
     * @return True caso o ECF possua a fun��o; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getChequePronto() throws ACBrException;

    /**
     * Retorna o intervalo a agurardar ap�s o envio de um comando ao ECF (em milissegundos).
     * @return Intervalo
     * @throws ACBrException
     */
    public native int getIntervaloAposComando() throws ACBrException;

    /**
     * Define o intervalo a aguardar ap�s o envio de um comando ao ECF (em milissegundos).
     * @param intervalo
     * @throws ACBrException
     */
    public native void setIntervaloAposComando(int intervalo) throws ACBrException;

    /**
     * Verifica se o ECF deve usar descri��o grande nos itens.
     * @return True caso use descri��o grande; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getDescricaoGrande() throws ACBrException;

    /**
     * Define se o ECF deve usar descri��o grande nos itens.
     * @param descricaoGrande
     * @throws ACBrException
     */
    public native void setDescricaoGrande(boolean descricaoGrande) throws ACBrException;

    /**
     * Verifica se o sinal da gaveta deve ser interpretado invertido.
     * @return True caso deva inverter o sinal; False caso contr�rio.
     * @throws ACBrException
     */
    public native boolean getGavetaSinalInvertido() throws ACBrException;

    /**
     * Define se o sinal da gaveta deve ser interpretado invertido.
     * @param gavetaSinalInvertido
     * @throws ACBrException
     */
    public native void setGavetaSinalInvertido(boolean gavetaSinalInvertido) throws ACBrException;

    /**
     * Retorna o operador.
     * @return String contendo o operador.
     * @throws ACBrException
     */
    public native String getOperador() throws ACBrException;

    /**
     * Define o operador
     * @param operador
     * @throws ACBrException
     */
    public native void setOperador(String operador) throws ACBrException;

    /**
     * Retorna o n�mero de linhas entre os cupons emitidos.
     * @return N�mero de linhas
     * @throws ACBrException
     */
    public native int getLinhasEntreCupons() throws ACBrException;

    /**
     * Define o n�mero de linhas entre os cupons emitidos.
     * @param linhasEntreCupons
     * @throws ACBrException
     */
    public native void setLinhasEntreCupons(int linhasEntreCupons) throws ACBrException;

    /**
     * Retorna a quantidade de casas decimais utilizadas no pre�o.
     * @return Quantidade de casas decimais utilizadas no pre�o.
     * @throws ACBrException
     */
    public native int getDecimaisPreco() throws ACBrException;

    /**
     * Define a quantidade de casas decimais utilizadas no pre�o.
     * @param decimaisPreco
     * @throws ACBrException
     */
    public native void setDecimaisPreco(int decimaisPreco) throws ACBrException;

    /**
     * Retorna a quantidade de casas decimais utilizadas na quantidade.
     * @return Quantidade de casas decimais utilizadas na quantidade.
     * @throws ACBrException
     */
    public native int getDecimaisQtd() throws ACBrException;

    /**
     * Define a quantidade de casas decimais utilizadas na quantidade.
     * @param decimaisQtd
     * @throws ACBrException
     */
    public native void setDecimaisQtd(int decimaisQtd) throws ACBrException;

    /**
     * Identifica o consumidor para o Cupom atual.<br/>
     * Utilize a propriedade getIdentificaConsumidorRodape para verificar se este m�todo deve ser chamado antes ou depois de abrir um novo cupom.
     * @see getIdentificaConsumidorRodape
     * @param cpfCnpj CPF ou CNPJ do consumidor.
     * @param nome Nome do consumidor.
     * @param endereco Endere�o do consumidor.
     * @throws ACBrException
     */
    public native void identificaConsumidor(String cpfCnpj, String nome, String endereco) throws ACBrException;

    /**
     * Abre um cupom fiscal.
     * @throws ACBrException
     */
    public void abreCupom() throws ACBrException {
        abreCupom("", "", "");
    }

    /**
     * Abre um cupom fiscal.
     * @param cpfCnpj CPF ou CNPJ do consumidor.
     * @param nome Nome do consumidor
     * @param endereco Endere�o do consumidor.
     * @throws ACBrException
     */
    public native void abreCupom(String cpfCnpj, String nome, String endereco) throws ACBrException;

    public native void legendaInmetroProximoItem() throws ACBrException;

    /**
     * Registra a venda de um item no cupom fiscal
     * @param codigo C�digo do item.
     * @param descricao Descri��o do item.
     * @param aliquotaICMS �ndice da al�quota.
     * @param qtd Quantidade
     * @param valorUnitario Valor unit�rio
     * @param descontoPorc Desconto
     * @param unidade Unidade
     * @param tipoDescontoAcrescimo Tipo de desconto: "$" para valor ou "%" para percentual.
     * @param descontoAcrescimo Desconto ou acr�scimo: "D" para desconto ou "A" para acr�scimo.
     * @throws ACBrException
     */
    public native void vendeItem(String codigo, String descricao, String aliquotaICMS, double qtd, double valorUnitario, double descontoPorc, String unidade, String tipoDescontoAcrescimo, String descontoAcrescimo) throws ACBrException;

    /**
     * Registra desconto ou acr�scimo para o item vendido anterior.
     * @param valorDescontoAcrescimo Valor do desconto ou acr�scimo
     * @param descontoAcrescimo Desconto ou Acr�scimo: "D" para desconto ou "A" para acr�scimo.
     * @throws ACBrException
     */
    public native void descontoAcrescimoItemAnterior(double valorDescontoAcrescimo, String descontoAcrescimo) throws ACBrException;

    /**
     * Subtotaliza o cupom fiscal.
     * @param descontoAcrescimo Desconto ou acr�scimo no total da venda.
     * @param mensagemRodape Mensagem no rodap� do cupom.
     * @throws ACBrException
     */
    public native void subtotalizaCupom(double descontoAcrescimo, String mensagemRodape) throws ACBrException;

    /**
     * Efetua pagamento da compra.
     * @param codFormaPagto �ndice da forma de pagamento.
     * @param valor Valor pago.
     * @param observacao Observa��o sobre o pagamento.
     * @param imprimeVinculado Flag indicando se haver� impress�o de comprovante vinculado para este pagamento.
     * @throws ACBrException
     */
    public native void efetuaPagamento(String codFormaPagto, double valor, String observacao, boolean imprimeVinculado) throws ACBrException;

    /**
     * Estorna o pagamento efetuado.
     * @param codFormaPagtoEstornar �ndice da forma de pagamento a estornar
     * @param codFormaPagtoEfetivar �ndice da forma de pagamento a efetivar.
     * @param valor Valor o pagamento
     * @param observacao Observa��es.
     * @throws ACBrException
     */
    public native void estornaPagamento(String codFormaPagtoEstornar, String codFormaPagtoEfetivar, double valor, String observacao) throws ACBrException;

    /**
     * Fecha o cupom fiscal.
     * @param observacao Observa��es
     * @throws ACBrException
     */
    public native void fechaCupom(String observacao) throws ACBrException;

    /**
     * Cancela o cupom fiscal atualmente aberto ou o �ltimo cupom emitido.
     * @throws ACBrException
     */
    public native void cancelaCupom() throws ACBrException;

    /**
     * Cancela o item vendido.
     * @param numItem N�mero do item vendido.
     * @throws ACBrException
     */
    public native void cancelaItemVendido(int numItem) throws ACBrException;

    /**
     * Cancela o item vendido parcial.
     * @param numItem N�mero do item
     * @param quantidade Quantidade
     * @throws ACBrException
     */
    public native void cancelaItemVendidoParcial(int numItem, double quantidade) throws ACBrException;

    /**
     * Cancela o desconto ou acr�scimo de um item vendido.
     * @param numItem N�mero do item.
     * @throws ACBrException
     */
    public native void cancelaDescontoAcrescimoItem(int numItem) throws ACBrException;

    /**
     * Cancela o desconto ou acr�scimo do subtotal da venda.
     * @param tipoAcrescimoDesconto Desconto ou acr�scimo: "D" para desconto ou "A" para acr�scimo.
     * @throws ACBrException
     */
    public native void cancelaDescontoAcrescimoSubTotal(char tipoAcrescimoDesconto) throws ACBrException;

    /**
     * Emite um relat�rio de leitura X.
     * @throws ACBrException
     */
    public native void leituraX() throws ACBrException;

    /**
     * Emite uma redu��o Z.
     * @throws ACBrException
     */
    public native void reducaoZ() throws ACBrException;

    /**
     * Abre um relat�rio gerencial
     * @param indice �ndice do relat�rio
     * @throws ACBrException
     */
    public native void abreRelatorioGerencial(int indice) throws ACBrException;

    /**
     * Imprime uma linha no relat�rio gerencial.
     * @param linha
     * @param indiceBMP
     * @throws ACBrException
     */
    public native void linhaRelatorioGerencial(String linha, int indiceBMP) throws ACBrException;

    /**
     * Abre um cupom vinculado ao cupom fiscal
     * @param coo String contendo o COO do cupom fiscal.
     * @param codFormaPagto C�digo da forma de pagamento.
     * @param valor Valor do pagamento.
     * @throws ACBrException
     */
    public native void abreCupomVinculado(String coo, String codFormaPagto, double valor) throws ACBrException;

    /**
     * Abre um cupom vinculado ao cupom fiscal.
     * @param coo String contendo o COO do cupom fiscal.
     * @param codFormaPagto �ndice da forma de pagamento
     * @param codComprovanteNaoFiscal C�digo do comprovante n�o fiscal.
     * @param valor Valor do pagamento.
     * @throws ACBrException
     */
    public native void abreCupomVinculadoCNF(String coo, String codFormaPagto, String codComprovanteNaoFiscal, double valor) throws ACBrException;

    /**
     * Imprime uma linha no cupom vinculado.
     * @param linha String contendo o texto a ser impresso
     * @throws ACBrException
     */
    public native void linhaCupomVinculado(String linha) throws ACBrException;

    /**
     * Fecha o relat�rio gerencial.
     * @throws ACBrException
     */
    public native void fechaRelatorio() throws ACBrException;

    /**
     * Pula um n�mero de linhas nos relat�rios
     * @param numLinhas N�mero de linhas a pular
     * @throws ACBrException
     */
    public native void pulaLinhas(int numLinhas) throws ACBrException;

    /**
     * Aciona a guilhotina para cortar o papel.
     * @param corteParcial Flag que indica se o corte � parcial ou total.
     * @throws ACBrException
     */
    public native void cortaPapel(boolean corteParcial) throws ACBrException;

    /**
     * Retorna as al�quotas programadas no ECF.
     * @return Array contendo as al�quotas
     * @throws ACBrException
     */
    public Aliquota[] getAliquotas() throws ACBrException {
        if (aliquotas == null) {
            carregaAliquotas();
        }
        return aliquotas.clone();
    }

    /**
     * Carrega as al�quotas programadas no ECF.
     * @see getAliquotas
     * @throws ACBrException
     */
    public void carregaAliquotas() throws ACBrException {
        int count = carregaAliquotasN();

        this.aliquotas = new Aliquota[count];
        for (int i = 0; i < count; i++) {
            Aliquota aliquota = new Aliquota();
            getAliquotaN(aliquota, i);

            aliquotas[i] = aliquota;
        }
    }

    /**
     * Programa uma nova al�quota na mem�ria do ECF.<br>
     * Cuidado, um al�quota uma vez programada n�o pode ser removida da mem�ria do ECF.
     * @param aliquota Valor percentual da al�quota.
     * @param tipo Tipo da al�quota: "T" para ICMS ou "S" para ISS
     * @throws ACBrException
     */
    public native void programaAliquota(double aliquota, char tipo) throws ACBrException;

    private native void getAliquotaN(Aliquota aliquota, int index);

    private native int carregaAliquotasN();

    /**
     * Retorna as formas de pagamento programadas na mem�ria do ECF.
     * @return Array contendo as formas de pagamento.
     * @throws ACBrException
     */
    public FormaPagamento[] getFormasPagamento() throws ACBrException {
        if (formasPagamento == null) {
            carregaFormasPagamento();
        }
        return formasPagamento.clone();
    }

    /**
     * Carrega as formas de pagamento programadas no ECF.
     * @see gerFormasPagamento
     * @throws ACBrException
     */
    public void carregaFormasPagamento() throws ACBrException {
        int count = carregaFormasPagamentoN();

        formasPagamento = new FormaPagamento[count];
        for (int i = 0; i < count; i++) {
            FormaPagamento formaPagamento = new FormaPagamento();
            getFormaPagamentoN(formaPagamento, i);

            formasPagamento[i] = formaPagamento;
        }
    }

    /**
     * Programa uma nova forma de pagamento na mem�ria do ECF.<br/>
     * Cuidado, um forma de pagamento uma vez programada n�o pode ser removida da mem�ria do ECF.
     * @param descricao Descri��o da forma de pagamento.
     * @param permiteVinculado Flag indicando se a forma de pagamento permite impress�o de cupom vinculado.
     * @throws ACBrException
     */
    public native void programaFormaPagamento(String descricao, boolean permiteVinculado) throws ACBrException;

    private native int carregaFormasPagamentoN();

    private native void getFormaPagamentoN(FormaPagamento formaPagamento, int index);

    /**
     * Retorna os comprovantes n�o fiscais programados no ECF.
     * @return Array contendo os comprovantes n�o fiscais.
     * @throws ACBrException
     */
    public ComprovanteNaoFiscal[] getComprovantesNaoFiscais() throws ACBrException {
        if (comprovantesNaoFiscais == null) {
            carregaComprovantesNaoFiscais();
        }
        return comprovantesNaoFiscais.clone();
    }

    /**
     * Carrega os comprovantes n�o fiscais programados no ECF.
     * @throws ACBrException
     */
    public void carregaComprovantesNaoFiscais() throws ACBrException {
        int count = carregaComprovantesNaoFiscaisN();
        comprovantesNaoFiscais = new ComprovanteNaoFiscal[count];
        for (int i = 0; i < count; i++) {
            ComprovanteNaoFiscal comprovanteNaoFiscal = new ComprovanteNaoFiscal();
            getComprovanteNaoFiscalN(comprovanteNaoFiscal, i);

            comprovantesNaoFiscais[i] = comprovanteNaoFiscal;
        }
    }

    public native void getComprovanteNaoFiscalN(Object comprovanteNaoFiscal, int index) throws ACBrException;

    public native int carregaComprovantesNaoFiscaisN() throws ACBrException;

    /**
     * Programa um novo comprovante n�o fiscal na mem�ria do ECF.<br/>
     * Cuidado, um comprovante n�o fiscal uma vez programada n�o pode ser removida da mem�ria do ECF.
     * @param descricao
     * @param tipo
     * @throws ACBrException
     */
    public native void programaComprovanteNaoFiscal(String descricao, String tipo) throws ACBrException;

    /**
     * Efetua uma retirada de valor em caixa.
     * @param valor Valor a ser retirado.
     * @param obs Observa��es.
     * @throws ACBrException
     */
    public native void sangria(double valor, String obs) throws ACBrException;

    /**
     * Efetua um suprimento de valor em caixa.
     * @param valor Valor a ser suprido.
     * @param obs Observa��es.
     * @throws ACBrException
     */
    public native void suprimento(double valor, String obs) throws ACBrException;

    /**
     * Abre a gaveta.
     * @throws ACBrException
     */
    public native void abreGaveta() throws ACBrException;

    public native void leituraMemoriaFiscal(String Inicial, String Final, boolean simpl) throws ACBrException;

    public native void leituraMemoriaFiscalCRZ(int Inicial, int Final, boolean simpl) throws ACBrException;

    public native void leituraMemoriaFiscalSerial(String Inicial, String Final, String NomeArquivo, boolean simpl) throws ACBrException;

    public native void leituraMemoriaFiscalSerialCRZ(int Inicial, int Final, String NomeArquivo, boolean simpl) throws ACBrException;

    public native void espelhoMFD(String Inicial, String Final, String NomeArquivo) throws ACBrException;

    public native void espelhoMFDCOO(int Inicial, int Final, String NomeArquivo) throws ACBrException;

    public native void arquivoMFD(String Inicial, String Final, String NomeArquivo) throws ACBrException;

    public native void arquivoMFDCOO(int Inicial, int Final, String NomeArquivo) throws ACBrException;

    public native void leituraMFDSerial(String Inicial, String Final, String NomeArquivo) throws ACBrException;

    public native void leituraMFDSerialCOO(int Inicial, int Final, String NomeArquivo) throws ACBrException;

    public native boolean getArredondaItemMFD() throws ACBrException;

    public native void setArredondaItemMFD(boolean arredondaItemMFD) throws ACBrException;

    public native boolean getArredondaPorQtd() throws ACBrException;

    public native void setArredondaPorQtd(boolean arredondaPorQtd) throws ACBrException;
}
