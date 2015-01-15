package logica;

import java.util.ArrayList;

public class Exp
{
	private String exp;
	private ArrayList<String> exps;

	Exp(String exp)
	{
		exps = new ArrayList<String>();
		this.exp = exp;
		int n = 0, a = 0;
		for (int i = 0; i < exp.length(); i++)
		{
			if (exp.charAt(i) == '(')
			{
				if (n == 0 && i > 0)
				{
					if (!vectorContains(exps, substr(exp, a-1, i - 1 - a)))
						exps.add(substr(exp, a, i - 1 - a));
					a = i;
				}
				n++;

			}
			else if (exp.charAt(i) == ')')
			{
				n--;
				if (n == 0 && i == exp.length() - 1)
					if (!vectorContains(exps, substr(exp, a, i + 1 - a)))
						exps.add(substr(exp, a, i + 1 - a));
			}
		}
	}

	public void printAll() {
		for (int i = 0; i < exps.size(); i++)
		{
			System.out.println(exps.get(i));
		}
	}


	public static String replace(String str, int comeco, int fim, String substituta) {
		StringBuffer sb = new StringBuffer(str);
		if (fim+comeco+1 <= str.length())
			sb.replace(comeco, fim+comeco+1, substituta);
		else
			sb.replace(comeco, str.length(), substituta);
		return sb.toString();

	}


	public static String substr(String str, int comeco, int fim) {
		StringBuffer sb = new StringBuffer(str);
        if (fim+comeco+1 <= str.length()) {
        	if (fim+comeco+1 == str.length())
        		return sb.substring(comeco+1, str.length()-1);
        	else
        		return sb.substring(comeco+1, comeco+fim-1);
        }
		else
			return sb.substring(comeco+1, str.length()-1);
	}


	boolean vectorContains(ArrayList<String> v, String exp)
	{
		if (exp.length() < 5)
			for (int i = 0; i < v.size(); i++)
				if (v.get(i) == exp)
					return true;
				else
				{
					for (int l = 0; i < v.size(); i++)
					{
						boolean b = true;
						if (exp.length() == v.get(i).length())
						{
							for (int j = 0; j < v.get(i).length(); j++)
							{
								if (v.get(l).charAt(j) == '+' || v.get(l).charAt(j) == '-' || v.get(l).charAt(j) == '(' || v.get(l).charAt(j) == ')')
									continue;

								int found;
								if (j != 0 && v.get(i).charAt(j - 1) == '-')
								{
									found = exp.indexOf("-" + v.get(l).charAt(j));
									if (found == -1)
									{
										b = false;
										break;
									}
								}
								else
								{
									found = exp.indexOf("+" + v.get(l).charAt(j));
									if (found == -1)
									{
										found = exp.indexOf("(" + v.get(l).charAt(j));
										if (found == -1)
										{
											b = false;
											break;
										}
									}
								}

							}
							if (b)
								return true;
						}
					}
				}
		return false;
	}

	String clearExpression(String exp)
	{

		for (int i = 0; i < exp.length(); i++)
		{
			char c = exp.charAt(i);

			if ((i > 0 && exp.charAt(i - 1) != '-') || c == '(' || c == ')' || c == '+' || c == '-')
				continue;

			for (int j = exp.length() - 1; j > 0; j--)
			{
				while (j > exp.length())
				{
					continue;
				}

				if ((j > 0 && exp.charAt(j - 1) != '-') || i == j || exp.charAt(j) == '(' || exp.charAt(j) == ')' || exp.charAt(j) == '+' || exp.charAt(j) == '-')
					continue;


				if (c == exp.charAt(j) && exp.charAt(i - 1) == '-' && exp.charAt(j - 1) == '-')
				{

					if (j > 1 && exp.charAt(j - 2) == '+')
						exp = replace(exp, j - 2, 3, "");
					else
						exp = replace(exp, j - 1, 3, "");

				}

			}
		}

		return exp;
	}

	void clearExpressions()
	{
		for (int i = 0; i < exps.size(); i++)
		{
			exps.set(i, clearExpression(exps.get(i)));
		}
	}

	boolean isSatisfiable(Arquivo arquivo)
	{
		clearExpressions();

		for (int i = 0; i < exps.size(); i++)
		{
			for (int j = 0; j < exps.get(i).length(); j++)
			{
				char c = exps.get(i).charAt(j);
				if (c == '+' || c == '-' || c == '(' || c == ')')
					continue;

				if (j > 0 && exps.get(i).charAt(j - 1) != '-')
					continue;

				for (int k = exps.size() - 1; k > 0; k--)
				{
					if (j == k)
						continue;

					for (int l = 0; l < exps.get(k).length(); l++)
					{
						if (exps.get(k).charAt(l) == '-' || exps.get(k).charAt(l) == '+' || exps.get(k).charAt(l) == '(' || exps.get(k).charAt(l) == ')')
							continue;

						if (exps.get(i).charAt(j) == exps.get(k).charAt(l) && l > 0 && exps.get(k).charAt(l - 1) != '-')
						{

							String str1 = exps.get(i);

							if (j > 1 && exps.get(i).charAt(j - 2) == '+')
								str1 = replace(exp, j - 2, 3, "");
							else
								if (exps.get(i).charAt(j + 1) != ')')
									str1 = replace(str1, j - 1, 3, "");
								else
									str1 = replace(str1, j - 1, 2, "");


							String str2 = exps.get(k);

							if (l > 0 && exps.get(k).charAt(l - 1) == '+')
								str2 = replace(str2 ,l - 1, 2, "");
							else
								if (exps.get(k).charAt(l + 1) != ')')
									str2 = replace(str2, l, 2, "");
								else
									str2 = replace(str2, l, 1, "");


							String str = substr(str1, 0, str1.length() - 1);
							if (!(str1.charAt(1) == ')' || str2.charAt(1) == ')'))
								str = str + '+';
							str = str + substr(str2, 1, str2.length() - 1);

							str = clearExpression(str);


							//System.out.println("vetor já contém? " + vectorContains(exps, str) + "   |   " + str);
							if (str != "()" && !vectorContains(exps, str))
							{
										exps.add(str);
							}
						}
					}
				}
			}
		}


		for (int i = 0; i < exps.size(); i++)
		{
			if (exps.get(i).length() != 3)
				continue;

			for (int j = 0; j < exps.size(); j++)
			{
				if (exps.get(j).length() != 4)
					continue;

				if (exps.get(i).charAt(1) == exps.get(j).charAt(2))
					return false;
			}
		}

		return true;
	}

	boolean isInFNC(String exp)
	{
		int a = 0, p = 0;
		for (int i = 1; i < exp.length() - 1; i++)
		{
			if (exp.charAt(i) == '>' || exp.charAt(i) == '.' || exp.charAt(i) == '(')
				return false;
		}

		return true;
	}

	boolean isInFNC()
	{
		int n = 0, a = 0;
		for (int i = 0; i < exp.length(); i++)
			if (exp.charAt(i) == '(')
			{
				if (n == 0 && i > 0)
				{
					if (exp.charAt(i - 1) == '>' || exp.charAt(i - 1) == '+')
						return false;
					a = i;
				}
				n++;
			}
			else if (exp.charAt(i) == ')')
				n--;

		for (int i = 0; i < exps.size(); i++)
		{
			if (!isInFNC(exps.get(i)))
				return false;
		}
		return true;
	}

	boolean areAllHorn()
	{
		for (int i = 0; i < exps.size(); i++)
		{
			int n = 0, a = 1, op = 0;
			for (int j = 1; j < exps.get(i).length() - 1; j++)
			{
				if (exps.get(i).charAt(j) == '>' || exps.get(i).charAt(j) == '.' || exps.get(i).charAt(j) == '+')
					op++;
				if (exps.get(i).charAt(j) == '-')
					n++;
			}

			if (!(n >= op))
				return false;
		}

		return true;
	}
}
