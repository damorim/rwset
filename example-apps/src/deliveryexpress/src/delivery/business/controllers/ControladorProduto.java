package delivery.business.controllers;

import java.util.ArrayList;

import delivery.business.Produto;
import delivery.business.exceptions.client.NomeObrigatorioException;
import delivery.business.exceptions.product.CodigoJaExistenteException;
import delivery.business.exceptions.product.CodigoObrigatorioException;
import delivery.business.exceptions.product.ProdutoNaoCadastradoException;
import delivery.business.exceptions.product.TamanhoObrigatorioException;
import delivery.business.exceptions.product.ValorInvalidoException;
import delivery.business.exceptions.product.ValorObrigatorioException;
import delivery.data.interfaces.RepositorioObjetos;

public class ControladorProduto {

	private RepositorioObjetos<Produto> repo;

	public ControladorProduto(RepositorioObjetos<Produto> rep) {
		this.repo = rep;
	}

	/**
	 * Filtra os produtos
	 * 
	 * @param filtro
	 *            String que será usada para filtrar
	 * @return ArrayList<Produto>
	 */
	public ArrayList<Produto> filtrarProdutos(String filtro) {
		return repo.vizualizar(filtro);
	}

	/**
	 * Verifica a existência de um produto
	 * 
	 * @param codigo
	 *            código do produto
	 * @return boolean
	 */
	public boolean existeProduto(String codigo) {
		return repo.existe(codigo);
	}

	/**
	 * Retorna um produto específico
	 * 
	 * @param codigo
	 *            código do produto
	 * @return Produto
	 */
	public Produto retornaProduto(String codigo) {
		return repo.vizualizarEspecifico(codigo);
	}

	/**
	 * Remove um produto
	 * 
	 * @param codigo
	 *            código do produto
	 * @throws ProdutoNaoCadastradoException
	 *             Ocorre se o produto não estiver cadastrado
	 */
	public void removerProduto(String codigo)
			throws ProdutoNaoCadastradoException {
		if (existeProduto(codigo)) {
			repo.remover(retornaProduto(codigo));
		} else {
			throw new ProdutoNaoCadastradoException();
		}
	}

	/**
	 * Adiciona um Produto
	 * 
	 * @param produto
	 *            produto que será adicionado
	 * @throws CodigoJaExistenteException
	 *             Ocorre se o ja houver um produto cadastrado com esse código
	 * @throws ValorInvalidoException
	 *             Ocorre se o valor (preço) do produto for menor ou igual a
	 *             zero
	 * @throws ValorObrigatorioException
	 *             Ocorre se o valor for nulo
	 * @throws CodigoObrigatorioException
	 *             Ocorre se o código for nulo
	 * @throws NomeObrigatorioException
	 *             Ocorre se o nome for nulo
	 * @throws TamanhoObrigatorioException
	 *             Ocorre se o tamanho for nulo
	 */
	public void adicionarProduto(Produto produto)
			throws CodigoJaExistenteException, ValorInvalidoException,
			ValorObrigatorioException, CodigoObrigatorioException,
			NomeObrigatorioException, TamanhoObrigatorioException {

		if (produto.getCodigo().isEmpty()) {
			throw new CodigoObrigatorioException();
		} else if (produto.getNome().isEmpty()) {
			throw new NomeObrigatorioException();
		} else if (produto.getTamanho().isEmpty()) {
			throw new TamanhoObrigatorioException();
		} else if (produto.getValor().toPlainString().isEmpty()) {
			throw new ValorObrigatorioException();
		} else if (produto.getValor().doubleValue() <= 0.0) {
			throw new ValorInvalidoException();
		} else {
			if (existeProduto(produto.getCodigo())) {
				throw new CodigoJaExistenteException();
			} else {
				repo.adicionar(produto);
			}
		}
	}

	/**
	 * Verifica o nome do produto
	 * 
	 * @param nome
	 *            nome do produto
	 * @throws NomeObrigatorioException
	 *             Ocorre se o nome for nulo
	 */
	public void verificarNome(String nome) throws NomeObrigatorioException {
		if (nome.isEmpty()) {
			throw new NomeObrigatorioException();
		}
	}

	/**
	 * Verifica o código de um produto
	 * 
	 * @param codigo
	 *            código do produto
	 * @throws CodigoObrigatorioException
	 *             Ocorre se ocorre se o código for nulo
	 */
	public void verificarCodigo(String codigo)
			throws CodigoObrigatorioException {
		if (codigo.isEmpty()) {
			throw new CodigoObrigatorioException();
		}
	}

	/**
	 * Verifica o tamanho do produto
	 * 
	 * @param tamanho
	 *            tamanho do produto
	 * @throws TamanhoObrigatorioException
	 *             Ocorre se o tamanho for nulo
	 */
	public void verificarTamanho(String tamanho)
			throws TamanhoObrigatorioException {
		if (tamanho.isEmpty()) {
			throw new TamanhoObrigatorioException();
		}
	}

	/**
	 * Verifica o valor (preço do produto)
	 * 
	 * @param valor
	 *            valor do produto
	 * @throws ValorObrigatorioException
	 *             Ocorre se o valor for nulo
	 */
	public void verificarValor(double valor) throws ValorObrigatorioException {
		if (String.valueOf(valor).isEmpty()) {
			throw new ValorObrigatorioException();
		}
	}

}
