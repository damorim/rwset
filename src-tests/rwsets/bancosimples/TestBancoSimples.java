package rwsets.bancosimples;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

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

public class TestBancoSimples {
  
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";

  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "bancosimples/Main.java";
    String coffeejar = EXAMPLES_JAR + SEP + "bancosimples.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "bk.findCliente(\"111111\").deposito(100);";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "banco", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/bancosimples/TestBancoSimples.test0.dot";
    //PrintWriter out = new PrintWriter(expectedResultFile);
    //out.println(sg.toDotString());
    //out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  @Test
  public void test1() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "bancosimples/Cliente.java";
    String coffeejar = EXAMPLES_JAR + SEP + "bancosimples.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "return conta.debitar(d);";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "banco", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/bancosimples/TestBancoSimples.test1.dot";
    //PrintWriter out = new PrintWriter(expectedResultFile);
    //out.println(sg.toDotString());
    //out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }

  @Test
  public void test2() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "bancosimples/Banco.java";
    String coffeejar = EXAMPLES_JAR + SEP + "bancosimples.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "findConta(nc2).deposito(val);";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "banco", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/bancosimples/TestBancoSimples.test3.dot";
    //PrintWriter out = new PrintWriter(expectedResultFile);
    //out.println(sg.toDotString());
    //out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  @Test
  public void test4() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "bancosimples/Conta.java";
    String coffeejar = EXAMPLES_JAR + SEP + "bancosimples.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "if(Saldo-val>=0){";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "banco", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/bancosimples/TestBancoSimples.test4.dot";
    //PrintWriter out = new PrintWriter(expectedResultFile);
    //out.println(sg.toDotString());
    //out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }

}
