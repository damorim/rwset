package dados.repositorio;

/*import dados.entidade.Contato;
import exceptions.CampoNaoPreenchidoException;
import exceptions.administrador.AdmNaoEncontradoException;
import exceptions.administrador.CpfInvalidoException;
import exceptions.administrador.CpfJaExisteException;
import exceptions.administrador.TelefoneInvalidoException;
import exceptions.administrador.TelefoneJaExisteException;
import negocio.ControladorAdm;*/

import dados.interfaces.IRepositorioAdm;
import dados.entidade.Administrador;
import dados.entidade.Contato;
import dados.IteratorClasse;
import exceptions.CampoNaoPreenchidoException;
import exceptions.administrador.CpfInvalidoException;
import exceptions.administrador.CpfJaExisteException;
import exceptions.administrador.TelefoneInvalidoException;
import exceptions.administrador.TelefoneJaExisteException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;



public class RepAdmArquivo implements IRepositorioAdm {

	private Vector<Administrador> vetorAdministradores;


	@SuppressWarnings({ "unchecked", "resource" })
	public RepAdmArquivo() throws IOException {
		this.vetorAdministradores = new Vector<Administrador>();

		File arquivo = new File("arquivoAdministrador.txt");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		if (arquivo.length() != 0) {
			ObjectInputStream reader = null;
			try {
				FileInputStream fileInput = new FileInputStream("arquivoAdministrador.txt");

				reader = new ObjectInputStream(fileInput);
				this.vetorAdministradores = (Vector<Administrador>) reader.readObject();
			} catch (ClassNotFoundException e) { 
			} 
		}
	}

	public void adicionar(Administrador admin) {
		this.vetorAdministradores.add(admin);
		ObjectOutputStream gravar = null;

		try {

			FileOutputStream writer = new FileOutputStream("arquivoAdministrador.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorAdministradores);
			gravar.flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				gravar.close();
			} catch (IOException e) {
			}
		}
	}

	public void remover(String cpf) {
		for (int i = 0; i < this.vetorAdministradores.size(); i++) {
			if (this.vetorAdministradores.elementAt(i).getContato() == null) {

			}
			else if (this.vetorAdministradores.elementAt(i).getCpf().equals(cpf)) {
				this.vetorAdministradores.remove(i);
			}
		}

		ObjectOutputStream gravar = null;

		FileOutputStream writer;
		try {
			writer = new FileOutputStream("arquivoAdministrador.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorAdministradores);
			gravar.flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				gravar.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Administrador procurar(String cpf) {

		//Cria um objeto FileInputStream
		FileInputStream fileStream;
		int indiceAchado = 0;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoAdministrador.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);

			this.vetorAdministradores = (Vector<Administrador>) os.readObject();
			for (int indice = 0; indice < vetorAdministradores.size(); indice++) {
				if (this.vetorAdministradores.elementAt(indice).getCpf().equals(cpf)) {
					indiceAchado = indice;
					//System.out.print(indiceAchado);
				}



			} 
		} catch (FileNotFoundException e) {			
		} catch (IOException ex) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {
			}

		}
		return this.vetorAdministradores.elementAt(indiceAchado);

	}
	//O método atualizar é basicamente feito o procurar, porém depois que ele atualiza um elemento
	//Ele deve re-serializar tudo
	@SuppressWarnings("unchecked")
	public void atualizar(Administrador administrador){
		FileInputStream fileStream;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoAdministrador.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);
			//Deserializa o vector e verifica se o cpf do elemento é igual 
			//O cpf do objeto, atualiza e re-serializa.
			int indiceAchado = 0;
			this.vetorAdministradores = (Vector<Administrador>) os.readObject();
			for (int indice = 0; indice < vetorAdministradores.size(); indice++) {
				if (this.vetorAdministradores.elementAt(indice).getCpf().equals(administrador.getCpf())) {
					indiceAchado = indice;
					//System.out.print(indiceAchado);
					this.vetorAdministradores.elementAt(indiceAchado).setContato(administrador.getContato());
					this.vetorAdministradores.elementAt(indiceAchado).setNome(administrador.getNome());
					this.vetorAdministradores.elementAt(indiceAchado).setSenha(administrador.getSenha());

				}


			}
			ObjectOutputStream gravar = null;

			try {

				FileOutputStream writer = new FileOutputStream("arquivoAdministrador.txt", false);
				gravar = new ObjectOutputStream(writer);
				gravar.writeObject(this.vetorAdministradores);
				gravar.flush();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					gravar.close();

				} catch (IOException e) {
				}
			}

		} catch (FileNotFoundException e) {			
		} catch (IOException ex) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {
			}

		}


	}



	public IteratorClasse<Administrador> iterator() {
		Vector<Administrador> listAdministradors = new Vector<Administrador>();
		for (int i = 0; i < this.vetorAdministradores.size(); i++) {
			listAdministradors.add(this.vetorAdministradores.elementAt(i));
		}
		IteratorClasse<Administrador> iterator = new IteratorClasse<Administrador>(listAdministradors);
		return iterator;
	}

	public static void main(String[] args) throws IOException, CpfJaExisteException, CpfInvalidoException, TelefoneInvalidoException, CampoNaoPreenchidoException, TelefoneJaExisteException {
		
		//TESTE REP-ARQUVIO
		
		RepAdmArquivo teste = new RepAdmArquivo();
		Contato contato = new Contato("Rua teste", " Testnumero", "Test complemento", "Test bairro" , "Test cidade" , "test uf", "test telefone");
		Administrador admin = new Administrador("Fabio", "156.111.111-11", "12345678", contato);
		Administrador adminSubstituir = new Administrador("Junior Alterado", "156.111.111-11", "55555", contato);

		//teste.adicionar(admin);
		//teste.adicionar(admin2);
		System.out.println(teste.procurar("156.111.111-11").getNome());
		
		teste.atualizar(adminSubstituir);		
		
		
		System.out.println(teste.procurar("156.111.111-11").getNome());

		
		/*
		 *:::::::::::TesteCONTROLADOR
		 * try {

			ControladorAdm controladorAdm = new ControladorAdm(new RepAdmArquivo());


		//controladorAdm.adicionar("156.111.111-11", "1234-5678", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");

		//controladorAdm.adicionar("146.111.111-11", "1235-5678", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");

		//controladorAdm.remover("156.111.111-11");

		try {

			System.out.print(controladorAdm.buscaCpf("146.111.111-11").getSenha());
		} catch (AdmNaoEncontradoException e) {
			e.getMessage();
		}

		} catch (IOException e) {			
		}
		catch (CpfInvalidoException e2) {
			e2.getMessage();
		} 
	} */
	}
}