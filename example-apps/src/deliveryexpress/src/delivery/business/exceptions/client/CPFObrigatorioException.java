package delivery.business.exceptions.client;

public class CPFObrigatorioException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -497812073479199589L;

	public CPFObrigatorioException(){
		super ("O campo CPF não deve ficar em branco");
	}

}
