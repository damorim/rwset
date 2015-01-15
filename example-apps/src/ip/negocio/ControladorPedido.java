package negocio;

import dados.IteratorClasse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

import dados.entidade.Cliente;
import dados.entidade.Contato;
import dados.entidade.Pedido;
import dados.entidade.Produto;
import dados.interfaces.IRepositorioPedido;
import exceptions.pedido.DatasInvalidasException;
import exceptions.pedido.PedidoNaoEncontradoException;
import exceptions.pedido.QuantidadeProdutosInvalidaException;
import exceptions.produto.ProdutoNaoEncontradoException;

public class ControladorPedido {
	private IRepositorioPedido repositorio;

	public ControladorPedido(IRepositorioPedido repositorio) {
		this.repositorio = repositorio;
	}

	public void adicionar(String cpf, String telefone, String nome, String logradouro, String numero, String complemento,
		String bairro, String cidade, String uf, String codigo, Produto[] produtos, Date data,
		String quantidadeProdutosStr, String senha) throws PedidoNaoEncontradoException, QuantidadeProdutosInvalidaException, ProdutoNaoEncontradoException {
		int quantidadeProdutos = 0;
		try {
			quantidadeProdutos = Integer.parseInt(quantidadeProdutosStr);
		} catch (NumberFormatException e) {
			throw new QuantidadeProdutosInvalidaException();
		}

		if (quantidadeProdutos != produtos.length) {
			throw new QuantidadeProdutosInvalidaException();
		}

		if (quantidadeProdutos > 7) {
			throw new QuantidadeProdutosInvalidaException();
		}

		Contato contato = new Contato(logradouro, numero, complemento, bairro, cidade, uf, telefone);
		Cliente cliente = new Cliente(cpf, nome, senha, contato);

		BigDecimal taxa = this.calculaTaxa(cliente, produtos.length);
		BigDecimal valor = new BigDecimal(0.0);
		valor = valor.add(taxa);
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i] != null) {
				valor = valor.add(produtos[i].getPreco());
			} else {
				throw new ProdutoNaoEncontradoException();
			}
		}

		boolean temDesconto = this.temDesconto(cliente);
		if (temDesconto) {
			double menorValor = (produtos[0].getPreco()).doubleValue();
			for (int i = 0; i < produtos.length; i++) {
				if ((produtos[i].getPreco()).doubleValue() < menorValor) {
					menorValor = (produtos[i].getPreco().doubleValue());
				}
			}

			valor = valor.subtract(new BigDecimal(menorValor));
		}

		Pedido pedido = new Pedido(codigo, cliente, produtos, data, produtos.length, valor);
		this.repositorio.adicionar(pedido);
	}

	@SuppressWarnings("null")
	private BigDecimal calculaTaxa(Cliente cliente, int tamanhoPedido) {
		BigDecimal taxa = null;
		double bonus;
		String bairro = cliente.getContato().getBairro();
		boolean desconto = false;
		
		String[] bairrosDesconto = {"Aflitos", "Espinheiro", "Tamarineira", "Jaqueira",
				"Torre", "Parnamirim", "Casa Forte", "Jabotatão", "Afogados", "Barro", 
				"Boa Viagem", "IPSEP"};
		
		for (int i = 0; i < bairrosDesconto.length; i++) {
			if (bairro.equalsIgnoreCase(bairrosDesconto[i])) {
				desconto = true;
			}
		}
		if (desconto) {
			taxa = new BigDecimal(3.50);
		} else {
			taxa = new BigDecimal(7.00);
		}
		if (tamanhoPedido > 5) {
			bonus = (tamanhoPedido - 5) * 0.1;
			taxa = taxa.subtract(taxa.multiply(new BigDecimal(bonus)));
		}
		return taxa;
	}

	private boolean temDesconto(Cliente cliente) {
		IteratorClasse<Pedido> iterator = repositorio.iterator();
		int contador = 0;
		boolean temDesconto = false;

		while (iterator.hasNext()) {
			Pedido auxiliar = iterator.next();
			if (auxiliar.getCliente().getCpf().equals(cliente.getCpf())) {
				contador++;
			}
		}

		if (contador % 5 == 0 && contador != 0) {
			temDesconto = true;
		}

		return temDesconto;
	}

	public void remover(String codigo) throws PedidoNaoEncontradoException {
		this.buscaCodigo(codigo);
		this.repositorio.remover(codigo);
	}
	private Pedido buscaCodigo(String codigo) throws PedidoNaoEncontradoException {
		Pedido resposta = null;
		IteratorClasse<Pedido> iterator = this.repositorio.iterator();
		while (iterator.hasNext()) {
			Pedido auxiliar = iterator.next();
			if (auxiliar.getCodigo().equals(codigo)) {
				resposta = auxiliar;
			}
		}
		if (resposta == null) {
			throw new PedidoNaoEncontradoException();
		}
		return resposta;
	}

	public IteratorClasse<Pedido> getListaPedidos(Date dataInicio, Date dataFim)
	throws DatasInvalidasException {
		if (!(dataInicio == null || dataFim == null)) {
			if (!dataInicio.before(dataFim)) {
				throw new DatasInvalidasException();
			}
		}
		IteratorClasse<Pedido> iterator = this.repositorio.iterator();
		Vector<Pedido> pedidos = new Vector<Pedido>();
		while (iterator.hasNext()) {
			Pedido auxiliar = iterator.next();
			if ((dataInicio == null || auxiliar.getData().after(dataInicio))
					&& (dataFim == null || auxiliar.getData().before(dataFim))) {
				pedidos.add(auxiliar);
			}
		}
		IteratorClasse<Pedido> retorno = new IteratorClasse<Pedido>(pedidos);
		return retorno;
	}

public IRepositorioPedido getRepositorio() {

	return this.repositorio;
}

}