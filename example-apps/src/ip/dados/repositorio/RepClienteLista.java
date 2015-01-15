package dados.repositorio;


import dados.IteratorClasse;
import dados.interfaces.IRepositorioCliente;
import dados.entidade.Cliente;
import dados.entidade.Pessoa;

public class RepClienteLista implements IRepositorioCliente {



	int numero_elementos; //Só pra saber se a lista esta vazia e tb o num. de el.
	Pessoa primeiro = new Cliente(); //O primeiro elemento da lista vai ser o do final, o ï¿½ltimo adicionado
	Pessoa ultimo =  new Cliente(); //O ultimo da lista sempre vai apontar pra null

	public RepClienteLista() {
		this.numero_elementos = 0; 
		this.primeiro = null;
		this.ultimo = null;

	}
	//LISTA ENCADEADA

	public boolean isVazia() {
		return (primeiro == null && ultimo == null);
	}

	public void adicionar(Cliente pessoa) {
		/*Qnd vc adiciona 1 elemento a lista, cria-se um objeto cliente
		 *e a esse objeto vai ser atribuï¿½do o valor de primeiro, que na verdade
		 *vai se tornar o elemento depois do primeiro. E o novo primeiro, vai ser
		 *cliente, que ï¿½ o objeto criado.
		 */
		Pessoa cliente = new Cliente((Cliente) pessoa);
		if (isVazia()) {
			this.primeiro = cliente;
			this.ultimo = cliente;

		}
		else {
			((Cliente) cliente).setProx((Cliente) this.primeiro);
		}
		this.primeiro = cliente;
		numero_elementos++;
	}

	public void adicionarNoFinal(Pessoa pessoa)  {
		this.numero_elementos++;
		Pessoa cliente = new Cliente((Cliente) pessoa);
		if (isVazia())
			primeiro = cliente;
		else
			((Cliente) ultimo).setProx((Cliente) cliente);
		this.ultimo = cliente;
	}


	public void imprimir() {
		if (this.numero_elementos == 0)
			System.out.print("[]");
		else {
			System.out.print("[");
			Pessoa aux = this.primeiro;
			for(int i=0; i < this.numero_elementos-1;i++) {
				System.out.print(aux.getCpf()+' ');
				aux = ((Cliente)aux).getProx();
			}
			System.out.print(((Cliente)aux).getCpf()+"]");



		}
	}

	public Cliente procurar(String cpf){ //Sistema de busca retornando um objeto
		Pessoa aux = new Cliente((Cliente) this.primeiro);
		
		if (((Cliente)aux).getCpf().equals(cpf)) {
			return (Cliente) primeiro;
		}
		else {
			for(int i=0; i <= numero_elementos - 1;i++) {
				if (((Cliente)aux).getProx().getCpf().equals(cpf)) {
					return ((Cliente)aux).getProx();
				}
				else
					aux = ((Cliente)aux).getProx();




			}

		}
		return null;
	}


	public void remover(String cpf){ //Excluir um objeto da lista
		Pessoa aux, auxAnterior = new Cliente(); //Auxiliar, vai servir de apontador
		aux = this.primeiro;
		auxAnterior = null;


		if (this.primeiro.getCpf().equals(cpf)) {
			this.primeiro = ((Cliente) primeiro).getProx();
			this.numero_elementos--;

		}
		else {
			while (aux !=null && !aux.getCpf().equals(cpf)) {
				auxAnterior = aux;
				aux = ((Cliente) aux).getProx();
			}
			if(aux != null) {
				((Cliente) auxAnterior).setProx(((Cliente) aux).getProx());
				this.numero_elementos--;
			}
			if(aux == ultimo) {
				ultimo = auxAnterior;
			}


		}


	}
	
	public void atualizar(Cliente pessoa) {

		
		procurar(pessoa.getCpf()).setContato(pessoa.getContato());
		procurar(pessoa.getCpf()).setNome(pessoa.getNome());
		procurar(pessoa.getCpf()).setSenha(pessoa.getSenha());

	}

	@Override
	public IteratorClasse<Cliente> iterator() {
		// TODO Auto-generated method stub
		return null;
	}



}
