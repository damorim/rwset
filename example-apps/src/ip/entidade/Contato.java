package dados.entidade;

import java.io.Serializable;

public class Contato implements Serializable{

	private static final long serialVersionUID = -1548581281174377261L;
	private String telefone, numero, rua, bairro, cidade, uf, complemento;

	public Contato(String rua, String numero, String complemento, String bairro, 
                String cidade, String uf, String telefone) {
		this.numero = numero;
		this.rua = rua;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefone = telefone;
		this.uf = uf;

	}
	
	public void setContato(String rua, String numero, String complemento, String bairro, 
                String cidade, String uf, String telefone) {
		this.numero = numero;
		this.rua = rua;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefone = telefone;
		this.uf = uf;
	}
	
	public String getTelefone() { return telefone;}
	public void setTelefone(String telefone) { this.telefone = telefone;}

	public String getNumero() { return numero;}
	public void setNumero(String numero) { this.numero = numero;}

	public String getRua() { return rua;}
	public void setRua(String rua) { this.rua = rua;}

	public String getBairro() { return bairro;}
	public void setBairro(String bairro) { this.bairro = bairro;}

	public String getCidade() { return cidade;}
	public void setCidade(String cidade) { this.cidade = cidade;}

	public String getComplemento() { return complemento;}
	public void setComplemento(String complemento) { this.complemento = complemento;}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
