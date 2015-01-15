package negocio;

import dados.entidade.Administrador;
import dados.entidade.Contato;
import dados.interfaces.IRepositorioAdm;
import exceptions.administrador.*;
import exceptions.CampoNaoPreenchidoException;
import dados.IteratorClasse;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorAdm {
	private IRepositorioAdm repositorio;

	public ControladorAdm(IRepositorioAdm repositorio) {
		this.repositorio = repositorio;
	}

	public void adicionar(String cpf, String telefone, String nome, String logradouro, String numero, String complemento, String bairro, String cidade, String senha, String uf) throws CpfJaExisteException, CpfInvalidoException, TelefoneInvalidoException, CampoNaoPreenchidoException, TelefoneJaExisteException {
		boolean cpfValido = this.validaCpf(cpf);
		boolean numeroValido = this.validaNumero(telefone);
		boolean cpfExiste = false;
		if(cpfValido) {
			cpfExiste = this.jaExisteCpf(cpf);
		}
		boolean telefoneExiste = this.jaExisteTelefone(telefone);
		boolean campoVazio = this.campoVazio(cpf, telefone, nome, logradouro, numero, bairro, cidade, senha);

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
		Administrador administrador = new Administrador(nome, cpf, senha, contato);
		this.repositorio.adicionar(administrador);
	}

	public void remover(String cpf) throws CpfInvalidoException {
		Administrador admin = null;
		try {
			admin = new Administrador(this.buscaCpf(cpf));
		} catch (AdmNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repositorio.remover(admin.getCpf());
	}

	public boolean jaExisteCpf(String cpf) {
		boolean jaExiste = false;
		IteratorClasse<Administrador> iterator = repositorio.iterator();
		while(iterator.hasNext()) {
			Administrador auxiliar = iterator.next();
			if(auxiliar.getCpf().equals(cpf))
				jaExiste = true;
		}

		return jaExiste;
	}

	public boolean jaExisteNumero(String numero) {
		boolean jaExiste = false;
		IteratorClasse<Administrador> iterator = repositorio.iterator();
		while(iterator.hasNext()) {
			Administrador auxiliar = iterator.next();
			if(auxiliar.getContato().getNumero().equals(numero))
				jaExiste = true;
		}

		return jaExiste;
	}

	public boolean jaExisteTelefone(String telefone) {
		boolean jaExiste = false;
		IteratorClasse<Administrador> iterator = repositorio.iterator();
		while(iterator.hasNext()) {
			Administrador auxiliar = iterator.next();
			if(auxiliar.getContato().getTelefone().equals(telefone))
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
			String numero, String bairro, String cidade, String senha) {
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
		} else if (senha == null || senha.trim().equals("")) {
			campoVazio = true;
		}

		return campoVazio;
	}

	public Administrador buscaCpf(String cpf) throws CpfInvalidoException, AdmNaoEncontradoException {
		Administrador resposta = null;
		boolean achou = false;
		IteratorClasse<Administrador> iterator = repositorio.iterator();
		if (this.validaCpf(cpf)) {
			while (iterator.hasNext()) {
				Administrador auxiliar = iterator.next();
				if (auxiliar.getCpf().equals(cpf)) {
					resposta = auxiliar;
					achou = true;
				}
			}

			if (!achou) {
				throw new AdmNaoEncontradoException();
			}

		} else {
			throw new CpfInvalidoException();
		}
		return resposta;
	}

	public Administrador buscaTelefone(String telefone) throws AdmNaoEncontradoException,
	TelefoneInvalidoException {
		Administrador resposta = null;
		boolean achou = false;
		IteratorClasse<Administrador> iterator = repositorio.iterator();
		if (this.validaNumero(telefone)) {
			while (iterator.hasNext()) {
				Administrador auxiliar = iterator.next();
				if (auxiliar.getContato().getTelefone().equals(telefone)) {
					resposta = auxiliar;
					achou = true;
				}
			}

			if (!achou) {
				throw new AdmNaoEncontradoException();
			}

		} else {
			throw new TelefoneInvalidoException();
		}
		return resposta;
	}

	public IteratorClasse<Administrador> buscaNome(String nome) throws AdmNaoEncontradoException {
		IteratorClasse<Administrador> iterator = repositorio.iterator();
		Vector<Administrador> resposta = new Vector<Administrador>();
		while (iterator.hasNext()) {
			Administrador auxiliar = iterator.next();
			if (auxiliar.getNome().toLowerCase().contains(nome.toLowerCase())) {
				resposta.add(auxiliar);
			}
		}

		if (resposta.isEmpty()) {
			throw new AdmNaoEncontradoException();
		}

		IteratorClasse<Administrador> retorno = new IteratorClasse<Administrador>(resposta);
		return retorno;
	}

	private boolean apenasLetras(String palavra) {
		Pattern pattern = Pattern.compile("[a-zA-Z\\sÇçÛûÙùÚúÒòÓóÕõÔôÌìÍíÈèÉéÊêÀàÁáÃã]+");
		Matcher matcher = pattern.matcher(palavra);
		return matcher.matches();

	}		
}