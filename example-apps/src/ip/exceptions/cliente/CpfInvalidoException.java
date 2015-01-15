package exceptions.cliente;

public class CpfInvalidoException extends Exception {

	private static final long serialVersionUID = 8430532418525952253L;

	public CpfInvalidoException() {
		super("CPF inválido!");
	}

}
