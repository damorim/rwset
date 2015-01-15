package dados.entidade;

import java.io.Serializable;
import java.math.BigDecimal;

import dados.entidade.interfaces.IProd;

public class Produto implements IProd, Serializable {
	
	private static final long serialVersionUID = 2196137339585578884L;
	protected String codigo;
	protected String nome;
	protected String descricao;
	protected BigDecimal preco;
	protected int quantidade;
	Produto prox;

	public Produto(String codigo, String nome, String descricao, BigDecimal preco) {
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.prox = null;
	}
	
	public Produto(Produto produto) {
		this.codigo = produto.getCodigo();
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.prox = produto.getProx();
	}
	
	public Produto() {
		
	}

	public void setNome(String nome) { this.nome = nome;}
	public String getNome() { return nome;}

	public void setCodigo(String codigo) { this.codigo = codigo;}
	public String getCodigo() { return codigo;}

	public void setDescricao(String descricao) { this.descricao = descricao;}
	public String getDescricao() { return descricao;}
	
	public void setPreco(BigDecimal preco) { this.preco = preco;}
	public BigDecimal getPreco() { return preco;}


	public Produto getProx() { return prox;}
	public void setProx(Produto prox) { this.prox = prox;}

}