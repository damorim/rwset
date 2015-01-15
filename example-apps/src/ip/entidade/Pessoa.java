package dados.entidade;

import java.io.Serializable;


public abstract class Pessoa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8228879323512431235L;
	protected String nome;
	protected String cpf;
	protected Contato contato;
    protected String senha;
    private Pessoa prox;
    
	public Pessoa(String nome, String cpf, String senha, Contato contato) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.contato = contato;
		this.prox = null;
	}
	
	public Pessoa() {	
		
	}
	
	public Pessoa(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.contato = pessoa.getContato();
		this.senha = pessoa.getSenha();
		this.setProx(pessoa.getProx());
	}

	public Pessoa(String cpf, String telefone, String nome,
			String logradouro, String numero, String complemento,
			String bairro, String cidade, String senha, String uf) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.contato.setContato(logradouro, numero, complemento, bairro, cidade, uf, telefone);
		
		// TODO Auto-generated constructor stub
	}

	public String getNome() { return nome;}
	public void setNome(String nome) { this.nome = nome;}

	public String getCpf() { return cpf;}
	public void setCpf(String cpf) { this.cpf = cpf;}

	public Contato getContato() { return contato;}
	public void setContato(Contato contato) { this.contato = contato;}

	public String getSenha() { return this.senha;}
	public void setSenha(String senha) { this.senha = senha;}

	public Pessoa getProx() { return prox;}
	public void setProx(Pessoa prox) { this.prox = prox;}

}

