package bancosimples;
public class Conta{
	private String numeroConta;
	private double Saldo;
	private Cliente dono;
	
	Conta(String nC, double saldo, Cliente c){
		numeroConta = nC;
		Saldo = saldo;
		dono = c;
	}
	
	double checkSaldo(){
		return this.Saldo;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public Cliente getDono() {
		return dono;
	}

	public void setDono(Cliente dono) {
		this.dono = dono;
	}

	double debitar(double val){
		if(Saldo-val>=0){
			Saldo -= val;
			return val;
		}
		return 0;
	}
	double deposito(double val){
		return Saldo+=val;
	}
	@Override
	public int hashCode() {
		return numeroConta.hashCode();
	}
	public int compareTo(Conta arg) {
		return numeroConta.compareTo(arg.numeroConta);
	}
}
