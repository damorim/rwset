package exceptions;

public class NomeInvalidoException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 365306938689757384L;

	public NomeInvalidoException () {
        super ("Nome inválido!");
    }
    
}
