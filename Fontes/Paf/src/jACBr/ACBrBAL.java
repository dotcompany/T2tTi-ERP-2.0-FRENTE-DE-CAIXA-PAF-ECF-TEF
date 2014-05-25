package jACBr;

import java.lang.String;
import java.util.Date;

/**
 * Classe que encapsula as fun��es de comunica��o com o BAL;
 * Esta classe utiliza chamadas nativas ao componente ACBr32
 * @see http://acbr.sourceforge.net/drupal
 * @author Rafael Batiati
 */
public final class ACBrBAL
{
	/**
	 * Handle para o componente nativo criado pelo ACBr32
	 * Este campo � utilizado apenas pela interface JNI.
	 */
	@SuppressWarnings("unused")
	private int handle;
	
	static
	{
		//Carrega a biblioteca de chamadas nativas JNI
		System.loadLibrary("ACBr32_JNI");
	}

	public ACBrBAL() throws ACBrException
	{
		this.create();
	}
	
	@Override
 	protected void finalize() throws Throwable
	{
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
	 * Ativa o componente, abrindo a porta de comunica��o com o BAL.
	 * @throws ACBrException
	 */
	public native void ativar() throws ACBrException;
	
	/**
	 * Desativa o componente, fechando a porta de comunica��o com o BAL.
	 * @throws ACBrException
	 */
	public native void desativar() throws ACBrException;
	
	/**
	 * Retorna o modelo do BAL.
	 * @see jACBr.ModeloBAL
	 * @return Modelo do BAL
	 * @throws ACBrException
	 */
	public native int getModelo() throws ACBrException;	
	
	/**
	 * Define o modelo do BAL.
	 * @see jACBr.ModeloBAL
	 * @param modelo 
	 * @throws ACBrException
	 */
	public native void setModelo(int modelo) throws ACBrException;

	/**
	 * Retorna a porta de comunica��o com o BAL.
	 * @return Porta de comunica��o. Ex.: COM1, LPT1 
	 * @throws ACBrException
	 */
	public native String getPorta() throws ACBrException;
	
	/**
	 * Define a porta de comunica��o com o BAL.
	 * @param porta Porta de comunica��o. Ex.: COM1, LPT1
	 * @throws ACBrException
	 */
	public native void setPorta(String porta) throws ACBrException;
	
	/**
	 * Verifica se o componente est� ativo.
	 * @see jACBr.ACBrBAL.ativar
	 * @return True caso o componente esteja ativo; False caso contr�tio.
	 * @throws ACBrException
	 */
	public native boolean getAtivo() throws ACBrException;
	
	
	/**
	 * Retorna o nome do modelo do BAL. 
	 * @return String com o nome do modelo do BAL.
	 * @throws ACBrException
	 */
	public native String getModeloStr() throws ACBrException;
	
        /**
	 * Retorna ultimo peso lido do BAL. 
	 * @return double com ultimo peso lido do BAL.
	 * @throws ACBrException
	 */
	public native double getUltimoPesoLido() throws ACBrException;
        
        /**
	 * Retorna a ultima resposta da BAL. 
	 * @return String com a ultima resposta do BAL.
	 * @throws ACBrException
	 */
        public native String getUltimaResposta() throws ACBrException;
        
        /**
	 * Retorna o peso da BAL. 
	 * @return double com o peso da BAL.
	 * @throws ACBrException
	 */
        public native double lePeso(int timeOut) throws ACBrException;
        
        /**
	 * Retorna o ultimo erro BAL. 
	 * @return String com o ultimo erro do BAL.
	 * @throws ACBrException
	 */
        public native String getUltimoErro() throws ACBrException;

}
