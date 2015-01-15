package relatorios;

import dados.entidade.Pedido;
import dados.interfaces.IRepositorioPedido;
import dados.IteratorClasse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class RelatorioCliente implements Iterable {

	private IteratorClasse<Pedido> iteratorPedido;
	private Date dataInicial;
	private Date dataFinal;
	private String nome;
	private File arquivo;
	private IteratorClasse<String> informacoesRelatorio;
	private IteratorClasse<String> retornavel;
	private String nomeArquivo;

	public RelatorioCliente (IRepositorioPedido repositorioPedidos, Date dataInicial,
			Date dataFinal, String nome) throws IOException {
		this.iteratorPedido = repositorioPedidos.iterator();
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.nome = nome;
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dataAtual = new Date();
		String auxiliar = formatador.format(dataAtual);
		String nomeArquivo = "Relatorio de Clientes " + auxiliar + ".txt";
		this.arquivo = new File (nomeArquivo);
		this.nomeArquivo = nomeArquivo;
		this.arquivo.createNewFile();
		this.escreveRelatorio();

	}

	private void escreveRelatorio(){

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicialStr, dataFinalStr = "";
		if (this.dataInicial == null){
			dataInicialStr = "__/__/____";
		} else {
			dataInicialStr = formatador.format(this.dataInicial);
		}
		if (this.dataFinal == null){
			dataFinalStr = "__/__/____";
		} else {
			dataFinalStr = formatador.format(this.dataFinal);
		}
		Vector<String> linhas = new Vector<String>();
		String primeiraLinha = "Nome do Cliente: " + this.nome;
		String segundaLinha = "Período de Compras: " + dataInicialStr + " - " + dataFinalStr;

		Vector<Pedido> dados = new Vector<Pedido>();
		while (this.iteratorPedido.hasNext()){
			Pedido auxiliar = this.iteratorPedido.next();
			if (auxiliar.getCliente().getNome().toLowerCase().
					contains(this.nome.toLowerCase())){
				if ((this.dataInicial == null || auxiliar.getData().
						after(this.dataInicial)) && (this.dataFinal == null ||
								auxiliar.getData().before(this.dataFinal))){
					dados.add(auxiliar);
				}
			}
		}


		Collections.sort(dados);

		linhas.add(primeiraLinha);
		linhas.add(segundaLinha);

		for (int i = 0; i<dados.size(); i++){
			String dataPedido = formatador.format(dados.elementAt(i).getData());
			String nomePedido = dados.elementAt(i).getCliente().getNome();
			String quantidadePedido = "";
			if (dados.elementAt(i).getQuantidade() == 1){
				quantidadePedido = "" + dados.elementAt(i).getQuantidade() + " Produto";
			} else {
				quantidadePedido = "" + dados.elementAt(i).getQuantidade() + " Produtos";
			}
			String codigoPedido = "";
			for (int j = 0; j<dados.elementAt(i).getQuantidade(); j++){
				codigoPedido = codigoPedido + "C: "
				+ dados.elementAt(i).getProdutos()[j].getCodigo() + " | ";
			}
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String valorPedido = nf.format(dados.elementAt(i).getPreco().doubleValue());
			String linha = dataPedido + " | " + nomePedido + " | " 
			+ quantidadePedido + " | " + codigoPedido + valorPedido;
			linhas.add(linha);
		}

		this.informacoesRelatorio = new IteratorClasse<String>(linhas);
		this.retornavel = new IteratorClasse<String>(linhas);

	}

	public void escreveArquivo() throws IOException{
		PrintWriter writer = new PrintWriter (new FileWriter (this.nomeArquivo));
		while (this.informacoesRelatorio.hasNext()){
			String linha = this.informacoesRelatorio.next();
			writer.println(linha);
		}
		writer.close();
	}

	@Override
	public IteratorClasse<String> iterator(){
		return this.retornavel;
	}
}