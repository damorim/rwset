package delivery.business.exceptions.product;

public class ValorObrigatorioException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8784596909190725711L;

	public ValorObrigatorioException(){
		super("O campo Valor não foi preenchido");
	}
}
