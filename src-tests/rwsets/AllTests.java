package rwsets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  // Add test classes here
  rwsets.coffeemaker.TestCoffeeMaker.class,
  rwsets.core.Sanity.class,
  rwsets.soot.SootTests.class,
<<<<<<< HEAD
  /* if688-2014.2-group-2 */
  rwsets.escola.GeeoIpTest.class,
  rwsets.hardware.HardwareTest.class,
  rwsets.logica.LogicaTest.class,
  rwsets.manuip.ManuIpTest.class
=======
 /* if688-2014.2-group-2 */
  // rwsets.escola.GeeoIpTest.class,
  // rwsets.hardware.HardwareTest.class,
  // rwsets.logica.LogicaTest.class,
  // rwsets.manuip.ManuIpTest.class
>>>>>>> 76ad96127783b32d6c65c46de5424437b7983232
})

public class AllTests  { }