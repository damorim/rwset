package negocio;

import dados.entidade.Cliente;
import dados.entidade.Contato;
import dados.interfaces.IRepositorioCliente;
import exceptions.cliente.*;
import exceptions.CampoNaoPreenchidoException;
import dados.IteratorClasse;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorCliente {
	private IRepositorioCliente repositorio;

	public ControladorCliente(IRepositorioCliente repositorio) {
		this.repositorio = repositorio;
	}

	public void adicionar(String cpf, String telefone, String nome, String senha, String logradouro, String numero, String complemento, String bairro, String cidade, String uf) throws CpfJaExisteException, CpfInvalidoException, TelefoneInvalidoException, CampoNaoPreenchidoException, TelefoneJaExisteException {
		boolean cpfValido = this.validaCpf(cpf);
		boolean numeroValido = this.validaNumero(telefone);
		boolean cpfExiste = false;
		if(cpfValido) {
			cpfExiste = this.jaExisteCpf(cpf);
		}
		boolean telefoneExiste = this.jaExisteTelefone(telefone);
		boolean campoVazio = this.campoVazio(cpf, telefone, nome, logradouro, numero, bairro, cidade);

		if(cpfExiste)
			throw new CpfJaExisteException();

		if(telefoneExiste)
			throw new TelefoneJaExisteException();

		if(!cpfValido)
			throw new CpfInvalidoException();

		if(!numeroValido)
			throw new TelefoneInvalidoException();

		if (campoVazio) 
			throw new CampoNaoPreenchidoException();

		Contato contato = new Contato(logradouro, numero, complemento, bairro, cidade, uf, telefone);
		Cliente cliente = new Cliente(nome, cpf, senha, contato);
		this.repositorio.adicionar(cliente);
	}

	public void remover(String cpf) throws CpfInvalidoException, ClienteNaoEncontradoException {
		this.buscaCpf(cpf);
		this.repositorio.remover(cpf);
	}

	public boolean jaExisteCpf(String cpf) {
		boolean jaExiste = false;
		IteratorClasse<Cliente> iterator = repositorio.iterator();
		while(iterator.hasNext()) {
			Cliente auxiliar = iterator.next();
			if(auxiliar.getCpf().equals(cpf))
				jaExiste = true;
		}

		return jaExiste;
	}
	
	public boolean jaExisteTelefone(String telefone) {
		boolean jaExiste = false;
		IteratorClasse<Cliente> iterator = repositorio.iterator();
		while(iterator.hasNext()) {
			Cliente auxiliar = iterator.next();
			if(auxiliar.getContato().getTelefone().equals(telefone))
				jaExiste = true;
		}

		return jaExiste;
	}

	public boolean jaExisteNumero(String numero) {
		boolean jaExiste = false;
		IteratorClasse<Cliente> iterator = repositorio.iterator();
		while(iterator.hasNext()) {
			Cliente auxiliar = iterator.next();
			if(auxiliar.getContato().getNumero().equals(numero))
				jaExiste = true;
		}

		return jaExiste;
	}

	public boolean validaCpf(String cpf) {
		boolean cpfValido = false;

		if(!(cpf == null)) {
			if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
				cpfValido = true;
			}
		}

		return cpfValido;
	} 

	public boolean validaNumero(String numero) {
		boolean numeroValido = false;
		if (numero.matches("\\d{4}\\-\\d{4}")) {
			numeroValido = true;
		}

		return numeroValido;
	}

	public boolean validaNome(String nome) {
		boolean nomeValido = false;
		if(this.apenasLetras(nome)) {
			nomeValido = true;
		}

		return nomeValido;
	}
	
	private boolean campoVazio(String cpf, String telefone, String nome, String logradouro,
			String numero, String bairro, String cidade) {
		boolean campoVazio = false;
		if (nome == null || nome.trim().equals("")) {
			campoVazio = true;
		} else if (logradouro == null || logradouro.trim().equals("")) {
			campoVazio = true;
		} else if (bairro == null || bairro.trim().equals("")) {
			campoVazio = true;
		} else if (cidade == null || cidade.trim().equals("")) {
			campoVazio = true;
		} else if (numero == null || numero.trim().equals("")) {
			campoVazio = true;
		} else if (cpf == null || cpf.trim().equals("")) {
			campoVazio = true;
		} else if (telefone == null || telefone.trim().equals("")) {
			campoVazio = true;
		}

		return campoVazio;
	}

	public Cliente buscaCpf(String cpf) throws CpfInvalidoException, ClienteNaoEncontradoException {
		Cliente resposta = null;
		boolean achou = false;
		IteratorClasse<Cliente> iterator = repositorio.iterator();
		if (this.validaCpf(cpf)) {
			while (iterator.hasNext()) {
				Cliente auxiliar = iterator.next();
				if (auxiliar.getCpf().equals(cpf)) {
					resposta = auxiliar;
					achou = true;
				}
			}

			if (!achou) {
				throw new ClienteNaoEncontradoException();
			}

		} else {
			throw new CpfInvalidoException();
		}
		return resposta;
	}

	public Cliente buscaTelefone(String telefone) throws ClienteNaoEncontradoException,
	TelefoneInvalidoException {
		Cliente resposta = null;
		boolean achou = false;
		IteratorClasse<Cliente> iterator = repositorio.iterator();
		if (this.validaNumero(telefone)) {
			while (iterator.hasNext()) {
				Cliente auxiliar = iterator.next();
				if (auxiliar.getContato().getTelefone().equals(telefone)) {
					resposta = auxiliar;
					achou = true;
				}
			}

			if (!achou) {
				throw new ClienteNaoEncontradoException();
			}

		} else {
			throw new TelefoneInvalidoException();
		}
		return resposta;
	}

	public IteratorClasse<Cliente> buscaNome(String nome) throws ClienteNaoEncontradoException {
		IteratorClasse<Cliente> iterator = repositorio.iterator();
		Vector<Cliente> resposta = new Vector<Cliente>();
		while (iterator.hasNext()) {
			Cliente auxiliar = iterator.next();
			if (auxiliar.getNome().toLowerCase().contains(nome.toLowerCase())) {
				resposta.add(auxiliar);
			}
		}

		if (resposta.isEmpty()) {
			throw new ClienteNaoEncontradoException();
		}

		IteratorClasse<Cliente> retorno = new IteratorClasse<Cliente>(resposta);
		return retorno;
	}

	private boolean apenasLetras(String palavra) {
		Pattern pattern = Pattern.compile("[a-zA-Z\\sÇçÛûÙùÚúÒòÓóÕõÔôÌìÍíÈèÉéÊêÀàÁáÃã]+");
		Matcher matcher = pattern.matcher(palavra);
		return matcher.matches();

	}		
}
