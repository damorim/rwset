package delivery.business.exceptions.product;

public class CodigoJaExistenteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7714029274510436281L;

	public CodigoJaExistenteException (){
		super("Já existe um produto cadastrado com esse código");
	}

}
