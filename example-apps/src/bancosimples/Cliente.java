package bancosimples;
public class Cliente {
	private String RG;
	private String nome;
	private String telefone;
	private Conta conta;
	
	Cliente(){}
	Cliente(String nome, String telefone,String RG){
		this.RG = RG;
		this.nome = nome;
		this.telefone = telefone;
	}
	void setConta(Conta conta){
		this.conta = conta;
	}
	Conta getConta(){
		return conta;
	}
	void deposito(double d){
		conta.deposito(d);
	}
	double debitar(double d){
		return conta.debitar(d);
	}
	double extrato(){
		return conta.checkSaldo();
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public int hashCode() {
		return RG.hashCode();
	}

	public int compareTo(Cliente arg) {
		return RG.compareTo(arg.RG);
	}
}
