package dados.repositorio;



import dados.IteratorClasse;
import dados.interfaces.IRepositorioAdm;
import dados.entidade.Administrador;


public class RepAdmLista implements IRepositorioAdm {



	int numero_elementos; //Só pra saber se a lista esta vazia e tb o num. de el.
	Administrador primeiro = new Administrador(); //O primeiro elemento da lista vai ser o do final, o ï¿½ltimo adicionado
	Administrador ultimo =  new Administrador(); //O ultimo da lista sempre vai apontar pra null

	public RepAdmLista() {
		this.numero_elementos = 0; 
		this.primeiro = null;
		this.ultimo = null;

	}
	//LISTA ENCADEADA

	public boolean isVazia() {
		return (primeiro == null && ultimo == null);
	}

	public void adicionar(Administrador administrador) {
		/*Qnd vc adiciona 1 elemento a lista, cria-se um objeto admin
		 *e a esse objeto vai ser atribuï¿½do o valor de primeiro, que na verdade
		 *vai se tornar o elemento depois do primeiro. E o novo primeiro, vai ser
		 *admin, que ï¿½ o objeto criado.
		 */
		Administrador admin = new Administrador(administrador);
		if (isVazia()) {
			this.primeiro = admin;
			this.ultimo = admin;

		}
		else {
			admin.setProx(this.primeiro);
		}
		this.primeiro = admin;
		numero_elementos++;
	}

	public void adicionarNoFinal(Administrador administrador)  {
		this.numero_elementos++;
		Administrador admin = new Administrador(administrador);
		if (isVazia())
			primeiro = admin;
		else
			ultimo.setProx( admin);
		this.ultimo = admin;
	}


	public void imprimir() {
		if (this.numero_elementos == 0)
			System.out.print("[]");
		else {
			System.out.print("[");
			Administrador aux = this.primeiro;
			for(int i=0; i < this.numero_elementos-1;i++) {
				System.out.print(aux.getCpf()+' ');
				aux = aux.getProx();
			}
			System.out.print(aux.getCpf()+"]");



		}
	}

	public Administrador procurar(String cpf){ //Sistema de busca retornando um objeto
		Administrador aux = new Administrador(this.primeiro);

		if (aux.getCpf().equals(cpf)) {
			return primeiro;
		}
		else {
			for(int i=0; i <= numero_elementos - 1;i++) {
				if (aux.getProx().getCpf().equals(cpf)) {
					return aux.getProx();
				}
				else
					aux = aux.getProx();




			}

		}
		return null;
	}


	public void remover(String cpf){ //Excluir um objeto da lista
		Administrador aux, auxAnterior = new Administrador(); //Auxiliar, vai servir de apontador
		aux = this.primeiro;
		auxAnterior = null;


		if (this.primeiro.getCpf().equals(cpf)) {
			this.primeiro = primeiro.getProx();
			this.numero_elementos--;

		}
		else {
			while (aux !=null && aux.getCpf() != cpf) {
				auxAnterior = aux;
				aux = ((Administrador) aux).getProx();
			}
			if(aux != null) {
				((Administrador) auxAnterior).setProx(aux.getProx());
				this.numero_elementos--;
			}
			if(aux == ultimo) {
				ultimo = auxAnterior;
			}


		}


	}
	@Override
	public IteratorClasse<Administrador> iterator() {
		return null;
	}

	public void atualizar(Administrador pessoa) {

		
		procurar(pessoa.getCpf()).setContato(pessoa.getContato());
		procurar(pessoa.getCpf()).setNome(pessoa.getNome());
		procurar(pessoa.getCpf()).setSenha(pessoa.getSenha());

	}
/*
	public static void main(String...arguments) {
		RepAdmLista lista = new RepAdmLista();

		Administrador teste = new Administrador("Paulo", "1", "444", null);
		Administrador teste2 = new Administrador("Fera", "2", "555", null);
		Administrador testeAtualiza = new Administrador("Fabio", "2", "444", null);

		lista.adicionar(teste);
		lista.adicionar(teste2);

		lista.imprimir();

		System.out.print(lista.procurar("2").getNome());

		lista.atualizar(testeAtualiza);
		
		System.out.print(lista.procurar("2").getNome());

		lista.imprimir();
	}
	*/

}



