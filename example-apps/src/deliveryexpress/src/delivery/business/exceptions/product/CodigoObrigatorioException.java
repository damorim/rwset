package delivery.business.exceptions.product;

public class CodigoObrigatorioException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6326231340619014116L;

	public CodigoObrigatorioException (){
		super("O campo Codigo não foi preenchido");
	}
}
