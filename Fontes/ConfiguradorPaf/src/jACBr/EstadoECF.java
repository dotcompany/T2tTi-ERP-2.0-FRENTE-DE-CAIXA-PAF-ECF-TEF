package jACBr;

public interface EstadoECF 
{
	/**
	 * Componente n�o inicializado.
	 */
    public final int NAO_INICIALIZADA = 0;
    
    /**
     * Estado desconhecido.
     */
    public final int DESCONHECIDO = 1;
    
    /**
     * ECF Livre
     */
    public final int LIVRE = 2;
    
    /**
     * Cupom fiscal aberto, vendendo itens.
     */
    public final int VENDA = 3;
    
    /**
     * Cupom fiscal totalizado, aguardando pagamentos.
     */
    public final int PAGAMENTO = 4;
    
    /**
     * Emitindo relat�rio
     */
    public final int RELATORIO = 5;
    
    /**
     * Bloqueada at� o pr�ximo movimento.
     */
    public final int BLOQUEADA = 6;
    
    /**
     * Requer leitura X.
     */
    public final int REQUER_Z = 7;
    
    /**
     * Requer redu��o Z.
     */
    public final int REQUER_X = 8;
    
    /**
     * Emitindo cupom n�o fiscal.
     */
    public final int NAO_FISCAL = 9;
}
