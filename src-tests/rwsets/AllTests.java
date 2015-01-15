package rwsets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
// Add test classes here
  rwsets.coffeemaker.TestCoffeeMaker.class,
  rwsets.core.Sanity.class,
  rwsets.campus.TestCampus.class,
  rwsets.ip.TestIp.class,
  rwsets.chat.TestChat.class,
  rwsets.logicaProj.TestLogica.class
})

public class AllTests  { }