package rwsets.campus;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Test;

import rwsets.Helper;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

import depend.util.graph.SimpleGraph;

public class TestCampus {
  
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  String CAMPUS_JAR = EXAMPLES_JAR + SEP + "campus.jar";
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "campus" + SEP + "globals" + SEP + "Banks.java";
    String campusJar = EXAMPLES_JAR + SEP + "campus.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(campusJar)).exists());
    
    String line = "if (!hasQuestion(question.getTitle())) {";
    SimpleGraph sg = depend.Main.analyze(campusJar, "globals", strCompUnit, line);
    
   // System.out.println(sg.toDotString()); 
    
    String expectedResultFile = TEST_DIR + SEP + "rwsets/campus/TestCampus.dot";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
  
  @Test
  public void testPrimitiveTypeDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "campus/application/Application.java";
    Assert.assertTrue((new File(strCompUnit)).exists());
    
    String line = "public static ArrayList<Question> questionsToSelect = new ArrayList<Question>();";    
    SimpleGraph sg = depend.Main.analyze(CAMPUS_JAR, "application", strCompUnit, line);
    System.out.println(sg.toDotString()); 
    // check
    String expectedResultFile = TEST_DIR + "/rwsets/campus/TestCampus2.dot";
    String expected = Helper.readFile(expectedResultFile);
    Assert.assertEquals(expected, sg.toDotString());
  }
  
 
}