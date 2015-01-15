package exceptions.administrador;


public class BairroInvalidoException extends Exception {

	private static final long serialVersionUID = 8493332690717566026L;

	public BairroInvalidoException(){
        super("Bairro inválido");
    }
}
