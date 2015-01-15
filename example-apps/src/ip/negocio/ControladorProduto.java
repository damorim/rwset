package negocio;

import dados.IteratorClasse;
import java.math.BigDecimal;
import java.util.Vector;

import dados.entidade.Livro;
import dados.entidade.Produto;
import dados.interfaces.IRepositorioProduto;
import exceptions.CampoNaoPreenchidoException;
import exceptions.CodigoInvalidoException;
import exceptions.produto.AnoInvalidoException;
import exceptions.produto.ProdutoNaoEncontradoException;
import exceptions.produto.ValorInvalidoException;

public class ControladorProduto {
	private IRepositorioProduto repositorio;

	public ControladorProduto(IRepositorioProduto repositorio) {
		this.repositorio = repositorio;
	}

	public void adicionar(String codigo, String nome, String descricao, String valor, String nomeAutor, String editora, String anoPublicacao) throws ValorInvalidoException,
	CampoNaoPreenchidoException, CodigoInvalidoException, AnoInvalidoException {
		if (valor.trim().length() == 0 || valor == null) {
			throw new CampoNaoPreenchidoException();
		}

		boolean valorInvalido = false;
		int quantidadePontos = 0;
		for (int i = 0; i < valor.length(); i++) {
			if (("" + valor.charAt(i)).matches("\\.")) {
				quantidadePontos++;
			}
			if (quantidadePontos > 1) {
				valorInvalido = true;
			}
			if (!(("" + valor.charAt(i)).matches("\\d{1}") || (("" + valor.charAt(i)).matches("\\.")))) {
				valorInvalido = true;
			}
		}

		if (valorInvalido) {
			throw new ValorInvalidoException();
		}

		boolean anoInvalido = true;

		if(anoPublicacao.matches("\\d{4}"))
			anoInvalido = false;

		if(anoInvalido)
			throw new AnoInvalidoException();
		
		double valorAux = Double.parseDouble(valor);

		BigDecimal valorBig = new BigDecimal(valorAux);
		if (codigo.trim().length() == 0 || codigo == null) {
			throw new CampoNaoPreenchidoException();
		} else if (nome.trim().length() == 0 || nome == null) {
			throw new CampoNaoPreenchidoException();
		} else {

			this.validaCodigo(codigo);
			Produto produto;
			
			produto = new Livro(codigo, nome, descricao, valorBig, nomeAutor, editora, anoPublicacao);
			this.repositorio.adicionar(produto);

		}

	}

	public void remover(String codigo) throws ProdutoNaoEncontradoException {

		this.buscaCodigo(codigo);
		this.repositorio.remover(codigo);

	}

	public Produto buscaCodigo(String codigo) throws ProdutoNaoEncontradoException {

		Produto resposta = null;
		IteratorClasse<Produto> iterator = null;

		iterator = this.repositorio.iterator();

		while (iterator.hasNext()) {
			Produto auxiliar = iterator.next();
			if (auxiliar.getCodigo().equals(codigo)) {
				resposta = auxiliar;
			}
		}

		if (resposta == null) {
			throw new ProdutoNaoEncontradoException();
		} else {
			return resposta;
		}
	}

	public IteratorClasse<Produto> buscaParteCodigo(String codigo) throws ProdutoNaoEncontradoException {
		IteratorClasse<Produto> iterator = this.repositorio.iterator();
		Vector<Produto> resposta = new Vector<Produto>();
		while (iterator.hasNext()) {
			Produto auxiliar = iterator.next();
			if (auxiliar.getCodigo().contains(codigo)) {
				resposta.add(auxiliar);
			}
		}

		if (resposta.isEmpty()) {
			throw new ProdutoNaoEncontradoException();
		}

		IteratorClasse<Produto> retorno = new IteratorClasse<Produto>(resposta);
		return retorno;
	}

	public IteratorClasse<Produto> buscaNome(String nome) throws ProdutoNaoEncontradoException {
		IteratorClasse<Produto> iterator = this.repositorio.iterator();
		Vector<Produto> resposta = new Vector<Produto>();

		while (iterator.hasNext()) {
			Produto auxiliar = iterator.next();
			if (auxiliar.getNome().toLowerCase().contains(nome.toLowerCase())) {
				resposta.add(auxiliar);
			}
		}

		if (resposta.isEmpty()) {
			throw new ProdutoNaoEncontradoException();
		}

		IteratorClasse<Produto> retorno = new IteratorClasse<Produto>(resposta);
		return retorno;
	}

	private void validaCodigo(String codigo) throws CodigoInvalidoException {
		IteratorClasse<Produto> iterator = null;

		iterator = this.repositorio.iterator();

		while (iterator.hasNext()) {
			Produto auxiliar = iterator.next();
			if (auxiliar.getCodigo().equals(codigo)) {
				throw new CodigoInvalidoException();
			}
		}
	}
}