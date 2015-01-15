package dados.entidade.interfaces;

import dados.entidade.Cliente;
import dados.entidade.Contato;

public interface ICliente { //O int 0 seria para caso um sistema de login identifica-lo como cliente
	final static int acesso = 0;
	
	public String getNome();
	public String getCpf();
	public String getSenha();
	public Contato getContato();
	public void setNome(String nome);
	public void setCpf(String cpf);
	public void setContato(Contato contato);
	public void setSenha(String senha); 
	public void setProx(Cliente cliente);
	public Cliente getProx();
}