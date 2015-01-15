package rwsets.chat;

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

  public class TestChat {
    
    String USER_DIR = System.getProperty("user.dir");
    String SEP = System.getProperty("file.separator");
    String EXAMPLES = USER_DIR + SEP + "example-apps";
    String TEST_DIR = USER_DIR + SEP + "src-tests";
    String EXAMPLES_SRC = EXAMPLES +  SEP + "src";
    String EXAMPLES_JAR = EXAMPLES; 
    String RESOURCES_DIR = USER_DIR + SEP + "dat";
    String CHAT_JAR = EXAMPLES_JAR + SEP + "chat.jar";
    
    @Test
    public void testBasicDoesNotCrash() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
      String strCompUnit = EXAMPLES_SRC + SEP + "chat" + SEP + "chat" + SEP + "server" + SEP + "MultiChatServer.java";
      Assert.assertTrue((new File(strCompUnit)).exists());
      String line = "thread = new MultiChatServerThread(info, entry, SenderSocket, connected_hand_shake, disconnected_hand_shake, whois_hand_shake);";    
      // check for crashes
      depend.Main.analyze(CHAT_JAR, "server", strCompUnit, line);
    }
    
    
    @Test
    public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

      String strCompUnit = EXAMPLES_SRC + SEP + "chat" + SEP + "chat" + SEP + "server" + SEP + "MultiChatServer.java";
      String chatJar = EXAMPLES_JAR + SEP + "chat.jar";
      
      Assert.assertTrue((new File(strCompUnit)).exists());
      Assert.assertTrue((new File(chatJar)).exists());
      
      String line = "UserInfo entry = new UserInfo(out, in, (\"\" + SenderSocket.getInetAddress()).substring(1), SenderSocket.getPort(), name);";
      SimpleGraph sg = depend.Main.analyze(chatJar, "server", strCompUnit, line);
      System.out.println(sg.toDotString());    
      String expectedResultFile = TEST_DIR + SEP + "rwsets/ip/TestIp.dot";
      Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
    }
}
