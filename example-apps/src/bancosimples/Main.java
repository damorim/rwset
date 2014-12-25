package bancosimples;

public class Main {
	public static void main(String[] args) {
		Banco bk = new Banco();
		bk.addCliente(new Cliente("C1" , "123456", "111111"));
		bk.addCliente(new Cliente("C2" , "123567", "222222"));
		bk.addCliente(new Cliente("C3" , "123458", "333333"));
		
		bk.addConta(new Conta("asdasd", 500, bk.findCliente("111111")));
		bk.findCliente("111111").deposito(100);
		System.out.println(bk.findCliente("111111").getConta().checkSaldo());
	}
}
