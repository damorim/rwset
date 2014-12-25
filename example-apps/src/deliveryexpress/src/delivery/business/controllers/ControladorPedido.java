package delivery.business.controllers;

import java.util.ArrayList;

import delivery.business.Cliente;
import delivery.business.Pedido;
import delivery.business.Produto;
import delivery.business.exceptions.client.ClienteNaoCadastradoException;
import delivery.business.exceptions.client.TelefoneObrigatorioException;
import delivery.business.exceptions.product.CodigoObrigatorioException;
import delivery.business.exceptions.product.ValorInvalidoException;
import delivery.business.exceptions.requests.ClienteObrigatorioException;
import delivery.business.exceptions.requests.DataObrigatorioException;
import delivery.business.exceptions.requests.ProdutosObrigatorioException;
import delivery.business.exceptions.requests.QuantidadeInvalidaException;
import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.interfaces.RepositorioPedidos;

public class ControladorPedido {

	private RepositorioPedidos repo;
	private RepositorioObjetos<Cliente> repClientes;

	/**
	 * Construtor
	 * 
	 * @param rep
	 *            Repositorio
	 * @param repClientes
	 *            Repositório dos objetos Cliente
	 */
	public ControladorPedido(RepositorioPedidos rep,
			RepositorioObjetos<Cliente> repClientes) {
		this.repo = rep;
		this.repClientes = repClientes;
	}

	/**
	 * Verifica a existência de um Pedido
	 * 
	 * @param codigo
	 *            String contendo codigo para producar pelo objeto Pedido
	 * @return boolean
	 */
	public boolean existePedido(String codigo) {
		if (repo.existe(codigo)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Filtra os pedidos pela data
	 * 
	 * @param dataInicial
	 *            data inicial
	 * @param dataFinal
	 *            data final
	 * @return ArrayList<Pedido>
	 */
	public ArrayList<Pedido> filtrarPedidosData(String dataInicial,
			String dataFinal) {
		return repo.vizualizarPeriodo(dataInicial, dataFinal);
	}

	/**
	 * Filtra os pedidos pelo código de um dos produtos
	 * 
	 * @param codigoProduto
	 *            código do produto a ser usado como filtro
	 * @return ArrayList<Pedido>
	 */
	public ArrayList<Pedido> filtrarPedidosProdutos(String codigoProduto) {
		return repo.vizualizar(codigoProduto);
	}

	/**
	 * Remove um pedido
	 * 
	 * @param codigo
	 *            código do pedido a ser removido
	 */
	public void removerPedido(String codigo) {
		if (repo.existe(codigo))
			repo.remover(repo.vizualizarEspecifico(codigo));
	}

	/**
	 * Retorna um pedido
	 * 
	 * @param codigo
	 *            código do pedido a ser retornado
	 * @return Pedido
	 */
	public Pedido retornaPedido(String codigo) {
		return repo.vizualizarEspecifico(codigo);
	}

	/**
	 * Adicionar pedido
	 * 
	 * @param pedido
	 *            Pedido a ser adicionado
	 * @throws DataObrigatorioException
	 *             Ocorre caso a data for nula
	 * @throws TelefoneObrigatorioException
	 *             Ocorre caso o telefone for nulo
	 * @throws QuantidadeInvalidaException
	 *             Ocorre caso a quantidade de produtos for diferente da
	 *             quantidade de produtos
	 * @throws ProdutosObrigatorioException
	 *             Ocorre caso a quantidade de produtos for nula
	 * @throws ClienteNaoCadastradoException
	 *             Ocorre caso o cliente não esteja cadastrado
	 */
	public void adicionarPedidos(Pedido pedido)
			throws DataObrigatorioException, TelefoneObrigatorioException,
			QuantidadeInvalidaException, ProdutosObrigatorioException,
			ClienteNaoCadastradoException {

		if (pedido.getDataPedido() == null
				|| pedido.getDataPedidoString().replaceAll(" ", "").length() == 10) {
			throw new DataObrigatorioException();
		} else if (pedido.getTelefoneCliente().isEmpty()) {
			throw new TelefoneObrigatorioException();
		} else if (pedido.getQuantidadeProdutos() == 0) {
			throw new QuantidadeInvalidaException();
		} else if (pedido.getProdutos() == null) {
			throw new ProdutosObrigatorioException();
		} else {

			if (!repClientes.existe(pedido.getTelefoneCliente())) {
				throw new ClienteNaoCadastradoException();
			} else if (pedido.getQuantidadeProdutos() != pedido.getProdutos().length) {
				throw new QuantidadeInvalidaException();
			} else {
				repo.adicionar(pedido);
			}
		}

	}

	/**
	 * Verifica a existência de um cliente
	 * 
	 * @param telefone
	 *            telefone do cliente
	 * @throws ClienteObrigatorioException
	 *             Ocorre caso o telefone for nulo
	 */
	public void verificarCliente(String telefone)
			throws ClienteObrigatorioException {
		if (telefone.isEmpty()) {
			throw new ClienteObrigatorioException();
		}
	}

	/**
	 * Verifica a validade de uma data
	 * 
	 * @param data
	 *            data a ser validado
	 * @throws DataObrigatorioException
	 *             Ocorre caso a data for nula
	 */
	public void verificarData(String data) throws DataObrigatorioException {
		if (data.isEmpty()) {
			throw new DataObrigatorioException();
		}
	}

	/**
	 * Verifica a quantidade de produtos
	 * 
	 * @param quantidade
	 *            quantidade de produtos
	 * @param produtos
	 *            array de objetos Produto
	 * @throws QuantidadeInvalidaException
	 *             Ocorre caso a quantidade de produtos for diferente da
	 *             quantidade de código de produto
	 */
	public void verificarQuantidade(int quantidade, String[] produtos)
			throws QuantidadeInvalidaException {
		if (quantidade < 0 || quantidade != produtos.length) {
			throw new QuantidadeInvalidaException();
		}
	}

	/**
	 * Verifica um valor (preço)
	 * 
	 * @param valor
	 *            valor do pedido
	 * @throws ValorInvalidoException
	 *             Ocorre caso o valor for menor ou igual a zero
	 */
	public void verificarValor(double valor) throws ValorInvalidoException {
		if (valor <= 0.0) {
			throw new ValorInvalidoException();
		}
	}

	/**
	 * Verifica o código do pedido
	 * 
	 * @param codigo
	 *            código do pedido
	 * @throws CodigoObrigatorioException
	 *             Ocorre caso o código for nulo
	 */
	public void verificarCodigo(String codigo)
			throws CodigoObrigatorioException {
		if (codigo.isEmpty()) {
			throw new CodigoObrigatorioException();
		}
	}

	/**
	 * Verifica os produtos
	 * 
	 * @param produtos
	 *            array de objetos Produto
	 * @throws ProdutosObrigatorioException
	 *             Ocorre caso o array for nulo
	 */
	public void verificarProdutos(Produto[] produtos)
			throws ProdutosObrigatorioException {
		if (produtos == null) {
			throw new ProdutosObrigatorioException();
		}
	}

	/**
	 * Retorna a quantidade de pedidos feitos por um cliente
	 * 
	 * @param telefone
	 *            telefone do cliente
	 * @return int
	 */
	public int quantidadePedidosTelefone(String telefone) {
		return repo.quantidadePedidosTelefone(telefone);
	}
}
