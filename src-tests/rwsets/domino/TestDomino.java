package rwsets.domino;

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

public class TestDomino {
  
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";

  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException 
  {
    String strCompUnit = EXAMPLES_SRC + SEP + "domino" + SEP + "Partida.java";
    String dominojar = EXAMPLES_JAR + SEP + "domino.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(dominojar)).exists());
    
    String line = "proximoJogador = duplas[ij[1]].getDupla()[ij[0]];";
    SimpleGraph sg = depend.Main.analyze(dominojar, "domino", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/domino/TestDomino.test0.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }

  @Test
  public void test1() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException 
  {
    String strCompUnit = EXAMPLES_SRC + SEP + "domino" + SEP + "Peca.java";
    String dominojar = EXAMPLES_JAR + SEP + "domino.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(dominojar)).exists());
    
    String line = "if (p.getNumeros()[0] == this.numeros[0] && p.getNumeros()[1] == this.numeros[1])";
    SimpleGraph sg = depend.Main.analyze(dominojar, "domino", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/domino/TestDomino.test1.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }

  @Test
  public void test2() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException 
  {
    String strCompUnit = EXAMPLES_SRC + SEP + "domino" + SEP + "Peca.java";
    String dominojar = EXAMPLES_JAR + SEP + "domino.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(dominojar)).exists());
    
    String line = "if (numeros[0] == numeros[1])";
    SimpleGraph sg = depend.Main.analyze(dominojar, "domino", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/domino/TestDomino.test2.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }

  @Test
  public void test3() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException 
  {
    String strCompUnit = EXAMPLES_SRC + SEP + "domino" + SEP + "Main.java";
    String dominojar = EXAMPLES_JAR + SEP + "domino.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(dominojar)).exists());
    
    String line = "minhaPartida.iniciarPartida(qtdPecas);";
    SimpleGraph sg = depend.Main.analyze(dominojar, "domino", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/domino/TestDomino.test3.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  
}