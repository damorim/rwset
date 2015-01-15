/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Josana
 */
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
  
/** 
 * 
 * @author Sekkuar 
 */  
public class Teste {  
  
   public static void main(String[] args) {  
       new Teste().go();  
    }  
      
    public void go(){  
        JFrame janela = new JFrame("Minha Janela");  
        JButton botao = new JButton("clica aqui!");  
          
        botao.addActionListener(new Listener());//cria o actionListener para o botão  
  
  
        janela.setSize(250, 250); //coloca o tamanho da frame  
        janela.add(botao); //coloca o botão na janela  
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        //Se essa janela fechar, todas fecham.  
        janela.setVisible(true); //mostra a janela  
    }  
  
  
    //Classe actionListener do botão  
    class Listener implements ActionListener{  
  
        public void actionPerformed(ActionEvent e) {  
            JFrame nova_janela = new JFrame("Outra Janela!");  
            // cria uma nova janela  
            JLabel label = new JLabel("Você abriu uma janela nova!");  
  
            nova_janela.setSize(200,200);  
            nova_janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
            //se esta janela for fechada, o programa continua rodando  
  
            nova_janela.add(label);  
            nova_janela.setVisible(true);  
        }  
  
    }  
}  

