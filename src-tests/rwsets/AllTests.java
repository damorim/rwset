package rwsets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  // Add test classes here
  rwsets.coffeemaker.TestCoffeeMaker.class,
  rwsets.core.Sanity.class,
  rwsets.bancosimples.TestBancoSimples.class,
  rwsets.delivery.TestDelivery.class,
  rwsets.domino.TestDomino.class,
  rwsets.chess.TestChess.class,
})

public class AllTests  { }