package dados.entidade;

import java.io.Serializable;

import dados.entidade.interfaces.IAdm;


@SuppressWarnings("rawtypes")
public class Administrador extends Pessoa implements IAdm, Serializable, Comparable {
	
	private static final long serialVersionUID = 799180543074610211L;
	Administrador prox; //Proximo da Lista
	Contato contato;
	
	public Administrador(String nome, String cpf, String senha, Contato contato) {
		super(nome, cpf, senha, contato);
		this.contato = contato;
		this.prox = null;
	}
	
	
	

	public Administrador(Administrador administrador) {
		this.nome = administrador.getNome();
		this.cpf = administrador.getCpf();
		this.contato = administrador.getContato();
		this.senha = administrador.getSenha();
		this.prox = administrador.getProx();
	
	}


	public Administrador() {
		
	}



	public Administrador getProx() {
		return prox;
	}

	public void setProx(Administrador prox) {
		this.prox =  prox;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}