package logica;

import java.util.ArrayList;


public class Resolucao {

	public static void main(String[] args)
	{
		Arquivo arquivo = new Arquivo("Expressoes.in", "Expressoes.out");
		int n;
		String formula;
		n = arquivo.readInt();
		for (int i = 0; i < n; i++)
		{
			formula = "";
			formula = arquivo.readString();
			Exp exp = new Exp(formula);
			//exp.printAll();

			arquivo.print("caso #" + (i + 1) + ": ");
			if (!exp.isInFNC())
			{
				arquivo.println("nao esta na FNC");
				continue;
			}
			if (!exp.areAllHorn())
			{
				arquivo.println("nem todas as clausulas sao de horn");
				continue;
			}
			if (exp.isSatisfiable(arquivo))
				arquivo.println("satisfativel");
			else
				arquivo.println("insatisfativel");
		}
	}


}
