package rwsets.database;



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

public class TestDatabase {

  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  String databasejar = EXAMPLES_JAR + SEP + "database.jar";
  
  @Test
  public void testAddDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "database/RepFunc.java";
    
    //filter vê se tem este nome na package
    String filter = "comp";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(databasejar)).exists());
    
    //line e de onde a análise e iniciada
    String line = " if (repList.insertCheck(repList, f)) {";
    SimpleGraph sg = depend.Main.analyze(databasejar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/database/TestDatabase.testAdd.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  @Test
  public void testAnalysisWithLineContents() throws Exception {
    String strCompUnit = EXAMPLES_SRC + SEP + "database/RepFunc.java";
    String exclusionFile = RESOURCES_DIR + SEP + "ExclusionAllJava.txt";
    String exclusionFileForCallGraph = RESOURCES_DIR + SEP + "ExclusionForCallGraph.txt";
    String databasejar = EXAMPLES_JAR + SEP + "database.jar";
    String targetClass = "Lcomp/RepFunc";
    String targetMethod = "addFuncRepLista(Lcomp/Funcionario;)Z";

    // checks
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(exclusionFile)).exists());
    Assert.assertTrue((new File(exclusionFileForCallGraph)).exists());
    Assert.assertTrue((new File(databasejar)).exists());


    String[] args = new String[] { 
        "-appJar=" + databasejar,
        "-printWalaWarnings=" + false, 
        "-exclusionFile=" + exclusionFile,
        "-exclusionFileForCallGraph=" + exclusionFileForCallGraph,
        "-dotPath=" + "/usr/bin/dot", 
        "-appPrefix=" + "comp",
        "-listAppClasses=" + false, 
        "-listAllClasses=" + false,
        "-listAppMethods=" + false, 
        "-genCallGraph=" + false,
        "-measureTime=" + false, 
        "-reportType=" + "list",
        "-targetClass=" + targetClass, 
        "-targetMethod=" + targetMethod,
        "-targetLine=33"};
    
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
    
    String expectedResultFile = TEST_DIR + SEP + "rwsets/database/TestDatabase.testAnalysisWithLineContents.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toString());
  } 
  
  @Test
  public void testFSearchDepedency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "database/List.java";
    
    //filter vê se tem este nome na package
    String filter = "comp";
        
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(databasejar)).exists());
    
    //line e de onde a análise e iniciada
    String line = " if (lista == null || lista.func == null) {";
    SimpleGraph sg = depend.Main.analyze(databasejar, filter, strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/database/TestDatabase.testFSearch.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  
}
