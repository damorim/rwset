package delivery.business.exceptions.client;

public class CidadeObrigatorioException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8647115897061777023L;

	public CidadeObrigatorioException(){
		super("O campo Cidade não foi preenchido");
	}
}
