package rwsets.cqueue;


import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
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

public class TestCqueue {

  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  String cqueuejar = EXAMPLES_JAR + SEP + "cqueue.jar";
  
  @Test
  public void testPopDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "cqueue/Pile.java";
    //filter vê se tem este nome na package
    String filter = "comp2";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(cqueuejar)).exists());
    
    //line e de onde a análise e iniciada
    String line = "if(this.next == null) return new Pile();";
    SimpleGraph sg = depend.Main.analyze(cqueuejar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/cqueue/TestCqueue.testpop.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  @Test
  public void testPushbackDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    
    
    String strCompUnit = EXAMPLES_SRC + SEP + "cqueue/Queue.java";
    //filter vê se tem este nome na package
    String filter = "comp2";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(cqueuejar)).exists());
    
    //line e de onde a análise e iniciada
    String line = "if (this.n == -1) {";
    SimpleGraph sg = depend.Main.analyze(cqueuejar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/cqueue/TestCqueue.testqueue.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
    
  }
    
}
