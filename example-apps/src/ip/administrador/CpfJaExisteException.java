package exceptions.administrador;

public class CpfJaExisteException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = -4048530757554713908L;

public CpfJaExisteException(){
	super("Esse cpf já foi cadastrado!");
}
}
