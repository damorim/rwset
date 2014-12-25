package delivery.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int codigoUltimoPedido = 1246;
	private int codidoPedido;
	private String telefoneCliente;
	private Produto[] produtos;
	private int quantidadeProdutos;
	private BigDecimal valor;
	private Date dataPedido;

	/**
	 * Construtor padrão
	 * 
	 * @param telefoneCliente
	 *            telefone do cliente associado ao pedido
	 * @param produtos
	 *            array de objetos Produto associados ao pedido
	 * @param quantidadeProdutos
	 *            quantidade de produtos do pedido
	 * @param valor
	 *            valor do pedido
	 * @param data
	 *            data do pedido
	 */
	public Pedido(String telefoneCliente, Produto[] produtos,
			int quantidadeProdutos, BigDecimal valor, String data) {

		Pedido.codigoUltimoPedido += 1;
		this.codidoPedido = Pedido.codigoUltimoPedido;
		this.telefoneCliente = telefoneCliente;
		this.produtos = produtos;
		this.valor = valor;
		this.quantidadeProdutos = quantidadeProdutos;

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataPedido = formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construtor espcial para adicionar os pedidos da planilha, que já possuem
	 * um código associado
	 * 
	 * @param codigoPedido
	 *            int contendo o codigo do pedido
	 * @param telefoneCliente
	 *            String contendo o telefone do cliente associado ao pedido
	 * @param produtos
	 *            Array de objetos Produto associados ao pedido
	 * @param quantidadeProdutos
	 *            int contendo a quantidade de produtos do pedido
	 * @param valor
	 *            BigDecimal contendo o valor do pedido
	 * @param data
	 *            String contendo a data do pedido
	 */
	public Pedido(int codigoPedido, String telefoneCliente, Produto[] produtos,
			int quantidadeProdutos, BigDecimal valor, String data) {

		this.codidoPedido = codigoPedido;
		this.telefoneCliente = telefoneCliente;
		this.produtos = produtos;
		this.valor = valor;
		this.quantidadeProdutos = quantidadeProdutos;

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataPedido = formato.parse(data);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
	}

	/*
	 * Getters e setters
	 */
	
	public Date getDataPedido() {
		return this.dataPedido;
	}

	public String getDataPedidoString() {
		return new SimpleDateFormat().format(dataPedido);
	}

	public String getDataPedidoStringFormatado() {
		return new SimpleDateFormat("dd/MM/yyyy").format(dataPedido);
	}

	public int getCodidoPedido() {
		return codidoPedido;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public Produto[] getProdutos() {
		return produtos;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public BigDecimal getValor() {
		return valor;
	}

}
