package bancosimples;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;


public class Banco {

	Vector<Conta> contas;
	Vector<Cliente> clientes;
	
	Banco(){
		contas = new Vector<Conta>();
		clientes = new Vector<Cliente>();
	}
	
	Cliente findCliente(String RG){
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext()){
			Cliente c = it.next();
			if(c.getRG() == RG)return c;
		}
		return null;
	}
	
	Conta findConta(String numeroConta){
		Iterator<Conta> it = contas.iterator();
		while(it.hasNext()){
			Conta c = it.next();
			if(c.getNumeroConta() == numeroConta)return c;
		}
		return null;
	}
	Cliente removeCliente(String RG){
		Cliente t =  findCliente(RG);
		clientes.remove(t);
		return t;
	}
	Conta removeConta(String numeroConta){
		Conta t =  findConta(numeroConta);
		contas.remove(t);
		return t;
	}
	
	double transferir(String nc1, String nc2, double val){
		val = findConta(nc1).debitar(val);
		findConta(nc2).deposito(val);
		return val;
	}
	boolean addConta(Conta c){
		if(findConta(c.getNumeroConta())==null){
			c.getDono().setConta(c);
			return contas.add(c);
		}else{
			return false;
		}
	}
	boolean addCliente(Cliente c){
		//Cliente cd = new Cliente("s", "sda", "ads");
		if(findConta(c.getRG())==null){
			return clientes.add(c);
		}else{
			return false;
		}
	}

	
}
