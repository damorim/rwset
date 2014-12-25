package delivery.business.exceptions.requests;

public class QuantidadeInvalidaException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 457456659926509689L;

	public QuantidadeInvalidaException(){
		super("O campo Quantidade n�o corresponde � quantidade de pedidos");
	}
}
