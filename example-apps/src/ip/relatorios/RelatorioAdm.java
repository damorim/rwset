package relatorios;

import dados.interfaces.IRepositorioPedido;
import dados.IteratorClasse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


@SuppressWarnings("rawtypes")
public class RelatorioAdm implements Iterable {

	private String nome;
	private File arquivo;
	private IteratorClasse<String> informacoesRelatorio;
	private IteratorClasse<String> retornavel;
	private String nomeArquivo;



public RelatorioAdm (IRepositorioPedido repositorio, String nome) throws IOException {
		this.nome = nome;
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dataAtual = new Date();
		String auxiliar = formatador.format(dataAtual);
		String nomeArquivo = "Relatorio de Administradores " + auxiliar + ".txt";
		this.arquivo = new File (nomeArquivo);
		this.nomeArquivo = nomeArquivo;
		this.arquivo.createNewFile();
		this.escreveRelatorio();
	}

private void escreveRelatorio(){

		Vector<String> linhas = new Vector<String>();
		String primeiraLinha = "Nome do Administrador: " + this.nome;

		linhas.add(primeiraLinha);

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
