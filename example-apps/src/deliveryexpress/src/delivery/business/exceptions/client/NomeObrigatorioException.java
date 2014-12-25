package delivery.business.exceptions.client;

public class NomeObrigatorioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4795454502345866112L;
	
	public NomeObrigatorioException(){
		super("O campo Nome não deve ficar em branco");
	}

}
