package exceptions.produto;

public class ProdutoNaoEncontradoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5502802968119199034L;

	public ProdutoNaoEncontradoException () {
		super ("Produto não encontrado!");
	}
	
}
