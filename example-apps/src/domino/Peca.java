package domino;


public class Peca {
	protected int numeros[] = new int [2];
	protected Peca vizinhos[] = new Peca [2];
	
	public Peca(int i, int j) 
	{
		/**numeros da peca serao o par i e j.**/
		numeros[0] = i;
		numeros[1] = j;
		vizinhos[0] = null;
		vizinhos[1] = null;
		
	}

	public int carroca() {
		/**Se a peca for carroca retorna o valor, senao for carroca retorna -1**/
		if (numeros[0] == numeros[1])
		  return numeros[0];
		else
		  return -1;
	}
	
	public Peca[] getVizinhos() 
	{
		return vizinhos;
	}

	public void setVizinhos(Peca[] vizinhos) 
	{
		this.vizinhos = vizinhos;
	}

	public int[] getNumeros() 
	{
		return numeros;
	}
	
	public int getSoma() 
	{
		return numeros[0]+numeros[1];
	}
	
	public String toString()
	{
		return numeros[0] + "-" +numeros[1];
	}

	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;	
		}		
		
		if(!(obj instanceof Peca))
		{
			return false;			
		}
		
		Peca p = (Peca)obj;
		
		if (p.getNumeros()[0] == this.numeros[0] && p.getNumeros()[1] == this.numeros[1])
		  return true;
		else
			return false;
		
	}
}
