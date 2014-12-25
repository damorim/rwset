package delivery.business.exceptions.product;

public class ProdutoNaoCadastradoException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7138046734744607284L;

	public ProdutoNaoCadastradoException(){
		super("Esse Produto não está cadastrado");
	}
	
}
