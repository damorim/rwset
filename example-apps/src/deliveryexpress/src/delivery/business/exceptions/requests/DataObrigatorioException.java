package delivery.business.exceptions.requests;

public class DataObrigatorioException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2796496523538144666L;

	public DataObrigatorioException(){
		super("O campo Data deve ser preenchido");
	}
}
