package dados;

import java.util.Iterator;
import java.util.Vector;

public class IteratorClasse<Generics> implements Iterator<Generics>{
	
	//O iterator do tipo geretics pode receber qualquer tipo, por isso usamos assim

	private Vector<Generics> vetor;
	private int indice;

	// Construtor Vector //
	public IteratorClasse(Vector<Generics> vetor){
		this.vetor = vetor;
		this.indice = 0;
	}

	// Construtor Array //
	public IteratorClasse(Generics[] array){
		this.vetor = new Vector<Generics>();
		Vector<Generics> auxiliar = new Vector<Generics>();
		for (int i = 0; i<array.length; i++){
			if (array[i] != null){
				auxiliar.add(array[i]);
			}
		}
		this.vetor = auxiliar;
		this.indice = 0;	
	}

	public boolean hasNext(){
		boolean hasNext = false;
		if (this.indice < this.vetor.size()){
			hasNext = true;
		}

		return hasNext;

	}

	public Generics next(){

		Generics resposta = this.vetor.get(this.indice);
		this.indice++;
		return resposta;

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
