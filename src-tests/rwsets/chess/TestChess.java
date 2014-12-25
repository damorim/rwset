package rwsets.chess;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

import rwsets.Helper;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

import depend.util.graph.SimpleGraph;

public class TestChess{
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";
  
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "chess/Main.java";
    String coffeejar = EXAMPLES_JAR + SEP + "chess.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "this.b = new Board(c, gb);";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "chess", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/chess/TestChess.test0.dot";
    //PrintWriter out = new PrintWriter(expectedResultFile);
    //out.println(sg.toDotString());
    //out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  @Test
  public void test1() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    // DA ERRADO, pois o ExclusionAllJava.txt exclui classes que herdam das classes excluidas. Neste caso
    // Board extende de JPanel, nao sendo incluso na hieraquia de classes.
    String strCompUnit = EXAMPLES_SRC + SEP + "chess/Board.java";
    String coffeejar = EXAMPLES_JAR + SEP + "chess.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "if(computer)";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "chess", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/chess/TestChess.test1.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  @Test
  public void test2() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "chess/Chess.java";
    String coffeejar = EXAMPLES_JAR + SEP + "chess.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "reset();";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "chess", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/chess/TestChess.test2.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }


}
