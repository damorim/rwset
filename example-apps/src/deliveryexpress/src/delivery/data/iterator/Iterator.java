package delivery.data.iterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import delivery.data.interfaces.InterfaceIterator;

public class Iterator<T> implements InterfaceIterator<T>, Serializable {
	
	private static final long serialVersionUID = 4873126580976199702L;
	private int indice;
	private ArrayList<T> objetos;
	
	public ArrayList<T> getObjetos() {
		return objetos;
	}

	public Iterator (T[] objeto) {
		this.indice = 0;
		this.objetos = new ArrayList<T>();
		
		for (int i = 0; i < objeto.length; i++) {
			if (objeto[i] != null)
				this.objetos.add(objeto[i]);
		}
	}
	
	public Iterator (Vector<T> vetor) {
		this.indice = 0;
		this.objetos = new ArrayList<T>();
		
		for (int i = 0; i < vetor.size(); i++) {
			this.objetos.add(vetor.get(i));
		}
	}
	
	public Iterator (ArrayList<T> arrayList) {
		this.objetos = arrayList;
		this.indice = 0;
	}
	
	public boolean hasNext(){
		if (this.indice < this.objetos.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public T next(){
		T proximo = this.objetos.get(this.indice);
		this.indice++;
		return proximo;
	}
}
