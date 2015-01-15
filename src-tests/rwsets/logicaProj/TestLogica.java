package rwsets.logicaProj;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import rwsets.Helper;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

import depend.util.graph.SimpleGraph;

public class TestLogica {
  
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "logicaProj" + SEP + "logica" + SEP + "Resolucao.java";
    String logicaJar = EXAMPLES_JAR + SEP + "logica.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(logicaJar)).exists());
    
    String line = "if (!exp.isInFNC())";
    SimpleGraph sg = depend.Main.analyze(logicaJar, "logica", strCompUnit, line);
    //System.out.println(sg.toDotString());    
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logicaProj/TestLogica.dot";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  

}
