package delivery.business.exceptions.requests;

public class ProdutosObrigatorioException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2301156971426753078L;

	public ProdutosObrigatorioException(){
		super("Insira ao menos um produto para efetuar o pedido");
	}
}
