package rwsets.ip;


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

  public class TestIp {
    
    String USER_DIR = System.getProperty("user.dir");
    String SEP = System.getProperty("file.separator");
    String EXAMPLES = USER_DIR + SEP + "example-apps";
    String TEST_DIR = USER_DIR + SEP + "src-tests";
    String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
    String EXAMPLES_JAR = EXAMPLES; 
    String RESOURCES_DIR = USER_DIR + SEP + "dat";
    String IP_JAR = EXAMPLES_JAR + SEP + "ip.jar";
    
    @Test
    public void testBasicDoesNotCrash() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
      String strCompUnit = EXAMPLES_SRC + SEP + "ip" + SEP + "dados" + SEP + "repositorio" + SEP + "RepAdmArquivo.java";
      Assert.assertTrue((new File(strCompUnit)).exists());
      String line = "this.vetorAdministradores = new Vector<Administrador>();";    
      // check for crashes
      depend.Main.analyze(IP_JAR, "dados/repositorio", strCompUnit, line);
    }
    
    @Test
    public void testPrimitiveTypeDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

      String strCompUnit = EXAMPLES_SRC + SEP + "ip" + SEP + "dados" + SEP + "repositorio" + SEP + "RepAdmArquivo.java";
      Assert.assertTrue((new File(strCompUnit)).exists());
      
      String line = "teste.atualizar(adminSubstituir);";    
      SimpleGraph sg = depend.Main.analyze(IP_JAR, "dados/repositorio", strCompUnit, line);
      System.out.println(sg.toDotString());  
      // check
      String expectedResultFile = TEST_DIR + SEP + "rwsets" + SEP + "ip" + SEP + "TestIpDep.dot";
      String expected = Helper.readFile(expectedResultFile);
      Assert.assertEquals(expected, sg.toDotString());
    }
    
    @Test
    public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

      String strCompUnit = EXAMPLES_SRC + SEP + "ip/dados/repositorio/RepAdmArquivo.java";
      String ipJar = EXAMPLES_JAR + SEP + "ip.jar";
      
      Assert.assertTrue((new File(strCompUnit)).exists());
      Assert.assertTrue((new File(ipJar)).exists());
      
      String line = "Vector<Administrador> listAdministradors = new Vector<Administrador>();";
      SimpleGraph sg = depend.Main.analyze(ipJar, "dados/repositorio", strCompUnit, line);
      //System.out.println(sg.toDotString());    
      String expectedResultFile = TEST_DIR + SEP + "rwsets/ip/TestIp.dot";
      Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
    }
}
