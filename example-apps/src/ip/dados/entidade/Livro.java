package dados.entidade;

import java.io.Serializable;
import java.math.BigDecimal;


public class Livro extends Produto implements Serializable{

	private static final long serialVersionUID = 2476645651903968754L;
	private String nomeAutor;
	private String editora;
	private String anoPublicacao;
	Livro prox;

	public Livro(String codigo, String nome, String descricao, BigDecimal valor, String nomeAutor, String editora, String anoPublicacao) {
		super(codigo, nome, descricao, valor);
		this.nomeAutor = nomeAutor;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
		this.prox = null;
	}

	

	public Livro(Produto produto) {
		super(produto);
		this.codigo = produto.getCodigo();
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.prox = (Livro) produto.getProx();
	}



	public Livro() {
	
	}



	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public Produto getProx() {
		return prox;
	}

	public void setProx(Produto prox) {
		this.prox = (Livro) prox;
	}
}