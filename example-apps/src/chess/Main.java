package chess;
import javax.swing.JFrame;



public class Main{
	Board b;
	Chess c;
	Main(){
		super();
		c = new Chess();
		Game_Board gb = new Game_Board();
		this.b = new Board(c, gb);
	
	}
	public static void main(String[] args) {
		Main m = new Main();
		//m.setBounds(0, 0, 500, 500);
	}

}
