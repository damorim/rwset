package testes;

import java.io.IOException;
import dados.excel.exception.CellNumberFormatException;
import dados.excel.exception.CellStringFormatException;
import dados.excel.exception.IOExcelException;
import dados.excel.exception.SheetNotFoundException;
import exceptions.CampoNaoPreenchidoException;
import exceptions.CodigoInvalidoException;
import exceptions.NomeInvalidoException;
import exceptions.TipoRepositorioInvalidoException;
import exceptions.administrador.AdmNaoEncontradoException;
import exceptions.administrador.CpfInvalidoException;
import exceptions.administrador.CpfJaExisteException;
import exceptions.administrador.TelefoneInvalidoException;
import exceptions.administrador.TelefoneJaExisteException;
import exceptions.cliente.BairroInvalidoException;
import exceptions.cliente.CidadeInvalidaException;
import exceptions.cliente.ClienteNaoEncontradoException;
import exceptions.cliente.EstadoInvalidoException;
import exceptions.pedido.DataInvalidaException;
import exceptions.pedido.DatasInvalidasException;
import exceptions.pedido.PedidoInvalidoException;
import exceptions.pedido.PedidoNaoEncontradoException;
import exceptions.produto.AnoInvalidoException;
import exceptions.produto.ProdutoNaoEncontradoException;
import exceptions.produto.ValorInvalidoException;
import fachada.Fachada;

public class Teste {

	public static void main(String[] args) throws IOException, TipoRepositorioInvalidoException, IOExcelException, SheetNotFoundException, CellStringFormatException, CellNumberFormatException, PedidoNaoEncontradoException, CpfJaExisteException, CpfInvalidoException, TelefoneInvalidoException, TelefoneJaExisteException, DatasInvalidasException, AnoInvalidoException, exceptions.cliente.CpfInvalidoException, exceptions.cliente.TelefoneInvalidoException, CampoNaoPreenchidoException, exceptions.cliente.CpfJaExisteException, exceptions.cliente.TelefoneJaExisteException, NomeInvalidoException, BairroInvalidoException, CidadeInvalidaException, EstadoInvalidoException, ClienteNaoEncontradoException, ProdutoNaoEncontradoException, PedidoInvalidoException, ValorInvalidoException, CodigoInvalidoException, AdmNaoEncontradoException, DataInvalidaException {
		Fachada fachada = null;
		try {
			fachada = new Fachada();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TipoRepositorioInvalidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOExcelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SheetNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CellStringFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CellNumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String cpf = "111.111.111-11";
		String telefone = "1111-1111";
		String nome = "Jose";
		String logradouro = "Rua";
		String numero = "10";
		String complemento = "Casa";
		String bairro = "Barro";
		String cidade = "cidade";
		String senha = "123123";
		String uf = "pe";
		String codigo = "1";
		String[] codigosProdutos = {"1"};
		String descricao = "Issae";
		String nomeLivro = "nomeLivro";
		String nomeAutor = "nomeAutor";
		String editora = "ufpe";
		String anoPublicacao = "2010";
		String preco = "33.10";
		
		
		fachada.adicionarAdministrador(cpf, telefone, nome, logradouro, numero, complemento, bairro, cidade, senha, uf);
		fachada.adicionarCliente(cpf, telefone, nome, logradouro, numero, complemento, bairro, cidade, senha, uf);
		fachada.adicionarProduto(codigo, nomeLivro, descricao, preco, nomeAutor, editora, anoPublicacao);
		fachada.adicionarPedido(cpf, codigosProdutos);
		fachada.buscarProdutoParteCodigo(codigo);
		fachada.cancelarPedido(codigo);
		String dataInicialStr = "12/04/2010";
		String dataFinalStr = "16/04/2013";
		String nomeAdm = "nomeAdm";
		fachada.fazerRelatorioAdm(nomeAdm);
		String nomeCliente = "nomeCliente";
		fachada.fazerRelatorioCliente(dataInicialStr, dataFinalStr, nomeCliente); 
		String codigoProduto = "431232";
		fachada.fazerRelatorioProduto(dataInicialStr, dataFinalStr, codigoProduto); //fazerRelatorioProduto e Cliente jogam exceção sobre Data Invalida, mas geram os relatorios com um certo delay.
		fachada.visualizarAdministradores(nome);
		fachada.visualizarClientes(nome);
		fachada.visualizarPedidos(dataInicialStr, dataFinalStr);
		fachada.visualizarProdutos(nome);
		fachada.removerAdm(cpf);
		fachada.removerCliente(cpf);
		fachada.removerProduto(codigo);

	}

}
