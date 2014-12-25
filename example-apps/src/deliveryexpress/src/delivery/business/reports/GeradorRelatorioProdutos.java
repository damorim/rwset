package delivery.business.reports;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import delivery.constants.Constants;
import delivery.business.Cliente;
import delivery.business.Pedido;
import delivery.business.controllers.ControladorCliente;
import delivery.business.controllers.ControladorPedido;

public class GeradorRelatorioProdutos {

	private ControladorPedido pedidos;
	private ControladorCliente clientes;
	private String codigoProduto;
	private String dataInicial;
	private String dataFinal;

	public GeradorRelatorioProdutos(ControladorCliente controlClientes,
			ControladorPedido controlPedidos, String codigo,
			String dataInicial, String dataFinal) {

		this.clientes = controlClientes;
		this.pedidos = controlPedidos;
		this.codigoProduto = codigo;
		this.dataFinal = dataFinal;
		this.dataInicial = dataInicial;

	}

	public ArrayList<String> gerarRelatorio() {
		ArrayList<String> relatorioFinal = new ArrayList<String>();
		String relatorio = "C�digo Identificador: " + this.codigoProduto;
		relatorioFinal.add(relatorio);

		ArrayList<Cliente> todosClientes = clientes.filtrarClientes("");
		ArrayList<Pedido> pedidosContemProduto = pedidos
				.filtrarPedidosProdutos(codigoProduto);
		ArrayList<Pedido> pedidosPeriodo = pedidos.filtrarPedidosData(
				dataInicial, dataFinal);

		for (Pedido pedidoQueContemProduto : pedidosContemProduto) {
			for (Pedido pedidoDoPeriodo : pedidosPeriodo) {
				if (pedidoQueContemProduto.getCodidoPedido() == pedidoDoPeriodo.getCodidoPedido()) {
					for (Cliente cliente : todosClientes) {
						if (cliente.gettelefone().equalsIgnoreCase(pedidoQueContemProduto.getTelefoneCliente())) {

							String data = pedidoQueContemProduto
									.getDataPedidoStringFormatado();
							String nome = cliente.getNome();
							int codigoPedido = pedidoQueContemProduto
									.getCodidoPedido();
							double valor = pedidoQueContemProduto.getValor()
									.doubleValue();

							relatorioFinal.add(formatarRelatorio(data, nome,
									codigoPedido, valor));
						}
					}

				}
			}
		}

		/*
		 * Escrita do relat�rio num arquivo
		 */
		FileWriter outFile = null;
		PrintWriter out = null;
		try {
			outFile = new FileWriter(Constants.relatoriosPath
					+ this.gerarNomeRelatorio());
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String s : relatorioFinal) {
			out.println(s);
		}
		out.close();

		return relatorioFinal;
	}

	private String gerarNomeRelatorio() {
		GregorianCalendar calendar = new GregorianCalendar();

		int mes = calendar.get(Calendar.MONTH) + 1;

		String nomeRelatorio = "Relatorio Produtos "
				+ calendar.get(Calendar.YEAR) + "" + mes + ""
				+ calendar.get(Calendar.DAY_OF_MONTH) + ""
				+ calendar.get(Calendar.HOUR_OF_DAY) + ""
				+ calendar.get(Calendar.MINUTE) + ""
				+ calendar.get(Calendar.SECOND) + ".txt";

		return nomeRelatorio;
	}

	private String formatarRelatorio(String data, String nomeCliente,
			int codigoPedido, double valorPedido) {
		String relatorio = "";

		relatorio += data + " | CI: " + codigoPedido + " | R$ " + valorPedido
				+ " | " + nomeCliente;

		return relatorio;
	}

}
