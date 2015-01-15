package dados.entidade.interfaces;

import dados.entidade.Administrador;
import dados.entidade.Contato;
import dados.entidade.Pessoa;

public interface IAdm {  //O int 1 seria para caso um sistema de login identifica-lo como admin
	final static int acesso = 1;
	
	public String getNome();
	public String getCpf();
	public String getSenha();
	public Contato getContato();
	public void setNome(String nome);
	public void setCpf(String cpf);
	public void setContato(Contato contato);
	public void setSenha(String senha); 
	public void setProx(Pessoa administrador);
	public Administrador getProx();
}
