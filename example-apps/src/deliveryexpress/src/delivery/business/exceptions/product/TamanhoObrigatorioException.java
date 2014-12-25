package delivery.business.exceptions.product;

public class TamanhoObrigatorioException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901471375985593784L;

	public TamanhoObrigatorioException(){
		super("O campo Tamanho não foi preenchido");
	}
}
