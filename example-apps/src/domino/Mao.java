package domino;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Mao extends ArrayList<Peca> {
  public String toString() {
    String resultado = new String();

    for (Peca peca : this) {
      resultado += peca.toString() + " ";
    }

    resultado += "\n";

    return resultado;
  }

  public Peca remove(Peca P) {
    for (Peca peca : this) {
      if (peca.equals(P)) {
        super.remove(peca);
        return peca;
      }
    }

    return null;
  }
}
