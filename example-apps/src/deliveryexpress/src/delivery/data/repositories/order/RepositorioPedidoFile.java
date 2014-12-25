package delivery.data.repositories.order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import delivery.constants.Constants;
import delivery.business.Pedido;
import delivery.business.Produto;
import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.interfaces.RepositorioPedidos;
import delivery.data.iterator.Iterator;

public class RepositorioPedidoFile implements RepositorioObjetos<Pedido>,
		RepositorioPedidos {

	private String nomeArquivo;
	private ArrayList<Pedido> pedidos;

	/**
	 * Instancia um reposit�rio em arquivo de objetos do tipo Pedido
	 */
	public RepositorioPedidoFile() {

		this.nomeArquivo = Constants.registeredRequestsPath;

		this.pedidos = new ArrayList<Pedido>();
		File verificador = new File(this.nomeArquivo);

		if (verificador.exists()) {
			verificador.delete();
			// try {
			//
			// FileInputStream arquivo = new FileInputStream(nomeArquivo);
			// ObjectInputStream lerArquivo = new ObjectInputStream(arquivo);
			//
			// @SuppressWarnings("unchecked")
			// Iterator<Pedido> produtosGravados = (Iterator<Pedido>) lerArquivo
			// .readObject();
			// this.pedidos = produtosGravados.getObjetos();
			//
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
			// }
		}
	}

	public void adicionar(Pedido pedido) {
		this.pedidos.add(pedido);
		ObjectOutputStream escrever = null;
		try {
			FileOutputStream file = new FileOutputStream(nomeArquivo);
			escrever = new ObjectOutputStream(file);

			Iterator<Pedido> iterador = new Iterator<Pedido>(pedidos);

			escrever.writeObject(iterador);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				escrever.flush();
				escrever.close();
			} catch (IOException e) {
			}
		}
	}

	public boolean existe(String codigo) {
		Iterator<Pedido> PedidoCadastrados = new Iterator<Pedido>(pedidos);

		while (PedidoCadastrados.hasNext()) {
			if (String.valueOf(PedidoCadastrados.next().getCodidoPedido())
					.equalsIgnoreCase(codigo)) {
				return true;
			}
		}

		return false;
	}

	public void remover(Pedido pedido) {
		this.pedidos.remove(pedido);
		ObjectOutputStream reescrever = null;

		try {
			FileOutputStream file = new FileOutputStream(nomeArquivo);
			reescrever = new ObjectOutputStream(file);
			Iterator<Pedido> PedidosCadastrados = new Iterator<Pedido>(pedidos);

			while (PedidosCadastrados.hasNext()) {
				reescrever.writeObject(PedidosCadastrados.next());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reescrever.flush();
				reescrever.close();
			} catch (IOException e) {
			}
		}
	}

	public ArrayList<Pedido> vizualizar(String codigoProduto) {
		Iterator<Pedido> todosPedidos = new Iterator<Pedido>(this.pedidos);
		ArrayList<Pedido> pedidosContemProduto = new ArrayList<Pedido>();
		Pedido proxPedido = null;

		while (todosPedidos.hasNext()) {

			proxPedido = todosPedidos.next();
			Iterator<Produto> listaProdutos = new Iterator<Produto>(
					proxPedido.getProdutos());

			while (listaProdutos.hasNext()) {
				Produto proxProduto = listaProdutos.next();

				if (proxProduto.getCodigo().equalsIgnoreCase(codigoProduto)) {
					pedidosContemProduto.add(proxPedido);
				}
			}
		}

		return pedidosContemProduto;

	}

	public Pedido vizualizarEspecifico(String codigo) {
		Iterator<Pedido> pedidosRealizados = new Iterator<Pedido>(this.pedidos);
		Pedido pedidoCompativel = null;
		Pedido proxPedido;
		String codigoProxPedido;

		while (pedidosRealizados.hasNext()) {
			proxPedido = pedidosRealizados.next();

			codigoProxPedido = proxPedido.getCodidoPedido() + "";

			if (codigoProxPedido.equalsIgnoreCase(codigo)) {
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
