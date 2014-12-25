package domino;

import java.util.Vector;

@SuppressWarnings("serial")
public class Tabuleiro extends Vector<Peca> 
{
	private Peca pecaInicial;
	
	public Tabuleiro(Peca inicial)	
	{
		super();
		this.add(inicial);
		this.pecaInicial = inicial;
	}
	
	public Peca getPecaInicial() 
	{
		return pecaInicial;
	}
	
	public Vector<Peca> getListaLado0()
	{
		return new Vector<Peca>(subList(0, indexOf(pecaInicial)));
	}
	
	public Vector<Peca> getListaLado1()
	{
		return new Vector<Peca>(subList(indexOf(pecaInicial)+1, this.size()));
	}
	
	public String toString()
	{
		String resultado = new String();
		
		for (Peca peca : this) 
		{
			resultado += peca.toString() + " ";	
		}
		
		resultado+="\n";
		
		return resultado;
	}

}