package delivery.business.exceptions.client;

public class BairroObrigatorioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 974612530105317279L;
	
	public BairroObrigatorioException(){
		super("O campo Bairro não foi preenchido");
	}
	
}
