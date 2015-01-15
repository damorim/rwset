package dados.repositorio;

import java.util.Vector;

import dados.IteratorClasse;
import dados.interfaces.IRepositorioAdm;
import dados.entidade.Administrador;

@SuppressWarnings("rawtypes")
public class RepAdmArray implements IRepositorioAdm, Iterable {

	private Administrador[] administradores;
	private int indice;

	public RepAdmArray() {
		this.administradores = new Administrador[1000];
		this.indice = 0;
	}
	
	public boolean isVazia() {
		if (indice == 0 && administradores[0].equals(null)) 
			return true;
		return false;
	}
	
	/*O método adicionar adiciona um objeto ao indice fixo que está é atributo do repositório
	daí depois o indice é incrementado em 1 para ficar apto a receber um novo objeto
	*/
	public void adicionar(Administrador administrador) {
		
		if(this.indice < this.administradores.length) {
			administradores[indice] = administrador;
			indice++;
		} else {
			Administrador[] aux = new Administrador[this.administradores.length * 2];
			for(int i = 0; i < this.indice; i++) {
				aux[i] = administradores[i];
			}

			this.administradores = aux;
			this.adicionar(administrador);
		}
	}

	//Só mais para fins de teste, imprimir certo atributo de todos
	public void imprimir() {
		for(int i = 0; i < indice; i++) {
			System.out.println(administradores[i].getCpf() + " - " + administradores[i].getNome());
		}
	}

	//A partir da string cpf que vai ser única, ele remove o objeto designado
	public void remover(String cpf) {
		for(int i = 0; i < indice; i++) {
			if(administradores[i].getCpf().equals(cpf)) {
				administradores[i] = null;
				if(i < indice - 1 && administradores[i + 1] != null) {
					administradores[i] = administradores[i + 1];
					administradores[i + 1] = null;
				}
			}
		}

		if(administradores[this.indice - 1] == null)
			indice--;
	}
	//Se o cpf for igual ao objeto no índice, retorna aquele objeto
	public Administrador procurar(String cpf) {
		for(int i = 0; i < indice; i++) {
			if(administradores[i].getCpf().equals(cpf))
				return administradores[i];
		}

		return null;
	}
	//Método para organizar, junto com um comparator ele compara 2 objetos, verificando seus cpfs
	//Caso um cpf for "menor" que outro, ele troca de lugar por meio de um auxiliar
	public void sort() {

		for (int i = 0; i < indice - 1; ++i)  {
			for (int j = i + 1; j < indice; ++j)  {
				if (administradores[i].getCpf().compareTo(administradores[j].getCpf()) > 0) {  
					Administrador aux = administradores[i];  
					administradores[i] = administradores[j];  
					administradores[j] = aux;  
				}  
			}
		}

	}

	public IteratorClasse<Administrador> iterator() {
		
		Vector<Administrador> listAdm = new Vector<Administrador>();
		for (int i = 0; i < this.indice; i++) {
			listAdm.add(this.administradores[i]);
		}
		
		IteratorClasse<Administrador> iterator = new IteratorClasse<Administrador>(listAdm);
		return iterator;
	}
 
	public void atualizar(Administrador administrador) {
		for (int i = 0; i < indice; i++) {
			if(administradores[i].getCpf().equals(administrador.getCpf()))
				administradores[i] = administrador;
		}
	}


//Teste 
	/*public static void main (String[] args) {

		RepAdmArray rep = new RepAdmArray();
		Administrador testep = new Administrador("Teste", "CPF", "Senha", null);
		
		Administrador teste2 = new Administrador("Teste2", "CPF2", "Senha3", null);
		
		Administrador teste3 = new Administrador("Teste3", "CPF3", "Senha3", null);
	


		rep.adicionar(teste3);
		rep.adicionar(teste2);
		rep.adicionar(testep);
	
		rep.imprimir();
		
		System.out.println("Procura: "+ rep.procurar("CPF2") + "Valores ordenados: \n");
		rep.sort();
		rep.imprimir();
	} 
*/
}