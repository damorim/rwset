package dados.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import dados.entidade.interfaces.IPedido;

public class Pedido implements Serializable, IPedido, Comparable<Pedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3890860726557093419L;
	private String codigo;
	private Cliente cliente;
	private Produto[] produtos;
	private int quantidade;
	private BigDecimal preco;
	private Date data;
	private Pedido prox;

	public Pedido(String codigo, Cliente cliente, Produto[] produtos, Date data, int quantidade, BigDecimal preco) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.produtos = produtos;
		this.quantidade = quantidade;
		this.preco = preco;
		this.data = data;
		this.prox = null;
	}
	
	public Pedido(Pedido produto) {
		super();
		this.codigo = produto.getCodigo();
		this.cliente = produto.getCliente();
		this.produtos = produto.getProdutos();
		this.quantidade = produto.getQuantidade();
		this.preco = produto.getPreco();
		this.data = produto.getData();
		this.prox = produto.getProx();
	}

	public Pedido() {
		
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Produto[] getProdutos() {
		return produtos;
	}
	public void setProdutos(Produto[] produtos) {
		this.produtos = produtos;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	//Precisamos do compareTo para quando for ordenar o vector pra gerar o relatório

	public int compareTo(Pedido pedido) {
		int resposta = 1;
		if(this.data.before(pedido.data))
			resposta = -1;
		else if(this.data.equals(pedido.data))
			resposta = 0;

		return resposta;

	}

	public void setData(Date data) {
		this.data = data;		
	}

	public Date getData() {
		return data;
	}

	public Pedido getProx() {
		return prox;
	}

	public void setProx(Pedido prox) {
		this.prox = prox;
	}

}