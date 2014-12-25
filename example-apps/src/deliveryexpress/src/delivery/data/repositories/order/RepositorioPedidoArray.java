package delivery.data.repositories.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.interfaces.RepositorioPedidos;
import delivery.data.iterator.Iterator;
import delivery.business.Pedido;
import delivery.business.Produto;

public class RepositorioPedidoArray implements RepositorioObjetos<Pedido>,	RepositorioPedidos {

	private Pedido[] pedidos;
	private int indice;
	private int capacidade;

	/**
	 * Instancia um reposit�rio em array de objetos do tipo Pedido
	 */
	public RepositorioPedidoArray() {

		this.capacidade = 50;
		this.pedidos = new Pedido[capacidade];
		this.indice = 0;

	}

	private int getIndice(String codigo) {
		int posicao = -1;

		String codigoSTR;
		for (int i = 0; i < this.indice; i++) {
			codigoSTR = "" + pedidos[i].getCodidoPedido();

			if (codigo.equalsIgnoreCase(codigoSTR)) {
				posicao = i;
			}
		}
		return posicao;

	}

	private void expandirArray() {
		this.capacidade += 10;

		Pedido[] novoArray = new Pedido[capacidade];

		for (int i = 0; i < pedidos.length; i++) {
			novoArray[i] = pedidos[i];
		}
		this.pedidos = novoArray;
	}

	public void adicionar(Pedido pedido) {

		if (this.indice == this.capacidade) {
			this.expandirArray();
		}

		this.pedidos[this.indice] = pedido;
		this.indice++;
	}

	public void remover(Pedido pedido) {

		String codigoSTR = "" + pedido.getCodidoPedido();

		int posicao = this.getIndice(codigoSTR);

		this.pedidos[posicao] = this.pedidos[indice - 1];
		indice--;
	}

	public boolean existe(String codigo) {

		int posicao = this.getIndice(codigo);

		if (posicao >= 0) {
			return true;
		} else {
			return false;
		}

	}

	public ArrayList<Pedido> vizualizar(String codigoProduto) {

		Iterator<Pedido> pedidosRealizados = new Iterator<Pedido>(pedidos);
		ArrayList<Pedido> pedidosContemProduto = new ArrayList<Pedido>();
		Iterator<Produto> listaProdutos;
		Produto proxProduto;

		while (pedidosRealizados.hasNext()) {

			Pedido p = pedidosRealizados.next();
			listaProdutos = new Iterator<Produto>(p.getProdutos());

			while (listaProdutos.hasNext()) {
				proxProduto = listaProdutos.next();

				if (proxProduto.getCodigo().equalsIgnoreCase(codigoProduto)) {
					pedidosContemProduto.add(p);
				}
			}

		}

		return pedidosContemProduto;
	}

	public Pedido vizualizarEspecifico(String codigoPedido) {
		Iterator<Pedido> pedidosRealizados = new Iterator<Pedido>(pedidos);
		Pedido pedidoCompativel = null;
		Pedido proxPedido;

		while (pedidosRealizados.hasNext()) {
			proxPedido = pedidosRealizados.next();

			String codigoProxPedido = proxPedido.getCodidoPedido() + "";

			if (codigoProxPedido.equalsIgnoreCase(codigoPedido)) {
				pedidoCompativel = proxPedido;
			}

		}

		return pedidoCompativel;
	}

	public ArrayList<Pedido> vizualizarPeriodo(String dataInicial,
			String dataFinal) {
		Iterator<Pedido> pedidosRealizados = new Iterator<Pedido>(this.pedidos);
		ArrayList<Pedido> pedidosPeriodo = new ArrayList<Pedido>();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataComeco = null;
		Date dataFim = null;
		try {
			if (dataInicial.isEmpty()) {
				dataInicial = "01/01/2000";
			}
			if (dataFinal.isEmpty()) {
				dataFinal = "31/12/2099";
			}
			dataComeco = formato.parse(dataInicial);
			dataFim = formato.parse(dataFinal);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Pedido proxPedido;
		Date dataPedido;

		while (pedidosRealizados.hasNext()) {
			proxPedido = pedidosRealizados.next();
			dataPedido = proxPedido.getDataPedido();

			if (dataComeco.before(dataPedido) && dataFim.after(dataPedido)) {
				pedidosPeriodo.add(proxPedido);
			} else if (dataComeco.compareTo(dataPedido) == 0
					|| dataFim.compareTo(dataPedido) == 0) {
				pedidosPeriodo.add(proxPedido);
			}
		}

		return pedidosPeriodo;
	}

	public int quantidadePedidosTelefone(String telefone) {
		Iterator<Pedido> todosPedidos = new Iterator<Pedido>(this.pedidos);

		Pedido proxPedido = null;
		int quantidadePedidosTelefone = 0;
		while (todosPedidos.hasNext()) {
			proxPedido = todosPedidos.next();
			if (proxPedido.getTelefoneCliente().equalsIgnoreCase(telefone)) {
				quantidadePedidosTelefone++;
			}
		}

		return quantidadePedidosTelefone;
	}

}
