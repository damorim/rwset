package rwsets.delivery;

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

public class TestDelivery {
  
  String USER_DIR = System.getProperty("user.dir");
  String SEP = System.getProperty("file.separator");
  String EXAMPLES = USER_DIR + SEP + "example-apps";
  String TEST_DIR = USER_DIR + SEP + "src-tests";
  String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
  String EXAMPLES_JAR = EXAMPLES; 
  String RESOURCES_DIR = USER_DIR + SEP + "dat";

  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "deliveryexpress/src/delivery/business/controllers/ControladorCliente.java";
    String coffeejar = EXAMPLES_JAR + SEP + "delivery.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "if (existeCliente(cliente.gettelefone())) {";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "delivery", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/delivery/TestDelivery.test0.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+"\n");
  }
  
  @Test
  public void test1() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "deliveryexpress/src/delivery/business/controllers/ControladorCliente.java";
    String coffeejar = EXAMPLES_JAR + SEP + "delivery.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "repo.adicionar(cliente);";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "delivery", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/delivery/TestDelivery.test1.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  
  @Test
  public void test2() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "deliveryexpress/src/delivery/business/reports/GeradorRelatorioProdutos.java";
    String coffeejar = EXAMPLES_JAR + SEP + "delivery.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "if (pedidoQueContemProduto.getCodidoPedido() == pedidoDoPeriodo.getCodidoPedido()) {";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "delivery", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/delivery/TestDelivery.test2.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  
  @Test
  public void test3() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = EXAMPLES_SRC + SEP + "deliveryexpress/src/delivery/business/reports/PedidosComProdutosPeriodo.java";
    String coffeejar = EXAMPLES_JAR + SEP + "delivery.jar";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());
    
    String line = "if(codigoString1.equalsIgnoreCase(codigoString2)) {";
    SimpleGraph sg = depend.Main.analyze(coffeejar, "delivery", strCompUnit, line);
        
    String expectedResultFile = TEST_DIR + SEP + "rwsets/delivery/TestDelivery.test3.dot";
//    PrintWriter out = new PrintWriter(expectedResultFile);
//    out.println(sg.toDotString());
//    out.close();
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString()+ "\n");
  }
  
}
