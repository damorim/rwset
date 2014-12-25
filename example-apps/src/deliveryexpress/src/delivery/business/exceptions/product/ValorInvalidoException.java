package delivery.business.exceptions.product;

public class ValorInvalidoException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4078263714833133317L;

	public ValorInvalidoException(){
		super("O valor deve ser maior que 0(zero)");
	}
}
