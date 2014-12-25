package delivery.data.repositories.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import delivery.business.Pedido;
import delivery.business.Produto;
import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.interfaces.RepositorioPedidos;
import delivery.data.iterator.Iterator;

public class RepositorioPedidoVector implements RepositorioObjetos<Pedido>,
		RepositorioPedidos {

	private Vector<Pedido> pedidos;

	/**
	 * Instancia um repositório em vector de objetos do tipo Pedido
	 */
	public RepositorioPedidoVector() {
		this.pedidos = new Vector<Pedido>();

	}

	public void adicionar(Pedido pedido) {
		this.pedidos.add(pedido);

	}

	public boolean existe(String codigo) {
		boolean achou = false;
		Iterator<Pedido> pedidosRealizados = new Iterator<Pedido>(pedidos);
		Pedido proxPedido;
		String codigoSTR;

		while (pedidosRealizados.hasNext() && achou == false) {
			proxPedido = pedidosRealizados.next();
			codigoSTR = "" + proxPedido.getCodidoPedido();

			if (codigoSTR.equalsIgnoreCase(codigo)) {
				achou = true;
			}
		}
		return achou;
	}

	public void remover(Pedido produto) {
		this.pedidos.remove(produto);
	}

	public ArrayList<Pedido> vizualizar(String codigoProduto) {
		Iterator<Pedido> todosPedidos = new Iterator<Pedido>(pedidos);
		ArrayList<Pedido> pedidosContemProduto = new ArrayList<Pedido>();

		Iterator<Produto> listaProdutos;
		Produto proxProduto;

		while (todosPedidos.hasNext()) {
			Pedido p = todosPedidos.next();
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
		String codigoProxPedido;
		while (pedidosRealizados.hasNext()) {
			proxPedido = pedidosRealizados.next();

			codigoProxPedido = proxPedido.getCodidoPedido() + "";

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
