package rwsets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  
  rwsets.database.TestDatabase.class,
  rwsets.cqueue.TestCqueue.class,
  rwsets.forest.TestForest.class,
  rwsets.pgproj.TestPgproj.class
  
})


public class AllTestsGrupo4 {}
