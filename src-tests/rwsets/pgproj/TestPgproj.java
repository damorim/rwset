package rwsets.pgproj;






import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import rwsets.Helper;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.io.CommandLine;
import com.ibm.wala.util.warnings.Warnings;

import depend.MethodDependencyAnalysis;
import depend.util.Util;
import depend.util.graph.SimpleGraph;


public class TestPgproj {
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  
  //@Test(expected=NullPointerException.class)
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "pgproj/AlgoritmoDeCasteljau.java";
    String pgprojjar = EXAMPLES_JAR + SEP + "pgproj.jar";
    //filter vê se tem este nome na package
    String filter = "projpg";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(pgprojjar)).exists());
    
    //line e de onde a análise e iniciada
    String line = "this.pontosCasteljau.add((Ponto)pontos.get(0));";
    SimpleGraph sg = depend.Main.analyze(pgprojjar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/pgproj/TestPgProj.test0.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  //teste acima da erro por motivo desconhecido
  
  @Test
  public void test1() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "pgproj/Ponto.java";
    String pgprojjar = EXAMPLES_JAR + SEP + "pgproj.jar";
    //filter vê se tem este nome na package
    String filter = "projpg";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(pgprojjar)).exists());
    
    //line e de onde a análise e iniciada
    String line = "boolean colidiu = false;";
    SimpleGraph sg = depend.Main.analyze(pgprojjar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/pgproj/TestPgProj.test1.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  
  
  
  
  
}
