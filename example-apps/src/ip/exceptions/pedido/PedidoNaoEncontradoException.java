package exceptions.pedido;

public class PedidoNaoEncontradoException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6740991126763866832L;

	public PedidoNaoEncontradoException () {
		super ("Pedido não encontrado!");
	}
}
