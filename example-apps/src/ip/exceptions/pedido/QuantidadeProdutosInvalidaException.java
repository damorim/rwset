package exceptions.pedido;

public class QuantidadeProdutosInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5512156152622871287L;

	public QuantidadeProdutosInvalidaException(){
		super("Quantidade de produtos inválida!");
	}
	
}
