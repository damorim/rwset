package dados.entidade;

import java.io.Serializable;

import dados.entidade.interfaces.ICliente;


public class Cliente extends Pessoa implements ICliente, Serializable  {

	private static final long serialVersionUID = -181648016681977239L;
	Cliente prox; //Proximo da Lista
	Contato contato;

	public Cliente(String nome, String cpf, String senha, Contato contato) {
		super(nome, cpf, senha, contato);
		this.contato = contato;
		this.prox = null;
	}

	public Cliente(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.contato = cliente.getContato();
		this.senha = cliente.getSenha();
		this.prox = cliente.getProx();

	}

	public Cliente() {

	}

	public Cliente getProx() { return prox;}
	public void setProx(Cliente prox) { this.prox =  prox;}

}