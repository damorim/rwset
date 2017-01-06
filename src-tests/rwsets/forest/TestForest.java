package rwsets.forest;

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


public class TestForest {

  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  String forestjar = EXAMPLES_JAR + SEP + "forest.jar";
  
  @Test
  public void testPrintDepedency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "forest/Bst.java";
    
    //filter vê se tem este nome na package
    String filter = "comp3";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(forestjar)).exists());
    
    //line e de onde a análise e iniciada
    String line = "if (node.left != null) printInOrder(node.left);";
    SimpleGraph sg = depend.Main.analyze(forestjar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/forest/TestForest.testPrint.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  
  @Test
  public void testPrintWithLineContents() throws Exception {
    String strCompUnit = EXAMPLES_SRC + SEP + "forest/Bst.java";
    String exclusionFile = RESOURCES_DIR + SEP + "ExclusionAllJava.txt";
    String exclusionFileForCallGraph = RESOURCES_DIR + SEP + "ExclusionForCallGraph.txt";
    String targetClass = "Lcomp3/Bst";
    String targetMethod = "printInOrder(Lcomp3/Bst;)V";

    // checks
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(exclusionFile)).exists());
    Assert.assertTrue((new File(exclusionFileForCallGraph)).exists());
    Assert.assertTrue((new File(forestjar)).exists());


    String[] args = new String[] { 
        "-appJar=" + forestjar,
        "-printWalaWarnings=" + false, 
        "-exclusionFile=" + exclusionFile,
        "-exclusionFileForCallGraph=" + exclusionFileForCallGraph,
        "-dotPath=" + "/usr/bin/dot", 
        "-appPrefix=" + "comp3",
        "-listAppClasses=" + false, 
        "-listAllClasses=" + false,
        "-listAppMethods=" + false, 
        "-genCallGraph=" + false,
        "-measureTime=" + false, 
        "-reportType=" + "list",
        "-targetClass=" + targetClass, 
        "-targetMethod=" + targetMethod,
        "-targetLine=75"};
    
    // reading and saving command-line properties
    Properties p = CommandLine.parse(args);
    Util.setProperties(p);

    // clearing warnings from WALA
    Warnings.clear();

    MethodDependencyAnalysis mda = new MethodDependencyAnalysis(p);

    // find informed class    
    IClass clazz = depend.Main.findClass(mda);
    //  find informed method
    IMethod method = depend.Main.findMethod(clazz);
    SimpleGraph sg = depend.Main.run(mda, method);
    
    String expectedResultFile = TEST_DIR + SEP + "rwsets/forest/TestForest.testPrintWithLineContents.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toString());
  } 
  
  @Test
  public void testSearchDepedency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "forest/Bst.java";
    
    //filter vê se tem este nome na package
    String filter = "comp3";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(forestjar)).exists());
    
    //line e de onde a análise e iniciada
    //String line = "   if (node == null) {";
    String line = "return search(node.left, n);";
    SimpleGraph sg = depend.Main.analyze(forestjar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/forest/TestForest.testSearch.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
}
